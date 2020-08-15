package me.swirtzly.minecraft.angels.client.renders.entities;

import javax.annotation.Nullable;

import com.mojang.blaze3d.platform.GlStateManager;

import me.swirtzly.minecraft.angels.WeepingAngels;
import me.swirtzly.minecraft.angels.client.models.entity.ModelAngel;
import me.swirtzly.minecraft.angels.client.models.entity.ModelAngelChild;
import me.swirtzly.minecraft.angels.client.models.entity.ModelAngelEd;
import me.swirtzly.minecraft.angels.client.models.entity.ModelAngelMel;
import me.swirtzly.minecraft.angels.client.models.entity.ModelAngela;
import me.swirtzly.minecraft.angels.client.models.entity.ModelAngelaAngel;
import me.swirtzly.minecraft.angels.client.models.entity.ModelClassicAngel;
import me.swirtzly.minecraft.angels.client.renders.entities.layers.CrackLayer;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class AngelRender extends MobRenderer {

    public static ResourceLocation TEXTURE_FOUR = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_4.png");
    private final ResourceLocation TEXTURE_ONE = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel.png");
    private final ResourceLocation TEXTURE_TWO = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_2.png");
    private final ResourceLocation TEXTURE_CLASSIC = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_3.png");
    private final ResourceLocation TEXTURE_CHILD = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_child.png");
    private final ResourceLocation TEXTURE_ANGELA = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_angela.png");
    private final ResourceLocation TEXTURE_ANGELA2 = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_5.png");

    private final EntityModel<WeepingAngelEntity> modelOne = new ModelAngel<WeepingAngelEntity>();
    private final EntityModel<WeepingAngelEntity> modelTwo = new ModelAngelEd<WeepingAngelEntity>();
    private final EntityModel<WeepingAngelEntity> modelChild = new ModelAngelChild<WeepingAngelEntity>();
    private final EntityModel<WeepingAngelEntity> modelClassic = new ModelClassicAngel<WeepingAngelEntity>();
    private final EntityModel<WeepingAngelEntity> modelMel = new ModelAngelMel<WeepingAngelEntity>();
    private final EntityModel<WeepingAngelEntity> modelAngela = new ModelAngela<WeepingAngelEntity>();
    private final EntityModel<WeepingAngelEntity> modelAngela2 = new ModelAngelaAngel();

    @SuppressWarnings("unchecked")
    public AngelRender(EntityRendererManager manager) {
        super(manager, new ModelAngelEd<WeepingAngelEntity>(), 0.0F);
        addLayer(new CrackLayer(this));
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}
	
	@Override
	protected boolean setBrightness(LivingEntity entitylivingbaseIn, float partialTicks, boolean combineTextures) {
		return true;
	}
	
	@Override
	protected void renderModel(LivingEntity living, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		if (living instanceof WeepingAngelEntity) {
            GlStateManager.pushMatrix();
            WeepingAngelEntity angel = (WeepingAngelEntity) living;

            ItemStack key = angel.getHeldItemMainhand();
            GlStateManager.pushMatrix();
            float offset = MathHelper.cos(living.ticksExisted * 0.1F) * -0.09F;
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
            GlStateManager.translated(0, -2, 0);
            GlStateManager.translated(0, offset, 0);
            renderItem(angel, key, ItemCameraTransforms.TransformType.FIXED);
            GlStateManager.popMatrix();

            switch (angel.getAngelType()) {
                case -1:
                    bindTexture(TEXTURE_CHILD);
                    modelChild.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                    break;
                case 0:
                    bindTexture(TEXTURE_ONE);
                    modelOne.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                    break;
                case 1:
                    bindTexture(TEXTURE_TWO);
					modelTwo.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
					break;
				case 2:
					bindTexture(TEXTURE_CLASSIC);
                    modelClassic.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                    break;
                case 3:
                    bindTexture(TEXTURE_FOUR);
                    modelMel.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                    break;
                case 4:
                    bindTexture(TEXTURE_ANGELA);
                    modelAngela.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                    break;
                case 5:
                    bindTexture(TEXTURE_ANGELA2);
                    modelAngela2.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                    break;
            }
            GlStateManager.popMatrix();
        }
    }

    private void renderItem(LivingEntity p_188358_1_, ItemStack p_188358_2_, ItemCameraTransforms.TransformType p_188358_3_) {
        if (!p_188358_2_.isEmpty()) {
            Minecraft.getInstance().getFirstPersonRenderer().renderItemSide(p_188358_1_, p_188358_2_, p_188358_3_, false);
        }
    }

}
