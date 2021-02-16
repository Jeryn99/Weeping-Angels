package me.swirtzly.minecraft.angels.client.models.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import me.swirtzly.minecraft.angels.WeepingAngels;
import me.swirtzly.minecraft.angels.client.poses.WeepingAngelPose;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import me.swirtzly.minecraft.angels.common.tileentities.PlinthTile;
import me.swirtzly.minecraft.angels.common.tileentities.StatueTile;
import me.swirtzly.minecraft.angels.utils.ClientUtil;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

public class ModelAngelaAngel extends EntityModel< WeepingAngelEntity > implements IAngelModel {

    public static final ResourceLocation ANGRY = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angela_two/normal/normal_angel_angry.png");

    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer leftArm;
    private final ModelRenderer rightArm;
    private final ModelRenderer Legs;
    private final ModelRenderer leftWing;
    private final ModelRenderer rightWing;

    private WeepingAngelPose weepingAngelPose = WeepingAngelPose.ANGRY;

    public ModelAngelaAngel() {
        textureWidth = 128;
        textureHeight = 128;

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.setTextureOffset(0, 17).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
        head.setTextureOffset(72, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.setTextureOffset(56, 17).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);
        body.setTextureOffset(32, 17).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.5F, false);

        leftArm = new ModelRenderer(this);
        leftArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        leftArm.setTextureOffset(24, 59).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.0F, false);

        rightArm = new ModelRenderer(this);
        rightArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        rightArm.setTextureOffset(10, 59).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.0F, false);

        Legs = new ModelRenderer(this);
        Legs.setRotationPoint(0.0F, 9.25F, 0.0F);
        Legs.setTextureOffset(40, 0).addBox(-5.0F, -0.25F, -3.0F, 10.0F, 11.0F, 6.0F, 0.0F, false);
        Legs.setTextureOffset(0, 0).addBox(-6.0F, 10.75F, -4.0F, 12.0F, 4.0F, 8.0F, 0.0F, false);

        leftWing = new ModelRenderer(this);
        leftWing.setRotationPoint(-1.0F, 5.0F, 2.0F);
        setRotationAngle(leftWing, 0.0F, -0.7854F, 0.0F);
        leftWing.setTextureOffset(0, 101).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 5.0F, 3.0F, 0.0F, false);
        leftWing.setTextureOffset(6, 83).addBox(-1.0F, -8.9F, 5.0F, 2.0F, 14.0F, 1.0F, 0.0F, false);
        leftWing.setTextureOffset(18, 83).addBox(-1.0F, -6.9F, 3.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);
        leftWing.setTextureOffset(8, 33).addBox(-1.0F, -10.9F, 6.0F, 2.0F, 21.0F, 3.0F, 0.0F, false);
        leftWing.setTextureOffset(0, 33).addBox(-1.0F, -10.0F, 9.0F, 2.0F, 24.0F, 2.0F, 0.0F, false);
        leftWing.setTextureOffset(38, 59).addBox(-1.0F, -8.0F, 11.0F, 2.0F, 17.0F, 1.0F, 0.0F, false);

        rightWing = new ModelRenderer(this);
        rightWing.setRotationPoint(1.0F, 5.0F, 2.0F);
        setRotationAngle(rightWing, 0.0F, 0.7854F, 0.0F);
        rightWing.setTextureOffset(10, 101).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 5.0F, 3.0F, 0.0F, false);
        rightWing.setTextureOffset(12, 83).addBox(-1.0F, -8.9F, 5.0F, 2.0F, 14.0F, 1.0F, 0.0F, false);
        rightWing.setTextureOffset(26, 83).addBox(-1.0F, -6.9F, 3.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);
        rightWing.setTextureOffset(18, 33).addBox(-1.0F, -10.0F, 9.0F, 2.0F, 24.0F, 2.0F, 0.0F, false);
        rightWing.setTextureOffset(0, 83).addBox(-1.0F, -8.0F, 11.0F, 2.0F, 17.0F, 1.0F, 0.0F, false);
        rightWing.setTextureOffset(0, 59).addBox(-1.0F, -10.9F, 6.0F, 2.0F, 21.0F, 3.0F, 0.0F, false);
    }

    public void setPose(WeepingAngelPose weepingAngelPose) {
        this.weepingAngelPose = weepingAngelPose;
    }

    public WeepingAngelPose getAngelPoses() {
        return weepingAngelPose;
    }

    @Override
    public void setRotationAngles(WeepingAngelEntity weepingAngelEntity, float v, float v1, float v2, float v3, float v4) {
        WeepingAngelPose pose = weepingAngelPose;
        if (weepingAngelEntity != null) {
            pose = WeepingAngelPose.getPose(weepingAngelEntity.getAngelPose());
        }

        boolean isAngry = pose.getEmotion() == WeepingAngelPose.Emotion.ANGRY || pose.getEmotion() == WeepingAngelPose.Emotion.SCREAM;
        float angleX = isAngry ? 20F : 0;
        float angleY = isAngry ? 60F : 45F;
        float angleZ = 0;

        if(pose.getEmotion() == WeepingAngelPose.Emotion.SCREAM){
            angleY += 10F;
            angleX -= 10F;
        }

        head.rotateAngleX = (float) Math.toRadians(0);
        head.rotateAngleY = (float) Math.toRadians(0);
        head.rotateAngleZ = (float) Math.toRadians(0);

        rightWing.rotateAngleX = (float) Math.toRadians(angleX);
        rightWing.rotateAngleY = (float) Math.toRadians(angleY);
        rightWing.rotateAngleZ = (float) Math.toRadians(angleZ);
        leftWing.rotateAngleX = (float) Math.toRadians(angleX);
        leftWing.rotateAngleY = (float) Math.toRadians(-angleY);
        leftWing.rotateAngleZ = (float) Math.toRadians(angleZ);


        if (pose == WeepingAngelPose.FURIOUS) {
            rightArm.rotateAngleX = (float) Math.toRadians(-115);
            rightArm.rotateAngleY = (float) Math.toRadians(0);
            rightArm.rotateAngleZ = (float) Math.toRadians(0);

            leftArm.rotateAngleX = (float) Math.toRadians(-55);
            leftArm.rotateAngleY = (float) Math.toRadians(0);
            leftArm.rotateAngleZ = (float) Math.toRadians(0);

            head.rotateAngleX = (float) Math.toRadians(17.5);
            head.rotateAngleY = (float) Math.toRadians(0);
            head.rotateAngleZ = (float) Math.toRadians(-10);
            return;
        }


        if (pose == WeepingAngelPose.ANGRY) {
            rightArm.rotateAngleX = (float) Math.toRadians(-90);
            rightArm.rotateAngleY = (float) Math.toRadians(-20);
            rightArm.rotateAngleZ = (float) Math.toRadians(30);

            leftArm.rotateAngleX = (float) Math.toRadians(-90);
            leftArm.rotateAngleY = (float) Math.toRadians(25);
            leftArm.rotateAngleZ = (float) Math.toRadians(-17.5);

            head.rotateAngleX = (float) Math.toRadians(0);
            head.rotateAngleY = (float) Math.toRadians(-12.5);
            head.rotateAngleZ = (float) Math.toRadians(0);
            return;
        }


        if (pose == WeepingAngelPose.HIDING) {
            head.rotateAngleX = (float) Math.toRadians(20);
            head.rotateAngleY = (float) Math.toRadians(0);
            head.rotateAngleZ = (float) Math.toRadians(0);

            rightArm.rotateAngleX = (float) Math.toRadians(-105);
            rightArm.rotateAngleY = (float) Math.toRadians(20);
            rightArm.rotateAngleZ = (float) Math.toRadians(12.5);

            leftArm.rotateAngleX = (float) Math.toRadians(-105);
            leftArm.rotateAngleY = (float) Math.toRadians(-20);
            leftArm.rotateAngleZ = (float) Math.toRadians(-12.5);
            return;
        }

        if (pose == WeepingAngelPose.APPROACH) {
            rightArm.rotateAngleX = -1.04533F;
            rightArm.rotateAngleY = -0.55851F;
            rightArm.rotateAngleZ = 0.0F;
            leftArm.rotateAngleX = -1.04533F;
            leftArm.rotateAngleY = 0.55851F;
            leftArm.rotateAngleZ = 0.0F;
            return;
        }

        if (pose == WeepingAngelPose.IDLE) {
            head.rotateAngleX = (float) Math.toRadians(0);
            head.rotateAngleY = (float) Math.toRadians(0);
            head.rotateAngleZ = (float) Math.toRadians(0);

            rightArm.rotateAngleX = (float) Math.toRadians(0);
            rightArm.rotateAngleY = (float) Math.toRadians(0);
            rightArm.rotateAngleZ = (float) Math.toRadians(-7.5);

            leftArm.rotateAngleX = (float) Math.toRadians(0);
            leftArm.rotateAngleY = (float) Math.toRadians(0);
            leftArm.rotateAngleZ = (float) Math.toRadians(7.5);
            return;
        }

        if (pose == WeepingAngelPose.SHY) {
            rightArm.rotateAngleX = (float) Math.toRadians(-90);
            rightArm.rotateAngleY = (float) Math.toRadians(-1.5);
            rightArm.rotateAngleZ = (float) Math.toRadians(-20);

            leftArm.rotateAngleX = (float) Math.toRadians(-120);
            leftArm.rotateAngleY = (float) Math.toRadians(-36);
            leftArm.rotateAngleZ = (float) Math.toRadians(10);

            head.rotateAngleX = (float) Math.toRadians(20);
            head.rotateAngleY = (float) Math.toRadians(-40);
            head.rotateAngleZ = (float) Math.toRadians(-20);
            return;
        }
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        body.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        leftArm.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        rightArm.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Legs.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        leftWing.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        rightWing.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public ResourceLocation getTextureForPose(Object angel, WeepingAngelPose pose) {

        if (angel instanceof WeepingAngelEntity) {
            WeepingAngelEntity weepingAngelEntity = (WeepingAngelEntity) angel;
            return generateTex(pose, WeepingAngelEntity.AngelVariants.valueOf(weepingAngelEntity.getVarient()));
        }

        if (angel instanceof StatueTile) {
            StatueTile weepingAngelEntity = (StatueTile) angel;
            return generateTex(weepingAngelEntity.getPose(), weepingAngelEntity.getAngelVarients());
        }

        if (angel instanceof PlinthTile) {
            PlinthTile weepingAngelEntity = (PlinthTile) angel;
            return generateTex(weepingAngelEntity.getPose(), weepingAngelEntity.getAngelVarients());
        }
        return ANGRY;
    }

    @Override
    public WeepingAngelPose getAngelPose() {
        return weepingAngelPose;
    }

    @Override
    public void setAngelPose(WeepingAngelPose angelType) {
        weepingAngelPose = angelType;
    }

    @Override
    public ResourceLocation generateTex(WeepingAngelPose pose, WeepingAngelEntity.AngelVariants angelVariants) {
        String variant = angelVariants.name().toLowerCase() + "_angel_";
        String location = "textures/entities/angela_two/";
        location = location + angelVariants.name().toLowerCase().toLowerCase() + "/";
        WeepingAngelPose.Emotion emotion = pose.getEmotion();
        String suffix = emotion.name().toLowerCase();
        if (angelVariants.isHeadless()) {
            suffix = "headless";
        }
        return new ResourceLocation(WeepingAngels.MODID, location + variant + suffix + ".png");    }
}