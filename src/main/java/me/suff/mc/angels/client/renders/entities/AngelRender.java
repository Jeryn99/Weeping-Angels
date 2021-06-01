package me.suff.mc.angels.client.renders.entities;

import com.mojang.blaze3d.platform.GlStateManager;
import me.suff.mc.angels.client.models.entity.*;
import me.suff.mc.angels.client.renders.entities.layers.CrackLayer;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nullable;

public class AngelRender extends MobRenderer<WeepingAngelEntity, EntityModel< WeepingAngelEntity > > {

    private final EntityModel< WeepingAngelEntity > modelOne = new ModelAngel< WeepingAngelEntity >();
    private final EntityModel< WeepingAngelEntity > modelTwo = new ModelAngelEd< WeepingAngelEntity >();
    private final EntityModel< WeepingAngelEntity > modelChild = new ModelAngelChild< WeepingAngelEntity >();
    private final EntityModel< WeepingAngelEntity > modelClassic = new ModelClassicAngel< WeepingAngelEntity >();
    private final EntityModel< WeepingAngelEntity > modelMel = new ModelAngelMel< WeepingAngelEntity >();
    private final EntityModel< WeepingAngelEntity > modelAngela = new ModelAngela< WeepingAngelEntity >();
    private final EntityModel< WeepingAngelEntity > modelAngela2 = new ModelAngelaAngel();

    @SuppressWarnings("unchecked")
    public AngelRender(EntityRendererManager manager) {
        super(manager, new ModelAngelEd<>(), 0.0F);
        addLayer(new CrackLayer(this));
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(WeepingAngelEntity entity) {
        return null;
    }

    @Override
    protected boolean setBrightness(WeepingAngelEntity entitylivingbaseIn, float partialTicks,
                                    boolean combineTextures) {
        return true;
    }

    @Override
    protected void renderModel(WeepingAngelEntity living, float limbSwing, float limbSwingAmount, float ageInTicks,
                               float netHeadYaw, float headPitch, float scaleFactor) {
        if (living instanceof WeepingAngelEntity) {

            WeepingAngelEntity angel = (WeepingAngelEntity) living;
            ItemStack key = angel.getHeldItemMainhand();

            GlStateManager.pushMatrix();

            // Render key
            GlStateManager.pushMatrix();
            float offset = MathHelper.cos(living.ticksExisted * 0.1F) * -0.09F;
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
            GlStateManager.translated(0, -2, 0);
            GlStateManager.translated(0, offset, 0);
            renderItem(angel, key, ItemCameraTransforms.TransformType.FIXED);
            GlStateManager.popMatrix();

            switch (angel.getAngelType()) {
                case -1:
                    bindTexture(getTexture(modelChild, angel));
                    modelChild.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                    break;
                case 0:
                    bindTexture(getTexture(modelOne, angel));
                    modelOne.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                    break;
                case 1:
                    bindTexture(getTexture(modelTwo, angel));
                    modelTwo.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                    break;
                case 2:
                    bindTexture(getTexture(modelClassic, angel));
                    modelClassic.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                    break;
                case 3:
                    bindTexture(getTexture(modelMel, angel));
                    modelMel.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                    break;
                case 4:
                    bindTexture(getTexture(modelAngela, angel));
                    modelAngela.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                    break;
                case 5:
                    bindTexture(getTexture(modelAngela2, angel));
                    modelAngela2.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                    break;
            }
            GlStateManager.popMatrix();
        }
    }

    public ResourceLocation getTexture(EntityModel< WeepingAngelEntity > model, WeepingAngelEntity entity) {
        IAngelModel iAngelModel = (IAngelModel) model;
        return iAngelModel.getTextureForPose(entity);
    }

    private void renderItem(LivingEntity p_188358_1_, ItemStack p_188358_2_,
                            ItemCameraTransforms.TransformType p_188358_3_) {
        if (!p_188358_2_.isEmpty()) {
            Minecraft.getInstance().getFirstPersonRenderer().renderItemSide(p_188358_1_, p_188358_2_, p_188358_3_,
                    false);
        }
    }

}
