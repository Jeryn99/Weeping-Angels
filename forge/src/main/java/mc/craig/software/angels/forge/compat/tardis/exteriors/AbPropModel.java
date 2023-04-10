package mc.craig.software.angels.forge.compat.tardis.exteriors;// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.forge.compat.tardis.EnumDoorTypes;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.tardis.mod.client.models.exteriors.ExteriorModel;
import net.tardis.mod.enums.EnumDoorState;
import net.tardis.mod.tileentities.exteriors.ExteriorTile;

public class AbPropModel extends ExteriorModel {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(WeepingAngels.MODID, "abpropmodel"), "main");
	private final ModelPart Posts;
	private final ModelPart Panels;
	private final ModelPart PPCB;
	private final ModelPart Roof;
	private final ModelPart Lamp;
	private final ModelPart Doors;
	private final ModelPart boti;
	private final ModelPart bb_main;

	public AbPropModel(ModelPart root) {
		this.Posts = root.getChild("Posts");
		this.Panels = root.getChild("Panels");
		this.PPCB = root.getChild("PPCB");
		this.Roof = root.getChild("Roof");
		this.Lamp = root.getChild("Lamp");
		this.Doors = root.getChild("Doors");
		this.boti = root.getChild("boti");
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Posts = partdefinition.addOrReplaceChild("Posts", CubeListBuilder.create().texOffs(28, 102).addBox(-3.0F, -57.0F, -2.0F, 3.0F, 57.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-14.0F, 21.0F, -15.0F));

		PartDefinition cube_r1 = Posts.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(28, 102).addBox(-17.0F, -60.0F, -17.0F, 3.0F, 57.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.0F, 3.0F, 15.0F, -3.1416F, 0.0F, 3.1416F));

		PartDefinition cube_r2 = Posts.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(28, 102).addBox(-17.0F, -60.0F, -17.0F, 3.0F, 57.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.0F, 3.0F, 15.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r3 = Posts.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(28, 102).addBox(-17.0F, -60.0F, -17.0F, 3.0F, 57.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.0F, 3.0F, 15.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition Panels = partdefinition.addOrReplaceChild("Panels", CubeListBuilder.create().texOffs(84, 74).addBox(-15.0F, -42.0F, -14.0F, 1.0F, 51.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(40, 102).addBox(-14.0F, -42.0F, -15.0F, 1.0F, 51.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(44, 102).addBox(13.0F, -42.0F, -15.0F, 1.0F, 51.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(84, 72).addBox(-13.0F, -42.0F, -15.0F, 26.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.0F, 0.0F));

		PartDefinition cube_r4 = Panels.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(84, 74).addBox(-15.0F, -54.0F, -14.0F, 1.0F, 51.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.0F, 0.0F, -3.1416F, 0.0F, 3.1416F));

		PartDefinition cube_r5 = Panels.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(84, 74).addBox(-15.0F, -54.0F, -14.0F, 1.0F, 51.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition PPCB = partdefinition.addOrReplaceChild("PPCB", CubeListBuilder.create().texOffs(90, 39).addBox(-15.0F, -59.0F, -18.0F, 30.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r6 = PPCB.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(90, 39).addBox(-15.0F, -59.0F, -18.0F, 30.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r7 = PPCB.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(90, 39).addBox(-15.0F, -59.0F, -18.0F, 30.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -3.1416F, 0.0F, 3.1416F));

		PartDefinition cube_r8 = PPCB.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(90, 39).addBox(-15.0F, -59.0F, -18.0F, 30.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition Roof = partdefinition.addOrReplaceChild("Roof", CubeListBuilder.create().texOffs(0, 39).addBox(-15.0F, -63.0F, -15.0F, 30.0F, 3.0F, 30.0F, new CubeDeformation(0.0F))
		.texOffs(0, 72).addBox(-14.0F, -65.0F, -14.0F, 28.0F, 2.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 25.0F, 0.0F));

		PartDefinition Lamp = partdefinition.addOrReplaceChild("Lamp", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 15).addBox(-2.5F, -7.0F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 8).addBox(-3.0F, -8.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(20, 11).addBox(-2.0F, -9.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -40.0F, 0.0F));

		PartDefinition Doors = partdefinition.addOrReplaceChild("Doors", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 1.0F));

		PartDefinition RDoor = Doors.addOrReplaceChild("RDoor", CubeListBuilder.create().texOffs(48, 102).addBox(-13.0F, -50.0F, -1.0F, 13.0F, 50.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-12.0F, -33.0F, -2.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(24, 0).addBox(-13.0F, -27.0F, 0.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(13.0F, -3.0F, -15.0F));

		PartDefinition LDoor = Doors.addOrReplaceChild("LDoor", CubeListBuilder.create().texOffs(0, 102).addBox(0.0F, -50.0F, -1.0F, 13.0F, 50.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(15, 55).addBox(11.5F, -2.0F, 0.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 51).addBox(3.0F, -34.0F, 0.0F, 6.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 40).addBox(3.0F, -36.0F, 1.0F, 7.0F, 8.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 8).addBox(10.0F, -32.0F, -2.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(28, 5).addBox(12.0F, -27.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-13.0F, -3.0F, -15.0F));

		PartDefinition boti = partdefinition.addOrReplaceChild("boti", CubeListBuilder.create().texOffs(156, 0).addBox(-13.5F, -58.05F, -14.0F, 27.0F, 55.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 0).addBox(-18.0F, -3.0F, -18.0F, 36.0F, 3.0F, 36.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Posts.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Panels.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		PPCB.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Roof.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Lamp.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Doors.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		boti.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void renderBones(ExteriorTile exteriorTile, float v, PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float alpha) {
		EnumDoorState state = exteriorTile.getOpen();
		matrixStack.pushPose();
		matrixStack.translate(0.0D, 0.75, 0.0D);
		switch (state) {
			case ONE:
				this.Doors.getChild("RDoor").yRot = (float) Math.toRadians(EnumDoorTypes.ABPROP.getRotationForState(EnumDoorState.ONE));
				this.Doors.getChild("LDoor").yRot = (float) Math.toRadians(EnumDoorTypes.ABPROP.getRotationForState(EnumDoorState.CLOSED));
				break;
			case BOTH:
				this.Doors.getChild("RDoor").yRot = (float) Math.toRadians(EnumDoorTypes.ABPROP.getRotationForState(EnumDoorState.ONE));
				this.Doors.getChild("LDoor").yRot = (float) Math.toRadians(EnumDoorTypes.ABPROP.getRotationForState(EnumDoorState.BOTH));
				break;
			case CLOSED:
				this.Doors.getChild("RDoor").yRot = (float) Math.toRadians(EnumDoorTypes.ABPROP.getRotationForState(EnumDoorState.CLOSED));
				this.Doors.getChild("LDoor").yRot = (float) Math.toRadians(EnumDoorTypes.ABPROP.getRotationForState(EnumDoorState.CLOSED));
		}
		Posts.render(matrixStack, buffer, packedLight, packedOverlay, 1, 1, 1, alpha);
		Panels.render(matrixStack, buffer, packedLight, packedOverlay, 1, 1, 1, alpha);
		PPCB.render(matrixStack, buffer, packedLight, packedOverlay, 1, 1, 1, alpha);
		Roof.render(matrixStack, buffer, packedLight, packedOverlay, 1, 1, 1, alpha);
		Lamp.render(matrixStack, buffer, packedLight, packedOverlay, 1, 1, 1, alpha);
		Doors.render(matrixStack, buffer, packedLight, packedOverlay, 1, 1, 1, alpha);
		bb_main.render(matrixStack, buffer, packedLight, packedOverlay, 1, 1, 1, alpha);

		matrixStack.popPose();
	}
}