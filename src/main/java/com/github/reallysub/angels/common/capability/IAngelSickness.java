package com.github.reallysub.angels.common.capability;

import net.minecraft.nbt.NBTTagCompound;

public interface IAngelSickness {
	
	NBTTagCompound writeNBT();
	
	void readNBT(NBTTagCompound nbt);
	
	void sync();
	
	boolean isSick();
	
	int getViewingTicks();
	
	void setViewingTicks(int ticks);
}
