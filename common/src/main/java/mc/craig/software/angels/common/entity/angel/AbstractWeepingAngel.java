package mc.craig.software.angels.common.entity.angel;

import mc.craig.software.angels.WAConfiguration;
import mc.craig.software.angels.common.WAConstants;
import mc.craig.software.angels.common.entity.angel.ai.AngelEmotion;
import mc.craig.software.angels.common.entity.angel.ai.AngelVariant;
import mc.craig.software.angels.common.entity.angel.ai.BodyRotationAngel;
import mc.craig.software.angels.util.ViewUtil;
import mc.craig.software.angels.util.WAHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class AbstractWeepingAngel extends Monster implements Enemy {
    private static final EntityDataAccessor<Integer> TIME_VIEWED = SynchedEntityData.defineId(AbstractWeepingAngel.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<String> EMOTION = SynchedEntityData.defineId(AbstractWeepingAngel.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Boolean> IS_HOOKED = SynchedEntityData.defineId(AbstractWeepingAngel.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<String> VARIANT = SynchedEntityData.defineId(AbstractWeepingAngel.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Boolean> SHOULD_DROP_LOOT = SynchedEntityData.defineId(AbstractWeepingAngel.class, EntityDataSerializers.BOOLEAN);

    public long timeSincePlayedSound = 0;

    public AbstractWeepingAngel(Level worldIn, EntityType<? extends Monster> entityType) {
        super(entityType, worldIn);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return createMonsterAttributes().
                add(Attributes.ATTACK_DAMAGE, 8D).
                add(Attributes.MAX_HEALTH, 20D).
                add(Attributes.KNOCKBACK_RESISTANCE, 1.0D).
                add(Attributes.MOVEMENT_SPEED, 0.8D).
                add(Attributes.ARMOR, 2.0D);
    }


    @Override
    public void tick() {
        super.tick();
        if (getSeenTime() == 0) {
            setNoAi(false);
        }
    }

    @Override
    public void aiStep() {
        if (!getMainHandItem().isEmpty()) {
            setPersistenceRequired();
        }

        super.aiStep();
        if (!level().isClientSide) {
            List<Player> players = level().getEntitiesOfClass(Player.class, getBoundingBox().inflate(WAConfiguration.CONFIG.stalkRange.get()));
            players.removeIf(player -> player.isSpectator() || player.isInvisible() || player.isSleeping() || player.level() != level());

            if (players.isEmpty()) {
                setSeenTime(0);
                setSpeed(0.5F);
                return;
            }

            Player targetPlayer = null;
            for (Player player : players) {
                if (ViewUtil.isInSight(player, this)) {
                    setSeenTime(getSeenTime() + 1);
                    invokeSeen(player);
                    return;
                }
                if (targetPlayer == null) {
                    targetPlayer = player;
                    setSeenTime(0);
                    setSpeed(0.5F);
                }
            }

            if (isSeen()) return;
            snapLookToPlayer(targetPlayer);
            moveTowards(targetPlayer);
        }
    }


    @Override
    public void makeStuckInBlock(BlockState state, @NotNull Vec3 motionMultiplierIn) {
        if (!state.is(Blocks.COBWEB)) {
            super.makeStuckInBlock(state, motionMultiplierIn);
        }
    }

    private void snapLookToPlayer(Player targetPlayer) {
        Vec3 vecPos = position();
        Vec3 vecPlayerPos = targetPlayer.position();
        float angle = (float) Math.toDegrees((float) Math.atan2(vecPos.z - vecPlayerPos.z, vecPos.x - vecPlayerPos.x));
        yHeadRot = yBodyRot = angle > 180 ? angle : angle + 90;
    }

    public void moveTowards(LivingEntity targetPlayer) {
        getNavigation().moveTo(targetPlayer, getSpeed());
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(TIME_VIEWED, 0);
        builder.define(EMOTION, AngelEmotion.IDLE.getId());
        builder.define(IS_HOOKED, false);
        builder.define(VARIANT, AngelVariant.BASALT.location().toString());
        builder.define(SHOULD_DROP_LOOT, true);
    }


    @Override
    protected boolean shouldDropLoot() {
        return getEntityData().get(SHOULD_DROP_LOOT);
    }

    public AngelVariant getVariant() {
        return AngelVariant.getVariant(ResourceLocation.tryParse(getEntityData().get(VARIANT)));
    }

    public void setVariant(AngelVariant angelVariant) {
        getEntityData().set(VARIANT, angelVariant.location().toString());
    }

    public AngelEmotion getEmotion() {
        String emotion = getEntityData().get(EMOTION);
        for (AngelEmotion angelEmotion : AngelEmotion.values()) {
            if (emotion.equalsIgnoreCase(angelEmotion.getId())) {
                return angelEmotion;
            }
        }
        return AngelEmotion.IDLE;
    }

    public void setEmotion(AngelEmotion angelEmotion){
        getEntityData().set(EMOTION, angelEmotion.getId());
    }

    public boolean isHooked() {
        return getEntityData().get(IS_HOOKED);
    }

    public void setHooked(boolean hooked){
        getEntityData().set(IS_HOOKED, hooked);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains(WAConstants.TIME_SEEN)) setSeenTime(compound.getInt(WAConstants.TIME_SEEN));
        if (compound.contains(WAConstants.EMOTION)) setEmotion(AngelEmotion.find(compound.getString(WAConstants.EMOTION).toUpperCase()));
        if (compound.contains(WAConstants.IS_HOOKED)) setEmotion(AngelEmotion.find(compound.getString(WAConstants.EMOTION).toUpperCase()));
        if (compound.contains(WAConstants.DROPS_LOOT)) setDrops(compound.getBoolean(WAConstants.DROPS_LOOT));
        if (compound.contains(WAConstants.VARIANT))
            setVariant(AngelVariant.getVariant(ResourceLocation.tryParse(compound.getString(WAConstants.VARIANT))));
    }

    public void setDrops(boolean drops) {
        getEntityData().set(SHOULD_DROP_LOOT, drops);
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean(WAConstants.IS_SEEN, isSeen());
        compound.putInt(WAConstants.TIME_SEEN, getSeenTime());
        compound.putString(WAConstants.EMOTION, getEmotion().getId());
        compound.putBoolean(WAConstants.DROPS_LOOT, shouldDropLoot());
        compound.putString(WAConstants.VARIANT, getVariant().location().toString());
    }


    public SoundEvent getSeenSound() {
        AngelVariant angelVariant = getVariant();
        ItemStack itemStack = angelVariant.getDrops();
        if (itemStack.getItem() instanceof BlockItem blockItem) {
            Block block = blockItem.getBlock();
            return block.defaultBlockState().getSoundType().getBreakSound();
        }
        return SoundEvents.STONE_PLACE;
    }

    @Override
    public boolean displayFireAnimation() {
        return false;
    }

    public long getTimeSincePlayedSound() {
        return timeSincePlayedSound;
    }

    public void setTimeSincePlayedSound(long timeSincePlayedSound) {
        this.timeSincePlayedSound = timeSincePlayedSound;
    }

    public boolean isSeen() {
        return getSeenTime() > 0;
    }

    public int getSeenTime() {
        return getEntityData().get(TIME_VIEWED);
    }

    public void setSeenTime(int time) {
        getEntityData().set(TIME_VIEWED, time);
    }

    @Override
    public void knockback(double strength, double x, double z) {

    }

    @Override
    protected @NotNull BodyRotationControl createBodyControl() {
        return new BodyRotationAngel(this);
    }

    public void invokeSeen(Player player) {
        getNavigation().moveTo((Path) null, 0);
        setNoAi(true);
    }

    @Override
    protected boolean isImmobile() {
        return getSeenTime() > 0;
    }
}