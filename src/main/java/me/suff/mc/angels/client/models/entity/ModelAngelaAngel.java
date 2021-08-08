package me.suff.mc.angels.client.models.entity;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.entities.WeepingAngel;
import me.suff.mc.angels.common.blockentities.PlinthBlockEntity;
import me.suff.mc.angels.common.blockentities.StatueBlockEntity;
import me.suff.mc.angels.common.variants.AbstractVariant;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.HumanoidArm;

public class ModelAngelaAngel extends ListModel<WeepingAngel> implements IAngelModel, HeadedModel, ArmedModel {

    public static final ResourceLocation ANGRY = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angela/normal/normal_angel_angry.png");

    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart leftArm;
    private final ModelPart rightArm;
    private final ModelPart Legs;
    private final ModelPart leftWing;
    private final ModelPart rightWing;

    private WeepingAngelPose weepingAngelPose = WeepingAngelPose.ANGRY;

    public ModelAngelaAngel(ModelPart root) {
        this.head = root.getChild("head");
        this.Legs = root.getChild("legs");
        this.body = root.getChild("body");
        this.rightArm = root.getChild("right_arm");
        this.leftArm = root.getChild("left_arm");
        this.leftWing = root.getChild("leftwing");
        this.rightWing = root.getChild("rightwing");
    }

    public static LayerDefinition getModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 17).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(72, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(56, 17).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(32, 17).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition leftArm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(24, 59).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition rightArm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(10, 59).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition Legs = partdefinition.addOrReplaceChild("legs", CubeListBuilder.create().texOffs(40, 0).addBox(-5.0F, -0.25F, -3.0F, 10.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 0).addBox(-6.0F, 10.75F, -4.0F, 12.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 9.25F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition leftWing = partdefinition.addOrReplaceChild("leftwing", CubeListBuilder.create().texOffs(0, 101).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(6, 83).addBox(-1.0F, -8.9F, 5.0F, 2.0F, 14.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(18, 83).addBox(-1.0F, -6.9F, 3.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(8, 33).addBox(-1.0F, -10.9F, 6.0F, 2.0F, 21.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 33).addBox(-1.0F, -10.0F, 9.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(38, 59).addBox(-1.0F, -8.0F, 11.0F, 2.0F, 17.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 5.0F, 2.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition rightWing = partdefinition.addOrReplaceChild("rightwing", CubeListBuilder.create().texOffs(10, 101).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(12, 83).addBox(-1.0F, -8.9F, 5.0F, 2.0F, 14.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(26, 83).addBox(-1.0F, -6.9F, 3.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(18, 33).addBox(-1.0F, -10.0F, 9.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 83).addBox(-1.0F, -8.0F, 11.0F, 2.0F, 17.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 59).addBox(-1.0F, -10.9F, 6.0F, 2.0F, 21.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 5.0F, 2.0F, 0.0F, 0.7854F, 0.0F));
        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    public void setPose(WeepingAngelPose weepingAngelPose) {
        this.weepingAngelPose = weepingAngelPose;
    }

    public WeepingAngelPose getAngelPoses() {
        return weepingAngelPose;
    }

    @Override
    public void setupAnim(WeepingAngel weepingAngel, float v, float v1, float v2, float v3, float v4) {
        WeepingAngelPose pose = weepingAngelPose;
        if (weepingAngel != null) {
            pose = WeepingAngelPose.getPose(weepingAngel.getAngelPose());
        }

        boolean isAngry = pose.getEmotion() == WeepingAngelPose.Emotion.ANGRY || pose.getEmotion() == WeepingAngelPose.Emotion.SCREAM;
        float angleX = isAngry ? 20F : 0;
        float angleY = isAngry ? 60F : 45F;
        float angleZ = 0;

        if (pose.getEmotion() == WeepingAngelPose.Emotion.SCREAM) {
            angleY += 10F;
            angleX -= 10F;
        }

        head.xRot = (float) Math.toRadians(0);
        head.yRot = (float) Math.toRadians(0);
        head.zRot = (float) Math.toRadians(0);

        rightWing.xRot = (float) Math.toRadians(angleX);
        rightWing.yRot = (float) Math.toRadians(angleY);
        rightWing.zRot = (float) Math.toRadians(angleZ);
        leftWing.xRot = (float) Math.toRadians(angleX);
        leftWing.yRot = (float) Math.toRadians(-angleY);
        leftWing.zRot = (float) Math.toRadians(angleZ);


        if (pose == WeepingAngelPose.FURIOUS) {
            rightArm.xRot = (float) Math.toRadians(-115);
            rightArm.yRot = (float) Math.toRadians(0);
            rightArm.zRot = (float) Math.toRadians(0);

            leftArm.xRot = (float) Math.toRadians(-55);
            leftArm.yRot = (float) Math.toRadians(0);
            leftArm.zRot = (float) Math.toRadians(0);

            head.xRot = (float) Math.toRadians(17.5);
            head.yRot = (float) Math.toRadians(0);
            head.zRot = (float) Math.toRadians(-10);
            return;
        }


        if (pose == WeepingAngelPose.ANGRY) {
            rightArm.xRot = (float) Math.toRadians(-90);
            rightArm.yRot = (float) Math.toRadians(-20);
            rightArm.zRot = (float) Math.toRadians(30);

            leftArm.xRot = (float) Math.toRadians(-90);
            leftArm.yRot = (float) Math.toRadians(25);
            leftArm.zRot = (float) Math.toRadians(-17.5);

            head.xRot = (float) Math.toRadians(0);
            head.yRot = (float) Math.toRadians(-12.5);
            head.zRot = (float) Math.toRadians(0);
            return;
        }


        if (pose == WeepingAngelPose.HIDING) {
            head.xRot = (float) Math.toRadians(20);
            head.yRot = (float) Math.toRadians(0);
            head.zRot = (float) Math.toRadians(0);

            rightArm.xRot = (float) Math.toRadians(-105);
            rightArm.yRot = (float) Math.toRadians(20);
            rightArm.zRot = (float) Math.toRadians(12.5);

            leftArm.xRot = (float) Math.toRadians(-105);
            leftArm.yRot = (float) Math.toRadians(-20);
            leftArm.zRot = (float) Math.toRadians(-12.5);
            return;
        }

        if (pose == WeepingAngelPose.APPROACH) {
            rightArm.xRot = -1.04533F;
            rightArm.yRot = -0.55851F;
            rightArm.zRot = 0.0F;
            leftArm.xRot = -1.04533F;
            leftArm.yRot = 0.55851F;
            leftArm.zRot = 0.0F;
            return;
        }

        if (pose == WeepingAngelPose.IDLE) {
            head.xRot = (float) Math.toRadians(0);
            head.yRot = (float) Math.toRadians(0);
            head.zRot = (float) Math.toRadians(0);

            rightArm.xRot = (float) Math.toRadians(0);
            rightArm.yRot = (float) Math.toRadians(0);
            rightArm.zRot = (float) Math.toRadians(-7.5);

            leftArm.xRot = (float) Math.toRadians(0);
            leftArm.yRot = (float) Math.toRadians(0);
            leftArm.zRot = (float) Math.toRadians(7.5);
            return;
        }

        if (pose == WeepingAngelPose.SHY) {
            rightArm.xRot = (float) Math.toRadians(-90);
            rightArm.yRot = (float) Math.toRadians(-1.5);
            rightArm.zRot = (float) Math.toRadians(-20);

            leftArm.xRot = (float) Math.toRadians(-120);
            leftArm.yRot = (float) Math.toRadians(-36);
            leftArm.zRot = (float) Math.toRadians(10);

            head.xRot = (float) Math.toRadians(20);
            head.yRot = (float) Math.toRadians(-40);
            head.zRot = (float) Math.toRadians(-20);
            return;
        }
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        body.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        leftArm.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        rightArm.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Legs.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        leftWing.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        rightWing.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
    }

    @Override
    public Iterable<ModelPart> parts() {
        return ImmutableList.of(this.body, this.leftWing, this.rightWing, this.head, this.leftArm, this.rightArm, this.Legs);
    }

    @Override
    public ResourceLocation getTextureForPose(Object angel, WeepingAngelPose pose) {

        if (angel instanceof WeepingAngel weepingAngel) {
            return generateTex(pose, weepingAngel.getVariant());
        }

        if (angel instanceof StatueBlockEntity statueBlockEntity) {
            return generateTex(statueBlockEntity.getPose(), statueBlockEntity.getAngelVarients());
        }

        if (angel instanceof PlinthBlockEntity plinthBlockEntity) {
            return generateTex(plinthBlockEntity.getPose(), plinthBlockEntity.getAngelVarients());
        }
        return ANGRY;
    }

    @Override
    public WeepingAngelPose getAngelPose() {
        return weepingAngelPose;
    }

    @Override
    public void setAngelPose(WeepingAngelPose angelType) {
        weepingAngelPose = angelType;
    }

    @Override
    public ResourceLocation generateTex(WeepingAngelPose pose, AbstractVariant abstractVariant) {
        String variant = abstractVariant.getRegistryName().getPath() + "_angel_";
        String coreFolder = "textures/entities/angela/";
        coreFolder = coreFolder + abstractVariant.getRegistryName().getPath() + "/";
        WeepingAngelPose.Emotion emotion = pose.getEmotion();
        String suffix = abstractVariant.isHeadless() ? "headless" : emotion.name().toLowerCase();
        return new ResourceLocation(abstractVariant.getRegistryName().getNamespace(), coreFolder + variant + suffix + ".png");
    }

    @Override
    public ModelPart getHead() {
        return head;
    }

    protected ModelPart getArm(HumanoidArm handSide) {
        return handSide == HumanoidArm.LEFT ? this.leftArm : this.rightArm;
    }

    @Override
    public void translateToHand(HumanoidArm handSide, PoseStack matrixStack) {
        ModelPart hand = this.getArm(handSide);
        boolean wasVisible = hand.visible;
        hand.visible = true;
        hand.translateAndRotate(matrixStack);
        hand.visible = wasVisible;
    }
}