package me.suff.angels.common.events;

import me.suff.angels.common.WAObjects;
import me.suff.angels.common.entities.EntityWeepingAngel;
import me.suff.angels.config.WAConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;

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
		if (source instanceof EntityLivingBase) {
			EntityLivingBase attacker = (EntityLivingBase) source;
			EntityLivingBase victim = e.getEntityLiving();
			
			if (victim instanceof EntityWeepingAngel) {
				
				if (WAConfig.CONFIG.hardcoreMode.get()) {
					e.setCanceled(true);
					return;
				}
				
				ItemStack item = attacker.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
				boolean isPic = item.getItem() instanceof ItemPickaxe || item.getItem().getRegistryName().toString().contains("pickaxe");
				e.setCanceled(!isPic);
				
				if (!isPic) {
					attacker.attackEntityFrom(WAObjects.STONE, 2F);
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
	public void onLootTablesLoaded(LootTableLoadEvent event) {
		if (event.getName().getNamespace().contains("chests")) {
			final LootPool pool2 = event.getTable().getPool("pool2");
			if (pool2 != null) {
				pool2.addEntry(new LootEntryItem(WAObjects.Items.CHRONODYNE_GENERATOR, 10, 0, new LootFunction[]{new SetCount(new LootCondition[0], new RandomValueRange(1, 5))}, new LootCondition[0], "weeping-angels:generators"));
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
	
}
