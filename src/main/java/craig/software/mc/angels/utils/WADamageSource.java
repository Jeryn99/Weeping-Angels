package craig.software.mc.angels.utils;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class WADamageSource extends DamageSource {

    private final String message;

    public WADamageSource(String name) {
        super(name);
        this.message = "source.weeping_angels." + name;
    }

    @Override
    public ITextComponent getLocalizedDeathMessage(LivingEntity entity) {
        return new TranslationTextComponent(message, entity.getName());
    }
}
