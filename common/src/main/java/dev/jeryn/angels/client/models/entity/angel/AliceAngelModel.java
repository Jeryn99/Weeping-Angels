package dev.jeryn.angels.client.models.entity.angel;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
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
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;

import java.util.Locale;

public class AliceAngelModel extends AngelModel implements ArmedModel {


    public static final AnimationDefinition IDLE1 = AnimationDefinition.Builder.withLength(0f).addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 15f), AnimationChannel.Interpolations.LINEAR))).addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-22.5f, -32.5f, -32.5f), AnimationChannel.Interpolations.LINEAR))).addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-17.5f, -25f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("leftWing", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, -17.5f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("rightWing", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, -15f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("Body", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).build();
    public static final AnimationDefinition IDLE2 = AnimationDefinition.Builder.withLength(0f).addAnimation("Body", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(17.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-162.5f, 0f, -42.5f), AnimationChannel.Interpolations.LINEAR))).addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(15f, 0f, -20f), AnimationChannel.Interpolations.LINEAR))).addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-27.5f, 37.5f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("leftWing", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(12.5f, 25f, -15f), AnimationChannel.Interpolations.LINEAR))).addAnimation("rightWing", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-12.5f, 20f, -5f), AnimationChannel.Interpolations.LINEAR))).build();
    public static final AnimationDefinition IDLE3 = AnimationDefinition.Builder.withLength(0f).addAnimation("Body", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(20f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-20f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-25f, 0f, -7.5f), AnimationChannel.Interpolations.LINEAR))).addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(45f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("leftWing", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, -1f), AnimationChannel.Interpolations.LINEAR))).addAnimation("leftWing", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-25f, -2.5f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("rightWing", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, -1f), AnimationChannel.Interpolations.LINEAR))).addAnimation("rightWing", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-25f, 2.5f, 0f), AnimationChannel.Interpolations.LINEAR))).build();
    public static final AnimationDefinition IDLE4 = AnimationDefinition.Builder.withLength(0f).addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-80f, -25f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-82.5f, -47.5f, -27.5f), AnimationChannel.Interpolations.LINEAR))).addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-2.5f, -30f, -17.5f), AnimationChannel.Interpolations.LINEAR))).addAnimation("leftWing", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(2.5f, -27.5f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("rightWing", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, -27.5f, 12.5f), AnimationChannel.Interpolations.LINEAR))).build();
    public static final AnimationDefinition IDLE5 = AnimationDefinition.Builder.withLength(0f).addAnimation("Body", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-7.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-102.5f, -10f, 2.5f), AnimationChannel.Interpolations.LINEAR))).addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-92.5f, 40f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(37.5f, 32.5f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("leftWing", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(32.5f, -2.5f, -37.5f), AnimationChannel.Interpolations.LINEAR))).addAnimation("rightWing", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(50f, 15f, 45f), AnimationChannel.Interpolations.LINEAR))).build();
    public static final AnimationDefinition IDLE6 = AnimationDefinition.Builder.withLength(0f).addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-112.5f, -25f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-112.5f, 25f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(17.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("leftWing", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, -7.5f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("rightWing", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 7.5f, 0f), AnimationChannel.Interpolations.LINEAR))).build();
    public static final AnimationDefinition IDLE7 = AnimationDefinition.Builder.withLength(0f).addAnimation("Body", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(22.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-87.5f, -32.5f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-92.5f, 17.5f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(47.5f, -12.5f, -7.5f), AnimationChannel.Interpolations.LINEAR))).addAnimation("leftWing", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(20f, 0f, -10f), AnimationChannel.Interpolations.LINEAR))).addAnimation("rightWing", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(47.5f, 25f, 50f), AnimationChannel.Interpolations.LINEAR))).build();
    public static final AnimationDefinition ANGRY1 = AnimationDefinition.Builder.withLength(0f).addAnimation("Body", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(22.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-87.5f, 85f, 5f), AnimationChannel.Interpolations.LINEAR))).addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-80f, 17.5f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(47.5f, 27.5f, 22.5f), AnimationChannel.Interpolations.LINEAR))).addAnimation("leftWing", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(20f, 0f, -10f), AnimationChannel.Interpolations.LINEAR))).addAnimation("rightWing", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(47.5f, 25f, 50f), AnimationChannel.Interpolations.LINEAR))).build();
    public static final AnimationDefinition ANGRY2 = AnimationDefinition.Builder.withLength(0f).addAnimation("Body", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-100f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-122.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("leftWing", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, -20f, 7.5f), AnimationChannel.Interpolations.LINEAR))).addAnimation("rightWing", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 20f, -7.5f), AnimationChannel.Interpolations.LINEAR))).build();
    public static final AnimationDefinition ANGRY3 = AnimationDefinition.Builder.withLength(0f).addAnimation("Body", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(22.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-122.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-112.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-22.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("leftWing", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("rightWing", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).build();
    public static final AnimationDefinition ANGRY4 = AnimationDefinition.Builder.withLength(0f).addAnimation("Body", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(22.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-107.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-87.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-17.5f, 12.5f, 20f), AnimationChannel.Interpolations.LINEAR))).addAnimation("leftWing", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 32.5f, -25f), AnimationChannel.Interpolations.LINEAR))).addAnimation("rightWing", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 10f), AnimationChannel.Interpolations.LINEAR))).build();
    public static final AnimationDefinition ANGRY5 = AnimationDefinition.Builder.withLength(0f).addAnimation("Body", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, -7.5f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-90f, 7.5f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(12.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 10f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("leftWing", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, -12.5f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("rightWing", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, -17.5f, 0f), AnimationChannel.Interpolations.LINEAR))).build();
    public static final AnimationDefinition ANGRY6 = AnimationDefinition.Builder.withLength(24f).addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-90f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-90f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("leftWing", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, -27.5f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("rightWing", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 27.5f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("Body", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).build();
    public static final AnimationDefinition ANIMATION_STREAM = AnimationDefinition.Builder.withLength(26.125f).looping().addAnimation("Body", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(2f, KeyframeAnimations.degreeVec(17.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(4f, KeyframeAnimations.degreeVec(20f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(6f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(8f, KeyframeAnimations.degreeVec(-7.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(10f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(12f, KeyframeAnimations.degreeVec(22.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(14f, KeyframeAnimations.degreeVec(22.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(16f, KeyframeAnimations.degreeVec(5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(18f, KeyframeAnimations.degreeVec(22.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(20f, KeyframeAnimations.degreeVec(22.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(22.00733333333292f, KeyframeAnimations.degreeVec(0f, -7.5f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(24f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(25.916666666666668f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 15f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(2f, KeyframeAnimations.degreeVec(-162.5f, 0f, -42.5f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(4f, KeyframeAnimations.degreeVec(-20f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(6f, KeyframeAnimations.degreeVec(-80f, -25f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(8f, KeyframeAnimations.degreeVec(-102.5f, -10f, 2.5f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(10f, KeyframeAnimations.degreeVec(-112.5f, -25f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(12f, KeyframeAnimations.degreeVec(-87.5f, -32.5f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(14f, KeyframeAnimations.degreeVec(-87.5f, 85f, 5f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(16f, KeyframeAnimations.degreeVec(-100f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(18f, KeyframeAnimations.degreeVec(-122.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(20f, KeyframeAnimations.degreeVec(-107.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(22.00733333333292f, KeyframeAnimations.degreeVec(-90f, 7.5f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(24f, KeyframeAnimations.degreeVec(-90f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(25.916666666666668f, KeyframeAnimations.degreeVec(0f, 0f, 15f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-22.5f, -32.5f, -32.5f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(2f, KeyframeAnimations.degreeVec(15f, 0f, -20f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(4f, KeyframeAnimations.degreeVec(-25f, 0f, -7.5f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(6f, KeyframeAnimations.degreeVec(-82.5f, -47.5f, -27.5f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(8f, KeyframeAnimations.degreeVec(-92.5f, 40f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(10f, KeyframeAnimations.degreeVec(-112.5f, 25f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(12f, KeyframeAnimations.degreeVec(-92.5f, 17.5f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(14f, KeyframeAnimations.degreeVec(-80f, 17.5f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(16f, KeyframeAnimations.degreeVec(-122.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(18f, KeyframeAnimations.degreeVec(-112.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(20f, KeyframeAnimations.degreeVec(-87.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(22.00733333333292f, KeyframeAnimations.degreeVec(12.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(24f, KeyframeAnimations.degreeVec(-90f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(25.916666666666668f, KeyframeAnimations.degreeVec(-22.5f, -32.5f, -32.5f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-17.5f, -25f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(2f, KeyframeAnimations.degreeVec(-27.5f, 37.5f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(4f, KeyframeAnimations.degreeVec(45f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(6f, KeyframeAnimations.degreeVec(-2.5f, -30f, -17.5f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(8f, KeyframeAnimations.degreeVec(37.5f, 32.5f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(10f, KeyframeAnimations.degreeVec(17.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(12f, KeyframeAnimations.degreeVec(47.5f, -12.5f, -7.5f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(14f, KeyframeAnimations.degreeVec(47.5f, 27.5f, 22.5f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(16f, KeyframeAnimations.degreeVec(-5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(18f, KeyframeAnimations.degreeVec(-22.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(20f, KeyframeAnimations.degreeVec(-17.5f, 12.5f, 20f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(22.00733333333292f, KeyframeAnimations.degreeVec(0f, 10f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(24f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(26f, KeyframeAnimations.degreeVec(-17.5f, -25f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("leftWing", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(4f, KeyframeAnimations.posVec(0f, 0f, -1f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(6f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(25.916666666666668f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("leftWing", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, -17.5f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(2f, KeyframeAnimations.degreeVec(12.5f, 25f, -15f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(4f, KeyframeAnimations.degreeVec(-25f, -2.5f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(6f, KeyframeAnimations.degreeVec(2.5f, -27.5f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(8f, KeyframeAnimations.degreeVec(32.5f, -2.5f, -37.5f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(10f, KeyframeAnimations.degreeVec(0f, -7.5f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(12f, KeyframeAnimations.degreeVec(20f, 0f, -10f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(14f, KeyframeAnimations.degreeVec(20f, 0f, -10f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(16f, KeyframeAnimations.degreeVec(0f, -20f, 7.5f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(18f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(20f, KeyframeAnimations.degreeVec(0f, 32.5f, -25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(22.00733333333292f, KeyframeAnimations.degreeVec(0f, -12.5f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(24f, KeyframeAnimations.degreeVec(0f, -27.5f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(25.916666666666668f, KeyframeAnimations.degreeVec(0f, -17.5f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("rightWing", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(4f, KeyframeAnimations.posVec(0f, 0f, -1f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(6f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(25.916666666666668f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("rightWing", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, -15f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(2f, KeyframeAnimations.degreeVec(-12.5f, 20f, -5f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(4f, KeyframeAnimations.degreeVec(-25f, 2.5f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(6f, KeyframeAnimations.degreeVec(0f, -27.5f, 12.5f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(8f, KeyframeAnimations.degreeVec(50f, 15f, 45f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(10f, KeyframeAnimations.degreeVec(0f, 7.5f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(12f, KeyframeAnimations.degreeVec(47.5f, 25f, 50f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(14f, KeyframeAnimations.degreeVec(47.5f, 25f, 50f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(16f, KeyframeAnimations.degreeVec(0f, 20f, -7.5f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(18f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(20f, KeyframeAnimations.degreeVec(0f, 0f, 10f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(22.00733333333292f, KeyframeAnimations.degreeVec(0f, -17.5f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(24f, KeyframeAnimations.degreeVec(0f, 27.5f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(25.916666666666668f, KeyframeAnimations.degreeVec(0f, -15f, 0f), AnimationChannel.Interpolations.CATMULLROM))).build();
    private final ModelPart Angel, root, leftWing, rightWing, leftArm, rightArm, body;

    public AliceAngelModel(ModelPart root) {
        this.root = root;
        this.Angel = root.getChild("Angel");
        this.body = Angel.getChild("Body");
        this.leftWing = body.getChild("leftWing");
        this.rightWing = body.getChild("rightWing");
        this.leftArm = body.getChild("leftArm");
        this.rightArm = body.getChild("rightArm");
    }

    public static LayerDefinition meshLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Angel = partdefinition.addOrReplaceChild("Angel", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition Body = Angel.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(32, 17).addBox(-4.0F, -9.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.5F))
                .texOffs(56, 17).addBox(-4.0F, -9.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -15.0F, 0.0F));

        PartDefinition leftArm = Body.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(24, 59).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -7.0F, 0.0F));

        PartDefinition rightArm = Body.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(10, 59).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -7.0F, 0.0F));

        PartDefinition head = Body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 17).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(72, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, -9.0F, 0.0F));

        PartDefinition leftWing = Body.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(0, 101).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(6, 83).addBox(-1.0F, -8.9F, 5.0F, 2.0F, 14.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(18, 83).addBox(-1.0F, -6.9F, 3.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(8, 33).addBox(-1.0F, -10.9F, 6.0F, 2.0F, 21.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 33).addBox(-1.0F, -10.0F, 9.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(38, 59).addBox(-1.0F, -8.0F, 11.0F, 2.0F, 17.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -4.0F, 2.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition rightWing = Body.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(10, 101).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(12, 83).addBox(-1.0F, -8.9F, 5.0F, 2.0F, 14.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(26, 83).addBox(-1.0F, -6.9F, 3.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(18, 33).addBox(-1.0F, -10.0F, 9.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 83).addBox(-1.0F, -8.0F, 11.0F, 2.0F, 17.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 59).addBox(-1.0F, -10.9F, 6.0F, 2.0F, 21.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -4.0F, 2.0F, 0.0F, 0.7854F, 0.0F));

        PartDefinition Legs = Angel.addOrReplaceChild("Legs", CubeListBuilder.create().texOffs(40, 0).addBox(-5.0F, -0.25F, -3.0F, 10.0F, 11.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-6.0F, 10.75F, -4.0F, 12.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -14.75F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setupAnim(WeepingAngel weepingAngel, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        // TODO Store these in AngelTexVariants
        body.getChild("head").visible = weepingAngel.getVariant() != AngelVariant.RUSTED_NO_HEAD;
        body.getChild("rightArm").visible = weepingAngel.getVariant() != AngelVariant.RUSTED_NO_ARM;
        body.getChild("leftWing").visible = weepingAngel.getVariant() != AngelVariant.RUSTED_NO_WING;

        this.root().getAllParts().forEach(ModelPart::resetPose);

        if (weepingAngel.getFakeAnimation() != -1) {
            animate(ChiselScreen.POSE_ANIMATION_STATE, getAnimationDefinition(weepingAngel.getFakeAnimation()), Minecraft.getInstance().player.tickCount);
            return;
        }

        int playbackSpeed = Mth.clamp(weepingAngel.level().random.nextInt(7), 2, 7);
        if (isBlockPosBehindPlayer(Minecraft.getInstance().player, weepingAngel.blockPosition()) || weepingAngel.isHooked() || weepingAngel.getSeenTime() > 0 || weepingAngel.tickCount < 200) {
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
        return new ResourceLocation(WeepingAngels.MODID, "textures/entity/angel/alice/variants/" + angelVariant.location().getPath() + "/" + angelVariant.location().getPath() + "_angel_" + angelEmotion.getId().toLowerCase(Locale.ENGLISH) + ".png");
    }

    @Override
    public void animateTile(StatueBlockEntity statueBlockEntity) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        animate(statueBlockEntity.getAnimationState(), poseForId(statueBlockEntity.getAnimation()), Minecraft.getInstance().player.tickCount);
    }

    @Override
    public AnimationDefinition poseForId(int index) {
        return switch (index) {
            case 1 -> IDLE1;
            case 2 -> IDLE2;
            case 3 -> IDLE3;
            case 4 -> IDLE4;
            case 5 -> IDLE5;
            case 6 -> IDLE6;
            case 7 -> IDLE7;
            case 8 -> ANGRY1;
            case 9 -> ANGRY2;
            case 10 -> ANGRY3;
            case 11 -> ANGRY4;
            case 12 -> ANGRY5;
            case 0 -> ANGRY6;
            default -> IDLE2;
        };
    }


    @Override
    public void translateToHand(HumanoidArm pSide, PoseStack pPoseStack) {
        ModelPart hand = this.getArm(pSide);
        boolean wasVisible = hand.visible;
        hand.visible = true;
        hand.translateAndRotate(pPoseStack);
        hand.visible = wasVisible;

    }

    protected ModelPart getArm(HumanoidArm pSide) {
        return pSide == HumanoidArm.LEFT ? this.leftArm : this.rightArm;
    }
}