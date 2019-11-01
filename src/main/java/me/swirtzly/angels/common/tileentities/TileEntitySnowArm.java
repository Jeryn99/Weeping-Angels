package me.swirtzly.angels.common.tileentities;

import me.swirtzly.angels.common.WAObjects;
import me.swirtzly.angels.common.entities.EntityWeepingAngel;
import me.swirtzly.angels.utils.Teleporter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntitySnowArm extends TileEntity implements ITickableTileEntity {
	
	private AxisAlignedBB AABB = new AxisAlignedBB(0.2, 0, 0, 0.8, 2, 0.1);
	
	public TileEntitySnowArm() {
		super(WAObjects.Tiles.ARM);
	}
	
	@Override
	public void tick() {
		if (!world.getEntitiesWithinAABB(PlayerEntity.class, AABB.offset(getPos())).isEmpty() && !world.isRemote) {
			EntityWeepingAngel angel = new EntityWeepingAngel(world);
			angel.setChild(false);
			Teleporter.move(angel, world.dimension.getType(), getPos());
			world.addEntity(angel);
			world.removeBlock(getPos(), true);
		}
	}
}
