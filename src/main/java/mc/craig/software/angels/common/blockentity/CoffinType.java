package mc.craig.software.angels.common.blockentity;

import mc.craig.software.angels.WeepingAngels;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;

public enum CoffinType {
    NEW, SEMI_WEATHERED, WEATHERED, HEAVILY_WEATHERED;

    CoffinType() {
    }

    public ResourceLocation getTexture() {
        return new ResourceLocation(WeepingAngels.MODID, "textures/blockentity/coffin_" + name().toLowerCase() + ".png");
    }

    public static CoffinType find(String coffinType) {
        for (CoffinType type : CoffinType.values()) {
            if(type.getId().equalsIgnoreCase(coffinType)){
                return type;
            }
        }
        return NEW;
    }

    public static CoffinType random(RandomSource randomSource) {
        int pick = randomSource.nextInt(CoffinType.values().length);
        return CoffinType.values()[pick];
    }

    public String getId() {
        return name().toLowerCase();
    }
}
