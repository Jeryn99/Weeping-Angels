package me.sub.angels.common.tileentities;

import me.sub.angels.common.entities.EntityWeepingAngel;
import me.sub.angels.common.misc.WATeleporter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntitySnowArm extends TileEntity implements ITickable {
	
	private AxisAlignedBB AABB = new AxisAlignedBB(0.2, 0, 0, 0.8, 2, 0.1);
	
	@Override
	public void update() {
		if (!world.getEntitiesWithinAABB(EntityPlayer.class, AABB.offset(getPos())).isEmpty() && !world.isRemote) {
			EntityWeepingAngel angel = new EntityWeepingAngel(world);
			angel.setChild(false);
			WATeleporter.teleportDimEntity(angel, getPos(), angel.dimension, null);
			world.spawnEntity(angel);
			world.setBlockToAir(getPos());
		}
	}
	
}
