package me.suff.mc.angels.common.blockentity;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.block.PlinthBlock;
import me.suff.mc.angels.common.block.StatueBlock;
import me.suff.mc.angels.common.entity.WeepingAngelEntity;
import me.suff.mc.angels.common.objects.WATiles;
import me.suff.mc.angels.enums.WeepingAngelPose;
import me.suff.mc.angels.enums.WeepingAngelVariants;
import me.suff.mc.angels.util.Constants;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SpawnerBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Tickable;
import org.jetbrains.annotations.Nullable;

/* Created by Craig on 19/02/2021 */
public class PlinthTile extends BlockEntity implements Tickable, IPlaceableStatue {

    private WeepingAngelPose weepingAngelPose = WeepingAngelPose.APPROACH;
    private WeepingAngelVariants weepingAngelVariants = WeepingAngelVariants.NORMAL;
    private boolean isSpawned = false;

    public void setSpawned(boolean spawned) {
        isSpawned = spawned;
    }

    public boolean isSpawned() {
        return isSpawned;
    }

    public PlinthTile() {
        super(WATiles.PLINTH_TILE);
    }

    @Nullable
    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        CompoundTag compoundTag = this.toTag(new CompoundTag());
        return new BlockEntityUpdateS2CPacket(this.pos, 2, compoundTag);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        setAngelVariant(WeepingAngelVariants.getVariant(tag.getString(Constants.VARIANT)));
        setAngelPose(WeepingAngelPose.getPose(tag.getString(Constants.CURRENT_POSE)));
        setSpawned((tag.getBoolean(Constants.SPAWNED)));
        return super.toTag(tag);
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        tag.putString(Constants.CURRENT_POSE, getAngelPose().name());
        tag.putString(Constants.VARIANT, getAngelVariant().name());
        tag.putBoolean(Constants.SPAWNED, isSpawned);
        super.fromTag(state, tag);
    }

    @Override
    public WeepingAngelVariants getAngelVariant() {
        return weepingAngelVariants;
    }

    @Override
    public void setAngelVariant(WeepingAngelVariants weepingAngelVariants) {
        this.weepingAngelVariants = weepingAngelVariants;
    }

    @Override
    public WeepingAngelPose getAngelPose() {
        return weepingAngelPose;
    }

    @Override
    public void setAngelPose(WeepingAngelPose weepingAngelPose) {
        this.weepingAngelPose = weepingAngelPose;
    }

    @Override
    public void tick() {
        if(!isSpawned()) {
            if (world != null && world.isReceivingRedstonePower(getPos()) && !world.isClient()) {
                ServerWorld serverWorld = (ServerWorld) world;
                WeepingAngelEntity weepingAngelEntity = WeepingAngels.WEEPING_ANGEL.spawn(serverWorld, null, null, null, pos.up(), SpawnReason.EVENT, false, false);
                weepingAngelEntity.setPose(getAngelPose());
                weepingAngelEntity.setVarient(getAngelVariant());
                BlockState blockState = world.getBlockState(pos);
                weepingAngelEntity.bodyYaw = 22.5F * blockState.get(PlinthBlock.ROTATION);
                setSpawned(true);
                markDirty();
            }
        }
    }
}
