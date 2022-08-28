package mc.craig.software.angels.client.models.entity.angel;

import mc.craig.software.angels.common.entity.angel.AngelEmotion;
import mc.craig.software.angels.common.entity.angel.AngelVariant;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;

public interface AngelModel {
    Iterable<ModelPart> getWings();
    ResourceLocation texture(AngelEmotion angelEmotion, AngelVariant angelVariant);
}
