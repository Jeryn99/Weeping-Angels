package me.suff.mc.angels.client.models;

import me.suff.mc.angels.common.entity.WeepingAngelEntity;
import me.suff.mc.angels.enums.WeepingAngelPose;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

/* Created by Craig on 18/02/2021 */
public class WeepingAngelModel extends EntityModel< WeepingAngelEntity > {

    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart leftArm;
    private final ModelPart rightArm;
    private final ModelPart Legs;
    private final ModelPart leftWing;
    private final ModelPart rightWing;
    private WeepingAngelPose weepingAngelPose = WeepingAngelPose.APPROACH;

    public WeepingAngelModel() {
        textureWidth = 128;
        textureHeight = 128;
        head = new ModelPart(this);
        head.setPivot(0.0F, 0.0F, 0.0F);
        head.setTextureOffset(0, 17).addCuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
        head.setTextureOffset(72, 0).addCuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F, false);

        body = new ModelPart(this);
        body.setPivot(0.0F, 0.0F, 0.0F);
        body.setTextureOffset(56, 17).addCuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);
        body.setTextureOffset(32, 17).addCuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.5F, false);

        leftArm = new ModelPart(this);
        leftArm.setPivot(-5.0F, 2.0F, 0.0F);
        leftArm.setTextureOffset(24, 59).addCuboid(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.0F, false);

        rightArm = new ModelPart(this);
        rightArm.setPivot(5.0F, 2.0F, 0.0F);
        rightArm.setTextureOffset(10, 59).addCuboid(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.0F, false);

        Legs = new ModelPart(this);
        Legs.setPivot(0.0F, 9.25F, 0.0F);
        Legs.setTextureOffset(40, 0).addCuboid(-5.0F, -0.25F, -3.0F, 10.0F, 11.0F, 6.0F, 0.0F, false);
        Legs.setTextureOffset(0, 0).addCuboid(-6.0F, 10.75F, -4.0F, 12.0F, 4.0F, 8.0F, 0.0F, false);

        leftWing = new ModelPart(this);
        leftWing.setPivot(-1.0F, 5.0F, 2.0F);
        setRotationAngle(leftWing, 0.0F, -0.7854F, 0.0F);
        leftWing.setTextureOffset(0, 101).addCuboid(-1.0F, -4.0F, 0.0F, 2.0F, 5.0F, 3.0F, 0.0F, false);
        leftWing.setTextureOffset(6, 83).addCuboid(-1.0F, -8.9F, 5.0F, 2.0F, 14.0F, 1.0F, 0.0F, false);
        leftWing.setTextureOffset(18, 83).addCuboid(-1.0F, -6.9F, 3.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);
        leftWing.setTextureOffset(8, 33).addCuboid(-1.0F, -10.9F, 6.0F, 2.0F, 21.0F, 3.0F, 0.0F, false);
        leftWing.setTextureOffset(0, 33).addCuboid(-1.0F, -10.0F, 9.0F, 2.0F, 24.0F, 2.0F, 0.0F, false);
        leftWing.setTextureOffset(38, 59).addCuboid(-1.0F, -8.0F, 11.0F, 2.0F, 17.0F, 1.0F, 0.0F, false);

        rightWing = new ModelPart(this);
        rightWing.setPivot(1.0F, 5.0F, 2.0F);
        setRotationAngle(rightWing, 0.0F, 0.7854F, 0.0F);
        rightWing.setTextureOffset(10, 101).addCuboid(-1.0F, -4.0F, 0.0F, 2.0F, 5.0F, 3.0F, 0.0F, false);
        rightWing.setTextureOffset(12, 83).addCuboid(-1.0F, -8.9F, 5.0F, 2.0F, 14.0F, 1.0F, 0.0F, false);
        rightWing.setTextureOffset(26, 83).addCuboid(-1.0F, -6.9F, 3.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);
        rightWing.setTextureOffset(18, 33).addCuboid(-1.0F, -10.0F, 9.0F, 2.0F, 24.0F, 2.0F, 0.0F, false);
        rightWing.setTextureOffset(0, 83).addCuboid(-1.0F, -8.0F, 11.0F, 2.0F, 17.0F, 1.0F, 0.0F, false);
        rightWing.setTextureOffset(0, 59).addCuboid(-1.0F, -10.9F, 6.0F, 2.0F, 21.0F, 3.0F, 0.0F, false);
    }

    public WeepingAngelPose getWeepingAngelPose() {
        return weepingAngelPose;
    }

    public void setWeepingAngelPose(WeepingAngelPose weepingAngelPose) {
        this.weepingAngelPose = weepingAngelPose;
    }

    @Override
    public void setAngles(WeepingAngelEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        WeepingAngelPose pose = weepingAngelPose;
        if (entity != null) {
            pose = WeepingAngelPose.getPose(entity.getAngelPose());
        }

        boolean isAngry = pose.getEmotion() == WeepingAngelPose.Emotion.ANGRY || pose.getEmotion() == WeepingAngelPose.Emotion.SCREAM;
        float angleX = isAngry ? 20F : 0;
        float angleY = isAngry ? 60F : 45F;
        float angleZ = 0;

        if (pose.getEmotion() == WeepingAngelPose.Emotion.SCREAM) {
            angleY += 10F;
            angleX -= 10F;
        }

        head.pitch = (float) Math.toRadians(0);
        head.yaw = (float) Math.toRadians(0);
        head.roll = (float) Math.toRadians(0);

        rightWing.pitch = (float) Math.toRadians(angleX);
        rightWing.yaw = (float) Math.toRadians(angleY);
        rightWing.roll = (float) Math.toRadians(angleZ);
        leftWing.pitch = (float) Math.toRadians(angleX);
        leftWing.yaw = (float) Math.toRadians(-angleY);
        leftWing.roll = (float) Math.toRadians(angleZ);


        if (pose == WeepingAngelPose.FURIOUS) {
            rightArm.pitch = (float) Math.toRadians(-115);
            rightArm.yaw = (float) Math.toRadians(0);
            rightArm.roll = (float) Math.toRadians(0);

            leftArm.pitch = (float) Math.toRadians(-55);
            leftArm.yaw = (float) Math.toRadians(0);
            leftArm.roll = (float) Math.toRadians(0);

            head.pitch = (float) Math.toRadians(17.5);
            head.yaw = (float) Math.toRadians(0);
            head.roll = (float) Math.toRadians(-10);
            return;
        }


        if (pose == WeepingAngelPose.ANGRY) {
            rightArm.pitch = (float) Math.toRadians(-90);
            rightArm.yaw = (float) Math.toRadians(-20);
            rightArm.roll = (float) Math.toRadians(30);

            leftArm.pitch = (float) Math.toRadians(-90);
            leftArm.yaw = (float) Math.toRadians(25);
            leftArm.roll = (float) Math.toRadians(-17.5);

            head.pitch = (float) Math.toRadians(0);
            head.yaw = (float) Math.toRadians(-12.5);
            head.roll = (float) Math.toRadians(0);
            return;
        }


        if (pose == WeepingAngelPose.HIDING) {
            head.pitch = (float) Math.toRadians(20);
            head.yaw = (float) Math.toRadians(0);
            head.roll = (float) Math.toRadians(0);

            rightArm.pitch = (float) Math.toRadians(-105);
            rightArm.yaw = (float) Math.toRadians(20);
            rightArm.roll = (float) Math.toRadians(12.5);

            leftArm.pitch = (float) Math.toRadians(-105);
            leftArm.yaw = (float) Math.toRadians(-20);
            leftArm.roll = (float) Math.toRadians(-12.5);
            return;
        }

        if (pose == WeepingAngelPose.APPROACH) {
            rightArm.pitch = -1.04533F;
            rightArm.yaw = -0.55851F;
            rightArm.roll = 0.0F;
            leftArm.pitch = -1.04533F;
            leftArm.yaw = 0.55851F;
            leftArm.roll = 0.0F;
            return;
        }

        if (pose == WeepingAngelPose.IDLE) {
            head.pitch = (float) Math.toRadians(0);
            head.yaw = (float) Math.toRadians(0);
            head.roll = (float) Math.toRadians(0);

            rightArm.pitch = (float) Math.toRadians(0);
            rightArm.yaw = (float) Math.toRadians(0);
            rightArm.roll = (float) Math.toRadians(-7.5);

            leftArm.pitch = (float) Math.toRadians(0);
            leftArm.yaw = (float) Math.toRadians(0);
            leftArm.roll = (float) Math.toRadians(7.5);
            return;
        }

        if (pose == WeepingAngelPose.SHY) {
            rightArm.pitch = (float) Math.toRadians(-90);
            rightArm.yaw = (float) Math.toRadians(-1.5);
            rightArm.roll = (float) Math.toRadians(-20);

            leftArm.pitch = (float) Math.toRadians(-120);
            leftArm.yaw = (float) Math.toRadians(-36);
            leftArm.roll = (float) Math.toRadians(10);

            head.pitch = (float) Math.toRadians(20);
            head.yaw = (float) Math.toRadians(-40);
            head.roll = (float) Math.toRadians(-20);
            return;
        }
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
        rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
        Legs.render(matrixStack, buffer, packedLight, packedOverlay);
        leftWing.render(matrixStack, buffer, packedLight, packedOverlay);
        rightWing.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelPart bone, float x, float y, float z) {
        bone.pitch = x;
        bone.yaw = y;
        bone.roll = z;
    }
}
