package me.suff.mc.angels.client.models.entity;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import me.suff.mc.angels.common.variants.AbstractVariant;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

/**
 * Angel Type: 4 Created by Craig on 11/03/2020 @ 20:58
 */
public class ModelWeepingVillager extends EntityModel<WeepingAngelEntity> implements IAngelModel {

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
        texWidth = 64;
        texHeight = 64;

        body = new ModelRenderer(this);
        body.setPos(0.0F, 18.0F, 0.0F);
        body.texOffs(27, 14).addBox(-4.0F, -18.0F, -3.0F, 8.0F, 18.0F, 6.0F, 0.5F, false);

        wing0 = new ModelRenderer(this);
        wing0.setPos(-1.5F, -15.0F, 3.25F);
        body.addChild(wing0);
        setRotationAngle(wing0, 0.1745F, -1.0472F, 0.0F);
        wing0.texOffs(50, 0).addBox(-0.5F, -3.0F, 0.75F, 1.0F, 7.0F, 6.0F, 0.5F, false);
        wing0.texOffs(52, 13).addBox(-0.5F, 5.0F, 2.75F, 1.0F, 2.0F, 4.0F, 0.5F, false);
        wing0.texOffs(47, 0).addBox(-0.5F, 8.0F, 3.75F, 1.0F, 2.0F, 3.0F, 0.5F, false);
        wing0.texOffs(59, 2).addBox(-0.5F, 11.0F, 5.75F, 1.0F, 2.0F, 1.0F, 0.5F, false);

        wing2 = new ModelRenderer(this);
        wing2.setPos(1.5F, -15.0F, 3.25F);
        body.addChild(wing2);
        setRotationAngle(wing2, 0.1745F, 1.0472F, 0.0F);
        wing2.texOffs(50, 0).addBox(-0.5F, -3.0F, 0.75F, 1.0F, 7.0F, 6.0F, 0.5F, false);
        wing2.texOffs(52, 13).addBox(-0.5F, 5.0F, 2.75F, 1.0F, 2.0F, 4.0F, 0.5F, false);
        wing2.texOffs(47, 0).addBox(-0.5F, 8.0F, 3.75F, 1.0F, 2.0F, 3.0F, 0.5F, false);
        wing2.texOffs(59, 2).addBox(-0.5F, 11.0F, 5.75F, 1.0F, 2.0F, 1.0F, 0.5F, false);

        head = new ModelRenderer(this);
        head.setPos(0.0F, -18.0F, 0.0F);
        body.addChild(head);
        head.texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, 0.0F, false);

        nose = new ModelRenderer(this);
        nose.setPos(0.0F, -3.0F, -4.0F);
        head.addChild(nose);
        nose.texOffs(25, 1).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        arms = new ModelRenderer(this);
        arms.setPos(0.0F, -16.0F, 0.0F);
        body.addChild(arms);
        setRotationAngle(arms, -1.1781F, 0.0F, 0.0F);
        arms.texOffs(30, 39).addBox(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
        arms.texOffs(13, 35).addBox(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
        arms.texOffs(13, 35).addBox(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

        leg0 = new ModelRenderer(this);
        leg0.setPos(2.0F, 12.0F, 0.0F);
        leg0.texOffs(0, 19).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        leg1 = new ModelRenderer(this);
        leg1.setPos(-2.0F, 12.0F, 0.0F);
        leg1.texOffs(0, 19).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);


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
    public Iterable<ModelRenderer> wings(MatrixStack pose) {
        pose.translate(0, 1.2, 0);
        return ImmutableList.of(wing0, wing2);
    }

    @Override
    public ModelRenderer getSantaAttachment(MatrixStack pose, boolean b) {
        return null;
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
        return TEXTURE;
    }

    public void setRotationAngle(ModelRenderer ModelRenderer, float x, float y, float z) {
        ModelRenderer.xRot = x;
        ModelRenderer.yRot = y;
        ModelRenderer.zRot = z;
    }

    @Override
    public ResourceLocation getTextureForPose(Object angel, WeepingAngelPose pose) {
        return TEXTURE;
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        leg0.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        leg1.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
    }

}
