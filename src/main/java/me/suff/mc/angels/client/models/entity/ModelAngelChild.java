package me.suff.mc.angels.client.models.entity;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import me.suff.mc.angels.common.tileentities.PlinthTile;
import me.suff.mc.angels.common.tileentities.StatueTile;
import me.suff.mc.angels.common.variants.AbstractVariant;
import me.suff.mc.angels.common.variants.AngelTypes;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

/**
 * Angel Type: Child
 */
public class ModelAngelChild extends ListModel<WeepingAngelEntity> implements IAngelModel {

    private final ModelPart WeepingCherubFix;
    private final ModelPart LeftLeg;
    private final ModelPart RightLeg;
    private final ModelPart LeftArm;
    private final ModelPart RightArm;
    private final ModelPart Body;
    private final ModelPart Head;
    private final ModelPart LeftWing;
    private final ModelPart RightWing;

    private WeepingAngelPose weepingAngelPose = WeepingAngelPose.ANGRY;

    /**
     * Angel Type: Child
     */
    public ModelAngelChild() {
        texWidth = 67;
        texHeight = 69;

        WeepingCherubFix = new ModelPart(this);
        WeepingCherubFix.setPos(0.0F, 24.0F, 0.0F);


        LeftLeg = new ModelPart(this);
        LeftLeg.setPos(1.9F, -12.0F, 0.0F);
        WeepingCherubFix.addChild(LeftLeg);
        LeftLeg.texOffs(19, 50).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        RightLeg = new ModelPart(this);
        RightLeg.setPos(-1.9F, -12.0F, 0.0F);
        WeepingCherubFix.addChild(RightLeg);
        RightLeg.texOffs(19, 50).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        LeftArm = new ModelPart(this);
        LeftArm.setPos(5.0F, -21.5F, 0.0F);
        WeepingCherubFix.addChild(LeftArm);
        setRotationAngle(LeftArm, -0.6109F, 0.0F, 0.0F);
        LeftArm.texOffs(53, 53).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.0F, false);

        RightArm = new ModelPart(this);
        RightArm.setPos(-5.0F, -21.5F, 0.0F);
        WeepingCherubFix.addChild(RightArm);
        setRotationAngle(RightArm, -0.6109F, 0.0F, 0.0F);
        RightArm.texOffs(50, 17).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.0F, false);

        Body = new ModelPart(this);
        Body.setPos(0.0F, -24.0F, 0.0F);
        WeepingCherubFix.addChild(Body);
        Body.texOffs(33, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);
        Body.texOffs(29, 30).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 15.0F, 4.0F, 0.25F, false);

        Head = new ModelPart(this);
        Head.setPos(0.0F, -24.0F, 0.0F);
        WeepingCherubFix.addChild(Head);
        Head.texOffs(0, 17).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
        Head.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F, false);

        LeftWing = new ModelPart(this);
        LeftWing.setPos(0.0F, -19.0F, 2.0F);
        WeepingCherubFix.addChild(LeftWing);
        setRotationAngle(LeftWing, 0.0F, -0.7854F, 0.0F);
        LeftWing.texOffs(0, 50).addBox(0.0F, -7.0F, 0.0F, 9.0F, 15.0F, 0.0F, 0.0F, false);

        RightWing = new ModelPart(this);
        RightWing.setPos(0.0F, -19.0F, 2.0F);
        WeepingCherubFix.addChild(RightWing);
        setRotationAngle(RightWing, 0.0F, 0.7854F, 0.0F);
        RightWing.texOffs(0, 34).addBox(-9.0F, -7.0F, 0.0F, 9.0F, 15.0F, 0.0F, 0.0F, false);
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
    public void setupAnim(WeepingAngelEntity weepingAngelEntity, float v, float v1, float v2, float v3, float v4) {
        WeepingAngelPose pose = weepingAngelPose;
        if (weepingAngelEntity != null) {
            pose = WeepingAngelPose.getPose(weepingAngelEntity.getAngelPose());
        }

        if (pose == WeepingAngelPose.FURIOUS) {
            RightArm.xRot = (float) Math.toRadians(-115);
            RightArm.yRot = (float) Math.toRadians(0);
            RightArm.zRot = (float) Math.toRadians(0);

            LeftArm.xRot = (float) Math.toRadians(-55);
            LeftArm.yRot = (float) Math.toRadians(0);
            LeftArm.zRot = (float) Math.toRadians(0);

            Head.xRot = (float) Math.toRadians(17.5);
            Head.yRot = (float) Math.toRadians(0);
            Head.zRot = (float) Math.toRadians(-10);
            return;
        }

        if (pose == WeepingAngelPose.APPROACH) {
            RightArm.xRot = -1.04533F;
            RightArm.yRot = -0.55851F;
            RightArm.zRot = 0.0F;
            LeftArm.xRot = -1.04533F;
            LeftArm.yRot = 0.55851F;
            LeftArm.zRot = 0.0F;
            return;
        }


        if (pose == WeepingAngelPose.ANGRY) {
            RightArm.xRot = (float) Math.toRadians(-90);
            RightArm.yRot = (float) Math.toRadians(-20);
            RightArm.zRot = (float) Math.toRadians(30);

            LeftArm.xRot = (float) Math.toRadians(-90);
            LeftArm.yRot = (float) Math.toRadians(25);
            LeftArm.zRot = (float) Math.toRadians(-17.5);

            Head.xRot = (float) Math.toRadians(0);
            Head.yRot = (float) Math.toRadians(-12.5);
            Head.zRot = (float) Math.toRadians(0);
            return;
        }

        if (pose == WeepingAngelPose.HIDING) {
            Head.xRot = (float) Math.toRadians(20);
            Head.yRot = (float) Math.toRadians(0);
            Head.zRot = (float) Math.toRadians(0);


            RightArm.xRot = (float) Math.toRadians(-105);
            RightArm.yRot = (float) Math.toRadians(20);
            RightArm.zRot = (float) Math.toRadians(12.5);

            LeftArm.xRot = (float) Math.toRadians(-105);
            LeftArm.yRot = (float) Math.toRadians(-20);
            LeftArm.zRot = (float) Math.toRadians(-12.5);
            return;
        }

        if (pose == WeepingAngelPose.IDLE) {
            Head.xRot = (float) Math.toRadians(0);
            Head.yRot = (float) Math.toRadians(0);
            Head.zRot = (float) Math.toRadians(0);


            RightArm.xRot = (float) Math.toRadians(0);
            RightArm.yRot = (float) Math.toRadians(0);
            RightArm.zRot = (float) Math.toRadians(7.5);

            LeftArm.xRot = (float) Math.toRadians(0);
            LeftArm.yRot = (float) Math.toRadians(0);
            LeftArm.zRot = (float) Math.toRadians(-7.5);
            return;
        }

        if (pose == WeepingAngelPose.SHY) {
            RightArm.xRot = (float) Math.toRadians(-90);
            RightArm.yRot = (float) Math.toRadians(-1.5);
            RightArm.zRot = (float) Math.toRadians(-20);

            LeftArm.xRot = (float) Math.toRadians(-120);
            LeftArm.yRot = (float) Math.toRadians(-36);
            LeftArm.zRot = (float) Math.toRadians(10);

            Head.xRot = (float) Math.toRadians(20);
            Head.yRot = (float) Math.toRadians(-40);
            Head.zRot = (float) Math.toRadians(-20);

            return;
        }
    }


    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        matrixStack.pushPose();
        matrixStack.scale(0.5F, 0.5F, 0.5F);
        matrixStack.translate(0, 1.5, 0);
        WeepingCherubFix.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        matrixStack.popPose();
    }

    @Override
    public Iterable<ModelPart> parts() {
        return ImmutableList.of(this.Body, this.LeftWing, this.RightWing, this.Head, this.LeftArm, this.RightArm, this.LeftLeg, this.RightLeg, this.WeepingCherubFix);
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    @Override
    public ResourceLocation getTextureForPose(Object angel, WeepingAngelPose pose) {
        if (angel instanceof WeepingAngelEntity) {
            WeepingAngelEntity weepingAngelEntity = (WeepingAngelEntity) angel;
            return generateTex(pose, weepingAngelEntity.getVariant());
        }

        if (angel instanceof StatueTile) {
            StatueTile weepingAngelEntity = (StatueTile) angel;
            return generateTex(weepingAngelEntity.getPose(), weepingAngelEntity.getAngelVarients());
        }

        if (angel instanceof PlinthTile) {
            PlinthTile weepingAngelEntity = (PlinthTile) angel;
            return generateTex(weepingAngelEntity.getPose(), weepingAngelEntity.getAngelVarients());
        }

        return generateTex(WeepingAngelPose.APPROACH, AngelTypes.NORMAL.get());
    }

    @Override
    public ResourceLocation generateTex(WeepingAngelPose pose, AbstractVariant angelVariants) {
        String location = "textures/entities/cherub/angel_cherub_";
        WeepingAngelPose.Emotion emotion = pose.getEmotion();
        String suffix = emotion.name().toLowerCase();
        return new ResourceLocation(WeepingAngels.MODID, location + suffix + ".png");
    }

}
