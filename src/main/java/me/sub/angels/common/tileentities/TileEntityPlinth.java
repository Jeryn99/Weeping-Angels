package me.sub.angels.common.tileentities;

import me.sub.angels.client.models.poses.PoseManager;
import me.sub.angels.common.entities.AngelEnums;
import me.sub.angels.common.entities.EntityWeepingAngel;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class TileEntityPlinth extends TileEntity implements ITickable {
	
	private boolean hasSpawned = false;
	private int rotation;
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
		this.rotation = compound.getInteger("rotation");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setBoolean("hasSpawned", hasSpawned);
		compound.setInteger("rotation", rotation);
		compound.setString("pose", pose);
		return compound;
	}
	
	public int getRotation() {
		return this.rotation;
	}
	
	public void setRotation(int rotation) {
		this.rotation = rotation;
		sendUpdates();
	}
	
	@Override
	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.pos, 3, this.getUpdateTag());
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
		handleUpdateTag(pkt.getNbtCompound());
	}
	
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() {
		return super.getRenderBoundingBox().grow(8, 8, 8);
	}
	
	public void sendUpdates() {
		world.markBlockRangeForRenderUpdate(pos, pos);
		world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
		world.scheduleBlockUpdate(pos, this.getBlockType(), 0, 0);
		markDirty();
	}
	
	@Override
	public void update() {
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
