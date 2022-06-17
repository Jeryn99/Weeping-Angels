package me.suff.mc.angels.common.blockentities;

import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.entities.AngelType;
import me.suff.mc.angels.common.variants.AngelVariant;

/* Created by Craig on 03/04/2021 */
public interface IPlinth {

    void changeModel();

    void changePose();

    void sendUpdatesToClient();

    AngelType getCurrentType();

    WeepingAngelPose getCurrentPose();

    AngelVariant getVariant();

    void setAbstractVariant(AngelVariant variant);

}
