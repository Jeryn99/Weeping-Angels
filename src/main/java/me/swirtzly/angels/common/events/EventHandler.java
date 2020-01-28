package me.swirtzly.angels.common.events;

import me.swirtzly.angels.common.WAObjects;
import me.swirtzly.angels.common.entities.EntityWeepingAngel;
import me.swirtzly.angels.config.WAConfig;
import me.swirtzly.angels.utils.AngelUtils;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.ItemLootEntry;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraft.world.storage.loot.functions.ILootFunction;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

public class EventHandler {
	
	private static void generateArms(World world, BlockPos position) {
		if (!WAConfig.CONFIG.arms.get()) return;
		BlockPos pos = new BlockPos(position.add(new BlockPos(8, 0, 8)));
		if ((!world.dimension.isNether() || pos.getY() < 255) && world.getBiome(position).doesSnowGenerate(world, pos)) {
			if (world.getBlockState(pos).getBlock() == Blocks.SNOW || world.getBlockState(pos).getBlock() == Blocks.SNOW_BLOCK)
				world.setBlockState(pos, WAObjects.Blocks.ARM.getDefaultState(), 1);
		}
	}
	
	@SubscribeEvent
	public void cancelDamage(LivingAttackEvent e) {
		if (!WAConfig.CONFIG.pickaxeOnly.get()) return;
		
		Entity source = e.getSource().getTrueSource();
		if (source instanceof LivingEntity) {
			LivingEntity attacker = (LivingEntity) source;
			LivingEntity victim = e.getEntityLiving();
			
			if (victim instanceof EntityWeepingAngel) {
				
				if (WAConfig.CONFIG.hardcoreMode.get()) {
					e.setCanceled(true);
					return;
				}
				
				ItemStack item = attacker.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
				boolean isPic = item.getItem() instanceof PickaxeItem || item.getItem().getRegistryName().toString().contains("pickaxe");
				e.setCanceled(!isPic);
				
				if (!isPic) {
					attacker.attackEntityFrom(WAObjects.STONE, 2F);
				} else {
					Item pick = item.getItem();
					
					if (pick != Items.DIAMOND_PICKAXE && victim.world.getDifficulty() == Difficulty.HARD) {
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
	public void onLootTablesLoaded(LootTableLoadEvent event) {
		if (event.getName().getNamespace().contains("chests")) {
			final LootPool pool2 = event.getTable().getPool("pool2");
			if (pool2 != null) {
//				pool2.addEntry(new ItemLootEntry(WAObjects.Items.CHRONODYNE_GENERATOR, 10, 0, new ILootFunction[]{new SetCount(new ILootCondition[0], new RandomValueRange(1, 5))}, new ILootCondition[0], "weeping_angels:generators"));
			}
		}
	}
	
	@SubscribeEvent
	public void onSpawn(LivingSpawnEvent.CheckSpawn e) {
		if (e.getEntity() instanceof EntityWeepingAngel) {
			e.setResult(Event.Result.DENY);
			for (int i : WAConfig.CONFIG.dimensionWhitelist.get()) {
				if (i == e.getWorld().getDimension().getType().getId()) {
					e.setResult(Event.Result.DEFAULT);
				}
			}
		}
	}

	@SubscribeEvent
	public void serverStartingEvent(FMLServerStartingEvent event) {
		AngelUtils.LIGHT_ITEMS.clear();
		AngelUtils.setupLightItems();
	}
}
