package mc.craig.software.angels.client.models.entity.angel;

import mc.craig.software.angels.common.blockentity.StatueBlockEntity;
import mc.craig.software.angels.common.entity.angel.AngelEmotion;
import mc.craig.software.angels.common.entity.angel.AngelTextureVariant;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.resources.ResourceLocation;

public abstract class AngelModel extends HierarchicalModel<WeepingAngel> {
    public Iterable<ModelPart> getWings() {
        return null;
    }

    public ResourceLocation texture(AngelEmotion angelEmotion, AngelTextureVariant angelTextureVariant) {
        return DefaultPlayerSkin.getDefaultSkin();
    }

    public void animateTile(StatueBlockEntity statueBlockEntity) {

    }

    public AnimationDefinition poseForId(int index) {
        return null;
    }
}
