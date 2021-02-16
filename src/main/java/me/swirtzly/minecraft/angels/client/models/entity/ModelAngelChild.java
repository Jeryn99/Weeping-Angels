package me.swirtzly.minecraft.angels.client.models.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import me.swirtzly.minecraft.angels.WeepingAngels;
import me.swirtzly.minecraft.angels.client.poses.WeepingAngelPose;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;

/**
 * Angel Type: Child
 */
public class ModelAngelChild extends EntityModel< WeepingAngelEntity > implements IAngelModel {

    private final ResourceLocation TEXTURE = new ResourceLocation(WeepingAngels.MODID,
            "textures/entities/angel_child.png");

    private final ModelRenderer WeepingCherubFix;
    private final ModelRenderer LeftLeg;
    private final ModelRenderer RightLeg;
    private final ModelRenderer LeftArm;
    private final ModelRenderer RightArm;
    private final ModelRenderer Body;
    private final ModelRenderer Head;
    private final ModelRenderer LeftWing;
    private final ModelRenderer RightWing;

    private WeepingAngelPose weepingAngelPose = WeepingAngelPose.ANGRY;

    /**
     * Angel Type: Child
     */
    public ModelAngelChild() {
        textureWidth = 128;
        textureHeight = 128;

        WeepingCherubFix = new ModelRenderer(this);
        WeepingCherubFix.setRotationPoint(0.0F, 24.0F, 0.0F);


        LeftLeg = new ModelRenderer(this);
        LeftLeg.setRotationPoint(1.9F, -12.0F, 0.0F);
        WeepingCherubFix.addChild(LeftLeg);
        LeftLeg.setTextureOffset(19, 50).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        RightLeg = new ModelRenderer(this);
        RightLeg.setRotationPoint(-1.9F, -12.0F, 0.0F);
        WeepingCherubFix.addChild(RightLeg);
        RightLeg.setTextureOffset(19, 50).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        LeftArm = new ModelRenderer(this);
        LeftArm.setRotationPoint(5.0F, -21.5F, 0.0F);
        WeepingCherubFix.addChild(LeftArm);
        LeftArm.setTextureOffset(53, 53).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.0F, false);

        RightArm = new ModelRenderer(this);
        RightArm.setRotationPoint(-5.0F, -21.5F, 0.0F);
        WeepingCherubFix.addChild(RightArm);
        RightArm.setTextureOffset(50, 17).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.0F, false);

        Body = new ModelRenderer(this);
        Body.setRotationPoint(0.0F, -24.0F, 0.0F);
        WeepingCherubFix.addChild(Body);
        Body.setTextureOffset(33, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);
        Body.setTextureOffset(29, 30).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 15.0F, 4.0F, 0.25F, false);

        Head = new ModelRenderer(this);
        Head.setRotationPoint(0.0F, -24.0F, 0.0F);
        WeepingCherubFix.addChild(Head);
        Head.setTextureOffset(0, 17).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
        Head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F, false);

        LeftWing = new ModelRenderer(this);
        LeftWing.setRotationPoint(0.0F, -19.0F, 2.0F);
        WeepingCherubFix.addChild(LeftWing);
        setRotationAngle(LeftWing, 0.0F, -0.7854F, 0.0F);
        LeftWing.setTextureOffset(0, 50).addBox(0.0F, -7.0F, 0.0F, 9.0F, 15.0F, 0.0F, 0.0F, false);

        RightWing = new ModelRenderer(this);
        RightWing.setRotationPoint(0.0F, -19.0F, 2.0F);
        WeepingCherubFix.addChild(RightWing);
        setRotationAngle(RightWing, 0.0F, 0.7854F, 0.0F);
        RightWing.setTextureOffset(0, 34).addBox(-9.0F, -7.0F, 0.0F, 9.0F, 15.0F, 0.0F, 0.0F, false);
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
    public ResourceLocation generateTex(WeepingAngelPose pose, WeepingAngelEntity.AngelVariants angelVariants) {
        return null;
    }

    @Override
    public void setRotationAngles(WeepingAngelEntity weepingAngelEntity, float v, float v1, float v2, float v3, float v4) {
        WeepingAngelPose pose = weepingAngelPose;
        if (weepingAngelEntity != null) {
            pose = WeepingAngelPose.getPose(weepingAngelEntity.getAngelPose());
        }

        if (pose == WeepingAngelPose.FURIOUS) {
            RightArm.rotateAngleX = (float) Math.toRadians(-115);
            RightArm.rotateAngleY = (float) Math.toRadians(0);
            RightArm.rotateAngleZ = (float) Math.toRadians(0);

            LeftArm.rotateAngleX = (float) Math.toRadians(-55);
            LeftArm.rotateAngleY = (float) Math.toRadians(0);
            LeftArm.rotateAngleZ = (float) Math.toRadians(0);

            Head.rotateAngleX = (float) Math.toRadians(17.5);
            Head.rotateAngleY = (float) Math.toRadians(0);
            Head.rotateAngleZ = (float) Math.toRadians(-10);
            return;
        }

        if (pose == WeepingAngelPose.APPROACH) {
            RightArm.rotateAngleX = -1.04533F;
            RightArm.rotateAngleY = -0.55851F;
            RightArm.rotateAngleZ = 0.0F;
            LeftArm.rotateAngleX = -1.04533F;
            LeftArm.rotateAngleY = 0.55851F;
            LeftArm.rotateAngleZ = 0.0F;
            return;
        }


        if (pose == WeepingAngelPose.ANGRY) {
            RightArm.rotateAngleX = (float) Math.toRadians(-90);
            RightArm.rotateAngleY = (float) Math.toRadians(-20);
            RightArm.rotateAngleZ = (float) Math.toRadians(30);

            LeftArm.rotateAngleX = (float) Math.toRadians(-90);
            LeftArm.rotateAngleY = (float) Math.toRadians(25);
            LeftArm.rotateAngleZ = (float) Math.toRadians(-17.5);

            Head.rotateAngleX = (float) Math.toRadians(0);
            Head.rotateAngleY = (float) Math.toRadians(-12.5);
            Head.rotateAngleZ = (float) Math.toRadians(0);
            return;
        }

        if (pose == WeepingAngelPose.HIDING) {
            Head.rotateAngleX = (float) Math.toRadians(20);
            Head.rotateAngleY = (float) Math.toRadians(0);
            Head.rotateAngleZ = (float) Math.toRadians(0);


            RightArm.rotateAngleX = (float) Math.toRadians(-105);
            RightArm.rotateAngleY = (float) Math.toRadians(20);
            RightArm.rotateAngleZ = (float) Math.toRadians(12.5);

            LeftArm.rotateAngleX = (float) Math.toRadians(-105);
            LeftArm.rotateAngleY = (float) Math.toRadians(-20);
            LeftArm.rotateAngleZ = (float) Math.toRadians(-12.5);
            return;
        }

        if (pose == WeepingAngelPose.IDLE) {
            Head.rotateAngleX = (float) Math.toRadians(0);
            Head.rotateAngleY = (float) Math.toRadians(0);
            Head.rotateAngleZ = (float) Math.toRadians(0);


            RightArm.rotateAngleX = (float) Math.toRadians(0);
            RightArm.rotateAngleY = (float) Math.toRadians(0);
            RightArm.rotateAngleZ = (float) Math.toRadians(7.5);

            LeftArm.rotateAngleX = (float) Math.toRadians(0);
            LeftArm.rotateAngleY = (float) Math.toRadians(0);
            LeftArm.rotateAngleZ = (float) Math.toRadians(-7.5);
            return;
        }

        if (pose == WeepingAngelPose.SHY) {
            RightArm.rotateAngleX = (float) Math.toRadians(-90);
            RightArm.rotateAngleY = (float) Math.toRadians(-1.5);
            RightArm.rotateAngleZ = (float) Math.toRadians(-20);

            LeftArm.rotateAngleX = (float) Math.toRadians(-120);
            LeftArm.rotateAngleY = (float) Math.toRadians(-36);
            LeftArm.rotateAngleZ = (float) Math.toRadians(10);

            Head.rotateAngleX = (float) Math.toRadians(20);
            Head.rotateAngleY = (float) Math.toRadians(-40);
            Head.rotateAngleZ = (float) Math.toRadians(-20);

            return;
        }
    }


    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        matrixStack.push();
        matrixStack.scale(0.5F, 0.5F, 0.5F);
        matrixStack.translate(0, 1.5, 0);
        WeepingCherubFix.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        matrixStack.pop();
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public ResourceLocation getTextureForPose(Object angel, WeepingAngelPose pose) {

        return TEXTURE;
   /*     String angelVarients = null;

        if (angel instanceof WeepingAngelEntity) {
            WeepingAngelEntity weepingAngelEntity = (WeepingAngelEntity) angel;
            angelVarients = weepingAngelEntity.getVarient();
        }

        if (angel instanceof StatueTile) {
            StatueTile weepingAngelEntity = (StatueTile) angel;
            angelVarients = weepingAngelEntity.getAngelVarients().name().toLowerCase();
        }

        if (angel instanceof PlinthTile) {
            PlinthTile weepingAngelEntity = (PlinthTile) angel;
            angelVarients = weepingAngelEntity.getAngelVarients().name().toLowerCase();
        }

        String location = "textures/entities/cherub/";
        String varient = "normal_angel_";
        location = location + angelVarients.toLowerCase() + "/";

        String suffix = "idle";

        if (pose.create().isAngry()) {
            suffix = "angry";
        }

        if (pose == AngelPoses.POSE_OPEN_ARMS) {
            suffix = "scream";
        }

        return new ResourceLocation(WeepingAngels.MODID, location + varient + suffix + ".png");*/
    }

}
