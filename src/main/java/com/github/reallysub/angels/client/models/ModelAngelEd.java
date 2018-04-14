package com.github.reallysub.angels.client.models;

import com.github.reallysub.angels.common.entities.enums.AngelPoses;
import org.lwjgl.opengl.GL11;

import com.github.reallysub.angels.common.entities.EntityAngel;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

/**
 * Weeping Angel - EdusgprNetwork Created using Tabula 5.1.0
 */
public class ModelAngelEd extends ModelBiped {
	
	private boolean hidfac;
	
	ModelRenderer right_wing_0;
	ModelRenderer left_wing_0;
	ModelRenderer back_cloth_2;
	ModelRenderer head_2;
	ModelRenderer body_2;
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer left_arm;
	ModelRenderer right_arm;
	ModelRenderer cloth_0;
	ModelRenderer cloth_1;
	ModelRenderer cloth_2;
	ModelRenderer back_cloth;
	ModelRenderer left_wing_1;
	ModelRenderer right_wing_1;
	ModelRenderer nose;
	ModelRenderer face;
	ModelRenderer right_eyebrow;
	ModelRenderer left_eyebrow;
	ModelRenderer coverup;
	ModelRenderer angry_mouth;
	ModelRenderer teeth;
	ModelRenderer teeth_1;
	ModelRenderer teeth_2;
	ModelRenderer teeth_3;
	ModelRenderer teeth_4;
	ModelRenderer teeth_5;
	ModelRenderer left_arm_1;
	ModelRenderer right_arm_1;
	ModelRenderer zeth;
	ModelRenderer left_wing_2;
	ModelRenderer left_wing_3;
	ModelRenderer left_wing_4;
	ModelRenderer right_wing_2;
	ModelRenderer right_wing_3;
	ModelRenderer right_wing_4;
	
	public ModelAngelEd() {
		textureWidth = 88;
		textureHeight = 88;
		teeth_1 = new ModelRenderer(this, 63, 39);
		teeth_1.setRotationPoint(0.8F, -3.0F, 0.0F);
		teeth_1.addBox(-1.0F, 0.0F, -0.03F, 1, 1, 1, 0.0F);
		setRotateAngle(teeth_1, 0.0F, 0.0F, -0.7853981633974483F);
		left_arm_1 = new ModelRenderer(this, 34, 52);
		left_arm_1.setRotationPoint(0.0F, 4.0F, 2.0F);
		left_arm_1.addBox(-1.0F, 0.0F, -4.0F, 3, 6, 4, 0.0F);
		cloth_1 = new ModelRenderer(this, 34, 44);
		cloth_1.setRotationPoint(0.0F, 24.0F, 3.3F);
		cloth_1.addBox(-4.0F, -1.0F, -2.5F, 8, 1, 5, 0.0F);
		left_wing_4 = new ModelRenderer(this, 22, 36);
		left_wing_4.setRotationPoint(0.0F, 5.0F, 0.0F);
		left_wing_4.addBox(-1.0F, 0.0F, -2.0F, 2, 5, 2, 0.0F);
		setRotateAngle(left_wing_4, -1.1383037381507017F, 0.0F, 0.0F);
		teeth_3 = new ModelRenderer(this, 63, 39);
		teeth_3.setRotationPoint(-0.5F, -3.0F, 0.0F);
		teeth_3.addBox(-1.0F, 0.0F, -0.03F, 1, 1, 1, 0.0F);
		setRotateAngle(teeth_3, 0.0F, 0.0F, -0.7853981633974483F);
		right_arm = new ModelRenderer(this, 0, 32);
		right_arm.setRotationPoint(-5.0F, 2.5F, 0.0F);
		right_arm.addBox(-2.0F, -2.0F, -2.0F, 3, 6, 4, 0.0F);
		setRotateAngle(right_arm, 0.0F, 0.0F, 0.10000736613927509F);
		head = new ModelRenderer(this, 0, 16);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		cloth_0 = new ModelRenderer(this, 9, 43);
		cloth_0.setRotationPoint(0.0F, 24.0F, 0.0F);
		cloth_0.addBox(-5.0F, -1.0F, -2.0F, 10, 1, 5, 0.0F);
		teeth_2 = new ModelRenderer(this, 63, 39);
		teeth_2.setRotationPoint(0.5F, -3.0F, 0.0F);
		teeth_2.addBox(-1.0F, 0.0F, -0.03F, 1, 1, 1, 0.0F);
		setRotateAngle(teeth_2, 0.0F, 0.0F, -0.7853981633974483F);
		nose = new ModelRenderer(this, 32, 0);
		nose.setRotationPoint(0.0F, -4.5F, -4.0F);
		nose.addBox(-0.5F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
		setRotateAngle(nose, -0.2246238747316702F, 0.0F, 0.0F);
		coverup = new ModelRenderer(this, 54, 34);
		coverup.setRotationPoint(0.0F, -6.0F, -4.0F);
		coverup.addBox(-4.0F, 0.0F, -0.03F, 8, 1, 1, 0.0F);
		face = new ModelRenderer(this, 54, 28);
		face.setRotationPoint(0.0F, -4.5F, -4.0F);
		face.addBox(-3.0F, 0.0F, -0.01F, 6, 3, 1, 0.0F);
		back_cloth = new ModelRenderer(this, 60, 44);
		back_cloth.setRotationPoint(0.0F, 12.0F, 2.0F);
		back_cloth.addBox(-3.0F, 0.0F, -3.0F, 6, 12, 3, 0.0F);
		setRotateAngle(back_cloth, 0.2792526803190927F, 0.0F, 0.0F);
		teeth_5 = new ModelRenderer(this, 63, 39);
		teeth_5.setRotationPoint(0.15F, -3.0F, 0.0F);
		teeth_5.addBox(-1.0F, 0.0F, -0.03F, 1, 1, 1, 0.0F);
		setRotateAngle(teeth_5, 0.0F, 0.0F, -0.7853981633974483F);
		cloth_2 = new ModelRenderer(this, 10, 32);
		cloth_2.setRotationPoint(0.0F, 24.0F, 6.6F);
		cloth_2.addBox(-3.0F, -1.0F, -2.5F, 6, 1, 3, 0.0F);
		left_wing_1 = new ModelRenderer(this, 24, 0);
		left_wing_1.setRotationPoint(2.4F, 2.0F, 1.5F);
		left_wing_1.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
		setRotateAngle(left_wing_1, 1.53588974175501F, 0.9424777960769379F, 0.0F);
		head_2 = new ModelRenderer(this, 0, 0);
		head_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		head_2.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
		right_wing_2 = new ModelRenderer(this, 46, 27);
		right_wing_2.setRotationPoint(0.0F, 4.0F, -1.0F);
		right_wing_2.addBox(-1.0F, 0.0F, 0.0F, 2, 7, 2, 0.0F);
		setRotateAngle(right_wing_2, 1.2292353921796064F, 0.0F, 0.0F);
		zeth = new ModelRenderer(this, 20, 50);
		zeth.setRotationPoint(0.0F, 0.0F, -2.0F);
		zeth.addBox(-4.5F, -1.0F, -0.6F, 9, 1, 1, 0.0F);
		back_cloth_2 = new ModelRenderer(this, 0, 49);
		back_cloth_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		back_cloth_2.addBox(-3.0F, 0.0F, -3.0F, 6, 12, 3, 0.25F);
		setRotateAngle(back_cloth_2, 0.0F, 0.0F, 0.0F);
		teeth_4 = new ModelRenderer(this, 63, 39);
		teeth_4.setRotationPoint(-0.15F, -3.0F, 0.0F);
		teeth_4.addBox(-1.0F, 0.0F, -0.03F, 1, 1, 1, 0.0F);
		setRotateAngle(teeth_4, 0.0F, 0.0F, -0.7853981633974483F);
		right_wing_4 = new ModelRenderer(this, 24, 16);
		right_wing_4.setRotationPoint(0.0F, 5.0F, 0.0F);
		right_wing_4.addBox(-1.0F, 0.0F, -2.0F, 2, 5, 2, 0.0F);
		setRotateAngle(right_wing_4, -1.1383037381507017F, 0.0F, 0.0F);
		body_2 = new ModelRenderer(this, 32, 0);
		body_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		body_2.addBox(-4.0F, 0.0F, -2.0F, 8, 23, 4, 0.25F);
		right_wing_1 = new ModelRenderer(this, 0, 0);
		right_wing_1.setRotationPoint(-2.4F, 2.0F, 1.5F);
		right_wing_1.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
		setRotateAngle(right_wing_1, 1.53588974175501F, -0.9424777960769379F, 0.0F);
		left_eyebrow = new ModelRenderer(this, 62, 32);
		left_eyebrow.setRotationPoint(3.0F, -4.5F, -4.0F);
		left_eyebrow.addBox(-2.5F, -1.0F, -0.02F, 3, 1, 1, 0.0F);
		angry_mouth = new ModelRenderer(this, 63, 36);
		angry_mouth.setRotationPoint(0.0F, 0.0F, -4.0F);
		angry_mouth.addBox(-2.0F, -1.8F, -0.02F, 4, 2, 1, 0.0F);
		right_wing_3 = new ModelRenderer(this, 0, 16);
		right_wing_3.setRotationPoint(0.0F, 7.0F, 2.0F);
		right_wing_3.addBox(-1.0F, 0.0F, -2.0F, 2, 5, 2, 0.0F);
		setRotateAngle(right_wing_3, -1.2292353921796064F, 0.0F, 0.0F);
		right_eyebrow = new ModelRenderer(this, 54, 32);
		right_eyebrow.setRotationPoint(-3.0F, -4.5F, -4.0F);
		right_eyebrow.addBox(-0.5F, -1.0F, -0.02F, 3, 1, 1, 0.0F);
		left_wing_2 = new ModelRenderer(this, 78, 42);
		left_wing_2.setRotationPoint(0.0F, 4.0F, -1.0F);
		left_wing_2.addBox(-1.0F, 0.0F, 0.0F, 2, 7, 2, 0.0F);
		setRotateAngle(left_wing_2, 1.2292353921796064F, 0.0F, 0.0F);
		body = new ModelRenderer(this, 56, 0);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addBox(-4.0F, 0.0F, -2.0F, 8, 24, 4, 0.0F);
		right_wing_0 = new ModelRenderer(this, 0, 49);
		right_wing_0.setRotationPoint(-2.4F, 2.0F, 1.5F);
		right_wing_0.addBox(0.0F, 0.0F, -13.0F, 1, 11, 18, 0.0F);
		setRotateAngle(right_wing_0, 1.53588974175501F, -0.9424777960769379F, 0.0F);
		teeth = new ModelRenderer(this, 63, 39);
		teeth.setRotationPoint(-0.8F, -3.0F, 0.0F);
		teeth.addBox(-1.0F, 0.0F, -0.03F, 1, 1, 1, 0.0F);
		setRotateAngle(teeth, 0.0F, 0.0F, -0.7853981633974483F);
		left_wing_0 = new ModelRenderer(this, 38, 50);
		left_wing_0.setRotationPoint(2.4F, 2.0F, 1.5F);
		left_wing_0.addBox(-1.0F, 0.0F, -13.0F, 1, 11, 18, 0.0F);
		setRotateAngle(left_wing_0, 1.53588974175501F, 0.9424777960769379F, 0.0F);
		left_wing_3 = new ModelRenderer(this, 14, 36);
		left_wing_3.setRotationPoint(0.0F, 7.0F, 2.0F);
		left_wing_3.addBox(-1.0F, 0.0F, -2.0F, 2, 5, 2, 0.0F);
		setRotateAngle(left_wing_3, -1.2292353921796064F, 0.0F, 0.0F);
		left_arm = new ModelRenderer(this, 32, 27);
		left_arm.setRotationPoint(5.0F, 2.5F, -0.0F);
		left_arm.addBox(-1.0F, -2.0F, -2.0F, 3, 6, 4, 0.0F);
		setRotateAngle(left_arm, 0.0F, 0.0F, -0.10000736613927509F);
		right_arm_1 = new ModelRenderer(this, 20, 52);
		right_arm_1.setRotationPoint(0.0F, 4.0F, 2.0F);
		right_arm_1.addBox(-2.0F, 0.0F, -4.0F, 3, 6, 4, 0.0F);
		angry_mouth.addChild(teeth_1);
		left_arm.addChild(left_arm_1);
		left_wing_3.addChild(left_wing_4);
		angry_mouth.addChild(teeth_3);
		angry_mouth.addChild(teeth_2);
		head.addChild(nose);
		head.addChild(coverup);
		head.addChild(face);
		angry_mouth.addChild(teeth_5);
		right_wing_1.addChild(right_wing_2);
		cloth_0.addChild(zeth);
		angry_mouth.addChild(teeth_4);
		right_wing_3.addChild(right_wing_4);
		head.addChild(left_eyebrow);
		head.addChild(angry_mouth);
		right_wing_2.addChild(right_wing_3);
		head.addChild(right_eyebrow);
		left_wing_1.addChild(left_wing_2);
		angry_mouth.addChild(teeth);
		left_wing_2.addChild(left_wing_3);
		right_arm.addChild(right_arm_1);
		head.addChild(head_2);
		back_cloth.addChild(back_cloth_2);
	}
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		GlStateManager.pushMatrix();
		GL11.glEnable(GL11.GL_CULL_FACE);
		GlStateManager.enableLighting();
		cloth_1.render(f5);
		right_arm.render(f5);
		head.render(f5);
		cloth_0.render(f5);
		back_cloth.render(f5);
		cloth_2.render(f5);
		left_wing_1.render(f5);
		body_2.render(f5);
		right_wing_1.render(f5);
		body.render(f5);
		right_wing_0.render(f5);
		left_wing_0.render(f5);
		left_arm.render(f5);
		GlStateManager.disableLighting();
		GL11.glDisable(GL11.GL_CULL_FACE);
		GlStateManager.popMatrix();
	}
	
	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	@Override
	protected ModelRenderer getArmForSide(EnumHandSide side) {
		return side == EnumHandSide.LEFT ? left_arm : right_arm;
	}
	
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float scaleFactor, Entity entity) {
		
		EntityAngel angel = null;
		if (entity instanceof EntityAngel) {
			angel = (EntityAngel) entity;
		}
		
		if (angel.getPose().equals(AngelPoses.ANGERY.toString())) {
			right_eyebrow.rotateAngleZ = (float) (20 * Math.PI / 180);
			left_eyebrow.rotateAngleZ = (float) (-20 * Math.PI / 180);
			angry_mouth.isHidden = false;
			
		} else {
			angry_mouth.isHidden = true;
			right_eyebrow.rotateAngleZ = (float) (0 * Math.PI / 180);
			left_eyebrow.rotateAngleZ = (float) (0 * Math.PI / 180);
		}
		
		boolean flag = entity instanceof EntityLivingBase && ((EntityLivingBase) entity).getTicksElytraFlying() > 4;
		
		head.rotateAngleY = netheadYaw * 0.017453292F;
		head.rotateAngleZ = netheadYaw * 0.005F;
		
		if (flag) {
			head.rotateAngleX = -((float) Math.PI / 4F);
			
		} else {
			head.rotateAngleX = headPitch * 0.017453292F + head.rotateAngleZ * netheadYaw * 0.005F + 0.112F;
		}
		
		if (angel.getSeenTime() == 1) {
			
			if (angel.getPose().equals(AngelPoses.ANGERY.toString())) {
				right_eyebrow.rotateAngleZ = (float) (20 * Math.PI / 180);
				left_eyebrow.rotateAngleZ = (float) (-20 * Math.PI / 180);
				angry_mouth.isHidden = false;
				hidfac = false;
				right_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount * 0.5F - 1.5707963268F;
				left_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F - 1.5707963268F;
			} else {
				angry_mouth.isHidden = true;
				right_eyebrow.rotateAngleZ = (float) (0 * Math.PI / 180);
				left_eyebrow.rotateAngleZ = (float) (0 * Math.PI / 180);
				hidfac = Math.random() >= 0.5F;
				right_arm.rotateAngleX = 0;
				left_arm.rotateAngleX = 0;
			}
			
			right_arm.rotateAngleZ = 0;
			left_arm.rotateAngleZ = 0;
			
			right_arm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
			left_arm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
			right_arm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
			left_arm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
			// 1.53588974175501F, 0.9424777960769379F, 0.0F
			
			right_wing_0.rotateAngleX = MathHelper.sin(ageInTicks * 0.067F) * 0.05F + 1.53588974175501F;
			right_wing_0.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount * 0.5F - 0.9424777960769379F;
			right_wing_0.rotateAngleZ = 0;
			
			left_wing_0.rotateAngleX = MathHelper.sin(ageInTicks * 0.067F) * 0.05F + 1.53588974175501F;
			left_wing_0.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount * 0.5F + 0.9424777960769379F;
			left_wing_0.rotateAngleZ = 0;
			left_wing_1.rotateAngleX = MathHelper.sin(ageInTicks * 0.067F) * 0.05F + 1.53588974175501F;
			left_wing_1.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount * 0.5F + 0.9424777960769379F;
			left_wing_1.rotateAngleZ = 0;
			if (hidfac) {
				head.rotateAngleX = 0.8F;
				head.rotateAngleY = 0.0F;
				head.rotateAngleZ = 0.0F;
				left_arm_1.rotateAngleX = -0.52F;
				left_arm.rotateAngleX = -1.85F;
				left_arm.rotateAngleY = 0.61F;
				left_arm.rotateAngleZ = -0.087F;
				right_arm_1.rotateAngleX = -0.52F;
				right_arm.rotateAngleX = -1.85F;
				right_arm.rotateAngleY = -0.61F;
				right_arm.rotateAngleZ = 0.087F;
				
			} else {
				left_arm_1.rotateAngleX = 0;
				left_arm.rotateAngleY = 0;
				left_arm.rotateAngleZ = 0;
				right_arm_1.rotateAngleX = 0;
				right_arm.rotateAngleY = 0;
				right_arm.rotateAngleZ = 0;
				
			}
			
			if (angel.getBreakPos().getY() > 0) {
				right_arm.rotateAngleX = -1.5707963268F;
				right_arm.rotateAngleY = -(float) (angel.rotationYaw * Math.PI / 180) - (float) Math.atan((angel.getBreakPos().getX() - angel.getPosition().getX()) / (Math.sqrt(Math.pow((angel.getBreakPos().getX() - angel.getPosition().getX()), 2) + Math.pow((angel.getBreakPos().getZ() - angel.getPosition().getZ()), 2))));
				right_arm.rotateAngleZ = 0;
			}
		}
		
		right_wing_1.rotateAngleX = right_wing_0.rotateAngleX;
		right_wing_1.rotateAngleY = right_wing_0.rotateAngleY;
		right_wing_1.rotateAngleZ = right_wing_0.rotateAngleZ;
		right_wing_1.rotationPointX = right_wing_0.rotationPointX;
		right_wing_1.rotationPointY = right_wing_0.rotationPointY;
		right_wing_1.rotationPointZ = right_wing_0.rotationPointZ;
		
		left_wing_0.rotateAngleX = right_wing_0.rotateAngleX;
		left_wing_0.rotateAngleY = -right_wing_0.rotateAngleY;
		left_wing_0.rotateAngleZ = right_wing_0.rotateAngleZ;
		left_wing_0.rotationPointX = -right_wing_0.rotationPointX;
		left_wing_0.rotationPointY = right_wing_0.rotationPointY;
		left_wing_0.rotationPointZ = right_wing_0.rotationPointZ;
		
		left_wing_1.rotateAngleX = left_wing_0.rotateAngleX;
		left_wing_1.rotateAngleY = left_wing_0.rotateAngleY;
		left_wing_1.rotateAngleZ = left_wing_0.rotateAngleZ;
		left_wing_1.rotationPointX = left_wing_0.rotationPointX;
		left_wing_1.rotationPointY = left_wing_0.rotationPointY;
		left_wing_1.rotationPointZ = left_wing_0.rotationPointZ;
	}
}
