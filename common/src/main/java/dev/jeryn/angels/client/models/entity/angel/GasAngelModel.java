package dev.jeryn.angels.client.models.entity.angel;

import com.google.common.collect.ImmutableList;
import dev.jeryn.angels.WeepingAngels;
import dev.jeryn.angels.client.screen.ChiselScreen;
import dev.jeryn.angels.common.blockentity.StatueBlockEntity;
import dev.jeryn.angels.common.entity.angel.WeepingAngel;
import dev.jeryn.angels.common.entity.angel.ai.AngelEmotion;
import dev.jeryn.angels.common.entity.angel.ai.AngelVariant;
import net.minecraft.client.Minecraft;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class GasAngelModel extends AngelModel {


    public static final AnimationDefinition ANIMATION_STREAM = AnimationDefinition.Builder.withLength(24f).looping().addAnimation("Body", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(2f, KeyframeAnimations.degreeVec(17.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(4f, KeyframeAnimations.degreeVec(20f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(6f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(8f, KeyframeAnimations.degreeVec(-7.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(10f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(12f, KeyframeAnimations.degreeVec(22.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(14f, KeyframeAnimations.degreeVec(22.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(16f, KeyframeAnimations.degreeVec(5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(18f, KeyframeAnimations.degreeVec(22.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(20f, KeyframeAnimations.degreeVec(22.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(22f, KeyframeAnimations.degreeVec(0f, -7.5f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(24f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, -7.5f), AnimationChannel.Interpolations.LINEAR), new Keyframe(2f, KeyframeAnimations.degreeVec(-162.5f, 0f, -42.5f), AnimationChannel.Interpolations.LINEAR), new Keyframe(4f, KeyframeAnimations.degreeVec(-20f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(6f, KeyframeAnimations.degreeVec(-80f, -25f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(8f, KeyframeAnimations.degreeVec(-102.5f, -10f, 2.5f), AnimationChannel.Interpolations.LINEAR), new Keyframe(10f, KeyframeAnimations.degreeVec(-112.5f, -25f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(12f, KeyframeAnimations.degreeVec(-87.5f, -32.5f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(14f, KeyframeAnimations.degreeVec(-87.5f, 85f, 5f), AnimationChannel.Interpolations.LINEAR), new Keyframe(16f, KeyframeAnimations.degreeVec(-100f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(18f, KeyframeAnimations.degreeVec(-122.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(20f, KeyframeAnimations.degreeVec(-107.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(22f, KeyframeAnimations.degreeVec(-90f, 7.5f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(24f, KeyframeAnimations.degreeVec(-90f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-38.74486554355917f, -2.510328914337606f, 16.74647054064235f), AnimationChannel.Interpolations.LINEAR), new Keyframe(2f, KeyframeAnimations.degreeVec(15f, 0f, -20f), AnimationChannel.Interpolations.LINEAR), new Keyframe(4f, KeyframeAnimations.degreeVec(-25f, 0f, -7.5f), AnimationChannel.Interpolations.LINEAR), new Keyframe(6f, KeyframeAnimations.degreeVec(-82.5f, -47.5f, -27.5f), AnimationChannel.Interpolations.LINEAR), new Keyframe(8f, KeyframeAnimations.degreeVec(-92.5f, 40f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(10f, KeyframeAnimations.degreeVec(-112.5f, 25f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(12f, KeyframeAnimations.degreeVec(-92.5f, 17.5f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(14f, KeyframeAnimations.degreeVec(-80f, 17.5f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(16f, KeyframeAnimations.degreeVec(-122.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(18f, KeyframeAnimations.degreeVec(-112.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(20f, KeyframeAnimations.degreeVec(-87.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(22f, KeyframeAnimations.degreeVec(12.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(24f, KeyframeAnimations.degreeVec(-90f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-17.5f, -25f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(2f, KeyframeAnimations.degreeVec(-27.5f, 37.5f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(4f, KeyframeAnimations.degreeVec(45f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(6f, KeyframeAnimations.degreeVec(-2.5f, -30f, -17.5f), AnimationChannel.Interpolations.LINEAR), new Keyframe(8f, KeyframeAnimations.degreeVec(37.5f, 32.5f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(10f, KeyframeAnimations.degreeVec(17.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(12f, KeyframeAnimations.degreeVec(47.5f, -12.5f, -7.5f), AnimationChannel.Interpolations.LINEAR), new Keyframe(14f, KeyframeAnimations.degreeVec(47.5f, 27.5f, 22.5f), AnimationChannel.Interpolations.LINEAR), new Keyframe(16f, KeyframeAnimations.degreeVec(-5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(18f, KeyframeAnimations.degreeVec(-22.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(20f, KeyframeAnimations.degreeVec(-17.5f, 12.5f, 20f), AnimationChannel.Interpolations.LINEAR), new Keyframe(22f, KeyframeAnimations.degreeVec(0f, 10f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(24f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("leftWing", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(4f, KeyframeAnimations.posVec(0f, 0f, -1f), AnimationChannel.Interpolations.LINEAR), new Keyframe(6f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("leftWing", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, -17.5f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(2f, KeyframeAnimations.degreeVec(12.5f, 25f, -15f), AnimationChannel.Interpolations.LINEAR), new Keyframe(4f, KeyframeAnimations.degreeVec(-25f, -2.5f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(6f, KeyframeAnimations.degreeVec(2.5f, -27.5f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(8f, KeyframeAnimations.degreeVec(32.5f, -2.5f, -37.5f), AnimationChannel.Interpolations.LINEAR), new Keyframe(10f, KeyframeAnimations.degreeVec(0f, -7.5f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(12f, KeyframeAnimations.degreeVec(20f, 0f, -10f), AnimationChannel.Interpolations.LINEAR), new Keyframe(14f, KeyframeAnimations.degreeVec(20f, 0f, -10f), AnimationChannel.Interpolations.LINEAR), new Keyframe(16f, KeyframeAnimations.degreeVec(0f, -20f, 7.5f), AnimationChannel.Interpolations.LINEAR), new Keyframe(18f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(20f, KeyframeAnimations.degreeVec(0f, 32.5f, -25f), AnimationChannel.Interpolations.LINEAR), new Keyframe(22f, KeyframeAnimations.degreeVec(0f, -12.5f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(24f, KeyframeAnimations.degreeVec(0f, -27.5f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("rightWing", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(4f, KeyframeAnimations.posVec(0f, 0f, -1f), AnimationChannel.Interpolations.LINEAR), new Keyframe(6f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("rightWing", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, -15f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(2f, KeyframeAnimations.degreeVec(-12.5f, 20f, -5f), AnimationChannel.Interpolations.LINEAR), new Keyframe(4f, KeyframeAnimations.degreeVec(-25f, 2.5f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(6f, KeyframeAnimations.degreeVec(0f, -27.5f, 12.5f), AnimationChannel.Interpolations.LINEAR), new Keyframe(8f, KeyframeAnimations.degreeVec(50f, 15f, 45f), AnimationChannel.Interpolations.LINEAR), new Keyframe(10f, KeyframeAnimations.degreeVec(0f, 7.5f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(12f, KeyframeAnimations.degreeVec(47.5f, 25f, 50f), AnimationChannel.Interpolations.LINEAR), new Keyframe(14f, KeyframeAnimations.degreeVec(47.5f, 25f, 50f), AnimationChannel.Interpolations.LINEAR), new Keyframe(16f, KeyframeAnimations.degreeVec(0f, 20f, -7.5f), AnimationChannel.Interpolations.LINEAR), new Keyframe(18f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(20f, KeyframeAnimations.degreeVec(0f, 0f, 10f), AnimationChannel.Interpolations.LINEAR), new Keyframe(22f, KeyframeAnimations.degreeVec(0f, -17.5f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(24f, KeyframeAnimations.degreeVec(0f, 27.5f, 0f), AnimationChannel.Interpolations.LINEAR))).build();
    private final ModelPart body;
    private final ModelPart Legs;
    private final ModelPart root;
    private final ModelPart leftWing;
    private final ModelPart rightWing;

    public GasAngelModel(ModelPart root) {
        this.root = root;
        this.body = root.getChild("Body");
        this.Legs = root.getChild("Legs");
        this.leftWing = body.getChild("leftWing");
        this.rightWing = body.getChild("rightWing");
    }

    public static LayerDefinition meshLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(26, 18).addBox(-4.0F, -10.0F, -2.0F, 8.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.0F, 0.0F));

        PartDefinition headband_r1 = head.addOrReplaceChild("headband_r1", CubeListBuilder.create().texOffs(28, 53).addBox(-4.5F, 0.0F, -4.75F, 9.0F, 1.0F, 9.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -6.5F, 0.25F, -0.1745F, 0.0F, 0.0F));

        PartDefinition leftArm = body.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(0, 46).addBox(-1.0F, -1.5F, -1.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(35, 45).addBox(-1.0F, 5.5F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -8.5F, 0.0F));

        PartDefinition rightArm = body.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(47, 43).addBox(-2.0F, -1.5F, -1.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(19, 46).addBox(-2.0F, 5.5F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -8.5F, 0.0F));

        PartDefinition leftWing = body.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(28, 32).mirror().addBox(-0.5F, -2.0F, 1.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 0).mirror().addBox(-0.5F, 8.0F, 6.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 5).mirror().addBox(-0.5F, 11.0F, 7.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(50, 2).mirror().addBox(-0.5F, 5.0F, 4.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(12, 33).mirror().addBox(-0.5F, 3.0F, 4.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(43, 25).mirror().addBox(-0.5F, 1.0F, 2.0F, 1.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 34).mirror().addBox(-0.5F, -1.0F, -1.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(48, 34).mirror().addBox(-0.5F, -3.0F, 3.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(50, 18).mirror().addBox(-0.5F, -4.0F, 4.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.5F, -7.5F, 2.0F));

        PartDefinition rightWing = body.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(28, 32).addBox(-0.5F, -2.0F, 1.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(21, 16).addBox(-0.5F, 8.0F, 6.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 16).addBox(-0.5F, 11.0F, 7.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 2).addBox(-0.5F, 5.0F, 4.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(12, 33).addBox(-0.5F, 3.0F, 4.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(43, 25).addBox(-0.5F, 1.0F, 2.0F, 1.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 34).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(48, 34).addBox(-0.5F, -3.0F, 3.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(50, 18).addBox(-0.5F, -4.0F, 4.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, -7.5F, 2.0F));

        PartDefinition Legs = partdefinition.addOrReplaceChild("Legs", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -14.0F, -2.5F, 8.0F, 12.0F, 5.0F, new CubeDeformation(0.3F))
                .texOffs(25, 9).addBox(-5.0F, -2.0F, -3.5F, 10.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 56).addBox(-3.5F, -8.0F, -1.5F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(16, 55).addBox(0.5F, -8.0F, -1.5F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition SKIRT_r1 = Legs.addOrReplaceChild("SKIRT_r1", CubeListBuilder.create().texOffs(38, 63).addBox(-5.25F, -2.0F, -3.5F, 10.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.0F, 0.0F, 0.1309F));

        return LayerDefinition.create(meshdefinition, 72, 72);
    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setupAnim(WeepingAngel weepingAngel, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        if (weepingAngel.getFakeAnimation() != -1) {
            animate(ChiselScreen.POSE_ANIMATION_STATE, getAnimationDefinition(weepingAngel.getFakeAnimation()), Minecraft.getInstance().player.tickCount, 1000);
            return;
        }

        int playbackSpeed = Mth.clamp(weepingAngel.level().random.nextInt(7), 2, 7);
        if (weepingAngel.isHooked() || weepingAngel.getSeenTime() > 0 || weepingAngel.tickCount < 200) {
            playbackSpeed = 0;
        }
        animate(weepingAngel.POSE_ANIMATION_STATE, ANIMATION_STREAM, weepingAngel.tickCount, playbackSpeed);
    }

    @Override
    public Iterable<ModelPart> getWings() {
        return ImmutableList.of(rightWing, leftWing);
    }

    @Override
    public ModelPart getHead() {
        return body.getChild("head");
    }


    @Override
    public ResourceLocation texture(AngelEmotion angelEmotion, AngelVariant angelVariant) {
        return new ResourceLocation(WeepingAngels.MODID, "textures/entity/angel/spare_time/variants/" + angelVariant.location().getPath() + "/" + angelVariant.location().getPath() + "_angel_" + angelEmotion.getId() + ".png");
    }

    @Override
    public void animateTile(StatueBlockEntity statueBlockEntity) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        animate(statueBlockEntity.getAnimationState(), poseForId(statueBlockEntity.getAnimation()), Minecraft.getInstance().player.tickCount);
    }

    @Override
    public AnimationDefinition poseForId(int index) {
        return getAnimationDefinition(index);
    }
}
