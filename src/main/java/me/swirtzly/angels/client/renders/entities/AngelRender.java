package me.swirtzly.angels.client.renders.entities;

import com.mojang.blaze3d.platform.GlStateManager;
import me.swirtzly.angels.WeepingAngels;
import me.swirtzly.angels.client.models.entity.*;
import me.swirtzly.angels.client.renders.entities.layers.CrackLayer;
import me.swirtzly.angels.common.entities.WeepingAngelEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EnderDragonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.minecart.MinecartEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nullable;

public class AngelRender extends MobRenderer {
	
	public static ResourceLocation TEXTURE_FOUR = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_4.png");
	private ResourceLocation TEXTURE_ONE = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel.png");
	private ResourceLocation TEXTURE_TWO = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_2.png");
	
	private ResourceLocation TEXTURE_CLASSIC = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_3.png");
	
	private ResourceLocation TEXTURE_CHILD = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_child.png");
	private ResourceLocation TEXTURE_ANGELA = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_angela.png");
	
	private EntityModel<WeepingAngelEntity> modelOne = new ModelAngel<WeepingAngelEntity>();
	private EntityModel<WeepingAngelEntity> modelTwo = new ModelAngelEd<WeepingAngelEntity>();
	private EntityModel<WeepingAngelEntity> modelChild = new ModelAngelChild<WeepingAngelEntity>();
	private EntityModel<WeepingAngelEntity> modelClassic = new ModelClassicAngel<WeepingAngelEntity>();
	private EntityModel<WeepingAngelEntity> modelMel = new ModelAngelMel<WeepingAngelEntity>();
	private EntityModel<WeepingAngelEntity> modelAngela = new ModelAngela();
	
	@SuppressWarnings("unchecked")
	public AngelRender(EntityRendererManager manager) {
		super(manager, new ModelAngelEd<WeepingAngelEntity>(), 0.0F);
		addLayer(new CrackLayer(this));
		addLayer(new HeldItemLayer(this));
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

			float f1 = MathHelper.sin(living.world.getGameTime() * 0.2F) / 2.0F + 0.5F;
			BlockPos blockpos = living.getPosition().add(3,1, 3);
			if (blockpos != null) {
				this.bindTexture(EnderDragonRenderer.ENDERCRYSTAL_BEAM_TEXTURES);
				float f2 = (float)blockpos.getX() + 0.5F;
				float f3 = (float)blockpos.getY() + 0.5F;
				float f4 = (float)blockpos.getZ() + 0.5F;
				double d0 = (double)f2 - living.posX;
				double d1 = (double)f3 - living.posY;
				double d2 = (double)f4 - living.posZ;
				EnderDragonRenderer.renderCrystalBeams(angel.posX + d0, angel.posY - 0.3D + (double)(f1 * 0.4F) + d1, angel.posZ + d2, Minecraft.getInstance().getRenderPartialTicks(), (double)f2, (double)f3, (double)f4, (int) f1, living.posX, living.posY, living.posZ);
			}


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
			}
			GlStateManager.popMatrix();
			
		}
	}
	
}
