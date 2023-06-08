package mc.craig.software.angels.common.entity.angel;

import com.google.common.collect.ImmutableList;
import mc.craig.software.angels.WAConfiguration;
import mc.craig.software.angels.common.CatacombTracker;
import mc.craig.software.angels.common.WAEntities;
import mc.craig.software.angels.common.WASounds;
import mc.craig.software.angels.common.entity.angel.ai.AngelEmotion;
import mc.craig.software.angels.common.entity.angel.ai.AngelVariant;
import mc.craig.software.angels.common.items.WAItems;
import mc.craig.software.angels.util.HurtHelper;
import mc.craig.software.angels.util.Teleporter;
import mc.craig.software.angels.util.WADamageSources;
import mc.craig.software.angels.util.WATags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.ClimbOnTopOfPowderSnowGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.OpenDoorGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class WeepingAngel extends AbstractWeepingAngel {

    public AnimationState POSE_ANIMATION_STATE = new AnimationState();

    private int fakeAnimation = -1;

    public int getFakeAnimation() {
        return fakeAnimation;
    }

    public void setFakeAnimation(int fakeAnimation) {
        this.fakeAnimation = fakeAnimation;
    }

    public WeepingAngel(Level worldIn) {
        super(worldIn, WAEntities.WEEPING_ANGEL.get());
        int id = 0;

        // Goals
        goalSelector.addGoal(id++, new OpenDoorGoal(this, false));
        goalSelector.addGoal(id++, new MeleeAttackGoal(this, 0.5f, true));
        goalSelector.addGoal(id++, new ClimbOnTopOfPowderSnowGoal(this, this.level()));

        // Targeting
        targetSelector.addGoal(id++, new NearestAttackableTargetGoal<>(this, Player.class, true));
        targetSelector.addGoal(id++, (new HurtByTargetGoal(this)).setAlertOthers(WeepingAngel.class));
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag) {
        setVariant(AngelVariant.getVariantForPos(this));
        return super.finalizeSpawn(level, difficulty, reason, spawnData, dataTag);
    }

    @Nullable
    @Override
    public ItemStack getPickResult() {
        return new ItemStack(WAItems.ANGEL_SPAWNER.get());
    }

    @Override
    public boolean doHurtTarget(Entity pEntity) {
        float attackDamage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);

        // Teleporting
        boolean shouldTeleport = random.nextInt(100) < WAConfiguration.CONFIG.teleportChance.get();
        if (shouldTeleport && pEntity.level() instanceof ServerLevel serverLevel) {
            ServerLevel chosenDimension = Teleporter.getRandomDimension(random, serverLevel);
            int xCoord = (int) (getX() + random.nextInt(WAConfiguration.CONFIG.teleportRange.get()));
            int zCoord = (int) (getZ() + random.nextInt(WAConfiguration.CONFIG.teleportRange.get()));

            for (int i = 0; i < 10; i++) {
                boolean successfulTeleport = Teleporter.performTeleport(pEntity, Teleporter.getRandomDimension(random, serverLevel), xCoord, chosenDimension.getHeight(Heightmap.Types.MOTION_BLOCKING, xCoord, zCoord), zCoord, pEntity.getYRot(), pEntity.getXRot(), true);
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
        if (pEntity.level() instanceof ServerLevel serverLevel) {
            boolean didHurt = pEntity.hurt(WADamageSources.getSource(serverLevel, WADamageSources.SNAPPED_NECK), attackDamage);

            this.doEnchantDamageEffects(this, pEntity);
            this.setLastHurtMob(pEntity);
            return didHurt;
        }
        return false;
    }


    @Override
    public boolean killedEntity(ServerLevel serverLevel, LivingEntity livingEntity) {
        boolean wasKilled = super.killedEntity(serverLevel, livingEntity);
        if (wasKilled) {
            playSound(WASounds.NECK_SNAP.get());
        }
        return wasKilled;    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        if (!state.liquid()) {
            BlockState blockState = Blocks.STONE.defaultBlockState();
            SoundType soundType = blockState.getSoundType();
            this.playSound(soundType.getStepSound(), soundType.getVolume() * 0.15F, soundType.getPitch());
        }
    }

    @Override
    public void push(Entity entity) {
        super.push(entity);
        doHurtTarget(entity);
    }

    @Override
    protected void doPush(Entity entity) {
        super.doPush(entity);
        doHurtTarget(entity);
    }

    @Override
    public void tick() {
        super.tick();

        Level level = level();

        if (!level.isClientSide()) {
            if (CatacombTracker.isInCatacomb(this)) {
                Warden.applyDarknessAround((ServerLevel) level, this.position(), this, 20);
            }
        }

        if (!POSE_ANIMATION_STATE.isStarted()) {
            POSE_ANIMATION_STATE.start(tickCount - random.nextInt(10000));
        }

        // Ensure angels do not lock in the air or walk through water
        if (isSeen() && (!onGround() || level.containsAnyLiquid(getBoundingBox())) && !isHooked()) {
            setSeenTime(0);
            setNoAi(false);
        }

        if (tickCount % 400 == 0) {
            if (isHooked()) {
                setHooked(false);
            }
            if (isSeen()) {
                investigateBlocks();
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
                playerInv.setItem(i, ItemStack.EMPTY);
            }
        }
    }


    @Override
    protected @NotNull PathNavigation createNavigation(@NotNull Level worldIn) {
        WallClimberNavigation navigator = new WallClimberNavigation(this, worldIn);
        navigator.setCanFloat(false);
        navigator.setCanOpenDoors(true);
        navigator.setAvoidSun(false);
        navigator.setSpeedModifier(1.0D);
        return navigator;
    }

    @Override
    public void kill() {
        remove(RemovalReason.KILLED);
    }

    @Override
    public boolean onClimbable() {
        return horizontalCollision;
    }

    @Override
    public void invokeSeen(Player player) {
        super.invokeSeen(player);
        if (getSeenTime() == 1 && System.currentTimeMillis() - timeSincePlayedSound > 5000) {
            setEmotion(AngelEmotion.randomEmotion(random));
            playSound(SoundEvents.STONE_PLACE);

            if (player instanceof ServerPlayer serverPlayer && player.distanceTo(this) < 15) {
                setTimeSincePlayedSound(System.currentTimeMillis());
                serverPlayer.connection.send(new ClientboundSoundPacket(Holder.direct(getSeenSound()), SoundSource.BLOCKS, player.getX(), player.getY(), player.getZ(), 0.25F, 1.0F, this.random.nextLong()));
            }
        }
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.STONE_HIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return WASounds.CRUMBLING.get();
    }


    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        Level level = level();
        if (!level.isClientSide()) {
            boolean isHurt = HurtHelper.handleAngelHurt(this, pSource, pAmount);
            ServerLevel serverLevel = (ServerLevel) level;
            if (isHurt) {
                if (getVariant().getDrops().getItem() instanceof BlockItem blockItem) {
                    BlockState defaultState = blockItem.getBlock().defaultBlockState();
                    playSound(defaultState.getSoundType().getHitSound());
                }
                serverLevel.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.STONE.defaultBlockState()), getX(), getY(0.5D), getZ(), 5, 0.1D, 0.0D, 0.1D, 0.2D);
                return super.hurt(pSource, pAmount);
            }
        }
        return false;
    }

    @Override
    protected void tickDeath() {
        Level level = level();
        ++this.deathTime;
        if (this.deathTime == 20 && !level.isClientSide()) {
            if(shouldDropLoot()) {
                ItemEntity itemEntity = new ItemEntity(EntityType.ITEM, level);
                itemEntity.setItem(getVariant().getDrops());
                itemEntity.setPos(getX(), getY(), getZ());
                level.addFreshEntity(itemEntity);
            }
            this.remove(Entity.RemovalReason.KILLED);
        }

    }

    public void investigateBlocks() {
        Level level = level();
        if (level.isClientSide() || !level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING) || !WAConfiguration.CONFIG.blockBreaking.get())
            return;
        for (Iterator<BlockPos> iterator = BlockPos.withinManhattanStream(blockPosition(), 25, 3, 25).iterator(); iterator.hasNext(); ) {
            BlockPos pos = iterator.next();
            BlockState blockState = level.getBlockState(pos);
            BlockReactions.BlockReaction blockBehaviour = BlockReactions.BLOCK_BEHAVIOUR.get(blockState.getBlock());
            boolean completed = blockBehaviour.interact(this, blockState, level, pos);
            if (completed) {
                Warden.applyDarknessAround((ServerLevel) level, Vec3.atBottomCenterOf(blockPosition()), this, 64);
                return;
            }
        }
    }

    public Crackiness getCrackiness() {
        return WeepingAngel.Crackiness.byFraction(this.getHealth() / this.getMaxHealth());
    }

    public enum Crackiness {
        NONE(1.0F),
        LOW(0.75F),
        MEDIUM(0.5F),
        HIGH(0.25F);

        private static final List<WeepingAngel.Crackiness> BY_DAMAGE = Stream.of(values()).sorted(Comparator.comparingDouble((crackiness) -> crackiness.fraction)).collect(ImmutableList.toImmutableList());
        private final float fraction;

        Crackiness(float pFraction) {
            this.fraction = pFraction;
        }

        public static WeepingAngel.Crackiness byFraction(float pFraction) {
            for (WeepingAngel.Crackiness crackiness : BY_DAMAGE) {
                if (pFraction < crackiness.fraction) {
                    return crackiness;
                }
            }

            return NONE;
        }
    }
}
