package me.suff.mc.angels.common.tileentities;

import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.compat.tardis.TardisMod;
import me.suff.mc.angels.utils.AngelUtil;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;

import static me.suff.mc.angels.utils.AngelUtil.RAND;

public class CoffinTile extends TileEntity implements ITickableTileEntity {

    private Coffin coffin = null;
    private boolean isOpen, hasSkeleton = false;
    private float openAmount = 0.0F, alpha = 1;
    private boolean doingSomething = false;
    private int ticks, pulses, knockTime;

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
        handleUpdateTag(getBlockState(), pkt.getTag());
    }

    @Override
    public void onLoad() {
        if (coffin == null) {
            coffin = AngelUtil.randomCoffin();
            setChanged();
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

        if (knockTime <= 0) {
            knockTime = RAND.nextInt(1800) + 10;
        }


        if (knockTime > 0 && level.getGameTime() % knockTime == 0 && !coffin.isPoliceBox() && !isOpen() && hasSkeleton()) {
            level.playSound(null, getBlockPos().getX(), getBlockPos().getY(), getBlockPos().getZ(), WAObjects.Sounds.KNOCK.get(), SoundCategory.BLOCKS, 1.0F * 16, 1.0F);
            knockTime = RAND.nextInt(1800);
        }

        if (doingSomething && coffin.isPoliceBox()) {
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
                if (!level.isClientSide) {
                    level.removeBlock(worldPosition, false);
                    if (ModList.get().isLoaded("tardis")) {
                        TardisMod.create(level.getServer(), worldPosition);
                        level.setBlockAndUpdate(worldPosition.above(), ForgeRegistries.BLOCKS.getValue(new ResourceLocation("tardis", "broken_exterior")).defaultBlockState());
                    }

                    int i = 25;
                    while (i > 0) {
                        int j = ExperienceOrbEntity.getExperienceValue(i);
                        i -= j;
                        this.level.addFreshEntity(new ExperienceOrbEntity(this.level, worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), j));
                    }
                }
            }
        }

    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(worldPosition, 3, getUpdateTag());
    }


    @Override
    public CompoundNBT getUpdateTag() {
        return save(new CompoundNBT());
    }

    public void sendUpdates() {
        if (level != null && getBlockState() != null && getBlockState().getBlock() != null) {
            level.updateNeighbourForOutputSignal(worldPosition, getBlockState().getBlock());
            level.sendBlockUpdated(worldPosition, level.getBlockState(worldPosition), level.getBlockState(worldPosition), 3);
        }
        setChanged();
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        coffin = getCorrectCoffin(nbt.getString("coffin_type"));
        isOpen = nbt.getBoolean("isOpen");
        hasSkeleton = nbt.getBoolean("hasSkeleton");
        openAmount = nbt.getFloat("openAmount");
        alpha = nbt.getFloat("alpha");
        doingSomething = nbt.getBoolean("doingSomething");
        super.load(state, nbt);
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        if (coffin == null) {
            coffin = AngelUtil.randomCoffin();
        }
        compound.putString("coffin_type", coffin.name());
        compound.putBoolean("isOpen", isOpen);
        compound.putBoolean("hasSkeleton", hasSkeleton);
        compound.putBoolean("doingSomething", doingSomething);
        compound.putFloat("openAmount", openAmount);
        compound.putFloat("alpha", alpha);
        return super.save(compound);
    }

    public boolean isDoingSomething() {
        return doingSomething;
    }

    public void setDoingSomething(boolean doingSomething) {
        this.doingSomething = doingSomething;
        if (doingSomething) {
            level.playSound(null, worldPosition, WAObjects.Sounds.TARDIS_TAKEOFF.get(), SoundCategory.BLOCKS, 1, 1);
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
        NEW, WEATHERED, SLIGHTLY_WEATHERED, HEAVILY_WEATHERED, PTB(true), PTB_2(true);

        private final boolean isPoliceBox;

        Coffin() {
            this.isPoliceBox = false;
        }

        Coffin(boolean isPoliceBox) {
            this.isPoliceBox = isPoliceBox;
        }

        public boolean isPoliceBox() {
            return isPoliceBox;
        }
    }
}
