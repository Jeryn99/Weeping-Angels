package craig.software.mc.angels.client.renders.entities;

import com.mojang.blaze3d.vertex.PoseStack;
import craig.software.mc.angels.WeepingAngels;
import craig.software.mc.angels.client.models.entity.PortalModel;
import craig.software.mc.angels.client.models.entity.WAModels;
import craig.software.mc.angels.common.entities.Portal;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class AnomalyRender extends MobRenderer<Portal, EntityModel<Portal>> implements EntityRendererProvider<Portal> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(WeepingAngels.MODID, "textures/entities/anomaly.png");

    public AnomalyRender(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new PortalModel(Minecraft.getInstance().getEntityModels().bakeLayer(WAModels.ANOMALY)), 0);
    }


    @Override
    public void render(@NotNull Portal entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, @NotNull MultiBufferSource bufferIn, int packedLightIn) {
        matrixStackIn.pushPose();
        Camera activerenderinfo = Minecraft.getInstance().gameRenderer.getMainCamera();
        matrixStackIn.mulPose(activerenderinfo.rotation());
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.popPose();
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Portal entity) {
        return TEXTURE;
    }

    @Nullable
    @Override
    protected RenderType getRenderType(@NotNull Portal p_115322_, boolean p_115323_, boolean p_115324_, boolean p_115325_) {
        return RenderType.entityCutout(TEXTURE);
    }

    @Override
    public @NotNull EntityRenderer<Portal> create(@NotNull Context context) {
        return new AnomalyRender(context);
    }
}
