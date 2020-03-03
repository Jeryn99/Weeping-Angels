package me.swirtzly.angels.common.tileentities;

import me.swirtzly.angels.client.models.poses.PoseManager;
import me.swirtzly.angels.common.entities.AngelEnums;
import me.swirtzly.angels.common.entities.EntityWeepingAngel;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityStatue extends TileEntity implements ITickable {
	private boolean hasSpawned = false;
    private int rotation = 0;
    private String pose = PoseManager.getRandomPose().getRegistryName();
    
    public boolean getHasSpawned() {
        return hasSpawned;
    }

    public void setHasSpawned(boolean hasSpawned) {
        this.hasSpawned = hasSpawned;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        setHasSpawned(compound.getBoolean("hasSpawned"));
        setPose(compound.getString("pose"));
        setRotation(compound.getInteger("rotation"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setBoolean("hasSpawned", hasSpawned);
        compound.setString("pose", pose);
        compound.setInteger("rotation", rotation);
        return compound;
    }

    public String getPose() {
        return pose;
    }

    public void setPose(String pose) {
        this.pose = pose;
        sendUpdates();
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(pos, 3, getUpdateTag());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
    	 return writeToNBT(new NBTTagCompound());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        handleUpdateTag(pkt.getNbtCompound());
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
        sendUpdates();
    }
    
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return super.getRenderBoundingBox().grow(8, 8, 8);
    }
    
    //We would remove world.scheduleBlockUpdate as it would constantly be called, making Angels spawn every tick :(
    public void sendUpdates() {
    	world.markBlockRangeForRenderUpdate(pos, pos);
    	world.scheduleBlockUpdate(pos, getBlockType(), 0, 0);
        markDirty();
    }

    @Override
    public void update() {
        if (world.isRemote) return;

        if (world.getRedstonePowerFromNeighbors(pos) > 0 && world.getTileEntity(pos) instanceof TileEntityStatue) {
        	TileEntityStatue statue = (TileEntityStatue) world.getTileEntity(pos);
        	if (statue.getHasSpawned() == false) {
        	EntityWeepingAngel angel = new EntityWeepingAngel(world);
            angel.setType(AngelEnums.AngelType.ANGEL_TWO.getId());
            angel.setChild(false);
            angel.setLocationAndAngles(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0, 0);
            angel.setPose(getPose());
            world.spawnEntity(angel);
            world.destroyBlock(pos, false); //This ensures users can't just mine the statue and reuse it like a mob spawner
            statue.setHasSpawned(true);
            sendUpdates();
        	}
        }
    }
}
