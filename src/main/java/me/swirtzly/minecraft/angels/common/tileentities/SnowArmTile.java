package me.swirtzly.minecraft.angels.common.tileentities;

import me.swirtzly.minecraft.angels.common.WAObjects;
import me.swirtzly.minecraft.angels.common.entities.AngelEnums;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import me.swirtzly.minecraft.angels.common.misc.WAConstants;
import me.swirtzly.minecraft.angels.utils.AngelUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static net.minecraft.block.SnowBlock.LAYERS;

public class SnowArmTile extends TileEntity implements ITickableTileEntity {

    private final AxisAlignedBB AABB = new AxisAlignedBB(0.2, 0, 0, 0.8, 2, 0.1);
    private SnowAngelStages snowAngelStages = SnowAngelStages.ARM;
    private WeepingAngelEntity.AngelVarients angelVarients = WeepingAngelEntity.AngelVarients.NORMAL;

    public void setSnowAngelStage(SnowAngelStages snowAngelStages) {
        this.snowAngelStages = snowAngelStages;
    }

    public SnowAngelStages getSnowAngelStage() {
        return snowAngelStages;
    }

    public SnowArmTile() {
        super(WAObjects.Tiles.ARM.get());
    }

    public void setAngelVarients(WeepingAngelEntity.AngelVarients angelVarients) {
        this.angelVarients = angelVarients;
    }

    public WeepingAngelEntity.AngelVarients getAngelVarients() {
        return angelVarients;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        if (nbt.contains(WAConstants.VARIENT)) {
            setAngelVarients(WeepingAngelEntity.AngelVarients.valueOf(nbt.getString(WAConstants.VARIENT)));
        }

        if (nbt.contains(WAConstants.SNOW_STAGE)) {
            setSnowAngelStage(SnowAngelStages.valueOf(nbt.getString(WAConstants.SNOW_STAGE)));
        }

        rotation = nbt.getInt("rotation");

    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putString(WAConstants.SNOW_STAGE, snowAngelStages.name());
        compound.putString(WAConstants.VARIENT, angelVarients.name());
        compound.putInt("rotation", rotation);
        return super.write(compound);
    }


    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(pos, 3, getUpdateTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return write(new CompoundNBT());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        super.onDataPacket(net, pkt);
        handleUpdateTag(getBlockState(), pkt.getNbtCompound());
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        this.read(state, tag);
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return super.getRenderBoundingBox().grow(8, 8, 8);
    }

    public void sendUpdates() {
        world.updateComparatorOutputLevel(pos, getBlockState().getBlock());
        world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
        markDirty();
    }


    @Override
    public void tick() {
        if (!world.getEntitiesWithinAABB(PlayerEntity.class, AABB.offset(getPos())).isEmpty() && !world.isRemote) {
            WeepingAngelEntity angel = new WeepingAngelEntity(world);
            angel.setType(AngelEnums.AngelType.ANGELA_MC);
            angel.setVarient(WeepingAngelEntity.AngelVarients.NORMAL);
            BlockPos newPos = getPos();
            angel.setPosition(newPos.getX() + 0.5D, newPos.getY(), newPos.getZ() + 0.5D);
            world.addEntity(angel);
            Integer layers = world.getBlockState(pos).get(LAYERS);
            world.setBlockState(pos, Blocks.SNOW.getDefaultState().with(LAYERS, layers));
        }

        if(angelVarients.isHeadless() && snowAngelStages == SnowAngelStages.HEAD){
            setSnowAngelStage(AngelUtils.randowSnowStage());
        }
    }

    private int rotation = 0;

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
        sendUpdates();
    }


    public enum SnowAngelStages {
        ARM, HEAD, BODY
    }



}
