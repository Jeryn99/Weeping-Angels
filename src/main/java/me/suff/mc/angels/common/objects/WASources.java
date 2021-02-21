package me.suff.mc.angels.common.objects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

/* Created by Craig on 19/02/2021 */
public class WASources {

    public static final DamageSource BREAK_NECK = new WASource("neck_snap");
    public static final DamageSource PUNCH_STONE = new WASource("punch_stone");
    public static final DamageSource BACK_IN_TIME = new WASource("backintime");

    public static class WASource extends DamageSource {
        protected WASource(String name) {
            super(name);
        }

        @Override
        public Text getDeathMessage(LivingEntity entity) {
            return new TranslatableText("source.weeping_angels." + name, entity.getDisplayName());
        }
    }

}
