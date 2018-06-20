package me.sub.angels.common.events;

import me.sub.angels.common.WAObjects;
import me.sub.angels.common.entities.EntityAngel;
import me.sub.angels.main.config.WAConfig;
import me.sub.angels.utils.AngelUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

@Mod.EventBusSubscriber
public class CommonEvents {
	
	/**
	 * Update checker thing, tells the player that the mods out of date if they're on a old build
	 */
	@SubscribeEvent
	public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent e) {
		EntityPlayer player = e.player;
		if (!player.world.isRemote) {
			ForgeVersion.CheckResult version = ForgeVersion.getResult(Loader.instance().activeModContainer());
			if (version.status == ForgeVersion.Status.OUTDATED) {
				TextComponentString url = new TextComponentString(TextFormatting.AQUA + TextFormatting.BOLD.toString() + "UPDATE");
				url.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://minecraft.curseforge.com/projects/weeping-angels-mod"));
				url.getStyle().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString("Open URL")));
				
				player.sendMessage(new TextComponentString(TextFormatting.GOLD + "[Weeping Angels] : ").appendSibling(url));
				String changes = String.valueOf(version.changes).replace("{" + version.target + "=", "").replace("}", "");
				player.sendMessage(new TextComponentString(TextFormatting.GOLD + "Changes: " + TextFormatting.BLUE + changes));
			}
		}
	}
	
	@SubscribeEvent
	public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
		// Angel
		if (event.getEntity() instanceof EntityAngel) {
			EntityAngel angel = (EntityAngel) event.getEntity();
			if (WAConfig.angels.angelLocking) {
				AngelUtils.getAllAngels(angel);
			}
		}
	}
	
	/*
	 * Spawning arms in snow biomes
	 */
	@SubscribeEvent
	public static void decorateBiomeEvent(DecorateBiomeEvent e) {
		if (e.getWorld().getBiome(e.getPos()).isSnowyBiome()) {
			if (e.getRand().nextInt(30) <= 10) {
				generate(e.getWorld(), e.getPos());
			}
		}
	}


	public static boolean generate(World world, BlockPos position) {
		BlockPos pos = new BlockPos(position.add(new BlockPos(8, 0, 8)));

		if ((!world.provider.isNether() || pos.getY() < 255) && world.getBiome(position).isSnowyBiome()) {
			if (world.getBlockState(pos).getBlock() == Blocks.SNOW || world.getBlockState(pos).getBlock() == Blocks.SNOW_LAYER)
				world.setBlockState(pos, WAObjects.WABlocks.ARM.getDefaultState(), 1);
		}
		return true;
	}
}
