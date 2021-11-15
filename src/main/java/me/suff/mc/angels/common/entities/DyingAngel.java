package me.suff.mc.angels.common.entities;

import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.entities.attributes.WAAttributes;
import me.suff.mc.angels.common.misc.WAConstants;
import me.suff.mc.angels.config.WAConfig;
import me.suff.mc.angels.utils.AngelUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

public class DyingAngel extends QuantumLockedLifeform {

    private static final EntityDataAccessor<String> CURRENT_POSE = SynchedEntityData.defineId(DyingAngel.class, EntityDataSerializers.STRING);

    public DyingAngel(Level worldIn, EntityType<? extends Monster> entityType) {
        super(worldIn, entityType);
    }

    public DyingAngel(Level world) {
        super(world, WAObjects.EntityEntries.APLAN.get());
        goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0D));
        goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0D));
        xpReward = WAConfig.CONFIG.xpGained.get();
    }

    public DyingAngel(EntityType<DyingAngel> dyingAngelEntityType, Level level) {
        this(level);
    }


    @Override
    public boolean displayFireAnimation() {
        return false;
    }

    @Override
    public void knockback(double p_147241_, double p_147242_, double p_147243_) {

    }

    @Override
    public void invokeSeen(Player player) {
        super.invokeSeen(player);
        if (player instanceof ServerPlayer && getSeenTime() == 1 && getPrevPos().asLong() != blockPosition().asLong()) {
            setPose(WeepingAngelPose.getRandomPose(random));
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        getEntityData().define(CURRENT_POSE, WeepingAngelPose.getRandomPose(AngelUtil.RAND).name());
    }

    public String getAngelPose() {
        return getEntityData().get(CURRENT_POSE);
    }

    public void setPose(WeepingAngelPose weepingAngelPose) {
        getEntityData().set(CURRENT_POSE, weepingAngelPose.name());
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putString(WAConstants.POSE, getAngelPose());
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);

        if (compound.contains(WAConstants.POSE))
            setPose(WeepingAngelPose.getPose(compound.getString(WAConstants.POSE)));
    }

    @Override
    public void killed(ServerLevel serverLevel, LivingEntity living) {
        super.killed(serverLevel, living);
        if (living instanceof Player) {
            playSound(WAObjects.Sounds.ANGEL_NECK_SNAP.get(), 1, 1);
        }
    }

    @Override
    protected void playStepSound(BlockPos blockPos, BlockState p_20136_) {
        if (!p_20136_.getMaterial().isLiquid()) {
            BlockState blockstate = Blocks.STONE.defaultBlockState();
            SoundType soundtype = blockstate.is(Blocks.SNOW) ? blockstate.getSoundType(level, blockPos, this) : p_20136_.getSoundType(level, blockPos, this);
            this.playSound(soundtype.getStepSound(), soundtype.getVolume() * 0.15F, soundtype.getPitch());
        }
    }

    @Override
    public boolean doHurtTarget(Entity p_21372_) {
        float damage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        return p_21372_.hurt(WAObjects.ANGEL_NECK_SNAP, damage);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return createMonsterAttributes().
                add(Attributes.ATTACK_DAMAGE, WAConfig.CONFIG.damage.get()).
                add(Attributes.MAX_HEALTH, 50D).
                add(Attributes.KNOCKBACK_RESISTANCE, 1.0D).
                add(Attributes.MOVEMENT_SPEED, 0.4D).
                add(WAAttributes.BLOCK_BREAK_RANGE.get(), WAConfig.CONFIG.blockBreakRange.get()).
                add(Attributes.ARMOR, 2.0D);
    }
}
