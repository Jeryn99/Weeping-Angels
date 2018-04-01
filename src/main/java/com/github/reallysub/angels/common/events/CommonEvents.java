package com.github.reallysub.angels.common.events;

import com.github.reallysub.angels.main.config.Utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CommonEvents {
	
	@SubscribeEvent
	public void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
		if (event.getEntity() instanceof EntityPlayer) {
			Utils.getAllAngels(event.getEntityLiving(), 40, 40);
		}
	}
	
}
