package me.suff.mc.angels.client.models.block;// Made with Blockbench 3.8.4
// Exported for Minecraft version 1.15 - 1.16
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.suff.mc.angels.client.models.entity.WAModels;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class CoffinModel extends EntityModel<Entity> {
    public final ModelPart door;
    public final ModelPart goodHandle;
    public final ModelPart badHandle;
    private final ModelPart main;

    public CoffinModel(ModelPart root) {
        this.door = root.getChild("door");
        this.goodHandle = root.getChild("goodHandle");
        this.badHandle = root.getChild("badHandle");
        this.main = root.getChild("main");
    }

    public static LayerDefinition getModelData(){
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(0, 38).addBox(-15.0F, -51.0F, 3.0F, 1.0F, 17.0F, 11.0F, new CubeDeformation(0.0F)).mirror(true)
                .texOffs(0, 38).addBox(0.0F, -51.0F, 3.0F, 1.0F, 17.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(59, 61).addBox(-15.0F, -68.0F, 3.0F, 1.0F, 6.0F, 11.0F, new CubeDeformation(0.0F)).mirror(true)
                .texOffs(59, 61).addBox(0.0F, -68.0F, 3.0F, 1.0F, 6.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(13, 61).addBox(-16.0F, -63.0F, 3.0F, 1.0F, 13.0F, 11.0F, new CubeDeformation(0.0F)).mirror(true)
                .texOffs(13, 61).addBox(1.0F, -63.0F, 3.0F, 1.0F, 13.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(70, 28).addBox(-15.0F, -69.0F, 3.0F, 16.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(36, 0).addBox(-15.0F, -34.0F, 3.0F, 16.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(36, 36).addBox(-15.0F, -69.0F, 13.0F, 16.0F, 35.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(7.0F, 57.0F, -6.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition door = partdefinition.addOrReplaceChild("door", CubeListBuilder.create().texOffs(0, 0).addBox(-17.0F, -16.0F, -2.0F, 16.0F, 36.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(36, 12).addBox(-18.0F, -10.0F, -2.0F, 18.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(9.0F, 4.0F, -3.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition goodHandle = door.addOrReplaceChild("goodHandle", CubeListBuilder.create().texOffs(37, 29).addBox(0.25F, -3.275F, -0.125F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-17.25F, -1.725F, -2.625F, 0.0F, 0.0F, 0.0F));

        PartDefinition badHandle = door.addOrReplaceChild("badHandle", CubeListBuilder.create(), PartPose.offsetAndRotation(-9.0F, 20.0F, 3.0F, 0.0F, 0.0F, 0.0F));
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
        main.render(matrixStack, buffer, packedLight, packedOverlay);
        door.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}