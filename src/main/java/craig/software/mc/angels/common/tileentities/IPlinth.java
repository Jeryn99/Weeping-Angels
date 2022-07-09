package craig.software.mc.angels.common.tileentities;

import craig.software.mc.angels.client.poses.WeepingAngelPose;
import craig.software.mc.angels.common.entities.AngelType;
import craig.software.mc.angels.common.variants.AbstractVariant;

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
