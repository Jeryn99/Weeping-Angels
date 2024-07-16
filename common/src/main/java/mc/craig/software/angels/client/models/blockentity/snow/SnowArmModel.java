package mc.craig.software.angels.client.models.blockentity.snow;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class SnowArmModel extends EntityModel<Entity> {
    private final ModelPart arm;

    public SnowArmModel(ModelPart root) {
        arm = root.getChild("arm");
    }

    public static LayerDefinition meshLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition arm = partdefinition.addOrReplaceChild("arm", CubeListBuilder.create().texOffs(24, 59).addBox(-2.0F, -5.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 23.0F, 0.0F, -2.6616F, -0.4363F, 0.5236F));
        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int k) {
        arm.render(poseStack, vertexConsumer, packedLight, packedOverlay);

    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

}