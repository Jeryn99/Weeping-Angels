package me.swirtzly.angels.utils;

import javax.annotation.Nullable;

import me.swirtzly.angels.common.events.FortuneLevelEvent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by 50ap5ud5
 * on 13 Feb 2020 @ 11:59:07 am
 */
public class WAEnchantHelper {

	/**
	 *Gets Fortune Enchantment level when entity is killed
	 * @param target
	 * @param killer
	 * @param cause
	 * @return
	 */
   public static int getFortuneLevel(Entity target, @Nullable Entity killer, DamageSource cause)
   {
       int fortune = 0;
       if (killer instanceof LivingEntity)
           fortune = getFortuneModifier((LivingEntity)killer);
       if (target instanceof LivingEntity)
    	   fortune = getFortuneLevel((LivingEntity)target, cause, fortune);
       return fortune;
   }
   
   public static int getFortuneLevel(LivingEntity target, DamageSource cause, int level)
   {
       FortuneLevelEvent event = new FortuneLevelEvent(target, cause, level);
       MinecraftForge.EVENT_BUS.post(event);
       return event.getFortuneLevel();
   }
   
   public static int getFortuneModifier(LivingEntity entityIn) {
	      return EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.FORTUNE, entityIn);
   }

}
