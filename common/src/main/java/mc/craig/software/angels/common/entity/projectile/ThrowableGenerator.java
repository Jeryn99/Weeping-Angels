package mc.craig.software.angels.common.entity.projectile;

import mc.craig.software.angels.common.WAConstants;
import mc.craig.software.angels.common.WAEntities;
import mc.craig.software.angels.common.blockentity.GeneratorBlockEntity;
import mc.craig.software.angels.common.blocks.GeneratorBlock;
import mc.craig.software.angels.common.blocks.WABlocks;
import mc.craig.software.angels.common.items.WAItems;
import mc.craig.software.angels.util.WAHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
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
    public boolean save(CompoundTag compound) {
        compound.putBoolean(WAConstants.ACTIVATED, isActivated);
        return super.save(compound);
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        setActivated(compound.getBoolean(WAConstants.ACTIVATED));
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        BlockPos pos = result.getBlockPos().above();
        BlockState blockState = level.getBlockState(pos);


        if (blockState.getMaterial().isReplaceable()) {
            level.setBlock(pos, WABlocks.CHRONODYNE_GENERATOR.get().defaultBlockState().setValue(GeneratorBlock.ROTATION, random.nextInt(15)), 3);
            if (level.getBlockEntity(pos) instanceof GeneratorBlockEntity generatorBlockEntity) {
                generatorBlockEntity.setActivated(isActivated);
                generatorBlockEntity.sendUpdates();
            }
            remove(RemovalReason.DISCARDED);
            return;
        }


        if (!level.isClientSide) {
            Entity thrower = getOwner();
            if (thrower instanceof ServerPlayer serverPlayer) {
                ItemStack stack = new ItemStack(WAItems.CHRONODYNE_GENERATOR.get());
                if (!serverPlayer.getInventory().add(stack)) {
                    serverPlayer.spawnAtLocation(stack);
                }
                remove(RemovalReason.DISCARDED);
            }
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
