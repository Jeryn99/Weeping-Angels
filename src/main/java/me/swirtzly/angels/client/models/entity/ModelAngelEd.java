package me.swirtzly.angels.client.models.entity;

import me.swirtzly.angels.client.models.poses.PoseManager;
import me.swirtzly.angels.common.entities.EntityWeepingAngel;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Weeping Angel - EdusgprNetwork Created using Tabula 5.1.0
 */
public class ModelAngelEd extends ModelBiped implements IAngelModel {

    private ModelRenderer right_wing_0;
    private ModelRenderer left_wing_0;
    private ModelRenderer back_cloth_2;
    private ModelRenderer head_2;
    private ModelRenderer body_2;
    private ModelRenderer head;
    private ModelRenderer body;
    private ModelRenderer left_arm;
    private ModelRenderer right_arm;
    private ModelRenderer cloth_0;
    private ModelRenderer cloth_1;
    private ModelRenderer cloth_2;
    private ModelRenderer back_cloth;
    private ModelRenderer left_wing_1;
    private ModelRenderer right_wing_1;
    private ModelRenderer nose;
    private ModelRenderer face;
    private ModelRenderer right_eyebrow;
    private ModelRenderer left_eyebrow;
    private ModelRenderer coverup;
    private ModelRenderer angry_mouth;
    private ModelRenderer teeth;
    private ModelRenderer teeth_1;
    private ModelRenderer teeth_2;
    private ModelRenderer teeth_3;
    private ModelRenderer teeth_4;
    private ModelRenderer teeth_5;
    private ModelRenderer wrist_left;
    private ModelRenderer wrist_right;
    private ModelRenderer zeth;
    private ModelRenderer left_wing_2;
    private ModelRenderer left_wing_3;
    private ModelRenderer left_wing_4;
    private ModelRenderer right_wing_2;
    private ModelRenderer right_wing_3;
    private ModelRenderer right_wing_4;

    public ModelAngelEd() {
        textureWidth = 88;
        textureHeight = 88;
        teeth_1 = new ModelRenderer(this, 63, 39);
        teeth_1.setRotationPoint(0.8F, -3.0F, 0.0F);
        teeth_1.addBox(-1.0F, 0.0F, -0.03F, 1, 1, 1, 0.0F);
        setRotateAngle(teeth_1, 0.0F, 0.0F, -0.7853981633974483F);
        wrist_left = new ModelRenderer(this, 34, 52);
        wrist_left.setRotationPoint(0.0F, 4.0F, 2.0F);
        wrist_left.addBox(-1.0F, 0.0F, -4.0F, 3, 6, 4, 0.0F);
        cloth_1 = new ModelRenderer(this, 34, 44);
        cloth_1.setRotationPoint(0.0F, 24.0F, 3.3F);
        cloth_1.addBox(-4.0F, -1.0F, -2.5F, 8, 1, 5, 0.0F);
        left_wing_4 = new ModelRenderer(this, 22, 36);
        left_wing_4.setRotationPoint(0.0F, 5.0F, 0.0F);
        left_wing_4.addBox(-1.0F, 0.0F, -2.0F, 2, 5, 2, 0.0F);
        setRotateAngle(left_wing_4, -1.1383037381507017F, 0.0F, 0.0F);
        teeth_3 = new ModelRenderer(this, 63, 39);
        teeth_3.setRotationPoint(-0.5F, -3.0F, 0.0F);
        teeth_3.addBox(-1.0F, 0.0F, -0.03F, 1, 1, 1, 0.0F);
        setRotateAngle(teeth_3, 0.0F, 0.0F, -0.7853981633974483F);
        right_arm = new ModelRenderer(this, 0, 32);
        right_arm.setRotationPoint(-5.0F, 2.5F, 0.0F);
        right_arm.addBox(-2.0F, -2.0F, -2.0F, 3, 6, 4, 0.0F);
        setRotateAngle(right_arm, 0.0F, 0.0F, 0.10000736613927509F);
        head = new ModelRenderer(this, 0, 16);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        cloth_0 = new ModelRenderer(this, 9, 43);
        cloth_0.setRotationPoint(0.0F, 24.0F, 0.0F);
        cloth_0.addBox(-5.0F, -1.0F, -2.0F, 10, 1, 5, 0.0F);
        teeth_2 = new ModelRenderer(this, 63, 39);
        teeth_2.setRotationPoint(0.5F, -3.0F, 0.0F);
        teeth_2.addBox(-1.0F, 0.0F, -0.03F, 1, 1, 1, 0.0F);
        setRotateAngle(teeth_2, 0.0F, 0.0F, -0.7853981633974483F);
        nose = new ModelRenderer(this, 32, 0);
        nose.setRotationPoint(0.0F, -4.5F, -4.0F);
        nose.addBox(-0.5F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        setRotateAngle(nose, -0.2246238747316702F, 0.0F, 0.0F);
        coverup = new ModelRenderer(this, 54, 34);
        coverup.setRotationPoint(0.0F, -6.0F, -4.0F);
        coverup.addBox(-4.0F, 0.0F, -0.03F, 8, 1, 1, 0.0F);
        face = new ModelRenderer(this, 54, 28);
        face.setRotationPoint(0.0F, -4.5F, -4.0F);
        face.addBox(-3.0F, 0.0F, -0.01F, 6, 3, 1, 0.0F);
        back_cloth = new ModelRenderer(this, 60, 44);
        back_cloth.setRotationPoint(0.0F, 12.0F, 2.0F);
        back_cloth.addBox(-3.0F, 0.0F, -3.0F, 6, 12, 3, 0.0F);
        setRotateAngle(back_cloth, 0.2792526803190927F, 0.0F, 0.0F);
        teeth_5 = new ModelRenderer(this, 63, 39);
        teeth_5.setRotationPoint(0.15F, -3.0F, 0.0F);
        teeth_5.addBox(-1.0F, 0.0F, -0.03F, 1, 1, 1, 0.0F);
        setRotateAngle(teeth_5, 0.0F, 0.0F, -0.7853981633974483F);
        cloth_2 = new ModelRenderer(this, 10, 32);
        cloth_2.setRotationPoint(0.0F, 24.0F, 6.6F);
        cloth_2.addBox(-3.0F, -1.0F, -2.5F, 6, 1, 3, 0.0F);
        left_wing_1 = new ModelRenderer(this, 24, 0);
        left_wing_1.setRotationPoint(2.4F, 2.0F, 1.5F);
        left_wing_1.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        setRotateAngle(left_wing_1, 1.53588974175501F, 0.9424777960769379F, 0.0F);
        head_2 = new ModelRenderer(this, 0, 0);
        head_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        head_2.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
        right_wing_2 = new ModelRenderer(this, 46, 27);
        right_wing_2.setRotationPoint(0.0F, 4.0F, -1.0F);
        right_wing_2.addBox(-1.0F, 0.0F, 0.0F, 2, 7, 2, 0.0F);
        setRotateAngle(right_wing_2, 1.2292353921796064F, 0.0F, 0.0F);
        zeth = new ModelRenderer(this, 20, 50);
        zeth.setRotationPoint(0.0F, 0.0F, -2.0F);
        zeth.addBox(-4.5F, -1.0F, -0.6F, 9, 1, 1, 0.0F);
        back_cloth_2 = new ModelRenderer(this, 0, 49);
        back_cloth_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        back_cloth_2.addBox(-3.0F, 0.0F, -3.0F, 6, 12, 3, 0.25F);
        setRotateAngle(back_cloth_2, 0.0F, 0.0F, 0.0F);
        teeth_4 = new ModelRenderer(this, 63, 39);
        teeth_4.setRotationPoint(-0.15F, -3.0F, 0.0F);
        teeth_4.addBox(-1.0F, 0.0F, -0.03F, 1, 1, 1, 0.0F);
        setRotateAngle(teeth_4, 0.0F, 0.0F, -0.7853981633974483F);
        right_wing_4 = new ModelRenderer(this, 24, 16);
        right_wing_4.setRotationPoint(0.0F, 5.0F, 0.0F);
        right_wing_4.addBox(-1.0F, 0.0F, -2.0F, 2, 5, 2, 0.0F);
        setRotateAngle(right_wing_4, -1.1383037381507017F, 0.0F, 0.0F);
        body_2 = new ModelRenderer(this, 32, 0);
        body_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        body_2.addBox(-4.0F, 0.0F, -2.0F, 8, 23, 4, 0.25F);
        right_wing_1 = new ModelRenderer(this, 0, 0);
        right_wing_1.setRotationPoint(-2.4F, 2.0F, 1.5F);
        right_wing_1.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        setRotateAngle(right_wing_1, 1.53588974175501F, -0.9424777960769379F, 0.0F);
        left_eyebrow = new ModelRenderer(this, 62, 32);
        left_eyebrow.setRotationPoint(3.0F, -4.5F, -4.0F);
        left_eyebrow.addBox(-2.5F, -1.0F, -0.02F, 3, 1, 1, 0.0F);
        angry_mouth = new ModelRenderer(this, 63, 36);
        angry_mouth.setRotationPoint(0.0F, 0.0F, -4.0F);
        angry_mouth.addBox(-2.0F, -1.8F, -0.02F, 4, 2, 1, 0.0F);
        right_wing_3 = new ModelRenderer(this, 0, 16);
        right_wing_3.setRotationPoint(0.0F, 7.0F, 2.0F);
        right_wing_3.addBox(-1.0F, 0.0F, -2.0F, 2, 5, 2, 0.0F);
        setRotateAngle(right_wing_3, -1.2292353921796064F, 0.0F, 0.0F);
        right_eyebrow = new ModelRenderer(this, 54, 32);
        right_eyebrow.setRotationPoint(-3.0F, -4.5F, -4.0F);
        right_eyebrow.addBox(-0.5F, -1.0F, -0.02F, 3, 1, 1, 0.0F);
        left_wing_2 = new ModelRenderer(this, 78, 42);
        left_wing_2.setRotationPoint(0.0F, 4.0F, -1.0F);
        left_wing_2.addBox(-1.0F, 0.0F, 0.0F, 2, 7, 2, 0.0F);
        setRotateAngle(left_wing_2, 1.2292353921796064F, 0.0F, 0.0F);
        body = new ModelRenderer(this, 56, 0);
        body.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.addBox(-4.0F, 0.0F, -2.0F, 8, 24, 4, 0.0F);
        right_wing_0 = new ModelRenderer(this, 0, 49);
        right_wing_0.setRotationPoint(-2.4F, 2.0F, 1.5F);
        right_wing_0.addBox(0.0F, 0.0F, -13.0F, 1, 11, 18, 0.0F);
        setRotateAngle(right_wing_0, 1.53588974175501F, -0.9424777960769379F, 0.0F);
        teeth = new ModelRenderer(this, 63, 39);
        teeth.setRotationPoint(-0.8F, -3.0F, 0.0F);
        teeth.addBox(-1.0F, 0.0F, -0.03F, 1, 1, 1, 0.0F);
        setRotateAngle(teeth, 0.0F, 0.0F, -0.7853981633974483F);
        left_wing_0 = new ModelRenderer(this, 38, 50);
        left_wing_0.setRotationPoint(2.4F, 2.0F, 1.5F);
        left_wing_0.addBox(-1.0F, 0.0F, -13.0F, 1, 11, 18, 0.0F);
        setRotateAngle(left_wing_0, 1.53588974175501F, 0.9424777960769379F, 0.0F);
        left_wing_3 = new ModelRenderer(this, 14, 36);
        left_wing_3.setRotationPoint(0.0F, 7.0F, 2.0F);
        left_wing_3.addBox(-1.0F, 0.0F, -2.0F, 2, 5, 2, 0.0F);
        setRotateAngle(left_wing_3, -1.2292353921796064F, 0.0F, 0.0F);
        left_arm = new ModelRenderer(this, 32, 27);
        left_arm.setRotationPoint(5.0F, 2.5F, -0.0F);
        left_arm.addBox(-1.0F, -2.0F, -2.0F, 3, 6, 4, 0.0F);
        setRotateAngle(left_arm, 0.0F, 0.0F, -0.10000736613927509F);
        wrist_right = new ModelRenderer(this, 20, 52);
        wrist_right.setRotationPoint(0.0F, 4.0F, 2.0F);
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

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        PoseManager.AngelPoses pose = PoseManager.AngelPoses.IDLE;
        if (entityIn instanceof EntityWeepingAngel) {
            EntityWeepingAngel angel = (EntityWeepingAngel) entityIn;
            pose = PoseManager.AngelPoses.valueOf(angel.getPose());
            setupAngles(angel.swingProgress, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, pose);
        } else {
            setupAngles(0, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, pose);
        }

        renderQuickly(pose, 0.06125F);
    }

    private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        // Not Implemented
    }

    @Override
    protected ModelRenderer getArmForSide(EnumHandSide side) {
        return side == EnumHandSide.LEFT ? left_arm : right_arm;
    }

    public float degreeToRadian(float degree) {
        return (float) (degree * Math.PI / 180);
    }

    @SideOnly(Side.CLIENT)
    public void resetAngles(ModelRenderer model) {
        model.rotateAngleX = 0;
        model.rotateAngleY = 0;
        model.rotateAngleY = 0;
    }

    @Override
    public void setupAngles(float swingProgress, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, PoseManager.AngelPoses pose) {
        head.rotateAngleY = netheadYaw * 0.017453292F;
        head.rotateAngleX = headPitch * 0.017453292F;

        right_arm.rotationPointY = 2.5F;
        left_arm.rotationPointY = 2.5F;
        left_arm.rotateAngleX = 0;
        left_arm.rotateAngleY = 0;
        left_arm.rotateAngleZ = 0;
        right_arm.rotateAngleX = 0;
        right_arm.rotateAngleY = 0;
        right_arm.rotateAngleZ = 0;

        if (pose != null) {
            angry_mouth.isHidden = !pose.hasAngryFace();

            switch (pose) {
                case ANGRY:
                    float swing = MathHelper.sin(swingProgress * (float) Math.PI);
                    float f1 = MathHelper.sin((1.0F - (1.0F - swingProgress) * (1.0F - swingProgress)) * (float) Math.PI);
                    head.rotateAngleX = headPitch * 0.017453292F;
                    right_arm.rotateAngleZ = 0.0F;
                    left_arm.rotateAngleZ = 0.0F;
                    right_arm.rotateAngleY = -(0.1F - swing * 0.6F);
                    left_arm.rotateAngleY = 0.1F - swing * 0.6F;
                    right_arm.rotateAngleX = -1.5F;
                    left_arm.rotateAngleX = -1.5F;
                    right_arm.rotateAngleX += swing * 1.2F - f1 * 0.4F;
                    left_arm.rotateAngleX += swing * 1.2F - f1 * 0.4F;
                    right_arm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
                    left_arm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
                    right_arm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
                    left_arm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
                    break;
                case ANGRY_TWO:
                    wrist_left.rotateAngleX = -0.52F;
                    left_arm.rotateAngleX = -1.5F;
                    left_arm.rotateAngleY = 0.31F;
                    left_arm.rotateAngleZ = -0.087F;
                    wrist_right.rotateAngleX = -0.52F;
                    right_arm.rotateAngleX = -1.5F;
                    right_arm.rotateAngleY = -0.31F;
                    right_arm.rotateAngleZ = 0.087F;
                    break;
                case SHY:
                    left_arm.rotateAngleX = 0;
                    left_arm.rotateAngleY = 0.4F;
                    left_arm.rotateAngleZ = -0.3F;
                    wrist_left.rotateAngleX = -0.4F;
                    right_arm.rotateAngleX = -1.3F;
                    right_arm.rotateAngleY = -0.9F;
                    wrist_right.rotateAngleX = -0.9F;
                    head.rotateAngleX = degreeToRadian(30);
                    head.rotateAngleY = degreeToRadian(19);
                    head.rotateAngleZ = 0;
                    break;
                case HIDING_FACE_ANGRY:
                case HIDING_FACE:
                    wrist_left.rotateAngleX = -0.52F;
                    left_arm.rotateAngleX = -1.85F;
                    left_arm.rotateAngleY = 0.61F;
                    left_arm.rotateAngleZ = -0.087F;
                    wrist_right.rotateAngleX = -0.52F;
                    right_arm.rotateAngleX = -1.85F;
                    right_arm.rotateAngleY = -0.61F;
                    right_arm.rotateAngleZ = 0.087F;
                    head.rotateAngleX = 0.11F;
                    head.rotateAngleY = 0.0F;
                    head.rotateAngleZ = 0.0F;
                    break;
                case OPEN_ARMS:
                    left_arm.rotateAngleX = degreeToRadian(-90);
                    right_arm.rotateAngleX = degreeToRadian(-90);

                    left_arm.rotateAngleY = degreeToRadian(30);
                    right_arm.rotateAngleY = degreeToRadian(-30);

                    left_arm.rotateAngleZ = degreeToRadian(-30);
                    right_arm.rotateAngleZ = degreeToRadian(30);

                    wrist_left.rotateAngleX = degreeToRadian(-45);
                    wrist_right.rotateAngleX = degreeToRadian(-45);
                    head.rotateAngleX = degreeToRadian(15);
                    body.rotateAngleX = degreeToRadian(0);
                    break;
                case IDLE:
                    right_arm.rotateAngleX = 0;
                    left_arm.rotateAngleX = 0;
                    wrist_left.rotateAngleX = 0;
                    wrist_right.rotateAngleX = 0;
                    head.rotateAngleX = headPitch * 0.017453292F + head.rotateAngleZ * netheadYaw * 0.005F + 0.112F;
                    break;
                default:
                    throw new IllegalStateException("Unexpected Angel pose: " + pose);
            }

            if (pose.hasAngryFace()) {
                right_eyebrow.rotateAngleZ = (float) (20 * Math.PI / 180);
                left_eyebrow.rotateAngleZ = (float) (-20 * Math.PI / 180);
                angry_mouth.isHidden = false;
            } else {
                right_eyebrow.rotateAngleZ = (float) (0 * Math.PI / 180);
                left_eyebrow.rotateAngleZ = (float) (0 * Math.PI / 180);
            }
        }
    }

    @Override
    public void renderQuickly(PoseManager.AngelPoses pose, float scale) {
        GlStateManager.pushMatrix();
        GlStateManager.enableCull();
        cloth_1.render(scale);
        right_arm.render(scale);
        head.render(scale);
        cloth_0.render(scale);
        back_cloth.render(scale);
        cloth_2.render(scale);
        left_wing_1.render(scale);
        body_2.render(scale);
        right_wing_1.render(scale);
        body.render(scale);
        right_wing_0.render(scale);
        left_wing_0.render(scale);
        left_arm.render(scale);
        GlStateManager.disableCull();
        GlStateManager.popMatrix();
    }
}

