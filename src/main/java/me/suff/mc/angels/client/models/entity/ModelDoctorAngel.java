package me.suff.mc.angels.client.models.entity;// Made with Blockbench 4.0.5
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import me.suff.mc.angels.common.tileentities.PlinthTile;
import me.suff.mc.angels.common.tileentities.StatueTile;
import me.suff.mc.angels.common.variants.AbstractVariant;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;

public class ModelDoctorAngel extends EntityModel<WeepingAngelEntity> implements IAngelModel {
    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer leftArm;
    private final ModelRenderer rightArm;
    private final ModelRenderer Legs;
    private final ModelRenderer LLeg;
    private final ModelRenderer RLeg;
    private final ModelRenderer leftWing;
    private final ModelRenderer rightWing;

    public static final ResourceLocation IDLE = new ResourceLocation(WeepingAngels.MODID, "textures/entities/disaster/doctor/doctor_angel_idle.png");
    private WeepingAngelPose weepingAngelPose = WeepingAngelPose.ANGRY;


    public ModelDoctorAngel() {
        texWidth = 128;
        texHeight = 128;

        head = new ModelRenderer(this);
        head.setPos(0.0F, 0.0F, 0.0F);
        head.texOffs(0, 17).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
        head.texOffs(72, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F, false);

        body = new ModelRenderer(this);
        body.setPos(0.0F, 0.0F, 0.0F);
        body.texOffs(56, 17).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);
        body.texOffs(32, 17).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 8.0F, 4.0F, 0.5F, false);
        body.texOffs(41, 0).addBox(-5.0F, 8.5F, -2.5F, 10.0F, 11.0F, 5.0F, 0.0F, false);

        leftArm = new ModelRenderer(this);
        leftArm.setPos(-5.0F, 2.0F, 0.0F);
        leftArm.texOffs(24, 59).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.0F, false);
        leftArm.texOffs(28, 35).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 10.0F, 4.0F, 0.25F, true);

        rightArm = new ModelRenderer(this);
        rightArm.setPos(5.0F, 2.0F, 0.0F);
        rightArm.texOffs(10, 59).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.0F, false);
        rightArm.texOffs(28, 35).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 10.0F, 4.0F, 0.25F, false);

        Legs = new ModelRenderer(this);
        Legs.setPos(0.0F, 9.25F, 0.0F);


        LLeg = new ModelRenderer(this);
        LLeg.setPos(-2.0F, 2.75F, 0.0F);
        Legs.addChild(LLeg);
        LLeg.texOffs(0, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);
        LLeg.texOffs(48, 50).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, true);

        RLeg = new ModelRenderer(this);
        RLeg.setPos(2.0F, 2.75F, 0.0F);
        Legs.addChild(RLeg);
        RLeg.texOffs(0, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
        RLeg.texOffs(67, 50).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);

        leftWing = new ModelRenderer(this);
        leftWing.setPos(-1.0F, 5.0F, 2.0F);
        setRotationAngle(leftWing, 0.0F, -0.7854F, 0.0F);
        leftWing.texOffs(0, 101).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 5.0F, 3.0F, 0.0F, false);
        leftWing.texOffs(6, 83).addBox(-1.0F, -8.9F, 5.0F, 2.0F, 14.0F, 1.0F, 0.0F, false);
        leftWing.texOffs(18, 83).addBox(-1.0F, -6.9F, 3.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);
        leftWing.texOffs(8, 33).addBox(-1.0F, -10.9F, 6.0F, 2.0F, 21.0F, 3.0F, 0.0F, false);
        leftWing.texOffs(0, 33).addBox(-1.0F, -10.0F, 9.0F, 2.0F, 24.0F, 2.0F, 0.0F, false);
        leftWing.texOffs(38, 59).addBox(-1.0F, -8.0F, 11.0F, 2.0F, 17.0F, 1.0F, 0.0F, false);

        rightWing = new ModelRenderer(this);
        rightWing.setPos(1.0F, 5.0F, 2.0F);
        setRotationAngle(rightWing, 0.0F, 0.7854F, 0.0F);
        rightWing.texOffs(10, 101).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 5.0F, 3.0F, 0.0F, false);
        rightWing.texOffs(12, 83).addBox(-1.0F, -8.9F, 5.0F, 2.0F, 14.0F, 1.0F, 0.0F, false);
        rightWing.texOffs(26, 83).addBox(-1.0F, -6.9F, 3.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);
        rightWing.texOffs(18, 33).addBox(-1.0F, -10.0F, 9.0F, 2.0F, 24.0F, 2.0F, 0.0F, false);
        rightWing.texOffs(0, 83).addBox(-1.0F, -8.0F, 11.0F, 2.0F, 17.0F, 1.0F, 0.0F, false);
        rightWing.texOffs(0, 59).addBox(-1.0F, -10.9F, 6.0F, 2.0F, 21.0F, 3.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(WeepingAngelEntity weepingAngel, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
        WeepingAngelPose pose = weepingAngelPose;
        if (weepingAngel != null) {
            pose = WeepingAngelPose.getPose(weepingAngel.getAngelPose());
        }

        boolean isAngry = pose.getEmotion() == WeepingAngelPose.Emotion.ANGRY || pose.getEmotion() == WeepingAngelPose.Emotion.SCREAM;
        float angleX = isAngry ? 20F : 0;
        float angleY = isAngry ? 60F : 45F;
        float angleZ = 0;

        if (pose.getEmotion() == WeepingAngelPose.Emotion.SCREAM) {
            angleY += 10F;
            angleX -= 10F;
        }

        head.xRot = (float) Math.toRadians(0);
        head.yRot = (float) Math.toRadians(0);
        head.zRot = (float) Math.toRadians(0);

        rightWing.xRot = (float) Math.toRadians(angleX);
        rightWing.yRot = (float) Math.toRadians(angleY);
        rightWing.zRot = (float) Math.toRadians(angleZ);
        leftWing.xRot = (float) Math.toRadians(angleX);
        leftWing.yRot = (float) Math.toRadians(-angleY);
        leftWing.zRot = (float) Math.toRadians(angleZ);


        if (pose == WeepingAngelPose.FURIOUS) {
            rightArm.xRot = (float) Math.toRadians(-115);
            rightArm.yRot = (float) Math.toRadians(0);
            rightArm.zRot = (float) Math.toRadians(0);

            leftArm.xRot = (float) Math.toRadians(-55);
            leftArm.yRot = (float) Math.toRadians(0);
            leftArm.zRot = (float) Math.toRadians(0);

            head.xRot = (float) Math.toRadians(17.5);
            head.yRot = (float) Math.toRadians(0);
            head.zRot = (float) Math.toRadians(-10);
            return;
        }


        if (pose == WeepingAngelPose.ANGRY) {
            rightArm.xRot = (float) Math.toRadians(-90);
            rightArm.yRot = (float) Math.toRadians(-20);
            rightArm.zRot = (float) Math.toRadians(30);

            leftArm.xRot = (float) Math.toRadians(-90);
            leftArm.yRot = (float) Math.toRadians(25);
            leftArm.zRot = (float) Math.toRadians(-17.5);

            head.xRot = (float) Math.toRadians(0);
            head.yRot = (float) Math.toRadians(-12.5);
            head.zRot = (float) Math.toRadians(0);
            return;
        }


        if (pose == WeepingAngelPose.HIDING) {
            head.xRot = (float) Math.toRadians(20);
            head.yRot = (float) Math.toRadians(0);
            head.zRot = (float) Math.toRadians(0);

            rightArm.xRot = (float) Math.toRadians(-105);
            rightArm.yRot = (float) Math.toRadians(20);
            rightArm.zRot = (float) Math.toRadians(12.5);

            leftArm.xRot = (float) Math.toRadians(-105);
            leftArm.yRot = (float) Math.toRadians(-20);
            leftArm.zRot = (float) Math.toRadians(-12.5);
            return;
        }

        if (pose == WeepingAngelPose.APPROACH) {
            rightArm.xRot = -1.04533F;
            rightArm.yRot = -0.55851F;
            rightArm.zRot = 0.0F;
            leftArm.xRot = -1.04533F;
            leftArm.yRot = 0.55851F;
            leftArm.zRot = 0.0F;
            return;
        }

        if (pose == WeepingAngelPose.IDLE) {
            head.xRot = (float) Math.toRadians(0);
            head.yRot = (float) Math.toRadians(0);
            head.zRot = (float) Math.toRadians(0);

            rightArm.xRot = (float) Math.toRadians(0);
            rightArm.yRot = (float) Math.toRadians(0);
            rightArm.zRot = (float) Math.toRadians(-7.5);

            leftArm.xRot = (float) Math.toRadians(0);
            leftArm.yRot = (float) Math.toRadians(0);
            leftArm.zRot = (float) Math.toRadians(7.5);
            return;
        }

        if (pose == WeepingAngelPose.SHY) {
            rightArm.xRot = (float) Math.toRadians(-90);
            rightArm.yRot = (float) Math.toRadians(-1.5);
            rightArm.zRot = (float) Math.toRadians(-20);

            leftArm.xRot = (float) Math.toRadians(-120);
            leftArm.yRot = (float) Math.toRadians(-36);
            leftArm.zRot = (float) Math.toRadians(10);

            head.xRot = (float) Math.toRadians(20);
            head.yRot = (float) Math.toRadians(-40);
            head.zRot = (float) Math.toRadians(-20);
            return;
        }
    }


    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
        rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
        Legs.render(matrixStack, buffer, packedLight, packedOverlay);
        leftWing.render(matrixStack, buffer, packedLight, packedOverlay);
        rightWing.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    @Override
    public ResourceLocation generateTex(WeepingAngelPose pose, AbstractVariant abstractVariant) {
        return IDLE;
    }

    @Override
    public ResourceLocation getTextureForPose(Object angel, WeepingAngelPose pose) {
        if (angel instanceof WeepingAngelEntity) {
            WeepingAngelEntity weepingAngel = (WeepingAngelEntity) angel;
            return generateTex(pose, weepingAngel.getVariant());
        }

        if (angel instanceof StatueTile) {
            StatueTile statueTile = (StatueTile) angel;
            return generateTex(statueTile.getPose(), statueTile.getAngelVarients());
        }

        if (angel instanceof PlinthTile) {
            PlinthTile plinthTile = (PlinthTile) angel;
            return generateTex(plinthTile.getPose(), plinthTile.getVariant());
        }
        return IDLE;
    }

    @Override
    public WeepingAngelPose getAngelPose() {
        return weepingAngelPose;
    }

    @Override
    public void setAngelPose(WeepingAngelPose angelPose) {
        this.weepingAngelPose = angelPose;
    }



}