package dev.jeryn.angels.client.render.entity.layers;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.jeryn.angels.WeepingAngels;
import dev.jeryn.angels.client.models.ModelRegistration;
import dev.jeryn.angels.client.models.entity.angel.AngelModel;
import dev.jeryn.angels.common.entity.angel.WeepingAngel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

import java.util.Map;

public class WeepingAngelCrackinessLayer extends RenderLayer<WeepingAngel, AngelModel> {
    private static final Map<WeepingAngel.Crackiness, ResourceLocation> resourceLocations = ImmutableMap.of(WeepingAngel.Crackiness.LOW, new ResourceLocation(WeepingAngels.MODID, "textures/entity/angel/alice/cracks/low_cracks.png"), WeepingAngel.Crackiness.MEDIUM, new ResourceLocation(WeepingAngels.MODID, "textures/entity/angel/alice/cracks/medium_cracks.png"), WeepingAngel.Crackiness.HIGH, new ResourceLocation(WeepingAngels.MODID, "textures/entity/angel/alice/cracks/high_cracks.png"));

    public WeepingAngelCrackinessLayer(RenderLayerParent<WeepingAngel, AngelModel> pRenderer) {
        super(pRenderer);
    }

    protected static <T extends LivingEntity> void renderAngelModel(EntityModel<T> model, ResourceLocation textureLocation, PoseStack matrixStack, MultiBufferSource buffer, int packedLight, float red, float green, float blue) {
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(textureLocation));
        model.renderToBuffer(matrixStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, 1.0F);
    }

    public void render(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, WeepingAngel pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        if (!pLivingEntity.isInvisible()) {
            WeepingAngel.Crackiness weepingAngelCracks = pLivingEntity.getCrackiness();
            if (weepingAngelCracks != WeepingAngel.Crackiness.NONE) {
                ResourceLocation resourcelocation = resourceLocations.get(weepingAngelCracks);
                renderAngelModel(ModelRegistration.getModelFor(pLivingEntity.getVariant()), resourcelocation, pMatrixStack, pBuffer, pPackedLight, 1.0F, 1.0F, 1.0F);
            }
        }
    }
}