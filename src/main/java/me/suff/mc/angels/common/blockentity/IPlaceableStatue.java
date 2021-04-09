package me.suff.mc.angels.common.blockentity;

import me.suff.mc.angels.enums.WeepingAngelPose;
import me.suff.mc.angels.enums.WeepingAngelVariants;

/* Created by Craig on 19/02/2021 */
public interface IPlaceableStatue {
    WeepingAngelVariants getAngelVariant();

    void setAngelVariant(WeepingAngelVariants weepingAngelVariants);

    WeepingAngelPose getAngelPose();

    void setAngelPose(WeepingAngelPose weepingAngelPose);

}
