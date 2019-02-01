package me.sub.angels.common.tileentities;

import me.sub.angels.common.entities.EntityAnomaly;
import me.sub.angels.common.entities.EntityWeepingAngel;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BoundingBox;

public class TileEntityChronodyneGenerator extends BlockEntity implements Tickable
{

    private BoundingBox AABB = new BoundingBox(0.2, 0, 0, 0.8, 2, 0.1);

    public TileEntityChronodyneGenerator(BlockEntityType<?> blockEntityType_1)
    {
        super(blockEntityType_1);
    }

    @Override public void tick()
    {
        if (!world.getEntities(EntityWeepingAngel.class, AABB.offset(getPos()), angel -> true).isEmpty() && !world.isClient) {

            for (EntityWeepingAngel angel : world.getEntities(EntityWeepingAngel.class, AABB.offset(getPos()), angel -> true)) {
                if (world.isClient) {
                    //TODO
//                    this.world.addParticle(EnumParticleTypes.EXPLOSION_LARGE, this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 1.0D, 0.0D, 0.0D);
                } else {
                    EntityAnomaly a = new EntityAnomaly(world);
                    a.setPositionAndAngles(pos.getX(), pos.getY(), pos.getZ(), 0, 0);
                    world.spawnEntity(a);
                }

                angel.invalidate();
            }

            world.setBlockState(getPos(), Blocks.AIR.getDefaultState());
        }
    }
}
