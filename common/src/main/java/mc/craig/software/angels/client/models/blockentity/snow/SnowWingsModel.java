package mc.craig.software.angels.client.models.blockentity.snow;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class SnowWingsModel extends EntityModel<Entity> {
    private final ModelPart rightWing;
    private final ModelPart leftWing;

    public SnowWingsModel(ModelPart root) {
        this.leftWing = root.getChild("leftWing");
        this.rightWing = root.getChild("rightWing");
    }

    public static LayerDefinition meshLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition leftWing = partdefinition.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(6, 83).addBox(-5.9695F, -4.2282F, 3.0303F, 2.0F, 14.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(18, 83).addBox(-5.9695F, -2.2282F, 1.0303F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(8, 33).addBox(-5.9695F, -6.2282F, 4.0303F, 2.0F, 21.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 33).addBox(-5.9695F, -5.3282F, 7.0303F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(38, 59).addBox(-5.9695F, -3.3282F, 9.0303F, 2.0F, 17.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 1.2217F, 0.0F, -0.3491F));

        PartDefinition rightWing = partdefinition.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(12, 83).addBox(3.9695F, -4.2282F, 3.0303F, 2.0F, 14.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(26, 83).addBox(3.9695F, -2.2282F, 1.0303F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(18, 33).addBox(3.9695F, -5.3282F, 7.0303F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 83).addBox(3.9695F, -3.3282F, 9.0303F, 2.0F, 17.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 59).addBox(3.9695F, -6.2282F, 4.0303F, 2.0F, 21.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 1.2217F, 0.0F, 0.3491F));
        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(@NotNull Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack matrixStack, @NotNull VertexConsumer buffer, int packedLight, int packedOverlay, int k) {
        leftWing.render(matrixStack, buffer, packedLight, packedOverlay);
        rightWing.render(matrixStack, buffer, packedLight, packedOverlay);
    }
}