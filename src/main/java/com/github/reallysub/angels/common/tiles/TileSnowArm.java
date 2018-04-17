package com.github.reallysub.angels.common.tiles;

import com.github.reallysub.angels.common.entities.EntityAngel;
import com.github.reallysub.angels.main.AngelUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

public class TileSnowArm extends TileEntity implements ITickable {
	
	private AxisAlignedBB AABB = new AxisAlignedBB(0.2, 0, 0, 0.8, 2, 0.1);
	
	@Override
	public void update() {
		/*
		 * When player collides/jumps over tile, Angel summoned into world and block removed
		 */
		if (!world.getEntitiesWithinAABB(EntityPlayer.class, AABB.offset(getPos())).isEmpty() && !world.isRemote) {
			EntityAngel angel = new EntityAngel(world);
			angel.setChild(false);
			AngelUtils.teleportDimEntity(angel, getPos(), angel.dimension);
			world.spawnEntity(angel);
			world.setBlockToAir(getPos());
		}
	}
	
}
