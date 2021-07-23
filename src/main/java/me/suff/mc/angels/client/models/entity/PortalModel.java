package me.suff.mc.angels.client.models.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.suff.mc.angels.common.entities.AnomalyEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class PortalModel extends EntityModel<AnomalyEntity>  {

    private final ModelPart PortalMain;
    private final ModelPart PortalFrontage;
    private final ModelPart PortalFrontage2;

    public PortalModel(ModelPart root) {
        this.PortalMain = root.getChild("PortalMain");
        this.PortalFrontage2 = root.getChild("PortalFrontage2");
        this.PortalFrontage = root.getChild("PortalFrontage");
    }

    public static LayerDefinition getModelData(){
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition PortalMain = partdefinition.addOrReplaceChild("PortalMain", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, -16.0F, 0.0F, 32.0F, 32.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 8.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition PortalFrontage = partdefinition.addOrReplaceChild("PortalFrontage", CubeListBuilder.create().texOffs(0, 32).addBox(-16.0F, -16.0F, -1.0F, 32.0F, 32.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 8.0F, 0.0F, 0.0F, 0.0F, 0.7854F));
        PartDefinition PortalFrontage2 = partdefinition.addOrReplaceChild("PortalFrontage2", CubeListBuilder.create().texOffs(0, 32).addBox(-16.0F, -16.0F, 1.0F, 32.0F, 32.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 8.0F, 0.0F, 0.0F, 0.0F, -0.7854F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(AnomalyEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        PortalMain.zRot = -entityIn.tickCount * 2;
        PortalFrontage.zRot = entityIn.tickCount / 2F;
        PortalFrontage2.zRot = -entityIn.tickCount / 2F;
    }

    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        PortalMain.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        PortalFrontage.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        PortalFrontage2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
    }

}
