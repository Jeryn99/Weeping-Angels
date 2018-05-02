package me.sub.angels.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Event;

public class EventAngelTeleport extends Event {
	
	EntityPlayer player;
	
	public EventAngelTeleport(EntityPlayer player) {
		this.player = player;
	}
	
	public EntityPlayer getPlayer() {
		return player;
	}
}
