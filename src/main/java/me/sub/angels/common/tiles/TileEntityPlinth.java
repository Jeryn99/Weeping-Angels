package me.sub.angels.common.tiles;

import me.sub.angels.common.entities.EntityAngel;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

import javax.annotation.Nullable;

public class TileEntityPlinth extends TileEntity implements ITickable {

    private boolean hasSpawned = false;

    public boolean getHasSpawned() {
        return hasSpawned;
    }

    public void setHasSpawned(boolean hasSpawned) {
        this.hasSpawned = hasSpawned;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        setHasSpawned(compound.getBoolean("hasSpawned"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setBoolean("hasSpawned", hasSpawned);
        return compound;
    }

    @Override
    @Nullable
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, 3, this.getUpdateTag());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        handleUpdateTag(pkt.getNbtCompound());
    }


    private void sendUpdates() {
        world.markBlockRangeForRenderUpdate(pos, pos);
        world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
        world.scheduleBlockUpdate(pos, this.getBlockType(), 0, 0);
        markDirty();
    }

    @Override
    public void update() {
        if (world.isRemote) return;

        if (world.isBlockIndirectlyGettingPowered(pos) > 0 && world.getTileEntity(pos) instanceof TileEntityPlinth) {
            TileEntityPlinth plinth = (TileEntityPlinth) world.getTileEntity(pos);
            if (!plinth.getHasSpawned()) {
                EntityAngel angel = new EntityAngel(world);
                angel.setType(1);
                angel.setChild(false);
                angel.setLocationAndAngles(pos.getX(), pos.getY() + 1, pos.getZ(), 0, 0);
                world.spawnEntity(angel);
                plinth.setHasSpawned(true);
                sendUpdates();
            }

        }
    }
}
