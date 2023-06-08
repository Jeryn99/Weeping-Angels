package mc.craig.software.angels.client.models.entity;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3d;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.client.poses.WeepingAngelPose;
import mc.craig.software.angels.common.entities.WeepingAngel;
import mc.craig.software.angels.common.variants.AbstractVariant;
import mc.craig.software.angels.utils.Pair;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.HumanoidArm;

/**
 * Angel Type: 1
 * Weeping Angel - EdusgprNetwork Created using Tabula 5.1.0
 */
public class ModelAngelEd extends ListModel<WeepingAngel> implements IAngelModel {

    private final ResourceLocation TEXTURE = new ResourceLocation(WeepingAngels.MODID,
            "textures/entities/angel_ed.png");

    private final ModelPart head;
    private final ModelPart right_eyebrow;
    private final ModelPart left_eyebrow;
    private final ModelPart angry_mouth;
    private final ModelPart teeth1;
    private final ModelPart teeth2;
    private final ModelPart teeth3;
    private final ModelPart teeth4;
    private final ModelPart teeth5;
    private final ModelPart teeth6;
    private final ModelPart Other;
    private final ModelPart nose;
    private final ModelPart face;
    private final ModelPart coverup;
    private final ModelPart Wings;
    private final ModelPart RWing;
    private final ModelPart right_wing_1;
    private final ModelPart right_wing_2;
    private final ModelPart right_wing_3;
    private final ModelPart right_wing_4;
    private final ModelPart right_wing_0;
    private final ModelPart LWing;
    private final ModelPart left_wing_1;
    private final ModelPart left_wing_2;
    private final ModelPart left_wing_3;
    private final ModelPart left_wing_4;
    private final ModelPart left_wing_0;
    private final ModelPart Body;
    private final ModelPart Main;
    private final ModelPart cloth_2;
    private final ModelPart cloth_0;
    private final ModelPart zeth;
    private final ModelPart cloth_1;
    private final ModelPart back_cloth;
    private final ModelPart body_2;
    private final ModelPart back_cloth_2;
    private final ModelPart right_arm;
    private final ModelPart right_arm_1;
    private final ModelPart left_arm;
    private final ModelPart left_arm_1;


    private WeepingAngelPose weepingAngelPose = WeepingAngelPose.ANGRY;
    private boolean showHurt = false;
    private Pair<ModelPart, Vector3d> headData;

    /**
     * Angel Type: 1
     */
    public ModelAngelEd(ModelPart root) {
        this.head = root.getChild("head");

        this.right_eyebrow = head.getChild("right_eyebrow");

        this.left_eyebrow = head.getChild("left_eyebrow");

        this.angry_mouth = head.getChild("angry_mouth");

        this.teeth1 = angry_mouth.getChild("teeth1");

        this.teeth2 = angry_mouth.getChild("teeth2");

        this.teeth3 = angry_mouth.getChild("teeth3");

        this.teeth4 = angry_mouth.getChild("teeth4");

        this.teeth5 = angry_mouth.getChild("teeth5");

        this.teeth6 = angry_mouth.getChild("teeth6");

        this.Other = head.getChild("Other");

        this.nose = Other.getChild("nose");

        this.face = Other.getChild("face");

        this.coverup = Other.getChild("coverup");

        this.Wings = root.getChild("Wings");

        this.RWing = Wings.getChild("RWing");

        this.right_wing_1 = RWing.getChild("right_wing_1");

        this.right_wing_2 = right_wing_1.getChild("right_wing_2");

        this.right_wing_3 = right_wing_2.getChild("right_wing_3");

        this.right_wing_4 = right_wing_3.getChild("right_wing_4");

        this.right_wing_0 = RWing.getChild("right_wing_0");

        this.LWing = Wings.getChild("LWing");

        this.left_wing_1 = LWing.getChild("left_wing_1");

        this.left_wing_2 = left_wing_1.getChild("left_wing_2");

        this.left_wing_3 = left_wing_2.getChild("left_wing_3");

        this.left_wing_4 = left_wing_3.getChild("left_wing_4");

        this.left_wing_0 = LWing.getChild("left_wing_0");

        this.Body = root.getChild("Body");

        this.Main = Body.getChild("Main");

        this.cloth_2 = Main.getChild("cloth_2");

        this.cloth_0 = Main.getChild("cloth_0");

        this.zeth = cloth_0.getChild("zeth");

        this.cloth_1 = Main.getChild("cloth_1");

        this.back_cloth = Main.getChild("back_cloth");

        this.body_2 = Main.getChild("body_2");

        this.back_cloth_2 = Main.getChild("back_cloth_2");

        this.right_arm = Body.getChild("right_arm");

        this.right_arm_1 = right_arm.getChild("right_arm_1");

        this.left_arm = Body.getChild("left_arm");

        this.left_arm_1 = left_arm.getChild("left_arm_1");
    }

    public static LayerDefinition getModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().mirror(false).texOffs(0, 16).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .mirror(false).texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.4F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition right_eyebrow = head.addOrReplaceChild("right_eyebrow", CubeListBuilder.create().mirror(false).texOffs(54, 32).addBox(-2.5F, -1.0F, -0.02F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -4.5F, -4.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition left_eyebrow = head.addOrReplaceChild("left_eyebrow", CubeListBuilder.create().mirror(false).texOffs(62, 32).addBox(-0.5F, -1.0F, -0.02F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -4.5F, -4.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition angry_mouth = head.addOrReplaceChild("angry_mouth", CubeListBuilder.create().mirror(false).texOffs(63, 36).addBox(-2.0F, -1.8F, -0.02F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -4.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition teeth1 = angry_mouth.addOrReplaceChild("teeth1", CubeListBuilder.create().mirror(false).texOffs(63, 39).addBox(-1.2021F, 0.2121F, -0.03F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.8F, -3.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition teeth2 = angry_mouth.addOrReplaceChild("teeth2", CubeListBuilder.create().mirror(false).texOffs(63, 39).addBox(-1.2021F, 0.2121F, -0.03F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.8F, -3.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition teeth3 = angry_mouth.addOrReplaceChild("teeth3", CubeListBuilder.create().mirror(false).texOffs(63, 39).addBox(-1.2728F, 0.2828F, -0.03F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -3.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition teeth4 = angry_mouth.addOrReplaceChild("teeth4", CubeListBuilder.create().mirror(false).texOffs(63, 39).addBox(-1.2728F, 0.2828F, -0.03F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -3.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition teeth5 = angry_mouth.addOrReplaceChild("teeth5", CubeListBuilder.create().mirror(false).texOffs(63, 39).addBox(-1.2021F, 0.2121F, -0.03F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.15F, -3.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition teeth6 = angry_mouth.addOrReplaceChild("teeth6", CubeListBuilder.create().mirror(false).texOffs(63, 39).addBox(-1.2021F, 0.2121F, -0.03F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.15F, -3.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition Other = head.addOrReplaceChild("Other", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition nose = Other.addOrReplaceChild("nose", CubeListBuilder.create().mirror(false).texOffs(32, 0).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -28.5F, -4.0F, -0.2246F, 0.0F, 0.0F));

        PartDefinition face = Other.addOrReplaceChild("face", CubeListBuilder.create().mirror(false).texOffs(54, 28).addBox(-3.0F, 0.0F, -0.01F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -28.5F, -4.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition coverup = Other.addOrReplaceChild("coverup", CubeListBuilder.create().mirror(false).texOffs(54, 34).addBox(-4.0F, 0.0F, -0.03F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -30.0F, -4.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition Wings = partdefinition.addOrReplaceChild("Wings", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition RWing = Wings.addOrReplaceChild("RWing", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition right_wing_1 = RWing.addOrReplaceChild("right_wing_1", CubeListBuilder.create().mirror(false).texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4F, -22.0F, 1.5F, 1.5359F, 0.9425F, 0.0F));
        PartDefinition right_wing_2 = right_wing_1.addOrReplaceChild("right_wing_2", CubeListBuilder.create().mirror(false).texOffs(46, 27).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, -1.0F, 1.2292F, 0.0F, 0.0F));
        PartDefinition right_wing_3 = right_wing_2.addOrReplaceChild("right_wing_3", CubeListBuilder.create().mirror(false).texOffs(0, 16).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.0F, 2.0F, -1.2292F, 0.0F, 0.0F));
        PartDefinition right_wing_4 = right_wing_3.addOrReplaceChild("right_wing_4", CubeListBuilder.create().mirror(false).texOffs(24, 16).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.0F, 0.0F, -1.1383F, 0.0F, 0.0F));
        PartDefinition right_wing_0 = RWing.addOrReplaceChild("right_wing_0", CubeListBuilder.create().mirror(false).texOffs(0, 49).addBox(-0.5F, 0.0F, -13.0F, 1.0F, 11.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4F, -22.0F, 1.5F, 1.5359F, 0.9425F, 0.0F));
        PartDefinition LWing = Wings.addOrReplaceChild("LWing", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition left_wing_1 = LWing.addOrReplaceChild("left_wing_1", CubeListBuilder.create().mirror(false).texOffs(24, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4F, -22.0F, 1.5F, 1.5359F, -0.9425F, 0.0F));
        PartDefinition left_wing_2 = left_wing_1.addOrReplaceChild("left_wing_2", CubeListBuilder.create().mirror(false).texOffs(78, 42).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, -1.0F, 1.2292F, 0.0F, 0.0F));
        PartDefinition left_wing_3 = left_wing_2.addOrReplaceChild("left_wing_3", CubeListBuilder.create().mirror(false).texOffs(14, 36).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.0F, 2.0F, -1.2292F, 0.0F, 0.0F));
        PartDefinition left_wing_4 = left_wing_3.addOrReplaceChild("left_wing_4", CubeListBuilder.create().mirror(false).texOffs(22, 36).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.0F, 0.0F, -1.1383F, 0.0F, 0.0F));
        PartDefinition left_wing_0 = LWing.addOrReplaceChild("left_wing_0", CubeListBuilder.create().mirror(false).texOffs(38, 50).addBox(-0.5F, 0.0F, -13.0F, 1.0F, 11.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4F, -22.0F, 1.5F, 1.5359F, -0.9425F, 0.0F));
        PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition Main = Body.addOrReplaceChild("Main", CubeListBuilder.create().mirror(false).texOffs(56, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 24.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -24.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition cloth_2 = Main.addOrReplaceChild("cloth_2", CubeListBuilder.create().mirror(false).texOffs(10, 32).addBox(-3.0F, -1.0F, -2.5F, 6.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, 6.6F, 0.0F, 0.0F, 0.0F));
        PartDefinition cloth_0 = Main.addOrReplaceChild("cloth_0", CubeListBuilder.create().mirror(false).texOffs(9, 43).addBox(-5.0F, -1.0F, -2.0F, 10.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition zeth = cloth_0.addOrReplaceChild("zeth", CubeListBuilder.create().mirror(false).texOffs(20, 50).addBox(-4.5F, -1.0F, -0.6F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition cloth_1 = Main.addOrReplaceChild("cloth_1", CubeListBuilder.create().mirror(false).texOffs(34, 44).addBox(-4.0F, -1.0F, -2.5F, 8.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, 3.3F, 0.0F, 0.0F, 0.0F));
        PartDefinition back_cloth = Main.addOrReplaceChild("back_cloth", CubeListBuilder.create().mirror(false).texOffs(60, 44).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.0F, 2.0F, 0.2793F, 0.0F, 0.0F));
        PartDefinition body_2 = Main.addOrReplaceChild("body_2", CubeListBuilder.create().mirror(false).texOffs(32, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 23.0F, 4.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition back_cloth_2 = Main.addOrReplaceChild("back_cloth_2", CubeListBuilder.create().mirror(false).texOffs(0, 49).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.0F, 2.0F, 0.2793F, 0.0F, 0.0F));
        PartDefinition right_arm = Body.addOrReplaceChild("right_arm", CubeListBuilder.create().mirror(false).texOffs(0, 32).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -22.5F, 0.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition right_arm_1 = right_arm.addOrReplaceChild("right_arm_1", CubeListBuilder.create().mirror(false).texOffs(20, 52).addBox(-1.0F, 0.0F, -4.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 2.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition left_arm = Body.addOrReplaceChild("left_arm", CubeListBuilder.create().mirror(false).texOffs(32, 27).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -22.5F, 0.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition left_arm_1 = left_arm.addOrReplaceChild("left_arm_1", CubeListBuilder.create().mirror(false).texOffs(34, 52).addBox(-2.0F, 0.0F, -4.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 2.0F, 0.0F, 0.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 88, 88);
    }

    public void setPose(WeepingAngelPose weepingAngelPose) {
        this.weepingAngelPose = weepingAngelPose;
    }

    public WeepingAngelPose getAngelPoses() {
        return weepingAngelPose;
    }

    @Override
    public void setupAnim(WeepingAngel weepingAngel, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch) {

        WeepingAngelPose pose = weepingAngelPose;
        if (weepingAngel != null) {
            pose = WeepingAngelPose.getPose(weepingAngel.getAngelPose());
        }

        if (pose != null) {

            angry_mouth.visible = pose.getEmotion() == WeepingAngelPose.Emotion.ANGRY || pose.getEmotion() == WeepingAngelPose.Emotion.SCREAM;

            head.xRot = (float) Math.toRadians(0);
            head.yRot = (float) Math.toRadians(0);
            head.zRot = (float) Math.toRadians(0);

            if (pose == WeepingAngelPose.FURIOUS) {
                right_arm.xRot = (float) Math.toRadians(-115);
                right_arm.yRot = (float) Math.toRadians(0);
                right_arm.zRot = (float) Math.toRadians(0);

                left_arm.xRot = (float) Math.toRadians(-55);
                left_arm.yRot = (float) Math.toRadians(0);
                left_arm.zRot = (float) Math.toRadians(0);

                head.xRot = (float) Math.toRadians(17.5);
                head.yRot = (float) Math.toRadians(0);
                head.zRot = (float) Math.toRadians(-10);
                return;
            }


            if (pose == WeepingAngelPose.ANGRY) {
                right_arm.xRot = (float) Math.toRadians(-90);
                right_arm.yRot = (float) Math.toRadians(25);
                right_arm.zRot = (float) Math.toRadians(17.5);

                left_arm.xRot = (float) Math.toRadians(-90);
                left_arm.yRot = (float) Math.toRadians(-25);
                left_arm.zRot = (float) Math.toRadians(-17.5);

                head.xRot = (float) Math.toRadians(10);
                head.yRot = (float) Math.toRadians(0);
                head.zRot = (float) Math.toRadians(0);
                return;
            }


            if (pose == WeepingAngelPose.HIDING) {
                head.xRot = (float) Math.toRadians(20);
                head.yRot = (float) Math.toRadians(0);
                head.zRot = (float) Math.toRadians(0);

                right_arm.xRot = (float) Math.toRadians(-105);
                right_arm.yRot = (float) Math.toRadians(20);
                right_arm.zRot = (float) Math.toRadians(12.5);

                left_arm.xRot = (float) Math.toRadians(-105);
                left_arm.yRot = (float) Math.toRadians(-20);
                left_arm.zRot = (float) Math.toRadians(-12.5);
                return;
            }

            if (pose == WeepingAngelPose.APPROACH) {
                right_arm.xRot = -1.04533F;
                right_arm.yRot = -0.55851F;
                right_arm.zRot = 0.0F;
                left_arm.xRot = -1.04533F;
                left_arm.yRot = 0.55851F;
                left_arm.zRot = 0.0F;
                return;
            }

            if (pose == WeepingAngelPose.IDLE) {
                head.xRot = (float) Math.toRadians(0);
                head.yRot = (float) Math.toRadians(0);
                head.zRot = (float) Math.toRadians(0);

                right_arm.xRot = (float) Math.toRadians(0);
                right_arm.yRot = (float) Math.toRadians(0);
                right_arm.zRot = (float) Math.toRadians(-7.5);

                left_arm.xRot = (float) Math.toRadians(0);
                left_arm.yRot = (float) Math.toRadians(0);
                left_arm.zRot = (float) Math.toRadians(7.5);
                return;
            }

            if (pose == WeepingAngelPose.SHY) {
                right_arm.xRot = (float) Math.toRadians(-90);
                right_arm.yRot = (float) Math.toRadians(-1.5);
                right_arm.zRot = (float) Math.toRadians(-20);

                left_arm.xRot = (float) Math.toRadians(-120);
                left_arm.yRot = (float) Math.toRadians(-36);
                left_arm.zRot = (float) Math.toRadians(10);

                head.xRot = (float) Math.toRadians(20);
                head.yRot = (float) Math.toRadians(-40);
                head.zRot = (float) Math.toRadians(-20);
                return;
            }
        }
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(matrixStack, buffer, packedLight, showHurt ? packedOverlay : OverlayTexture.NO_OVERLAY);
        Wings.render(matrixStack, buffer, packedLight, showHurt ? packedOverlay : OverlayTexture.NO_OVERLAY);
        Body.render(matrixStack, buffer, packedLight, showHurt ? packedOverlay : OverlayTexture.NO_OVERLAY);
    }

    @Override
    public Iterable<ModelPart> parts() {
        return ImmutableList.of(right_wing_0,
                left_wing_0,
                back_cloth_2,
                Body,
                body_2,
                head,
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
                teeth6,
                teeth1,
                teeth2,
                teeth3,
                teeth4,
                teeth5,
                left_arm_1,
                right_arm_1,
                zeth,
                left_wing_2,
                left_wing_3,
                left_wing_4,
                right_wing_2,
                right_wing_3,
                right_wing_4);
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
    public Pair<ModelPart, Vector3d> getHeadData(HeadPlacement placement) {
        if (headData == null) {
            headData = new Pair<>(head, new Vector3d(0, 0, 0));
        }
        return headData;
    }

    @Override
    public Iterable<ModelPart> wings(PoseStack pose) {
        pose.translate(0, 1.5, 0);
        return ImmutableList.of(left_wing_0, left_wing_1, right_wing_0, right_wing_1);
    }

    @Override
    public boolean toggleHurt(boolean hurtShow) {
        return showHurt;
    }

    @Override
    public ResourceLocation generateTex(WeepingAngelPose pose, AbstractVariant angelVariants) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getTextureForPose(Object angel, WeepingAngelPose pose) {
        return TEXTURE;
    }

    protected ModelPart getArm(HumanoidArm handSide) {
        return handSide == HumanoidArm.LEFT ? this.left_arm : this.right_arm;
    }

}
