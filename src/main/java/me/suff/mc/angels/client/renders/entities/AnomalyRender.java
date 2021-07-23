package me.suff.mc.angels.client.renders.entities;

import com.mojang.blaze3d.vertex.PoseStack;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.client.models.entity.PortalModel;
import me.suff.mc.angels.client.models.entity.WAModels;
import me.suff.mc.angels.common.entities.AnomalyEntity;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class AnomalyRender extends MobRenderer<AnomalyEntity, EntityModel<AnomalyEntity>> implements EntityRendererProvider<AnomalyEntity> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(WeepingAngels.MODID, "textures/entities/anomaly.png");

    public AnomalyRender(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new PortalModel(Minecraft.getInstance().getEntityModels().bakeLayer(WAModels.ANOMALY)),0);
    }


    @Override
    public void render(AnomalyEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        matrixStackIn.pushPose();
        Camera activerenderinfo = Minecraft.getInstance().gameRenderer.getMainCamera();
        matrixStackIn.mulPose(activerenderinfo.rotation());
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(AnomalyEntity entity) {
        return TEXTURE;
    }

    @Override
    public EntityRenderer<AnomalyEntity> create(Context context) {
        return new AnomalyRender(context);
    }
}
