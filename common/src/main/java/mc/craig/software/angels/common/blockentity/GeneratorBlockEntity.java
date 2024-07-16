package mc.craig.software.angels.common.blockentity;

import mc.craig.software.angels.common.WAConstants;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import mc.craig.software.angels.util.WADamageSources;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class GeneratorBlockEntity extends BlockEntity implements BlockEntityTicker<GeneratorBlockEntity> {

    public final AnimationState ANIMATION = new AnimationState();
    private int tickCount = 0;
    private boolean activated = false;
    private boolean hasSpawned = false;

    public GeneratorBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(WABlockEntities.GENERATOR.get(), blockPos, blockState);
    }


    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }


    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag, provider);
        return tag;
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag, HolderLookup.Provider provider) {
        super.saveAdditional(compoundTag, provider);
        compoundTag.putInt(WAConstants.TICK_COUNT, getTickCount());
        compoundTag.putBoolean(WAConstants.ACTIVATED, isActivated());
        compoundTag.putBoolean(WAConstants.SPAWNED, hasSpawned());
    }

    @Override
    protected void loadAdditional(CompoundTag compoundTag, HolderLookup.Provider provider) {
        super.loadAdditional(compoundTag, provider);
        setTickCount(compoundTag.getInt(WAConstants.TICK_COUNT));
        setActivated(compoundTag.getBoolean(WAConstants.ACTIVATED));
        setHasSpawned(compoundTag.getBoolean(WAConstants.SPAWNED));
    }


    public void sendUpdates() {
        if (level != null && getBlockState() != null && getBlockState().getBlock() != null) {
            level.updateNeighbourForOutputSignal(worldPosition, getBlockState().getBlock());
            level.sendBlockUpdated(worldPosition, level.getBlockState(worldPosition), level.getBlockState(worldPosition), 3);
        }
        setChanged();
    }


    public boolean hasSpawned() {
        return hasSpawned;
    }

    public void setHasSpawned(boolean hasSpawned) {
        this.hasSpawned = hasSpawned;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setTickCount(int tickCount) {
        this.tickCount = tickCount;
    }

    public int getTickCount() {
        return tickCount;
    }

    @Override
    public void tick(Level level, BlockPos blockPos, BlockState blockState, GeneratorBlockEntity blockEntity) {

        if (!ANIMATION.isStarted()) {
            ANIMATION.start(tickCount);
        }

        if (isActivated()) {
            tickCount++;
        }

        if (tickCount > 100 && !hasSpawned()) {
            setHasSpawned(true);
            level.playSound(null, worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), SoundEvents.END_PORTAL_SPAWN, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat()));
            tickCount = 0;
            sendUpdates();
        }

        if (hasSpawned()) {
            dragEntitiesAround(level);
        }


        if (tickCount > 200) {
            level.removeBlock(blockPos, false);
        }
    }



    private void dragEntitiesAround(Level level) {
        for (Entity entity : level.getEntitiesOfClass(LivingEntity.class, createBoundingBox(worldPosition).inflate(32))) {

            if (entity instanceof WeepingAngel weepingAngel) {
                weepingAngel.setNoAi(false);
                weepingAngel.setHooked(true);
            }

            if (entity.distanceToSqr(worldPosition.getX(), worldPosition.getY(), worldPosition.getZ()) <= 4) {
                if (entity instanceof WeepingAngel weepingAngel) {
                    weepingAngel.remove(Entity.RemovalReason.KILLED);
                } else {
                    Holder.Reference<DamageType> damageType = level.registryAccess()
                            .registryOrThrow(Registries.DAMAGE_TYPE)
                            .getHolderOrThrow(WADamageSources.GENERATOR);
                    entity.hurt(new DamageSource(damageType), 5F);
                }
            }

            BlockPos pos = worldPosition.subtract(entity.blockPosition());
            Vec3 vec = new Vec3(pos.getX(), pos.getY(), pos.getZ()).normalize();
            entity.setDeltaMovement(vec.scale(0.25D));

            for (int i = 0; i < 2; ++i) {
                this.level.addParticle(ParticleTypes.ELECTRIC_SPARK, entity.getRandomX(0.5D), entity.getRandomY(), entity.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
            }
        }
    }


    public static AABB createBoundingBox(BlockPos tilePos) {
        int i = tilePos.getX();
        int j = tilePos.getY();
        int k = tilePos.getZ();
        return new AABB(i, j, k, i, j - 4, k);
    }
}
