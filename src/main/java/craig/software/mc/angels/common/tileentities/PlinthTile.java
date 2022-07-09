package craig.software.mc.angels.common.tileentities;

import craig.software.mc.angels.client.poses.WeepingAngelPose;
import craig.software.mc.angels.common.blocks.PlinthBlock;
import craig.software.mc.angels.common.entities.AngelType;
import craig.software.mc.angels.common.misc.WAConstants;
import craig.software.mc.angels.common.variants.AbstractVariant;
import craig.software.mc.angels.common.variants.AngelVariants;
import craig.software.mc.angels.config.WAConfig;
import craig.software.mc.angels.utils.NBTPatcher;
import craig.software.mc.angels.common.WAObjects;
import craig.software.mc.angels.common.entities.WeepingAngelEntity;
import craig.software.mc.angels.utils.AngelUtil;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;

public class PlinthTile extends TileEntity implements ITickableTileEntity, IPlinth {

    private boolean hasSpawned = false;
    private String type = AngelType.DISASTER_MC.name();
    private WeepingAngelPose pose = WeepingAngelPose.getRandomPose(AngelUtil.RAND);
    private AbstractVariant angelVariant = AngelVariants.NORMAL.get();

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
        NBTPatcher.strip(compound, "model");
        type = compound.getString("model");
        if (compound.contains(WAConstants.VARIENT)) {
            setAngelVarients(AngelVariants.VARIANTS_REGISTRY.get().getValue(new ResourceLocation(compound.getString(WAConstants.VARIENT))));
        }
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        super.save(compound);
        NBTPatcher.strip(compound, "model");
        compound.putBoolean("hasSpawned", hasSpawned);
        compound.putString("model", type);
        compound.putString("pose", pose.name());
        NBTPatcher.strip(compound, "model");
        compound.putString(WAConstants.VARIENT, angelVariant.getRegistryName().toString());
        return compound;
    }

    public AngelType getAngelType() {
        return AngelType.get(type);
    }

    public void setAngelType(String type) {
        this.type = type;
    }

    public void setAngelType(AngelType type) {
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
        boolean current = getBlockState().getValue(PlinthBlock.CLASSIC);

        if (isClassic && !current) {
            level.setBlockAndUpdate(worldPosition, getBlockState().setValue(PlinthBlock.CLASSIC, true));
        } else if (!isClassic && current) {
            level.setBlockAndUpdate(worldPosition, getBlockState().setValue(PlinthBlock.CLASSIC, false));
        }


        if (WAConfig.CONFIG.spawnFromBlocks.get() && level.getBestNeighborSignal(worldPosition) > 0 && level.getBlockEntity(worldPosition) instanceof PlinthTile) {
            PlinthTile plinth = (PlinthTile) level.getBlockEntity(worldPosition);
            spawn();
        }
    }

    public WeepingAngelPose getPose() {
        return pose;
    }

    public void setPose(WeepingAngelPose pose) {
        this.pose = pose;
    }

    public AbstractVariant getAngelVarients() {
        return angelVariant;
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
        setPose(WeepingAngelPose.next(getPose()));
    }

    @Override
    public void sendUpdatesToClient() {
        level.updateNeighbourForOutputSignal(worldPosition, getBlockState().getBlock());
        level.sendBlockUpdated(worldPosition, level.getBlockState(worldPosition), level.getBlockState(worldPosition), 3);
        setChanged();
    }

    @Override
    public void spawn() {
        if (!getHasSpawned()) {
            WeepingAngelEntity angel = new WeepingAngelEntity(level);
            angel.setType(getAngelType());
            angel.moveTo(worldPosition.getX() + 0.5D, worldPosition.getY() + 1, worldPosition.getZ() + 0.5D, 0, 0);
            angel.setPose(getPose());
            angel.setVarient(getAngelVarients());
            level.addFreshEntity(angel);
            setHasSpawned(true);
            sendUpdates();
        }
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
