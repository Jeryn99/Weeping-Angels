package me.suff.mc.angels.common.tileentities;

import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.entities.AngelType;
import me.suff.mc.angels.common.variants.AbstractVariant;

/* Created by Craig on 03/04/2021 */
public interface IPlinth {

    void changeModel();

    void changePose();

    void sendUpdatesToClient();

    void spawn();

    AngelType getCurrentType();

    WeepingAngelPose getCurrentPose();

    AbstractVariant getVariant();

    void setAbstractVariant(AbstractVariant variant);


}
