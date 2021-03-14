package me.suff.mc.angels.common.tileentities;

import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.utils.AngelUtils;
import me.suff.mc.angels.common.entities.AngelEnums;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import me.suff.mc.angels.common.misc.WAConstants;
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

import static net.minecraft.block.SnowBlock.LAYERS;


public class SnowArmTile extends TileEntity implements ITickableTileEntity {

    private final AxisAlignedBB AABB = new AxisAlignedBB(0.2, 0, 0, 0.8, 2, 0.1);
    private SnowAngelStages snowAngelStages = SnowAngelStages.ARM;
    private WeepingAngelEntity.AngelVariants angelVariants = WeepingAngelEntity.AngelVariants.NORMAL;
    private boolean hasSetup = false;
    private int rotation = 0;

    public SnowArmTile() {
        super(WAObjects.Tiles.SNOW_ANGEL.get());
    }

    public SnowAngelStages getSnowAngelStage() {
        return snowAngelStages;
    }

    public void setSnowAngelStage(SnowAngelStages snowAngelStages) {
        this.snowAngelStages = snowAngelStages;
    }

    public WeepingAngelEntity.AngelVariants getAngelVariants() {
        return angelVariants;
    }

    public void setAngelVariants(WeepingAngelEntity.AngelVariants angelVariants) {
        this.angelVariants = angelVariants;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);

        if (nbt.contains(WAConstants.VARIENT)) {
            setAngelVariants(WeepingAngelEntity.AngelVariants.valueOf(nbt.getString(WAConstants.VARIENT)));
        }

        if (nbt.contains(WAConstants.SNOW_STAGE)) {
            setSnowAngelStage(SnowAngelStages.valueOf(nbt.getString(WAConstants.SNOW_STAGE)));
        }

        rotation = nbt.getInt("rotation");
        hasSetup = nbt.getBoolean("setup");

    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putString(WAConstants.SNOW_STAGE, snowAngelStages.name());
        compound.putString(WAConstants.VARIENT, angelVariants.name());
        compound.putInt("rotation", rotation);
        compound.putBoolean("setup", hasSetup);
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

    public boolean isHasSetup() {
        return hasSetup;
    }

    public void setHasSetup(boolean hasSetup) {
        this.hasSetup = hasSetup;
    }

    @Override
    public void tick() {
        if (world != null && !world.getEntitiesWithinAABB(PlayerEntity.class, AABB.offset(getPos())).isEmpty() && !world.isRemote) {
            WeepingAngelEntity angel = new WeepingAngelEntity(world);
            angel.setType(AngelEnums.AngelType.ANGELA_MC);
            angel.setVarient(angelVariants);
            BlockPos newPos = getPos();
            angel.setPosition(newPos.getX() + 0.5D, newPos.getY(), newPos.getZ() + 0.5D);
            world.addEntity(angel);
            Integer layers = world.getBlockState(pos).get(LAYERS);
            world.setBlockState(pos, Blocks.SNOW.getDefaultState().with(LAYERS, layers));
        }

        if (angelVariants.isHeadless() && snowAngelStages == SnowAngelStages.HEAD || !hasSetup) {
            setSnowAngelStage(AngelUtils.randowSnowStage());
            hasSetup = true;
            sendUpdates();
        }

        //Randomness for world generatiopn
        if (!hasSetup) {
            setRotation(world.rand.nextInt(360));
            setSnowAngelStage(AngelUtils.randowSnowStage());
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
