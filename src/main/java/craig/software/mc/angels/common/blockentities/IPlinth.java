package craig.software.mc.angels.common.blockentities;

import craig.software.mc.angels.client.poses.WeepingAngelPose;
import craig.software.mc.angels.common.entities.WeepingAngelTypes;
import craig.software.mc.angels.common.variants.AngelVariant;

/* Created by Craig on 03/04/2021 */
public interface IPlinth {

    void changeModel();

    void changePose();

    void sendUpdatesToClient();

    WeepingAngelTypes getCurrentType();

    WeepingAngelPose getCurrentPose();

    AngelVariant getVariant();

    void setAbstractVariant(AngelVariant variant);

}
