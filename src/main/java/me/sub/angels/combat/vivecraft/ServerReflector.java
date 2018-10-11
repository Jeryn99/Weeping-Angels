package me.sub.angels.combat.vivecraft;

import me.sub.angels.WeepingAngels;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.UUID;

public class ServerReflector extends VivecraftReflector {

    private int enabled = -1;

    //Vivecraft Client/Non-VR
    private Field fVivePlayers;

    private Method mGetVivePlayer;
    private Method mContainsVivePlayer;

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
    private Method mNormalize;

    @Override
    public boolean init() {
        enabled = 0;
        WeepingAngels.LOGGER.info("Checking for Vivecraft Client...");
        try {
            //Vivecraft Client/Non-VR
            Class<?> cNetworkHelper = Class.forName("com.mtbs3d.minecrift.api.NetworkHelper");
            Class<?> cVivePlayer = Class.forName("com.mtbs3d.minecrift.api.VivePlayer");

            fVivePlayers = cNetworkHelper.getDeclaredField("vivePlayers");

            mGetVivePlayer = fVivePlayers.getClass().getMethod("get",Object.class);
            mContainsVivePlayer = fVivePlayers.getClass().getMethod("containsKey",Object.class);

            mGetControllerDir = cVivePlayer.getDeclaredMethod("getControllerDir",int.class);
            mGetHMDDir = cVivePlayer.getDeclaredMethod("getHMDDir");
            mGetControllerPos = cVivePlayer.getDeclaredMethod("getControllerPos",int.class);
            mGetHMDPos = cVivePlayer.getDeclaredMethod("getHMDPos");
            isVR = cVivePlayer.getMethod("isVR");

            WeepingAngels.LOGGER.info("Vivecraft Client detected! Enabling compatibility features.");

        }catch (Exception e){
            enabled = -1;
            WeepingAngels.LOGGER.info("No Vivecraft Client detected!");
        }

        if(enabled<0)
        {
            //Vivecraft Forge Extensions
            enabled = 1;
            try{
                Class<?> cVRPlayerData = Class.forName("com.techjar.vivecraftforge.util.VRPlayerData");
                Class<?> cObjectInfo =  Class.forName("com.techjar.vivecraftforge.util.VRPlayerData.ObjectInfo");

                fHead = cVRPlayerData.getDeclaredField("head");
                fController0 = cVRPlayerData.getDeclaredField("controller0");
                fController1 = cVRPlayerData.getDeclaredField("controller1");

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
                mNormalize = cQuaternion.getMethod("normalize");

                WeepingAngels.LOGGER.info("Vivecraft Forge Extensions detected! Enabling compatability features.");
            }catch (Exception e)
            {
                enabled = -1;
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
                Object vivePlayers = fVivePlayers.get(null);
                if ((boolean) mContainsVivePlayer.invoke(vivePlayers, uuid)) {
                    Object vivePlayer = mGetVivePlayer.invoke(vivePlayers, uuid);
                    return (boolean) isVR.invoke(vivePlayer);
                }
            }
            else if(enabled==1) {
                return (boolean)mHasPlayerData.invoke(null,player);
            }

        } catch (Exception e) {
            WeepingAngels.LOGGER.warn("Vivecraft Client: Unknown Error Parsing isVRPlayer\n" + e.getStackTrace());
        }

        return false;
    }

    @Override
    public Vec3d getHMDPos(EntityPlayer player) {
        try {
            UUID uuid = player.getUniqueID();
            if(enabled==0) {
                //Network Character - attempt to get from NetworkHelper
                Object vivePlayers = fVivePlayers.get(null);
                if ((boolean) mContainsVivePlayer.invoke(vivePlayers, uuid)) {
                    Object vivePlayer = mGetVivePlayer.invoke(vivePlayers, uuid);
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
            WeepingAngels.LOGGER.warn("Vivecraft Client: Unknown Error Parsing getHMDPos\n" + e.getStackTrace());
        }
        return player.getPositionVector().add(0, 1.62, 0);
    }

    @Override
    public Vec3d getHMDRot(EntityPlayer player) {
        try {
            UUID uuid = player.getUniqueID();
            if(enabled==0) {
                //Network Character - attempt to get from NetworkHelper
                Object vivePlayers = fVivePlayers.get(null);
                if ((boolean) mContainsVivePlayer.invoke(vivePlayers, uuid)) {
                    Object vivePlayer = mGetVivePlayer.invoke(vivePlayers, uuid);
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

                mNormalize.invoke(quaternion);

                return (Vec3d)mVecMultiply.invoke(quaternion,new Vec3d(1,1,1));
            }
        } catch (Exception e) {
            WeepingAngels.LOGGER.info("Vivecraft Client: Unknown Error Parsing getHMDRot\n" + e.getMessage());
        }
        return player.getLookVec();
    }

    @Override
    public Vec3d getControllerPos(EntityPlayer player, int c) {
        try {
            UUID uuid = player.getUniqueID();
            if(enabled==0) {
                //Network Character - attempt to get from NetworkHelper
                Object vivePlayers = fVivePlayers.get(null);
                if ((boolean) mContainsVivePlayer.invoke(vivePlayers, uuid)) {
                    Object vivePlayer = mGetVivePlayer.invoke(vivePlayers, uuid);
                    return (Vec3d) mGetControllerPos.invoke(vivePlayer,c);
                }
            }
            else if(enabled==1)
            {
                Object playerController = (c==0)?fController0.get(mGetPlayerData.invoke(null,player)):fController1.get(mGetPlayerData.invoke(null,player));

                float X = fPosX.getFloat(playerController);
                float Y = fPosY.getFloat(playerController);
                float Z = fPosZ.getFloat(playerController);

                return new Vec3d(X,Y,Z);
            }

        } catch (Exception e) {
            WeepingAngels.LOGGER.warn("Vivecraft Client: Unknown Error Parsing getControllerPos\n" + e.getMessage());
        }
        return player.getPositionVector().add(0, 1.62, 0);
    }

    @Override
    public Vec3d getControllerRot(EntityPlayer player, int c) {
        try {
            UUID uuid = player.getUniqueID();
            if(enabled==0) {
                //Network Character - attempt to get from NetworkHelper
                Object vivePlayers = fVivePlayers.get(null);
                if ((boolean) mContainsVivePlayer.invoke(vivePlayers, uuid)) {
                    Object vivePlayer = mGetVivePlayer.invoke(vivePlayers, uuid);
                    return (Vec3d) mGetControllerDir.invoke(vivePlayer, c);
                }
            }
            else if(enabled==1)
            {
                Object playerController = (c==0)?fController0.get(mGetPlayerData.invoke(null,player)):fController1.get(mGetPlayerData.invoke(null,player));

                float W = fRotW.getFloat(playerController);
                float X = fRotX.getFloat(playerController);
                float Y = fRotY.getFloat(playerController);
                float Z = fRotZ.getFloat(playerController);

                Object quaternion = conQuaternion.newInstance(W,X,Y,Z);

                mNormalize.invoke(quaternion);

                return (Vec3d)mVecMultiply.invoke(quaternion,new Vec3d(1,1,1));
            }
        } catch (Exception e) {
            WeepingAngels.LOGGER.warn("Vivecraft Client: Unknown Error Parsing getControllerRot\n" + e.getMessage());
        }
        return player.getLookVec();
    }
}