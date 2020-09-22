package me.swirtzly.minecraft.angels.common.events;

import me.swirtzly.minecraft.angels.WeepingAngels;
import me.swirtzly.minecraft.angels.common.WAObjects;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import me.swirtzly.minecraft.angels.config.WAConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EventHandler {

	@SubscribeEvent
	public static void onKilled(LivingDeathEvent event){
		LivingEntity killed = event.getEntityLiving();
		DamageSource damageSource = event.getSource();
		if(damageSource == WAObjects.ANGEL_NECK_SNAP){
			killed.playSound(WAObjects.Sounds.ANGEL_NECK_SNAP.get(), 1, 1);
		}
	}

	@SubscribeEvent
	public static void onBiomeLoad(BiomeLoadingEvent biomeLoadingEvent) {
		Biome.Category biomeCategory = biomeLoadingEvent.getCategory();
		if (biomeCategory == Biome.Category.ICY || biomeCategory.getName().contains("snow")) {
			WeepingAngels.LOGGER.info("Added Arms to: " + biomeLoadingEvent.getName());
			biomeLoadingEvent.getGeneration().withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, WAObjects.WorldGenEntries.ARM_SNOW_FEATURE_CONFIGURED).build();
		}
	}

	@SubscribeEvent
	public static void onAngelDamage(LivingAttackEvent e) {
		if (!WAConfig.CONFIG.pickaxeOnly.get()) return;
		
		Entity source = e.getSource().getTrueSource();
		if (source instanceof LivingEntity) {
            LivingEntity attacker = (LivingEntity) source;
            LivingEntity victim = e.getEntityLiving();

            if (victim instanceof WeepingAngelEntity && attacker instanceof PlayerEntity) {

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
				
				if (!(source instanceof LivingEntity)) {
					e.setCanceled(true);
				}
			}
		}
	}
}
	
