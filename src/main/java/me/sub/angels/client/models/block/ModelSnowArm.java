package me.sub.angels.client.models.block;

import me.sub.angels.main.WeepingAngels;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class ModelSnowArm extends ModelBase {
	
	ResourceLocation TEX = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_2.png");
	
	ModelRenderer left_arm;
	ModelRenderer left_arm_1;
	
	public ModelSnowArm() {
		this.textureWidth = 88;
		this.textureHeight = 88;
		this.left_arm = new ModelRenderer(this, 32, 27);
		this.left_arm.setRotationPoint(-1.2F, 22.1F, -0.1F);
		this.left_arm.addBox(-1.0F, -2.0F, -2.0F, 3, 6, 4, 0.0F);
		this.setRotation(left_arm, 0.0F, 0.4553564018453205F, 2.9595548126067843F);
		this.left_arm_1 = new ModelRenderer(this, 34, 52);
		this.left_arm_1.setRotationPoint(0.0F, 4.0F, 2.0F);
		this.left_arm_1.addBox(-1.0F, 0.0F, -4.0F, 3, 6, 4, 0.0F);
		this.setRotation(left_arm_1, -0.5235987755982988F, 0.0F, 0.0F);
		this.left_arm.addChild(this.left_arm_1);
	}
	
	public void render(float scale) {
		GlStateManager.pushMatrix();
		Minecraft.getMinecraft().renderEngine.bindTexture(TEX);
		this.left_arm.render(scale);
		;
		GlStateManager.popMatrix();
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
}
