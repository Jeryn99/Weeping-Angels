package me.sub.angels.common.tileentities;

import me.sub.angels.client.models.poses.PoseManager;
import me.sub.angels.common.entities.EntityWeepingAngel;
import me.sub.angels.common.misc.AngelEnums;
import net.fabricmc.fabric.block.entity.ClientSerializable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BoundingBox;
import net.minecraft.world.World;

public class TileEntityPlinth extends BlockEntity implements Tickable, ClientSerializable
{

    BoundingBox BB = new BoundingBox(45, 45, 45, 45, 45, 45);
    private boolean hasSpawned = false;
    private int rotation;
    private String pose = PoseManager.AngelPoses.HIDING_FACE.toString();

    public TileEntityPlinth(BlockEntityType<?> blockEntityType_1)
    {
        super(blockEntityType_1);
    }

    public boolean getHasSpawned() {
        return hasSpawned;
    }

    public void setHasSpawned(boolean hasSpawned) {
        this.hasSpawned = hasSpawned;
    }

    @Override public void fromTag(CompoundTag compound)
    {
        super.fromTag(compound);
        setHasSpawned(compound.getBoolean("hasSpawned"));
        setPose(compound.getString("pose"));
        this.rotation = compound.getInt("rotation");
    }

    @Override public CompoundTag toTag(CompoundTag compound)
    {
        super.toTag(compound);
        compound.putBoolean("hasSpawned", hasSpawned);
        compound.putInt("rotation", rotation);
        compound.putString("pose", pose);
        return compound;
    }

    public int getRotation() {
        return this.rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
        sendUpdates();
    }

//    @SideOnly(Side.CLIENT)
//    public AxisAlignedBB getRenderBoundingBox() {
//        return super.getRenderBoundingBox().grow(8, 8, 8);
//    }

    public void sendUpdates() {
//        world.markBlockRangeForRenderUpdate(pos, pos);
//        world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
//        world.scheduleBlockUpdate(pos, this.getBlockType(), 0, 0);
        markDirty();
    }

    public PoseManager.AngelPoses getRandomPose(World world) {
        return PoseManager.AngelPoses.values()[world.random.nextInt(PoseManager.AngelPoses.values().length)];
    }

    public String getPose() {
        return pose;
    }

    public void setPose(String pose) {
        this.pose = pose;
    }

    @Override public void fromClientTag(CompoundTag tag)
    {
        this.fromTag(tag);
    }

    @Override public CompoundTag toClientTag(CompoundTag tag)
    {
       return this.toTag(tag);
    }

    @Override public void tick()
    {
        if (world.isClient) return;

        if (world.getReceivedRedstonePower(pos) > 0 && world.getBlockEntity(pos) instanceof TileEntityPlinth) {
            TileEntityPlinth plinth = (TileEntityPlinth) world.getBlockEntity(pos);
            if (!plinth.getHasSpawned()) {
                EntityWeepingAngel angel = new EntityWeepingAngel(world);
                angel.setAngelType(AngelEnums.AngelType.ANGEL_ONE.getId());
                angel.setChild(false);
                angel.setPositionAndAngles(pos.getX(), pos.getY() + 1, pos.getZ(), 0, 0);
                angel.setPose(getPose());
                world.spawnEntity(angel);
                plinth.setHasSpawned(true);
                sendUpdates();
            }
        }
    }
}
