package me.suff.mc.angels.client.models.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import me.suff.mc.angels.common.variants.AbstractVariant;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

/**
 * Angel Type: 4 Created by Craig on 11/03/2020 @ 20:58
 */
public class ModelWeepingVillager extends EntityModel<WeepingAngelEntity> implements IAngelModel {

    private final ResourceLocation TEXTURE = new ResourceLocation(WeepingAngels.MODID, "textures/entities/weeping_villager.png");

    private final ModelPart body;
    private final ModelPart wing0;
    private final ModelPart wing2;
    private final ModelPart head;
    private final ModelPart nose;
    private final ModelPart arms;
    private final ModelPart leg0;
    private final ModelPart leg1;
    private WeepingAngelPose weepingAngelPose = WeepingAngelPose.ANGRY;

    public ModelWeepingVillager(ModelPart root) {
        body = root.getChild("body");
        wing0 = root.getChild("wing0");
        wing2 = root.getChild("wing2");
        head = root.getChild("head");
        nose = root.getChild("nose");
        arms = root.getChild("arms");
        leg0 = root.getChild("leg0");
        leg1 = root.getChild("leg1");
    }

    public static LayerDefinition getModelData(){
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(27, 14).addBox(-4.0F, -18.0F, -3.0F, 8.0F, 18.0F, 6.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offsetAndRotation(0.0F, 18.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition wing0 = body.addOrReplaceChild("wing0", CubeListBuilder.create().texOffs(50, 0).addBox(-0.5F, -3.0F, 0.75F, 1.0F, 7.0F, 6.0F, new CubeDeformation(0.5F)).mirror(false)
                .texOffs(52, 13).addBox(-0.5F, 5.0F, 2.75F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.5F)).mirror(false)
                .texOffs(47, 0).addBox(-0.5F, 8.0F, 3.75F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.5F)).mirror(false)
                .texOffs(59, 2).addBox(-0.5F, 11.0F, 5.75F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offsetAndRotation(-1.5F, -15.0F, 3.25F, 0.1745F, -1.0472F, 0.0F));

        PartDefinition wing2 = body.addOrReplaceChild("wing2", CubeListBuilder.create().texOffs(50, 0).addBox(-0.5F, -3.0F, 0.75F, 1.0F, 7.0F, 6.0F, new CubeDeformation(0.5F)).mirror(false)
                .texOffs(52, 13).addBox(-0.5F, 5.0F, 2.75F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.5F)).mirror(false)
                .texOffs(47, 0).addBox(-0.5F, 8.0F, 3.75F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.5F)).mirror(false)
                .texOffs(59, 2).addBox(-0.5F, 11.0F, 5.75F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offsetAndRotation(1.5F, -15.0F, 3.25F, 0.1745F, 1.0472F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -18.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(25, 1).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -3.0F, -4.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition arms = body.addOrReplaceChild("arms", CubeListBuilder.create().texOffs(30, 39).addBox(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(13, 35).addBox(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(13, 35).addBox(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -16.0F, 0.0F, -1.1781F, 0.0F, 0.0F));

        PartDefinition leg0 = partdefinition.addOrReplaceChild("leg0", CubeListBuilder.create().texOffs(0, 19).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, 12.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition leg1 = partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(0, 19).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 12.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(WeepingAngelEntity weepingAngelEntity, float v, float v1, float v2, float v3, float v4) {
        WeepingAngelPose pose = weepingAngelPose;
        if (weepingAngelEntity != null) {
            pose = WeepingAngelPose.getPose(weepingAngelEntity.getAngelPose());
        }

        nose.xRot = (float) Math.toRadians(0);
        nose.yRot = (float) Math.toRadians(0);
        nose.zRot = (float) Math.toRadians(0);

        body.xRot = (float) Math.toRadians(0);
        body.yRot = (float) Math.toRadians(0);
        body.zRot = (float) Math.toRadians(0);

        arms.xRot = (float) Math.toRadians(-67.5);
        arms.yRot = (float) Math.toRadians(0);
        arms.zRot = (float) Math.toRadians(0);

        wing0.xRot = 0.1745F;
        wing0.yRot = -1.0472F;
        wing0.zRot = (float) Math.toRadians(0);

        wing2.xRot = 0.1745F;
        wing2.yRot = 1.0472F;
        wing2.zRot = (float) Math.toRadians(0);

        if (pose == WeepingAngelPose.HIDING) {
            head.xRot = (float) Math.toRadians(32.5);
            head.yRot = (float) Math.toRadians(0);
            head.zRot = (float) Math.toRadians(0);

            nose.xRot = (float) Math.toRadians(-17.5);
            arms.xRot = (float) Math.toRadians(-82.5);
            return;
        }

        if (pose == WeepingAngelPose.IDLE || pose == WeepingAngelPose.SHY) {
            head.xRot = (float) Math.toRadians(0);
            head.yRot = (float) Math.toRadians(0);
            head.zRot = (float) Math.toRadians(0);

            body.xRot = (float) Math.toRadians(0);
            body.yRot = (float) Math.toRadians(0);
            body.zRot = (float) Math.toRadians(0);

            arms.xRot = (float) Math.toRadians(-67.5);
            arms.yRot = (float) Math.toRadians(0);
            arms.zRot = (float) Math.toRadians(0);
            return;
        }

        if (pose.getEmotion() == WeepingAngelPose.Emotion.ANGRY) {
            head.xRot = (float) Math.toRadians(-32.5);
            body.xRot = (float) Math.toRadians(17.5);
            wing2.xRot = 0.1745F * 2;
            wing0.xRot = 0.1745F * 2;
            return;
        }

    }

    @Override
    public WeepingAngelPose getAngelPose() {
        return weepingAngelPose;
    }

    @Override
    public void setAngelPose(WeepingAngelPose angelPose) {
        this.weepingAngelPose = angelPose;
    }

    @Override
    public ResourceLocation generateTex(WeepingAngelPose pose, AbstractVariant angelVariants) {
        return null;
    }

    public void setRotationAngle(ModelPart ModelRenderer, float x, float y, float z) {
        ModelRenderer.xRot = x;
        ModelRenderer.yRot = y;
        ModelRenderer.zRot = z;
    }

    @Override
    public ResourceLocation getTextureForPose(Object angel, WeepingAngelPose pose) {
        return TEXTURE;
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        leg0.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        leg1.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
    }

}
