package me.suff.mc.angels.common.tileentities;

import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.entities.AngelEnums;
import me.suff.mc.angels.common.entities.AngelEnums.AngelType;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import me.suff.mc.angels.common.misc.WAConstants;
import me.suff.mc.angels.common.variants.AbstractVariant;
import me.suff.mc.angels.common.variants.AngelTypes;
import me.suff.mc.angels.config.WAConfig;
import me.suff.mc.angels.utils.AngelUtil;
import me.suff.mc.angels.utils.NBTPatcher;
import me.suff.mc.angels.utils.ViewUtil;
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

import static me.suff.mc.angels.common.blocks.StatueBlock.ROTATION;

public class StatueTile extends TileEntity implements ITickableTileEntity, IPlinth {

    private String type = AngelEnums.AngelType.ANGELA_MC.name();
    private WeepingAngelPose pose = WeepingAngelPose.getRandomPose(AngelUtil.RAND);
    private AbstractVariant angelVariant = AngelTypes.NORMAL.get();


    public StatueTile() {
        super(WAObjects.Tiles.STATUE.get());
    }

    @Override
    public void load(BlockState state, CompoundNBT compound) {
        super.load(state, compound);
        NBTPatcher.angelaToVillager(compound, "model");
        setPose(WeepingAngelPose.getPose(compound.getString("pose")));
        type = compound.getString("model");
        if (compound.contains(WAConstants.VARIENT)) {
            setAngelVarients(AngelTypes.VARIANTS_REGISTRY.get().getValue(new ResourceLocation(compound.getString(WAConstants.VARIENT))));
        }
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        super.save(compound);
        compound.putString("model", type);
        compound.putString("pose", pose.name());
        compound.putString(WAConstants.VARIENT, angelVariant.getRegistryName().toString());
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

    @Override
    public void tick() {
        if (level.isClientSide) return;

        ServerWorld world = (ServerWorld) level;
        boolean isGraveYard = world.structureFeatureManager().getStructureAt(getBlockPos(), true, WAObjects.Structures.GRAVEYARD.get()).isValid();

        if (level.getGameTime() % 200 == 0 && isGraveYard && world.random.nextBoolean()) {
            PlayerEntity playerentity = this.level.getNearestPlayer(this.getBlockPos().getX(), getBlockPos().getY(), getBlockPos().getZ(), 50.0D, false);
            if (playerentity != null) {
                if (ViewUtil.isInSightPos(playerentity, getBlockPos())) {
                    BlockState newState = getBlockState().setValue(ROTATION, MathHelper.floor((double) (playerentity.yHeadRot * 16.0F / 360.0F) + 0.5D) & 15);
                    level.setBlock(getBlockPos(), newState, 67);
                    changePose();
                }
            }
        }


        if (WAConfig.CONFIG.spawnFromBlocks.get() && level.getBestNeighborSignal(worldPosition) > 0 && level.getBlockEntity(worldPosition) instanceof StatueTile) {
            WeepingAngelEntity angel = new WeepingAngelEntity(level);
            angel.setVarient(angelVariant);
            angel.setType(type);
            angel.moveTo(worldPosition.getX() + 0.5D, worldPosition.getY(), worldPosition.getZ() + 0.5D, 0, 0);
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
        setAngelType(AngelUtil.randomType());
    }

    @Override
    public void changePose() {
        setPose(WeepingAngelPose.getRandomPose(AngelUtil.RAND));
    }

    @Override
    public void sendUpdatesToClient() {
        level.updateNeighbourForOutputSignal(worldPosition, getBlockState().getBlock());
        level.sendBlockUpdated(worldPosition, level.getBlockState(worldPosition), level.getBlockState(worldPosition), 3);
        setChanged();
    }
}
