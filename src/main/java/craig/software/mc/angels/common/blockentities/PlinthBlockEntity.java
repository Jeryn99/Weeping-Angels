package craig.software.mc.angels.common.blockentities;

import craig.software.mc.angels.client.poses.WeepingAngelPose;
import craig.software.mc.angels.common.WAObjects;
import craig.software.mc.angels.common.blocks.PlinthBlock;
import craig.software.mc.angels.common.entities.WeepingAngelTypes;
import craig.software.mc.angels.common.entities.WeepingAngel;
import craig.software.mc.angels.common.misc.WAConstants;
import craig.software.mc.angels.common.variants.AngelVariants;
import craig.software.mc.angels.common.variants.AngelVariant;
import craig.software.mc.angels.config.WAConfiguration;
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
import org.jetbrains.annotations.NotNull;

public class PlinthBlockEntity extends BlockEntity implements BlockEntityTicker<PlinthBlockEntity>, IPlinth {

    private boolean hasSpawned = false;
    private String type = WeepingAngelTypes.DISASTER_MC.name();
    private WeepingAngelPose pose = WeepingAngelPose.getRandomPose(RandomSource.create());
    private AngelVariant angelVariant = AngelVariants.NORMAL.get();

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
    public void load(@NotNull CompoundTag compound) {
        super.load(compound);

        setHasSpawned(compound.getBoolean("hasSpawned"));
        setPose(WeepingAngelPose.getPose(compound.getString("pose")));
        type = compound.getString("model");
        if (compound.contains(WAConstants.VARIENT)) {
            setAngelVarients(AngelVariants.VARIANTS_REGISTRY.get().getValue(new ResourceLocation(compound.getString(WAConstants.VARIENT))));
        }
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag compound) {
        super.saveAdditional(compound);
        compound.putBoolean("hasSpawned", hasSpawned);
        compound.putString("model", type);
        compound.putString("pose", pose.name());
        compound.putString(WAConstants.VARIENT, angelVariant.getRegistryName().toString());
    }

    public WeepingAngelTypes getAngelType() {
        boolean found = false;
        for (WeepingAngelTypes value : WeepingAngelTypes.values()) {
            if (value.name().equals(type)) {
                found = true;
                break;
            }
        }
        if (!found) {
            type = WeepingAngelTypes.DISASTER_MC.name();
        }


        return WeepingAngelTypes.valueOf(type.isEmpty() ? WeepingAngelTypes.DISASTER_MC.name() : type);
    }

    public void setAngelType(String type) {
        this.type = type;
    }

    public void setAngelType(WeepingAngelTypes type) {
        this.type = type.name();
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
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
    public void tick(@NotNull Level p_155253_, @NotNull BlockPos p_155254_, @NotNull BlockState p_155255_, @NotNull PlinthBlockEntity p_155256_) {
        if (level.isClientSide) return;

        boolean isClassic = getAngelType() == WeepingAngelTypes.A_DIZZLE;
        boolean current = getBlockState().getValue(PlinthBlock.CLASSIC);

        if (isClassic && !current) {
            level.setBlockAndUpdate(worldPosition, getBlockState().setValue(PlinthBlock.CLASSIC, true));
        } else if (!isClassic && current) {
            level.setBlockAndUpdate(worldPosition, getBlockState().setValue(PlinthBlock.CLASSIC, false));
        }


        if (level.getDifficulty() != Difficulty.PEACEFUL && WAConfiguration.CONFIG.spawnFromBlocks.get() && level.getBestNeighborSignal(worldPosition) > 0 && level.getBlockEntity(worldPosition) instanceof PlinthBlockEntity plinth) {
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


    public void setAngelVarients(AngelVariant angelVariants) {
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
        setAngelType(WeepingAngelTypes.next(getAngelType()));
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
    public WeepingAngelTypes getCurrentType() {
        return getAngelType();
    }

    @Override
    public WeepingAngelPose getCurrentPose() {
        return pose;
    }

    @Override
    public AngelVariant getVariant() {
        return angelVariant;
    }

    @Override
    public void setAbstractVariant(AngelVariant variant) {
        this.angelVariant = variant;
    }
}
