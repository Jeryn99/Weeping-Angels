package mc.craig.software.angels.common.blockentity;

import mc.craig.software.angels.common.WAConstants;
import mc.craig.software.angels.common.entity.angel.AngelVariant;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

public interface Plinth  {

    void changeVariant(Plinth plinth);
    void setSpecificVariant(AngelVariant angelVariant);
    AngelVariant getVariant();

    default CompoundTag serializeNBT(CompoundTag compoundTag) {
        compoundTag.putString(WAConstants.VARIANT, getVariant().location().toString());
        return compoundTag;
    }

    default void deserializeNBT(CompoundTag compoundTag){
        setSpecificVariant(AngelVariant.getVariant(new ResourceLocation(compoundTag.getString(WAConstants.VARIANT))));
    }

}
