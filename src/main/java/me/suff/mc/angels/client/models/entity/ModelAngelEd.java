package me.suff.mc.angels.client.models.entity;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.entities.WeepingAngel;
import me.suff.mc.angels.common.variants.AbstractVariant;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

/**
 * Angel Type: 1
 * Weeping Angel - EdusgprNetwork Created using Tabula 5.1.0
 */
public class ModelAngelEd extends ListModel<WeepingAngel> implements IAngelModel {

    private final ResourceLocation TEXTURE = new ResourceLocation(WeepingAngels.MODID,
            "textures/entities/angel_ed.png");

    private final ModelPart main;
    private final ModelPart right_wing_0;
    private final ModelPart left_wing_0;
    private final ModelPart back_cloth_2;
    private final ModelPart head_2;
    private final ModelPart body_2;
    private final ModelPart head;
    private final ModelPart nose;
    private final ModelPart face;
    private final ModelPart right_eyebrow;
    private final ModelPart left_eyebrow;
    private final ModelPart coverup;
    private final ModelPart angry_mouth;
    private final ModelPart teeth;
    private final ModelPart teeth1;
    private final ModelPart teeth2;
    private final ModelPart teeth3;
    private final ModelPart teeth4;
    private final ModelPart teeth5;
    private final ModelPart body;
    private final ModelPart left_arm;
    private final ModelPart wrist_left;
    private final ModelPart right_arm;
    private final ModelPart wrist_right;
    private final ModelPart cloth_0;
    private final ModelPart zeth;
    private final ModelPart cloth_1;
    private final ModelPart cloth_2;
    private final ModelPart back_cloth;
    private final ModelPart left_wing_1;
    private final ModelPart left_wing_2;
    private final ModelPart left_wing_3;
    private final ModelPart left_wing_4;
    private final ModelPart right_wing_1;
    private final ModelPart right_wing_2;
    private final ModelPart right_wing_3;
    private final ModelPart right_wing_4;


    private WeepingAngelPose weepingAngelPose = WeepingAngelPose.ANGRY;

    /**
     * Angel Type: 1
     */
    public ModelAngelEd(ModelPart root) {
        this.main = root.getChild("main");
        this.right_wing_0 = main.getChild("right_wing_0");
        this.left_wing_0 = main.getChild("left_wing_0");
        this.back_cloth_2 = main.getChild("back_cloth_2");
        this.head_2 = main.getChild("head_2");
        this.body_2 = main.getChild("body_2");
        this.head = main.getChild("head");
        this.body = main.getChild("body");
        this.left_arm = main.getChild("left_arm");
        this.right_arm = main.getChild("right_arm");
        this.cloth_0 = main.getChild("cloth_0");
        this.cloth_1 = main.getChild("cloth_1");
        this.cloth_2 = main.getChild("cloth_2");
        this.back_cloth = main.getChild("back_cloth");
        this.left_wing_1 = main.getChild("left_wing_1");
        this.right_wing_1 = main.getChild("right_wing_1");
        this.nose = head.getChild("nose");
        this.face = head.getChild("face");
        this.right_eyebrow = head.getChild("right_eyebrow");
        this.left_eyebrow = head.getChild("left_eyebrow");
        this.coverup = head.getChild("coverup");
        this.angry_mouth = head.getChild("angry_mouth");
        this.teeth = angry_mouth.getChild("teeth6");
        this.teeth1 = angry_mouth.getChild("teeth1");
        this.teeth2 = angry_mouth.getChild("teeth2");
        this.teeth3 = angry_mouth.getChild("teeth3");
        this.teeth4 = angry_mouth.getChild("teeth4");
        this.teeth5 = angry_mouth.getChild("teeth5");
        this.wrist_left = left_arm.getChild("left_arm_1");
        this.wrist_right = right_arm.getChild("left_arm_2");
        this.zeth = cloth_0.getChild("zeth");
        this.left_wing_2 = left_wing_1.getChild("left_wing_2");
        this.left_wing_3 = left_wing_2.getChild("left_wing_3");
        this.left_wing_4 = left_wing_3.getChild("left_wing_4");
        this.right_wing_2 = right_wing_1.getChild("right_wing_2");
        this.right_wing_3 = right_wing_2.getChild("right_wing_3");
        this.right_wing_4 = right_wing_3.getChild("right_wing_4");
    }

    public static LayerDefinition getModelData(){
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition right_wing_0 = main.addOrReplaceChild("right_wing_0", CubeListBuilder.create().texOffs(0, 49).addBox(-1.0F, 0.0F, -13.0F, 1.0F, 11.0F, 18.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.4F, 2.0F, 1.5F, 1.5359F, 0.9425F, 0.0F));

        PartDefinition left_wing_0 = main.addOrReplaceChild("left_wing_0", CubeListBuilder.create().texOffs(38, 50).addBox(0.0F, 0.0F, -13.0F, 1.0F, 11.0F, 18.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.4F, 2.0F, 1.5F, 1.5359F, -0.9425F, 0.0F));

        PartDefinition back_cloth_2 = main.addOrReplaceChild("back_cloth_2", CubeListBuilder.create().texOffs(0, 49).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 12.0F, 2.0F, 0.2793F, 0.0F, 0.0F));

        PartDefinition head_2 = main.addOrReplaceChild("head_2", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.4F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition body_2 = main.addOrReplaceChild("body_2", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 23.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition head = main.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(32, 0).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -4.5F, -4.0F, -0.2246F, 0.0F, 0.0F));

        PartDefinition face = head.addOrReplaceChild("face", CubeListBuilder.create().texOffs(54, 28).addBox(-3.0F, 0.0F, -0.01F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -4.5F, -4.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition right_eyebrow = head.addOrReplaceChild("right_eyebrow", CubeListBuilder.create().texOffs(54, 32).addBox(-2.5F, -1.0F, -0.02F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, -4.5F, -4.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition left_eyebrow = head.addOrReplaceChild("left_eyebrow", CubeListBuilder.create().texOffs(62, 32).addBox(-0.5F, -1.0F, -0.02F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, -4.5F, -4.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition coverup = head.addOrReplaceChild("coverup", CubeListBuilder.create().texOffs(54, 34).addBox(-4.0F, 0.0F, -0.03F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -6.0F, -4.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition angry_mouth = head.addOrReplaceChild("angry_mouth", CubeListBuilder.create().texOffs(63, 36).addBox(-2.0F, -1.8F, -0.02F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, -4.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition teeth1 = angry_mouth.addOrReplaceChild("teeth1", CubeListBuilder.create().texOffs(63, 39).addBox(0.0F, 0.0F, -0.03F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.8F, -3.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition teeth2 = angry_mouth.addOrReplaceChild("teeth2", CubeListBuilder.create().texOffs(63, 39).addBox(0.0F, 0.0F, -0.03F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.8F, -3.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition teeth3 = angry_mouth.addOrReplaceChild("teeth3", CubeListBuilder.create().texOffs(63, 39).addBox(0.0F, 0.0F, -0.03F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.5F, -3.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition teeth4 = angry_mouth.addOrReplaceChild("teeth4", CubeListBuilder.create().texOffs(63, 39).addBox(0.0F, 0.0F, -0.03F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5F, -3.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition teeth5 = angry_mouth.addOrReplaceChild("teeth5", CubeListBuilder.create().texOffs(63, 39).addBox(0.0F, 0.0F, -0.03F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.15F, -3.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition teeth6= angry_mouth.addOrReplaceChild("teeth6", CubeListBuilder.create().texOffs(63, 39).addBox(0.0F, 0.0F, -0.03F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.15F, -3.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition body = main.addOrReplaceChild("body", CubeListBuilder.create().texOffs(56, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 24.0F, 4.0F, new CubeDeformation(0.2F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition left_arm = main.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 27).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.0F, 1.5F, 0.0F, -1.85F, -0.6109F, -0.0873F));

        PartDefinition left_arm_1 = left_arm.addOrReplaceChild("left_arm_1", CubeListBuilder.create().texOffs(34, 52).addBox(-2.0F, 0.0F, -4.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 4.0F, 2.0F, -0.5236F, 0.0F, 0.0F));

        PartDefinition right_arm = main.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 32).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 1.5F, 0.0F, -1.85F, 0.6109F, 0.0873F));

        PartDefinition left_arm_2 = right_arm.addOrReplaceChild("left_arm_2", CubeListBuilder.create().texOffs(20, 52).addBox(-1.0F, 0.0F, -4.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 4.0F, 2.0F, -0.5236F, 0.0F, 0.0F));

        PartDefinition cloth_0 = main.addOrReplaceChild("cloth_0", CubeListBuilder.create().texOffs(9, 43).addBox(-5.0F, -1.0F, -2.0F, 10.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition zeth = cloth_0.addOrReplaceChild("zeth", CubeListBuilder.create().texOffs(20, 50).addBox(-4.5F, -1.0F, -0.6F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition cloth_1 = main.addOrReplaceChild("cloth_1", CubeListBuilder.create().texOffs(34, 44).addBox(-4.0F, -1.0F, -2.5F, 8.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 24.0F, 3.3F, 0.0F, 0.0F, 0.0F));

        PartDefinition cloth_2 = main.addOrReplaceChild("cloth_2", CubeListBuilder.create().texOffs(10, 32).addBox(-3.0F, -1.0F, -2.5F, 6.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 24.0F, 6.6F, 0.0F, 0.0F, 0.0F));

        PartDefinition back_cloth = main.addOrReplaceChild("back_cloth", CubeListBuilder.create().texOffs(60, 44).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 12.0F, 2.0F, 0.2793F, 0.0F, 0.0F));

        PartDefinition left_wing_1 = main.addOrReplaceChild("left_wing_1", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.4F, 2.0F, 1.5F, 1.5359F, -0.9425F, 0.0F));

        PartDefinition left_wing_2 = left_wing_1.addOrReplaceChild("left_wing_2", CubeListBuilder.create().texOffs(78, 42).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 4.0F, -1.0F, 1.2292F, 0.0F, 0.0F));

        PartDefinition left_wing_3 = left_wing_2.addOrReplaceChild("left_wing_3", CubeListBuilder.create().texOffs(14, 36).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 7.0F, 2.0F, -1.2292F, 0.0F, 0.0F));

        PartDefinition left_wing_4 = left_wing_3.addOrReplaceChild("left_wing_4", CubeListBuilder.create().texOffs(22, 36).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 5.0F, 0.0F, -1.1383F, 0.0F, 0.0F));

        PartDefinition right_wing_1 = main.addOrReplaceChild("right_wing_1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.4F, 2.0F, 1.5F, 1.5359F, 0.9425F, 0.0F));

        PartDefinition right_wing_2 = right_wing_1.addOrReplaceChild("right_wing_2", CubeListBuilder.create().texOffs(46, 27).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 4.0F, -1.0F, 1.2292F, 0.0F, 0.0F));

        PartDefinition right_wing_3 = right_wing_2.addOrReplaceChild("right_wing_3", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 7.0F, 2.0F, -1.2292F, 0.0F, 0.0F));

        PartDefinition right_wing_4 = right_wing_3.addOrReplaceChild("right_wing_4", CubeListBuilder.create().texOffs(24, 16).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 5.0F, 0.0F, -1.1383F, 0.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 88, 88);
    }

    public void setPose(WeepingAngelPose weepingAngelPose) {
        this.weepingAngelPose = weepingAngelPose;
    }

    public WeepingAngelPose getAngelPoses() {
        return weepingAngelPose;
    }

    @Override
    public void setupAnim(WeepingAngel weepingAngel, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        right_arm.y = 2.5F;
        left_arm.y = 2.5F;
        left_arm.xRot = 0;
        left_arm.yRot = 0;
        left_arm.zRot = 0;
        right_arm.xRot = 0;
        right_arm.yRot = 0;
        right_arm.zRot = 0;

        WeepingAngelPose pose = weepingAngelPose;
        if (weepingAngel != null) {
            pose = WeepingAngelPose.getPose(weepingAngel.getAngelPose());
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
                float swing = Mth.sin(attackTime * (float) Math.PI);
                float f1 = Mth.sin((1.0F - (1.0F - attackTime) * (1.0F - attackTime)) * (float) Math.PI);
                right_arm.zRot = 0.0F;
                left_arm.zRot = 0.0F;
                right_arm.yRot = -(0.1F - swing * 0.6F);
                left_arm.yRot = 0.1F - swing * 0.6F;
                right_arm.xRot = -1.5F;
                left_arm.xRot = -1.5F;
                right_arm.xRot += swing * 1.2F - f1 * 0.4F;
                left_arm.xRot += swing * 1.2F - f1 * 0.4F;
                right_arm.zRot += Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
                left_arm.zRot -= Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
                right_arm.xRot += Mth.sin(ageInTicks * 0.067F) * 0.05F;
                left_arm.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
                head.xRot = headPitch * 0.017453292F;
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
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
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
    public Iterable<ModelPart> parts() {
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
                teeth1,
                teeth2,
                teeth3,
                teeth4,
                teeth5,
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

    private void setRotateAngle(ModelPart modelRenderer, float x, float y, float z) {
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
        return null;
    }

    @Override
    public ResourceLocation getTextureForPose(Object angel, WeepingAngelPose pose) {
        return TEXTURE;
    }

}
