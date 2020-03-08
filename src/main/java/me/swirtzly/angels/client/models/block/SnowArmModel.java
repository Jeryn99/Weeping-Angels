package me.swirtzly.angels.client.models.block;

import com.mojang.blaze3d.platform.GlStateManager;
import me.swirtzly.angels.WeepingAngels;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.util.ResourceLocation;

public class SnowArmModel extends Model {
	
	private ResourceLocation ANGEL_TEXTURE = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_2.png");
	
	private RendererModel left_arm, left_arm_wrist;
	
	public SnowArmModel() {
		textureWidth = 88;
		textureHeight = 88;
		left_arm = new RendererModel(this, 32, 27);
		left_arm.setRotationPoint(-1.2F, 22.1F, -0.1F);
		left_arm.addBox(-1.0F, -2.0F, -2.0F, 3, 6, 4, 0.0F);
		setRotation(left_arm, 0.0F, 0.4553564018453205F, 2.9595548126067843F);
		left_arm_wrist = new RendererModel(this, 34, 52);
		left_arm_wrist.setRotationPoint(0.0F, 4.0F, 2.0F);
		left_arm_wrist.addBox(-1.0F, 0.0F, -4.0F, 3, 6, 4, 0.0F);
		setRotation(left_arm_wrist, -0.5235987755982988F, 0.0F, 0.0F);
		left_arm.addChild(left_arm_wrist);
	}
	
	public void render(float scale) {
		GlStateManager.pushMatrix();
		Minecraft.getInstance().getTextureManager().bindTexture(ANGEL_TEXTURE);
		left_arm.render(scale);
		GlStateManager.popMatrix();
	}
	
	private void setRotation(RendererModel model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
}
