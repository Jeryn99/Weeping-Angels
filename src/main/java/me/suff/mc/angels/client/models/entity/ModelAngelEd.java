package me.suff.mc.angels.client.models.entity;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import me.suff.mc.angels.common.variants.AbstractVariant;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

/**
 * Angel Type: 1
 * Weeping Angel - EdusgprNetwork Created using Tabula 5.1.0
 */
public class ModelAngelEd extends SegmentedModel<WeepingAngelEntity> implements IAngelModel {

    private final ResourceLocation TEXTURE = new ResourceLocation(WeepingAngels.MODID,
            "textures/entities/angel_ed.png");

    private final ModelRenderer right_wing_0;
    private final ModelRenderer left_wing_0;
    private final ModelRenderer back_cloth_2;
    private final ModelRenderer head_2;
    private final ModelRenderer body_2;
    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer left_arm;
    private final ModelRenderer right_arm;
    private final ModelRenderer cloth_0;
    private final ModelRenderer cloth_1;
    private final ModelRenderer cloth_2;
    private final ModelRenderer back_cloth;
    private final ModelRenderer left_wing_1;
    private final ModelRenderer right_wing_1;
    private final ModelRenderer nose;
    private final ModelRenderer face;
    private final ModelRenderer right_eyebrow;
    private final ModelRenderer left_eyebrow;
    private final ModelRenderer coverup;
    private final ModelRenderer angry_mouth;
    private final ModelRenderer teeth;
    private final ModelRenderer teeth_1;
    private final ModelRenderer teeth_2;
    private final ModelRenderer teeth_3;
    private final ModelRenderer teeth_4;
    private final ModelRenderer teeth_5;
    private final ModelRenderer wrist_left;
    private final ModelRenderer wrist_right;
    private final ModelRenderer zeth;
    private final ModelRenderer left_wing_2;
    private final ModelRenderer left_wing_3;
    private final ModelRenderer left_wing_4;
    private final ModelRenderer right_wing_2;
    private final ModelRenderer right_wing_3;
    private final ModelRenderer right_wing_4;

    private WeepingAngelPose weepingAngelPose = WeepingAngelPose.ANGRY;

    /**
     * Angel Type: 1
     */
    public ModelAngelEd() {
        texWidth = 88;
        texHeight = 88;
        teeth_1 = new ModelRenderer(this, 63, 39);
        teeth_1.setPos(0.8F, -3.0F, 0.0F);
        teeth_1.addBox(-1.0F, 0.0F, -0.03F, 1, 1, 1, 0.0F);
        setRotateAngle(teeth_1, 0.0F, 0.0F, -0.7853981633974483F);
        wrist_left = new ModelRenderer(this, 34, 52);
        wrist_left.setPos(0.0F, 4.0F, 2.0F);
        wrist_left.addBox(-1.0F, 0.0F, -4.0F, 3, 6, 4, 0.0F);
        cloth_1 = new ModelRenderer(this, 34, 44);
        cloth_1.setPos(0.0F, 24.0F, 3.3F);
        cloth_1.addBox(-4.0F, -1.0F, -2.5F, 8, 1, 5, 0.0F);
        left_wing_4 = new ModelRenderer(this, 22, 36);
        left_wing_4.setPos(0.0F, 5.0F, 0.0F);
        left_wing_4.addBox(-1.0F, 0.0F, -2.0F, 2, 5, 2, 0.0F);
        setRotateAngle(left_wing_4, -1.1383037381507017F, 0.0F, 0.0F);
        teeth_3 = new ModelRenderer(this, 63, 39);
        teeth_3.setPos(-0.5F, -3.0F, 0.0F);
        teeth_3.addBox(-1.0F, 0.0F, -0.03F, 1, 1, 1, 0.0F);
        setRotateAngle(teeth_3, 0.0F, 0.0F, -0.7853981633974483F);
        right_arm = new ModelRenderer(this, 0, 32);
        right_arm.setPos(-5.0F, 2.5F, 0.0F);
        right_arm.addBox(-2.0F, -2.0F, -2.0F, 3, 6, 4, 0.0F);
        setRotateAngle(right_arm, 0.0F, 0.0F, 0.10000736613927509F);
        head = new ModelRenderer(this, 0, 16);
        head.setPos(0.0F, 0.0F, 0.0F);
        head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        cloth_0 = new ModelRenderer(this, 9, 43);
        cloth_0.setPos(0.0F, 24.0F, 0.0F);
        cloth_0.addBox(-5.0F, -1.0F, -2.0F, 10, 1, 5, 0.0F);
        teeth_2 = new ModelRenderer(this, 63, 39);
        teeth_2.setPos(0.5F, -3.0F, 0.0F);
        teeth_2.addBox(-1.0F, 0.0F, -0.03F, 1, 1, 1, 0.0F);
        setRotateAngle(teeth_2, 0.0F, 0.0F, -0.7853981633974483F);
        nose = new ModelRenderer(this, 32, 0);
        nose.setPos(0.0F, -4.5F, -4.0F);
        nose.addBox(-0.5F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        setRotateAngle(nose, -0.2246238747316702F, 0.0F, 0.0F);
        coverup = new ModelRenderer(this, 54, 34);
        coverup.setPos(0.0F, -6.0F, -4.0F);
        coverup.addBox(-4.0F, 0.0F, -0.03F, 8, 1, 1, 0.0F);
        face = new ModelRenderer(this, 54, 28);
        face.setPos(0.0F, -4.5F, -4.0F);
        face.addBox(-3.0F, 0.0F, -0.01F, 6, 3, 1, 0.0F);
        back_cloth = new ModelRenderer(this, 60, 44);
        back_cloth.setPos(0.0F, 12.0F, 2.0F);
        back_cloth.addBox(-3.0F, 0.0F, -3.0F, 6, 12, 3, 0.0F);
        setRotateAngle(back_cloth, 0.2792526803190927F, 0.0F, 0.0F);
        teeth_5 = new ModelRenderer(this, 63, 39);
        teeth_5.setPos(0.15F, -3.0F, 0.0F);
        teeth_5.addBox(-1.0F, 0.0F, -0.03F, 1, 1, 1, 0.0F);
        setRotateAngle(teeth_5, 0.0F, 0.0F, -0.7853981633974483F);
        cloth_2 = new ModelRenderer(this, 10, 32);
        cloth_2.setPos(0.0F, 24.0F, 6.6F);
        cloth_2.addBox(-3.0F, -1.0F, -2.5F, 6, 1, 3, 0.0F);
        left_wing_1 = new ModelRenderer(this, 24, 0);
        left_wing_1.setPos(2.4F, 2.0F, 1.5F);
        left_wing_1.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        setRotateAngle(left_wing_1, 1.53588974175501F, 0.9424777960769379F, 0.0F);
        head_2 = new ModelRenderer(this, 0, 0);
        head_2.setPos(0.0F, 0.0F, 0.0F);
        head_2.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
        right_wing_2 = new ModelRenderer(this, 46, 27);
        right_wing_2.setPos(0.0F, 4.0F, -1.0F);
        right_wing_2.addBox(-1.0F, 0.0F, 0.0F, 2, 7, 2, 0.0F);
        setRotateAngle(right_wing_2, 1.2292353921796064F, 0.0F, 0.0F);
        zeth = new ModelRenderer(this, 20, 50);
        zeth.setPos(0.0F, 0.0F, -2.0F);
        zeth.addBox(-4.5F, -1.0F, -0.6F, 9, 1, 1, 0.0F);
        back_cloth_2 = new ModelRenderer(this, 0, 49);
        back_cloth_2.setPos(0.0F, 0.0F, 0.0F);
        back_cloth_2.addBox(-3.0F, 0.0F, -3.0F, 6, 12, 3, 0.25F);
        setRotateAngle(back_cloth_2, 0.0F, 0.0F, 0.0F);
        teeth_4 = new ModelRenderer(this, 63, 39);
        teeth_4.setPos(-0.15F, -3.0F, 0.0F);
        teeth_4.addBox(-1.0F, 0.0F, -0.03F, 1, 1, 1, 0.0F);
        setRotateAngle(teeth_4, 0.0F, 0.0F, -0.7853981633974483F);
        right_wing_4 = new ModelRenderer(this, 24, 16);
        right_wing_4.setPos(0.0F, 5.0F, 0.0F);
        right_wing_4.addBox(-1.0F, 0.0F, -2.0F, 2, 5, 2, 0.0F);
        setRotateAngle(right_wing_4, -1.1383037381507017F, 0.0F, 0.0F);
        body_2 = new ModelRenderer(this, 32, 0);
        body_2.setPos(0.0F, 0.0F, 0.0F);
        body_2.addBox(-4.0F, 0.0F, -2.0F, 8, 23, 4, 0.25F);
        right_wing_1 = new ModelRenderer(this, 0, 0);
        right_wing_1.setPos(-2.4F, 2.0F, 1.5F);
        right_wing_1.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        setRotateAngle(right_wing_1, 1.53588974175501F, -0.9424777960769379F, 0.0F);
        left_eyebrow = new ModelRenderer(this, 62, 32);
        left_eyebrow.setPos(3.0F, -4.5F, -4.0F);
        left_eyebrow.addBox(-2.5F, -1.0F, -0.02F, 3, 1, 1, 0.0F);
        angry_mouth = new ModelRenderer(this, 63, 36);
        angry_mouth.setPos(0.0F, 0.0F, -4.0F);
        angry_mouth.addBox(-2.0F, -1.8F, -0.02F, 4, 2, 1, 0.0F);
        right_wing_3 = new ModelRenderer(this, 0, 16);
        right_wing_3.setPos(0.0F, 7.0F, 2.0F);
        right_wing_3.addBox(-1.0F, 0.0F, -2.0F, 2, 5, 2, 0.0F);
        setRotateAngle(right_wing_3, -1.2292353921796064F, 0.0F, 0.0F);
        right_eyebrow = new ModelRenderer(this, 54, 32);
        right_eyebrow.setPos(-3.0F, -4.5F, -4.0F);
        right_eyebrow.addBox(-0.5F, -1.0F, -0.02F, 3, 1, 1, 0.0F);
        left_wing_2 = new ModelRenderer(this, 78, 42);
        left_wing_2.setPos(0.0F, 4.0F, -1.0F);
        left_wing_2.addBox(-1.0F, 0.0F, 0.0F, 2, 7, 2, 0.0F);
        setRotateAngle(left_wing_2, 1.2292353921796064F, 0.0F, 0.0F);
        body = new ModelRenderer(this, 56, 0);
        body.setPos(0.0F, 0.0F, 0.0F);
        body.addBox(-4.0F, 0.0F, -2.0F, 8, 24, 4, 0.0F);
        right_wing_0 = new ModelRenderer(this, 0, 49);
        right_wing_0.setPos(-2.4F, 2.0F, 1.5F);
        right_wing_0.addBox(0.0F, 0.0F, -13.0F, 1, 11, 18, 0.0F);
        setRotateAngle(right_wing_0, 1.53588974175501F, -0.9424777960769379F, 0.0F);
        teeth = new ModelRenderer(this, 63, 39);
        teeth.setPos(-0.8F, -3.0F, 0.0F);
        teeth.addBox(-1.0F, 0.0F, -0.03F, 1, 1, 1, 0.0F);
        setRotateAngle(teeth, 0.0F, 0.0F, -0.7853981633974483F);
        left_wing_0 = new ModelRenderer(this, 38, 50);
        left_wing_0.setPos(2.4F, 2.0F, 1.5F);
        left_wing_0.addBox(-1.0F, 0.0F, -13.0F, 1, 11, 18, 0.0F);
        setRotateAngle(left_wing_0, 1.53588974175501F, 0.9424777960769379F, 0.0F);
        left_wing_3 = new ModelRenderer(this, 14, 36);
        left_wing_3.setPos(0.0F, 7.0F, 2.0F);
        left_wing_3.addBox(-1.0F, 0.0F, -2.0F, 2, 5, 2, 0.0F);
        setRotateAngle(left_wing_3, -1.2292353921796064F, 0.0F, 0.0F);
        left_arm = new ModelRenderer(this, 32, 27);
        left_arm.setPos(5.0F, 2.5F, -0.0F);
        left_arm.addBox(-1.0F, -2.0F, -2.0F, 3, 6, 4, 0.0F);
        setRotateAngle(left_arm, 0.0F, 0.0F, -0.10000736613927509F);
        wrist_right = new ModelRenderer(this, 20, 52);
        wrist_right.setPos(0.0F, 4.0F, 2.0F);
        wrist_right.addBox(-2.0F, 0.0F, -4.0F, 3, 6, 4, 0.0F);
        angry_mouth.addChild(teeth_1);
        left_arm.addChild(wrist_left);
        left_wing_3.addChild(left_wing_4);
        angry_mouth.addChild(teeth_3);
        angry_mouth.addChild(teeth_2);

        head.addChild(nose);
        head.addChild(coverup);
        head.addChild(face);
        angry_mouth.addChild(teeth_5);
        right_wing_1.addChild(right_wing_2);
        cloth_0.addChild(zeth);
        angry_mouth.addChild(teeth_4);
        right_wing_3.addChild(right_wing_4);
        head.addChild(left_eyebrow);
        head.addChild(angry_mouth);
        right_wing_2.addChild(right_wing_3);
        head.addChild(right_eyebrow);
        left_wing_1.addChild(left_wing_2);
        angry_mouth.addChild(teeth);
        left_wing_2.addChild(left_wing_3);
        right_arm.addChild(wrist_right);
        head.addChild(head_2);
        back_cloth.addChild(back_cloth_2);
    }

    public void setPose(WeepingAngelPose weepingAngelPose) {
        this.weepingAngelPose = weepingAngelPose;
    }

    public WeepingAngelPose getAngelPoses() {
        return weepingAngelPose;
    }

    @Override
    public void setupAnim(WeepingAngelEntity weepingAngelEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        right_arm.y = 2.5F;
        left_arm.y = 2.5F;
        left_arm.xRot = 0;
        left_arm.yRot = 0;
        left_arm.zRot = 0;
        right_arm.xRot = 0;
        right_arm.yRot = 0;
        right_arm.zRot = 0;

        WeepingAngelPose pose = weepingAngelPose;
        if (weepingAngelEntity != null) {
            pose = WeepingAngelPose.getPose(weepingAngelEntity.getAngelPose());
        }

        if (pose != null) {

            if (pose == WeepingAngelPose.FURIOUS) {
                left_arm.xRot = -1.5F;
                left_arm.yRot = 0.31F;
                left_arm.zRot = -0.087F;
                right_arm.xRot = -1.5F;
                right_arm.yRot = -0.31F;
                right_arm.zRot = 0.087F;
                wrist_right.xRot = -0.52F;
                wrist_left.xRot = -0.52F;
                return;
            }

            if (pose == WeepingAngelPose.SHY) {
                left_arm.xRot = 0;
                left_arm.yRot = 0.4F;
                left_arm.zRot = -0.3F;
                right_arm.xRot = -1.3F;
                right_arm.yRot = -0.9F;
                wrist_right.xRot = -0.9F;
                wrist_left.xRot = -0.4F;
                head.xRot = (float) Math.toRadians(30);
                head.yRot = (float) Math.toRadians(19);
                head.zRot = 0;
                return;
            }

            if (pose == WeepingAngelPose.ANGRY) {

                right_arm.xRot = (float) Math.toRadians(-90);
                right_arm.yRot = (float) Math.toRadians(25);
                right_arm.zRot = (float) Math.toRadians(17.5);

                left_arm.xRot = (float) Math.toRadians(-90);
                left_arm.yRot = (float) Math.toRadians(-25);
                left_arm.zRot = (float) Math.toRadians(-17.5);

                head.xRot = (float) Math.toRadians(10);
                head.yRot = (float) Math.toRadians(0);
                head.zRot = (float) Math.toRadians(0);

                return;
            }

            if (pose == WeepingAngelPose.APPROACH) {
                left_arm.xRot = (float) Math.toRadians(-90);
                right_arm.xRot = (float) Math.toRadians(-90);

                left_arm.yRot = (float) Math.toRadians(30);
                right_arm.yRot = (float) Math.toRadians(-30);

                left_arm.zRot = (float) Math.toRadians(-30);
                right_arm.zRot = (float) Math.toRadians(30);

                wrist_left.xRot = (float) Math.toRadians(-45);
                wrist_right.xRot = (float) Math.toRadians(-45);
                head.xRot = (float) Math.toRadians(15);
                return;
            }

            if (pose == WeepingAngelPose.HIDING) {
                left_arm.xRot = -1.85F;
                left_arm.yRot = 0.61F;
                left_arm.zRot = -0.087F;
                right_arm.xRot = -1.85F;
                right_arm.yRot = -0.61F;
                right_arm.zRot = 0.087F;

                wrist_right.xRot = -0.52F;
                wrist_left.xRot = -0.52F;
                head.xRot = 0.11F;
                head.yRot = 0.0F;
                head.zRot = 0.0F;
                return;
            }

            if (pose == WeepingAngelPose.IDLE) {
                left_arm.xRot = 0F;
                left_arm.yRot = 0F;
                left_arm.zRot = 0F;
                right_arm.xRot = 0F;
                right_arm.yRot = 0F;
                right_arm.zRot = 0F;

                wrist_right.xRot = 0F;
                wrist_left.xRot = 0F;
                head.xRot = 0F;
                head.yRot = 0.0F;
                head.zRot = 0.0F;
                return;
            }

            boolean isAngry = pose.getEmotion() == WeepingAngelPose.Emotion.ANGRY;
            angry_mouth.visible = isAngry;

            if (isAngry) {
                right_eyebrow.zRot = (float) (20 * Math.PI / 180);
                left_eyebrow.zRot = (float) (-20 * Math.PI / 180);
            } else {
                right_eyebrow.zRot = (float) (0 * Math.PI / 180);
                left_eyebrow.zRot = (float) (0 * Math.PI / 180);
            }
        }
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        matrixStack.pushPose();
        cloth_1.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        right_arm.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        head.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        cloth_0.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        back_cloth.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        cloth_2.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        left_wing_1.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        body_2.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        right_wing_1.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        body.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        right_wing_0.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        left_wing_0.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        left_arm.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        matrixStack.popPose();
    }

    @Override
    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(right_wing_0,
                left_wing_0,
                back_cloth_2,
                head_2,
                body_2,
                head,
                body,
                left_arm,
                right_arm,
                cloth_0,
                cloth_1,
                cloth_2,
                back_cloth,
                left_wing_1,
                right_wing_1,
                nose,
                face,
                right_eyebrow,
                left_eyebrow,
                coverup,
                angry_mouth,
                teeth,
                teeth_1,
                teeth_2,
                teeth_3,
                teeth_4,
                teeth_5,
                wrist_left,
                wrist_right,
                zeth,
                left_wing_2,
                left_wing_3,
                left_wing_4,
                right_wing_2,
                right_wing_3,
                right_wing_4);
    }

    private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
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

    @Override
    public ResourceLocation getTextureForPose(Object angel, WeepingAngelPose pose) {
        return TEXTURE;
    }

    @Override
    public Iterable<ModelRenderer> wings(MatrixStack pose) {
        return ImmutableList.of(left_wing_0, left_wing_1);
    }

    @Override
    public ModelRenderer getSantaAttachment(MatrixStack pose, boolean b) {
        return head;
    }
}
