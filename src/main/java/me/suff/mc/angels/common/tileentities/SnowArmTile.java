package me.suff.mc.angels.common.tileentities;

import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.entities.AngelEnums;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import me.suff.mc.angels.common.misc.WAConstants;
import me.suff.mc.angels.common.variants.AbstractVariant;
import me.suff.mc.angels.common.variants.AngelTypes;
import me.suff.mc.angels.utils.AngelUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import static net.minecraft.block.SnowBlock.LAYERS;


public class SnowArmTile extends TileEntity implements ITickableTileEntity {

    private final AxisAlignedBB AABB = new AxisAlignedBB(0.2, 0, 0, 0.8, 2, 0.1);
    private SnowAngelStages snowAngelStages = SnowAngelStages.ARM;
    private AbstractVariant angelVariant = AngelTypes.NORMAL.get();
    private boolean hasSetup = false;
    private int rotation = 0;

    public SnowArmTile() {
        super(WAObjects.Tiles.SNOW_ANGEL.get());
    }

    public SnowAngelStages getSnowAngelStage() {
        if(snowAngelStages == null){
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
    public void load(BlockState state, CompoundNBT nbt) {
        super.load(state, nbt);

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
    public CompoundNBT save(CompoundNBT compound) {
        compound.putString(WAConstants.SNOW_STAGE, snowAngelStages.name());
        compound.putString(WAConstants.VARIENT, angelVariant.getRegistryName().toString());
        compound.putInt("rotation", rotation);
        compound.putBoolean("setup", hasSetup);
        return super.save(compound);
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(worldPosition, 3, getUpdateTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return save(new CompoundNBT());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        super.onDataPacket(net, pkt);
        handleUpdateTag(getBlockState(), pkt.getTag());
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        this.load(state, tag);
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
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
    public void tick() {

        if (snowAngelStages == SnowAngelStages.ARM) return;

        if (level != null && !level.getEntitiesOfClass(PlayerEntity.class, AABB.move(getBlockPos())).isEmpty() && !level.isClientSide) {
            WeepingAngelEntity angel = new WeepingAngelEntity(level);
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

        //Randomness for world generatiopn
        if (!hasSetup) {
            setRotation(level.random.nextInt(360));
            setSnowAngelStage(AngelUtil.randowSnowStage());
            hasSetup = true;
            sendUpdates();
        }
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
