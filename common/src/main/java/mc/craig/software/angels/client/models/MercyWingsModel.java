package mc.craig.software.angels.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.entity.angel.ai.AngelEmotion;
import mc.craig.software.angels.common.entity.angel.ai.AngelVariant;
import mc.craig.software.angels.donators.DonationChecker;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public class MercyWingsModel extends HierarchicalModel<LivingEntity> {

//TODO: Fix this
//    public static final AnimationDefinition CLOSE = AnimationDefinition.Builder.withLength(1.2916666666666667f).addAnimation("LMain", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-7.5f, 55f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.2916666666666667f, KeyframeAnimations.degreeVec(-12.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("L1", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.25f, KeyframeAnimations.degreeVec(87.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("L2", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.16666666666666666f, KeyframeAnimations.degreeVec(75f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.2083333333333333f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("L3", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.125f, KeyframeAnimations.degreeVec(65f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.1666666666666667f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("L4", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.08333333333333333f, KeyframeAnimations.degreeVec(58.71f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.125f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("L5", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.041666666666666664f, KeyframeAnimations.degreeVec(50f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.0833333333333333f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("Feather6", new AnimationChannel(AnimationChannel.Targets.SCALE, new Keyframe(0.20833333333333334f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.5833333333333334f, KeyframeAnimations.scaleVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("Feather7", new AnimationChannel(AnimationChannel.Targets.SCALE, new Keyframe(0.16666666666666666f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.5416666666666666f, KeyframeAnimations.scaleVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("Feather8", new AnimationChannel(AnimationChannel.Targets.SCALE, new Keyframe(0.125f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.5f, KeyframeAnimations.scaleVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("Feather9", new AnimationChannel(AnimationChannel.Targets.SCALE, new Keyframe(0.08333333333333333f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.4583333333333333f, KeyframeAnimations.scaleVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("Feather10", new AnimationChannel(AnimationChannel.Targets.SCALE, new Keyframe(0.041666666666666664f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.4166666666666667f, KeyframeAnimations.scaleVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("LOpenRotPoint", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.375f, KeyframeAnimations.posVec(0f, 0.35f, 0.35f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.25f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("LOpenRotPoint", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.375f, KeyframeAnimations.degreeVec(90f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("RMain", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-7.5f, -55f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.2916666666666667f, KeyframeAnimations.degreeVec(-12.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("ROpenRotPoint", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.375f, KeyframeAnimations.posVec(0f, 0.35f, 0.35f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.25f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("ROpenRotPoint", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.375f, KeyframeAnimations.degreeVec(90f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("R1", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.25f, KeyframeAnimations.degreeVec(87.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("Feather2", new AnimationChannel(AnimationChannel.Targets.SCALE, new Keyframe(0.20833333333333334f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.5833333333333334f, KeyframeAnimations.scaleVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("R2", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.16666666666666666f, KeyframeAnimations.degreeVec(75f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.2083333333333333f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("Feather3", new AnimationChannel(AnimationChannel.Targets.SCALE, new Keyframe(0.16666666666666666f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.5416666666666666f, KeyframeAnimations.scaleVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("R3", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.125f, KeyframeAnimations.degreeVec(65f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.1666666666666667f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("Feather4", new AnimationChannel(AnimationChannel.Targets.SCALE, new Keyframe(0.125f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.5f, KeyframeAnimations.scaleVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("R4", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.08333333333333333f, KeyframeAnimations.degreeVec(58.71f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.125f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("Feather5", new AnimationChannel(AnimationChannel.Targets.SCALE, new Keyframe(0.08333333333333333f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.4583333333333333f, KeyframeAnimations.scaleVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("R5", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.041666666666666664f, KeyframeAnimations.degreeVec(50f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.0833333333333333f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("Feather11", new AnimationChannel(AnimationChannel.Targets.SCALE, new Keyframe(0.041666666666666664f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.4166666666666667f, KeyframeAnimations.scaleVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).build();
//    public static final AnimationDefinition OPEN = AnimationDefinition.Builder.withLength(1.765f).addAnimation("LMain", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-12.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.7083333333333334f, KeyframeAnimations.degreeVec(-7.5f, 55f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.5833333333333333f, KeyframeAnimations.degreeVec(-7.5f, 55f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("L1", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.8333333333333334f, KeyframeAnimations.degreeVec(87.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.25f, KeyframeAnimations.degreeVec(87.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("L2", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.875f, KeyframeAnimations.degreeVec(75f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.2916666666666667f, KeyframeAnimations.degreeVec(75f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("L3", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.9166666666666666f, KeyframeAnimations.degreeVec(65f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.3333333333333333f, KeyframeAnimations.degreeVec(65f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("L4", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.9583333333333334f, KeyframeAnimations.degreeVec(58.71f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.625f, KeyframeAnimations.degreeVec(58.71f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("L5", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1f, KeyframeAnimations.degreeVec(50f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.75f, KeyframeAnimations.degreeVec(50f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("Feather6", new AnimationChannel(AnimationChannel.Targets.SCALE, new Keyframe(0.4583333333333333f, KeyframeAnimations.scaleVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.7083333333333334f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.LINEAR))).addAnimation("Feather7", new AnimationChannel(AnimationChannel.Targets.SCALE, new Keyframe(0.5f, KeyframeAnimations.scaleVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.75f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.LINEAR))).addAnimation("Feather8", new AnimationChannel(AnimationChannel.Targets.SCALE, new Keyframe(0.5416666666666666f, KeyframeAnimations.scaleVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.7916666666666666f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.LINEAR))).addAnimation("Feather9", new AnimationChannel(AnimationChannel.Targets.SCALE, new Keyframe(0.5833333333333334f, KeyframeAnimations.scaleVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.8333333333333335f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.LINEAR))).addAnimation("Feather10", new AnimationChannel(AnimationChannel.Targets.SCALE, new Keyframe(0.625f, KeyframeAnimations.scaleVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.875f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.LINEAR))).addAnimation("LOpenRotPoint", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.16666666666666666f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.7083333333333334f, KeyframeAnimations.posVec(0f, 0.35f, 0.35f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.25f, KeyframeAnimations.posVec(0f, 0.35f, 0.35f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("LOpenRotPoint", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.16666666666666666f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.7083333333333334f, KeyframeAnimations.degreeVec(90f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.25f, KeyframeAnimations.degreeVec(90f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("RMain", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-12.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.7083333333333334f, KeyframeAnimations.degreeVec(-7.5f, -55f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.5833333333333333f, KeyframeAnimations.degreeVec(-7.5f, -55f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("ROpenRotPoint", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.16666666666666666f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.7083333333333334f, KeyframeAnimations.posVec(0f, 0.35f, 0.35f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.25f, KeyframeAnimations.posVec(0f, 0.35f, 0.35f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("ROpenRotPoint", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.16666666666666666f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.7083333333333334f, KeyframeAnimations.degreeVec(90f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.25f, KeyframeAnimations.degreeVec(90f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("R1", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.8333333333333334f, KeyframeAnimations.degreeVec(87.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.25f, KeyframeAnimations.degreeVec(87.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("Feather2", new AnimationChannel(AnimationChannel.Targets.SCALE, new Keyframe(0.5f, KeyframeAnimations.scaleVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.75f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.LINEAR))).addAnimation("R2", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.875f, KeyframeAnimations.degreeVec(75f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.2916666666666667f, KeyframeAnimations.degreeVec(75f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("Feather3", new AnimationChannel(AnimationChannel.Targets.SCALE, new Keyframe(0.5f, KeyframeAnimations.scaleVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.75f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.LINEAR))).addAnimation("R3", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.9166666666666666f, KeyframeAnimations.degreeVec(65f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.3333333333333333f, KeyframeAnimations.degreeVec(65f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("Feather4", new AnimationChannel(AnimationChannel.Targets.SCALE, new Keyframe(0.5416666666666666f, KeyframeAnimations.scaleVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.7916666666666666f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.LINEAR))).addAnimation("R4", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.9583333333333334f, KeyframeAnimations.degreeVec(58.71f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.625f, KeyframeAnimations.degreeVec(58.71f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("Feather5", new AnimationChannel(AnimationChannel.Targets.SCALE, new Keyframe(0.5833333333333334f, KeyframeAnimations.scaleVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.8333333333333335f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.LINEAR))).addAnimation("R5", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1f, KeyframeAnimations.degreeVec(50f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.75f, KeyframeAnimations.degreeVec(50f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("Feather11", new AnimationChannel(AnimationChannel.Targets.SCALE, new Keyframe(0.625f, KeyframeAnimations.scaleVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.875f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.LINEAR))).build();

    private final ModelPart Lwing;
    private final ModelPart RWing;
    private final ModelPart root;

    public MercyWingsModel(ModelPart root) {
        this.root = root;
        this.Lwing = root.getChild("Lwing");
        this.RWing = root.getChild("RWing");
    }

    public static LayerDefinition meshLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Lwing = partdefinition.addOrReplaceChild("Lwing", CubeListBuilder.create().texOffs(8, 0).addBox(-3.0F, -22.0F, 1.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 24.0F, 0.5F));

        PartDefinition LMain = Lwing.addOrReplaceChild("LMain", CubeListBuilder.create().texOffs(20, 6).addBox(-1.0F, -6.75F, -0.25F, 2.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 20).addBox(-1.0F, -5.75F, -0.25F, 2.0F, 7.0F, 2.0F, new CubeDeformation(-0.2F))
                .texOffs(39, 7).addBox(-1.0F, -4.75F, 0.75F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F))
                .texOffs(55, 9).addBox(-1.0F, -4.25F, 1.25F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.02F)), PartPose.offset(-2.0F, -21.5F, 2.25F));

        PartDefinition LOpenRotPoint = LMain.addOrReplaceChild("LOpenRotPoint", CubeListBuilder.create().texOffs(37, 37).addBox(-1.0F, -2.0071F, -1.8597F, 2.0F, 2.0F, 10.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -5.0F, 2.0F, -0.9599F, 0.0F, 0.0F));

        PartDefinition Inner_r1 = LOpenRotPoint.addOrReplaceChild("Inner_r1", CubeListBuilder.create().texOffs(51, 32).addBox(-1.0F, -0.1F, -9.9F, 2.0F, 2.0F, 10.0F, new CubeDeformation(-0.11F)), PartPose.offsetAndRotation(0.0F, -1.9071F, 8.0403F, 0.0873F, 0.0F, 0.0F));

        PartDefinition L1 = LMain.addOrReplaceChild("L1", CubeListBuilder.create().texOffs(19, 3).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 3.0F, 17.0F, new CubeDeformation(0.0F))
                .texOffs(38, 0).addBox(-0.5F, -1.5F, 3.5F, 1.0F, 2.0F, 12.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, -5.5F, 2.75F, -1.0908F, 0.0F, 0.0F));

        PartDefinition Feather6 = L1.addOrReplaceChild("Feather6", CubeListBuilder.create().texOffs(0, 32).mirror().addBox(0.0F, -1.25F, -2.0F, 0.0F, 4.0F, 28.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.5F, 2.0F));

        PartDefinition L2 = LMain.addOrReplaceChild("L2", CubeListBuilder.create().texOffs(0, 0).addBox(-0.55F, -1.0F, -2.0F, 1.0F, 3.0F, 17.0F, new CubeDeformation(0.0F))
                .texOffs(52, 18).addBox(-0.55F, -1.5F, 3.5F, 1.0F, 2.0F, 11.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, -4.75F, 2.0F, -1.0908F, 0.0F, 0.0F));

        PartDefinition Feather7 = L2.addOrReplaceChild("Feather7", CubeListBuilder.create().texOffs(0, 32).mirror().addBox(0.0F, -1.0F, 0.0F, 0.0F, 4.0F, 28.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.25F, 1.0F));

        PartDefinition L3 = LMain.addOrReplaceChild("L3", CubeListBuilder.create().texOffs(0, 20).addBox(-0.6F, -1.0F, -1.0F, 1.0F, 3.0F, 15.0F, new CubeDeformation(0.0F))
                .texOffs(58, 5).addBox(-0.6F, -1.5F, 3.5F, 1.0F, 2.0F, 10.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, -3.75F, 1.5F, -1.0908F, 0.0F, 0.0F));

        PartDefinition Feather8 = L3.addOrReplaceChild("Feather8", CubeListBuilder.create().texOffs(0, 36).mirror().addBox(0.0F, -1.0F, 1.0F, 0.0F, 4.0F, 24.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.25F, 1.0F));

        PartDefinition L4 = LMain.addOrReplaceChild("L4", CubeListBuilder.create().texOffs(19, 25).addBox(-0.65F, -1.0F, -1.0F, 1.0F, 3.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(71, 3).addBox(-0.65F, -1.5F, 2.5F, 1.0F, 2.0F, 9.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, -2.5F, 1.75F, -1.1781F, 0.0F, 0.0F));

        PartDefinition Feather9 = L4.addOrReplaceChild("Feather9", CubeListBuilder.create().texOffs(0, 34).mirror().addBox(0.0F, -0.5F, 1.5F, 0.0F, 3.0F, 21.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -0.25F, 0.5F));

        PartDefinition L5 = LMain.addOrReplaceChild("L5", CubeListBuilder.create().texOffs(34, 23).addBox(-0.7F, -1.0F, -1.0F, 1.0F, 3.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(70, 18).addBox(-0.7F, -1.5F, 1.5F, 1.0F, 2.0F, 8.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, -1.5F, 1.75F, -1.2654F, 0.0F, 0.0F));

        PartDefinition Feather10 = L5.addOrReplaceChild("Feather10", CubeListBuilder.create().texOffs(0, 37).mirror().addBox(0.0F, -1.0F, 1.5F, 0.0F, 2.0F, 15.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.25F, 0.5F));

        PartDefinition RWing = partdefinition.addOrReplaceChild("RWing", CubeListBuilder.create().texOffs(8, 0).mirror().addBox(1.0F, -22.0F, 1.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, 24.0F, 0.5F));

        PartDefinition RMain = RWing.addOrReplaceChild("RMain", CubeListBuilder.create().texOffs(20, 6).mirror().addBox(-1.0F, -6.75F, -0.25F, 2.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 20).mirror().addBox(-1.0F, -5.75F, -0.25F, 2.0F, 7.0F, 2.0F, new CubeDeformation(-0.2F)).mirror(false)
                .texOffs(39, 7).mirror().addBox(-1.0F, -4.75F, 0.75F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F)).mirror(false)
                .texOffs(55, 9).mirror().addBox(-1.0F, -4.25F, 1.25F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.02F)).mirror(false), PartPose.offset(2.0F, -21.5F, 2.25F));

        PartDefinition ROpenRotPoint = RMain.addOrReplaceChild("ROpenRotPoint", CubeListBuilder.create().texOffs(37, 37).mirror().addBox(-1.0F, -2.0071F, -1.8597F, 2.0F, 2.0F, 10.0F, new CubeDeformation(-0.1F)).mirror(false), PartPose.offsetAndRotation(0.0F, -5.0F, 2.0F, -0.9599F, 0.0F, 0.0F));

        PartDefinition Inner_r2 = ROpenRotPoint.addOrReplaceChild("Inner_r2", CubeListBuilder.create().texOffs(51, 32).mirror().addBox(-1.0F, -0.1F, -9.9F, 2.0F, 2.0F, 10.0F, new CubeDeformation(-0.11F)).mirror(false), PartPose.offsetAndRotation(0.0F, -1.9071F, 8.0403F, 0.0873F, 0.0F, 0.0F));

        PartDefinition R1 = RMain.addOrReplaceChild("R1", CubeListBuilder.create().texOffs(19, 3).mirror().addBox(-0.5F, -1.0F, -1.0F, 1.0F, 3.0F, 17.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(38, 0).mirror().addBox(-0.5F, -1.5F, 3.5F, 1.0F, 2.0F, 12.0F, new CubeDeformation(-0.01F)).mirror(false), PartPose.offsetAndRotation(0.0F, -5.5F, 2.75F, -1.0908F, 0.0F, 0.0F));

        PartDefinition Feather2 = R1.addOrReplaceChild("Feather2", CubeListBuilder.create().texOffs(0, 32).addBox(0.0F, -1.25F, -2.0F, 0.0F, 4.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, 2.0F));

        PartDefinition R2 = RMain.addOrReplaceChild("R2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.45F, -1.0F, -2.0F, 1.0F, 3.0F, 17.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(52, 18).mirror().addBox(-0.45F, -1.5F, 3.5F, 1.0F, 2.0F, 11.0F, new CubeDeformation(-0.01F)).mirror(false), PartPose.offsetAndRotation(0.0F, -4.75F, 2.0F, -1.0908F, 0.0F, 0.0F));

        PartDefinition Feather3 = R2.addOrReplaceChild("Feather3", CubeListBuilder.create().texOffs(0, 32).addBox(0.0F, -1.0F, 0.0F, 0.0F, 4.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.25F, 1.0F));

        PartDefinition R3 = RMain.addOrReplaceChild("R3", CubeListBuilder.create().texOffs(0, 20).mirror().addBox(-0.4F, -1.0F, -1.0F, 1.0F, 3.0F, 15.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(58, 5).mirror().addBox(-0.4F, -1.5F, 3.5F, 1.0F, 2.0F, 10.0F, new CubeDeformation(-0.01F)).mirror(false), PartPose.offsetAndRotation(0.0F, -3.75F, 1.5F, -1.0908F, 0.0F, 0.0F));

        PartDefinition Feather4 = R3.addOrReplaceChild("Feather4", CubeListBuilder.create().texOffs(0, 36).addBox(0.0F, -1.0F, 1.0F, 0.0F, 4.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.25F, 1.0F));

        PartDefinition R4 = RMain.addOrReplaceChild("R4", CubeListBuilder.create().texOffs(19, 25).mirror().addBox(-0.35F, -1.0F, -1.0F, 1.0F, 3.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(71, 3).mirror().addBox(-0.35F, -1.5F, 2.5F, 1.0F, 2.0F, 9.0F, new CubeDeformation(-0.01F)).mirror(false), PartPose.offsetAndRotation(0.0F, -2.5F, 1.75F, -1.1781F, 0.0F, 0.0F));

        PartDefinition Feather5 = R4.addOrReplaceChild("Feather5", CubeListBuilder.create().texOffs(0, 34).addBox(0.0F, -0.5F, 1.5F, 0.0F, 3.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.25F, 0.5F));

        PartDefinition R5 = RMain.addOrReplaceChild("R5", CubeListBuilder.create().texOffs(34, 23).mirror().addBox(-0.3F, -1.0F, -1.0F, 1.0F, 3.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(70, 18).mirror().addBox(-0.3F, -1.5F, 1.5F, 1.0F, 2.0F, 8.0F, new CubeDeformation(-0.01F)).mirror(false), PartPose.offsetAndRotation(0.0F, -1.5F, 1.75F, -1.2654F, 0.0F, 0.0F));

        PartDefinition Feather11 = R5.addOrReplaceChild("Feather11", CubeListBuilder.create().texOffs(0, 37).addBox(0.0F, -1.0F, 1.5F, 0.0F, 2.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.25F, 0.5F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(LivingEntity teacup, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
//TODO: Fix this
//        this.root().getAllParts().forEach(ModelPart::resetPose);
//TODO: Fix this
//        if (teacup instanceof Player player) {
//            DonationChecker.getDonatorData(player).ifPresent(donator -> {
//                this.animate(donator.openState, OPEN, ageInTicks, 1.6F);
//                this.animate(donator.closeState, CLOSE, ageInTicks, 3F);
//            });
//        }
    }


    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Lwing.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        RWing.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return root;
    }

    public static ResourceLocation texture(AngelEmotion angelEmotion, AngelVariant angelVariant) {
        return new ResourceLocation(WeepingAngels.MODID, "textures/entity/wings/mercy_wings.png");
    }

}