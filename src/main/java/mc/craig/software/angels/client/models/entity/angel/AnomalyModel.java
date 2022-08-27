package mc.craig.software.angels.client.models.entity.angel;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mc.craig.software.angels.common.entity.anomaly.AnomalyEntity;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class AnomalyModel extends HierarchicalModel<AnomalyEntity> {


    public static final AnimationDefinition SPIN = AnimationDefinition.Builder.withLength(8f).addAnimation("A", new AnimationChannel(AnimationChannel.Targets.SCALE, new Keyframe(0f, KeyframeAnimations.scaleVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.5f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1f, KeyframeAnimations.scaleVec(0.5f, 0.5f, 0.5f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.5f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.LINEAR), new Keyframe(2f, KeyframeAnimations.scaleVec(0.5f, 0.5f, 0.5f), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.5f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.LINEAR), new Keyframe(3f, KeyframeAnimations.scaleVec(0.5f, 0.5f, 0.5f), AnimationChannel.Interpolations.LINEAR), new Keyframe(3.5f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.LINEAR), new Keyframe(4f, KeyframeAnimations.scaleVec(0.5f, 0.5f, 0.5f), AnimationChannel.Interpolations.LINEAR), new Keyframe(4.5f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.LINEAR), new Keyframe(5f, KeyframeAnimations.scaleVec(0.5f, 0.5f, 0.5f), AnimationChannel.Interpolations.LINEAR), new Keyframe(5.5f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.LINEAR), new Keyframe(6f, KeyframeAnimations.scaleVec(0.5f, 0.5f, 0.5f), AnimationChannel.Interpolations.LINEAR), new Keyframe(6.5f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.LINEAR), new Keyframe(7f, KeyframeAnimations.scaleVec(0.5f, 0.5f, 0.5f), AnimationChannel.Interpolations.LINEAR), new Keyframe(7.708333333333333f, KeyframeAnimations.scaleVec(1.5f, 1.5f, 1.5f), AnimationChannel.Interpolations.LINEAR), new Keyframe(8f, KeyframeAnimations.scaleVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("C", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(8f, KeyframeAnimations.degreeVec(1180f, -2045f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("D", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(8f, KeyframeAnimations.degreeVec(3600f, 0f, 3600f), AnimationChannel.Interpolations.LINEAR))).addAnimation("E", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(8f, KeyframeAnimations.degreeVec(0f, -3600f, -3600f), AnimationChannel.Interpolations.LINEAR))).addAnimation("CF", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(8f, KeyframeAnimations.degreeVec(0f, 0f, -3600f), AnimationChannel.Interpolations.LINEAR))).build();

    private final ModelPart A;
    private final ModelPart root;

    public AnomalyModel(ModelPart root) {
        this.root = root;
        this.A = root.getChild("A");
    }

    @Override
    public ModelPart root() {
        return root;
    }

    public static LayerDefinition meshLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition A = partdefinition.addOrReplaceChild("A", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, 0.0F));

        PartDefinition B = A.addOrReplaceChild("B", CubeListBuilder.create().texOffs(36, 19).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition C = B.addOrReplaceChild("C", CubeListBuilder.create().texOffs(0, 43).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition D = C.addOrReplaceChild("D", CubeListBuilder.create().texOffs(38, 33).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition E = D.addOrReplaceChild("E", CubeListBuilder.create().texOffs(0, 19).addBox(-6.0F, -6.0F, -6.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition CF = E.addOrReplaceChild("CF", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -0.5F, -9.0F, 18.0F, 1.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }


    @Override
    public void setupAnim(AnomalyEntity anomalyEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        animate(anomalyEntity.SPIN_STATE, SPIN, pAgeInTicks);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        A.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
