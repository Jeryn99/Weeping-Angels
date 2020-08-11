package me.swirtzly.minecraft.angels.common.events;

import me.swirtzly.minecraft.angels.common.WAObjects;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import me.swirtzly.minecraft.angels.config.WAConfig;
import me.swirtzly.minecraft.angels.utils.AngelUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@Mod.EventBusSubscriber
public class EventHandler {
	
	@SubscribeEvent
	public static void cancelDamage(LivingAttackEvent e) {
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
	
	@SubscribeEvent
	public static void serverStartingEvent(FMLServerStartingEvent event) {
		AngelUtils.LIGHT_ITEMS.clear();
		AngelUtils.setupLightItems();
	}
}
