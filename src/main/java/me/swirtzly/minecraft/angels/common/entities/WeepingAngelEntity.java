package me.swirtzly.minecraft.angels.common.entities;

import me.swirtzly.minecraft.angels.WeepingAngels;
import me.swirtzly.minecraft.angels.api.EventAngelBreakEvent;
import me.swirtzly.minecraft.angels.client.poses.WeepingAngelPose;
import me.swirtzly.minecraft.angels.common.WAObjects;
import me.swirtzly.minecraft.angels.common.entities.attributes.WAAttributes;
import me.swirtzly.minecraft.angels.common.misc.WAConstants;
import me.swirtzly.minecraft.angels.config.WAConfig;
import me.swirtzly.minecraft.angels.utils.AngelUtils;
import me.swirtzly.minecraft.angels.utils.NBTPatcher;
import me.swirtzly.minecraft.angels.utils.WATeleporter;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.BreakDoorGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPlaySoundEffectPacket;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.*;
import net.minecraft.util.concurrent.TickDelayedTask;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.common.MinecraftForge;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;

import static me.swirtzly.minecraft.angels.utils.WATeleporter.yCoordSanity;

public class WeepingAngelEntity extends QuantumLockBaseEntity {

    private static final DataParameter< String > TYPE = EntityDataManager.createKey(WeepingAngelEntity.class, DataSerializers.STRING);
    private static final DataParameter< String > CURRENT_POSE = EntityDataManager.createKey(WeepingAngelEntity.class, DataSerializers.STRING);
    private static final DataParameter< String > VARIENT = EntityDataManager.createKey(WeepingAngelEntity.class, DataSerializers.STRING);
    private static final Predicate< Difficulty > DIFFICULTY = (difficulty) -> difficulty == Difficulty.EASY;
    private final SoundEvent[] CHILD_SOUNDS = new SoundEvent[]{SoundEvents.ENTITY_VEX_AMBIENT, WAObjects.Sounds.LAUGHING_CHILD.get()};
    public long timeSincePlayedSound = 0;

    public WeepingAngelEntity(EntityType< ? extends QuantumLockBaseEntity > type, World world) {
        this(world);
    }

    public WeepingAngelEntity(World world) {
        super(world, WAObjects.EntityEntries.WEEPING_ANGEL.get());
        goalSelector.addGoal(0, new BreakDoorGoal(this, DIFFICULTY));
        goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0D));
        goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 50.0F));
        experienceValue = WAConfig.CONFIG.xpGained.get();
        enablePersistence();
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(WAConfig.CONFIG.damage.get());
        this.getAttributes().registerAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(16.0D);
        this.getAttributes().registerAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(999D);
        this.getAttributes().registerAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(WAConfig.CONFIG.moveSpeed.get());
        this.getAttributes().registerAttribute(WAAttributes.BLOCK_BREAK_RANGE.get()).setBaseValue(WAConfig.CONFIG.blockBreakRange.get());
    }

    public void dropAngelStuff() {
        if (world.isRemote()) return;
        AngelUtils.dropEntityLoot(this, this.attackingPlayer);
        entityDropItem(getHeldItemMainhand());
        entityDropItem(getHeldItemOffhand());
    }

    public void setPlayer(PlayerEntity player) {
        this.attackingPlayer = player;
    }

    @Override
    protected void registerData() {
        super.registerData();
        getDataManager().register(TYPE, AngelUtils.randomType().name());
        getDataManager().register(CURRENT_POSE, WeepingAngelPose.getRandomPose(AngelUtils.RAND).name());
        getDataManager().register(VARIENT, AngelUtils.randomVarient().name());
    }

    public String getVarient() {
        return getDataManager().get(VARIENT);
    }

    public void setVarient(AngelVariants varient) {
        getDataManager().set(VARIENT, varient.name());
    }

    @Override
    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,  ILivingEntityData spawnDataIn,  CompoundNBT dataTag) {
        playSound(WAObjects.Sounds.ANGEL_AMBIENT.get(), 0.5F, 1.0F);
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.BLOCK_STONE_HIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return WAObjects.Sounds.ANGEL_DEATH.get();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (isCherub() && ticksExisted % AngelUtils.secondsToTicks(2) == 0) {
            return CHILD_SOUNDS[rand.nextInt(CHILD_SOUNDS.length)];
        }
        return null;
    }

    @Override
    public float getEyeHeight(Pose p_213307_1_) {
        return isCherub() ? 0.5F : 1.3F;
    }

    @Override
    public EntitySize getSize(Pose poseIn) {
        return isCherub() ? new EntitySize(0.8F, 0.8F, true) : super.getSize(poseIn);
    }

    @Override
    public boolean isChild() {
        return isCherub();
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) entity;
            if (!isCherub()) {
                // Teleport Only
                if (WAConfig.CONFIG.justTeleport.get()) {
                    teleportInteraction(serverPlayerEntity);
                    return true;
                }

                // Chance to teleport/deal damage
                boolean shouldTeleport = rand.nextInt(10) < 5 && getHealth() > 5 && !isInCatacomb();
                if (shouldTeleport) {
                    teleportInteraction(serverPlayerEntity);
                } else {
                    dealDamage(serverPlayerEntity);
                }
                return true;
            } else {
                //Child Behaviour
                if (WAConfig.CONFIG.torchBlowOut.get()) {
                    AngelUtils.removeLightFromHand(serverPlayerEntity, this);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isInCatacomb() {
        if (world instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) world;
            BlockPos catacomb = serverWorld.getWorldServer().findNearestStructure(WAObjects.Structures.CATACOMBS.get().getStructureName(), getPosition(), 100, false);

            if (catacomb == null) {
                return false;
            }

            return getDistanceSq(catacomb.getX(), catacomb.getY(), catacomb.getZ()) < 50;
        }

        return false;
    }

    public void dealDamage(PlayerEntity playerMP) {
        if (getHealth() > 5) {
            playerMP.attackEntityFrom(WAObjects.ANGEL, 4.0F);
            heal(4.0F);
        } else {
            playerMP.attackEntityFrom(WAObjects.ANGEL_NECK_SNAP, 4.0F);
            heal(2.0F);
        }


        // Steals keys from the player
        if (getHeldItemMainhand().isEmpty() && rand.nextBoolean()) {
            for (int i = 0; i < playerMP.inventory.getSizeInventory(); i++) {
                ItemStack stack = playerMP.inventory.getStackInSlot(i);
                if (stack.getItem().isIn(AngelUtils.KEYS)) {
                    setHeldItem(Hand.MAIN_HAND, playerMP.inventory.getStackInSlot(i).copy());
                    playerMP.inventory.getStackInSlot(i).setCount(0);
                    playerMP.container.detectAndSendChanges();
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
    protected boolean canDropLoot() {
        return true;
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        entityDropItem(getHeldItemMainhand());
        entityDropItem(getHeldItemOffhand());

        if (getAngelType() == AngelEnums.AngelType.ANGELA_MC) {
            AngelVariants angelVarient = AngelVariants.valueOf(getVarient());
            entityDropItem(angelVarient.getDropStack());
        }

    }

    @Override
    public void setMotionMultiplier(BlockState state, Vec3d motionMultiplierIn) {
        if (state.getBlock() != Blocks.COBWEB) { 
            super.setMotionMultiplier(state, motionMultiplierIn);
        }
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putString(WAConstants.POSE, getAngelPose());
        compound.putString(WAConstants.TYPE, getAngelType().name());
        compound.putString(WAConstants.VARIENT, getVarient());
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);

        NBTPatcher.angelaToVillager(compound, WAConstants.TYPE);

        if (compound.contains(WAConstants.POSE))
            setPose(WeepingAngelPose.getPose(compound.getString(WAConstants.POSE)));

        if (compound.contains(WAConstants.TYPE)) setType(compound.getString(WAConstants.TYPE));

        if (compound.contains(WAConstants.VARIENT))
            setVarient(AngelVariants.valueOf(compound.getString(WAConstants.VARIENT)));
    }

    @Override
    public void notifyDataManagerChange(DataParameter< ? > key) {
        super.notifyDataManagerChange(key);
        if (TYPE.equals(key)) {
            recalculateSize();
        }
    }

    @Override
    public AxisAlignedBB getBoundingBox() {
        return super.getBoundingBox();
    }

    @Override
    public void invokeSeen(PlayerEntity player) {
        super.invokeSeen(player);
        if (player instanceof ServerPlayerEntity && getSeenTime() == 1 && getPrevPos().toLong() != getPosition().toLong()) {
            setPrevPos(getPosition());
            boolean canPlaySound = !player.isCreative() && getTimeSincePlayedSound() == 0 || System.currentTimeMillis() - getTimeSincePlayedSound() >= 20000;
            // Play Sound
            if (canPlaySound) {
                if (WAConfig.CONFIG.playSeenSounds.get() && player.getDistance(this) < 15) {
                    setTimeSincePlayedSound(System.currentTimeMillis());
                    ((ServerPlayerEntity) player).connection.sendPacket(new SPlaySoundEffectPacket(WAObjects.Sounds.ANGEL_SEEN.get(), SoundCategory.HOSTILE, player.getPosX(), player.getPosY(), player.getPosZ(), 0.1F, 1.0F));
                }
            }

            if (getAngelType() != AngelEnums.AngelType.VIO_1) {
                setPose(WeepingAngelPose.getRandomPose(AngelUtils.RAND));
            } else {
                setPose(Objects.requireNonNull(rand.nextBoolean() ? WeepingAngelPose.ANGRY : WeepingAngelPose.HIDING));
            }
        }

    }


    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        if (!blockIn.getMaterial().isLiquid()) {
            BlockState blockstate = this.world.getBlockState(pos.up());
            SoundType soundtype = blockstate.getBlock() == Blocks.SNOW ? blockstate.getSoundType(world, pos, this) : blockIn.getSoundType(world, pos, this);

            if (isCherub()) {
                if (world.rand.nextInt(5) == 4) {
                    playSound(WAObjects.Sounds.CHILD_RUN.get(), 0.1F, soundtype.getPitch());
                }
            } else if (WAConfig.CONFIG.playScrapeSounds.get() && world.rand.nextInt(5) == 4) {
                playSound(WAObjects.Sounds.STONE_SCRAP.get(), 0.1F, soundtype.getPitch());
            }
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (getSeenTime() == 0 || world.isAirBlock(getPosition().down())) {
            setNoAI(false);
        }

        if (ticksExisted % 500 == 0 && getAttackTarget() == null && getSeenTime() == 0) {
            setPose(Objects.requireNonNull(WeepingAngelPose.HIDING));
        }

        if (WAConfig.CONFIG.blockBreaking.get() && isSeen() && world.getGameRules().get(GameRules.MOB_GRIEFING).get()) {
            double range = getAttribute(WAAttributes.BLOCK_BREAK_RANGE.get()).getValue();
            replaceBlocks(getBoundingBox().grow(range, 3, range));
        }
    }

    @Override
    protected PathNavigator createNavigator(World worldIn) {
        GroundPathNavigator navigator = new GroundPathNavigator(this, worldIn);
        navigator.setCanSwim(false);
        navigator.setBreakDoors(true);
        navigator.setAvoidSun(false);
        navigator.setSpeed(1.0D);
        return navigator;
    }

    private void replaceBlocks(AxisAlignedBB box) {
        if (world.isRemote || ticksExisted % 100 != 0) return;

        if (world.getLight(getPosition()) == 0) {
            return;
        }

        for (Iterator< BlockPos > iterator = BlockPos.getAllInBox(new BlockPos(box.maxX, box.maxY, box.maxZ), new BlockPos(box.minX, box.minY, box.minZ)).iterator(); iterator.hasNext(); ) {
            BlockPos pos = iterator.next();
            ServerWorld serverWorld = (ServerWorld) world;
            BlockState blockState = serverWorld.getBlockState(pos);
            if (isAllowed(blockState, pos)) {

                if (blockState.getBlock().isIn(AngelUtils.BANNED_BLOCKS) || blockState.getBlock() == Blocks.LAVA) {
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
                        Vec3d start = getPositionVec();
                        Vec3d end = new Vec3d(pos.getX(), pos.getY(), pos.getZ());
                        Vec3d path = start.subtract(end);
                        for (int i = 0; i < 10; ++i) {
                            double percent = i / 10.0;
                            ((ServerWorld) world).spawnParticle(ParticleTypes.PORTAL, pos.getX() + 0.5 + path.getX() * percent, pos.getY() + 1.3 + path.getY() * percent, pos.getZ() + 0.5 + path.z * percent, 20, 0, 0, 0, 0);
                        }
                        return;
                    }
                }

                if (blockState.getLightValue() > 0 && !(blockState.getBlock() instanceof NetherPortalBlock) && !(blockState.getBlock() instanceof EndPortalBlock)) {
                    AngelUtils.playBreakEvent(this, pos, Blocks.AIR.getDefaultState());
                    return;
                }
            }
        }
    }

    private void teleportInteraction(ServerPlayerEntity player) {
        if (world.isRemote) return;
        AngelUtils.EnumTeleportType type = AngelUtils.EnumTeleportType.valueOf(WAConfig.CONFIG.teleportType.get());
        switch (type) {

            case DONT:
                attackEntityAsMob(player);
                break;
            case STRUCTURES:
                Objects.requireNonNull(world.getServer()).enqueue(new TickDelayedTask(0, () -> {
                    if (!WATeleporter.handleStructures(player)) {
                        dealDamage(player);
                    }
                }));
                break;
            case RANDOM_PLACE:
                double x = player.getPosX() + rand.nextInt(WAConfig.CONFIG.teleportRange.get());
                double z = player.getPosZ() + rand.nextInt(WAConfig.CONFIG.teleportRange.get());

                ServerWorld teleportWorld = WAConfig.CONFIG.angelDimTeleport.get() ? WATeleporter.getRandomDimension(rand) : (ServerWorld) player.world;
                ChunkPos chunkPos = new ChunkPos(new BlockPos(x, 0, z));
                teleportWorld.forceChunk(chunkPos.x, chunkPos.z, true);

                teleportWorld.getServer().enqueue(new TickDelayedTask(0, () -> {
                    BlockPos blockPos = new BlockPos(x, yCoordSanity(teleportWorld, new BlockPos(x, 0, z)), z);

                    if (AngelUtils.isOutsideOfBorder(teleportWorld, blockPos)) {
                        WorldInfo worldInfo = teleportWorld.getWorldInfo();
                        blockPos = new BlockPos(worldInfo.getSpawnX() + 12, worldInfo.getSpawnY(), worldInfo.getSpawnZ() + 12);
                        blockPos = new BlockPos(blockPos.getX(), yCoordSanity(teleportWorld, blockPos), blockPos.getZ());
                        WeepingAngels.LOGGER.error("Weeping Angel Attempted to Teleport [" + player.getName().getUnformattedComponentText() + "] outside the world border! Correcting!");
                    }

                    if (teleportWorld != null) {
                        WATeleporter.teleportPlayerTo(player, blockPos, teleportWorld);
                        teleportWorld.forceChunk(chunkPos.x, chunkPos.z, false);
                        heal(10);
                    }
                }));
                break;
        }
    }

    @Override
    public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) {
        return worldIn.getDifficulty() != Difficulty.PEACEFUL && this.isValidLightLevel() && super.canSpawn(worldIn, spawnReasonIn);
    }


    protected boolean isValidLightLevel() {
        BlockPos blockpos = new BlockPos(this.getPosX(), this.getBoundingBox().minY, this.getPosZ());
        if (this.world.getLightFor(LightType.SKY, blockpos) > this.rand.nextInt(32)) {
            return false;
        } else {
            int i = this.world.isThundering() ? this.world.getNeighborAwareLightSubtracted(blockpos, 10) : this.world.getLight(blockpos);
            return i <= this.rand.nextInt(8);
        }
    }

    public String getAngelPose() {
        return getDataManager().get(CURRENT_POSE);
    }

    public void setPose(WeepingAngelPose weepingAngelPose) {
        getDataManager().set(CURRENT_POSE, weepingAngelPose.name());
    }

    public boolean isCherub() {
        return getAngelType() == AngelEnums.AngelType.ED_ANGEL_CHILD;
    }

    public AngelEnums.AngelType getAngelType() {
        String type = getDataManager().get(TYPE);
        return type.isEmpty() ? AngelEnums.AngelType.ANGELA_MC : AngelEnums.AngelType.valueOf(type);
    }

    public void setType(String angelType) {
        getDataManager().set(TYPE, angelType);
    }

    public void setType(AngelEnums.AngelType angelType) {
        setType(angelType.name());
    }


    @Override
    protected void onDeathUpdate() {
        ++this.deathTime;
        if (this.deathTime == 20) {
            hurtTime = 0;
            dropAngelStuff();
            this.remove();
            playSound(getDeathSound(), 1, 1);
        }
        for (int i = 0; i < 20; ++i) {
            double d0 = this.rand.nextGaussian() * 0.02D;
            double d1 = this.rand.nextGaussian() * 0.02D;
            double d2 = this.rand.nextGaussian() * 0.02D;
            this.world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, Blocks.STONE.getDefaultState()), this.getPosXRandom(1.0D), this.getPosYRandom(), this.getPosZRandom(1.0D), d0, d1, d2);
        }
    }

    public boolean isAllowed(BlockState blockState, BlockPos blockPos) {
        EventAngelBreakEvent eventAngelBreakEvent = new EventAngelBreakEvent(this, blockState, blockPos);
        MinecraftForge.EVENT_BUS.post(eventAngelBreakEvent);
        return !eventAngelBreakEvent.isCanceled();
    }

    public enum AngelVariants {
        MOSSY(new ItemStack(Blocks.VINE)), NORMAL(new ItemStack(Blocks.COBBLESTONE)), RUSTED(new ItemStack(Blocks.GRANITE)), RUSTED_NO_ARM(new ItemStack(Blocks.GRANITE)), RUSTED_NO_WING(new ItemStack(Blocks.GRANITE)), RUSTED_HEADLESS(true, new ItemStack(Blocks.GRANITE));

        private final boolean headless;
        private final ItemStack dropStack;

        AngelVariants(ItemStack stack) {
            this(false, stack);
        }

        AngelVariants(boolean b, ItemStack stack) {
            headless = b;
            this.dropStack = stack;
        }

        public ItemStack getDropStack() {
            return dropStack;
        }

        public boolean isHeadless() {
            return headless;
        }
    }

}
