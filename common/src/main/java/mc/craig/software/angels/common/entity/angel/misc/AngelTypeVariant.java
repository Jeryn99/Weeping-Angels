package mc.craig.software.angels.common.entity.angel.misc;

import mc.craig.software.angels.common.entity.angel.AngelTextureVariant;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public enum AngelTypeVariant {
    ALICE(AngelTextureVariant.VARIANTS, 12);

    private final Map<ResourceLocation, AngelTextureVariant> allowedVariants;
    private final int blockPoseSize;

    AngelTypeVariant(Map<ResourceLocation, AngelTextureVariant> allowedVariants, int blockPoseSize) {
        this.allowedVariants = allowedVariants;
        this.blockPoseSize = blockPoseSize;
    }

    public Map<ResourceLocation, AngelTextureVariant> getAllowedVariants() {
        return allowedVariants;
    }

    int blockPoseSize(){
        return blockPoseSize;
    }
}
