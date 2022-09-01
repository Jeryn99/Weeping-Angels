package mc.craig.software.angels.common.entity.angel;

import mc.craig.software.angels.common.WAEntities;
import mc.craig.software.angels.common.blockentity.GeneratorBlockEntity;
import mc.craig.software.angels.common.blocks.WABlocks;
import mc.craig.software.angels.common.items.WAItems;
import mc.craig.software.angels.util.WAHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class ThrowableGenerator extends ThrowableItemProjectile {

    public boolean isActivated = false;
    public ThrowableGenerator(Level level) {
        super(WAEntities.GENERATOR.get(), level);
    }

    public ThrowableGenerator(double d, double e, double f, Level level) {
        super(WAEntities.GENERATOR.get(), d, e, f, level);
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public ThrowableGenerator(LivingEntity livingEntity, Level level) {
        super(WAEntities.GENERATOR.get(), livingEntity, level);
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        BlockPos pos = result.getBlockPos().above();
        BlockState blockState = level.getBlockState(pos);

        if(blockState.getMaterial().isReplaceable()) {
            level.setBlock(pos, WABlocks.CHRONODYNE_GENERATOR.get().defaultBlockState(), 3);
            if (level.getBlockEntity(pos) instanceof GeneratorBlockEntity generatorBlockEntity) {
                generatorBlockEntity.setActivated(isActivated);
                generatorBlockEntity.sendUpdates();
            }
            remove(RemovalReason.DISCARDED);
        } else {
            ItemEntity itemEntity = new ItemEntity(EntityType.ITEM, level);
            itemEntity.setItem(new ItemStack(WAItems.CHRONODYNE_GENERATOR.get()));
            itemEntity.moveTo(pos.getX(), pos.getY(), pos.getZ());
            level.addFreshEntity(itemEntity);
        }
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(getDefaultItem());
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return WAHelper.spawnPacket(this);
    }


    @Override
    protected Item getDefaultItem() {
        return WABlocks.CHRONODYNE_GENERATOR.get().asItem();
    }
}
