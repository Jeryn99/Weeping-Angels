package mc.craig.software.angels.client.models.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class TardisModel extends HierarchicalModel {


    public static final AnimationDefinition MODEL_TAKEOFF = AnimationDefinition.Builder.withLength(12f)
            .addAnimation("fade_value",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 9.5f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1f, KeyframeAnimations.posVec(0f, 9.5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(2.125f, KeyframeAnimations.posVec(0f, 5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(3f, KeyframeAnimations.posVec(0f, 10f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(4f, KeyframeAnimations.posVec(0f, 3f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(5f, KeyframeAnimations.posVec(0f, 6f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(5.791677f, KeyframeAnimations.posVec(0f, 3f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(6.916767f, KeyframeAnimations.posVec(0f, 5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(8f, KeyframeAnimations.posVec(0f, 2f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(9.167666f, KeyframeAnimations.posVec(0f, 4f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(12f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM))).build();
    ModelPart fade_value;
    public float initAlpha = 0;
    float ANIMATION_SPEED = 1.1f;
    private float currentAlpha;

    public ModelPart fadeValue() {
        return fade_value;
    }

    public float getCurrentAlpha() {
        return currentAlpha;
    }

    private final ModelPart Posts;
    private final ModelPart Panels;
    private final ModelPart PPCB;
    private final ModelPart Roof;
    private final ModelPart Lamp;
    private final ModelPart RDoor;
    private final ModelPart LDoor;
    private final ModelPart bb_main;
    private final ModelPart root;

    public static void addMaterializationPart(PartDefinition partDefinition) {
        partDefinition.addOrReplaceChild("fade_value", CubeListBuilder.create().texOffs(128, 128), PartPose.offset(-24.0F, 24.0F, 0.0F));
    }

    public TardisModel(ModelPart root) {
        this.root = root;
        this.fade_value = root.getChild("fade_value");
        this.initAlpha = this.fade_value.y;
        this.Posts = root.getChild("Posts");
        this.Panels = root.getChild("Panels");
        this.PPCB = root.getChild("PPCB");
        this.Roof = root.getChild("Roof");
        this.Lamp = root.getChild("Lamp");
        this.RDoor = root.getChild("RDoor");
        this.LDoor = root.getChild("LDoor");
        this.bb_main = root.getChild("bb_main");
    }

    public static LayerDefinition meshLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        addMaterializationPart(partdefinition);
        PartDefinition Posts = partdefinition.addOrReplaceChild("Posts", CubeListBuilder.create().texOffs(28, 102).addBox(-3.0F, -57.0F, -2.0F, 3.0F, 57.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-14.0F, 21.0F, -15.0F));

        PartDefinition cube_r1 = Posts.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(28, 102).addBox(-17.0F, -60.0F, -17.0F, 3.0F, 57.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.0F, 3.0F, 15.0F, -3.1416F, 0.0F, 3.1416F));

        PartDefinition cube_r2 = Posts.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(28, 102).addBox(-17.0F, -60.0F, -17.0F, 3.0F, 57.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.0F, 3.0F, 15.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r3 = Posts.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(28, 102).addBox(-17.0F, -60.0F, -17.0F, 3.0F, 57.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.0F, 3.0F, 15.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition Panels = partdefinition.addOrReplaceChild("Panels", CubeListBuilder.create().texOffs(84, 74).addBox(-15.0F, -42.0F, -14.0F, 1.0F, 51.0F, 28.0F, new CubeDeformation(0.0F))
                .texOffs(40, 102).addBox(-14.0F, -42.0F, -15.0F, 1.0F, 51.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 102).addBox(13.0F, -42.0F, -15.0F, 1.0F, 51.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(84, 72).addBox(-13.0F, -42.0F, -15.0F, 26.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.0F, 0.0F));

        PartDefinition cube_r4 = Panels.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(84, 74).addBox(-15.0F, -54.0F, -14.0F, 1.0F, 51.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.0F, 0.0F, -3.1416F, 0.0F, 3.1416F));

        PartDefinition cube_r5 = Panels.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(84, 74).addBox(-15.0F, -54.0F, -14.0F, 1.0F, 51.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition PPCB = partdefinition.addOrReplaceChild("PPCB", CubeListBuilder.create().texOffs(90, 39).addBox(-15.0F, -59.0F, -18.0F, 30.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition cube_r6 = PPCB.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(90, 39).addBox(-15.0F, -59.0F, -18.0F, 30.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r7 = PPCB.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(90, 39).addBox(-15.0F, -59.0F, -18.0F, 30.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -3.1416F, 0.0F, 3.1416F));

        PartDefinition cube_r8 = PPCB.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(90, 39).addBox(-15.0F, -59.0F, -18.0F, 30.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition Roof = partdefinition.addOrReplaceChild("Roof", CubeListBuilder.create().texOffs(0, 39).addBox(-15.0F, -63.0F, -15.0F, 30.0F, 3.0F, 30.0F, new CubeDeformation(0.0F))
                .texOffs(0, 72).addBox(-14.0F, -65.0F, -14.0F, 28.0F, 2.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 25.0F, 0.0F));

        PartDefinition Lamp = partdefinition.addOrReplaceChild("Lamp", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 15).addBox(-2.5F, -7.0F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 8).addBox(-3.0F, -8.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(20, 11).addBox(-2.0F, -9.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -40.0F, 0.0F));

        PartDefinition RDoor = partdefinition.addOrReplaceChild("RDoor", CubeListBuilder.create().texOffs(48, 102).addBox(-13.0F, -50.0F, -1.0F, 13.0F, 50.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-12.0F, -33.0F, -2.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(24, 0).addBox(-13.0F, -27.0F, 0.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(13.0F, 21.0F, -14.0F));

        PartDefinition LDoor = partdefinition.addOrReplaceChild("LDoor", CubeListBuilder.create().texOffs(0, 102).addBox(0.0F, -50.0F, -1.0F, 13.0F, 50.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 51).addBox(3.0F, -34.0F, 0.0F, 6.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 40).addBox(3.0F, -36.0F, 1.0F, 7.0F, 8.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 8).addBox(10.0F, -32.0F, -2.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(28, 5).addBox(12.0F, -27.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-13.0F, 21.0F, -14.0F));

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 0).addBox(-18.0F, -3.0F, -18.0F, 36.0F, 3.0F, 36.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }


    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer buffer, int packedLight, int packedOverlay, int k) {
        Posts.render(poseStack, buffer, packedLight, packedOverlay, k);
        Panels.render(poseStack, buffer, packedLight, packedOverlay, k);
        PPCB.render(poseStack, buffer, packedLight, packedOverlay, k);
        Roof.render(poseStack, buffer, packedLight, packedOverlay, k);
        Lamp.render(poseStack, buffer, packedLight, packedOverlay, k);
        RDoor.render(poseStack, buffer, packedLight, packedOverlay, k);
        LDoor.render(poseStack, buffer, packedLight, packedOverlay, k);
        bb_main.render(poseStack, buffer, packedLight, packedOverlay, k);
    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    public void animate(AnimationState animationState, int timer) {
        this.animate(animationState, MODEL_TAKEOFF, (timer * 2) * ANIMATION_SPEED);
    }
}