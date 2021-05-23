package me.suff.mc.angels.common.tileentities;

import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.entities.AngelEnums;
import me.suff.mc.angels.common.entities.AngelEnums.AngelType;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import me.suff.mc.angels.common.misc.WAConstants;
import me.suff.mc.angels.config.WAConfig;
import me.suff.mc.angels.utils.AngelUtils;
import me.suff.mc.angels.utils.NBTPatcher;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;

import static me.suff.mc.angels.common.blocks.PlinthBlock.CLASSIC;

public class PlinthTile extends TileEntity implements ITickableTileEntity, IPlinth {

    private boolean hasSpawned = false;
    private String type = AngelEnums.AngelType.ANGELA_MC.name();
    private WeepingAngelPose pose = WeepingAngelPose.getRandomPose(AngelUtils.RAND);
    private WeepingAngelEntity.AngelVariants angelVariants = WeepingAngelEntity.AngelVariants.NORMAL;

    public PlinthTile() {
        super(WAObjects.Tiles.PLINTH.get());
    }

    public boolean getHasSpawned() {
        return hasSpawned;
    }

    public void setHasSpawned(boolean hasSpawned) {
        this.hasSpawned = hasSpawned;
    }

    @Override
    public void load(BlockState state, CompoundNBT compound) {
        super.load(state, compound);

        setHasSpawned(compound.getBoolean("hasSpawned"));
        setPose(WeepingAngelPose.getPose(compound.getString("pose")));
        type = compound.getString("model");
        if (compound.contains(WAConstants.VARIENT)) {
            setAngelVarients(WeepingAngelEntity.AngelVariants.valueOf(compound.getString(WAConstants.VARIENT)));
        }
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        super.save(compound);
        NBTPatcher.angelaToVillager(compound, "model");
        compound.putBoolean("hasSpawned", hasSpawned);
        compound.putString("model", type);
        compound.putString("pose", pose.name());
        compound.putString(WAConstants.VARIENT, angelVariants.name());
        return compound;
    }

    public AngelEnums.AngelType getAngelType() {
        return AngelEnums.AngelType.valueOf(type.isEmpty() ? AngelType.ANGELA_MC.name() : type);
    }

    public void setAngelType(String type) {
        this.type = type;
    }

    public void setAngelType(AngelEnums.AngelType type) {
        this.type = type.name();
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
    public AxisAlignedBB getRenderBoundingBox() {
        return super.getRenderBoundingBox().inflate(8, 8, 8);
    }

    public void sendUpdates() {
        level.updateNeighbourForOutputSignal(worldPosition, getBlockState().getBlock());
        level.sendBlockUpdated(worldPosition, level.getBlockState(worldPosition), level.getBlockState(worldPosition), 3);
        setChanged();
    }

    @Override
    public void tick() {
        if (level.isClientSide) return;


        boolean isClassic = getAngelType() == AngelType.A_DIZZLE;
        boolean current = getBlockState().getValue(CLASSIC);

        if (isClassic && !current) {
            level.setBlockAndUpdate(worldPosition, getBlockState().setValue(CLASSIC, true));
        } else if (!isClassic && current) {
            level.setBlockAndUpdate(worldPosition, getBlockState().setValue(CLASSIC, false));
        }


        if (WAConfig.CONFIG.spawnFromBlocks.get() && level.getBestNeighborSignal(worldPosition) > 0 && level.getBlockEntity(worldPosition) instanceof PlinthTile) {
            PlinthTile plinth = (PlinthTile) level.getBlockEntity(worldPosition);
            if (!plinth.getHasSpawned()) {
                WeepingAngelEntity angel = new WeepingAngelEntity(level);
                angel.setType(type);
                angel.moveTo(worldPosition.getX() + 0.5D, worldPosition.getY() + 1, worldPosition.getZ() + 0.5D, 0, 0);
                angel.setPose(getPose());
                angel.setVarient(angelVariants);
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

    public WeepingAngelEntity.AngelVariants getAngelVarients() {
        return angelVariants;
    }

    public void setAngelVarients(WeepingAngelEntity.AngelVariants angelVariants) {
        this.angelVariants = angelVariants;
    }

    @Override
    public void onLoad() {
        if (getPose() == null) {
            setPose(WeepingAngelPose.HIDING);
        }
    }

    @Override
    public void changeModel() {
        setAngelType(AngelUtils.randomType());
    }

    @Override
    public void changePose() {
        setPose(WeepingAngelPose.getRandomPose(AngelUtils.RAND));
    }

    @Override
    public void sendUpdatesToClient() {
        level.updateNeighbourForOutputSignal(worldPosition, getBlockState().getBlock());
        level.sendBlockUpdated(worldPosition, level.getBlockState(worldPosition), level.getBlockState(worldPosition), 3);
        setChanged();
    }
}
