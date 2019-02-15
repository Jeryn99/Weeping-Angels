package me.suff.angels.common.tileentities;

import me.suff.angels.client.models.poses.PoseManager;
import me.suff.angels.common.entities.AngelEnums;
import me.suff.angels.common.entities.EntityWeepingAngel;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityPlinth extends TileEntity implements ITickable {
	
	private boolean hasSpawned = false;
	private int rotation = 0;
	private String pose = PoseManager.getRandomPose().getRegistryName();
	
	public TileEntityPlinth(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}
	
	public boolean getHasSpawned() {
		return hasSpawned;
	}
	
	public void setHasSpawned(boolean hasSpawned) {
		this.hasSpawned = hasSpawned;
	}
	
	@Override
	public void read(NBTTagCompound compound) {
		super.read(compound);
		setHasSpawned(compound.getBoolean("hasSpawned"));
		setPose(compound.getString("pose"));
		rotation = compound.getInt("rotation");
	}
	
	@Override
	public NBTTagCompound write(NBTTagCompound compound) {
		super.write(compound);
		compound.setBoolean("hasSpawned", hasSpawned);
		compound.setInt("rotation", rotation);
		compound.setString("pose", pose);
		return compound;
	}
	
	public int getRotation() {
		return rotation;
	}
	
	public void setRotation(int rotation) {
		this.rotation = rotation;
		sendUpdates();
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(pos, 3, getUpdateTag());
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		return write(new NBTTagCompound());
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
		handleUpdateTag(pkt.getNbtCompound());
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return super.getRenderBoundingBox().grow(8, 8, 8);
	}
	
	public void sendUpdates() {
		world.markBlockRangeForRenderUpdate(pos, pos);
		world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
		markDirty();
	}
	
	@Override
	public void tick() {
		if (world.isRemote) return;
		
		if (world.getRedstonePowerFromNeighbors(pos) > 0 && world.getTileEntity(pos) instanceof TileEntityPlinth) {
			TileEntityPlinth plinth = (TileEntityPlinth) world.getTileEntity(pos);
			if (!plinth.getHasSpawned()) {
				EntityWeepingAngel angel = new EntityWeepingAngel(world);
				angel.setType(AngelEnums.AngelType.ANGEL_ONE.getId());
				angel.setChild(false);
				angel.setLocationAndAngles(pos.getX(), pos.getY() + 1, pos.getZ(), 0, 0);
				angel.setPose(getPose());
				world.spawnEntity(angel);
				plinth.setHasSpawned(true);
				sendUpdates();
			}
		}
	}
	
	public String getPose() {
		return pose;
	}
	
	public void setPose(String pose) {
		this.pose = pose;
	}
}
