package com.github.reallysub.angels.common.capability;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.github.reallysub.angels.common.network.MessageSicknessUpdate;
import com.github.reallysub.angels.main.WeepingAngels;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class CapabilityAngelSickness implements IAngelSickness {
	
	@CapabilityInject(IAngelSickness.class)
	public static final Capability<IAngelSickness> CAP = null;
	
	private boolean isSick;
	private int viewingTicks;
	
	public EntityPlayer player;
	
	public CapabilityAngelSickness(EntityPlayer player) {
		this.player = player;
	}
	
	@Override
	public NBTTagCompound writeNBT() {
		NBTTagCompound compound = new NBTTagCompound();
		compound.setBoolean("isSick", isSick);
		compound.setInteger("viewingTicks", viewingTicks);
		return compound;
	}
	
	@Override
	public void readNBT(NBTTagCompound nbt) {
		viewingTicks = nbt.getInteger("viewingTicks");
		isSick = nbt.getBoolean("isSick");
	}
	
	@Override
	public void sync() {
		WeepingAngels.NETWORK.sendToAll(new MessageSicknessUpdate(this.player));
	}
	
	@Override
	public boolean isSick() {
		return isSick;
	}
	
	@Override
	public int getViewingTicks() {
		return viewingTicks;
	}
	
	@Override
	public void setViewingTicks(int ticks) {
		viewingTicks = ticks;
	}
	
	public static class CapabilityAngelSicknessProvider implements ICapabilitySerializable<NBTTagCompound> {
		
		private IAngelSickness capability;
		
		public CapabilityAngelSicknessProvider(IAngelSickness capability) {
			this.capability = capability;
		}
		
		@Override
		public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
			return CAP != null && capability == CAP;
		}
		
		@Nullable
		@Override
		public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
			return (T) (capability == CAP ? CAP.cast(this.capability) : null);
		}
		
		@Override
		public NBTTagCompound serializeNBT() {
			return (NBTTagCompound) CAP.getStorage().writeNBT(CAP, this.capability, null);
		}
		
		@Override
		public void deserializeNBT(NBTTagCompound nbt) {
			CAP.getStorage().readNBT(CAP, this.capability, null, nbt);
		}
	}
	
	public static class Storage implements Capability.IStorage<IAngelSickness> {
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
	
}
