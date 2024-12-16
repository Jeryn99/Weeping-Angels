package dev.jeryn.angels.client.models.blockentity.snow;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class SnowHeadModel extends EntityModel<Entity> {
    private final ModelPart head;
    private final ModelPart Outer_r1;
    private final ModelPart Outer_r1_r1;
    private final ModelPart body;
    private final ModelPart body_r1;
    private final ModelPart leftArm;
    private final ModelPart rightArm;
    private final ModelPart leftWing;
    private final ModelPart leftWing_r1;
    private final ModelPart leftWing_r1_r1;
    private final ModelPart rightWing;
    private final ModelPart rightWing_r1;
    private final ModelPart rightWing_r1_r1;

    public SnowHeadModel(ModelPart root) {
        this.head = root.getChild("head");
        this.Outer_r1 = head.getChild("Outer_r1");
        this.Outer_r1_r1 = Outer_r1.getChild("Outer_r1");
        this.body = root.getChild("body");
        this.body_r1 = body.getChild("body_r1");
        this.leftArm = root.getChild("leftArm");
        this.rightArm = root.getChild("rightArm");
        this.leftWing = root.getChild("leftWing");
        this.leftWing_r1 = leftWing.getChild("leftWing_r1");
        this.leftWing_r1_r1 = leftWing_r1.getChild("leftWing_r1_r1");
        this.rightWing = root.getChild("rightWing");
        this.rightWing_r1 = rightWing.getChild("rightWing_r1");
        this.rightWing_r1_r1 = rightWing_r1.getChild("rightWing_r1_r1");
    }

    public static LayerDefinition meshLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 16.0F, 0.0F, -0.6109F, 0.0F, 0.0F));
        PartDefinition Outer_r1 = head.addOrReplaceChild("Outer_r1", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.1544F, 0.2786F, 0.3927F, 0.0F, 0.0F));

        PartDefinition Outer_r1_r1 = Outer_r1.addOrReplaceChild("Outer_r1_r1", CubeListBuilder.create().texOffs(0, 17).addBox(-4.0F, -6.4957F, -4.1981F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(72, 0).addBox(-4.0F, -6.4957F, -4.1981F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offsetAndRotation(0.0F, 6.8456F, -0.2786F, -0.1309F, 0.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition body_r1 = body.addOrReplaceChild("body_r1", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 17.1544F, 0.2786F, 0.3927F, 0.0F, 0.0F));

        PartDefinition leftArm = partdefinition.addOrReplaceChild("leftArm", CubeListBuilder.create(), PartPose.offsetAndRotation(-5.0F, 17.0F, 0.0F, -0.9163F, 0.0F, 1.0036F));

        PartDefinition rightArm = partdefinition.addOrReplaceChild("rightArm", CubeListBuilder.create(), PartPose.offsetAndRotation(5.0F, 17.0F, 0.0F, -0.9163F, 0.0F, -1.0036F));

        PartDefinition leftWing = partdefinition.addOrReplaceChild("leftWing", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 5.0F, 2.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition leftWing_r1 = leftWing.addOrReplaceChild("leftWing_r1", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, 12.1544F, -1.7214F, 0.2742F, 0.0381F, -0.2742F));

        PartDefinition leftWing_r1_r1 = leftWing_r1.addOrReplaceChild("leftWing_r1_r1", CubeListBuilder.create().texOffs(6, 83).addBox(-5.9695F, -4.2282F, 3.0303F, 2.0F, 14.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(18, 83).addBox(-5.9695F, -2.2282F, 1.0303F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(8, 33).addBox(-5.9695F, -6.2282F, 4.0303F, 2.0F, 21.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 33).addBox(-5.9695F, -5.3282F, 7.0303F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(38, 59).addBox(-5.9695F, -3.3282F, 9.0303F, 2.0F, 17.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 6.8456F, -0.2786F, -0.0924F, 0.0043F, 0.0924F));

        PartDefinition rightWing = partdefinition.addOrReplaceChild("rightWing", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, 5.0F, 2.0F, 0.0F, 0.7854F, 0.0F));

        PartDefinition rightWing_r1 = rightWing.addOrReplaceChild("rightWing_r1", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 12.1544F, -1.7214F, 0.2742F, -0.0381F, 0.2742F));

        PartDefinition rightWing_r1_r1 = rightWing_r1.addOrReplaceChild("rightWing_r1_r1", CubeListBuilder.create().texOffs(12, 83).addBox(3.9695F, -4.2282F, 3.0303F, 2.0F, 14.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(26, 83).addBox(3.9695F, -2.2282F, 1.0303F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(18, 33).addBox(3.9695F, -5.3282F, 7.0303F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 83).addBox(3.9695F, -3.3282F, 9.0303F, 2.0F, 17.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 59).addBox(3.9695F, -6.2282F, 4.0303F, 2.0F, 21.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 6.8456F, -0.2786F, -0.0924F, -0.0043F, -0.0924F));
        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(@NotNull Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack matrixStack, @NotNull VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(matrixStack, buffer, packedLight, packedOverlay);

    }

}