package com.github.reallysub.angels.common.tiles;

import com.github.reallysub.angels.common.entities.EntityAngel;
import com.github.reallysub.angels.main.Utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

public class TileSnowArm extends TileEntity implements ITickable {
	
	private int type = 1;
	private AxisAlignedBB AABB = new AxisAlignedBB(0.2, 0, 0, 0.8, 2, 0.1);
	
	@Override
	public void update() {
		if (!worldObj.getEntitiesWithinAABB(EntityPlayer.class, AABB.offset(getPos())).isEmpty() && !worldObj.isRemote) {
			EntityAngel angel = new EntityAngel(worldObj);
			Utils.teleportEntity(worldObj, angel, getPos().getX(), getPos().getY(), getPos().getZ());
			worldObj.spawnEntityInWorld(angel);
			worldObj.setBlockToAir(getPos());
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		type = compound.getInteger("type");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("type", type);
		return compound;
	}
	
}
