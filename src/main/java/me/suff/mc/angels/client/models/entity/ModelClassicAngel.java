package me.suff.mc.angels.client.models.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.utils.DateChecker;
import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

/**
 * Angel Type: 2 - Classic
 */
public class ModelClassicAngel extends EntityModel< WeepingAngelEntity > implements IAngelModel {

    private final ResourceLocation TEXTURE = new ResourceLocation(WeepingAngels.MODID,
            "textures/entities/a_dizzle/angel_classic.png");

    private final ResourceLocation TEXTURE_ANGRY = new ResourceLocation(WeepingAngels.MODID,
            "textures/entities/a_dizzle/angel_classic_angry.png");

    private final ResourceLocation TEXTURE_ANGRY_XMAS = new ResourceLocation(WeepingAngels.MODID,
            "textures/entities/a_dizzle/angel_classic_angry_xmas.png");

    private final ResourceLocation TEXTURE_XMAS = new ResourceLocation(WeepingAngels.MODID,
            "textures/entities/a_dizzle/angel_classic_xmas.png");

    private ModelRenderer leftfoot;
    private ModelRenderer rightfoot;
    private ModelRenderer leftwing1;
    private ModelRenderer leftwing2;
    private ModelRenderer leftwing3;
    private ModelRenderer leftwing4;
    private ModelRenderer rightwing1;
    private ModelRenderer rightwing2;
    private ModelRenderer rightwing3;
    private ModelRenderer rightwing4;
    private ModelRenderer head;
    private ModelRenderer body;
    private ModelRenderer rightarm;
    private ModelRenderer leftarm;
    private ModelRenderer rightleg;
    private ModelRenderer leftleg;

    private WeepingAngelPose weepingAngelPose = WeepingAngelPose.ANGRY;

    /**
     * Angel Type: 2 - Classic
     */
    public ModelClassicAngel() {
        textureWidth = 64;
        textureHeight = 32;

        leftfoot = new ModelRenderer(this);
        leftfoot.setRotationPoint(2.0F, 12.0F, 0.0F);
        leftfoot.setTextureOffset(32, 0).addBox(-2.0F, 7.0F, -4.0F, 6.0F, 5.0F, 8.0F, 0.0F, false);

        rightfoot = new ModelRenderer(this);
        rightfoot.setRotationPoint(-2.0F, 12.0F, 0.0F);
        rightfoot.setTextureOffset(32, 0).addBox(-4.0F, 7.0F, -4.0F, 6.0F, 5.0F, 8.0F, 0.0F, false);

        leftwing1 = new ModelRenderer(this);
        leftwing1.setRotationPoint(1.0F, 1.0F, 1.0F);
        setRotationAngle(leftwing1, 0.2094F, 0.6109F, 0.0F);
        leftwing1.setTextureOffset(40, 25).addBox(-0.5F, -1.0F, 1.0F, 1.0F, 5.0F, 2.0F, 0.0F, false);

        leftwing2 = new ModelRenderer(this);
        leftwing2.setRotationPoint(1.0F, 1.0F, 1.0F);
        setRotationAngle(leftwing2, 0.2094F, 0.6109F, 0.0175F);
        leftwing2.setTextureOffset(46, 19).addBox(-0.5F, -2.0F, 3.0F, 1.0F, 11.0F, 2.0F, 0.0F, false);

        leftwing3 = new ModelRenderer(this);
        leftwing3.setRotationPoint(1.0F, 1.0F, 1.0F);
        setRotationAngle(leftwing3, 0.2094F, 0.6109F, 0.0F);
        leftwing3.setTextureOffset(58, 12).addBox(-0.5F, -2.0F, 5.0F, 1.0F, 18.0F, 2.0F, 0.0F, false);

        leftwing4 = new ModelRenderer(this);
        leftwing4.setRotationPoint(1.0F, 1.0F, 1.0F);
        setRotationAngle(leftwing4, 0.2094F, 0.6109F, 0.0F);
        leftwing4.setTextureOffset(52, 16).addBox(-0.5F, 0.0F, 7.0F, 1.0F, 14.0F, 2.0F, 0.0F, false);

        rightwing1 = new ModelRenderer(this);
        rightwing1.setRotationPoint(-2.0F, 1.0F, 1.0F);
        setRotationAngle(rightwing1, 0.2094F, -0.6109F, 0.0F);
        rightwing1.setTextureOffset(40, 25).addBox(-0.5F, -1.0F, 1.0F, 1.0F, 5.0F, 2.0F, 0.0F, false);

        rightwing2 = new ModelRenderer(this);
        rightwing2.setRotationPoint(-2.0F, 1.0F, 1.0F);
        setRotationAngle(rightwing2, 0.2094F, -0.6109F, 0.0F);
        rightwing2.setTextureOffset(46, 19).addBox(-0.5F, -2.0F, 3.0F, 1.0F, 11.0F, 2.0F, 0.0F, false);

        rightwing3 = new ModelRenderer(this);
        rightwing3.setRotationPoint(-2.0F, 1.0F, 1.0F);
        setRotationAngle(rightwing3, 0.2094F, -0.6109F, 0.0F);
        rightwing3.setTextureOffset(58, 12).addBox(-0.5F, -2.0F, 5.0F, 1.0F, 18.0F, 2.0F, 0.0F, false);

        rightwing4 = new ModelRenderer(this);
        rightwing4.setRotationPoint(-2.0F, 1.0F, 1.0F);
        setRotationAngle(rightwing4, 0.2094F, -0.6109F, 0.0F);
        rightwing4.setTextureOffset(52, 16).addBox(-0.5F, 0.0F, 7.0F, 1.0F, 14.0F, 2.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.setTextureOffset(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);

        rightarm = new ModelRenderer(this);
        rightarm.setRotationPoint(-5.0F, 0.0F, 0.0F);
        rightarm.setTextureOffset(24, 19).addBox(-3.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);

        leftarm = new ModelRenderer(this);
        leftarm.setRotationPoint(5.0F, 0.0F, 0.0F);
        leftarm.setTextureOffset(24, 19).addBox(-1.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, 0.0F, true);

        rightleg = new ModelRenderer(this);
        rightleg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        rightleg.setTextureOffset(24, 19).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);

        leftleg = new ModelRenderer(this);
        leftleg.setRotationPoint(2.0F, 12.0F, 0.0F);
        leftleg.setTextureOffset(24, 19).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, 0.0F, true);
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


    @Override
    public void setRotationAngles(WeepingAngelEntity weepingAngelEntity, float v, float v1, float v2, float v3, float v4) {
        WeepingAngelPose pose = weepingAngelPose;
        if (weepingAngelEntity != null) {
            pose = WeepingAngelPose.getPose(weepingAngelEntity.getAngelPose());
        }

        boolean isAngry = pose.getEmotion() == WeepingAngelPose.Emotion.ANGRY;
        float angleX = isAngry ? 20F : 10F;
        float angleY = isAngry ? 60F : 30F;
        float angleZ = 5F;

        head.rotateAngleX = (float) Math.toRadians(0);
        head.rotateAngleY = (float) Math.toRadians(0);
        head.rotateAngleZ = (float) Math.toRadians(0);

        rightwing2.rotateAngleX = rightwing3.rotateAngleX = rightwing4.rotateAngleX = rightwing1.rotateAngleX = leftwing2.rotateAngleX = leftwing3.rotateAngleX = leftwing4.rotateAngleX = leftwing1.rotateAngleX = (float) Math.toRadians(angleX);
        rightwing2.rotateAngleY = rightwing3.rotateAngleY = rightwing4.rotateAngleY = rightwing1.rotateAngleY = (float) Math.toRadians(-angleY);
        leftwing2.rotateAngleY = leftwing3.rotateAngleY = leftwing4.rotateAngleY = leftwing1.rotateAngleY = (float) Math.toRadians(angleY);
        rightwing2.rotateAngleZ = rightwing3.rotateAngleZ = rightwing4.rotateAngleZ = rightwing1.rotateAngleZ = (float) Math.toRadians(angleZ);
        leftwing2.rotateAngleZ = leftwing3.rotateAngleZ = leftwing4.rotateAngleZ = leftwing1.rotateAngleZ = (float) Math.toRadians(-angleZ);


        if (pose == WeepingAngelPose.FURIOUS) {
            rightarm.rotateAngleZ = 0.0F;
            leftarm.rotateAngleZ = 0.0F;
            rightarm.rotateAngleY = -(0.1F - 0 * 0.6F);
            leftarm.rotateAngleY = 0.1F - 0 * 0.6F;
            rightarm.rotateAngleX = -1.570796F;
            leftarm.rotateAngleX = -1.570796F;
            return;
        }


        if (pose == WeepingAngelPose.APPROACH) {
            rightarm.rotateAngleX = -1.04533F;
            rightarm.rotateAngleY = -0.55851F;
            rightarm.rotateAngleZ = 0.0F;
            leftarm.rotateAngleX = -1.04533F;
            leftarm.rotateAngleY = 0.55851F;
            leftarm.rotateAngleZ = 0.0F;
            return;
        }

        if (pose == WeepingAngelPose.ANGRY) {
            rightarm.rotateAngleX = (float) Math.toRadians(-95);
            rightarm.rotateAngleY = (float) Math.toRadians(37.5);
            rightarm.rotateAngleZ = (float) Math.toRadians(40);
            leftarm.rotateAngleX = (float) Math.toRadians(-95);
            leftarm.rotateAngleY = (float) Math.toRadians(-37.5);
            leftarm.rotateAngleZ = (float) Math.toRadians(-40);
            return;
        }

        if (pose == WeepingAngelPose.IDLE || pose == WeepingAngelPose.HIDING) {
            rightarm.rotateAngleX = -1.74533F;
            rightarm.rotateAngleY = -0.55851F;
            rightarm.rotateAngleZ = 0.0F;
            leftarm.rotateAngleX = -1.74533F;
            leftarm.rotateAngleY = 0.55851F;
            leftarm.rotateAngleZ = 0.0F;
            return;
        }

        if (pose == WeepingAngelPose.SHY) {
            rightarm.rotateAngleX = (float) Math.toRadians(-90);
            rightarm.rotateAngleY = (float) Math.toRadians(-1.5);
            rightarm.rotateAngleZ = (float) Math.toRadians(-20);

            leftarm.rotateAngleX = (float) Math.toRadians(-120);
            leftarm.rotateAngleY = (float) Math.toRadians(-36);
            leftarm.rotateAngleZ = (float) Math.toRadians(10);

            head.rotateAngleX = (float) Math.toRadians(20);
            head.rotateAngleY = (float) Math.toRadians(-40);
            head.rotateAngleZ = (float) Math.toRadians(-20);
            return;
        }
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
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
    public ResourceLocation getTextureForPose(Object angel, WeepingAngelPose pose) {
        if (!DateChecker.isXmas()) {
            return pose.getEmotion() == WeepingAngelPose.Emotion.ANGRY ? TEXTURE_ANGRY : TEXTURE;
        }
        return pose.getEmotion() == WeepingAngelPose.Emotion.ANGRY ? TEXTURE_ANGRY_XMAS : TEXTURE_XMAS;
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }


}
