package me.suff.mc.angels.client.models.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class PoliceBoxModel extends EntityModel {
    public final ModelPart DoorRight;
    private final ModelPart Base;
    private final ModelPart Pillars;
    private final ModelPart Frame;
    private final ModelPart SideDoors;
    private final ModelPart PPCBSign;
    private final ModelPart Roof;
    private final ModelPart Lamp;
    private final ModelPart DoorLeft;

    public PoliceBoxModel(ModelPart root) {
        DoorRight = root.getChild("DoorRight");
        Base = root.getChild("Base");
        Pillars = root.getChild("Pillars");
        Frame = root.getChild("Frame");
        PPCBSign = root.getChild("PPCBSign");
        SideDoors = root.getChild("SideDoors");
        Roof = root.getChild("Roof");
        Lamp = root.getChild("Lamp");
        DoorLeft = root.getChild("DoorLeft");
    }

    public static LayerDefinition getModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Base = partdefinition.addOrReplaceChild("Base", CubeListBuilder.create().texOffs(0, 0).addBox(-18.5F, -3.0F, -18.5F, 37.0F, 3.0F, 37.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition Pillars = partdefinition.addOrReplaceChild("Pillars", CubeListBuilder.create().texOffs(0, 193).addBox(-17.5F, -60.0F, -17.5F, 4.0F, 57.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(140, 191).addBox(13.5F, -60.0F, -17.5F, 4.0F, 57.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(124, 191).addBox(13.5F, -60.0F, 13.5F, 4.0F, 57.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(108, 191).addBox(-17.5F, -60.0F, 13.5F, 4.0F, 57.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(99, 40).addBox(-16.5F, -62.0F, -16.5F, 33.0F, 2.0F, 33.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition Frame = partdefinition.addOrReplaceChild("Frame", CubeListBuilder.create().texOffs(209, 181).addBox(-13.5F, -53.0F, -17.0F, 1.0F, 50.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(215, 181).addBox(-17.0F, -53.0F, 12.5F, 2.0F, 50.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(209, 181).addBox(12.5F, -53.0F, 15.0F, 1.0F, 50.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(215, 181).addBox(15.0F, -53.0F, -13.5F, 2.0F, 50.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(209, 181).addBox(12.5F, -53.0F, -17.0F, 1.0F, 50.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(215, 181).addBox(-17.0F, -53.0F, -13.5F, 2.0F, 50.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(209, 181).addBox(-13.5F, -53.0F, 15.0F, 1.0F, 50.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(215, 181).addBox(15.0F, -53.0F, 12.5F, 2.0F, 50.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(221, 181).addBox(-17.0F, -53.0F, -0.5F, 1.0F, 50.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(221, 181).addBox(-0.5F, -53.0F, 16.0F, 1.0F, 50.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(221, 181).addBox(16.0F, -53.0F, -0.5F, 1.0F, 50.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 221).addBox(-17.0F, -54.0F, -17.0F, 34.0F, 1.0F, 34.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition SideDoors = partdefinition.addOrReplaceChild("SideDoors", CubeListBuilder.create().texOffs(52, 118).addBox(-17.0F, -50.0F, 4.0F, 1.0F, 50.0F, 25.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(104, 140).addBox(-13.5F, -50.0F, 31.5F, 25.0F, 50.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 118).addBox(14.0F, -50.0F, 4.0F, 1.0F, 50.0F, 25.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 21.0F, -16.5F, 0.0F, 0.0F, 0.0F));

        PartDefinition PPCBSign = partdefinition.addOrReplaceChild("PPCBSign", CubeListBuilder.create().texOffs(0, 40).addBox(-15.5F, -59.0F, -18.5F, 31.0F, 5.0F, 37.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 82).addBox(-18.5F, -59.0F, -15.5F, 37.0F, 5.0F, 31.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition Roof = partdefinition.addOrReplaceChild("Roof", CubeListBuilder.create().texOffs(111, 0).addBox(-32.0F, -6.0F, 3.0F, 29.0F, 2.0F, 29.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(105, 105).addBox(-33.0F, -4.0F, 2.0F, 31.0F, 4.0F, 31.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(17.5F, -35.0F, -17.5F, 0.0F, 0.0F, 0.0F));

        PartDefinition Lamp = partdefinition.addOrReplaceChild("Lamp", CubeListBuilder.create().texOffs(0, 0).addBox(-20.0F, -2.0F, 15.0F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 13).addBox(-20.0F, -7.5F, 15.0F, 5.0F, 6.0F, 5.0F, new CubeDeformation(-0.5F)).mirror(false)
                .texOffs(0, 7).addBox(-20.0F, -8.0F, 15.0F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(16, 13).addBox(-19.0F, -9.0F, 16.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(17.5F, -41.0F, -17.5F, 0.0F, 0.0F, 0.0F));

        PartDefinition DoorLeft = partdefinition.addOrReplaceChild("DoorLeft", CubeListBuilder.create().texOffs(182, 182).addBox(-12.0F, -50.0F, -1.0F, 12.0F, 50.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 24).addBox(-12.0F, -34.0F, -2.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(4, 24).addBox(-12.0F, -26.0F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(12.5F, 21.0F, -15.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition DoorRight = partdefinition.addOrReplaceChild("DoorRight", CubeListBuilder.create().texOffs(156, 156).addBox(0.0F, -50.0F, -1.0F, 12.0F, 50.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(221, 181).addBox(12.0F, -50.0F, -2.0F, 1.0F, 50.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 30).addBox(10.0F, -33.0F, -1.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-12.5F, 21.0F, -15.0F, 0.0F, 0.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void setupAnim(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Base.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        Pillars.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        Frame.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        SideDoors.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        PPCBSign.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        Roof.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        Lamp.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        DoorLeft.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        DoorRight.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}