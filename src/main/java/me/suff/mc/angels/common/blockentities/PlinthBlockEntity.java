package me.suff.mc.angels.common.blockentities;

import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.entities.AngelType;
import me.suff.mc.angels.common.entities.WeepingAngel;
import me.suff.mc.angels.common.misc.WAConstants;
import me.suff.mc.angels.common.variants.AbstractVariant;
import me.suff.mc.angels.common.variants.AngelTypes;
import me.suff.mc.angels.config.WAConfig;
import me.suff.mc.angels.utils.AngelUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import static me.suff.mc.angels.common.blocks.PlinthBlock.CLASSIC;

public class PlinthBlockEntity extends BlockEntity implements BlockEntityTicker<PlinthBlockEntity>, IPlinth {

    private boolean hasSpawned = false;
    private String type = AngelType.DISASTER_MC.name();
    private WeepingAngelPose pose = WeepingAngelPose.getRandomPose(RandomSource.create());
    private AbstractVariant angelVariant = AngelTypes.NORMAL.get();

    public PlinthBlockEntity(BlockPos blockPos, BlockState state) {
        super(WAObjects.Tiles.PLINTH.get(), blockPos, state);
    }

    public boolean getHasSpawned() {
        return hasSpawned;
    }

    public void setHasSpawned(boolean hasSpawned) {
        this.hasSpawned = hasSpawned;
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);

        setHasSpawned(compound.getBoolean("hasSpawned"));
        setPose(WeepingAngelPose.getPose(compound.getString("pose")));
        type = compound.getString("model");
        if (compound.contains(WAConstants.VARIENT)) {
            setAngelVarients(AngelTypes.VARIANTS_REGISTRY.get().getValue(new ResourceLocation(compound.getString(WAConstants.VARIENT))));
        }
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.putBoolean("hasSpawned", hasSpawned);
        compound.putString("model", type);
        compound.putString("pose", pose.name());
        compound.putString(WAConstants.VARIENT, angelVariant.getRegistryName().toString());
    }

    public AngelType getAngelType() {
        boolean found = false;
        for (AngelType value : AngelType.values()) {
            if (value.name().equals(type)) {
                found = true;
                break;
            }
        }
        if (!found) {
            type = AngelType.DISASTER_MC.name();
        }


        return AngelType.valueOf(type.isEmpty() ? AngelType.DISASTER_MC.name() : type);
    }

    public void setAngelType(String type) {
        this.type = type;
    }

    public void setAngelType(AngelType type) {
        this.type = type.name();
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putBoolean("hasSpawned", hasSpawned);
        compoundTag.putString("model", type);
        compoundTag.putString("pose", pose.name());
        compoundTag.putString(WAConstants.VARIENT, angelVariant.getRegistryName().toString());
        return compoundTag;
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        super.onDataPacket(net, pkt);
        handleUpdateTag(pkt.getTag());
    }

    @Override
    public AABB getRenderBoundingBox() {
        return super.getRenderBoundingBox().inflate(8, 8, 8);
    }

    public void sendUpdates() {
        level.updateNeighbourForOutputSignal(worldPosition, getBlockState().getBlock());
        level.sendBlockUpdated(worldPosition, level.getBlockState(worldPosition), level.getBlockState(worldPosition), 3);
        setChanged();
    }

    @Override
    public void tick(Level p_155253_, BlockPos p_155254_, BlockState p_155255_, PlinthBlockEntity p_155256_) {
        if (level.isClientSide) return;

        boolean isClassic = getAngelType() == AngelType.A_DIZZLE;
        boolean current = getBlockState().getValue(CLASSIC);

        if (isClassic && !current) {
            level.setBlockAndUpdate(worldPosition, getBlockState().setValue(CLASSIC, true));
        } else if (!isClassic && current) {
            level.setBlockAndUpdate(worldPosition, getBlockState().setValue(CLASSIC, false));
        }


        if (level.getDifficulty() != Difficulty.PEACEFUL && WAConfig.CONFIG.spawnFromBlocks.get() && level.getBestNeighborSignal(worldPosition) > 0 && level.getBlockEntity(worldPosition) instanceof PlinthBlockEntity plinth) {
            if (!plinth.getHasSpawned()) {
                WeepingAngel angel = new WeepingAngel(level);
                angel.setType(type);
                angel.moveTo(worldPosition.getX() + 0.5D, worldPosition.getY() + 1, worldPosition.getZ() + 0.5D, 0, 0);
                angel.setPose(getPose());
                angel.setVarient(angelVariant);
                level.addFreshEntity(angel);
                plinth.setHasSpawned(true);
                sendUpdates();
            }
        }
    }

    public WeepingAngelPose getPose() {
        return pose;
    }

    public void setPose(WeepingAngelPose pose) {
        this.pose = pose;
    }


    public void setAngelVarients(AbstractVariant angelVariants) {
        this.angelVariant = angelVariants;
    }

    @Override
    public void onLoad() {
        if (getPose() == null) {
            setPose(WeepingAngelPose.HIDING);
        }
    }

    @Override
    public void changeModel() {
        setAngelType(AngelType.next(getAngelType()));
    }

    @Override
    public void changePose() {
        setPose(WeepingAngelPose.next(pose));
    }

    @Override
    public void sendUpdatesToClient() {
        level.updateNeighbourForOutputSignal(worldPosition, getBlockState().getBlock());
        level.sendBlockUpdated(worldPosition, level.getBlockState(worldPosition), level.getBlockState(worldPosition), 3);
        setChanged();
    }

    @Override
    public AngelType getCurrentType() {
        return getAngelType();
    }

    @Override
    public WeepingAngelPose getCurrentPose() {
        return pose;
    }

    @Override
    public AbstractVariant getVariant() {
        return angelVariant;
    }

    @Override
    public void setAbstractVariant(AbstractVariant variant) {
        this.angelVariant = variant;
    }
}
