package me.suff.mc.angels.common.blockentity;

import me.suff.mc.angels.common.objects.WASounds;
import me.suff.mc.angels.common.objects.WATiles;
import me.suff.mc.angels.util.AngelUtils;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CoffinTile extends BlockEntity implements BlockEntityTicker, BlockEntityClientSerializable {

    private Coffin coffin = Coffin.HEAVILY_WEATHERED;
    private boolean isOpen, hasSkeleton = false;
    private float openAmount = 0.0F, alpha = 1;
    private boolean doingSomething = false;
    private int ticks, pulses;


    public CoffinTile(BlockPos pos, BlockState state) {
        super(WATiles.COFFIN_TILE, pos, state);
    }

    public Coffin getCoffin() {
        return coffin;
    }

    public void setCoffin(Coffin coffin) {
        this.coffin = coffin;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public float getOpenAmount() {
        return openAmount;
    }

    @Override
    public void tick(World world, BlockPos pos, BlockState state, BlockEntity blockEntity) {

        if (isOpen) {
            this.openAmount += 0.1F;
        } else {
            this.openAmount -= 0.1F;
        }

        if (this.openAmount > 1.0F) {
            this.openAmount = 1.0F;
        }

        if (this.openAmount < 0.0F) {
            this.openAmount = 0.0F;
        }

        if (doingSomething && coffin.name().contains("PTB")) {
            if (ticks % 60 < 30) {
                if (pulses <= 2)
                    this.alpha -= 0.01;
                else this.alpha -= 0.02;
            } else {
                this.alpha += 0.01;
            }

            if (ticks % 60 == 0)
                ++this.pulses;

            ++ticks;
            if (ticks >= 360) {
                if (!world.isClient) {
                    world.removeBlock(pos, false);

                    int i = 25;
                    while (i > 0) {
                        int j = ExperienceOrbEntity.roundToOrbSize(i);
                        i -= j;
                        this.world.spawnEntity(new ExperienceOrbEntity(this.world, pos.getX(), pos.getY(), pos.getZ(), j));
                    }
                }
            }
        }

        if (!world.isClient) {
            sendUpdates();
        }

    }


    @Override
    public void readNbt(CompoundTag nbt) {
        if (nbt.contains("coffin_type")) {
            coffin = getCorrectCoffin(nbt.getString("coffin_type"));
        }
        isOpen = nbt.getBoolean("isOpen");
        hasSkeleton = nbt.getBoolean("hasSkeleton");
        openAmount = nbt.getFloat("openAmount");
        alpha = nbt.getFloat("alpha");
        doingSomething = nbt.getBoolean("doingSomething");
        super.readNbt(nbt);
    }

    @Override
    public CompoundTag writeNbt(CompoundTag compound) {
        if (coffin != null) {
            compound.putString("coffin_type", coffin.name());
        } else {
            compound.putString("coffin_type", AngelUtils.randomCoffin().name());
        }
        compound.putBoolean("isOpen", isOpen);
        compound.putBoolean("hasSkeleton", hasSkeleton);
        compound.putBoolean("doingSomething", doingSomething);
        compound.putFloat("openAmount", openAmount);
        compound.putFloat("alpha", alpha);
        return super.writeNbt(compound);
    }

    public boolean isDoingSomething() {
        return doingSomething;
    }

    public void setDoingSomething(boolean doingSomething) {
        this.doingSomething = doingSomething;
        if (doingSomething) {
            world.playSound(null, pos, WASounds.TARDIS_TAKEOFF, SoundCategory.BLOCKS, 1, 1);
        }
        sendUpdates();

    }

    public float getAlpha() {
        return alpha;
    }

    public Coffin getCorrectCoffin(String coffin) {
        for (Coffin value : Coffin.values()) {
            if (value.name().equals(coffin)) {
                return value;
            }
        }
        return Coffin.HEAVILY_WEATHERED;
    }

    public void sendUpdates() {
        if (world != null && getCachedState() != null && getCachedState().getBlock() != null) {
            world.updateComparators(pos, getCachedState().getBlock());
            world.updateListeners(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
            markDirty();
            sync();
        }
    }

    @Override
    public void fromClientTag(CompoundTag compoundTag) {
        readNbt(compoundTag);
    }

    @Override
    public CompoundTag toClientTag(CompoundTag compoundTag) {
        return writeNbt(compoundTag);
    }

    public enum Coffin {
        NEW, WEATHERED, SLIGHTLY_WEATHERED, HEAVILY_WEATHERED, PTB, PTB_2
    }
}