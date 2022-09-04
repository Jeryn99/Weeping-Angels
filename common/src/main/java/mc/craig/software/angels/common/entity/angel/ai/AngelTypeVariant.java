package mc.craig.software.angels.common.entity.angel.ai;

import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public enum AngelTypeVariant {
    ALICE(AngelVariant.VARIANTS, 12);

    private final Map<ResourceLocation, AngelVariant> allowedVariants;
    private final int blockPoseSize;

    AngelTypeVariant(Map<ResourceLocation, AngelVariant> allowedVariants, int blockPoseSize) {
        this.allowedVariants = allowedVariants;
        this.blockPoseSize = blockPoseSize;
    }

    public Map<ResourceLocation, AngelVariant> getAllowedVariants() {
        return allowedVariants;
    }

    int blockPoseSize(){
        return blockPoseSize;
    }
}
