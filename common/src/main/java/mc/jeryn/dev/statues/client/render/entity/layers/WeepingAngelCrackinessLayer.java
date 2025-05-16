package mc.jeryn.dev.statues.client.render.entity.layers;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mc.jeryn.dev.statues.WeepingAngels;
import mc.jeryn.dev.statues.client.models.ModelRegistration;
import mc.jeryn.dev.statues.client.models.entity.angel.AngelModel;
import mc.jeryn.dev.statues.client.render.entity.AngelRenderState;
import mc.jeryn.dev.statues.common.entity.angel.WeepingAngel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

import java.util.Map;

public class WeepingAngelCrackinessLayer extends RenderLayer<AngelRenderState, AngelModel> {
    private static final Map<WeepingAngel.Crackiness, ResourceLocation> resourceLocations = ImmutableMap.of(WeepingAngel.Crackiness.LOW, ResourceLocation.tryBuild(WeepingAngels.MODID, "textures/entity/angel/alice/cracks/low_cracks.png"), WeepingAngel.Crackiness.MEDIUM, ResourceLocation.tryBuild(WeepingAngels.MODID, "textures/entity/angel/alice/cracks/medium_cracks.png"), WeepingAngel.Crackiness.HIGH, ResourceLocation.tryBuild(WeepingAngels.MODID, "textures/entity/angel/alice/cracks/high_cracks.png"));

    public WeepingAngelCrackinessLayer(RenderLayerParent<AngelRenderState, AngelModel> renderLayerParent) {
        super(renderLayerParent);
    }


    protected static <T extends LivingEntity> void renderAngelModel(AngelModel model, ResourceLocation textureLocation, PoseStack matrixStack, MultiBufferSource buffer, int packedLight, float red, float green, float blue) {
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(textureLocation));
        model.renderToBuffer(matrixStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY);
    }



    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, AngelRenderState entityRenderState, float f, float g) {
        if (!entityRenderState.isInvisible) {
            WeepingAngel.Crackiness crackiness = entityRenderState.crackiness;
            if (crackiness != WeepingAngel.Crackiness.NONE) {
                ResourceLocation texture = resourceLocations.get(crackiness);
                AngelModel model = ModelRegistration.getModelFor(entityRenderState.currentVariant);
                renderAngelModel(model, texture, poseStack, multiBufferSource, packedLight, 1.0F, 1.0F, 1.0F);
            }
        }
    }
}