package me.swirtzly.minecraft.angels.common.tileentities;

import me.swirtzly.minecraft.angels.common.WAObjects;
import me.swirtzly.minecraft.angels.utils.AngelUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;

public class CoffinTile extends TileEntity implements ITickableTileEntity {

    private Coffin coffin = null;
    private boolean isOpen, hasSkeleton = false;
    private float openAmount = 0.0F, alpha = 1;
    private boolean doingSomething = false;
    private int ticks, pulses;

    public CoffinTile() {
        super(WAObjects.Tiles.COFFIN.get());
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

    public void setHasSkeleton(boolean hasSkeleton) {
        this.hasSkeleton = hasSkeleton;
    }

    public boolean hasSkeleton() {
        return hasSkeleton;
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        super.onDataPacket(net, pkt);
        handleUpdateTag(getBlockState(), pkt.getNbtCompound());
    }

    @Override
    public void onLoad() {
        if (coffin == null) {
            coffin = AngelUtils.randomCoffin();
            markDirty();
        }
    }

    @Override
    public void tick() {
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

        if (doingSomething && coffin == Coffin.PTB) {
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
                if (!world.isRemote) {
                    world.removeBlock(pos, false);

                    if (ModList.get().isLoaded("tardis")) {
                        world.setBlockState(pos.up(), ForgeRegistries.BLOCKS.getValue(new ResourceLocation("tardis", "broken_exterior")).getDefaultState());
                    }

                    int i = 25;
                    while (i > 0) {
                        int j = ExperienceOrbEntity.getXPSplit(i);
                        i -= j;
                        this.world.addEntity(new ExperienceOrbEntity(this.world, pos.getX(), pos.getY(), pos.getZ(), j));
                    }
                }
            }
        }

    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(pos, 3, getUpdateTag());
    }


    @Override
    public CompoundNBT getUpdateTag() {
        return write(new CompoundNBT());
    }

    public void sendUpdates() {
        if (world != null && getBlockState() != null && getBlockState().getBlock() != null) {
            world.updateComparatorOutputLevel(pos, getBlockState().getBlock());
            world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
        }
        markDirty();
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        coffin = getCorrectCoffin(nbt.getString("coffin_type"));
        isOpen = nbt.getBoolean("isOpen");
        hasSkeleton = nbt.getBoolean("hasSkeleton");
        openAmount = nbt.getFloat("openAmount");
        alpha = nbt.getFloat("alpha");
        doingSomething = nbt.getBoolean("doingSomething");
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        if (coffin == null) {
            coffin = AngelUtils.randomCoffin();
        }
        compound.putString("coffin_type", coffin.name());
        compound.putBoolean("isOpen", isOpen);
        compound.putBoolean("hasSkeleton", hasSkeleton);
        compound.putBoolean("doingSomething", doingSomething);
        compound.putFloat("openAmount", openAmount);
        compound.putFloat("alpha", alpha);
        return super.write(compound);
    }

    public boolean isDoingSomething() {
        return doingSomething;
    }

    public void setDoingSomething(boolean doingSomething) {
        this.doingSomething = doingSomething;
        if (doingSomething) {
            world.playSound(null, pos, WAObjects.Sounds.TARDIS_TAKEOFF.get(), SoundCategory.BLOCKS, 1, 1);
        }
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

    public enum Coffin {
        NEW, WEATHERED, SLIGHTLY_WEATHERED, HEAVILY_WEATHERED, PTB, PTB_2
    }
}
