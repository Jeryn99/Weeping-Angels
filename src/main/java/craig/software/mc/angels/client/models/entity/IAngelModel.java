package craig.software.mc.angels.client.models.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import craig.software.mc.angels.client.poses.WeepingAngelPose;
import craig.software.mc.angels.common.variants.AbstractVariant;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;

public interface IAngelModel {

    ResourceLocation generateTex(WeepingAngelPose pose, AbstractVariant angelVariants);

    ResourceLocation getTextureForPose(Object weepingAngelEntity, WeepingAngelPose pose);

    WeepingAngelPose getAngelPose();

    void setAngelPose(WeepingAngelPose angelPose);

    Iterable<ModelRenderer> wings(MatrixStack pose);

    ModelRenderer getSantaAttachment(MatrixStack pose, boolean perform);

}
