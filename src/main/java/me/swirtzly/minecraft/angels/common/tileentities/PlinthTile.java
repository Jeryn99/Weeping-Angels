package me.swirtzly.minecraft.angels.common.tileentities;

import me.swirtzly.minecraft.angels.client.poses.AngelPoses;
import me.swirtzly.minecraft.angels.common.WAObjects;
import me.swirtzly.minecraft.angels.common.entities.AngelEnums;
import me.swirtzly.minecraft.angels.common.entities.AngelEnums.AngelType;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;

public class PlinthTile extends TileEntity implements ITickableTileEntity {
	
	private boolean hasSpawned = false;
	private int rotation = 0;
	private String type = AngelEnums.AngelType.ANGELA_MC.name();
	private ResourceLocation pose = AngelPoses.getRandomPose().getRegistryName();
	
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
	public void read(BlockState state, CompoundNBT compound) {
		super.read(state, compound);
		setHasSpawned(compound.getBoolean("hasSpawned"));
		setPose(new ResourceLocation(compound.getString("pose")));
		rotation = compound.getInt("rotation");
		type = compound.getString("model");
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		compound.putBoolean("hasSpawned", hasSpawned);
		compound.putInt("rotation", rotation);
		compound.putString("model", type);
		compound.putString("pose", pose.toString());
		return compound;
	}

	public AngelEnums.AngelType getAngelType() {
		return AngelEnums.AngelType.valueOf(type.isEmpty() ? AngelType.ANGELA_MC.name() : type);
	}

	public void setAngelType(String type) {
		this.type = type;
	}

	public void setAngelType(AngelEnums.AngelType type) {
		this.type = type.name();
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
		handleUpdateTag(getBlockState(), pkt.getNbtCompound());
	}
	
	@Override
	public void handleUpdateTag(BlockState state, CompoundNBT tag) {
		this.read(state, tag);
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
				angel.setType(type);
				angel.setLocationAndAngles(pos.getX() + 0.5D, pos.getY() + 1, pos.getZ() + 0.5D, 0, 0);
				angel.setPose(getPose());
				world.addEntity(angel);
				plinth.setHasSpawned(true);
				sendUpdates();
			}
		}
	}
	
	public ResourceLocation getPose() {
		return new ResourceLocation(pose.toString());
	}
	
	public void setPose(ResourceLocation pose) {
		this.pose = pose;
	}
}
