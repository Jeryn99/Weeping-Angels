package me.suff.mc.angels.common.blockentity;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.entity.WeepingAngelEntity;
import me.suff.mc.angels.common.objects.WATiles;
import me.suff.mc.angels.enums.WeepingAngelPose;
import me.suff.mc.angels.enums.WeepingAngelVariants;
import me.suff.mc.angels.util.Constants;
import net.minecraft.block.BlockState;
import net.minecraft.block.SpawnerBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Tickable;
import org.jetbrains.annotations.Nullable;

/* Created by Craig on 19/02/2021 */
public class PlinthTile extends BlockEntity implements Tickable, IPlaceableStatue {

    private WeepingAngelPose weepingAngelPose = WeepingAngelPose.APPROACH;
    private WeepingAngelVariants weepingAngelVariants = WeepingAngelVariants.NORMAL;

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
        return super.toTag(tag);
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        tag.putString(Constants.CURRENT_POSE, getAngelPose().name());
        tag.putString(Constants.VARIANT, getAngelVariant().name());
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
        if (world != null && world.isReceivingRedstonePower(getPos()) && !world.isClient()) {
            ServerWorld serverWorld = (ServerWorld) world;
            WeepingAngelEntity weepingAngelEntity = WeepingAngels.WEEPING_ANGEL.create(serverWorld);
            weepingAngelEntity.setPose(getAngelPose());
            weepingAngelEntity.setVarient(getAngelVariant());
            weepingAngelEntity.setPos(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
            serverWorld.spawnEntityAndPassengers(weepingAngelEntity);
            serverWorld.removeBlock(getPos(), false);
        }
    }
}
