package me.sub.angels.common.events;

import me.sub.angels.common.WAObjects;
import me.sub.angels.common.entities.EntityWeepingAngel;
import me.sub.angels.common.events.mods.EventAngelTeleport;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumDifficulty;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class EventHandler {

    @SubscribeEvent
    public static void angelTeleported(EventAngelTeleport e) {
        EntityPlayer player = e.getEntityPlayer();
        if (player.world.rand.nextInt(2) == 2) {
            player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 600, 3));
            player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 600, 1));
            player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 600, 3));
            player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 600, 3));
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
                boolean isPic = item.getItem() instanceof ItemPickaxe;
                e.setCanceled(!isPic);

                if (!isPic) {
                    attacker.attackEntityFrom(WAObjects.STONE, 2.5F);
                } else {
                    ItemPickaxe pick = (ItemPickaxe) item.getItem();

                    if (pick != Items.DIAMOND_PICKAXE && victim.world.getDifficulty() == EnumDifficulty.HARD) {
                        e.setCanceled(true);
                    }

                    victim.playSound(SoundEvents.BLOCK_STONE_BREAK, 1.0F, 1.0F);
                    pick.setDamage(item, pick.getDamage(item) - 1);
                }

                if (!(source instanceof Entity)) {
                    e.setCanceled(true);
                }
            }
        }
    }

}
