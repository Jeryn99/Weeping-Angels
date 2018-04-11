package com.github.reallysub.angels.client;

import com.github.reallysub.angels.events.EventAngelTeleport;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientEvents {
	
	@SubscribeEvent
	public static void teleportEvent(EventAngelTeleport e) {
		EntityPlayer player = e.getPlayer();
		// Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleAngelAppearance(player.world, player.posX, player.posY, player.posZ));
	}
	
}
