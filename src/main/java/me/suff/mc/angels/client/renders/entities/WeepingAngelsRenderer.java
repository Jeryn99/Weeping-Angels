package me.suff.mc.angels.client.renders.entities;

import com.mojang.blaze3d.matrix.MatrixStack;
import me.suff.mc.angels.client.models.entity.IAngelModel;
import me.suff.mc.angels.client.models.entity.ModelDisasterAngel;
import me.suff.mc.angels.client.models.entity.WeepingHeldItem;
import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import me.suff.mc.angels.utils.ClientUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

public class WeepingAngelsRenderer extends MobRenderer<WeepingAngelEntity, EntityModel<WeepingAngelEntity>> {

    public WeepingAngelsRenderer(EntityRendererManager manager) {
        super(manager, new ModelDisasterAngel(), 0.0F);
        addLayer(new AngelCrackedLayer(this));
        addLayer(new WeepingHeldItem<>(this));
    }

    @Override
    protected float getWhiteOverlayProgress(WeepingAngelEntity livingEntityIn, float partialTicks) {
        return 0;
    }

    @Override
    public void render(WeepingAngelEntity weepingAngelEntity, float pEntityYaw, float pPartialTicks, MatrixStack pMatrixStackIn, IRenderTypeBuffer pBufferIn, int pPackedLightIn) {
        model = ClientUtil.getModelForAngel(weepingAngelEntity.getAngelType());

        if(!(model instanceof IHasArm)) {
            ItemStack key = weepingAngelEntity.getMainHandItem();
            pMatrixStackIn.pushPose();
            float offset = MathHelper.cos(weepingAngelEntity.tickCount * 0.1F) * -0.09F;
            pMatrixStackIn.scale(0.5F, 0.5F, 0.5F);
            pMatrixStackIn.translate(0, 5, 0);
            pMatrixStackIn.translate(0, offset, 0);
            pMatrixStackIn.mulPose(Vector3f.YP.rotation(weepingAngelEntity.level.getGameTime() / 20F));
            renderItem(weepingAngelEntity, key, ItemCameraTransforms.TransformType.FIXED, false, pMatrixStackIn, pBufferIn, pPackedLightIn);
            pMatrixStackIn.popPose();
        }

        pMatrixStackIn.pushPose();
        super.render(weepingAngelEntity, pEntityYaw, pPartialTicks, pMatrixStackIn, pBufferIn, pPackedLightIn);
        pMatrixStackIn.popPose();
    }

    @Override
    protected float getFlipDegrees(WeepingAngelEntity entityLivingBaseIn) {
        return 90;
    }

    @Override
    protected void setupRotations(WeepingAngelEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        if (entityLiving.deathTime > 0) {
            float deathRotation = ((float) entityLiving.deathTime + partialTicks - 1.0F) / 20.0F * 1.6F;
            deathRotation = MathHelper.sqrt(deathRotation);
            if (deathRotation > 1.0F) {
                deathRotation = 1.0F;
            }
            matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(deathRotation * this.getFlipDegrees(entityLiving)));
            return;
        }

        super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    public ResourceLocation getTextureLocation(WeepingAngelEntity weepingAngelEntity) {
        IAngelModel iAngelModel = (IAngelModel) model;
        return iAngelModel.getTextureForPose(weepingAngelEntity, WeepingAngelPose.getPose(weepingAngelEntity.getAngelPose()));
    }

    private void renderItem(LivingEntity livingEntityIn, ItemStack itemStackIn, ItemCameraTransforms.TransformType transformTypeIn, boolean leftHand, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn) {
        if (!itemStackIn.isEmpty()) {
            Minecraft.getInstance().getItemInHandRenderer().renderItem(livingEntityIn, itemStackIn, transformTypeIn, leftHand, matrixStackIn, bufferIn, combinedLightIn);
        }
    }

}
