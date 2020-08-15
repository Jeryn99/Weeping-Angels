package me.swirtzly.minecraft.angels.client.models.entity;

import me.swirtzly.minecraft.angels.client.poses.AngelPoses;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;

public class ModelAngelaAngel extends EntityModel<WeepingAngelEntity> {
    private final RendererModel head;
    private final RendererModel body;
    private final RendererModel leftArm;
    private final RendererModel rightArm;
    private final RendererModel Legs;
    private final RendererModel leftWing;
    private final RendererModel rightWing;

    public ModelAngelaAngel() {
        textureWidth = 128;
        textureHeight = 128;

        head = new RendererModel(this);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.cubeList.add(new ModelBox(head, 0, 17, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F, false));
        head.cubeList.add(new ModelBox(head, 72, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F, false));

        body = new RendererModel(this);
        body.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.cubeList.add(new ModelBox(body, 56, 17, -4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F, false));
        body.cubeList.add(new ModelBox(body, 32, 17, -4.0F, 0.0F, -2.0F, 8, 12, 4, 0.5F, false));

        leftArm = new RendererModel(this);
        leftArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        leftArm.cubeList.add(new ModelBox(leftArm, 24, 59, -2.0F, -2.0F, -2.0F, 3, 12, 4, 0.0F, false));

        rightArm = new RendererModel(this);
        rightArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        rightArm.cubeList.add(new ModelBox(rightArm, 10, 59, -1.0F, -2.0F, -2.0F, 3, 12, 4, 0.0F, false));

        Legs = new RendererModel(this);
        Legs.setRotationPoint(0.0F, 9.25F, 0.0F);
        Legs.cubeList.add(new ModelBox(Legs, 40, 0, -5.0F, -0.25F, -3.0F, 10, 11, 6, 0.0F, false));
        Legs.cubeList.add(new ModelBox(Legs, 0, 0, -6.0F, 10.75F, -4.0F, 12, 4, 8, 0.0F, false));

        leftWing = new RendererModel(this);
        leftWing.setRotationPoint(-1.0F, 5.0F, 2.0F);
        setRotationAngle(leftWing, 0.0F, -0.7854F, 0.0F);
        leftWing.cubeList.add(new ModelBox(leftWing, 0, 101, -1.0F, -4.0F, 0.0F, 2, 5, 3, 0.0F, false));
        leftWing.cubeList.add(new ModelBox(leftWing, 6, 83, -1.0F, -8.9F, 5.0F, 2, 14, 1, 0.0F, false));
        leftWing.cubeList.add(new ModelBox(leftWing, 18, 83, -1.0F, -6.9F, 3.0F, 2, 10, 2, 0.0F, false));
        leftWing.cubeList.add(new ModelBox(leftWing, 8, 33, -1.0F, -10.9F, 6.0F, 2, 21, 3, 0.0F, false));
        leftWing.cubeList.add(new ModelBox(leftWing, 0, 33, -1.0F, -10.0F, 9.0F, 2, 24, 2, 0.0F, false));
        leftWing.cubeList.add(new ModelBox(leftWing, 38, 59, -1.0F, -8.0F, 11.0F, 2, 17, 1, 0.0F, false));

        rightWing = new RendererModel(this);
        rightWing.setRotationPoint(1.0F, 5.0F, 2.0F);
        setRotationAngle(rightWing, 0.0F, 0.7854F, 0.0F);
        rightWing.cubeList.add(new ModelBox(rightWing, 10, 101, -1.0F, -4.0F, 0.0F, 2, 5, 3, 0.0F, false));
        rightWing.cubeList.add(new ModelBox(rightWing, 12, 83, -1.0F, -8.9F, 5.0F, 2, 14, 1, 0.0F, false));
        rightWing.cubeList.add(new ModelBox(rightWing, 26, 83, -1.0F, -6.9F, 3.0F, 2, 10, 2, 0.0F, false));
        rightWing.cubeList.add(new ModelBox(rightWing, 18, 33, -1.0F, -10.0F, 9.0F, 2, 24, 2, 0.0F, false));
        rightWing.cubeList.add(new ModelBox(rightWing, 0, 83, -1.0F, -8.0F, 11.0F, 2, 17, 1, 0.0F, false));
        rightWing.cubeList.add(new ModelBox(rightWing, 0, 59, -1.0F, -10.9F, 6.0F, 2, 21, 3, 0.0F, false));
    }

    @Override
    public void render(WeepingAngelEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        setRotationAngles(entity, f, f1, f2, f3, f4, f5);
        head.render(f5);
        body.render(f5);
        leftArm.render(f5);
        rightArm.render(f5);
        Legs.render(f5);
        leftWing.render(f5);
        rightWing.render(f5);
    }

    @Override
    public void setRotationAngles(WeepingAngelEntity weepingAngelEntity, float p_212844_2_, float p_212844_3_, float p_212844_4_, float p_212844_5_, float p_212844_6_, float p_212844_7_) {
        AngelPoses pose = AngelPoses.getPoseFromString(weepingAngelEntity.getAngelPose().toString());
        
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
        
        if(pose == AngelPoses.POSE_SHY){
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

    public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}