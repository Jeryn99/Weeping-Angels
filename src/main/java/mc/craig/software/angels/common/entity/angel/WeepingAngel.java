package mc.craig.software.angels.common.entity.angel;

import mc.craig.software.angels.WAConfiguration;
import mc.craig.software.angels.common.WASounds;
import mc.craig.software.angels.util.HurtHelper;
import mc.craig.software.angels.util.Teleporter;
import mc.craig.software.angels.util.WADamageSources;
import mc.craig.software.angels.util.WATags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.ClimbOnTopOfPowderSnowGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.OpenDoorGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;

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

        // Teleporting
        boolean shouldTeleport = random.nextInt(100) < WAConfiguration.CONFIG.teleportChance.get();
        if (shouldTeleport && pEntity.level instanceof ServerLevel) {
            ServerLevel chosenDimension = Teleporter.getRandomDimension(random);
            double xCoord = getX() + random.nextInt(WAConfiguration.CONFIG.teleportRange.get());
            double zCoord = getZ() + random.nextInt(WAConfiguration.CONFIG.teleportRange.get());

            for (int i = 0; i < 10; i++) {
                boolean successfulTeleport = Teleporter.performTeleport(pEntity, Teleporter.getRandomDimension(random), xCoord, chosenDimension.getHeight(Heightmap.Types.MOTION_BLOCKING, (int) xCoord, (int) zCoord), zCoord, pEntity.getXRot(), pEntity.getYRot());
                if (successfulTeleport) {
                    return true;
                }
            }
            return false;
        }

        // Theft
        if (pEntity instanceof Player player) {
            stealItems(player);
        }

        // Hurt
        boolean didHurt = pEntity.hurt(WADamageSources.SNAPPED_NECK, attackDamage);
        this.doEnchantDamageEffects(this, pEntity);
        this.setLastHurtMob(pEntity);
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
            if (isSeen()) {
                investigateBlocks();
            } else {
                setEmotion(AngelEmotion.randomEmotion(random));
            }
        }
    }

    public void stealItems(Player player) {
        if (!getMainHandItem().isEmpty()) return;
        Inventory playerInv = player.getInventory();
        for (int i = 0; i < playerInv.items.size(); i++) {
            ItemStack item = playerInv.items.get(i);
            if (item.is(WATags.STEALABLE_ITEMS)) {
                setItemInHand(InteractionHand.MAIN_HAND, item.copy());
                setGuaranteedDrop(EquipmentSlot.MAINHAND);
                playerInv.setItem(0, ItemStack.EMPTY);
            }
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
        if (!level.isClientSide()) {
            boolean isHurt = HurtHelper.handleAngelHurt(this, pSource, pAmount);
            ServerLevel serverLevel = (ServerLevel) level;
            if (isHurt) {
                playSound(SoundEvents.STONE_BREAK);
                serverLevel.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.STONE.defaultBlockState()), getX(), getY(0.5D), getZ(), 5, 0.1D, 0.0D, 0.1D, 0.2D);
                return true;
            }
        }
        return false;
    }

    @Override
    protected void tickDeath() {
        ++this.deathTime;
        if (this.deathTime == 20 && !this.level.isClientSide()) {
            this.remove(Entity.RemovalReason.KILLED);
        }
    }

    public void investigateBlocks() {
        if (!level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING) || !WAConfiguration.CONFIG.blockBreaking.get())
            return;
        for (Iterator<BlockPos> iterator = BlockPos.withinManhattanStream(blockPosition(), 25, 3, 25).iterator(); iterator.hasNext(); ) {
            BlockPos pos = iterator.next();
            BlockState blockState = level.getBlockState(pos);
            BlockBehaviour.BlockReaction blockBehaviour = BlockBehaviour.BLOCK_BEHAVIOUR.get(blockState.getBlock());
            boolean completed = blockBehaviour.interact(this, blockState, level, pos);
            if (completed) return;
        }
    }
}
