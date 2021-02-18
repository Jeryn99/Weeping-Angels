package me.suff.mc.angels.common.entity;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.enums.WeepingAngelVariants;
import me.suff.mc.angels.enums.WeepingAngelPose;
import me.suff.mc.angels.util.AngelUtils;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/* Created by Craig on 18/02/2021 */
public class WeepingAngelEntity extends QuantumLockBaseEntity  {

    private static final TrackedData< String > CURRENT_POSE = DataTracker.registerData(WeepingAngelEntity.class, TrackedDataHandlerRegistry.STRING);
    private static final TrackedData< String > VARIENT = DataTracker.registerData(WeepingAngelEntity.class, TrackedDataHandlerRegistry.STRING);

    public WeepingAngelEntity(World worldIn, EntityType<QuantumLockBaseEntity> entityType) {
        super(worldIn, entityType);
    }

    public WeepingAngelEntity(World worldIn) {
        super(worldIn, WeepingAngels.WEEPING_ANGEL);
        goalSelector.add(5, new WanderAroundGoal(this, 1.0D));
        this.targetSelector.add(2, new FollowTargetGoal<>(this, PlayerEntity.class, true));
        this.goalSelector.add(7, new WanderAroundFarGoal(this, 1.0D));
        goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 50.0F));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23000000417232513D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0D).add(EntityAttributes.GENERIC_ARMOR, 2.0D).add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 9999D);
    }


    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        getDataTracker().startTracking(CURRENT_POSE, WeepingAngelPose.getRandomPose(AngelUtils.RAND).name());
        getDataTracker().startTracking(VARIENT, AngelUtils.randomVarient().name());
    }

    public String getVarient() {
        return getDataTracker().get(VARIENT);
    }

    public void setVarient(WeepingAngelVariants varient) {
        getDataTracker().set(VARIENT, varient.name());
    }

    public String getAngelPose() {
        return getDataTracker().get(CURRENT_POSE);
    }

    public void setPose(WeepingAngelPose weepingAngelPose) {
        getDataTracker().set(CURRENT_POSE, weepingAngelPose.name());
    }

    @Override
    public void tick() {
        super.tick();
        replaceBlocks(getBoundingBox().expand(24));
    }

    @Override
    public void writeCustomDataToTag(CompoundTag tag) {
        super.writeCustomDataToTag(tag);
    }

    @Override
    public void readCustomDataFromTag(CompoundTag tag) {
        super.readCustomDataFromTag(tag);
    }

    private void replaceBlocks(Box box) {
        if (world.isClient() || age % 100 != 0) return;

        if (world.getLightLevel(getBlockPos()) == 0) {
            return;
        }

        for (BlockPos pos : BlockPos.iterate(new BlockPos(box.maxX, box.maxY, box.maxZ), new BlockPos(box.minX, box.minY, box.minZ))) {
            ServerWorld serverWorld = (ServerWorld) world;
            BlockState blockState = serverWorld.getBlockState(pos);

            if (blockState.getBlock() == Blocks.LAVA) {
                continue;
            }

            if (blockState.getBlock() == Blocks.TORCH || blockState.getBlock() == Blocks.REDSTONE_TORCH || blockState.getBlock() == Blocks.GLOWSTONE) {
                AngelUtils.playBreakEvent(this, pos, Blocks.AIR.getDefaultState());
                return;
            }

            if (blockState.getBlock() == Blocks.REDSTONE_LAMP) {
                if (blockState.get(RedstoneLampBlock.LIT)) {
                    AngelUtils.playBreakEvent(this, pos, blockState.with(RedstoneLampBlock.LIT, false));
                    return;
                }
            }


            if (blockState.getBlock() instanceof NetherPortalBlock || blockState.getBlock() instanceof EndPortalBlock) {
                if (getHealth() < getMaxHealth()) {
                    heal(0.5F);
                    Vec3d start = getPos();
                    Vec3d end = new Vec3d(pos.getX(), pos.getY(), pos.getZ());
                    Vec3d path = start.subtract(end);
                    for (int i = 0; i < 10; ++i) {
                        double percent = i / 10.0;
                        ((ServerWorld) world).spawnParticles(ParticleTypes.PORTAL, pos.getX() + 0.5 + path.getX() * percent, pos.getY() + 1.3 + path.getY() * percent, pos.getZ() + 0.5 + path.z * percent, 20, 0, 0, 0, 0);
                    }
                    return;
                }
            }

            if (blockState.getLuminance() > 0 && !(blockState.getBlock() instanceof NetherPortalBlock) && !(blockState.getBlock() instanceof EndPortalBlock)) {
                AngelUtils.playBreakEvent(this, pos, Blocks.AIR.getDefaultState());
                return;
            }
        }
    }


}
