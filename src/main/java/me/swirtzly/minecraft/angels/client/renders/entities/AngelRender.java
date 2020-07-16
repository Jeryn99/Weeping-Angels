package me.swirtzly.minecraft.angels.client.renders.entities;

import javax.annotation.Nullable;

import com.mojang.blaze3d.platform.GlStateManager;

import me.swirtzly.minecraft.angels.WeepingAngels;
import me.swirtzly.minecraft.angels.client.models.entity.ModelAngel;
import me.swirtzly.minecraft.angels.client.models.entity.ModelAngelChild;
import me.swirtzly.minecraft.angels.client.models.entity.ModelAngelEd;
import me.swirtzly.minecraft.angels.client.models.entity.ModelAngelMel;
import me.swirtzly.minecraft.angels.client.models.entity.ModelAngela;
import me.swirtzly.minecraft.angels.client.models.entity.ModelClassicAngel;
import me.swirtzly.minecraft.angels.client.renders.entities.layers.CrackLayer;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

@SuppressWarnings("deprecation")
public class AngelRender extends MobRenderer<MobEntity, EntityModel<MobEntity>> {

    public static ResourceLocation TEXTURE_THREE = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_4.png");
    private final ResourceLocation TEXTURE_ZERO = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel.png");
    private final ResourceLocation TEXTURE_ONE = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_2.png");

    private final ResourceLocation TEXTURE_CLASSIC = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_3.png");

    private final ResourceLocation TEXTURE_CHILD = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_child.png");
    private final ResourceLocation TEXTURE_ANGELA = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_angela.png");

    private final EntityModel<WeepingAngelEntity> modelOne = new ModelAngel<WeepingAngelEntity>();
    private final EntityModel<WeepingAngelEntity> modelTwo = new ModelAngelEd<WeepingAngelEntity>();
    private final EntityModel<WeepingAngelEntity> modelChild = new ModelAngelChild<WeepingAngelEntity>();
    private final EntityModel<WeepingAngelEntity> modelClassic = new ModelClassicAngel<WeepingAngelEntity>();
    private final EntityModel<WeepingAngelEntity> modelMel = new ModelAngelMel<WeepingAngelEntity>();
    private final EntityModel<WeepingAngelEntity> modelAngela = new ModelAngela<WeepingAngelEntity>();

    
    public AngelRender(EntityRendererManager manager) {
        super(manager, new ModelAngelEd<MobEntity>(), 0.0F);
        addLayer(new CrackLayer(this));
    }
    
    @Nullable
	@Override
	protected ResourceLocation getEntityTexture(MobEntity entity) {
		return null;
	}
	
	@Override
	protected void renderModel(MobEntity living, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
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
                    bindTexture(TEXTURE_CHILD); //Child
                    modelChild.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                    break;
                case 0:
                    bindTexture(TEXTURE_ZERO); //Angel 0
                    modelOne.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                    break;
                case 1:
                    bindTexture(TEXTURE_ONE); //Angel 1
					modelTwo.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
					break;
				case 2:
					bindTexture(TEXTURE_CLASSIC); //Angel 2 A_dizzle
                    modelClassic.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                    break;
                case 3:
                    bindTexture(TEXTURE_THREE);// Angel 3
                    modelMel.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                    break;
                case 4:
                    bindTexture(TEXTURE_ANGELA); //Angel 4
                    modelAngela.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
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
