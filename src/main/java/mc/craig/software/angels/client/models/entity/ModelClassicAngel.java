package mc.craig.software.angels.client.models.entity;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.client.poses.WeepingAngelPose;
import mc.craig.software.angels.common.entities.WeepingAngelEntity;
import mc.craig.software.angels.common.variants.AbstractVariant;
import mc.craig.software.angels.utils.DateChecker;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

/**
 * Angel Type: 2 - Classic
 */
public class ModelClassicAngel extends SegmentedModel<WeepingAngelEntity> implements IAngelModel {

    private final ResourceLocation TEXTURE = new ResourceLocation(WeepingAngels.MODID,
            "textures/entities/a_dizzle/angel_classic.png");

    private final ResourceLocation TEXTURE_ANGRY = new ResourceLocation(WeepingAngels.MODID,
            "textures/entities/a_dizzle/angel_classic_angry.png");

    private final ResourceLocation TEXTURE_ANGRY_XMAS = new ResourceLocation(WeepingAngels.MODID,
            "textures/entities/a_dizzle/angel_classic_angry_xmas.png");

    private final ResourceLocation TEXTURE_XMAS = new ResourceLocation(WeepingAngels.MODID,
            "textures/entities/a_dizzle/angel_classic_xmas.png");

    private final ModelRenderer leftfoot;
    private final ModelRenderer rightfoot;
    private final ModelRenderer leftwing1;
    private final ModelRenderer leftwing2;
    private final ModelRenderer leftwing3;
    private final ModelRenderer leftwing4;
    private final ModelRenderer rightwing1;
    private final ModelRenderer rightwing2;
    private final ModelRenderer rightwing3;
    private final ModelRenderer rightwing4;
    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer rightarm;
    private final ModelRenderer leftarm;
    private final ModelRenderer rightleg;
    private final ModelRenderer leftleg;

    private WeepingAngelPose weepingAngelPose = WeepingAngelPose.ANGRY;

    /**
     * Angel Type: 2 - Classic
     */
    public ModelClassicAngel() {
        texWidth = 64;
        texHeight = 32;

        leftfoot = new ModelRenderer(this);
        leftfoot.setPos(2.0F, 12.0F, 0.0F);
        leftfoot.texOffs(32, 0).addBox(-2.0F, 7.0F, -4.0F, 6.0F, 5.0F, 8.0F, 0.0F, false);

        rightfoot = new ModelRenderer(this);
        rightfoot.setPos(-2.0F, 12.0F, 0.0F);
        rightfoot.texOffs(32, 0).addBox(-4.0F, 7.0F, -4.0F, 6.0F, 5.0F, 8.0F, 0.0F, false);

        leftwing1 = new ModelRenderer(this);
        leftwing1.setPos(1.0F, 1.0F, 1.0F);
        setRotationAngle(leftwing1, 0.2094F, 0.6109F, 0.0F);
        leftwing1.texOffs(40, 25).addBox(-0.5F, -1.0F, 1.0F, 1.0F, 5.0F, 2.0F, 0.0F, false);

        leftwing2 = new ModelRenderer(this);
        leftwing2.setPos(1.0F, 1.0F, 1.0F);
        setRotationAngle(leftwing2, 0.2094F, 0.6109F, 0.0175F);
        leftwing2.texOffs(46, 19).addBox(-0.5F, -2.0F, 3.0F, 1.0F, 11.0F, 2.0F, 0.0F, false);

        leftwing3 = new ModelRenderer(this);
        leftwing3.setPos(1.0F, 1.0F, 1.0F);
        setRotationAngle(leftwing3, 0.2094F, 0.6109F, 0.0F);
        leftwing3.texOffs(58, 12).addBox(-0.5F, -2.0F, 5.0F, 1.0F, 18.0F, 2.0F, 0.0F, false);

        leftwing4 = new ModelRenderer(this);
        leftwing4.setPos(1.0F, 1.0F, 1.0F);
        setRotationAngle(leftwing4, 0.2094F, 0.6109F, 0.0F);
        leftwing4.texOffs(52, 16).addBox(-0.5F, 0.0F, 7.0F, 1.0F, 14.0F, 2.0F, 0.0F, false);

        rightwing1 = new ModelRenderer(this);
        rightwing1.setPos(-2.0F, 1.0F, 1.0F);
        setRotationAngle(rightwing1, 0.2094F, -0.6109F, 0.0F);
        rightwing1.texOffs(40, 25).addBox(-0.5F, -1.0F, 1.0F, 1.0F, 5.0F, 2.0F, 0.0F, false);

        rightwing2 = new ModelRenderer(this);
        rightwing2.setPos(-2.0F, 1.0F, 1.0F);
        setRotationAngle(rightwing2, 0.2094F, -0.6109F, 0.0F);
        rightwing2.texOffs(46, 19).addBox(-0.5F, -2.0F, 3.0F, 1.0F, 11.0F, 2.0F, 0.0F, false);

        rightwing3 = new ModelRenderer(this);
        rightwing3.setPos(-2.0F, 1.0F, 1.0F);
        setRotationAngle(rightwing3, 0.2094F, -0.6109F, 0.0F);
        rightwing3.texOffs(58, 12).addBox(-0.5F, -2.0F, 5.0F, 1.0F, 18.0F, 2.0F, 0.0F, false);

        rightwing4 = new ModelRenderer(this);
        rightwing4.setPos(-2.0F, 1.0F, 1.0F);
        setRotationAngle(rightwing4, 0.2094F, -0.6109F, 0.0F);
        rightwing4.texOffs(52, 16).addBox(-0.5F, 0.0F, 7.0F, 1.0F, 14.0F, 2.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setPos(0.0F, 0.0F, 0.0F);
        head.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setPos(0.0F, 0.0F, 0.0F);
        body.texOffs(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);

        rightarm = new ModelRenderer(this);
        rightarm.setPos(-5.0F, 0.0F, 0.0F);
        rightarm.texOffs(24, 19).addBox(-3.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);

        leftarm = new ModelRenderer(this);
        leftarm.setPos(5.0F, 0.0F, 0.0F);
        leftarm.texOffs(24, 19).addBox(-1.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, 0.0F, true);

        rightleg = new ModelRenderer(this);
        rightleg.setPos(-2.0F, 12.0F, 0.0F);
        rightleg.texOffs(24, 19).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);

        leftleg = new ModelRenderer(this);
        leftleg.setPos(2.0F, 12.0F, 0.0F);
        leftleg.texOffs(24, 19).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, 0.0F, true);
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
    public void setupAnim(WeepingAngelEntity weepingAngelEntity, float v, float v1, float v2, float v3, float v4) {
        WeepingAngelPose pose = weepingAngelPose;
        if (weepingAngelEntity != null) {
            pose = WeepingAngelPose.getPose(weepingAngelEntity.getAngelPose());
        }

        boolean isAngry = pose.getEmotion() == WeepingAngelPose.Emotion.ANGRY;
        float angleX = isAngry ? 20F : 10F;
        float angleY = isAngry ? 60F : 30F;
        float angleZ = 5F;

        head.xRot = (float) Math.toRadians(0);
        head.yRot = (float) Math.toRadians(0);
        head.zRot = (float) Math.toRadians(0);

        rightwing2.xRot = rightwing3.xRot = rightwing4.xRot = rightwing1.xRot = leftwing2.xRot = leftwing3.xRot = leftwing4.xRot = leftwing1.xRot = (float) Math.toRadians(angleX);
        rightwing2.yRot = rightwing3.yRot = rightwing4.yRot = rightwing1.yRot = (float) Math.toRadians(-angleY);
        leftwing2.yRot = leftwing3.yRot = leftwing4.yRot = leftwing1.yRot = (float) Math.toRadians(angleY);
        rightwing2.zRot = rightwing3.zRot = rightwing4.zRot = rightwing1.zRot = (float) Math.toRadians(angleZ);
        leftwing2.zRot = leftwing3.zRot = leftwing4.zRot = leftwing1.zRot = (float) Math.toRadians(-angleZ);


        if (pose == WeepingAngelPose.FURIOUS) {
            rightarm.zRot = 0.0F;
            leftarm.zRot = 0.0F;
            rightarm.yRot = -(0.1F - 0 * 0.6F);
            leftarm.yRot = 0.1F - 0 * 0.6F;
            rightarm.xRot = -1.570796F;
            leftarm.xRot = -1.570796F;
            return;
        }


        if (pose == WeepingAngelPose.APPROACH) {
            rightarm.xRot = -1.04533F;
            rightarm.yRot = -0.55851F;
            rightarm.zRot = 0.0F;
            leftarm.xRot = -1.04533F;
            leftarm.yRot = 0.55851F;
            leftarm.zRot = 0.0F;
            return;
        }

        if (pose == WeepingAngelPose.ANGRY) {
            rightarm.xRot = (float) Math.toRadians(-95);
            rightarm.yRot = (float) Math.toRadians(37.5);
            rightarm.zRot = (float) Math.toRadians(40);
            leftarm.xRot = (float) Math.toRadians(-95);
            leftarm.yRot = (float) Math.toRadians(-37.5);
            leftarm.zRot = (float) Math.toRadians(-40);
            return;
        }

        if (pose == WeepingAngelPose.IDLE || pose == WeepingAngelPose.HIDING) {
            rightarm.xRot = -1.74533F;
            rightarm.yRot = -0.55851F;
            rightarm.zRot = 0.0F;
            leftarm.xRot = -1.74533F;
            leftarm.yRot = 0.55851F;
            leftarm.zRot = 0.0F;
            return;
        }

        if (pose == WeepingAngelPose.SHY) {
            rightarm.xRot = (float) Math.toRadians(-90);
            rightarm.yRot = (float) Math.toRadians(-1.5);
            rightarm.zRot = (float) Math.toRadians(-20);

            leftarm.xRot = (float) Math.toRadians(-120);
            leftarm.yRot = (float) Math.toRadians(-36);
            leftarm.zRot = (float) Math.toRadians(10);

            head.xRot = (float) Math.toRadians(20);
            head.yRot = (float) Math.toRadians(-40);
            head.zRot = (float) Math.toRadians(-20);
            return;
        }
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        leftfoot.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        rightfoot.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        leftwing1.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        leftwing2.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        leftwing3.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        leftwing4.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        rightwing1.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        rightwing2.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        rightwing3.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        rightwing4.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        head.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        body.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        rightarm.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        leftarm.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        rightleg.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        leftleg.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
    }

    @Override
    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(leftfoot,
                rightfoot,
                leftwing1,
                leftwing2,
                leftwing3,
                leftwing4,
                rightwing1,
                rightwing2,
                rightwing3,
                rightwing4,
                head,
                body,
                rightarm,
                leftarm,
                rightleg,
                leftleg);
    }


    @Override
    public ResourceLocation generateTex(WeepingAngelPose pose, AbstractVariant angelVariants) {
        if (!DateChecker.isXmas()) {
            return pose.getEmotion() == WeepingAngelPose.Emotion.ANGRY ? TEXTURE_ANGRY : TEXTURE;
        }
        return pose.getEmotion() == WeepingAngelPose.Emotion.ANGRY ? TEXTURE_ANGRY_XMAS : TEXTURE_XMAS;
    }

    @Override
    public ModelRenderer getSantaAttachment(MatrixStack pose, boolean b) {
        return null;
    }

    @Override
    public ResourceLocation getTextureForPose(Object angel, WeepingAngelPose pose) {
        if (!DateChecker.isXmas()) {
            return pose.getEmotion() == WeepingAngelPose.Emotion.ANGRY ? TEXTURE_ANGRY : TEXTURE;
        }
        return pose.getEmotion() == WeepingAngelPose.Emotion.ANGRY ? TEXTURE_ANGRY_XMAS : TEXTURE_XMAS;
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    @Override
    public Iterable<ModelRenderer> wings(MatrixStack pose) {
        return ImmutableList.of(leftwing1, leftwing2, leftwing3, leftwing4, rightwing1, rightwing2, rightwing3, rightwing4);
    }


}
