package me.sub.angels.common.sickness;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public interface IAngelSickness {

    boolean hasSickNess();

    void setSickness(boolean sick);

    int getStage();

    void setStage(int stageNumber);

    NBTTagCompound writeNBT();

    void readNBT(NBTTagCompound nbt);

    void updateClient();

    void update(EntityPlayer player);
}
