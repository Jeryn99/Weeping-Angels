package me.swirtzly.angels.common.tileentities;

import me.swirtzly.angels.common.WAObjects;
import me.swirtzly.angels.common.entities.EntityAnomaly;
import me.swirtzly.angels.common.entities.EntityWeepingAngel;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityChronodyneGenerator extends TileEntity implements ITickableTileEntity {
	
	private AxisAlignedBB AABB = new AxisAlignedBB(0.2, 0, 0, 0.8, 2, 0.1);
	
	public TileEntityChronodyneGenerator() {
		super(WAObjects.Tiles.CG.get());
	}
	
	@Override
	public void tick() {
		
		if (!world.getEntitiesWithinAABB(EntityWeepingAngel.class, AABB.offset(getPos())).isEmpty() && !world.isRemote) {
			world.getEntitiesWithinAABB(EntityWeepingAngel.class, AABB.offset(getPos())).forEach(entityWeepingAngel -> {
				if (world.isRemote) {
					world.addParticle(ParticleTypes.EXPLOSION, getPos().getX(), getPos().getY(), getPos().getZ(), 1.0D, 0.0D, 0.0D);
				} else {
					EntityAnomaly a = new EntityAnomaly(world);
					a.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), 0, 0);
					world.addEntity(a);
				}
				entityWeepingAngel.dropStuff();
				entityWeepingAngel.remove();
				world.removeBlock(getPos(), false);
			});
		}
	}
	
}
