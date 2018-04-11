package com.github.reallysub.angels.common.events;

import com.github.reallysub.angels.common.WAObjects;
import com.github.reallysub.angels.common.WorldGenArms;
import com.github.reallysub.angels.main.AngelUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

@Mod.EventBusSubscriber
public class CommonEvents {
	
	/*
	 * Update checker thing, tells the player that the mods out of date if they're on a old build
	 */
	@SubscribeEvent
	public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent e) {
		EntityPlayer player = e.player;
		if (!player.world.isRemote) {
			ForgeVersion.CheckResult version = ForgeVersion.getResult(Loader.instance().activeModContainer());
			if (version.status == ForgeVersion.Status.OUTDATED) {
				player.sendMessage(new TextComponentString(TextFormatting.GOLD + "You are running an outdated build of Weeping Angels! :("));
				TextComponentString url = new TextComponentString(TextFormatting.GOLD + "Click me to download the updated version at curse.com");
				url.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://minecraft.curseforge.com/projects/weeping-angels-mod"));
				url.getStyle().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString("Open URL")));
				player.sendMessage(url);
			}
		}
	}
	
	/*
	 * Checking for the angels
	 */
	@SubscribeEvent
	public void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
		
		// Player
		if (event.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntity();
			AngelUtils.getAllAngels(player, 40, 40);
		}
	}
	
	/*
	 * Spawning arms in snow biomes
	 */
	@SubscribeEvent
	public static void decorBiomeEvent(DecorateBiomeEvent e) {
		if (e.getWorld().getBiome(e.getPos()).isSnowyBiome()) {
			WorldGenArms arms = new WorldGenArms(WAObjects.WABlocks.angelArm);
			
			if (e.getRand().nextInt(30) <= 10) {
				arms.generate(e.getWorld(), e.getRand(), e.getPos());
			}
		}
	}
	
}
