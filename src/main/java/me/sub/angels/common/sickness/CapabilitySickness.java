package me.sub.angels.common.sickness;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class CapabilitySickness implements IAngelSickness {

    private boolean hasSickness;
    private int stage = 10;

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
        return null;
    }

    @Override
    public void readNBT(NBTTagCompound nbt) {

    }

    @Override
    public void updateClient() {

    }

    @Override
    public void update(EntityPlayer player) {

    }

}
