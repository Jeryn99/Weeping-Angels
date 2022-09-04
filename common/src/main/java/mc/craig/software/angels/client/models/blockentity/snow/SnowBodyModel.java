package mc.craig.software.angels.client.models.blockentity.snow;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class SnowBodyModel extends EntityModel<Entity> {
    private final ModelPart head;
    private final ModelPart Outer_r1;
    private final ModelPart body;
    private final ModelPart body_r1;
    private final ModelPart leftArm;
    private final ModelPart rightArm;
    private final ModelPart leftWing;
    private final ModelPart leftWing_r1;
    private final ModelPart rightWing;
    private final ModelPart rightWing_r1;

    public SnowBodyModel(ModelPart root) {
        this.head = root.getChild("head");
        this.Outer_r1 = head.getChild("Outer_r1");
        this.body = root.getChild("body");
        this.body_r1 = body.getChild("body_r1");
        this.leftArm = root.getChild("leftArm");
        this.rightArm = root.getChild("rightArm");
        this.leftWing = root.getChild("leftWing");
        this.leftWing_r1 = leftWing.getChild("leftWing_r1");
        this.rightWing = root.getChild("rightWing");
        this.rightWing_r1 = rightWing.getChild("rightWing_r1");
    }

    public static LayerDefinition meshLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 20.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

        PartDefinition Outer_r1 = head.addOrReplaceChild("Outer_r1", CubeListBuilder.create().texOffs(72, 0).addBox(-4.0F, -9.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)).mirror(false)
                .texOffs(0, 17).addBox(-4.0F, -9.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.4363F, 0.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition body_r1 = body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(32, 17).addBox(-4.0F, -1.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)).mirror(false)
                .texOffs(56, 17).addBox(-4.0F, -1.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 21.0F, 0.0F, 0.4363F, 0.0F, 0.0F));

        PartDefinition leftArm = partdefinition.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(24, 59).addBox(-2.0F, -1.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.0F, 21.0F, 0.0F, -0.9163F, 0.0F, 1.0036F));

        PartDefinition rightArm = partdefinition.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(10, 59).addBox(-1.0F, -1.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 21.0F, 0.0F, -1.0036F, 0.0F, -0.9163F));

        PartDefinition leftWing = partdefinition.addOrReplaceChild("leftWing", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 5.0F, 2.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition leftWing_r1 = leftWing.addOrReplaceChild("leftWing_r1", CubeListBuilder.create().texOffs(38, 59).addBox(-2.0F, -4.0F, 13.0F, 2.0F, 17.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 33).addBox(-2.0F, -6.0F, 11.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(8, 33).addBox(-2.0F, -6.9F, 8.0F, 2.0F, 21.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(18, 83).addBox(-2.0F, -2.9F, 5.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(6, 83).addBox(-2.0F, -4.9F, 7.0F, 2.0F, 14.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 101).addBox(-2.0F, 0.0F, 2.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 16.0F, -2.0F, 0.3038F, 0.0469F, -0.3038F));

        PartDefinition rightWing = partdefinition.addOrReplaceChild("rightWing", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, 5.0F, 2.0F, 0.0F, 0.7854F, 0.0F));

        PartDefinition rightWing_r1 = rightWing.addOrReplaceChild("rightWing_r1", CubeListBuilder.create().texOffs(0, 59).addBox(0.0F, -6.9F, 8.0F, 2.0F, 21.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 83).addBox(0.0F, -4.0F, 13.0F, 2.0F, 17.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(18, 33).addBox(0.0F, -6.0F, 11.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(26, 83).addBox(0.0F, -2.9F, 5.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(12, 83).addBox(0.0F, -4.9F, 7.0F, 2.0F, 14.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(10, 101).addBox(0.0F, 0.0F, 2.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 16.0F, -2.0F, 0.3038F, -0.0469F, 0.3038F));
        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack matrixStack, @NotNull VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
        rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
        leftWing.render(matrixStack, buffer, packedLight, packedOverlay);
        rightWing.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }
}