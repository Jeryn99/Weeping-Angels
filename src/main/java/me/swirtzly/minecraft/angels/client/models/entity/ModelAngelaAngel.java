package me.swirtzly.minecraft.angels.client.models.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import me.swirtzly.minecraft.angels.WeepingAngels;
import me.swirtzly.minecraft.angels.client.poses.AngelPoses;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import me.swirtzly.minecraft.angels.common.tileentities.PlinthTile;
import me.swirtzly.minecraft.angels.common.tileentities.StatueTile;
import me.swirtzly.minecraft.angels.utils.ClientUtil;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;

public class ModelAngelaAngel extends EntityModel<WeepingAngelEntity> implements IAngelModel {

    public static final ResourceLocation ANGRY = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angela_two/normal/normal_angel_angry.png");


    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer leftArm;
    private final ModelRenderer rightArm;
    private final ModelRenderer Legs;
    private final ModelRenderer leftWing;
    private final ModelRenderer rightWing;

    private AngelPoses angelPoses = AngelPoses.POSE_ANGRY;

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

    public void setPose(AngelPoses angelPoses) {
        this.angelPoses = angelPoses;
    }

    public AngelPoses getAngelPoses() {
        return angelPoses;
    }

    @Override
    public void setRotationAngles(WeepingAngelEntity weepingAngelEntity, float v, float v1, float v2, float v3, float v4) {
        AngelPoses pose = angelPoses;
        if (weepingAngelEntity != null) {
            pose = AngelPoses.getPoseFromString(weepingAngelEntity.getAngelPose());
        }

        if (pose == AngelPoses.POSE_ANGRY_TWO) {
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


        if (pose.create().isAngry()) {
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


        if (pose == AngelPoses.POSE_HIDING_FACE) {
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

        if (pose == AngelPoses.POSE_IDLE) {
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

        if (pose == AngelPoses.POSE_SHY) {
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
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
        rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
        Legs.render(matrixStack, buffer, packedLight, packedOverlay);
        leftWing.render(matrixStack, buffer, packedLight, packedOverlay);
        rightWing.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public ResourceLocation getTextureForPose(Object angel, AngelPoses pose) {

        if (angel instanceof WeepingAngelEntity) {
            WeepingAngelEntity weepingAngelEntity = (WeepingAngelEntity) angel;
            return ClientUtil.build(weepingAngelEntity.getVarient(), AngelPoses.getPoseFromString(weepingAngelEntity.getAngelPose()));
        }

        if (angel instanceof StatueTile) {
            StatueTile weepingAngelEntity = (StatueTile) angel;
            return ClientUtil.build(weepingAngelEntity.getAngelVarients().name(), AngelPoses.getPoseFromString(weepingAngelEntity.getPose()));
        }

        if (angel instanceof PlinthTile) {
            PlinthTile weepingAngelEntity = (PlinthTile) angel;
            return ClientUtil.build(weepingAngelEntity.getAngelVarients().name(), AngelPoses.getPoseFromString(weepingAngelEntity.getPose()));
        }
        return ANGRY;
    }

    @Override
    public AngelPoses getAngelPose() {
        return angelPoses;
    }

    @Override
    public void setAngelPose(AngelPoses angelType) {
        angelPoses = angelType;
    }
}