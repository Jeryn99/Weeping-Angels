package me.swirtzly.minecraft.angels.client.models.entity;

import com.mojang.blaze3d.platform.GlStateManager;
import me.swirtzly.minecraft.angels.client.poses.AngelPoses;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

import static java.lang.Math.toRadians;

/**
 * Angel Type: Child
 */
public class ModelAngelChild<T extends LivingEntity> extends BipedModel<T> {
	
	private RendererModel head_2;
	private RendererModel head;
	private RendererModel body;
	private RendererModel nose;
	private RendererModel mouthtop;
	private RendererModel right_wing_0;
	private RendererModel right_wing_0_1;
	private RendererModel left_arm;
	private RendererModel right_arm;
	private RendererModel left_leg;
	private RendererModel right_leg;
	private RendererModel right_wing_1;
	private RendererModel right_wing_2;
	private RendererModel right_wing_3;
	private RendererModel right_wing_4;
	private RendererModel right_wing_1_1;
	private RendererModel right_wing_2_1;
	private RendererModel right_wing_3_1;
	private RendererModel right_wing_4_1;
	/**
	 * Angel Type: Child
	 */
	public ModelAngelChild() {
		textureWidth = 64;
		textureHeight = 64;
		mouthtop = new RendererModel(this, 24, 0);
		mouthtop.setRotationPoint(0.0F, 0.0F, -4.0F);
		mouthtop.addBox(-3.5F, -2.6F, -0.01F, 7, 2, 1, 0.0F);
		left_arm = new RendererModel(this, 0, 48);
		left_arm.setRotationPoint(5.0F, 2.5F, -0.0F);
		left_arm.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, 0.0F);
		setRotateAngle(left_arm, 0.0F, 0.0F, -0.10000736613927509F);
		right_wing_3_1 = new RendererModel(this, 16, 32);
		right_wing_3_1.setRotationPoint(0.0F, 7.0F, 2.0F);
		right_wing_3_1.addBox(-1.0F, 0.0F, -2.0F, 2, 5, 2, 0.0F);
		setRotateAngle(right_wing_3_1, -1.2292353921796064F, 0.0F, 0.0F);
		right_wing_4_1 = new RendererModel(this, 16, 32);
		right_wing_4_1.setRotationPoint(0.0F, 5.0F, 0.0F);
		right_wing_4_1.addBox(-1.0F, 0.0F, -2.0F, 2, 5, 2, 0.0F);
		setRotateAngle(right_wing_4_1, -1.1383037381507017F, 0.0F, 0.0F);
		right_wing_0_1 = new RendererModel(this, 16, 23);
		right_wing_0_1.setRotationPoint(-2.4F, 2.0F, 1.5F);
		right_wing_0_1.addBox(0.0F, 0.0F, -13.0F, 0, 11, 18, 0.0F);
		setRotateAngle(right_wing_0_1, 1.53588974175501F, -0.9424777960769379F, 0.0F);
		right_wing_1 = new RendererModel(this, 16, 32);
		right_wing_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		right_wing_1.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
		right_wing_1_1 = new RendererModel(this, 16, 32);
		right_wing_1_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		right_wing_1_1.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
		right_arm = new RendererModel(this, 40, 16);
		right_arm.setRotationPoint(-5.0F, 2.5F, 0.0F);
		right_arm.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, 0.0F);
		setRotateAngle(right_arm, 0.0F, 0.0F, 0.10000736613927509F);
		right_wing_0 = new RendererModel(this, 16, 34);
		right_wing_0.setRotationPoint(2.4F, 2.0F, 1.5F);
		right_wing_0.addBox(0.0F, 0.0F, -13.0F, 0, 11, 18, 0.0F);
		setRotateAngle(right_wing_0, 1.53588974175501F, 0.9424777960769379F, 0.0F);
		left_leg = new RendererModel(this, 0, 32);
		left_leg.setRotationPoint(2.0F, 12.0F, 0.1F);
		left_leg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		right_wing_4 = new RendererModel(this, 16, 32);
		right_wing_4.setRotationPoint(0.0F, 5.0F, 0.0F);
		right_wing_4.addBox(-1.0F, 0.0F, -2.0F, 2, 5, 2, 0.0F);
		setRotateAngle(right_wing_4, -1.1383037381507017F, 0.0F, 0.0F);
		head_2 = new RendererModel(this, 32, 0);
		head_2.setRotationPoint(0.0F, 7.2F, 0.0F);
		head_2.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
		head = new RendererModel(this, 0, 0);
		head.setRotationPoint(0.0F, 7.2F, 0.0F);
		head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		body = new RendererModel(this, 16, 16);
		body.setRotationPoint(0.0F, 7.2F, 0.0F);
		body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
		nose = new RendererModel(this, 0, 0);
		nose.setRotationPoint(0.0F, -3.7F, -4.0F);
		nose.addBox(-0.5F, -0.3F, 0.0F, 1, 2, 1, 0.0F);
		setRotateAngle(nose, -0.40980330836826856F, 0.0F, 0.0F);
		right_leg = new RendererModel(this, 0, 16);
		right_leg.setRotationPoint(-2.0F, 12.0F, 0.1F);
		right_leg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		right_wing_2 = new RendererModel(this, 16, 32);
		right_wing_2.setRotationPoint(0.0F, 4.0F, -1.0F);
		right_wing_2.addBox(-1.0F, 0.0F, 0.0F, 2, 7, 2, 0.0F);
		setRotateAngle(right_wing_2, 1.2292353921796064F, 0.0F, 0.0F);
		right_wing_3 = new RendererModel(this, 16, 32);
		right_wing_3.setRotationPoint(0.0F, 7.0F, 2.0F);
		right_wing_3.addBox(-1.0F, 0.0F, -2.0F, 2, 5, 2, 0.0F);
		setRotateAngle(right_wing_3, -1.2292353921796064F, 0.0F, 0.0F);
		right_wing_2_1 = new RendererModel(this, 16, 32);
		right_wing_2_1.setRotationPoint(0.0F, 4.0F, -1.0F);
		right_wing_2_1.addBox(-1.0F, 0.0F, 0.0F, 2, 7, 2, 0.0F);
		setRotateAngle(right_wing_2_1, 1.2292353921796064F, 0.0F, 0.0F);
		head.addChild(mouthtop);
		body.addChild(left_arm);
		right_wing_2_1.addChild(right_wing_3_1);
		right_wing_3_1.addChild(right_wing_4_1);
		body.addChild(right_wing_0_1);
		right_wing_0.addChild(right_wing_1);
		right_wing_0_1.addChild(right_wing_1_1);
		body.addChild(right_arm);
		body.addChild(right_wing_0);
		body.addChild(left_leg);
		right_wing_3.addChild(right_wing_4);
		head.addChild(nose);
		body.addChild(right_leg);
		right_wing_1.addChild(right_wing_2);
		right_wing_2.addChild(right_wing_3);
		right_wing_1_1.addChild(right_wing_2_1);
	}
	
	@Override
	public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		
		if (entityIn instanceof WeepingAngelEntity) {
			WeepingAngelEntity angel = (WeepingAngelEntity) entityIn;
			if (angel.getSeenTime() == 5) {
				angelAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
			}
		}
		
		head_2.render(scale);
		head.render(scale);
		GlStateManager.pushMatrix();
		GlStateManager.enableCull();
		GlStateManager.translatef(body.offsetX, body.offsetY, body.offsetZ);
		GlStateManager.translatef(body.rotationPointX * scale, body.rotationPointY * scale, body.rotationPointZ * scale);
		GlStateManager.scaled(0.7D, 0.7D, 0.7D);
		GlStateManager.translatef(-body.offsetX, -body.offsetY, -body.offsetZ);
		GlStateManager.translatef(-body.rotationPointX * scale, -body.rotationPointY * scale, -body.rotationPointZ * scale);
		body.render(scale);
		GlStateManager.disableCull();
		GlStateManager.popMatrix();
	}
	
	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	public void angelAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float scaleFactor, Entity entity) {
		if (entity instanceof WeepingAngelEntity) {
			WeepingAngelEntity angel = (WeepingAngelEntity) entity;

			if (angel.getAngelPose().equals(AngelPoses.POSE_ANGRY.getRegistryName().toString())) {
				float f6 = MathHelper.sin(angel.ticksExisted / 50 * 3.141593F);
				right_arm.rotateAngleZ = 0.0F;
				left_arm.rotateAngleZ = 0.0F;
				right_arm.rotateAngleY = -(0.1F - f6 * 0.6F);
				left_arm.rotateAngleY = 0.1F - f6 * 0.6F;
				right_arm.rotateAngleX = -1.570796F;
				left_arm.rotateAngleX = -1.570796F;
			}

			if (angel.getAngelPose().equals(AngelPoses.POSE_HIDING_FACE.getRegistryName())) {
				right_arm.rotateAngleX = -1.04533F;
				right_arm.rotateAngleY = -0.55851F;
				right_arm.rotateAngleZ = 0.0F;
				left_arm.rotateAngleX = -1.04533F;
				left_arm.rotateAngleY = 0.55851F;
				left_arm.rotateAngleZ = 0.0F;
			} else {
				right_arm.rotateAngleX = -1.74533F;
				right_arm.rotateAngleY = -0.55851F;
				right_arm.rotateAngleZ = 0.0F;
				left_arm.rotateAngleX = -1.74533F;
				left_arm.rotateAngleY = 0.55851F;
				left_arm.rotateAngleZ = 0.0F;
			}
		} else {
			right_arm.rotateAngleX = -1.74533F;
			right_arm.rotateAngleY = -0.55851F;
			right_arm.rotateAngleZ = 0.0F;
			left_arm.rotateAngleX = -1.74533F;
			left_arm.rotateAngleY = 0.55851F;
			left_arm.rotateAngleZ = 0.0F;
		}
	}
}
