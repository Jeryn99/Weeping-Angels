package mc.craig.software.angels.common.blockentity;

import mc.craig.software.angels.common.WAConstants;
import mc.craig.software.angels.common.entity.angel.AngelEmotion;
import mc.craig.software.angels.common.entity.angel.AngelTextureVariant;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AnimationState;

public interface Plinth  {

    AnimationState animationState = new AnimationState();

    default AnimationState getAnimationState(){
        return animationState;
    }

    void changeVariant(Plinth plinth);
    void setSpecificVariant(AngelTextureVariant angelTextureVariant);
    AngelTextureVariant getVariant();

    default CompoundTag writeNbt(CompoundTag compoundTag) {
        if (getVariant() == null) {
            setSpecificVariant(AngelTextureVariant.getRandomVariant(AngelTextureVariant.VARIANTS, RandomSource.create()));
        }
        compoundTag.putString(WAConstants.VARIANT, getVariant().location().toString());
        compoundTag.putInt(WAConstants.ANIMATION, getAnimation());
        compoundTag.putString(WAConstants.EMOTION, getEmotion().getId());
        return compoundTag;
    }

    default void readNbt(CompoundTag compoundTag) {
        setSpecificVariant(AngelTextureVariant.getVariant(new ResourceLocation(compoundTag.getString(WAConstants.VARIANT))));
        setAnimation(Mth.clamp(compoundTag.getInt(WAConstants.ANIMATION), 0, 12));
        setEmotion(AngelEmotion.find(compoundTag.getString(WAConstants.EMOTION)));
    }

    void setAnimation(int animation);
    int getAnimation();

    void sendUpdates();


    void setEmotion(AngelEmotion value);
    AngelEmotion getEmotion();
}
