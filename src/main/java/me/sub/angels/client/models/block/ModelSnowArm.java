package me.sub.angels.client.models.block;

import com.mojang.blaze3d.platform.GlStateManager;
import me.sub.angels.WeepingAngels;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.Identifier;

public class ModelSnowArm extends EntityModel
{

    private Identifier ANGEL_TEXTURE = new Identifier(WeepingAngels.MODID, "textures/entities/angel_2.png");

    private Cuboid left_arm;
	private Cuboid left_arm_wrist;
	
	public ModelSnowArm() {
		textureWidth = 88;
		textureHeight = 88;
		left_arm = new Cuboid(this, 32, 27);
		left_arm.setRotationPoint(-1.2F, 22.1F, -0.1F);
		left_arm.addBox(-1.0F, -2.0F, -2.0F, 3, 6, 4, 0.0F);
		setRotation(left_arm, 0.0F, 0.4553564018453205F, 2.9595548126067843F);
		left_arm_wrist = new Cuboid(this, 34, 52);
		left_arm_wrist.setRotationPoint(0.0F, 4.0F, 2.0F);
		left_arm_wrist.addBox(-1.0F, 0.0F, -4.0F, 3, 6, 4, 0.0F);
		setRotation(left_arm_wrist, -0.5235987755982988F, 0.0F, 0.0F);
		left_arm.addChild(left_arm_wrist);
	}
	
	public void render(float scale) {
		GlStateManager.pushMatrix();
		MinecraftClient.getInstance().getTextureManager().bindTexture(ANGEL_TEXTURE);
		left_arm.render(scale);
		GlStateManager.popMatrix();
	}
	
	private void setRotation(Cuboid model, float x, float y, float z) {
		model.pitch = x;
		model.yaw = y;
		model.roll = z;
	}
	
}
