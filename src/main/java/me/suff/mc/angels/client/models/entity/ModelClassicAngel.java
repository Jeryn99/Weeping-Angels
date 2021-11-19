package me.suff.mc.angels.client.models.entity;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.entities.WeepingAngel;
import me.suff.mc.angels.common.variants.AbstractVariant;
import me.suff.mc.angels.utils.DateChecker;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.HumanoidArm;

public class ModelClassicAngel extends ListModel<WeepingAngel> implements IAngelModel, ArmedModel {

    private final ResourceLocation TEXTURE = new ResourceLocation(WeepingAngels.MODID,
            "textures/entities/a_dizzle/angel_classic.png");

    private final ResourceLocation TEXTURE_ANGRY = new ResourceLocation(WeepingAngels.MODID,
            "textures/entities/a_dizzle/angel_classic_angry.png");

    private final ResourceLocation TEXTURE_ANGRY_XMAS = new ResourceLocation(WeepingAngels.MODID,
            "textures/entities/a_dizzle/angel_classic_angry_xmas.png");

    private final ResourceLocation TEXTURE_XMAS = new ResourceLocation(WeepingAngels.MODID,
            "textures/entities/a_dizzle/angel_classic_xmas.png");

    private final ModelPart leftfoot;
    private final ModelPart rightfoot;
    private final ModelPart leftwing1;
    private final ModelPart leftwing2;
    private final ModelPart leftwing3;
    private final ModelPart leftwing4;
    private final ModelPart rightwing1;
    private final ModelPart rightwing2;
    private final ModelPart rightwing3;
    private final ModelPart rightwing4;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart rightarm;
    private final ModelPart leftarm;
    private final ModelPart rightleg;
    private final ModelPart leftleg;

    private WeepingAngelPose weepingAngelPose = WeepingAngelPose.ANGRY;
    private boolean showHurt = false;

    public ModelClassicAngel(ModelPart root) {
        this.leftfoot = root.getChild("leftfoot");
        this.rightfoot = root.getChild("rightfoot");
        this.leftwing1 = root.getChild("leftwing1");
        this.leftwing2 = root.getChild("leftwing2");
        this.leftwing3 = root.getChild("leftwing3");
        this.leftwing4 = root.getChild("leftwing4");
        this.rightwing1 = root.getChild("rightwing1");
        this.rightwing2 = root.getChild("rightwing2");
        this.rightwing3 = root.getChild("rightwing3");
        this.rightwing4 = root.getChild("rightwing4");
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.rightarm = root.getChild("rightarm");
        this.leftarm = root.getChild("leftarm");
        this.rightleg = root.getChild("rightleg");
        this.leftleg = root.getChild("leftleg");
    }

    public static LayerDefinition getModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition leftfoot = partdefinition.addOrReplaceChild("leftfoot", CubeListBuilder.create().texOffs(32, 0).addBox(-2.0F, 7.0F, -4.0F, 6.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, 12.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition rightfoot = partdefinition.addOrReplaceChild("rightfoot", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, 7.0F, -4.0F, 6.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 12.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition leftwing1 = partdefinition.addOrReplaceChild("leftwing1", CubeListBuilder.create().texOffs(40, 25).addBox(-0.5F, -1.0F, 1.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 1.0F, 1.0F, 0.2094F, 0.6109F, 0.0F));

        PartDefinition leftwing2 = partdefinition.addOrReplaceChild("leftwing2", CubeListBuilder.create().texOffs(46, 19).addBox(-0.5F, -2.0F, 3.0F, 1.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 1.0F, 1.0F, 0.2094F, 0.6109F, 0.0175F));

        PartDefinition leftwing3 = partdefinition.addOrReplaceChild("leftwing3", CubeListBuilder.create().texOffs(58, 12).addBox(-0.5F, -2.0F, 5.0F, 1.0F, 18.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 1.0F, 1.0F, 0.2094F, 0.6109F, 0.0F));

        PartDefinition leftwing4 = partdefinition.addOrReplaceChild("leftwing4", CubeListBuilder.create().texOffs(52, 16).addBox(-0.5F, 0.0F, 7.0F, 1.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 1.0F, 1.0F, 0.2094F, 0.6109F, 0.0F));

        PartDefinition rightwing1 = partdefinition.addOrReplaceChild("rightwing1", CubeListBuilder.create().texOffs(40, 25).addBox(-0.5F, -1.0F, 1.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 1.0F, 1.0F, 0.2094F, -0.6109F, 0.0F));

        PartDefinition rightwing2 = partdefinition.addOrReplaceChild("rightwing2", CubeListBuilder.create().texOffs(46, 19).addBox(-0.5F, -2.0F, 3.0F, 1.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 1.0F, 1.0F, 0.2094F, -0.6109F, 0.0F));

        PartDefinition rightwing3 = partdefinition.addOrReplaceChild("rightwing3", CubeListBuilder.create().texOffs(58, 12).addBox(-0.5F, -2.0F, 5.0F, 1.0F, 18.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 1.0F, 1.0F, 0.2094F, -0.6109F, 0.0F));

        PartDefinition rightwing4 = partdefinition.addOrReplaceChild("rightwing4", CubeListBuilder.create().texOffs(52, 16).addBox(-0.5F, 0.0F, 7.0F, 1.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 1.0F, 1.0F, 0.2094F, -0.6109F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2443F, 0.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition rightarm = partdefinition.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(24, 19).addBox(-3.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.0F, 0.0F, 0.0F, -1.7453F, -0.5585F, 0.0F));

        PartDefinition leftarm = partdefinition.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(24, 19).addBox(-1.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 0.0F, 0.0F, -1.7453F, 0.5585F, 0.0F));

        PartDefinition rightleg = partdefinition.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(24, 19).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 12.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition leftleg = partdefinition.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(24, 19).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, 12.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
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
    public void setupAnim(WeepingAngel weepingAngel, float v, float v1, float v2, float v3, float v4) {
        WeepingAngelPose pose = weepingAngelPose;
        if (weepingAngel != null) {
            pose = WeepingAngelPose.getPose(weepingAngel.getAngelPose());
        }

        boolean isAngry = pose.getEmotion() == WeepingAngelPose.Emotion.ANGRY;
        float angleX = isAngry ? 20F : 10F;
        float angleY = isAngry ? 60F : 30F;
        float angleZ = 5F;

        head.xRot = (float) Math.toRadians(0);
        head.yRot = (float) Math.toRadians(0);
        head.zRot = (float) Math.toRadians(0);

        rightwing2.xRot = rightwing3.xRot = rightwing4.xRot = rightwing1.xRot = leftwing2.xRot = leftwing3.xRot = leftwing4.xRot = leftwing1.xRot = (float) Math.toRadians(angleX);
        rightwing2.yRot = rightwing3.yRot = rightwing4.yRot = rightwing1.yRot = (float) Math.toRadians(-angleY);
        leftwing2.yRot = leftwing3.yRot = leftwing4.yRot = leftwing1.yRot = (float) Math.toRadians(angleY);
        rightwing2.zRot = rightwing3.zRot = rightwing4.zRot = rightwing1.zRot = (float) Math.toRadians(angleZ);
        leftwing2.zRot = leftwing3.zRot = leftwing4.zRot = leftwing1.zRot = (float) Math.toRadians(-angleZ);


        if (pose == WeepingAngelPose.FURIOUS) {
            rightarm.zRot = 0.0F;
            leftarm.zRot = 0.0F;
            rightarm.yRot = -(0.1F - 0 * 0.6F);
            leftarm.yRot = 0.1F - 0 * 0.6F;
            rightarm.xRot = -1.570796F;
            leftarm.xRot = -1.570796F;
            return;
        }


        if (pose == WeepingAngelPose.APPROACH) {
            rightarm.xRot = -1.04533F;
            rightarm.yRot = -0.55851F;
            rightarm.zRot = 0.0F;
            leftarm.xRot = -1.04533F;
            leftarm.yRot = 0.55851F;
            leftarm.zRot = 0.0F;
            return;
        }

        if (pose == WeepingAngelPose.ANGRY) {
            rightarm.xRot = (float) Math.toRadians(-95);
            rightarm.yRot = (float) Math.toRadians(37.5);
            rightarm.zRot = (float) Math.toRadians(40);
            leftarm.xRot = (float) Math.toRadians(-95);
            leftarm.yRot = (float) Math.toRadians(-37.5);
            leftarm.zRot = (float) Math.toRadians(-40);
            return;
        }

        if (pose == WeepingAngelPose.IDLE || pose == WeepingAngelPose.HIDING) {
            rightarm.xRot = -1.74533F;
            rightarm.yRot = -0.55851F;
            rightarm.zRot = 0.0F;
            leftarm.xRot = -1.74533F;
            leftarm.yRot = 0.55851F;
            leftarm.zRot = 0.0F;
            return;
        }

        if (pose == WeepingAngelPose.SHY) {
            rightarm.xRot = (float) Math.toRadians(-90);
            rightarm.yRot = (float) Math.toRadians(-1.5);
            rightarm.zRot = (float) Math.toRadians(-20);

            leftarm.xRot = (float) Math.toRadians(-120);
            leftarm.yRot = (float) Math.toRadians(-36);
            leftarm.zRot = (float) Math.toRadians(10);

            head.xRot = (float) Math.toRadians(20);
            head.yRot = (float) Math.toRadians(-40);
            head.zRot = (float) Math.toRadians(-20);
            return;
        }
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        leftfoot.render(matrixStack, buffer, packedLight, showHurt ? packedOverlay : OverlayTexture.NO_OVERLAY);
        rightfoot.render(matrixStack, buffer, packedLight, showHurt ? packedOverlay : OverlayTexture.NO_OVERLAY);
        leftwing1.render(matrixStack, buffer, packedLight, showHurt ? packedOverlay : OverlayTexture.NO_OVERLAY);
        leftwing2.render(matrixStack, buffer, packedLight, showHurt ? packedOverlay : OverlayTexture.NO_OVERLAY);
        leftwing3.render(matrixStack, buffer, packedLight, showHurt ? packedOverlay : OverlayTexture.NO_OVERLAY);
        leftwing4.render(matrixStack, buffer, packedLight, showHurt ? packedOverlay : OverlayTexture.NO_OVERLAY);
        rightwing1.render(matrixStack, buffer, packedLight, showHurt ? packedOverlay : OverlayTexture.NO_OVERLAY);
        rightwing2.render(matrixStack, buffer, packedLight, showHurt ? packedOverlay : OverlayTexture.NO_OVERLAY);
        rightwing3.render(matrixStack, buffer, packedLight, showHurt ? packedOverlay : OverlayTexture.NO_OVERLAY);
        rightwing4.render(matrixStack, buffer, packedLight, showHurt ? packedOverlay : OverlayTexture.NO_OVERLAY);
        head.render(matrixStack, buffer, packedLight, showHurt ? packedOverlay : OverlayTexture.NO_OVERLAY);
        body.render(matrixStack, buffer, packedLight, showHurt ? packedOverlay : OverlayTexture.NO_OVERLAY);
        rightarm.render(matrixStack, buffer, packedLight, showHurt ? packedOverlay : OverlayTexture.NO_OVERLAY);
        leftarm.render(matrixStack, buffer, packedLight, showHurt ? packedOverlay : OverlayTexture.NO_OVERLAY);
        rightleg.render(matrixStack, buffer, packedLight, showHurt ? packedOverlay : OverlayTexture.NO_OVERLAY);
        leftleg.render(matrixStack, buffer, packedLight, showHurt ? packedOverlay : OverlayTexture.NO_OVERLAY);
    }

    @Override
    public Iterable<ModelPart> parts() {
        return ImmutableList.of(leftfoot,
                rightfoot,
                leftwing1,
                leftwing2,
                leftwing3,
                leftwing4,
                rightwing1,
                rightwing2,
                rightwing3,
                rightwing4,
                head,
                body,
                rightarm,
                leftarm,
                rightleg,
                leftleg);
    }


    @Override
    public boolean toggleHurt(boolean hurtShow) {
        return showHurt;
    }

    @Override
    public ResourceLocation generateTex(WeepingAngelPose pose, AbstractVariant angelVariants) {
        return null;
    }

    @Override
    public ResourceLocation getTextureForPose(Object angel, WeepingAngelPose pose) {
        if (!DateChecker.isXmas()) {
            return pose.getEmotion() == WeepingAngelPose.Emotion.ANGRY ? TEXTURE_ANGRY : TEXTURE;
        }
        return pose.getEmotion() == WeepingAngelPose.Emotion.ANGRY ? TEXTURE_ANGRY_XMAS : TEXTURE_XMAS;
    }

    protected ModelPart getArm(HumanoidArm handSide) {
        return handSide == HumanoidArm.LEFT ? this.leftarm : this.rightarm;
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
