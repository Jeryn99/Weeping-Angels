package craig.software.mc.angels.common.blockentities;

import craig.software.mc.angels.common.WAObjects;
import craig.software.mc.angels.common.entities.AngelType;
import craig.software.mc.angels.common.entities.WeepingAngel;
import craig.software.mc.angels.common.misc.WAConstants;
import craig.software.mc.angels.common.variants.AngelTypes;
import craig.software.mc.angels.common.variants.AngelVariant;
import craig.software.mc.angels.utils.AngelUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

import static net.minecraft.world.level.block.SnowLayerBlock.LAYERS;


public class SnowAngelBlockEntity extends BlockEntity implements BlockEntityTicker<SnowAngelBlockEntity> {

    private final AABB AABB = new AABB(0.2, 0, 0, 0.8, 2, 0.1);
    private SnowAngelStages snowAngelStages = SnowAngelStages.ARM;
    private AngelVariant angelVariant = AngelTypes.NORMAL.get();
    private boolean hasSetup = false;
    private int rotation = 0;

    public SnowAngelBlockEntity(BlockPos pos, BlockState blockState) {
        super(WAObjects.Tiles.SNOW_ANGEL.get(), pos, blockState);
    }

    public SnowAngelStages getSnowAngelStage() {
        if (snowAngelStages == null) {
            return SnowAngelStages.BODY;
        }
        return snowAngelStages;
    }

    public void setSnowAngelStage(SnowAngelStages snowAngelStages) {
        this.snowAngelStages = snowAngelStages;
    }

    public AngelVariant getVariant() {
        return angelVariant;
    }

    public void setVariant(AngelVariant angelVariant) {
        this.angelVariant = angelVariant;
    }


    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);

        if (nbt.contains(WAConstants.VARIENT)) {
            setVariant(AngelTypes.VARIANTS_REGISTRY.get().getValue(new ResourceLocation(nbt.getString(WAConstants.VARIENT))));
        }

        if (nbt.contains(WAConstants.SNOW_STAGE)) {
            setSnowAngelStage(SnowAngelStages.valueOf(nbt.getString(WAConstants.SNOW_STAGE)));
        }

        rotation = nbt.getInt("rotation");
        hasSetup = nbt.getBoolean("setup");

    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        compound.putString(WAConstants.SNOW_STAGE, snowAngelStages.name());
        compound.putString(WAConstants.VARIENT, angelVariant.getRegistryName().toString());
        compound.putInt("rotation", rotation);
        compound.putBoolean("setup", hasSetup);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putString(WAConstants.SNOW_STAGE, snowAngelStages.name());
        compoundTag.putString(WAConstants.VARIENT, angelVariant.getRegistryName().toString());
        compoundTag.putInt("rotation", rotation);
        compoundTag.putBoolean("setup", hasSetup);
        return compoundTag;
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        super.onDataPacket(net, pkt);
        handleUpdateTag(pkt.getTag());
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
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

    public boolean isHasSetup() {
        return hasSetup;
    }

    public void setHasSetup(boolean hasSetup) {
        this.hasSetup = hasSetup;
    }

    @Override
    public void tick(@NotNull Level p_155253_, @NotNull BlockPos p_155254_, @NotNull BlockState p_155255_, @NotNull SnowAngelBlockEntity p_155256_) {

        //Randomness for world generation
        if (!hasSetup) {
            setRotation(level.random.nextInt(360));
            setSnowAngelStage(AngelUtil.randowSnowStage());
            hasSetup = true;
            sendUpdates();
        }

        if (snowAngelStages == SnowAngelStages.ARM) return;
        if (level != null && !level.getEntitiesOfClass(Player.class, AABB.move(getBlockPos())).isEmpty() && !level.isClientSide) {
            WeepingAngel angel = new WeepingAngel(level);
            angel.setType(AngelType.DISASTER_MC);
            angel.setVarient(angelVariant);
            BlockPos newPos = getBlockPos();
            angel.setPos(newPos.getX() + 0.5D, newPos.getY(), newPos.getZ() + 0.5D);
            level.addFreshEntity(angel);
            Integer layers = level.getBlockState(worldPosition).getValue(LAYERS);
            level.setBlockAndUpdate(worldPosition, Blocks.SNOW.defaultBlockState().setValue(LAYERS, layers));
        }

        //Ensure that any headless variants don't appear with head stage
        if (angelVariant.isHeadless() && snowAngelStages == SnowAngelStages.HEAD || !hasSetup) {
            setSnowAngelStage(AngelUtil.randowSnowStage());
            hasSetup = true;
            sendUpdates();
        }
    }

    @Override
    public boolean isRemoved() {
        return false;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
        sendUpdates();
    }

    public enum SnowAngelStages {
        ARM, HEAD, BODY, WINGS
    }


}
