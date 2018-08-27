package me.sub.angels.common.events;

import me.sub.angels.WeepingAngels;
import me.sub.angels.common.WAObjects;
import me.sub.angels.common.entities.EntityWeepingAngel;
import me.sub.angels.config.WAConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.util.Random;


@Mod.EventBusSubscriber(modid = WeepingAngels.MODID)
public class EventHandler {

	private static WorldGenMinable genCrystal = new WorldGenMinable(WAObjects.Blocks.KONTRON_ORE.getDefaultState(), 3);


	/**
	 * Update checker thing, tells the player that the mods out of date if they're on a old build
	 */
	@SubscribeEvent
	public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent e) {
		EntityPlayer player = e.player;
		if (!player.world.isRemote && WAConfig.angels.enableUpdateChecker) {
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
	public static void decorateBiomeEvent(DecorateBiomeEvent.Pre e) {

		World world = e.getWorld();
		Random rand = e.getRand();

		if (world.getBiome(e.getPos()).isSnowyBiome()) {
			if (rand.nextInt(5) <= 3) {
				generateArms(world, e.getPos());
			}
		}

		int blockY = rand.nextInt(64);
		int blockX = e.getChunkPos().x * 16 + (rand.nextInt(16) + 8);
		int blockZ = e.getChunkPos().z * 16 + (rand.nextInt(16) + 8);
		BlockPos pos = new BlockPos(blockX, blockY, blockZ);

		if (world.provider.getDimension() == 0 && rand.nextBoolean() && !world.getBiome(e.getPos()).isSnowyBiome()) {
			if (blockY > 3 && blockY < 60) {
				genCrystal.generate(world, rand, pos);
			}
		}
	}

	private static void generateArms(World world, BlockPos position) {
		BlockPos pos = new BlockPos(position.add(new BlockPos(8, 0, 8)));
		if ((!world.provider.isNether() || pos.getY() < 255) && world.getBiome(position).isSnowyBiome()) {
			if (world.getBlockState(pos).getBlock() == Blocks.SNOW || world.getBlockState(pos).getBlock() == Blocks.SNOW_LAYER) world.setBlockState(pos, WAObjects.Blocks.ARM.getDefaultState(), 1);
		}
	}
	
	@SubscribeEvent
	public static void cancelDamage(LivingAttackEvent e) {
		Entity source = e.getSource().getTrueSource();
		if (source instanceof EntityLivingBase) {
			EntityLivingBase attacker = (EntityLivingBase) source;
			EntityLivingBase victim = e.getEntityLiving();
			
			if (victim instanceof EntityWeepingAngel) {
				ItemStack item = attacker.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
				boolean isPic = item.getItem() instanceof ItemPickaxe || item.getItem().getRegistryName().toString().contains("pickaxe");
				e.setCanceled(!isPic);
				
				if (!isPic) {
					attacker.attackEntityFrom(WAObjects.STONE, 2.5F);
				} else {
					Item pick = item.getItem();
					
					if (pick != Items.DIAMOND_PICKAXE && victim.world.getDifficulty() == EnumDifficulty.HARD) {
						e.setCanceled(true);
					}
					
					victim.playSound(SoundEvents.BLOCK_STONE_BREAK, 1.0F, 1.0F);
				}
				
				if (!(source instanceof Entity)) {
					e.setCanceled(true);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onSpawn(LivingSpawnEvent.CheckSpawn e) {
		if (e.getEntity() instanceof EntityWeepingAngel) {
			e.setResult(Event.Result.DENY);
			for (int i : WAConfig.spawn.dimensionWhitelist) {
				if (i == e.getWorld().provider.getDimension()) {
					e.setResult(Event.Result.DEFAULT);
				}
			}
		}
	}

}
