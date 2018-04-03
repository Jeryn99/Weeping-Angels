package com.github.reallysub.angels.client.models;

import org.lwjgl.opengl.GL11;

import com.github.reallysub.angels.common.entities.EntityAngel;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

/**
 * Weeping Angel - EdusgprNetwork Created using Tabula 5.1.0
 */
public class ModelAngelEd extends ModelBiped {
	
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
		this.textureWidth = 88;
		this.textureHeight = 88;
		this.teeth_1 = new ModelRenderer(this, 63, 39);
		this.teeth_1.setRotationPoint(0.8F, -3.0F, 0.0F);
		this.teeth_1.addBox(-1.0F, 0.0F, -0.03F, 1, 1, 1, 0.0F);
		this.setRotateAngle(teeth_1, 0.0F, 0.0F, -0.7853981633974483F);
		this.left_arm_1 = new ModelRenderer(this, 34, 52);
		this.left_arm_1.setRotationPoint(0.0F, 4.0F, 2.0F);
		this.left_arm_1.addBox(-1.0F, 0.0F, -4.0F, 3, 6, 4, 0.0F);
		this.cloth_1 = new ModelRenderer(this, 34, 44);
		this.cloth_1.setRotationPoint(0.0F, 24.0F, 3.3F);
		this.cloth_1.addBox(-4.0F, -1.0F, -2.5F, 8, 1, 5, 0.0F);
		this.left_wing_4 = new ModelRenderer(this, 22, 36);
		this.left_wing_4.setRotationPoint(0.0F, 5.0F, 0.0F);
		this.left_wing_4.addBox(-1.0F, 0.0F, -2.0F, 2, 5, 2, 0.0F);
		this.setRotateAngle(left_wing_4, -1.1383037381507017F, 0.0F, 0.0F);
		this.teeth_3 = new ModelRenderer(this, 63, 39);
		this.teeth_3.setRotationPoint(-0.5F, -3.0F, 0.0F);
		this.teeth_3.addBox(-1.0F, 0.0F, -0.03F, 1, 1, 1, 0.0F);
		this.setRotateAngle(teeth_3, 0.0F, 0.0F, -0.7853981633974483F);
		this.right_arm = new ModelRenderer(this, 0, 32);
		this.right_arm.setRotationPoint(-5.0F, 2.5F, 0.0F);
		this.right_arm.addBox(-2.0F, -2.0F, -2.0F, 3, 6, 4, 0.0F);
		this.setRotateAngle(right_arm, 0.0F, 0.0F, 0.10000736613927509F);
		this.head = new ModelRenderer(this, 0, 16);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		this.cloth_0 = new ModelRenderer(this, 9, 43);
		this.cloth_0.setRotationPoint(0.0F, 24.0F, 0.0F);
		this.cloth_0.addBox(-5.0F, -1.0F, -2.0F, 10, 1, 5, 0.0F);
		this.teeth_2 = new ModelRenderer(this, 63, 39);
		this.teeth_2.setRotationPoint(0.5F, -3.0F, 0.0F);
		this.teeth_2.addBox(-1.0F, 0.0F, -0.03F, 1, 1, 1, 0.0F);
		this.setRotateAngle(teeth_2, 0.0F, 0.0F, -0.7853981633974483F);
		this.nose = new ModelRenderer(this, 32, 0);
		this.nose.setRotationPoint(0.0F, -4.5F, -4.0F);
		this.nose.addBox(-0.5F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
		this.setRotateAngle(nose, -0.2246238747316702F, 0.0F, 0.0F);
		this.coverup = new ModelRenderer(this, 54, 34);
		this.coverup.setRotationPoint(0.0F, -6.0F, -4.0F);
		this.coverup.addBox(-4.0F, 0.0F, -0.03F, 8, 1, 1, 0.0F);
		this.face = new ModelRenderer(this, 54, 28);
		this.face.setRotationPoint(0.0F, -4.5F, -4.0F);
		this.face.addBox(-3.0F, 0.0F, -0.01F, 6, 3, 1, 0.0F);
		this.back_cloth = new ModelRenderer(this, 60, 44);
		this.back_cloth.setRotationPoint(0.0F, 12.0F, 2.0F);
		this.back_cloth.addBox(-3.0F, 0.0F, -3.0F, 6, 12, 3, 0.0F);
		this.setRotateAngle(back_cloth, 0.2792526803190927F, 0.0F, 0.0F);
		this.teeth_5 = new ModelRenderer(this, 63, 39);
		this.teeth_5.setRotationPoint(0.15F, -3.0F, 0.0F);
		this.teeth_5.addBox(-1.0F, 0.0F, -0.03F, 1, 1, 1, 0.0F);
		this.setRotateAngle(teeth_5, 0.0F, 0.0F, -0.7853981633974483F);
		this.cloth_2 = new ModelRenderer(this, 10, 32);
		this.cloth_2.setRotationPoint(0.0F, 24.0F, 6.6F);
		this.cloth_2.addBox(-3.0F, -1.0F, -2.5F, 6, 1, 3, 0.0F);
		this.left_wing_1 = new ModelRenderer(this, 24, 0);
		this.left_wing_1.setRotationPoint(2.4F, 2.0F, 1.5F);
		this.left_wing_1.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
		this.setRotateAngle(left_wing_1, 1.53588974175501F, 0.9424777960769379F, 0.0F);
		this.head_2 = new ModelRenderer(this, 0, 0);
		this.head_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head_2.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
		this.right_wing_2 = new ModelRenderer(this, 46, 27);
		this.right_wing_2.setRotationPoint(0.0F, 4.0F, -1.0F);
		this.right_wing_2.addBox(-1.0F, 0.0F, 0.0F, 2, 7, 2, 0.0F);
		this.setRotateAngle(right_wing_2, 1.2292353921796064F, 0.0F, 0.0F);
		this.zeth = new ModelRenderer(this, 20, 50);
		this.zeth.setRotationPoint(0.0F, 0.0F, -2.0F);
		this.zeth.addBox(-4.5F, -1.0F, -0.6F, 9, 1, 1, 0.0F);
		this.back_cloth_2 = new ModelRenderer(this, 0, 49);
		this.back_cloth_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.back_cloth_2.addBox(-3.0F, 0.0F, -3.0F, 6, 12, 3, 0.25F);
		this.setRotateAngle(back_cloth_2, 0.0F, 0.0F, 0.0F);
		this.teeth_4 = new ModelRenderer(this, 63, 39);
		this.teeth_4.setRotationPoint(-0.15F, -3.0F, 0.0F);
		this.teeth_4.addBox(-1.0F, 0.0F, -0.03F, 1, 1, 1, 0.0F);
		this.setRotateAngle(teeth_4, 0.0F, 0.0F, -0.7853981633974483F);
		this.right_wing_4 = new ModelRenderer(this, 24, 16);
		this.right_wing_4.setRotationPoint(0.0F, 5.0F, 0.0F);
		this.right_wing_4.addBox(-1.0F, 0.0F, -2.0F, 2, 5, 2, 0.0F);
		this.setRotateAngle(right_wing_4, -1.1383037381507017F, 0.0F, 0.0F);
		this.body_2 = new ModelRenderer(this, 32, 0);
		this.body_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body_2.addBox(-4.0F, 0.0F, -2.0F, 8, 23, 4, 0.25F);
		this.right_wing_1 = new ModelRenderer(this, 0, 0);
		this.right_wing_1.setRotationPoint(-2.4F, 2.0F, 1.5F);
		this.right_wing_1.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
		this.setRotateAngle(right_wing_1, 1.53588974175501F, -0.9424777960769379F, 0.0F);
		this.left_eyebrow = new ModelRenderer(this, 62, 32);
		this.left_eyebrow.setRotationPoint(3.0F, -4.5F, -4.0F);
		this.left_eyebrow.addBox(-2.5F, -1.0F, -0.02F, 3, 1, 1, 0.0F);
		this.angry_mouth = new ModelRenderer(this, 63, 36);
		this.angry_mouth.setRotationPoint(0.0F, 0.0F, -4.0F);
		this.angry_mouth.addBox(-2.0F, -1.8F, -0.02F, 4, 2, 1, 0.0F);
		this.right_wing_3 = new ModelRenderer(this, 0, 16);
		this.right_wing_3.setRotationPoint(0.0F, 7.0F, 2.0F);
		this.right_wing_3.addBox(-1.0F, 0.0F, -2.0F, 2, 5, 2, 0.0F);
		this.setRotateAngle(right_wing_3, -1.2292353921796064F, 0.0F, 0.0F);
		this.right_eyebrow = new ModelRenderer(this, 54, 32);
		this.right_eyebrow.setRotationPoint(-3.0F, -4.5F, -4.0F);
		this.right_eyebrow.addBox(-0.5F, -1.0F, -0.02F, 3, 1, 1, 0.0F);
		this.left_wing_2 = new ModelRenderer(this, 78, 42);
		this.left_wing_2.setRotationPoint(0.0F, 4.0F, -1.0F);
		this.left_wing_2.addBox(-1.0F, 0.0F, 0.0F, 2, 7, 2, 0.0F);
		this.setRotateAngle(left_wing_2, 1.2292353921796064F, 0.0F, 0.0F);
		this.body = new ModelRenderer(this, 56, 0);
		this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 24, 4, 0.0F);
		this.right_wing_0 = new ModelRenderer(this, 0, 49);
		this.right_wing_0.setRotationPoint(-2.4F, 2.0F, 1.5F);
		this.right_wing_0.addBox(0.0F, 0.0F, -13.0F, 1, 11, 18, 0.0F);
		this.setRotateAngle(right_wing_0, 1.53588974175501F, -0.9424777960769379F, 0.0F);
		this.teeth = new ModelRenderer(this, 63, 39);
		this.teeth.setRotationPoint(-0.8F, -3.0F, 0.0F);
		this.teeth.addBox(-1.0F, 0.0F, -0.03F, 1, 1, 1, 0.0F);
		this.setRotateAngle(teeth, 0.0F, 0.0F, -0.7853981633974483F);
		this.left_wing_0 = new ModelRenderer(this, 38, 50);
		this.left_wing_0.setRotationPoint(2.4F, 2.0F, 1.5F);
		this.left_wing_0.addBox(-1.0F, 0.0F, -13.0F, 1, 11, 18, 0.0F);
		this.setRotateAngle(left_wing_0, 1.53588974175501F, 0.9424777960769379F, 0.0F);
		this.left_wing_3 = new ModelRenderer(this, 14, 36);
		this.left_wing_3.setRotationPoint(0.0F, 7.0F, 2.0F);
		this.left_wing_3.addBox(-1.0F, 0.0F, -2.0F, 2, 5, 2, 0.0F);
		this.setRotateAngle(left_wing_3, -1.2292353921796064F, 0.0F, 0.0F);
		this.left_arm = new ModelRenderer(this, 32, 27);
		this.left_arm.setRotationPoint(5.0F, 2.5F, -0.0F);
		this.left_arm.addBox(-1.0F, -2.0F, -2.0F, 3, 6, 4, 0.0F);
		this.setRotateAngle(left_arm, 0.0F, 0.0F, -0.10000736613927509F);
		this.right_arm_1 = new ModelRenderer(this, 20, 52);
		this.right_arm_1.setRotationPoint(0.0F, 4.0F, 2.0F);
		this.right_arm_1.addBox(-2.0F, 0.0F, -4.0F, 3, 6, 4, 0.0F);
		this.angry_mouth.addChild(this.teeth_1);
		this.left_arm.addChild(this.left_arm_1);
		this.left_wing_3.addChild(this.left_wing_4);
		this.angry_mouth.addChild(this.teeth_3);
		this.angry_mouth.addChild(this.teeth_2);
		this.head.addChild(this.nose);
		this.head.addChild(this.coverup);
		this.head.addChild(this.face);
		this.angry_mouth.addChild(this.teeth_5);
		this.right_wing_1.addChild(this.right_wing_2);
		this.cloth_0.addChild(this.zeth);
		this.angry_mouth.addChild(this.teeth_4);
		this.right_wing_3.addChild(this.right_wing_4);
		this.head.addChild(this.left_eyebrow);
		this.head.addChild(this.angry_mouth);
		this.right_wing_2.addChild(this.right_wing_3);
		this.head.addChild(this.right_eyebrow);
		this.left_wing_1.addChild(this.left_wing_2);
		this.angry_mouth.addChild(this.teeth);
		this.left_wing_2.addChild(this.left_wing_3);
		this.right_arm.addChild(this.right_arm_1);
		this.head.addChild(this.head_2);
		this.back_cloth.addChild(this.back_cloth_2);
	}
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		GlStateManager.pushMatrix();
		GL11.glEnable(GL11.GL_CULL_FACE);
		GlStateManager.enableLighting();
		this.cloth_1.render(f5);
		this.right_arm.render(f5);
		this.head.render(f5);
		this.cloth_0.render(f5);
		this.back_cloth.render(f5);
		this.cloth_2.render(f5);
		this.left_wing_1.render(f5);
		this.body_2.render(f5);
		this.right_wing_1.render(f5);
		this.body.render(f5);
		this.right_wing_0.render(f5);
		this.left_wing_0.render(f5);
		this.left_arm.render(f5);
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
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float scaleFactor, Entity entity) {
		
		EntityAngel angel = null;
		if (entity instanceof EntityAngel) {
			angel = (EntityAngel) entity;
		}
		
		boolean hidfac;
		if (angel.isAngry()) {
			right_eyebrow.rotateAngleZ = (float) (20 * Math.PI / 180);
			left_eyebrow.rotateAngleZ = (float) (-20 * Math.PI / 180);
			angry_mouth.isHidden = false;
			
		} else {
			angry_mouth.isHidden = true;
			right_eyebrow.rotateAngleZ = (float) (0 * Math.PI / 180);
			left_eyebrow.rotateAngleZ = (float) (0 * Math.PI / 180);
		}
		
		boolean flag = entity instanceof EntityLivingBase && ((EntityLivingBase) entity).getTicksElytraFlying() > 4;
		
		this.head.rotateAngleY = netheadYaw * 0.017453292F;
		this.head.rotateAngleZ = netheadYaw * 0.005F;
		
		if (flag) {
			this.head.rotateAngleX = -((float) Math.PI / 4F);
			
		} else {
			this.head.rotateAngleX = headPitch * 0.017453292F + this.head.rotateAngleZ * netheadYaw * 0.005F + 0.112F;
		}
		
		if (angel.getSeenTime() == 1) {
			
			if (angel.isAngry()) {
				right_eyebrow.rotateAngleZ = (float) (20 * Math.PI / 180);
				left_eyebrow.rotateAngleZ = (float) (-20 * Math.PI / 180);
				angry_mouth.isHidden = false;
				hidfac = false;
				this.right_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount * 0.5F - 1.5707963268F;
				this.left_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F - 1.5707963268F;
			} else {
				angry_mouth.isHidden = true;
				right_eyebrow.rotateAngleZ = (float) (0 * Math.PI / 180);
				left_eyebrow.rotateAngleZ = (float) (0 * Math.PI / 180);
				hidfac = Math.random() >= 0.5F;
				this.right_arm.rotateAngleX = 0;
				this.left_arm.rotateAngleX = 0;
			}
			
			this.right_arm.rotateAngleZ = 0;
			this.left_arm.rotateAngleZ = 0;
			
			this.right_arm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
			this.left_arm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
			this.right_arm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
			this.left_arm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
			// 1.53588974175501F, 0.9424777960769379F, 0.0F
			
			this.right_wing_0.rotateAngleX = MathHelper.sin(ageInTicks * 0.067F) * 0.05F + 1.53588974175501F;
			this.right_wing_0.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount * 0.5F - 0.9424777960769379F;
			this.right_wing_0.rotateAngleZ = 0;
			
			this.left_wing_0.rotateAngleX = MathHelper.sin(ageInTicks * 0.067F) * 0.05F + 1.53588974175501F;
			this.left_wing_0.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount * 0.5F + 0.9424777960769379F;
			this.left_wing_0.rotateAngleZ = 0;
			this.left_wing_1.rotateAngleX = MathHelper.sin(ageInTicks * 0.067F) * 0.05F + 1.53588974175501F;
			this.left_wing_1.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount * 0.5F + 0.9424777960769379F;
			this.left_wing_1.rotateAngleZ = 0;
			if (hidfac) {
				this.head.rotateAngleX = 0.8F;
				this.head.rotateAngleY = 0.0F;
				this.head.rotateAngleZ = 0.0F;
				this.left_arm_1.rotateAngleX = -0.52F;
				this.left_arm.rotateAngleX = -1.85F;
				this.left_arm.rotateAngleY = 0.61F;
				this.left_arm.rotateAngleZ = -0.087F;
				this.right_arm_1.rotateAngleX = -0.52F;
				this.right_arm.rotateAngleX = -1.85F;
				this.right_arm.rotateAngleY = -0.61F;
				this.right_arm.rotateAngleZ = 0.087F;
				
			} else {
				this.left_arm_1.rotateAngleX = 0;
				this.left_arm.rotateAngleY = 0;
				this.left_arm.rotateAngleZ = 0;
				this.right_arm_1.rotateAngleX = 0;
				this.right_arm.rotateAngleY = 0;
				this.right_arm.rotateAngleZ = 0;
				
			}
			
			if (angel.getBreakPos().getY() > 0) {
				this.right_arm.rotateAngleX = -1.5707963268F;
				this.right_arm.rotateAngleY = -(float) (angel.rotationYaw * Math.PI / 180) - (float) Math.atan((angel.getBreakPos().getX() - angel.getPosition().getX()) / (Math.sqrt(Math.pow((angel.getBreakPos().getX() - angel.getPosition().getX()), 2) + Math.pow((angel.getBreakPos().getZ() - angel.getPosition().getZ()), 2))));
				this.right_arm.rotateAngleZ = 0;
			}
		}
		
		this.right_wing_1.rotateAngleX = this.right_wing_0.rotateAngleX;
		this.right_wing_1.rotateAngleY = this.right_wing_0.rotateAngleY;
		this.right_wing_1.rotateAngleZ = this.right_wing_0.rotateAngleZ;
		this.right_wing_1.rotationPointX = this.right_wing_0.rotationPointX;
		this.right_wing_1.rotationPointY = this.right_wing_0.rotationPointY;
		this.right_wing_1.rotationPointZ = this.right_wing_0.rotationPointZ;
		
		this.left_wing_0.rotateAngleX = this.right_wing_0.rotateAngleX;
		this.left_wing_0.rotateAngleY = -this.right_wing_0.rotateAngleY;
		this.left_wing_0.rotateAngleZ = this.right_wing_0.rotateAngleZ;
		this.left_wing_0.rotationPointX = -this.right_wing_0.rotationPointX;
		this.left_wing_0.rotationPointY = this.right_wing_0.rotationPointY;
		this.left_wing_0.rotationPointZ = this.right_wing_0.rotationPointZ;
		
		this.left_wing_1.rotateAngleX = this.left_wing_0.rotateAngleX;
		this.left_wing_1.rotateAngleY = this.left_wing_0.rotateAngleY;
		this.left_wing_1.rotateAngleZ = this.left_wing_0.rotateAngleZ;
		this.left_wing_1.rotationPointX = this.left_wing_0.rotationPointX;
		this.left_wing_1.rotationPointY = this.left_wing_0.rotationPointY;
		this.left_wing_1.rotationPointZ = this.left_wing_0.rotationPointZ;
	}
}
