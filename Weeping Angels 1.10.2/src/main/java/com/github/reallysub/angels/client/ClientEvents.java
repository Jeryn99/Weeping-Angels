package com.github.reallysub.angels.client;

import com.github.reallysub.angels.client.particles.ParticleAngelAppearance;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientEvents {
	
	@SubscribeEvent
	public static void teleportEvent(EventTeleport e) {
		Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleAngelAppearance(e.player.worldObj, e.player.posX, e.player.posY, e.player.posZ));
	}
}
