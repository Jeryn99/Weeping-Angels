package me.sub.angels.common.tiles;

import me.sub.angels.common.entities.EntityAngel;
import me.sub.angels.utils.AngelUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

public class TileCG extends TileEntity implements ITickable {
	
	private AxisAlignedBB AABB = new AxisAlignedBB(0.2, 0, 0, 0.8, 2, 0.1);
	
	@Override
	public void update() {
	
		if (!world.getEntitiesWithinAABB(EntityAngel.class, AABB.offset(getPos())).isEmpty() && !world.isRemote) {
			
			for (EntityAngel angel : world.getEntitiesWithinAABB(EntityAngel.class, AABB.offset(getPos()))) {
				angel.setDead();
			}
			
			world.setBlockToAir(getPos());
		}
	}
	
}
