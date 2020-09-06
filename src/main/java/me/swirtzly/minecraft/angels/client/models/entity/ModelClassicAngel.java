package me.swirtzly.minecraft.angels.client.models.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import me.swirtzly.minecraft.angels.WeepingAngels;
import me.swirtzly.minecraft.angels.client.poses.AngelPoses;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;

/**
 * Angel Type: 2 - Classic
 */
public class ModelClassicAngel extends EntityModel<WeepingAngelEntity> implements IAngelModel {
	
	private final ResourceLocation TEXTURE = new ResourceLocation(WeepingAngels.MODID,
			"textures/entities/angel_3.png");
	
	private ModelRenderer leftfoot;
	private ModelRenderer rightfoot;
	private ModelRenderer leftwing1;
	private ModelRenderer leftwing2;
	private ModelRenderer leftwing3;
	private ModelRenderer leftwing4;
	private ModelRenderer rightwing1;
	private ModelRenderer rightwing2;
	private ModelRenderer rightwing3;
	private ModelRenderer rightwing4;
	private ModelRenderer head;
	private ModelRenderer body;
	private ModelRenderer rightarm;
	private ModelRenderer leftarm;
	private ModelRenderer rightleg;
	private ModelRenderer leftleg;

	private AngelPoses angelPoses = AngelPoses.POSE_ANGRY;

	@Override
	public AngelPoses getAngelPose() {
		return angelPoses;
	}

	@Override
	public void setAngelPose(AngelPoses angelPose) {
		this.angelPoses = angelPose;
	}

	/**
	 * Angel Type: 2 - Classic
	 */
	public ModelClassicAngel() {
		textureHeight = 32;
		textureWidth = 64;
		leftfoot = new ModelRenderer(this, 32, 0);
		leftfoot.addBox(-2F, 7F, -4F, 6, 5, 8);
		leftfoot.setRotationPoint(2.0F, 12F, 0.0F);
		leftfoot.rotateAngleX = 0.0F;
		leftfoot.rotateAngleY = 0.0F;
		leftfoot.rotateAngleZ = 0.0F;
		leftfoot.mirror = false;
		rightfoot = new ModelRenderer(this, 32, 0);
		rightfoot.addBox(-4F, 7F, -4F, 6, 5, 8);
		rightfoot.setRotationPoint(-2F, 12F, 0.0F);
		rightfoot.rotateAngleX = 0.0F;
		rightfoot.rotateAngleY = 0.0F;
		rightfoot.rotateAngleZ = 0.0F;
		rightfoot.mirror = false;
		leftwing1 = new ModelRenderer(this, 40, 25);
		leftwing1.addBox(-0.5F, -1F, 1.0F, 1, 5, 2);
		leftwing1.setRotationPoint(1.0F, 1.0F, 1.0F);
		leftwing1.rotateAngleX = 0.20944F;
		leftwing1.rotateAngleY = 0.61087F;
		leftwing1.rotateAngleZ = 0.0F;
		leftwing1.mirror = false;
		leftwing2 = new ModelRenderer(this, 46, 19);
		leftwing2.addBox(-0.5F, -2F, 3F, 1, 11, 2);
		leftwing2.setRotationPoint(1.0F, 1.0F, 1.0F);
		leftwing2.rotateAngleX = 0.20944F;
		leftwing2.rotateAngleY = 0.61087F;
		leftwing2.rotateAngleZ = 0.01745F;
		leftwing2.mirror = false;
		leftwing3 = new ModelRenderer(this, 58, 12);
		leftwing3.addBox(-0.5F, -2F, 5F, 1, 18, 2);
		leftwing3.setRotationPoint(1.0F, 1.0F, 1.0F);
		leftwing3.rotateAngleX = 0.20944F;
		leftwing3.rotateAngleY = 0.61087F;
		leftwing3.rotateAngleZ = 0.0F;
		leftwing3.mirror = false;
		leftwing4 = new ModelRenderer(this, 52, 16);
		leftwing4.addBox(-0.5F, 0.0F, 7F, 1, 14, 2);
		leftwing4.setRotationPoint(1.0F, 1.0F, 1.0F);
		leftwing4.rotateAngleX = 0.20944F;
		leftwing4.rotateAngleY = 0.61087F;
		leftwing4.rotateAngleZ = 0.0F;
		leftwing4.mirror = false;
		rightwing1 = new ModelRenderer(this, 40, 25);
		rightwing1.addBox(-0.5F, -1F, 1.0F, 1, 5, 2);
		rightwing1.setRotationPoint(-2F, 1.0F, 1.0F);
		rightwing1.rotateAngleX = 0.20944F;
		rightwing1.rotateAngleY = -0.61087F;
		rightwing1.rotateAngleZ = 0.0F;
		rightwing1.mirror = false;
		rightwing2 = new ModelRenderer(this, 46, 19);
		rightwing2.addBox(-0.5F, -2F, 3F, 1, 11, 2);
		rightwing2.setRotationPoint(-2F, 1.0F, 1.0F);
		rightwing2.rotateAngleX = 0.20944F;
		rightwing2.rotateAngleY = -0.61087F;
		rightwing2.rotateAngleZ = 0.0F;
		rightwing2.mirror = false;
		rightwing3 = new ModelRenderer(this, 58, 12);
		rightwing3.addBox(-0.5F, -2F, 5F, 1, 18, 2);
		rightwing3.setRotationPoint(-2F, 1.0F, 1.0F);
		rightwing3.rotateAngleX = 0.20944F;
		rightwing3.rotateAngleY = -0.61087F;
		rightwing3.rotateAngleZ = 0.0F;
		rightwing3.mirror = false;
		rightwing4 = new ModelRenderer(this, 52, 16);
		rightwing4.addBox(-0.5F, 0.0F, 7F, 1, 14, 2);
		rightwing4.setRotationPoint(-2F, 1.0F, 1.0F);
		rightwing4.rotateAngleX = 0.20944F;
		rightwing4.rotateAngleY = -0.61087F;
		rightwing4.rotateAngleZ = 0.0F;
		rightwing4.mirror = false;
		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4F, -8F, -4F, 8, 8, 8);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.rotateAngleX = 0.24435F;
		head.rotateAngleY = 0.0F;
		head.rotateAngleZ = 0.0F;
		head.mirror = false;
		body = new ModelRenderer(this, 0, 16);
		body.addBox(-4F, 0.0F, -2F, 8, 12, 4);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.rotateAngleX = 0.0F;
		body.rotateAngleY = 0.0F;
		body.rotateAngleZ = 0.0F;
		body.mirror = false;
		rightarm = new ModelRenderer(this, 24, 19);
		rightarm.addBox(-3F, 0.0F, -2F, 4, 9, 4);
		rightarm.setRotationPoint(-5F, 0.0F, 0.0F);
		rightarm.rotateAngleX = -1.74533F;
		rightarm.rotateAngleY = -0.55851F;
		rightarm.rotateAngleZ = 0.0F;
		rightarm.mirror = false;
		leftarm = new ModelRenderer(this, 24, 19);
		leftarm.addBox(-1F, 0.0F, -2F, 4, 9, 4);
		leftarm.setRotationPoint(5F, 0.0F, 0.0F);
		leftarm.rotateAngleX = -1.74533F;
		leftarm.rotateAngleY = 0.55851F;
		leftarm.rotateAngleZ = 0.0F;
		leftarm.mirror = false;
		rightleg = new ModelRenderer(this, 24, 19);
		rightleg.addBox(-2F, 0.0F, -2F, 4, 9, 4);
		rightleg.setRotationPoint(-2F, 12F, 0.0F);
		rightleg.rotateAngleX = 0.0F;
		rightleg.rotateAngleY = 0.0F;
		rightleg.rotateAngleZ = 0.0F;
		rightleg.mirror = false;
		leftleg = new ModelRenderer(this, 24, 19);
		leftleg.addBox(-2F, 0.0F, -2F, 4, 9, 4);
		leftleg.setRotationPoint(2.0F, 12F, 0.0F);
		leftleg.rotateAngleX = 0.0F;
		leftleg.rotateAngleY = 0.0F;
		leftleg.rotateAngleZ = 0.0F;
		leftleg.mirror = false;
	}

	@Override
	public void setRotationAngles(WeepingAngelEntity weepingAngelEntity, float v, float v1, float v2, float v3, float v4) {
		AngelPoses pose = angelPoses;
		if (weepingAngelEntity != null) {
			pose = AngelPoses.getPoseFromString(weepingAngelEntity.getAngelPose());
		}

		if (pose == AngelPoses.POSE_ANGRY_TWO) {
			rightarm.rotateAngleX = (float) Math.toRadians(-115);
			rightarm.rotateAngleY = (float) Math.toRadians(0);
			rightarm.rotateAngleZ = (float) Math.toRadians(0);

			leftarm.rotateAngleX = (float) Math.toRadians(-55);
			leftarm.rotateAngleY = (float) Math.toRadians(0);
			leftarm.rotateAngleZ = (float) Math.toRadians(0);

			head.rotateAngleX = (float) Math.toRadians(17.5);
			head.rotateAngleY = (float) Math.toRadians(0);
			head.rotateAngleZ = (float) Math.toRadians(-10);
			return;
		}


		if (pose.create().isAngry()) {
			rightarm.rotateAngleX = (float) Math.toRadians(-90);
			rightarm.rotateAngleY = (float) Math.toRadians(-20);
			rightarm.rotateAngleZ = (float) Math.toRadians(30);

			leftarm.rotateAngleX = (float) Math.toRadians(-90);
			leftarm.rotateAngleY = (float) Math.toRadians(25);
			leftarm.rotateAngleZ = (float) Math.toRadians(-17.5);

			head.rotateAngleX = (float) Math.toRadians(0);
			head.rotateAngleY = (float) Math.toRadians(-12.5);
			head.rotateAngleZ = (float) Math.toRadians(0);
			return;
		}

		if (pose == AngelPoses.POSE_HIDING_FACE) {
			head.rotateAngleX = (float) Math.toRadians(20);
			head.rotateAngleY = (float) Math.toRadians(0);
			head.rotateAngleZ = (float) Math.toRadians(0);

			rightarm.rotateAngleX = (float) Math.toRadians(-105);
			rightarm.rotateAngleY = (float) Math.toRadians(20);
			rightarm.rotateAngleZ = (float) Math.toRadians(12.5);

			leftarm.rotateAngleX = (float) Math.toRadians(-105);
			leftarm.rotateAngleY = (float) Math.toRadians(-20);
			leftarm.rotateAngleZ = (float) Math.toRadians(-12.5);
			return;
		}

		if (pose == AngelPoses.POSE_IDLE) {
			head.rotateAngleX = (float) Math.toRadians(0);
			head.rotateAngleY = (float) Math.toRadians(0);
			head.rotateAngleZ = (float) Math.toRadians(0);

			rightarm.rotateAngleX = (float) Math.toRadians(0);
			rightarm.rotateAngleY = (float) Math.toRadians(0);
			rightarm.rotateAngleZ = (float) Math.toRadians(7.5);

			leftarm.rotateAngleX = (float) Math.toRadians(0);
			leftarm.rotateAngleY = (float) Math.toRadians(0);
			leftarm.rotateAngleZ = (float) Math.toRadians(-7.5);
			return;
		}

		if(pose == AngelPoses.POSE_SHY){
			rightarm.rotateAngleX = (float) Math.toRadians(-90);
			rightarm.rotateAngleY = (float) Math.toRadians(-1.5);
			rightarm.rotateAngleZ = (float) Math.toRadians(-20);

			leftarm.rotateAngleX = (float) Math.toRadians(-120);
			leftarm.rotateAngleY = (float) Math.toRadians(-36);
			leftarm.rotateAngleZ = (float) Math.toRadians(10);

			head.rotateAngleX = (float) Math.toRadians(20);
			head.rotateAngleY = (float) Math.toRadians(-40);
			head.rotateAngleZ = (float) Math.toRadians(-20);
			return;
		}

	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		leftfoot.render(matrixStack, buffer, packedLight, packedOverlay);
		rightfoot.render(matrixStack, buffer, packedLight, packedOverlay);
		leftwing1.render(matrixStack, buffer, packedLight, packedOverlay);
		leftwing2.render(matrixStack, buffer, packedLight, packedOverlay);
		leftwing3.render(matrixStack, buffer, packedLight, packedOverlay);
		leftwing4.render(matrixStack, buffer, packedLight, packedOverlay);
		rightwing1.render(matrixStack, buffer, packedLight, packedOverlay);
		rightwing2.render(matrixStack, buffer, packedLight, packedOverlay);
		rightwing3.render(matrixStack, buffer, packedLight, packedOverlay);
		rightwing4.render(matrixStack, buffer, packedLight, packedOverlay);
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		rightarm.render(matrixStack, buffer, packedLight, packedOverlay);
		leftarm.render(matrixStack, buffer, packedLight, packedOverlay);
		rightleg.render(matrixStack, buffer, packedLight, packedOverlay);
		leftleg.render(matrixStack, buffer, packedLight, packedOverlay);
	}


	@Override
	public ResourceLocation getTextureForPose(AngelPoses pose) {
		return TEXTURE;
	}

}
