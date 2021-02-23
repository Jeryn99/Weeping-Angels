package me.suff.mc.angels.common.blockentity;

import me.suff.mc.angels.common.entity.WeepingAngelEntity;
import me.suff.mc.angels.common.objects.WATiles;
import me.suff.mc.angels.enums.WeepingAngelPose;
import me.suff.mc.angels.enums.WeepingAngelVariants;
import me.suff.mc.angels.util.Constants;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/* Created by Craig on 19/02/2021 */
public class PlinthTile extends BlockEntity implements BlockEntityTicker, IPlaceableStatue {

    private WeepingAngelPose weepingAngelPose = WeepingAngelPose.APPROACH;
    private WeepingAngelVariants weepingAngelVariants = WeepingAngelVariants.NORMAL;

    public PlinthTile(BlockPos pos, BlockState state) {
        super(WATiles.PLINTH_TILE, pos, state);
    }

    @Nullable
    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        CompoundTag compoundTag = this.writeNbt(new CompoundTag());
        return new BlockEntityUpdateS2CPacket(this.pos, 2, compoundTag);
    }

    @Override
    public CompoundTag writeNbt(CompoundTag tag) {
        setAngelVariant(WeepingAngelVariants.getVariant(tag.getString(Constants.VARIANT)));
        setAngelPose(WeepingAngelPose.getPose(tag.getString(Constants.CURRENT_POSE)));
        return super.writeNbt(tag);
    }

    @Override
    public void readNbt(CompoundTag nbt) {
        nbt.putString(Constants.CURRENT_POSE, getAngelPose().name());
        nbt.putString(Constants.VARIANT, getAngelVariant().name());
        super.readNbt(nbt);
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
    public void tick(World world, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        if (world != null && world.isReceivingRedstonePower(getPos()) && !world.isClient()) {
            WeepingAngelEntity weepingAngelEntity = new WeepingAngelEntity(world);
            weepingAngelEntity.setPose(getAngelPose());
            weepingAngelEntity.setVarient(getAngelVariant());
            weepingAngelEntity.setPos(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
            world.spawnEntity(weepingAngelEntity);
            world.removeBlock(getPos(), false);
        }
    }
}
