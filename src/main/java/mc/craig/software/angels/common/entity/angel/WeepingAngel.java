package mc.craig.software.angels.common.entity.angel;

import com.google.common.collect.Lists;
import mc.craig.software.angels.common.WASounds;
import mc.craig.software.angels.util.HurtHelper;
import mc.craig.software.angels.util.Teleporter;
import mc.craig.software.angels.util.WADamageSources;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.ClimbOnTopOfPowderSnowGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.OpenDoorGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.ArrayList;
import java.util.Iterator;

public class WeepingAngel extends AbstractWeepingAngel {

    public AnimationState POSE_ANIMATION_STATE = new AnimationState();

    public WeepingAngel(Level worldIn, EntityType<? extends Monster> entityType) {
        super(worldIn, entityType);
        int id = 0;

        // Goals
        goalSelector.addGoal(id++, new OpenDoorGoal(this, false));
        goalSelector.addGoal(id++, new MeleeAttackGoal(this, 0.5f, true));
        goalSelector.addGoal(id++, new ClimbOnTopOfPowderSnowGoal(this, this.level));
        goalSelector.addGoal(id++, new FloatGoal(this));

        // Targeting
        targetSelector.addGoal(id++, new NearestAttackableTargetGoal<>(this, Player.class, true));
        targetSelector.addGoal(id++, (new HurtByTargetGoal(this)).setAlertOthers(WeepingAngel.class));
    }

    @Override
    public boolean doHurtTarget(Entity pEntity) {
        float attackDamage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        boolean didHurt = pEntity.hurt(WADamageSources.SNAPPED_NECK, attackDamage);
        this.doEnchantDamageEffects(this, pEntity);
        this.setLastHurtMob(pEntity);
        if(pEntity.level instanceof ServerLevel) {

            Iterable<ServerLevel> dimensions = ServerLifecycleHooks.getCurrentServer().getAllLevels();
            ArrayList<ServerLevel> allowedDimensions = Lists.newArrayList(dimensions);
            allowedDimensions.remove(ServerLifecycleHooks.getCurrentServer().getLevel(Level.NETHER));

            Teleporter.performTeleport(pEntity, allowedDimensions.get(level.random.nextInt(allowedDimensions.size())), random.nextInt(100), random.nextInt(100), random.nextInt(100), 1, 1);
        }
        return didHurt;
    }

    @Override
    public boolean wasKilled(ServerLevel serverLevel, LivingEntity livingEntity) {
        boolean wasKilled = super.wasKilled(serverLevel, livingEntity);
        if (wasKilled) {
            playSound(WASounds.NECK_SNAP.get());
        }
        return wasKilled;
    }

    @Override
    public void actuallyHurt(DamageSource pDamageSource, float pDamageAmount) {
        super.actuallyHurt(pDamageSource, pDamageAmount);
    }

    @Override
    public void tick() {
        super.tick();

        if (!POSE_ANIMATION_STATE.isStarted()) {
            POSE_ANIMATION_STATE.start(tickCount - random.nextInt(10000));
        }

        if (tickCount % 400 == 0) {
            investigateBlocks();
        }
    }

    @Override
    public void invokeSeen(Player player) {
        super.invokeSeen(player);
        if (getSeenTime() == 1) {
            playSound(SoundEvents.STONE_PLACE);
        }
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.STONE_HIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.STONE_BREAK;
    }


    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        boolean isHurt = HurtHelper.handleAngelHurt(this, pSource, pAmount);
        if (!level.isClientSide()) {
            ServerLevel serverLevel = (ServerLevel) level;
            if (isHurt) {
                playSound(SoundEvents.STONE_BREAK);
                serverLevel.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.STONE.defaultBlockState()), getX(), getY(0.5D), getZ(), 5, 0.1D, 0.0D, 0.1D, 0.2D);
            }
        }
        return isHurt;
    }

    @Override
    protected void tickDeath() {
        ++this.deathTime;
        if (this.deathTime == 20 && !this.level.isClientSide()) {
            this.remove(Entity.RemovalReason.KILLED);
        }
    }

    public void investigateBlocks() {
        for (Iterator<BlockPos> iterator = BlockPos.withinManhattanStream(blockPosition(), 25, 3, 25).iterator(); iterator.hasNext(); ) {
            BlockPos pos = iterator.next();
            BlockState blockState = level.getBlockState(pos);
            BlockBehaviour.BlockReaction blockBehaviour = BlockBehaviour.BLOCK_BEHAVIOUR.get(blockState.getBlock());
            boolean completed = blockBehaviour.interact(this, blockState, level, pos);
            if (completed) return;
        }
    }
}
