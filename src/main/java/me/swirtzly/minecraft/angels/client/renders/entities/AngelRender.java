package me.swirtzly.minecraft.angels.client.renders.entities;

import com.mojang.blaze3d.matrix.MatrixStack;
import me.swirtzly.minecraft.angels.client.models.entity.IAngelModel;
import me.swirtzly.minecraft.angels.client.models.entity.ModelAngelEd;
import me.swirtzly.minecraft.angels.client.poses.AngelPoses;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import me.swirtzly.minecraft.angels.utils.ClientUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class AngelRender extends MobRenderer<WeepingAngelEntity, EntityModel<WeepingAngelEntity>> {

    public AngelRender(EntityRendererManager manager) {
        super(manager, new ModelAngelEd(), 0.0F);
        addLayer(new CrackLayer(this));
    }

    @Override
    protected float getOverlayProgress(WeepingAngelEntity livingEntityIn, float partialTicks) {
        return 0;
    }

    @Override
    public void render(WeepingAngelEntity angel, float p_225623_2_, float p_225623_3_, MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int p_225623_6_) {
        ItemStack key = angel.getHeldItemMainhand();
        matrixStack.push();
        float offset = MathHelper.cos(angel.ticksExisted * 0.1F) * -0.09F;
        matrixStack.scale(0.5F, 0.5F, 0.5F);
        matrixStack.translate(0, 4.5, 0);
        matrixStack.translate(0, offset, 0);
        renderItem(angel, key, ItemCameraTransforms.TransformType.FIXED, false, matrixStack, iRenderTypeBuffer, p_225623_6_);
        matrixStack.pop();
        entityModel = ClientUtil.getModelForAngel(angel.getAngelType());
        super.render(angel, p_225623_2_, p_225623_3_, matrixStack, iRenderTypeBuffer, p_225623_6_);
    }

    @Override
    public ResourceLocation getEntityTexture(WeepingAngelEntity weepingAngelEntity) {
        IAngelModel iAngelModel = (IAngelModel) entityModel;
        return iAngelModel.getTextureForPose(weepingAngelEntity, AngelPoses.getPoseFromString(weepingAngelEntity.getAngelPose()));
    }

    private void renderItem(LivingEntity livingEntityIn, ItemStack itemStackIn, ItemCameraTransforms.TransformType transformTypeIn, boolean leftHand, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn) {
        if (!itemStackIn.isEmpty()) {
            Minecraft.getInstance().getFirstPersonRenderer().renderItemSide(livingEntityIn, itemStackIn, transformTypeIn, leftHand, matrixStackIn, bufferIn, combinedLightIn);
        }
    }

}
