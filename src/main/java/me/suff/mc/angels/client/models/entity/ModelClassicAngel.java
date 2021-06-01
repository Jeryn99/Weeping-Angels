package me.suff.mc.angels.client.models.entity;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.client.poses.AngelPoses;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.ResourceLocation;

/**
 * Angel Type: 2 - Classic
 */
public class ModelClassicAngel< T extends WeepingAngelEntity > extends BipedModel< T > implements IAngelModel {

    private final static ResourceLocation TEXTURE = new ResourceLocation(WeepingAngels.MODID,
            "textures/entities/angel_3.png");

    private RendererModel leftfoot;
    private RendererModel rightfoot;
    private RendererModel leftwing1;
    private RendererModel leftwing2;
    private RendererModel leftwing3;
    private RendererModel leftwing4;
    private RendererModel rightwing1;
    private RendererModel rightwing2;
    private RendererModel rightwing3;
    private RendererModel rightwing4;
    private RendererModel head;
    private RendererModel body;
    private RendererModel rightarm;
    private RendererModel leftarm;
    private RendererModel rightleg;
    private RendererModel leftleg;

    /**
     * Angel Type: 2 - Classic
     */
    public ModelClassicAngel() {
        textureHeight = 32;
        textureWidth = 64;
        leftfoot = new RendererModel(this, 32, 0);
        leftfoot.addBox(-2F, 7F, -4F, 6, 5, 8);
        leftfoot.setRotationPoint(2.0F, 12F, 0.0F);
        leftfoot.rotateAngleX = 0.0F;
        leftfoot.rotateAngleY = 0.0F;
        leftfoot.rotateAngleZ = 0.0F;
        leftfoot.mirror = false;
        rightfoot = new RendererModel(this, 32, 0);
        rightfoot.addBox(-4F, 7F, -4F, 6, 5, 8);
        rightfoot.setRotationPoint(-2F, 12F, 0.0F);
        rightfoot.rotateAngleX = 0.0F;
        rightfoot.rotateAngleY = 0.0F;
        rightfoot.rotateAngleZ = 0.0F;
        rightfoot.mirror = false;
        leftwing1 = new RendererModel(this, 40, 25);
        leftwing1.addBox(-0.5F, -1F, 1.0F, 1, 5, 2);
        leftwing1.setRotationPoint(1.0F, 1.0F, 1.0F);
        leftwing1.rotateAngleX = 0.20944F;
        leftwing1.rotateAngleY = 0.61087F;
        leftwing1.rotateAngleZ = 0.0F;
        leftwing1.mirror = false;
        leftwing2 = new RendererModel(this, 46, 19);
        leftwing2.addBox(-0.5F, -2F, 3F, 1, 11, 2);
        leftwing2.setRotationPoint(1.0F, 1.0F, 1.0F);
        leftwing2.rotateAngleX = 0.20944F;
        leftwing2.rotateAngleY = 0.61087F;
        leftwing2.rotateAngleZ = 0.01745F;
        leftwing2.mirror = false;
        leftwing3 = new RendererModel(this, 58, 12);
        leftwing3.addBox(-0.5F, -2F, 5F, 1, 18, 2);
        leftwing3.setRotationPoint(1.0F, 1.0F, 1.0F);
        leftwing3.rotateAngleX = 0.20944F;
        leftwing3.rotateAngleY = 0.61087F;
        leftwing3.rotateAngleZ = 0.0F;
        leftwing3.mirror = false;
        leftwing4 = new RendererModel(this, 52, 16);
        leftwing4.addBox(-0.5F, 0.0F, 7F, 1, 14, 2);
        leftwing4.setRotationPoint(1.0F, 1.0F, 1.0F);
        leftwing4.rotateAngleX = 0.20944F;
        leftwing4.rotateAngleY = 0.61087F;
        leftwing4.rotateAngleZ = 0.0F;
        leftwing4.mirror = false;
        rightwing1 = new RendererModel(this, 40, 25);
        rightwing1.addBox(-0.5F, -1F, 1.0F, 1, 5, 2);
        rightwing1.setRotationPoint(-2F, 1.0F, 1.0F);
        rightwing1.rotateAngleX = 0.20944F;
        rightwing1.rotateAngleY = -0.61087F;
        rightwing1.rotateAngleZ = 0.0F;
        rightwing1.mirror = false;
        rightwing2 = new RendererModel(this, 46, 19);
        rightwing2.addBox(-0.5F, -2F, 3F, 1, 11, 2);
        rightwing2.setRotationPoint(-2F, 1.0F, 1.0F);
        rightwing2.rotateAngleX = 0.20944F;
        rightwing2.rotateAngleY = -0.61087F;
        rightwing2.rotateAngleZ = 0.0F;
        rightwing2.mirror = false;
        rightwing3 = new RendererModel(this, 58, 12);
        rightwing3.addBox(-0.5F, -2F, 5F, 1, 18, 2);
        rightwing3.setRotationPoint(-2F, 1.0F, 1.0F);
        rightwing3.rotateAngleX = 0.20944F;
        rightwing3.rotateAngleY = -0.61087F;
        rightwing3.rotateAngleZ = 0.0F;
        rightwing3.mirror = false;
        rightwing4 = new RendererModel(this, 52, 16);
        rightwing4.addBox(-0.5F, 0.0F, 7F, 1, 14, 2);
        rightwing4.setRotationPoint(-2F, 1.0F, 1.0F);
        rightwing4.rotateAngleX = 0.20944F;
        rightwing4.rotateAngleY = -0.61087F;
        rightwing4.rotateAngleZ = 0.0F;
        rightwing4.mirror = false;
        head = new RendererModel(this, 0, 0);
        head.addBox(-4F, -8F, -4F, 8, 8, 8);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.rotateAngleX = 0.24435F;
        head.rotateAngleY = 0.0F;
        head.rotateAngleZ = 0.0F;
        head.mirror = false;
        body = new RendererModel(this, 0, 16);
        body.addBox(-4F, 0.0F, -2F, 8, 12, 4);
        body.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.rotateAngleX = 0.0F;
        body.rotateAngleY = 0.0F;
        body.rotateAngleZ = 0.0F;
        body.mirror = false;
        rightarm = new RendererModel(this, 24, 19);
        rightarm.addBox(-3F, 0.0F, -2F, 4, 9, 4);
        rightarm.setRotationPoint(-5F, 0.0F, 0.0F);
        rightarm.rotateAngleX = -1.74533F;
        rightarm.rotateAngleY = -0.55851F;
        rightarm.rotateAngleZ = 0.0F;
        rightarm.mirror = false;
        leftarm = new RendererModel(this, 24, 19);
        leftarm.addBox(-1F, 0.0F, -2F, 4, 9, 4);
        leftarm.setRotationPoint(5F, 0.0F, 0.0F);
        leftarm.rotateAngleX = -1.74533F;
        leftarm.rotateAngleY = 0.55851F;
        leftarm.rotateAngleZ = 0.0F;
        leftarm.mirror = false;
        rightleg = new RendererModel(this, 24, 19);
        rightleg.addBox(-2F, 0.0F, -2F, 4, 9, 4);
        rightleg.setRotationPoint(-2F, 12F, 0.0F);
        rightleg.rotateAngleX = 0.0F;
        rightleg.rotateAngleY = 0.0F;
        rightleg.rotateAngleZ = 0.0F;
        rightleg.mirror = false;
        leftleg = new RendererModel(this, 24, 19);
        leftleg.addBox(-2F, 0.0F, -2F, 4, 9, 4);
        leftleg.setRotationPoint(2.0F, 12F, 0.0F);
        leftleg.rotateAngleX = 0.0F;
        leftleg.rotateAngleY = 0.0F;
        leftleg.rotateAngleZ = 0.0F;
        leftleg.mirror = false;
    }

    @Override
    public void render(WeepingAngelEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        setRotationAngles(entity, f, f1, f2, f3, f4, f5);
        leftfoot.render(f5);
        rightfoot.render(f5);
        leftwing1.render(f5);
        leftwing2.render(f5);
        leftwing3.render(f5);
        leftwing4.render(f5);
        rightwing1.render(f5);
        rightwing2.render(f5);
        rightwing3.render(f5);
        rightwing4.render(f5);
        head.render(f5);
        body.render(f5);
        rightarm.render(f5);
        leftarm.render(f5);
        rightleg.render(f5);
        leftleg.render(f5);
    }

    @Override
    public void setRotationAngles(WeepingAngelEntity weepingAngelEntity, float p_212844_2_, float p_212844_3_, float p_212844_4_, float p_212844_5_, float p_212844_6_, float p_212844_7_) {
        AngelPoses pose = AngelPoses.getPoseFromString(weepingAngelEntity.getAngelPose().toString());

        if (pose == AngelPoses.POSE_ANGRY_TWO) {
            rightarm.rotateAngleX = (float) Math.toRadians(-115);
            rightarm.rotateAngleY = (float) Math.toRadians(0);
            rightarm.rotateAngleZ = (float) Math.toRadians(0);

            leftarm.rotateAngleX = (float) Math.toRadians(-55);
            leftarm.rotateAngleY = (float) Math.toRadians(0);
            leftarm.rotateAngleZ = (float) Math.toRadians(0);

            head.rotateAngleX = (float) Math.toRadians(17.5);
            head.rotateAngleY = (float) Math.toRadians(0);
            head.rotateAngleZ = (float) Math.toRadians(-10);
            return;
        }


        if (pose.create().isAngry()) {
            rightarm.rotateAngleX = (float) Math.toRadians(-90);
            rightarm.rotateAngleY = (float) Math.toRadians(-20);
            rightarm.rotateAngleZ = (float) Math.toRadians(30);

            leftarm.rotateAngleX = (float) Math.toRadians(-90);
            leftarm.rotateAngleY = (float) Math.toRadians(25);
            leftarm.rotateAngleZ = (float) Math.toRadians(-17.5);

            head.rotateAngleX = (float) Math.toRadians(0);
            head.rotateAngleY = (float) Math.toRadians(-12.5);
            head.rotateAngleZ = (float) Math.toRadians(0);
            return;
        }

        if (pose == AngelPoses.POSE_HIDING_FACE) {
            head.rotateAngleX = (float) Math.toRadians(20);
            head.rotateAngleY = (float) Math.toRadians(0);
            head.rotateAngleZ = (float) Math.toRadians(0);

            rightarm.rotateAngleX = (float) Math.toRadians(-105);
            rightarm.rotateAngleY = (float) Math.toRadians(20);
            rightarm.rotateAngleZ = (float) Math.toRadians(12.5);

            leftarm.rotateAngleX = (float) Math.toRadians(-105);
            leftarm.rotateAngleY = (float) Math.toRadians(-20);
            leftarm.rotateAngleZ = (float) Math.toRadians(-12.5);
            return;
        }

        if (pose == AngelPoses.POSE_IDLE) {
            head.rotateAngleX = (float) Math.toRadians(0);
            head.rotateAngleY = (float) Math.toRadians(0);
            head.rotateAngleZ = (float) Math.toRadians(0);

            rightarm.rotateAngleX = (float) Math.toRadians(0);
            rightarm.rotateAngleY = (float) Math.toRadians(0);
            rightarm.rotateAngleZ = (float) Math.toRadians(7.5);

            leftarm.rotateAngleX = (float) Math.toRadians(0);
            leftarm.rotateAngleY = (float) Math.toRadians(0);
            leftarm.rotateAngleZ = (float) Math.toRadians(-7.5);
            return;
        }

        if (pose == AngelPoses.POSE_SHY) {
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
    public ResourceLocation getTextureForPose(WeepingAngelEntity angel) {
        return TEXTURE;
    }
}
