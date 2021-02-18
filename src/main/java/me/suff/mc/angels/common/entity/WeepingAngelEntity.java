package me.suff.mc.angels.common.entity;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.objects.WASounds;
import me.suff.mc.angels.enums.WeepingAngelPose;
import me.suff.mc.angels.enums.WeepingAngelVariants;
import me.suff.mc.angels.util.AngelUtils;
import me.suff.mc.angels.util.Constants;
import me.suff.mc.angels.util.PlayerUtils;
import me.suff.mc.angels.util.WAConfig;
import net.minecraft.block.*;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.packet.s2c.play.PlaySoundFromEntityS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/* Created by Craig on 18/02/2021 */
public class WeepingAngelEntity extends QuantumLockBaseEntity {

    private static final TrackedData< String > CURRENT_POSE = DataTracker.registerData(WeepingAngelEntity.class, TrackedDataHandlerRegistry.STRING);
    private static final TrackedData< String > VARIENT = DataTracker.registerData(WeepingAngelEntity.class, TrackedDataHandlerRegistry.STRING);
    private long timeSincePlayedSound = 0;

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
        if (getSeenTime() == 0 || world.isAir(getBlockPos().down())) {
            setAiDisabled(false);
        }
        if (age % 500 == 0 && getTarget() == null && getSeenTime() == 0) {
            setPose(Objects.requireNonNull(WeepingAngelPose.HIDING));
        }
        if (WAConfig.BreakConfig.breakBlocks.getValue() && isSeen()) {
            replaceBlocks(getBoundingBox().expand(WAConfig.BreakConfig.breakRange.getValue()));
        }
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return WASounds.ANGEL_DEATH;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        // We need to be compatible with the kill command
        if (source == DamageSource.OUT_OF_WORLD) {
            return false;
        }

        //Pickaxe only!
        if (source.getAttacker() instanceof LivingEntity) {
            LivingEntity living = (LivingEntity) source.getAttacker();
            ItemStack stack = PlayerUtils.getItemFromActive(living);
            return stack.getItem() instanceof PickaxeItem ? super.damage(source, amount) : false;
        }
        return false;
    }

    @Override
    public void takeKnockback(float f, double d, double e) {
        //No lol
    }

    @Override
    public void writeCustomDataToTag(CompoundTag tag) {
        super.writeCustomDataToTag(tag);
        tag.putString(Constants.CURRENT_POSE, getAngelPose());
        tag.putString(Constants.VARIANT, getVarient());
    }

    @Override
    public void readCustomDataFromTag(CompoundTag tag) {
        super.readCustomDataFromTag(tag);
        setVarient(WeepingAngelVariants.getVariant(tag.getString(Constants.VARIANT)));
        setPose(WeepingAngelPose.getPose(tag.getString(Constants.CURRENT_POSE)));
    }

    @Override
    public void invokeSeen(PlayerEntity player) {
        super.invokeSeen(player);
        if (player instanceof ServerPlayerEntity && getSeenTime() == 1 && getPrevPos().asLong() != getBlockPos().asLong()) {
            setPrevPos(getBlockPos());
            boolean canPlaySound = !player.isCreative() && getTimeSincePlayedSound() == 0 || System.currentTimeMillis() - getTimeSincePlayedSound() >= 20000;
            // Play Sound
            if (canPlaySound) {
                if (WAConfig.AngelBehaviour.playSeenSounds.getValue() && player.distanceTo(this) < 15) {
                    setTimeSincePlayedSound(System.currentTimeMillis());
                    ((ServerPlayerEntity) player).networkHandler.sendPacket(new PlaySoundFromEntityS2CPacket(WASounds.ANGEL_SEEN, SoundCategory.HOSTILE, this, 0.1F, 1.0F));
                }
            }
            setPose(WeepingAngelPose.getRandomPose(AngelUtils.RAND));
        }
    }

    @Override
    public @Nullable EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable CompoundTag entityTag) {
        playSound(WASounds.ANGEL_AMBIENT, 0.5F, 1.0F);
        return super.initialize(world, difficulty, spawnReason, entityData, entityTag);
    }

    @Override
    public boolean shouldRenderName() {
        return false;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.BLOCK_STONE_HIT;
    }

    public long getTimeSincePlayedSound() {
        return timeSincePlayedSound;
    }

    public void setTimeSincePlayedSound(long timeSincePlayedSound) {
        this.timeSincePlayedSound = timeSincePlayedSound;
    }

    //DESTROY LIGHT BLOCKS
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
