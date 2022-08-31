package mc.craig.software.angels.common.blockentity;

import mc.craig.software.angels.WeepingAngels;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;

public enum CoffinType {
    NEW(false), SEMI_WEATHERED(false), WEATHERED(false), HEAVILY_WEATHERED(false),
    POLICE_BOX(true), POLICE_BOX_2(true), POLICE_BOX_3(true), POLICE_BOX_4(true), POLICE_BOX_5(true), POLICE_BOX_6(true), POLICE_BOX_7(true), POLICE_BOX_8(true);

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

    public static CoffinType randomCoffin(RandomSource randomSource) {
        CoffinType[] coffinTypes = new CoffinType[]{NEW, SEMI_WEATHERED, WEATHERED, HEAVILY_WEATHERED};
        int pick = randomSource.nextInt(coffinTypes.length);
        return coffinTypes[pick];
    }

    public static CoffinType randomTardis(RandomSource randomSource) {
        CoffinType[] coffinTypes = new CoffinType[]{POLICE_BOX, POLICE_BOX_2, POLICE_BOX_3, POLICE_BOX_4, POLICE_BOX_5};
        int pick = randomSource.nextInt(coffinTypes.length);
        return coffinTypes[pick];
    }

    public String getId() {
        return name().toLowerCase();
    }
}
