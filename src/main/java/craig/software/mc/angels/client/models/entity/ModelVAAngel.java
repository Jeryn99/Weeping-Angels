package craig.software.mc.angels.client.models.entity;


import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import craig.software.mc.angels.client.poses.WeepingAngelPose;
import craig.software.mc.angels.common.variants.AbstractVariant;
import craig.software.mc.angels.common.variants.AngelVariants;
import craig.software.mc.angels.common.entities.WeepingAngelEntity;
import craig.software.mc.angels.common.tileentities.PlinthTile;
import craig.software.mc.angels.common.tileentities.StatueTile;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

public class ModelVAAngel extends EntityModel<WeepingAngelEntity> implements IAngelModel {
    private final ModelRenderer head;
    private final ModelRenderer headband_r1;
    private final ModelRenderer body;
    private final ModelRenderer leftArm;
    private final ModelRenderer rightArm;
    private final ModelRenderer Legs;
    private final ModelRenderer SKIRT_r1;
    private final ModelRenderer leftWing;
    private final ModelRenderer rightWing;

    private WeepingAngelPose weepingPose = WeepingAngelPose.ANGRY;


    public ModelVAAngel() {
        texWidth = 72;
        texHeight = 72;

        head = new ModelRenderer(this);
        head.setPos(0.0F, 0.0F, 0.0F);
        head.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

        headband_r1 = new ModelRenderer(this);
        headband_r1.setPos(0.0F, -6.5F, 0.25F);
        head.addChild(headband_r1);
        setRotationAngle(headband_r1, -0.1745F, 0.0F, 0.0F);
        headband_r1.texOffs(28, 53).addBox(-4.5F, 0.0F, -4.75F, 9.0F, 1.0F, 9.0F, -0.1F, false);

        body = new ModelRenderer(this);
        body.setPos(0.0F, 24.0F, 0.0F);
        body.texOffs(26, 18).addBox(-4.0F, -24.0F, -2.0F, 8.0F, 10.0F, 4.0F, 0.0F, false);

        leftArm = new ModelRenderer(this);
        leftArm.setPos(5.0F, 1.5F, 0.0F);
        leftArm.texOffs(0, 46).addBox(-1.0F, -1.5F, -1.5F, 3.0F, 7.0F, 3.0F, 0.0F, false);
        leftArm.texOffs(35, 45).addBox(-1.0F, 5.5F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, false);

        rightArm = new ModelRenderer(this);
        rightArm.setPos(-5.0F, 1.5F, 0.0F);
        rightArm.texOffs(47, 43).addBox(-2.0F, -1.5F, -1.5F, 3.0F, 7.0F, 3.0F, 0.0F, false);
        rightArm.texOffs(19, 46).addBox(-2.0F, 5.5F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, false);

        Legs = new ModelRenderer(this);
        Legs.setPos(0.0F, 24.0F, 0.0F);
        Legs.texOffs(0, 16).addBox(-4.0F, -14.0F, -2.5F, 8.0F, 12.0F, 5.0F, 0.3F, false);
        Legs.texOffs(25, 9).addBox(-5.0F, -2.0F, -3.5F, 10.0F, 2.0F, 7.0F, 0.0F, false);
        Legs.texOffs(0, 56).addBox(-3.5F, -8.0F, -1.5F, 3.0F, 8.0F, 3.0F, 0.0F, false);
        Legs.texOffs(16, 55).addBox(0.5F, -8.0F, -1.5F, 3.0F, 8.0F, 3.0F, 0.0F, false);

        SKIRT_r1 = new ModelRenderer(this);
        SKIRT_r1.setPos(0.0F, -6.0F, 0.0F);
        Legs.addChild(SKIRT_r1);
        setRotationAngle(SKIRT_r1, 0.0F, 0.0F, 0.1309F);
        SKIRT_r1.texOffs(38, 63).addBox(-5.25F, -2.0F, -3.5F, 10.0F, 2.0F, 7.0F, 0.0F, false);

        leftWing = new ModelRenderer(this);
        leftWing.setPos(2.5F, 2.5F, 2.0F);
        leftWing.texOffs(28, 32).addBox(-0.5F, -2.0F, 1.0F, 1.0F, 1.0F, 8.0F, 0.0F, true);
        leftWing.texOffs(0, 0).addBox(-0.5F, 8.0F, 6.0F, 1.0F, 3.0F, 2.0F, 0.0F, true);
        leftWing.texOffs(0, 5).addBox(-0.5F, 11.0F, 7.0F, 1.0F, 2.0F, 1.0F, 0.0F, true);
        leftWing.texOffs(50, 2).addBox(-0.5F, 5.0F, 4.0F, 1.0F, 3.0F, 4.0F, 0.0F, true);
        leftWing.texOffs(12, 33).addBox(-0.5F, 3.0F, 4.0F, 1.0F, 2.0F, 5.0F, 0.0F, true);
        leftWing.texOffs(43, 25).addBox(-0.5F, 1.0F, 2.0F, 1.0F, 2.0F, 7.0F, 0.0F, true);
        leftWing.texOffs(0, 34).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 2.0F, 10.0F, 0.0F, true);
        leftWing.texOffs(48, 34).addBox(-0.5F, -3.0F, 3.0F, 1.0F, 1.0F, 5.0F, 0.0F, true);
        leftWing.texOffs(50, 18).addBox(-0.5F, -4.0F, 4.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);

        rightWing = new ModelRenderer(this);
        rightWing.setPos(-2.5F, 2.5F, 2.0F);
        rightWing.texOffs(28, 32).addBox(-0.5F, -2.0F, 1.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);
        rightWing.texOffs(21, 16).addBox(-0.5F, 8.0F, 6.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
        rightWing.texOffs(0, 16).addBox(-0.5F, 11.0F, 7.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        rightWing.texOffs(50, 2).addBox(-0.5F, 5.0F, 4.0F, 1.0F, 3.0F, 4.0F, 0.0F, false);
        rightWing.texOffs(12, 33).addBox(-0.5F, 3.0F, 4.0F, 1.0F, 2.0F, 5.0F, 0.0F, false);
        rightWing.texOffs(43, 25).addBox(-0.5F, 1.0F, 2.0F, 1.0F, 2.0F, 7.0F, 0.0F, false);
        rightWing.texOffs(0, 34).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 2.0F, 10.0F, 0.0F, false);
        rightWing.texOffs(48, 34).addBox(-0.5F, -3.0F, 3.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
        rightWing.texOffs(50, 18).addBox(-0.5F, -4.0F, 4.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(WeepingAngelEntity weepingAngel, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
        WeepingAngelPose pose = getAngelPose();
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
        rightWing.yRot = (float) Math.toRadians(-angleY);
        rightWing.zRot = (float) Math.toRadians(angleZ);
        leftWing.xRot = (float) Math.toRadians(angleX);
        leftWing.yRot = (float) Math.toRadians(angleY);
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
            rightArm.yRot = (float) Math.toRadians(-20);
            rightArm.zRot = (float) Math.toRadians(12.5);

            leftArm.xRot = (float) Math.toRadians(-105);
            leftArm.yRot = (float) Math.toRadians(20);
            leftArm.zRot = (float) Math.toRadians(-12.5);
            return;
        }

        if (pose == WeepingAngelPose.APPROACH) {
            rightArm.xRot = -1.04533F;
            rightArm.yRot = 0.55851F;
            rightArm.zRot = 0.0F;
            leftArm.xRot = -1.04533F;
            leftArm.yRot = -0.55851F;
            leftArm.zRot = 0.0F;
            return;
        }

        if (pose == WeepingAngelPose.IDLE) {
            head.xRot = (float) Math.toRadians(0);
            head.yRot = (float) Math.toRadians(0);
            head.zRot = (float) Math.toRadians(0);

            rightArm.xRot = (float) Math.toRadians(0);
            rightArm.yRot = (float) Math.toRadians(0);
            rightArm.zRot = (float) Math.toRadians(7.5);

            leftArm.xRot = (float) Math.toRadians(0);
            leftArm.yRot = (float) Math.toRadians(0);
            leftArm.zRot = (float) Math.toRadians(-7.5);
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
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        body.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        leftArm.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        rightArm.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Legs.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        leftWing.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        rightWing.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    @Override
    public ResourceLocation generateTex(WeepingAngelPose pose, AbstractVariant abstractVariant) {

        //TODO: This is dumb
        if (abstractVariant != AngelVariants.NORMAL.get() && abstractVariant != AngelVariants.RUSTED.get()) {
            abstractVariant = AngelVariants.NORMAL.get();
        }

        String variant = abstractVariant.getRegistryName().getPath() + "_angel_";
        String coreFolder = "textures/entities/spare_time/";
        coreFolder = coreFolder + abstractVariant.getRegistryName().getPath() + "/";
        WeepingAngelPose.Emotion emotion = pose.getEmotion();
        String suffix = abstractVariant.isHeadless() ? "headless" : emotion.name().toLowerCase();
        return new ResourceLocation(abstractVariant.getRegistryName().getNamespace(), coreFolder + variant + suffix + ".png");
    }

    @Override
    public ResourceLocation getTextureForPose(Object angel, WeepingAngelPose pose) {

        if (angel instanceof WeepingAngelEntity) {
            WeepingAngelEntity weepingAngel = (WeepingAngelEntity) angel;
            return generateTex(pose, weepingAngel.getVariant());
        }

        if (angel instanceof StatueTile) {
            StatueTile statueBlockEntity = (StatueTile) angel;
            return generateTex(statueBlockEntity.getPose(), statueBlockEntity.getAngelVarients());
        }

        if (angel instanceof PlinthTile) {
            PlinthTile plinthBlockEntity = (PlinthTile) angel;
            return generateTex(plinthBlockEntity.getPose(), plinthBlockEntity.getAngelVarients());
        }
        return generateTex(WeepingAngelPose.ANGRY, AngelVariants.NORMAL.get());
    }


    @Override
    public WeepingAngelPose getAngelPose() {
        return weepingPose;
    }

    @Override
    public void setAngelPose(WeepingAngelPose angelPose) {
        this.weepingPose = angelPose;
    }

    @Override
    public Iterable<ModelRenderer> wings(MatrixStack pose) {
        return ImmutableList.of(leftWing, rightWing);
    }

    @Override
    public ModelRenderer getSantaAttachment(MatrixStack pose, boolean b) {
        return head;
    }
}