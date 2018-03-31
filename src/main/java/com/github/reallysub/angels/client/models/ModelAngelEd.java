package com.github.reallysub.angels.client.models;

import com.github.reallysub.angels.common.entities.EntityAngel;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

public class ModelAngelEd extends ModelBase {
	
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
		this.back_cloth_2.setRotationPoint(0.0F, 12.0F, 2.0F);
		this.back_cloth_2.addBox(-3.0F, 0.0F, -3.0F, 6, 12, 3, 0.25F);
		this.setRotateAngle(back_cloth_2, 0.2792526803190927F, 0.0F, 0.0F);
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
		this.right_wing_2.addChild(this.right_wing_3);
		this.head.addChild(this.right_eyebrow);
		this.left_wing_1.addChild(this.left_wing_2);
		this.angry_mouth.addChild(this.teeth);
		this.left_wing_2.addChild(this.left_wing_3);
		this.right_arm.addChild(this.right_arm_1);
	}
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		setRotationAngles(f,f1,f2,f3,f4,f5,entity);

        EntityAngel angel = null;

        if (entity instanceof EntityAngel) {
            angel = (EntityAngel) entity;
        }

		this.cloth_1.render(f5);
		this.head.render(f5);
		this.cloth_0.render(f5);
		this.back_cloth.render(f5);
		this.cloth_2.render(f5);
		this.left_wing_1.render(f5);
		this.head_2.render(f5);
		this.back_cloth_2.render(f5);
		this.body_2.render(f5);
		this.right_wing_1.render(f5);
		this.body.render(f5);
		this.right_wing_0.render(f5);
		this.left_wing_0.render(f5);

        GlStateManager.pushMatrix();
        if(angel.isAngry()) {
            GlStateManager.translate(0,0.1,0);
            GlStateManager.rotate(-65, 1, 0, 0);
        }
		this.left_arm.render(f5);
		GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        if(angel.isAngry()) {
            GlStateManager.translate(0,0.1,0);
            GlStateManager.rotate(-65, 1, 0, 0);
        }
        this.right_arm.render(f5);
        GlStateManager.popMatrix();

	}
	
	@SuppressWarnings("incomplete-switch")
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		EntityAngel angel = null;
		
		if (entityIn instanceof EntityAngel) {
			angel = (EntityAngel) entityIn;
		}
		
		if (angel.isAngry()) {
			right_eyebrow.rotateAngleZ = (float) (20 * Math.PI / 180);
			left_eyebrow.rotateAngleZ = (float) (-20 * Math.PI / 180);
			angry_mouth.isHidden = false;
		} else {
			angry_mouth.isHidden = true;
			right_eyebrow.rotateAngleZ = (float) (0 * Math.PI / 180);
			left_eyebrow.rotateAngleZ = (float) (0 * Math.PI / 180);
		}
	}
	
	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
}
