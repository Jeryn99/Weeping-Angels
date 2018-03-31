package com.github.reallysub.angels.client;

import com.github.reallysub.angels.WeepingAngels;
import com.github.reallysub.angels.common.entities.EntityAngel;
import com.github.reallysub.angels.common.packets.MessageAngelSeen;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientEvents {
	
	@SubscribeEvent
	public void renderAngels(RenderLivingEvent.Post<EntityAngel> e) {
		EntityLivingBase entity = e.getEntity();
		if (entity instanceof EntityAngel && !Minecraft.getMinecraft().player.isPotionActive(MobEffects.BLINDNESS)) {
			EntityAngel angel = (EntityAngel) entity;
			if (!angel.isSeen()) WeepingAngels.INSTANCE.sendToServer(new MessageAngelSeen(angel.getEntityId()));
		}
	}
	
}
