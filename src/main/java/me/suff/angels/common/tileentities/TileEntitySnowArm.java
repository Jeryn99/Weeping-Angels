package me.suff.angels.common.tileentities;

import me.suff.angels.common.WAObjects;
import me.suff.angels.common.entities.EntityWeepingAngel;
import me.suff.angels.utils.Teleporter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntitySnowArm extends TileEntity implements ITickable {
	
	private AxisAlignedBB AABB = new AxisAlignedBB(0.2, 0, 0, 0.8, 2, 0.1);
	
	public TileEntitySnowArm() {
		super(WAObjects.Tiles.ARM);
	}
	
	@Override
	public void tick() {
		if (!world.getEntitiesWithinAABB(EntityPlayer.class, AABB.offset(getPos())).isEmpty() && !world.isRemote) {
			EntityWeepingAngel angel = new EntityWeepingAngel(world);
			angel.setChild(false);
			Teleporter.move(angel, world.dimension.getType(), getPos());
			world.spawnEntity(angel);
			world.removeBlock(getPos());
		}
	}
}
