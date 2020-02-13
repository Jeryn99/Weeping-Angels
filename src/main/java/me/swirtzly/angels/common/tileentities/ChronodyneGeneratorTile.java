package me.swirtzly.angels.common.tileentities;

import me.swirtzly.angels.common.WAObjects;
import me.swirtzly.angels.common.entities.AnomalyEntity;
import me.swirtzly.angels.common.entities.WeepingAngelEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;

public class ChronodyneGeneratorTile extends TileEntity implements ITickableTileEntity {
	
	private AxisAlignedBB AABB = new AxisAlignedBB(0.2, 0, 0, 0.8, 2, 0.1); //Area of Effect
	private AxisAlignedBB RENDER_BOX = new AxisAlignedBB(-3, 0, -3, 3, 5, 3);

	public ChronodyneGeneratorTile() {
		super(WAObjects.Tiles.CG.get());
	}
	
	@Override
	public void tick() {

		BlockState blockBelow = world.getBlockState(getPos().down());
		if (blockBelow.isAir(world, pos.down()) || blockBelow.getMaterial().isLiquid() || blockBelow.getBlock() == WAObjects.Blocks.CG.get()) {
			ItemEntity itemEntity = new ItemEntity(EntityType.ITEM, world);
			itemEntity.setPosition(getPos().getX(), getPos().getY(), getPos().getZ());
			itemEntity.setItem(new ItemStack(WAObjects.Items.CHRONODYNE_GENERATOR.get()));
			world.addEntity(itemEntity);
			world.removeBlock(getPos(), true);
		}

		if (!world.getEntitiesWithinAABB(WeepingAngelEntity.class, AABB.offset(getPos())).isEmpty() && !world.isRemote) {
			world.getEntitiesWithinAABB(WeepingAngelEntity.class, AABB.offset(getPos())).forEach(entityWeepingAngel -> {
				if (world.isRemote) {
					world.addParticle(ParticleTypes.EXPLOSION, getPos().getX(), getPos().getY(), getPos().getZ(), 1.0D, 0.0D, 0.0D);
				} else {
					AnomalyEntity a = new AnomalyEntity(world);
					a.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), 0, 0);
					world.addEntity(a);
				}
				entityWeepingAngel.dropAngelStuff();
				entityWeepingAngel.remove();
				world.removeBlock(getPos(), false);
			});
		}
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return RENDER_BOX.offset(this.getPos());
	}
	
}
