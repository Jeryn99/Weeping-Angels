package mc.craig.software.angels.common.blockentities;

import mc.craig.software.angels.client.poses.WeepingAngelPose;
import mc.craig.software.angels.common.entities.AngelType;
import mc.craig.software.angels.common.variants.AbstractVariant;

/* Created by Craig on 03/04/2021 */
public interface IPlinth {

    void changeModel();

    void changePose();

    void sendUpdatesToClient();

    AngelType getCurrentType();

    WeepingAngelPose getCurrentPose();

    AbstractVariant getVariant();

    void setAbstractVariant(AbstractVariant variant);

}
