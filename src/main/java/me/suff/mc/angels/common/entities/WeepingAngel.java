package me.suff.mc.angels.common.entities;

import com.google.common.collect.ImmutableList;
import me.suff.mc.angels.api.EventAngelBreakEvent;
import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.entities.attributes.WAAttributes;
import me.suff.mc.angels.common.misc.WAConstants;
import me.suff.mc.angels.common.variants.AbstractVariant;
import me.suff.mc.angels.common.variants.AngelTypes;
import me.suff.mc.angels.config.WAConfig;
import me.suff.mc.angels.utils.AngelUtil;
import me.suff.mc.angels.utils.TagUtil;
import me.suff.mc.angels.utils.WATeleporter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreakDoorGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static me.suff.mc.angels.utils.AngelUtil.updateBlock;

public class WeepingAngel extends QuantumLockedLifeform {

    private static final EntityDataAccessor<String> TYPE = SynchedEntityData.defineId(WeepingAngel.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<String> CURRENT_POSE = SynchedEntityData.defineId(WeepingAngel.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<String> VARIANT = SynchedEntityData.defineId(WeepingAngel.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Float> LAUGH = SynchedEntityData.defineId(WeepingAngel.class, EntityDataSerializers.FLOAT);
    private static final Predicate<Difficulty> DIFFICULTY = (difficulty) -> difficulty == Difficulty.EASY;
    private static final SoundEvent[] CHILD_SOUNDS = new SoundEvent[]{SoundEvents.VEX_AMBIENT, WAObjects.Sounds.LAUGHING_CHILD.get()};
    public long timeSincePlayedSound = 0;

    public WeepingAngel(EntityType<? extends QuantumLockedLifeform> type, Level world) {
        this(world);
    }

    public WeepingAngel(Level world) {
        super(world, WAObjects.EntityEntries.WEEPING_ANGEL.get());
        goalSelector.addGoal(0, new BreakDoorGoal(this, DIFFICULTY));
        goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0D));
        goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0D));
        xpReward = WAConfig.CONFIG.xpGained.get();
    }


    public static AttributeSupplier.Builder createAttributes() {
        return createMonsterAttributes().
                add(Attributes.ATTACK_DAMAGE, WAConfig.CONFIG.damage.get()).
                add(Attributes.MAX_HEALTH, 50D).
                add(Attributes.KNOCKBACK_RESISTANCE, 1.0D).
                add(Attributes.MOVEMENT_SPEED, 0.8D).
                add(WAAttributes.BLOCK_BREAK_RANGE.get(), WAConfig.CONFIG.blockBreakRange.get()).
                add(Attributes.ARMOR, 2.0D);
    }

    @Override
    public void knockback(double p_147241_, double p_147242_, double p_147243_) {

    }

    @Override
    public boolean isPersistenceRequired() {
        return super.isPersistenceRequired() || !getMainHandItem().isEmpty() || !getOffhandItem().isEmpty();
    }

    public void dropAngelStuff() {
        if (level.isClientSide()) return;
        AngelUtil.dropEntityLoot(this, this.lastHurtByPlayer);
        spawnAtLocation(getMainHandItem());
        spawnAtLocation(getOffhandItem());
    }

    @Override
    public boolean displayFireAnimation() {
        return false;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        getEntityData().define(TYPE, AngelUtil.randomType().name());
        getEntityData().define(CURRENT_POSE, WeepingAngelPose.getRandomPose(AngelUtil.RAND).name());
        getEntityData().define(VARIANT, getAngelType().getWeightedHandler().getRandom(null).getRegistryName().toString());
        getEntityData().define(LAUGH, random.nextFloat());
    }

    public AbstractVariant getVariant() {
        return AngelTypes.VARIANTS_REGISTRY.get().getValue(new ResourceLocation(getEntityData().get(VARIANT)));
    }

    public void setVarient(AbstractVariant varient) {
        getEntityData().set(VARIANT, varient.getRegistryName().toString());
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverWorld, DifficultyInstance difficultyInstance, MobSpawnType spawnReason, @Nullable SpawnGroupData livingEntityData, @Nullable CompoundTag compoundNBT) {
        playSound(WAObjects.Sounds.ANGEL_AMBIENT.get(), 0.5F, 1.0F);
        setVarient(getAngelType().getWeightedHandler().getRandom(this));
        return super.finalizeSpawn(serverWorld, difficultyInstance, spawnReason, livingEntityData, compoundNBT);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.STONE_HIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return WAObjects.Sounds.ANGEL_DEATH.get();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (isCherub() && tickCount % AngelUtil.secondsToTicks(2) == 0) {
            return CHILD_SOUNDS[random.nextInt(CHILD_SOUNDS.length)];
        }
        return null;
    }

    @Override
    public float getEyeHeight(Pose p_213307_1_) {
        return isCherub() ? 0.5F : 1.3F;
    }

    @Override
    public EntityDimensions getDimensions(Pose poseIn) {
        return isCherub() ? new EntityDimensions(0.8F, 0.8F, true) : super.getDimensions(poseIn);
    }

    @Override
    public boolean isBaby() {
        return isCherub();
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        if (entity instanceof ServerPlayer serverPlayerEntity) {

            boolean shouldTeleport = random.nextInt(20) < 5 && getHealth() > 5 && !isInCatacomb() || WAConfig.CONFIG.justTeleport.get();

            if (isCherub() || !shouldTeleport) {
                dealDamage(serverPlayerEntity);
                if (WAConfig.CONFIG.torchBlowOut.get()) {
                    AngelUtil.extinguishHand(serverPlayerEntity, this);
                }
                return true;
            }

            teleportInteraction(serverPlayerEntity);
            return true;
        }
        return true;
    }


    public boolean isInCatacomb() {
        if (level instanceof ServerLevel serverWorld) {
            BlockPos catacomb = null; //serverWorld.getLevel().findNearestMapFeature(WAFeatures.CATACOMB.get(), blockPosition(), 100, false);
            if (catacomb == null) {
                return false;
            }
            return distanceToSqr(catacomb.getX(), catacomb.getY(), catacomb.getZ()) < 50;
        }
        return false;
    }


    public void dealDamage(Player player) {
        double damage = WAConfig.CONFIG.damage.get();
        player.hurt(getHealth() > 5 ? WAObjects.ANGEL : WAObjects.ANGEL_NECK_SNAP, (float) damage);
        heal(getHealth() > 5 ? 4F : 2F);
        stealItems(player);
        this.setLastHurtMob(player);
    }

    private void stealItems(Player player) {
        // Steals items from the player
        if (getMainHandItem().isEmpty() && random.nextBoolean()) {
            for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                ItemStack stack = player.getInventory().getItem(i);
                if (stack.is(AngelUtil.THEFT)) {
                    setItemInHand(InteractionHand.MAIN_HAND, player.getInventory().getItem(i).copy());
                    player.getInventory().getItem(i).setCount(0);
                    player.inventoryMenu.broadcastChanges();
                    return;
                }
            }
        }
    }

    public long getTimeSincePlayedSound() {
        return timeSincePlayedSound;
    }

    public void setTimeSincePlayedSound(long timeSincePlayedSound) {
        this.timeSincePlayedSound = timeSincePlayedSound;
    }

    @Override
    protected boolean shouldDropExperience() {
        return true;
    }

    @Override
    public void die(DamageSource cause) {
        super.die(cause);
        spawnAtLocation(getMainHandItem());
        spawnAtLocation(getOffhandItem());

        if (getAngelType() == AngelType.DISASTER_MC) {
            AbstractVariant variant = getVariant();
            if (variant.shouldDrop(cause, this)) {
                spawnAtLocation(variant.stackDrop().getItem());
            }
        }

    }

    @Override
    public void makeStuckInBlock(BlockState state, Vec3 motionMultiplierIn) {
        if (!state.is(Blocks.COBWEB)) {
            super.makeStuckInBlock(state, motionMultiplierIn);
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putString(WAConstants.POSE, getAngelPose());
        compound.putString(WAConstants.TYPE, getAngelType().name());
        compound.putString(WAConstants.VARIENT, getVariant().getRegistryName().toString());
        compound.putFloat(WAConstants.LAUGH, getLaugh());
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);

        if (compound.contains(WAConstants.POSE))
            setPose(WeepingAngelPose.getPose(compound.getString(WAConstants.POSE)));

        if (compound.contains(WAConstants.LAUGH))
            setLaugh(serializeNBT().getFloat(WAConstants.LAUGH));

        if (compound.contains(WAConstants.TYPE)) setType(compound.getString(WAConstants.TYPE));

        if (compound.contains(WAConstants.VARIENT))
            setVarient(Objects.requireNonNull(AngelTypes.VARIANTS_REGISTRY.get().getValue(new ResourceLocation(compound.getString(WAConstants.VARIENT)))));
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);
        if (TYPE.equals(key)) {
            refreshDimensions();
            setVarient(getAngelType().getWeightedHandler().getRandom(this));
        }

    }

    @Override
    public void invokeSeen(Player player) {
        super.invokeSeen(player);
        if (player instanceof ServerPlayer && getSeenTime() == 1 && getPrevPos().asLong() != blockPosition().asLong()) {
            setPrevPos(blockPosition());
            playSeenSound(player);
            randomisePose();
            this.zza = 0.0F;
            this.yya = 0.0F;
            this.zza = 0.0F;
        }
    }

    private void randomisePose() {
        setPose(WeepingAngelPose.getRandomPose(random));
    }

    private void playSeenSound(Player player) {
        if (player.distanceTo(this) < 15) {
            setTimeSincePlayedSound(System.currentTimeMillis());
            ((ServerPlayer) player).connection.send(new ClientboundSoundPacket(getSeenSound(), SoundSource.BLOCKS, player.getX(), player.getY(), player.getZ(), 0.25F, 1.0F));
        }
    }

    public SoundEvent getSeenSound() {
        AbstractVariant abstractVariant = getVariant();
        ItemStack itemStack = abstractVariant.stackDrop();
        if (itemStack.getItem() instanceof BlockItem blockItem) {
            Block block = blockItem.getBlock();
            return block.defaultBlockState().getSoundType().getBreakSound();
        }
        return SoundEvents.STONE_PLACE;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        // Don't bother if we're on hard mode.
        if (level.getDifficulty() == Difficulty.HARD) {
            return;
        }

        if (!blockIn.getMaterial().isLiquid()) {
            BlockState blockstate = this.level.getBlockState(pos.above());
            SoundType soundtype = blockstate.getBlock() == Blocks.SNOW ? blockstate.getSoundType(level, pos, this) : blockIn.getSoundType(level, pos, this);

            if (level.getDifficulty() == Difficulty.EASY) {
                playSound(soundtype.getStepSound(), 0.3F, soundtype.getPitch());
            } else {
                if (level.random.nextInt(5) == 4) {
                    playSound(soundtype.getStepSound(), 0.3F, soundtype.getPitch());
                }
            }
        }
    }

    @Override
    public void tick() {

        if (!WAConfig.CONFIG.isVariantPermitted(getVariant())) {
            setVarient(getAngelType().getWeightedHandler().getRandom(this));
        }

        if (!WAConfig.CONFIG.isModelPermitted(getAngelType())) {
            setType(AngelType.next(getAngelType()));
        }


        getVariant().tick(this);
        super.tick();


        if (tickCount % 500 == 0 && getTarget() == null && getSeenTime() == 0) {
            setPose(Objects.requireNonNull(WeepingAngelPose.HIDING));
        }

        if (random.nextBoolean() && WAConfig.CONFIG.blockBreaking.get() && isSeen() && level.getGameRules().getRule(GameRules.RULE_MOBGRIEFING).get()) {
            replaceBlocks();
        }
    }


    @Override
    protected PathNavigation createNavigation(Level worldIn) {
        WallClimberNavigation navigator = new WallClimberNavigation(this, worldIn);
        navigator.setCanFloat(false);
        navigator.setCanOpenDoors(true);
        navigator.setAvoidSun(false);
        navigator.setSpeedModifier(1.0D);
        return navigator;
    }

    private void replaceBlocks() {
        if (level.isClientSide || tickCount % 100 != 0) return;

        if (level.getMaxLocalRawBrightness(blockPosition()) == 0) {
            return;
        }
        int range = (int) getAttributeValue(WAAttributes.BLOCK_BREAK_RANGE.get());
        for (Iterator<BlockPos> iterator = BlockPos.withinManhattanStream(blockPosition(), range, 3, range).iterator(); iterator.hasNext(); ) {
            BlockPos pos = iterator.next();
            ServerLevel serverWorld = (ServerLevel) level;
            BlockState blockState = serverWorld.getBlockState(pos);
            if (isAllowed(blockState, pos)) {

                if (blockState.getBlock() == Blocks.LAVA) {
                    continue;
                }

                if (blockState.getBlock() == Blocks.TORCH || blockState.getBlock() == Blocks.REDSTONE_TORCH || blockState.getBlock() == Blocks.GLOWSTONE) {
                    updateBlock(this, pos, Blocks.AIR.defaultBlockState(), true);
                    return;
                }

                if (blockState.hasProperty(BlockStateProperties.LIT)) {
                    if (blockState.getValue(BlockStateProperties.LIT)) {
                        updateBlock(this, pos, blockState.setValue(BlockStateProperties.LIT, false), false);
                        return;
                    }
                    continue;
                }

                if (level.getBlockEntity(pos) != null) {
                    BlockEntity blockEntity = level.getBlockEntity(pos);
                    blockEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(iEnergyStorage -> {
                        if (iEnergyStorage.canExtract()) {
                            iEnergyStorage.extractEnergy(100, false);
                            heal(0.5F);
                            return;
                        }
                    });
                }

                if (blockState.getBlock() instanceof NetherPortalBlock || blockState.getBlock() instanceof EndPortalBlock) {
                    if (getHealth() < getMaxHealth()) {
                        heal(0.5F);
                        Vec3 start = position();
                        Vec3 end = new Vec3(pos.getX(), pos.getY(), pos.getZ());
                        Vec3 path = start.subtract(end);
                        for (int i = 0; i < 10; ++i) {
                            double percent = i / 10.0;
                            ((ServerLevel) level).sendParticles(ParticleTypes.PORTAL, pos.getX() + 0.5 + path.x() * percent, pos.getY() + 1.3 + path.y() * percent, pos.getZ() + 0.5 + path.z * percent, 20, 0, 0, 0, 0);
                        }
                        return;
                    } else {
                        continue;
                    }
                }

                if (blockState.getLightEmission(level, pos) > 0 && !(blockState.getBlock() instanceof NetherPortalBlock) && !(blockState.getBlock() instanceof EndPortalBlock)) {
                    updateBlock(this, pos, Blocks.AIR.defaultBlockState(), true);
                    return;
                }
            }
        }
    }

    private void teleportInteraction(ServerPlayer player) {
        if (level.isClientSide) return;
        AngelUtil.EnumTeleportType type = WAConfig.CONFIG.teleportType.get();
        switch (type) {
            case STRUCTURE -> WATeleporter.handleStructures(player);
            case DONT -> doHurtTarget(player);
            case RANDOM_PLACE -> {
                double x = player.getX() + random.nextInt(WAConfig.CONFIG.teleportRange.get());
                double z = player.getZ() + random.nextInt(WAConfig.CONFIG.teleportRange.get());
                ServerLevel teleportWorld = WAConfig.CONFIG.angelDimTeleport.get() ? WATeleporter.getRandomDimension(random) : (ServerLevel) player.level;
                ChunkPos chunkPos = new ChunkPos(new BlockPos(x, 0, z));
                teleportWorld.setChunkForced(chunkPos.x, chunkPos.z, true);
                teleportWorld.getServer().tell(new TickTask(teleportWorld.getServer().getTickCount() + 1, () -> {
                    BlockPos blockPos = WATeleporter.findSafePlace(player, teleportWorld, new BlockPos(x, player.getY(), z));

                    if (AngelUtil.isOutsideOfBorder(teleportWorld, blockPos)) {
                        dealDamage(player);
                        return;
                    }

                    if (teleportWorld != null) {
                        dealDamage(player);
                        WATeleporter.teleportPlayerTo(player, blockPos, teleportWorld);
                        teleportWorld.setChunkForced(chunkPos.x, chunkPos.z, false);
                        heal(10);
                    }
                }));
            }
        }
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return worldIn.getDifficulty() != Difficulty.PEACEFUL && this.isValidLightLevel() && super.checkSpawnRules(worldIn, spawnReasonIn);
    }


    protected boolean isValidLightLevel() {
        BlockPos blockpos = new BlockPos(this.getX(), this.getBoundingBox().minY, this.getZ());
        if (this.level.getBrightness(LightLayer.SKY, blockpos) > this.random.nextInt(32)) {
            return false;
        } else {
            int i = this.level.isThundering() ? this.level.getMaxLocalRawBrightness(blockpos, 10) : this.level.getMaxLocalRawBrightness(blockpos);
            return i <= this.random.nextInt(8);
        }
    }

    public String getAngelPose() {
        return getEntityData().get(CURRENT_POSE);
    }

    public void setPose(WeepingAngelPose weepingAngelPose) {
        getEntityData().set(CURRENT_POSE, weepingAngelPose.name());
    }

    public boolean isCherub() {
        return getAngelType() == AngelType.ED_ANGEL_CHILD;
    }

    public AngelType getAngelType() {
        String type = getEntityData().get(TYPE);
        return type.isEmpty() ? AngelType.DISASTER_MC : AngelType.valueOf(type);
    }

    public void setType(String angelType) {
        getEntityData().set(TYPE, angelType);
    }

    public void setType(AngelType angelType) {
        setType(angelType.name());
    }


    @Override
    protected void tickDeath() {
        ++this.deathTime;
        if (this.deathTime == 20) {
            hurtTime = 0;
            this.removeAfterChangingDimensions();
        }
        for (int i = 0; i < 20; ++i) {
            double d0 = this.random.nextGaussian() * 0.02D;
            double d1 = this.random.nextGaussian() * 0.02D;
            double d2 = this.random.nextGaussian() * 0.02D;
            this.level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.STONE.defaultBlockState()), this.getRandomX(1.0D), this.getRandomY(), this.getRandomZ(1.0D), d0, d1, d2);
        }
    }

    @Override
    public void kill() {
        remove(RemovalReason.KILLED);
    }

    public boolean isAllowed(BlockState blockState, BlockPos blockPos) {

        if (TagUtil.hasTag(ForgeRegistries.BLOCKS, AngelUtil.ANGEL_IGNORE, blockState.getBlock())) {
            return false;
        }



        EventAngelBreakEvent eventAngelBreakEvent = new EventAngelBreakEvent(this, blockState, blockPos);
        MinecraftForge.EVENT_BUS.post(eventAngelBreakEvent);
        return !eventAngelBreakEvent.isCanceled();
    }

    public float getLaugh() {
        return getEntityData().get(LAUGH);
    }

    public void setLaugh(float laugh) {
        getEntityData().set(LAUGH, laugh);
    }

    public WeepingAngel.Cracks getCrackiness() {
        return WeepingAngel.Cracks.byFraction(this.getHealth() / this.getMaxHealth());
    }

    public enum Cracks {
        NONE(1.0F),
        LOW(0.75F),
        MEDIUM(0.5F),
        HIGH(0.25F);

        private static final List<WeepingAngel.Cracks> BY_DAMAGE = Stream.of(values()).sorted(Comparator.comparingDouble((p_226516_0_) -> (double) p_226516_0_.fraction)).collect(ImmutableList.toImmutableList());
        private final float fraction;

        Cracks(float p_i225732_3_) {
            this.fraction = p_i225732_3_;
        }

        public static WeepingAngel.Cracks byFraction(float p_226515_0_) {
            for (WeepingAngel.Cracks weepCracks : BY_DAMAGE) {
                if (p_226515_0_ < weepCracks.fraction) {
                    return weepCracks;
                }
            }
            return NONE;
        }
    }
}
