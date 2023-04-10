package mc.craig.software.angels.forge.compat.tardis.interiordoors;
// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.forge.compat.tardis.EnumDoorTypes;
import mc.craig.software.angels.forge.compat.tardis.WATexVariants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.tardis.mod.client.models.interiordoors.AbstractInteriorDoorModel;
import net.tardis.mod.entity.DoorEntity;
import net.tardis.mod.enums.EnumDoorState;
import net.tardis.mod.helper.TardisHelper;
import net.tardis.mod.tileentities.ConsoleTile;

public class AbPropIntDoorModel extends AbstractInteriorDoorModel{
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(WeepingAngels.MODID, "abpropintdoormodel"), "main");
	private final ModelPart IntDoors;
	private final ModelPart boti;

	public AbPropIntDoorModel(ModelPart root) {
		this.IntDoors = root.getChild("IntDoors");
		this.boti = root.getChild("boti");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition IntDoors = partdefinition.addOrReplaceChild("IntDoors", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 1.0F));

		PartDefinition Posts = IntDoors.addOrReplaceChild("Posts", CubeListBuilder.create().texOffs(56, 12).addBox(-3.0F, -54.0F, 6.0F, 3.0F, 57.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-14.0F, -3.0F, -15.0F));

		PartDefinition cube_r1 = Posts.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 63).addBox(-17.0F, -60.0F, -17.0F, 5.0F, 57.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.0F, 6.0F, 23.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition PPCB = IntDoors.addOrReplaceChild("PPCB", CubeListBuilder.create().texOffs(0, 0).addBox(-15.0F, -56.0F, -10.0F, 30.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(28, 76).addBox(-15.0F, -56.0F, -6.0F, 30.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Doors = IntDoors.addOrReplaceChild("Doors", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 1.0F));

		PartDefinition RDoor = Doors.addOrReplaceChild("RDoor", CubeListBuilder.create().texOffs(28, 12).addBox(-13.0F, -50.0F, -1.0F, 13.0F, 50.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(3, 3).addBox(-12.0F, -33.0F, -2.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(67, 2).addBox(-13.0F, -27.0F, 0.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(13.0F, 0.0F, -7.0F));

		PartDefinition LDoor = Doors.addOrReplaceChild("LDoor", CubeListBuilder.create().texOffs(0, 12).addBox(0.0F, -50.0F, -1.0F, 13.0F, 50.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 3).addBox(11.5F, -2.0F, 0.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(24, 63).addBox(3.0F, -34.0F, 0.0F, 6.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(40, 63).addBox(3.0F, -36.0F, 1.0F, 7.0F, 8.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(56, 12).addBox(10.0F, -32.0F, -2.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(12.0F, -27.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-13.0F, 0.0F, -7.0F));

		PartDefinition Panels = IntDoors.addOrReplaceChild("Panels", CubeListBuilder.create().texOffs(20, 63).addBox(-14.0F, -39.0F, -7.0F, 1.0F, 51.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 63).addBox(13.0F, -39.0F, -7.0F, 1.0F, 51.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(67, 0).addBox(-13.0F, -39.0F, -7.0F, 26.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 0.0F));

		PartDefinition boti = partdefinition.addOrReplaceChild("boti", CubeListBuilder.create().texOffs(70, 76).addBox(-13.5F, -50.1F, -7.9F, 27.0F, 50.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		IntDoors.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		boti.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void renderBones(DoorEntity doorEntity, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
		EnumDoorState state = doorEntity.getOpenState();
		poseStack.pushPose();
		poseStack.translate(0.0D, 0.6f - 0.08, -0.1f);
		poseStack.scale(0.65F, 0.65F, 0.65F);
		switch (state) {
			case ONE:
				this.IntDoors.getChild("Doors").getChild("RDoor").yRot = (float) Math.toRadians(EnumDoorTypes.ABPROP.getRotationForState(EnumDoorState.ONE));
				this.IntDoors.getChild("Doors").getChild("LDoor").yRot = (float) Math.toRadians(EnumDoorTypes.ABPROP.getRotationForState(EnumDoorState.CLOSED));
				break;
			case BOTH:
				this.IntDoors.getChild("Doors").getChild("RDoor").yRot = (float) Math.toRadians(EnumDoorTypes.ABPROP.getRotationForState(EnumDoorState.ONE));
				this.IntDoors.getChild("Doors").getChild("LDoor").yRot = (float) Math.toRadians(EnumDoorTypes.ABPROP.getRotationForState(EnumDoorState.BOTH));
				break;
			case CLOSED:
				this.IntDoors.getChild("Doors").getChild("RDoor").yRot = (float) Math.toRadians(EnumDoorTypes.ABPROP.getRotationForState(EnumDoorState.CLOSED));
				this.IntDoors.getChild("Doors").getChild("LDoor").yRot = (float) Math.toRadians(EnumDoorTypes.ABPROP.getRotationForState(EnumDoorState.CLOSED));
		}

		IntDoors.render(poseStack, vertexConsumer, packedLight, packedOverlay);
		poseStack.popPose();
	}

	@Override
	public ResourceLocation getTexture() {
		ConsoleTile tile = TardisHelper.getConsoleInWorld(Minecraft.getInstance().level).orElse(null);
		if (tile != null) {
			int index = tile.getExteriorManager().getExteriorVariant();
			if (tile.getExteriorType().getVariants() != null && index < tile.getExteriorType().getVariants().length)
				return tile.getExteriorType().getVariants()[index].getInteriorDoorTexture();
		}
		return WATexVariants.BLU;	}
}