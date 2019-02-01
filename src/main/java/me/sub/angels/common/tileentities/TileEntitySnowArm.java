package me.sub.angels.common.tileentities;

import me.sub.angels.common.entities.EntityWeepingAngel;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BoundingBox;

public class TileEntitySnowArm extends BlockEntity implements Tickable
{

    private BoundingBox AABB = new BoundingBox(0.2, 0, 0, 0.8, 2, 0.1);

    public TileEntitySnowArm(BlockEntityType<?> blockEntityType_1)
    {
        super(blockEntityType_1);
    }

    @Override public void tick()
    {
        if (!world.getEntities(PlayerEntity.class, AABB.offset(getPos()), playerEntity -> true).isEmpty() && !world.isClient) {
            EntityWeepingAngel angel = new EntityWeepingAngel(world);
            angel.setChild(false);
//            WATeleporter.teleportDimEntity(angel, getPos(), angel.dimension.getRawId(), null);
            world.spawnEntity(angel);
            world.setBlockState(getPos(), Blocks.AIR.getDefaultState());
        }
    }
}
