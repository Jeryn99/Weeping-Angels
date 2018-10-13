package me.sub.angels.combat.vivecraft;

import me.sub.angels.WeepingAngels;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ServerReflector extends VivecraftReflector {

    private int enabled = -1;

    //Vivecraft Client/Non-VR
    private Field fVivePlayers;

    private Method mGetControllerDir;
    private Method mGetControllerPos;
    private Method mGetHMDDir;
    private Method mGetHMDPos;
    private Method isVR;

    //Vivecraft Forge Extensions
    private Constructor<?> conQuaternion;

    private Field fHead;
    private Field fController0;
    private Field fController1;

    private Field fSeated;
    private Field fWorldScale;

    private Field fPosX;
    private Field fPosY;
    private Field fPosZ;
    private Field fRotW;
    private Field fRotX;
    private Field fRotY;
    private Field fRotZ;

    private Method mGetPlayerData;
    private Method mHasPlayerData;

    private Method mVecMultiply;

    @Override
    public boolean init() {
        enabled = 0;
        WeepingAngels.LOGGER.error("Checking for Vivecraft Client...");
        try {
            //Vivecraft Client/Non-VR
            Class<?> cNetworkHelper = Class.forName("com.mtbs3d.minecrift.api.NetworkHelper");
            Class<?> cVivePlayer = Class.forName("com.mtbs3d.minecrift.api.VivePlayer");

            fVivePlayers = cNetworkHelper.getDeclaredField("vivePlayers");

            //mGetVivePlayer = fVivePlayers.getType().getMethod("get",Object.class);
            //mContainsVivePlayer = fVivePlayers.getType().getMethod("containsKey",Object.class);

            mGetControllerDir = cVivePlayer.getDeclaredMethod("getControllerDir",int.class);
            mGetHMDDir = cVivePlayer.getDeclaredMethod("getHMDDir");
            mGetControllerPos = cVivePlayer.getDeclaredMethod("getControllerPos",int.class);
            mGetHMDPos = cVivePlayer.getDeclaredMethod("getHMDPos");
            isVR = cVivePlayer.getMethod("isVR");

            WeepingAngels.LOGGER.error("Vivecraft Client detected! Enabling compatibility features.");

        }catch (Exception e){
            enabled = -1;
            WeepingAngels.LOGGER.error("No Vivecraft Client detected!",e);
        }

        if(enabled<0)
        {
            //Vivecraft Forge Extensions
            enabled = 1;
            try{
                Class<?> cVRPlayerData = Class.forName("com.techjar.vivecraftforge.util.VRPlayerData");
                Class<?> cObjectInfo =  Class.forName("com.techjar.vivecraftforge.util.VRPlayerData$ObjectInfo");

                fHead = cVRPlayerData.getDeclaredField("head");
                fController0 = cVRPlayerData.getDeclaredField("controller0");
                fController1 = cVRPlayerData.getDeclaredField("controller1");

                fSeated = cVRPlayerData.getDeclaredField("seated");
                fWorldScale = cVRPlayerData.getDeclaredField("worldScale");

                fPosX = cObjectInfo.getDeclaredField("posX");
                fPosY = cObjectInfo.getDeclaredField("posY");
                fPosZ = cObjectInfo.getDeclaredField("posZ");
                fRotW = cObjectInfo.getDeclaredField("rotW");
                fRotX = cObjectInfo.getDeclaredField("rotX");
                fRotY = cObjectInfo.getDeclaredField("rotY");
                fRotZ = cObjectInfo.getDeclaredField("rotZ");

                Class<?> cPlayerTracker = Class.forName("com.techjar.vivecraftforge.util.PlayerTracker");

                mGetPlayerData = cPlayerTracker.getMethod("getPlayerData", EntityPlayer.class);
                mHasPlayerData = cPlayerTracker.getMethod("hasPlayerData",EntityPlayer.class);

                Class<?> cQuaternion = Class.forName("com.techjar.vivecraftforge.util.Quaternion");

                conQuaternion = cQuaternion.getConstructor(float.class,float.class,float.class,float.class);
                mVecMultiply = cQuaternion.getMethod("multiply", Vec3d.class);

                WeepingAngels.LOGGER.error("Vivecraft Forge Extensions detected! Enabling compatability features.");
            }catch (Exception e)
            {
                enabled = -1;
                WeepingAngels.LOGGER.error("No Vivecraft Forge Extensions detected!",e);
            }
        }

        return enabled>=0;
    }

    @Override
    public boolean isVRPlayer(EntityPlayer player) {
        if(enabled<0)return false;
        try {
            UUID uuid = player.getUniqueID();
            if(enabled==0) {
                Map<UUID,?> vivePlayers = (Map<UUID,? extends Object>)fVivePlayers.get(null);
                if (vivePlayers.containsKey(uuid)) {
                    Object vivePlayer = vivePlayers.get(uuid);
                    return (boolean) isVR.invoke(vivePlayer);
                }
            }
            else if(enabled==1) {
                return (boolean)mHasPlayerData.invoke(null,player);
            }

        } catch (Exception e) {
            WeepingAngels.LOGGER.warn("Vivecraft Server: Unknown Error Parsing isVRPlayer", e);
        }

        return false;
    }

    @Override
    public Vec3d getHMDPos(EntityPlayer player) {
        try {
            UUID uuid = player.getUniqueID();
            if(enabled==0) {
                //Network Character - attempt to get from NetworkHelper
                Map<UUID,?> vivePlayers = (Map<UUID,? extends Object>)fVivePlayers.get(null);
                if (vivePlayers.containsKey(uuid)) {
                    Object vivePlayer = vivePlayers.get(uuid);
                    return (Vec3d) mGetHMDPos.invoke(vivePlayer);
                }
            }
            else if(enabled==1)
            {
                Object playerHead = fHead.get(mGetPlayerData.invoke(null,player));

                float X = fPosX.getFloat(playerHead);
                float Y = fPosY.getFloat(playerHead);
                float Z = fPosZ.getFloat(playerHead);

                return new Vec3d(X,Y,Z);
            }

        } catch (Exception e) {
            WeepingAngels.LOGGER.warn("Vivecraft Server: Unknown Error Parsing getHMDPos", e);
        }
        return player.getPositionVector().addVector(0, 1.62, 0);
    }

    @Override
    public Vec3d getHMDRot(EntityPlayer player) {
        try {
            UUID uuid = player.getUniqueID();
            if(enabled==0) {
                //Network Character - attempt to get from NetworkHelper
                Map<UUID,?> vivePlayers = (Map<UUID,? extends Object>)fVivePlayers.get(null);
                if (vivePlayers.containsKey(uuid)) {
                    Object vivePlayer = vivePlayers.get(uuid);
                    return (Vec3d) mGetHMDDir.invoke(vivePlayer);
                }
            }
            else if(enabled==1)
            {
                Object playerHead = fHead.get(mGetPlayerData.invoke(null,player));

                float W = fRotW.getFloat(playerHead);
                float X = fRotX.getFloat(playerHead);
                float Y = fRotY.getFloat(playerHead);
                float Z = fRotZ.getFloat(playerHead);

                Object quaternion = conQuaternion.newInstance(W,X,Y,Z);

                //mNormalize.invoke(quaternion);

                return (Vec3d)mVecMultiply.invoke(quaternion,new Vec3d(0,0,-1));
            }
        } catch (Exception e) {
            WeepingAngels.LOGGER.error("Vivecraft Server: Unknown Error Parsing getHMDRot", e);
        }
        return player.getLookVec();
    }

    @Override
    public Vec3d getControllerPos(EntityPlayer player, int c) {
        try {
            UUID uuid = player.getUniqueID();
            if(enabled==0) {
                //Network Character - attempt to get from NetworkHelper
                Map<UUID,?> vivePlayers = (Map<UUID,? extends Object>)fVivePlayers.get(null);
                if (vivePlayers.containsKey(uuid)) {
                    Object vivePlayer = vivePlayers.get(uuid);
                    return (Vec3d) mGetControllerPos.invoke(vivePlayer,c);
                }
            }
            else if(enabled==1)
            {
                Object playerData = mGetPlayerData.invoke(null,player);

                if(fSeated.getBoolean(playerData))
                {
                    Object playerHMD = fHead.get(playerData);

                    float Wr = fRotW.getFloat(playerHMD);
                    float Xr = fRotX.getFloat(playerHMD);
                    float Yr = fRotY.getFloat(playerHMD);
                    float Zr = fRotZ.getFloat(playerHMD);

                    Object quaternion = conQuaternion.newInstance(Wr,Xr,Yr,Zr);

                    Vec3d dir = (Vec3d)mVecMultiply.invoke(quaternion,new Vec3d(0,0,-1));

                    dir = dir.rotateYaw((float) Math.toRadians(c==0?-35:35));
                    dir = new Vec3d(dir.x,0,dir.z);
                    dir = dir.normalize();

                    float worldScale = fWorldScale.getFloat(playerData);

                    return new Vec3d(fPosX.getFloat(playerHMD),fPosY.getFloat(playerHMD),fPosZ.getFloat(playerHMD)).addVector(dir.x * 0.3 * worldScale,-0.4 * worldScale, dir.z * 0.3 * worldScale);
                }
                else {
                    Object playerController = (c == 0) ? fController0.get(playerData) : fController1.get(playerData);

                    float X = fPosX.getFloat(playerController);
                    float Y = fPosY.getFloat(playerController);
                    float Z = fPosZ.getFloat(playerController);

                    return new Vec3d(X, Y, Z);
                }
            }

        } catch (Exception e) {
            WeepingAngels.LOGGER.warn("Vivecraft Server: Unknown Error Parsing getControllerPos", e);
        }
        return player.getPositionVector().addVector(0, 1.62, 0);
    }

    @Override
    public Vec3d getControllerRot(EntityPlayer player, int c) {
        try {
            UUID uuid = player.getUniqueID();
            if(enabled==0) {
                //Network Character - attempt to get from NetworkHelper
                Map<UUID,?> vivePlayers = (Map<UUID,? extends Object>)fVivePlayers.get(null);
                if (vivePlayers.containsKey(uuid)) {
                    Object vivePlayer = vivePlayers.get(uuid);
                    return (Vec3d) mGetControllerDir.invoke(vivePlayer, c);
                }
            }
            else if(enabled==1)
            {
                Object playerData = mGetPlayerData.invoke(null,player);

                if(fSeated.getBoolean(playerData))c=0;

                Object playerController = (c==0)?fController0.get(playerData):fController1.get(playerData);

                float W = fRotW.getFloat(playerController);
                float X = fRotX.getFloat(playerController);
                float Y = fRotY.getFloat(playerController);
                float Z = fRotZ.getFloat(playerController);

                Object quaternion = conQuaternion.newInstance(W,X,Y,Z);

                return (Vec3d)mVecMultiply.invoke(quaternion,new Vec3d(0,0,-1));
            }
        } catch (Exception e) {
            WeepingAngels.LOGGER.warn("Vivecraft Server: Unknown Error Parsing getControllerRot", e);
        }
        return player.getLookVec();
    }
}
