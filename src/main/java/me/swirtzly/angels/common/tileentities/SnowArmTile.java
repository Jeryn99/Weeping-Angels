package me.swirtzly.angels.common.tileentities;

import me.swirtzly.angels.common.WAObjects;
import me.swirtzly.angels.common.entities.WeepingAngelEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class SnowArmTile extends TileEntity implements ITickableTileEntity {
	
	private AxisAlignedBB AABB = new AxisAlignedBB(0.2, 0, 0, 0.8, 2, 0.1);

    public SnowArmTile() {
		super(WAObjects.Tiles.ARM.get());
	}
	
	@Override
	public void tick() {
		if (!world.getEntitiesWithinAABB(PlayerEntity.class, AABB.offset(getPos())).isEmpty() && !world.isRemote) {
            WeepingAngelEntity angel = new WeepingAngelEntity(world);
			angel.setChild(false);
			BlockPos newPos = getPos();
			angel.setPosition(newPos.getX() + 0.5D, newPos.getY(), newPos.getZ() + 0.5D);
			world.addEntity(angel);
			world.removeBlock(getPos(), true);
		}
	}
}
