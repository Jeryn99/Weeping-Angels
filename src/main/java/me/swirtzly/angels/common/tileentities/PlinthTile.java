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
import net.minecraft.util.math.AxisAlignedBB;

public class PlinthTile extends TileEntity implements ITickableTileEntity {
	
	private boolean hasSpawned = false;
	private int rotation = 0;
	private String pose = PoseManager.getRandomPose().getRegistryName();

    public PlinthTile() {
		super(WAObjects.Tiles.PLINTH.get());
	}
	
	public boolean getHasSpawned() {
		return hasSpawned;
	}
	
	public void setHasSpawned(boolean hasSpawned) {
		this.hasSpawned = hasSpawned;
	}
	
	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		setHasSpawned(compound.getBoolean("hasSpawned"));
		setPose(compound.getString("pose"));
		rotation = compound.getInt("rotation");
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		compound.putBoolean("hasSpawned", hasSpawned);
		compound.putInt("rotation", rotation);
		compound.putString("pose", pose);
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
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return super.getRenderBoundingBox().grow(8, 8, 8);
	}
	
	public void sendUpdates() {
		world.updateComparatorOutputLevel(pos, getBlockState().getBlock());
		world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
		markDirty();
	}
	
	@Override
	public void tick() {
		if (world.isRemote) return;

        if (world.getRedstonePowerFromNeighbors(pos) > 0 && world.getTileEntity(pos) instanceof PlinthTile) {
            PlinthTile plinth = (PlinthTile) world.getTileEntity(pos);
			if (!plinth.getHasSpawned()) {
                WeepingAngelEntity angel = new WeepingAngelEntity(world);
				angel.setType(AngelEnums.AngelType.ANGEL_ONE.getId());
				angel.setChild(false);
				angel.setLocationAndAngles(pos.getX(), pos.getY() + 1, pos.getZ(), 0, 0);
				angel.setPose(getPose());
				world.addEntity(angel);
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
