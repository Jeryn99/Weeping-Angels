package mc.craig.software.angels.client.models.entity.angel;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.blockentity.StatueBlockEntity;
import mc.craig.software.angels.common.entity.angel.AngelEmotion;
import mc.craig.software.angels.common.entity.angel.AngelTextureVariant;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import static mc.craig.software.angels.client.models.entity.angel.AliceAngelModel.*;

public class ADizzleAngelModel extends AngelModel {

    private final ModelPart Angel;
    private final ModelPart root;

    public ADizzleAngelModel(ModelPart root) {
        this.root = root;
        this.Angel = root.getChild("Angel");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Angel = partdefinition.addOrReplaceChild("Angel", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition Body = Angel.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 0.0F));

        PartDefinition leftArm = Body.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(24, 19).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -10.0F, 0.0F));

        PartDefinition rightArm = Body.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(24, 19).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -10.0F, 0.0F));

        PartDefinition head = Body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 0.0F));

        PartDefinition leftWing = Body.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(40, 25).addBox(-0.5F, -2.5F, 0.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(46, 19).addBox(-0.5F, -3.5F, 2.0F, 1.0F, 11.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(58, 12).addBox(-0.5F, -3.5F, 4.0F, 1.0F, 18.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(52, 16).addBox(-0.5F, -1.5F, 6.0F, 1.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -8.5F, 2.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition rightWing = Body.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(40, 25).addBox(-0.5F, -2.5F, 0.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(46, 19).addBox(-0.5F, -3.5F, 2.0F, 1.0F, 11.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(58, 12).addBox(-0.5F, -3.5F, 4.0F, 1.0F, 18.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(52, 16).addBox(-0.5F, -1.5F, 6.0F, 1.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -8.5F, 2.0F, 0.0F, 0.7854F, 0.0F));

        PartDefinition Legs = Angel.addOrReplaceChild("Legs", CubeListBuilder.create().texOffs(32, 0).addBox(0.0F, 7.0F, -4.0F, 6.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(32, 0).addBox(-6.0F, 7.0F, -4.0F, 6.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(24, 19).addBox(-4.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(24, 19).mirror().addBox(0.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -12.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Angel.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return root;
    }


    @Override
    public ResourceLocation texture(AngelEmotion angelEmotion, AngelTextureVariant angelTextureVariant) {
        return new ResourceLocation(WeepingAngels.MODID, "textures/entity/angel/a_dizzle/variants/a_dizzle_" + angelEmotion.getId() + ".png");
    }

    @Override
    public void animateTile(StatueBlockEntity statueBlockEntity) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        animate(statueBlockEntity.getAnimationState(), poseForId(statueBlockEntity.getAnimation()), Minecraft.getInstance().player.tickCount);
    }

    @Override
    public AnimationDefinition poseForId(int index) {
        return switch (index) {
            case 1 -> AliceAngelModel.IDLE1;
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
    public void setupAnim(WeepingAngel weepingAngel, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        int playbackSpeed = Mth.clamp(weepingAngel.level.random.nextInt(7), 2, 7);
        if (weepingAngel.isHooked() || weepingAngel.getSeenTime() > 0) {
            playbackSpeed = 0;
        }

        animate(weepingAngel.POSE_ANIMATION_STATE, ANIMATION_STREAM, weepingAngel.tickCount, playbackSpeed);
    }
}