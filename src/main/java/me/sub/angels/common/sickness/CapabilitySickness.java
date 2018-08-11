package me.sub.angels.common.sickness;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilitySickness implements IAngelSickness {

    @CapabilityInject(IAngelSickness.class)
    public static final Capability<IAngelSickness> CAPABILITY = null;
    private final EntityPlayer entityPlayer;

    private boolean hasSickness;
    private int stage = 10;

    public CapabilitySickness(EntityPlayer player) {
        this.entityPlayer = player;
    }

    @Override
    public boolean hasSickNess() {
        return hasSickness;
    }

    @Override
    public void setSickness(boolean sick) {
        hasSickness = sick;
    }

    @Override
    public int getStage() {
        return stage;
    }

    @Override
    public void setStage(int stageNumber) {
        stage = stageNumber;
    }

    @Override
    public NBTTagCompound writeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setBoolean("hasSickness", hasSickNess());
        nbt.setInteger("stage", getStage());
        return nbt;
    }

    @Override
    public void readNBT(NBTTagCompound nbt) {
        setSickness(nbt.getBoolean("hasSickness"));
        setStage(nbt.getInteger("stage"));
    }

    @Override
    public void updateClient() {

    }

    @Override
    public void update(EntityPlayer player) {

    }


    public static class SicknessStorage implements Capability.IStorage<IAngelSickness> {
        @Nullable
        @Override
        public NBTBase writeNBT(Capability<IAngelSickness> capability, IAngelSickness instance, EnumFacing side) {
            return instance.writeNBT();
        }

        @Override
        public void readNBT(Capability<IAngelSickness> capability, IAngelSickness instance, EnumFacing side, NBTBase nbt) {
            instance.readNBT((NBTTagCompound) nbt);
        }
    }


    public static class SicknessProvider implements ICapabilitySerializable<NBTTagCompound> {

        private IAngelSickness capability;

        public SicknessProvider(IAngelSickness capability) {
            this.capability = capability;
        }

        @Override
        public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
            return CapabilitySickness.CAPABILITY != null && capability == CapabilitySickness.CAPABILITY;
        }

        @Nullable
        @Override
        public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
            return capability == CapabilitySickness.CAPABILITY ? CapabilitySickness.CAPABILITY.cast(this.capability) : null;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            return (NBTTagCompound) CapabilitySickness.CAPABILITY.getStorage().writeNBT(CapabilitySickness.CAPABILITY, this.capability, null);
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            CapabilitySickness.CAPABILITY.getStorage().readNBT(CapabilitySickness.CAPABILITY, this.capability, null, nbt);
        }
    }

}
