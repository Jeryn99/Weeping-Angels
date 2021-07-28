package me.suff.mc.angels.common.blockentities;

import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.entities.AngelEnums;
import me.suff.mc.angels.common.entities.WeepingAngel;
import me.suff.mc.angels.common.misc.WAConstants;
import me.suff.mc.angels.common.variants.AbstractVariant;
import me.suff.mc.angels.common.variants.AngelTypes;
import me.suff.mc.angels.utils.AngelUtil;
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

import static net.minecraft.world.level.block.SnowLayerBlock.LAYERS;


public class SnowAngelBlockEntity extends BlockEntity implements BlockEntityTicker<SnowAngelBlockEntity> {

    private final AABB AABB = new AABB(0.2, 0, 0, 0.8, 2, 0.1);
    private SnowAngelStages snowAngelStages = SnowAngelStages.ARM;
    private AbstractVariant angelVariant = AngelTypes.NORMAL.get();
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

    public AbstractVariant getVariant() {
        return angelVariant;
    }

    public void setVariant(AbstractVariant angelVariant) {
        this.angelVariant = angelVariant;
    }


    @Override
    public void load(CompoundTag nbt) {
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
    public CompoundTag save(CompoundTag compound) {
        compound.putString(WAConstants.SNOW_STAGE, snowAngelStages.name());
        compound.putString(WAConstants.VARIENT, angelVariant.getRegistryName().toString());
        compound.putInt("rotation", rotation);
        compound.putBoolean("setup", hasSetup);
        return super.save(compound);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return new ClientboundBlockEntityDataPacket(worldPosition, 3, getUpdateTag());
    }

    @Override
    public CompoundTag getUpdateTag() {
        return save(new CompoundTag());
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
    public void tick(Level p_155253_, BlockPos p_155254_, BlockState p_155255_, SnowAngelBlockEntity p_155256_) {

        //Randomness for world generatiopn
        if (!hasSetup) {
            setRotation(level.random.nextInt(360));
            setSnowAngelStage(AngelUtil.randowSnowStage());
            hasSetup = true;
            sendUpdates();
        }

        if (snowAngelStages == SnowAngelStages.ARM) return;
        if (level != null && !level.getEntitiesOfClass(Player.class, AABB.move(getBlockPos())).isEmpty() && !level.isClientSide) {
            WeepingAngel angel = new WeepingAngel(level);
            angel.setType(AngelEnums.AngelType.ANGELA_MC);
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
