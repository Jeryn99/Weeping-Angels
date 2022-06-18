package craig.software.mc.angels.common.blockentities;

import craig.software.mc.angels.client.poses.WeepingAngelPose;
import craig.software.mc.angels.common.WAObjects;
import craig.software.mc.angels.common.blocks.StatueBlock;
import craig.software.mc.angels.common.entities.AngelType;
import craig.software.mc.angels.common.entities.WeepingAngel;
import craig.software.mc.angels.common.level.WAFeatures;
import craig.software.mc.angels.common.misc.WAConstants;
import craig.software.mc.angels.common.variants.AngelTypes;
import craig.software.mc.angels.common.variants.AngelVariant;
import craig.software.mc.angels.config.WAConfig;
import craig.software.mc.angels.utils.AngelUtil;
import craig.software.mc.angels.utils.ViewUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

public class StatueBlockEntity extends BlockEntity implements BlockEntityTicker<StatueBlockEntity>, IPlinth {

    private String type = AngelType.DISASTER_MC.name();
    private WeepingAngelPose pose = WeepingAngelPose.getRandomPose(RandomSource.create());
    private AngelVariant angelVariant = AngelTypes.NORMAL.get();


    public StatueBlockEntity(BlockPos pos, BlockState state) {
        super(WAObjects.Tiles.STATUE.get(), pos, state);

    }

    @Override
    public void load(@NotNull CompoundTag compound) {
        super.load(compound);
        setPose(WeepingAngelPose.getPose(compound.getString("pose")));
        type = compound.getString("model");
        if (compound.contains(WAConstants.VARIENT)) {
            setAngelVarients(AngelTypes.VARIANTS_REGISTRY.get().getValue(new ResourceLocation(compound.getString(WAConstants.VARIENT))));
        }
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag compound) {
        super.saveAdditional(compound);
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
    public @NotNull CompoundTag getUpdateTag() {
        CompoundTag compound = new CompoundTag();
        compound.putString("model", type);
        compound.putString("pose", pose.name());
        compound.putString(WAConstants.VARIENT, angelVariant.getRegistryName().toString());
        return compound;
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

    @Override
    public void tick(@NotNull Level p_155253_, @NotNull BlockPos p_155254_, @NotNull BlockState p_155255_, @NotNull StatueBlockEntity p_155256_) {
        if (level.isClientSide) return;

        ServerLevel world = (ServerLevel) level;
        boolean isGraveYard = world.structureManager().getStructureAt(getBlockPos(), AngelUtil.getConfigured(world, WAFeatures.GRAVEYARD.getId())).isValid();
        if (level.getGameTime() % 200 == 0 && isGraveYard && world.random.nextBoolean()) {
            Player playerentity = this.level.getNearestPlayer(this.getBlockPos().getX(), getBlockPos().getY(), getBlockPos().getZ(), 50.0D, false);
            if (playerentity != null) {
                if (ViewUtil.isInSightPos(playerentity, getBlockPos())) {
                    BlockState newState = getBlockState().setValue(StatueBlock.ROTATION, Mth.floor((double) (playerentity.yHeadRot * 16.0F / 360.0F) + 0.5D) & 15);
                    level.setBlock(getBlockPos(), newState, 67);
                    changePose();
                }
            }
        }

        if (level.getDifficulty() != Difficulty.PEACEFUL && WAConfig.CONFIG.spawnFromBlocks.get() && level.getBestNeighborSignal(worldPosition) > 0 && level.getBlockEntity(worldPosition) instanceof StatueBlockEntity) {
            WeepingAngel angel = new WeepingAngel(level);
            angel.setVarient(angelVariant);
            angel.setType(type);
            angel.moveTo(worldPosition.getX() + 0.5D, worldPosition.getY(), worldPosition.getZ() + 0.5D, 22.5F * this.getBlockState().getValue(StatueBlock.ROTATION), 0);
            angel.setPose(getPose());
            level.addFreshEntity(angel);
            level.removeBlock(worldPosition, false);
        }
    }


    public WeepingAngelPose getPose() {
        return pose;
    }

    public void setPose(WeepingAngelPose pose) {
        this.pose = pose;
    }

    public AngelVariant getAngelVarients() {
        return angelVariant;
    }

    public void setAngelVarients(AngelVariant angelVariants) {
        this.angelVariant = angelVariants;
    }

    @Override
    public void onLoad() {
        if (getPose() == null) {
            setPose(WeepingAngelPose.HIDING);
            setChanged();
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
        return getPose();
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
