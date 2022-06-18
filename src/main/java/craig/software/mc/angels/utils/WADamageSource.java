package craig.software.mc.angels.utils;

import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class WADamageSource extends DamageSource {

    private final String message;

    public WADamageSource(String name) {
        super(name);
        this.message = "source.weeping_angels." + name;
    }

    @Override
    public @NotNull Component getLocalizedDeathMessage(LivingEntity entity) {
        return Component.translatable(message, entity.getName());
    }
}
