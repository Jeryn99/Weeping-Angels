package com.github.reallysub.angels.client;

import com.github.reallysub.angels.main.config.Config;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientEvents {
	
	@SubscribeEvent
	public static void join(EntityJoinWorldEvent event) {
		if (Config.analytics && event.getEntity() instanceof EntityPlayer) {
			for (ModContainer mods : Loader.instance().getActiveModList()) {
				System.out.println(mods.getModId());
				AnalyticsTracking.sendData((EntityPlayer) event.getEntity(), "mods", mods.getModId());
			}
		}
	}
	
}
