package com.github.reallysub.angels.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Event;

public class EventTeleport extends Event {
	
	EntityPlayer player;
	
	public EventTeleport(EntityPlayer player) {
		this.player = player;
	}
}
