package me.suff.mc.angels.common.blockentities;

import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.entities.AngelEnums;
import me.suff.mc.angels.common.entities.AngelEnums.AngelType;
import me.suff.mc.angels.common.entities.WeepingAngel;
import me.suff.mc.angels.common.misc.WAConstants;
import me.suff.mc.angels.common.variants.AbstractVariant;
import me.suff.mc.angels.common.variants.AngelTypes;
import me.suff.mc.angels.common.level.WAWorld;
import me.suff.mc.angels.config.WAConfig;
import me.suff.mc.angels.utils.AngelUtil;
import me.suff.mc.angels.utils.ViewUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import static me.suff.mc.angels.common.blocks.StatueBlock.ROTATION;

public class StatueBlockEntity extends BlockEntity implements BlockEntityTicker<StatueBlockEntity>, IPlinth {

    private String type = AngelEnums.AngelType.DISASTER_MC.name();
    private WeepingAngelPose pose = WeepingAngelPose.getRandomPose(AngelUtil.RAND);
    private AbstractVariant angelVariant = AngelTypes.NORMAL.get();


    public StatueBlockEntity(BlockPos pos, BlockState state) {
        super(WAObjects.Tiles.STATUE.get(), pos, state);
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        setPose(WeepingAngelPose.getPose(compound.getString("pose")));
        type = compound.getString("model");
        if (compound.contains(WAConstants.VARIENT)) {
            setAngelVarients(AngelTypes.VARIANTS_REGISTRY.get().getValue(new ResourceLocation(compound.getString(WAConstants.VARIENT))));
        }
    }

    @Override
    public CompoundTag save(CompoundTag compound) {
        super.save(compound);
        compound.putString("model", type);
        compound.putString("pose", pose.name());
        compound.putString(WAConstants.VARIENT, angelVariant.getRegistryName().toString());
        return compound;
    }

    public AngelEnums.AngelType getAngelType() {
        boolean found = false;
        for (AngelType value : AngelType.values()) {
            if (value.name().equals(type)) {
                found = true;
                break;
            }
        }
        if(!found){
            type = AngelType.DISASTER_MC.name();
        }

        return AngelEnums.AngelType.valueOf(type.isEmpty() ? AngelType.DISASTER_MC.name() : type);
    }

    public void setAngelType(String type) {
        this.type = type;
    }

    public void setAngelType(AngelEnums.AngelType type) {
        this.type = type.name();
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
    public AABB getRenderBoundingBox() {
        return super.getRenderBoundingBox().inflate(8, 8, 8);
    }

    @Override
    public void tick(Level p_155253_, BlockPos p_155254_, BlockState p_155255_, StatueBlockEntity p_155256_) {
        if (level.isClientSide) return;

        ServerLevel world = (ServerLevel) level;
        boolean isGraveYard = world.structureFeatureManager().getStructureAt(getBlockPos(), true, WAWorld.GRAVEYARD.get()).isValid();

        if (level.getGameTime() % 200 == 0 && isGraveYard && world.random.nextBoolean()) {
            Player playerentity = this.level.getNearestPlayer(this.getBlockPos().getX(), getBlockPos().getY(), getBlockPos().getZ(), 50.0D, false);
            if (playerentity != null) {
                if (ViewUtil.isInSightPos(playerentity, getBlockPos())) {
                    BlockState newState = getBlockState().setValue(ROTATION, Mth.floor((double) (playerentity.yHeadRot * 16.0F / 360.0F) + 0.5D) & 15);
                    level.setBlock(getBlockPos(), newState, 67);
                    changePose();
                }
            }
        }

        if (WAConfig.CONFIG.spawnFromBlocks.get() && level.getBestNeighborSignal(worldPosition) > 0 && level.getBlockEntity(worldPosition) instanceof StatueBlockEntity) {
            WeepingAngel angel = new WeepingAngel(level);
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
