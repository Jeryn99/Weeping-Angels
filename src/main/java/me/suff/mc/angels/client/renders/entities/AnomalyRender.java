package me.suff.mc.angels.client.renders.entities;

import com.mojang.blaze3d.matrix.MatrixStack;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.client.models.entity.PortalModel;
import me.suff.mc.angels.common.entities.AnomalyEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;

public class AnomalyRender extends MobRenderer< AnomalyEntity, EntityModel< AnomalyEntity > > {

    private static final ResourceLocation TEXTURE = new ResourceLocation(WeepingAngels.MODID, "textures/entities/anomaly.png");

    public AnomalyRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new PortalModel(), 0);
    }

    @Override
    public void render(AnomalyEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.push();
        ActiveRenderInfo activerenderinfo = Minecraft.getInstance().gameRenderer.getActiveRenderInfo();
        matrixStackIn.rotate(activerenderinfo.getRotation());
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.pop();
    }

    @Override
    public ResourceLocation getEntityTexture(AnomalyEntity entity) {
        return TEXTURE;
    }
}
