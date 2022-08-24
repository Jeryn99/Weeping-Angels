package mc.craig.software.angels.util;

import net.minecraft.world.damagesource.DamageSource;

public class WADamageSources extends DamageSource {

    public static WADamageSources GENERATOR = new WADamageSources("generator");
    public static WADamageSources PUNCH_STONE = new WADamageSources("punch_stone");

    public WADamageSources(String message) {
        super(message);
    }

    @Override
    public String getMsgId() {
        return super.getMsgId();
    }
}
