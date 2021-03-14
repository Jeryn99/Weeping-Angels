package me.suff.mc.angels.client.models.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

/**
 * Angel Type: 4 Created by Craig on 11/03/2020 @ 20:58
 */
public class ModelWeepingVillager extends EntityModel< WeepingAngelEntity > implements IAngelModel {

    private final ResourceLocation TEXTURE = new ResourceLocation(WeepingAngels.MODID,
            "textures/entities/weeping_villager.png");

    private final ModelRenderer body;
    private final ModelRenderer wing0;
    private final ModelRenderer wing2;
    private final ModelRenderer head;
    private final ModelRenderer nose;
    private final ModelRenderer arms;
    private final ModelRenderer leg0;
    private final ModelRenderer leg1;
    private WeepingAngelPose weepingAngelPose = WeepingAngelPose.ANGRY;

    /**
     * Angel Type: 4
     */
    public ModelWeepingVillager() {
        textureWidth = 64;
        textureHeight = 64;

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 18.0F, 0.0F);
        body.setTextureOffset(27, 14).addBox(-4.0F, -18.0F, -3.0F, 8.0F, 18.0F, 6.0F, 0.5F, false);

        wing0 = new ModelRenderer(this);
        wing0.setRotationPoint(-1.5F, -15.0F, 3.25F);
        body.addChild(wing0);
        setRotationAngle(wing0, 0.1745F, -1.0472F, 0.0F);
        wing0.setTextureOffset(50, 0).addBox(-0.5F, -3.0F, 0.75F, 1.0F, 7.0F, 6.0F, 0.5F, false);
        wing0.setTextureOffset(52, 13).addBox(-0.5F, 5.0F, 2.75F, 1.0F, 2.0F, 4.0F, 0.5F, false);
        wing0.setTextureOffset(47, 0).addBox(-0.5F, 8.0F, 3.75F, 1.0F, 2.0F, 3.0F, 0.5F, false);
        wing0.setTextureOffset(59, 2).addBox(-0.5F, 11.0F, 5.75F, 1.0F, 2.0F, 1.0F, 0.5F, false);

        wing2 = new ModelRenderer(this);
        wing2.setRotationPoint(1.5F, -15.0F, 3.25F);
        body.addChild(wing2);
        setRotationAngle(wing2, 0.1745F, 1.0472F, 0.0F);
        wing2.setTextureOffset(50, 0).addBox(-0.5F, -3.0F, 0.75F, 1.0F, 7.0F, 6.0F, 0.5F, false);
        wing2.setTextureOffset(52, 13).addBox(-0.5F, 5.0F, 2.75F, 1.0F, 2.0F, 4.0F, 0.5F, false);
        wing2.setTextureOffset(47, 0).addBox(-0.5F, 8.0F, 3.75F, 1.0F, 2.0F, 3.0F, 0.5F, false);
        wing2.setTextureOffset(59, 2).addBox(-0.5F, 11.0F, 5.75F, 1.0F, 2.0F, 1.0F, 0.5F, false);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, -18.0F, 0.0F);
        body.addChild(head);
        head.setTextureOffset(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, 0.0F, false);

        nose = new ModelRenderer(this);
        nose.setRotationPoint(0.0F, -3.0F, -4.0F);
        head.addChild(nose);
        nose.setTextureOffset(25, 1).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        arms = new ModelRenderer(this);
        arms.setRotationPoint(0.0F, -16.0F, 0.0F);
        body.addChild(arms);
        setRotationAngle(arms, -1.1781F, 0.0F, 0.0F);
        arms.setTextureOffset(30, 39).addBox(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
        arms.setTextureOffset(13, 35).addBox(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
        arms.setTextureOffset(13, 35).addBox(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

        leg0 = new ModelRenderer(this);
        leg0.setRotationPoint(2.0F, 12.0F, 0.0F);
        leg0.setTextureOffset(0, 19).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        leg1 = new ModelRenderer(this);
        leg1.setRotationPoint(-2.0F, 12.0F, 0.0F);
        leg1.setTextureOffset(0, 19).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);


    }

    @Override
    public void setRotationAngles(WeepingAngelEntity weepingAngelEntity, float v, float v1, float v2, float v3, float v4) {
        WeepingAngelPose pose = weepingAngelPose;
        if (weepingAngelEntity != null) {
            pose = WeepingAngelPose.getPose(weepingAngelEntity.getAngelPose());
        }

        nose.rotateAngleX = (float) Math.toRadians(0);
        nose.rotateAngleY = (float) Math.toRadians(0);
        nose.rotateAngleZ = (float) Math.toRadians(0);

        body.rotateAngleX = (float) Math.toRadians(0);
        body.rotateAngleY = (float) Math.toRadians(0);
        body.rotateAngleZ = (float) Math.toRadians(0);

        arms.rotateAngleX = (float) Math.toRadians(-67.5);
        arms.rotateAngleY = (float) Math.toRadians(0);
        arms.rotateAngleZ = (float) Math.toRadians(0);

        wing0.rotateAngleX = 0.1745F;
        wing0.rotateAngleY = -1.0472F;
        wing0.rotateAngleZ = (float) Math.toRadians(0);

        wing2.rotateAngleX = 0.1745F;
        wing2.rotateAngleY = 1.0472F;
        wing2.rotateAngleZ = (float) Math.toRadians(0);

        if (pose == WeepingAngelPose.HIDING) {
            head.rotateAngleX = (float) Math.toRadians(32.5);
            head.rotateAngleY = (float) Math.toRadians(0);
            head.rotateAngleZ = (float) Math.toRadians(0);

            nose.rotateAngleX = (float) Math.toRadians(-17.5);
            arms.rotateAngleX = (float) Math.toRadians(-82.5);
            return;
        }

        if (pose == WeepingAngelPose.IDLE || pose == WeepingAngelPose.SHY) {
            head.rotateAngleX = (float) Math.toRadians(0);
            head.rotateAngleY = (float) Math.toRadians(0);
            head.rotateAngleZ = (float) Math.toRadians(0);

            body.rotateAngleX = (float) Math.toRadians(0);
            body.rotateAngleY = (float) Math.toRadians(0);
            body.rotateAngleZ = (float) Math.toRadians(0);

            arms.rotateAngleX = (float) Math.toRadians(-67.5);
            arms.rotateAngleY = (float) Math.toRadians(0);
            arms.rotateAngleZ = (float) Math.toRadians(0);
            return;
        }

        if (pose.getEmotion() == WeepingAngelPose.Emotion.ANGRY) {
            head.rotateAngleX = (float) Math.toRadians(-32.5);
            body.rotateAngleX = (float) Math.toRadians(17.5);
            wing2.rotateAngleX = 0.1745F * 2;
            wing0.rotateAngleX = 0.1745F * 2;
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
    public ResourceLocation generateTex(WeepingAngelPose pose, WeepingAngelEntity.AngelVariants angelVariants) {
        return null;
    }

    public void setRotationAngle(ModelRenderer ModelRenderer, float x, float y, float z) {
        ModelRenderer.rotateAngleX = x;
        ModelRenderer.rotateAngleY = y;
        ModelRenderer.rotateAngleZ = z;
    }

    @Override
    public ResourceLocation getTextureForPose(Object angel, WeepingAngelPose pose) {
        return TEXTURE;
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        leg0.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        leg1.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
    }

}
