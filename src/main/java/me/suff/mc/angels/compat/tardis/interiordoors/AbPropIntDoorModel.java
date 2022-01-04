package me.suff.mc.angels.compat.tardis.interiordoors;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import me.suff.mc.angels.utils.EnumDoorTypes;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.client.models.interiordoors.IInteriorDoorRenderer;
import net.tardis.mod.entity.DoorEntity;
import net.tardis.mod.enums.EnumDoorState;

public class AbPropIntDoorModel extends EntityModel<Entity> implements IInteriorDoorRenderer {
	private final ModelRenderer Posts;
	private final ModelRenderer cube_r1;
	private final ModelRenderer Panels;
	private final ModelRenderer PPCB;
	private final ModelRenderer Roof;
	private final ModelRenderer Lamp;
	private final ModelRenderer Doors;
	private final ModelRenderer RDoor;
	private final ModelRenderer LDoor;
	private final ModelRenderer boti;
	private final ModelRenderer bb_main;

	public AbPropIntDoorModel() {
		texWidth = 256;
		texHeight = 256;

		Posts = new ModelRenderer(this);
		Posts.setPos(-14.0F, 21.0F, -15.0F);
		Posts.texOffs(28, 102).addBox(-3.0F, -57.0F, -2.0F, 3.0F, 57.0F, 3.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(14.0F, 3.0F, 15.0F);
		Posts.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, -1.5708F, 0.0F);
		cube_r1.texOffs(28, 102).addBox(-17.0F, -60.0F, -17.0F, 3.0F, 57.0F, 3.0F, 0.0F, false);

		Panels = new ModelRenderer(this);
		Panels.setPos(0.0F, 12.0F, 0.0F);
		Panels.texOffs(40, 102).addBox(-14.0F, -42.0F, -15.0F, 1.0F, 51.0F, 1.0F, 0.0F, false);
		Panels.texOffs(44, 102).addBox(13.0F, -42.0F, -15.0F, 1.0F, 51.0F, 1.0F, 0.0F, false);
		Panels.texOffs(84, 72).addBox(-13.0F, -42.0F, -15.0F, 26.0F, 1.0F, 1.0F, 0.0F, false);

		PPCB = new ModelRenderer(this);
		PPCB.setPos(0.0F, 24.0F, 0.0F);
		PPCB.texOffs(90, 39).addBox(-15.0F, -59.0F, -18.0F, 30.0F, 5.0F, 3.0F, 0.0F, false);

		Roof = new ModelRenderer(this);
		Roof.setPos(0.0F, 25.0F, 0.0F);


		Lamp = new ModelRenderer(this);
		Lamp.setPos(0.0F, -40.0F, 0.0F);


		Doors = new ModelRenderer(this);
		Doors.setPos(0.0F, 24.0F, 1.0F);


		RDoor = new ModelRenderer(this);
		RDoor.setPos(13.0F, -3.0F, -15.0F);
		Doors.addChild(RDoor);
		setRotationAngle(RDoor, 0.0F, 0.0F, 0.0F);
		RDoor.texOffs(48, 102).addBox(-13.0F, -50.0F, -1.0F, 13.0F, 50.0F, 1.0F, 0.0F, false);
		RDoor.texOffs(0, 0).addBox(-12.0F, -33.0F, -2.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		RDoor.texOffs(24, 0).addBox(-13.0F, -27.0F, 0.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);

		LDoor = new ModelRenderer(this);
		LDoor.setPos(-13.0F, -3.0F, -15.0F);
		Doors.addChild(LDoor);
		setRotationAngle(LDoor, 0.0F, 0.0F, 0.0F);
		LDoor.texOffs(0, 102).addBox(0.0F, -50.0F, -1.0F, 13.0F, 50.0F, 1.0F, 0.0F, false);
		LDoor.texOffs(0, 51).addBox(3.0F, -34.0F, 0.0F, 6.0F, 7.0F, 2.0F, 0.0F, false);
		LDoor.texOffs(0, 40).addBox(3.0F, -36.0F, 1.0F, 7.0F, 8.0F, 0.0F, 0.0F, false);
		LDoor.texOffs(0, 8).addBox(10.0F, -32.0F, -2.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		LDoor.texOffs(28, 5).addBox(12.0F, -27.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		boti = new ModelRenderer(this);
		boti.setPos(0.0F, 0.0F, 0.0F);
		Doors.addChild(boti);
		boti.texOffs(163, 0).addBox(-13.0F, -53.0F, -17.0F, 26.0F, 50.0F, 1.0F, 0.0F, false);

		bb_main = new ModelRenderer(this);
		bb_main.setPos(0.0F, 24.0F, 0.0F);
		bb_main.texOffs(32, 32).addBox(-18.0F, -3.0F, -18.0F, 36.0F, 3.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(Entity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {

	}


	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}

	@Override
	public void render(DoorEntity door, MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay) {
		EnumDoorState state = door.getOpenState();
		matrixStack.pushPose();
		matrixStack.translate(0.0D, 0.81D, 0.175D);
		matrixStack.scale(0.65F, 0.65F, 0.65F);
		switch (state) {
			case ONE:
				this.RDoor.yRot = (float) Math.toRadians(EnumDoorTypes.ABPROP.getRotationForState(EnumDoorState.ONE));
				this.LDoor.yRot = (float) Math.toRadians(EnumDoorTypes.ABPROP.getRotationForState(EnumDoorState.CLOSED));
				break;
			case BOTH:
				this.RDoor.yRot = (float) Math.toRadians(EnumDoorTypes.ABPROP.getRotationForState(EnumDoorState.ONE));
				this.LDoor.yRot = (float) Math.toRadians(EnumDoorTypes.ABPROP.getRotationForState(EnumDoorState.BOTH));
				break;
			case CLOSED:
				this.RDoor.yRot = (float) Math.toRadians(EnumDoorTypes.ABPROP.getRotationForState(EnumDoorState.CLOSED));
				this.LDoor.yRot = (float) Math.toRadians(EnumDoorTypes.ABPROP.getRotationForState(EnumDoorState.CLOSED));
		}
		matrixStack.translate(0,1.25,0);
		RDoor.render(matrixStack, buffer, packedLight, packedOverlay);
		LDoor.render(matrixStack, buffer, packedLight, packedOverlay);
		boti.render(matrixStack, buffer, packedLight, packedOverlay);
		matrixStack.translate(0,-1.5,0);
		PPCB.render(matrixStack, buffer, packedLight, packedOverlay);
		Panels.render(matrixStack, buffer, packedLight, packedOverlay);
		Posts.render(matrixStack, buffer, packedLight, packedOverlay);
		matrixStack.popPose();
	}

	@Override
	public void renderBones(DoorEntity door, MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay) {

	}

	@Override
	public void renderBoti(DoorEntity door, MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay) {

	}

	public ResourceLocation getTexture() {
		return new ResourceLocation("weeping_angels", "textures/exteriors/abpropintdoor.png");

	}

	@Override
	public void renderDoorWhenClosed(DoorEntity door, MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, ModelRenderer doorBone) {

	}

	@Override
	public void renderDoorWhenClosed(DoorEntity door, MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, ModelRenderer... doorBones) {

	}

	@Override
	public boolean doesDoorOpenIntoBotiWindow() {
		return false;
	}

	@Override
	public void renderToBuffer(MatrixStack p_225598_1_, IVertexBuilder p_225598_2_, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {

	}
}