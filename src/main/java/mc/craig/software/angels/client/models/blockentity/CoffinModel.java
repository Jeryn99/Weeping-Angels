package mc.craig.software.angels.client.models.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mc.craig.software.angels.common.blockentity.CoffinBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class CoffinModel extends HierarchicalModel {
    private final ModelPart Body;
    private final ModelPart Door;
    private final ModelPart root;


    public static final AnimationDefinition OPEN = AnimationDefinition.Builder.withLength(1.125f).addAnimation("Door", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, -115f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.125f, KeyframeAnimations.degreeVec(0f, -115f, 0f), AnimationChannel.Interpolations.CATMULLROM))).build();
    public static final AnimationDefinition CLOSE = AnimationDefinition.Builder.withLength(1.125f).addAnimation("Door", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, -115f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.375f, KeyframeAnimations.degreeVec(0f, -115f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.125f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).build();

    public CoffinModel(ModelPart root) {
        this.root = root;
        this.Body = root.getChild("Body");
        this.Door = root.getChild("Door");
    }

    public static LayerDefinition meshLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 100).addBox(-15.0F, -51.0F, 3.0F, 1.0F, 17.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(0, 38).addBox(0.0F, -51.0F, 3.0F, 1.0F, 17.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(47, 111).addBox(-15.0F, -68.0F, 3.0F, 1.0F, 6.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(59, 61).addBox(0.0F, -68.0F, 3.0F, 1.0F, 6.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(23, 104).addBox(-16.0F, -63.0F, 3.0F, 1.0F, 13.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(13, 61).addBox(1.0F, -63.0F, 3.0F, 1.0F, 13.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(70, 28).addBox(-15.0F, -69.0F, 3.0F, 16.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(36, 0).addBox(-15.0F, -34.0F, 3.0F, 16.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(36, 36).addBox(-15.0F, -69.0F, 13.0F, 16.0F, 35.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, 57.0F, -6.0F));

        PartDefinition Door = partdefinition.addOrReplaceChild("Door", CubeListBuilder.create().texOffs(0, 0).addBox(-17.0F, -16.0F, -2.0F, 16.0F, 36.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(36, 12).addBox(-18.0F, -10.0F, -2.0F, 18.0F, 13.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(37, 29).addBox(-17.0F, -5.0F, -2.75F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, 4.0F, -3.0F));

        PartDefinition cube_r1 = Door.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(41, 29).addBox(0.1927F, -3.2401F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.25F, -1.725F, -2.625F, 0.1745F, 0.0F, 0.2182F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        Door.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return root;
    }


    @Override
    public void setupAnim(Entity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

    }

    public void animateTile(CoffinBlockEntity coffinBlockEntity){
        this.root().getAllParts().forEach(ModelPart::resetPose);

        if(coffinBlockEntity.isOpen()){
            animate(coffinBlockEntity.COFFIN_OPEN, OPEN, coffinBlockEntity.animationTimer);
            return;
        }

        animate(coffinBlockEntity.COFFIN_CLOSE, CLOSE, coffinBlockEntity.animationTimer);
    }
}
