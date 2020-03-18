package me.swirtzly.angels.compat.vivecraft;

import me.swirtzly.angels.WeepingAngels;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**	
 * Bridges gap between the Vivecraft Client and Vivecraft Forge Extensions to the mod.	
 * This class specifically is meant to be used for server-side operations. Uses heavy	
 * reflection so dependancy is not required.	
 */
public class ServerReflector extends VivecraftReflector {
	
	// Keeps track of what part of Vivecraft is detected.
    // 0 = Vivecraft Client/Non-VR companion
    // 1 = Vivecraft Forge Extensions
    // -1 = Vanilla Forge
    public int enabled = -1;

    // *** Vivecraft Client/Non-VR Companion methods and fields ***
    private Field fVivePlayers;

    private Method mGetControllerDir;
    private Method mGetControllerPos;
    private Method mGetHMDDir;
    private Method mGetHMDPos;
    private Method isVR;

    // *** Vivecraft Forge Extensions methods and fields ***
    private Constructor<?> conQuaternion;
    
    private Field fHead;	   
    private Field fController0;	
    private Field fController1;	

    private Field fSeated;	
    private Field fWorldScale;	

    private Field fPosX, fPosY, fPosZ;		
    private Field fRotW, fRotX, fRotY, fRotZ;
    
    private Field fVRPlayers;
    private Field fEntities;
    private Field fPosition;
    
    private Method mGetPlayerData;
    private Method mIsVRPlayer;
    private Method mVecMultiply;
    
    /**	
     * Initializes the bridge. Since reflection is used we detect if the classes/methods/fields are	
     * present, and store them for later use. Must be run once before any other function is used, likely	
     * during post-init.	
     *	
     * @return Returns true if any version of vivecraft was detected.	
     */
    @Override
    public boolean init() {
        enabled = 0;
        WeepingAngels.LOGGER.info("Checking for Vivecraft Client...");
        try {

            //Detect classes and fields from the Vivecraft Client/Non-VR Companion
            //Vivecraft Client/Non-VR
            Class<?> cNetworkHelper = Class.forName("org.vivecraft.api.NetworkHelper");
            Class<?> cVivePlayer = Class.forName("org.vivecraft.api.VivePlayer");

            fVivePlayers = cNetworkHelper.getDeclaredField("vivePlayers");

            mGetControllerDir = cVivePlayer.getDeclaredMethod("getControllerDir",int.class);
            mGetHMDDir = cVivePlayer.getDeclaredMethod("getHMDDir");
            mGetControllerPos = cVivePlayer.getDeclaredMethod("getControllerPos",int.class);
            mGetHMDPos = cVivePlayer.getDeclaredMethod("getHMDPos");
            isVR = cVivePlayer.getMethod("isVR");

            WeepingAngels.LOGGER.info("Vivecraft Client detected! Enabling compatibility features.");

        }catch (Exception e){
            enabled = -1;
        }

        if(enabled<0)
        {
            //Vivecraft Forge Extensions
            enabled = 1;
            try{
                Class<?> cVRPlayerData = Class.forName("com.techjar.vivecraftforge.util.VRPlayerData");
                Class<?> cEntityVRObject =  Class.forName("com.techjar.vivecraftforge.entity.EntityVRObject");
                Class<?> cProxyServer = Class.forName("com.techjar.vivecraftforge.proxy.ProxyServer");
                
                fVRPlayers = cProxyServer.getDeclaredField("vrPlayers");
                fEntities = cVRPlayerData.getDeclaredField("entities");
                
                fRotW = cEntityVRObject.getDeclaredField("rotW");
                fRotX = cEntityVRObject.getDeclaredField("rotX");
                fRotY = cEntityVRObject.getDeclaredField("rotY");
                fRotZ = cEntityVRObject.getDeclaredField("rotZ");
                fPosition = cEntityVRObject.getDeclaredField("position");
                
                mIsVRPlayer = cProxyServer.getDeclaredMethod("isVRPlayer", EntityPlayer.class);
                
                Class<?> cQuaternion = Class.forName("com.techjar.vivecraftforge.util.Quaternion");

                conQuaternion = cQuaternion.getConstructor(float.class,float.class,float.class,float.class);
                mVecMultiply = cQuaternion.getMethod("multiply", Vec3d.class);

                WeepingAngels.LOGGER.info("Vivecraft Forge Extensions detected! Enabling compatability features.");
            }catch (Exception e)
            {
                enabled = -1;
            }
        }

        if(enabled<0)
            WeepingAngels.LOGGER.info("Vivecraft not detected!");

        return enabled>=0;
    }
    
    /**	
     * Checks to see if the player is currently using a VR headset.	
     *	
     * @param player The Player	
     * @return Returns true if the player is listed in the VR player lists.	
     */
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
                return (boolean)mIsVRPlayer.invoke(null,player);
            }

        } catch (Exception e) {
            WeepingAngels.LOGGER.warn("Vivecraft Server: Unknown Error Parsing isVRPlayer", e);
        }

        return false;
    }
    
    /**
     * Gets the position of the player's headset. Seems to be in world-space
     *
     * @param player The Player
     * @return The position
     */
    @Override
    public Vec3d getHMDPos(EntityPlayer player) {
        try {
            if(enabled==0) {
                UUID uuid = player.getUniqueID();
                //Network Character - attempt to get from NetworkHelper
                Map<UUID,?> vivePlayers = (Map<UUID,? extends Object>)fVivePlayers.get(null);
                if (vivePlayers.containsKey(uuid)) {
                    Object vivePlayer = vivePlayers.get(uuid);
                    return (Vec3d) mGetHMDPos.invoke(vivePlayer);
                }
            }
            else if(enabled==1)
            {
                Map<EntityPlayer, ?> vrPlayers = (Map<EntityPlayer,? extends Object>)fVRPlayers.get(null);
                if(vrPlayers.containsKey(player)) {
                    Object playerHead = ((List<? extends Object>)fEntities.get(vrPlayers.get(player))).get(0);
                    if(playerHead!=null)
                        return (Vec3d) fPosition.get(playerHead);
                }
            }

        } catch (Exception e) {
            WeepingAngels.LOGGER.warn("Vivecraft Server: Unknown Error Parsing getHMDPos", e);
        }
        return player.getPositionVector().add(0, 1.62, 0);
    }
    

    /**
     * Gets a vector representing the direction the headset is facing. Does not include side-to-side tilts.
     *
     * @param player The Player
     * @return The direction vector
     */
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
                Map<EntityPlayer, ?> vrPlayers = (Map<EntityPlayer,? extends Object>)fVRPlayers.get(null);
                if(vrPlayers.containsKey(player)) {
                    Object playerHead = ((List<? extends Object>)fEntities.get(vrPlayers.get(player))).get(0);
                    if(playerHead!=null){
                        float W = fRotW.getFloat(playerHead);
                        float X = fRotX.getFloat(playerHead);
                        float Y = fRotY.getFloat(playerHead);
                        float Z = fRotZ.getFloat(playerHead);

                        Object quaternion = conQuaternion.newInstance(W,X,Y,Z);

                        return (Vec3d)mVecMultiply.invoke(quaternion,new Vec3d(0,0,-1));
                    }
                }
            }
        } catch (Exception e) {
            WeepingAngels.LOGGER.warn("Vivecraft Server: Unknown Error Parsing getHMDRot", e);
        }
        return player.getLookVec();
    }
    
    /**
     * Gets the position of one of the player's controllers. Seems to be in world-space.
     * Auto calculates controller in seated mode as player wouldn't have tracked controllers
     * in that case. While Vivecraft Client/Non-VR Companion does this for us, the Forge
     * Extensions does not.
     *
     * @return The position
     */
	@Override
	public Vec3d getControllerPos(EntityPlayer player, int c) {
	 try {
            UUID uuid = player.getUniqueID();
            if (enabled == 0) {
                //Vivecraft Client/Non-VR Companion Methods
                Map<UUID, ?> vivePlayers = (Map<UUID, ? extends Object>) fVivePlayers.get(null);
                if (vivePlayers.containsKey(uuid)) {
                    Object vivePlayer = vivePlayers.get(uuid);
                    return (Vec3d) mGetControllerPos.invoke(vivePlayer, c);
                }
            } else if (enabled == 1) {
                //Vivecraft Forge Extensions Methods
                Object playerData = mGetPlayerData.invoke(null, player);

                if (fSeated.getBoolean(playerData)) {
                    //Player is seated - calculate controller position as Vivecraft Client does.
                    Object playerHMD = fHead.get(playerData);

                    float Wr = fRotW.getFloat(playerHMD);
                    float Xr = fRotX.getFloat(playerHMD);
                    float Yr = fRotY.getFloat(playerHMD);
                    float Zr = fRotZ.getFloat(playerHMD);

                    Object quaternion = conQuaternion.newInstance(Wr, Xr, Yr, Zr);

                    Vec3d dir = (Vec3d) mVecMultiply.invoke(quaternion, new Vec3d(0, 0, -1));

                    dir = dir.rotateYaw((float) Math.toRadians(c == 0 ? -35 : 35));
                    dir = new Vec3d(dir.x, 0, dir.z);
                    dir = dir.normalize();

                    float worldScale = fWorldScale.getFloat(playerData);

                    return new Vec3d(fPosX.getFloat(playerHMD), fPosY.getFloat(playerHMD), fPosZ.getFloat(playerHMD)).add(dir.x * 0.3 * worldScale, -0.4 * worldScale, dir.z * 0.3 * worldScale);
                } else {
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
        //Default to player eye position
        return player.getPositionVector().add(0, 1.62, 0);
	}
	

    /**
     * Gets the direction vector of one of the player's controllers. Seems to be in world-space.
     * Auto calculates controller in seated mode as player wouldn't have tracked controllers
     * in that case. While Vivecraft Client/Non-VR Companion does this for us, the Forge
     * Extensions does not.
     *
     * @return The direction
     */
	@Override
	public Vec3d getControllerRot(EntityPlayer player, int c) {
	 try {
            UUID uuid = player.getUniqueID();
            if (enabled == 0) {
                //Vivecraft Client/Non-VR Companion Methods
                Map<UUID, ?> vivePlayers = (Map<UUID, ? extends Object>) fVivePlayers.get(null);
                if (vivePlayers.containsKey(uuid)) {
                    Object vivePlayer = vivePlayers.get(uuid);
                    return (Vec3d) mGetControllerDir.invoke(vivePlayer, c);
                }
            } else if (enabled == 1) {
                //Vivecraft Forge Extensions Methods
                Object playerData = mGetPlayerData.invoke(null, player);

                //Default to left controller in seated mode, same as Vivecraft Client does
                if (fSeated.getBoolean(playerData)) c = 0;

                Object playerController = (c == 0) ? fController0.get(playerData) : fController1.get(playerData);

                //Convert Quaternion to direction vector
                float W = fRotW.getFloat(playerController);
                float X = fRotX.getFloat(playerController);
                float Y = fRotY.getFloat(playerController);
                float Z = fRotZ.getFloat(playerController);

                Object quaternion = conQuaternion.newInstance(W, X, Y, Z);

                return (Vec3d) mVecMultiply.invoke(quaternion, new Vec3d(0, 0, -1));
            }
        } catch (Exception e) {
            WeepingAngels.LOGGER.warn("Vivecraft Server: Unknown Error Parsing getControllerRot", e);
        }
        //Default to player look vector
        return player.getLookVec();
	}
}