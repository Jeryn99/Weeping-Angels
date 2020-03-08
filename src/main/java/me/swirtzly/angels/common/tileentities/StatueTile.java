package me.swirtzly.angels.common.tileentities;

import me.swirtzly.angels.client.models.poses.PoseManager;
import me.swirtzly.angels.common.WAObjects;
import me.swirtzly.angels.common.entities.AngelEnums;
import me.swirtzly.angels.common.entities.WeepingAngelEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Swirtzly on 17/02/2020 @ 12:18
 */
public class StatueTile extends TileEntity implements ITickableTileEntity {
	private int rotation;
	private String pose = PoseManager.getRandomPose().getRegistryName();
	
	public StatueTile() {
		super(WAObjects.Tiles.STATUE.get());
	}
	
	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		setPose(compound.getString("pose"));
		setRotation(compound.getInt("rotation"));
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		compound.putString("pose", pose);
		compound.putInt("rotation", rotation);
		return compound;
	}
	
	public String getPose() {
		return pose;
	}
	
	public void setPose(String pose) {
		this.pose = pose;
		sendUpdates();
	}
	
	public void sendUpdates() {
		markDirty();
	}
	
	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		return new SUpdateTileEntityPacket(pos, 3, getUpdateTag());
	}
	
	@Override
	public CompoundNBT getUpdateTag() {
		return write(new CompoundNBT());
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
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
	
	@Override
	public void tick() {
		if (world.isRemote) return;
		
		if (world.getRedstonePowerFromNeighbors(pos) > 0 && world.getTileEntity(pos) instanceof StatueTile) {
			WeepingAngelEntity angel = new WeepingAngelEntity(world);
			angel.setType(AngelEnums.AngelType.ANGEL_TWO.getId());
			angel.setChild(false);
			angel.setLocationAndAngles(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0, 0);
			angel.setPose(getPose());
			world.addEntity(angel);
			world.removeBlock(getPos(), false);
			sendUpdates();
		}
	}
}
