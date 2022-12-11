package mc.craig.software.angels.common.tileentities;

import mc.craig.software.angels.client.poses.WeepingAngelPose;
import mc.craig.software.angels.common.WAObjects;
import mc.craig.software.angels.common.blocks.StatueBlock;
import mc.craig.software.angels.common.entities.AngelType;
import mc.craig.software.angels.common.entities.WeepingAngelEntity;
import mc.craig.software.angels.common.misc.WAConstants;
import mc.craig.software.angels.common.variants.AbstractVariant;
import mc.craig.software.angels.common.variants.AngelVariants;
import mc.craig.software.angels.config.WAConfig;
import mc.craig.software.angels.utils.AngelUtil;
import mc.craig.software.angels.utils.NBTPatcher;
import mc.craig.software.angels.utils.ViewUtil;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.server.ServerWorld;

public class StatueTile extends TileEntity implements ITickableTileEntity, IPlinth {

    private String type = AngelType.DISASTER_MC.name();
    private WeepingAngelPose pose = WeepingAngelPose.getRandomPose(AngelUtil.RAND);
    private AbstractVariant angelVariant = AngelVariants.NORMAL.get();


    public StatueTile() {
        super(WAObjects.Tiles.STATUE.get());
    }

    @Override
    public void load(BlockState state, CompoundNBT compound) {
        super.load(state, compound);
        NBTPatcher.strip(compound, "model");
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
        compound.putString("model", type);
        compound.putString("pose", pose.name());
        compound.putString(WAConstants.VARIENT, angelVariant.getRegistryName().toString());
        NBTPatcher.strip(compound, "model");
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

    @Override
    public void tick() {
        if (level.isClientSide) return;

        ServerWorld world = (ServerWorld) level;
        boolean isGraveYard = world.structureFeatureManager().getStructureAt(getBlockPos(), true, WAObjects.Structures.GRAVEYARD.get()).isValid();

        if (level.getGameTime() % 200 == 0 && isGraveYard && world.random.nextBoolean()) {
            PlayerEntity playerentity = this.level.getNearestPlayer(this.getBlockPos().getX(), getBlockPos().getY(), getBlockPos().getZ(), 50.0D, false);
            if (playerentity != null) {
                if (ViewUtil.isInSightPos(playerentity, getBlockPos())) {
                    BlockState newState = getBlockState().setValue(StatueBlock.ROTATION, MathHelper.floor((double) (playerentity.yHeadRot * 16.0F / 360.0F) + 0.5D) & 15);
                    level.setBlock(getBlockPos(), newState, 67);
                    changePose();
                }
            }
        }


        if (WAConfig.CONFIG.spawnFromBlocks.get() && level.getBestNeighborSignal(worldPosition) > 0 && level.getBlockEntity(worldPosition) instanceof StatueTile) {
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
            setChanged();
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
        WeepingAngelEntity angel = new WeepingAngelEntity(level);
        angel.setVarient(getAngelVarients());
        angel.setType(getAngelType());
        angel.moveTo(worldPosition.getX() + 0.5D, worldPosition.getY(), worldPosition.getZ() + 0.5D, 0, 0);
        angel.setPose(getPose());
        level.addFreshEntity(angel);
        level.removeBlock(worldPosition, false);
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
