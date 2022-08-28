package mc.craig.software.angels.common.blockentity;

import mc.craig.software.angels.WeepingAngels;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;

public enum CoffinType {
    NEW(false), SEMI_WEATHERED(false), WEATHERED(false), HEAVILY_WEATHERED(false), POLICE_BOX(true), POLICE_BOX_2(true), POLICE_BOX_3(true), POLICE_BOX_4(true), POLICE_BOX_5(true);

    private final boolean isTardis;

    CoffinType(boolean isTardis) {
        this.isTardis = isTardis;
    }

    public static CoffinType find(String coffinType) {
        for (CoffinType type : CoffinType.values()) {
            if (type.getId().equalsIgnoreCase(coffinType)) {
                return type;
            }
        }
        return NEW;
    }

    public ResourceLocation getTexture() {
        return new ResourceLocation(WeepingAngels.MODID, "textures/blockentity/coffin_" + name().toLowerCase() + ".png");
    }

    public boolean isTardis() {
        return isTardis;
    }

    public static CoffinType random(RandomSource randomSource) {
        int pick = randomSource.nextInt(CoffinType.values().length);
        return CoffinType.values()[pick];
    }

    public String getId() {
        return name().toLowerCase();
    }
}
