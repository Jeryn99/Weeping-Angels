package mc.craig.software.angels.client.models.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class CoffinModel extends EntityModel<Entity> {
    private final ModelPart coffin_body;
    private final ModelPart coffin_door;

    public CoffinModel(ModelPart root) {
        this.coffin_body = root.getChild("body");
        this.coffin_door = root.getChild("door");
    }

    public static LayerDefinition getModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 100).addBox(-15.0F, -51.0F, 3.0F, 1.0F, 17.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(0, 38).addBox(0.0F, -51.0F, 3.0F, 1.0F, 17.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(47, 111).addBox(-15.0F, -68.0F, 3.0F, 1.0F, 6.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(59, 61).addBox(0.0F, -68.0F, 3.0F, 1.0F, 6.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(23, 104).addBox(-16.0F, -63.0F, 3.0F, 1.0F, 13.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(13, 61).addBox(1.0F, -63.0F, 3.0F, 1.0F, 13.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(70, 28).addBox(-15.0F, -69.0F, 3.0F, 16.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(36, 0).addBox(-15.0F, -34.0F, 3.0F, 16.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(36, 36).addBox(-15.0F, -69.0F, 13.0F, 16.0F, 35.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, 57.0F, -6.0F));

        PartDefinition door = partdefinition.addOrReplaceChild("door", CubeListBuilder.create().texOffs(0, 0).addBox(-17.0F, -16.0F, -2.0F, 16.0F, 36.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(36, 12).addBox(-18.0F, -10.0F, -2.0F, 18.0F, 13.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(37, 29).addBox(-17.0F, -5.0F, -2.75F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, 4.0F, -3.0F));

        PartDefinition cube_r1 = door.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(41, 29).addBox(0.1927F, -3.2401F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.25F, -1.725F, -2.625F, 0.1745F, 0.0F, 0.2182F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    /**
     * Sets this entity's model rotation angles
     *
     * @param pEntityIn
     * @param pLimbSwing
     * @param pLimbSwingAmount
     * @param pAgeInTicks
     * @param pNetHeadYaw
     * @param pHeadPitch
     */
    @Override
    public void setupAnim(Entity pEntityIn, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        coffin_body.render(matrixStack, buffer, packedLight, packedOverlay);
        coffin_door.render(matrixStack, buffer, packedLight, packedOverlay);
    }
}