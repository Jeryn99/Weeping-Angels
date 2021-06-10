package me.suff.mc.angels.client.renders.entities;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.vertex.MatrixApplyingVertexBuilder;
import com.mojang.blaze3d.vertex.VertexBuilderUtils;
import me.suff.mc.angels.client.models.entity.IAngelModel;
import me.suff.mc.angels.client.models.entity.ModelAngelaAngel;
import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import me.suff.mc.angels.utils.ClientUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ModelBakery;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

public class AngelRender extends MobRenderer<WeepingAngelEntity, EntityModel<WeepingAngelEntity>> {

    public AngelRender(EntityRendererManager manager) {
        super(manager, new ModelAngelaAngel(), 0.0F);
    }

    @Override
    protected float getWhiteOverlayProgress(WeepingAngelEntity livingEntityIn, float partialTicks) {
        return 0;
    }

    @Override
    public void render(WeepingAngelEntity angel, float p_225623_2_, float p_225623_3_, MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int p_225623_6_) {
        model = ClientUtil.getModelForAngel(angel.getAngelType());

        ItemStack key = angel.getMainHandItem();
        matrixStack.pushPose();
        float offset = MathHelper.cos(angel.tickCount * 0.1F) * -0.09F;
        matrixStack.scale(0.5F, 0.5F, 0.5F);
        matrixStack.translate(0, 4.5, 0);
        matrixStack.translate(0, offset, 0);
        renderItem(angel, key, ItemCameraTransforms.TransformType.FIXED, false, matrixStack, iRenderTypeBuffer, p_225623_6_);
        matrixStack.popPose();

        if (calcOverlay(angel.getHealth()) != -1) {
            MatrixStack.Entry matrixstack$entry = matrixStack.last();
            IVertexBuilder ivertexbuilder = new MatrixApplyingVertexBuilder(Minecraft.getInstance().levelRenderer.renderBuffers.crumblingBufferSource().getBuffer(ModelBakery.DESTROY_TYPES.get(calcOverlay(angel.getHealth()))), matrixstack$entry.pose(), matrixstack$entry.normal());
            IRenderTypeBuffer finalIRenderTypeBuffer = iRenderTypeBuffer;
            iRenderTypeBuffer = (p_230014_2_) -> {
                IVertexBuilder vertexBuilder = finalIRenderTypeBuffer.getBuffer(p_230014_2_);
                return p_230014_2_.affectsCrumbling() ? VertexBuilderUtils.create(ivertexbuilder, vertexBuilder) : vertexBuilder;
            };
        }
        super.render(angel, p_225623_2_, p_225623_3_, matrixStack, iRenderTypeBuffer, p_225623_6_);
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


    public int calcOverlay(float health) {
        if (health > 45) {
            return -1;
        }
        return (int) Math.floor((1.0 - (health / 50.0)) * 9);
    }

    private void renderItem(LivingEntity livingEntityIn, ItemStack itemStackIn, ItemCameraTransforms.TransformType transformTypeIn, boolean leftHand, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn) {
        if (!itemStackIn.isEmpty()) {
            Minecraft.getInstance().getItemInHandRenderer().renderItem(livingEntityIn, itemStackIn, transformTypeIn, leftHand, matrixStackIn, bufferIn, combinedLightIn);
        }
    }

}
