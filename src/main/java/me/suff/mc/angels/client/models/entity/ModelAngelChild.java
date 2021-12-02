package me.suff.mc.angels.client.models.entity;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3d;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.blockentities.PlinthBlockEntity;
import me.suff.mc.angels.common.blockentities.StatueBlockEntity;
import me.suff.mc.angels.common.entities.WeepingAngel;
import me.suff.mc.angels.common.variants.AbstractVariant;
import me.suff.mc.angels.common.variants.AngelTypes;
import me.suff.mc.angels.utils.Pair;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;

/**
 * Angel Type: Child
 */
public class ModelAngelChild extends ListModel<WeepingAngel> implements IAngelModel {

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
    private boolean showHurt = false;
    private Pair<ModelPart, Vector3d> headData;

    /**
     * Angel Type: Child
     */
    public ModelAngelChild(ModelPart root) {
        this.WeepingCherubFix = root.getChild("WeepingCherubFix");
        this.LeftLeg = WeepingCherubFix.getChild("LeftLeg");
        this.RightLeg = WeepingCherubFix.getChild("RightLeg");
        this.LeftArm = WeepingCherubFix.getChild("LeftArm");
        this.RightArm = WeepingCherubFix.getChild("RightArm");
        this.Body = WeepingCherubFix.getChild("Body");
        this.Head = WeepingCherubFix.getChild("Head");
        this.LeftWing = WeepingCherubFix.getChild("LeftWing");
        this.RightWing = WeepingCherubFix.getChild("RightWing");
    }

    public static LayerDefinition getModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition WeepingCherubFix = partdefinition.addOrReplaceChild("WeepingCherubFix", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition LeftLeg = WeepingCherubFix.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(19, 50).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.9F, -12.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition RightLeg = WeepingCherubFix.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(19, 50).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.9F, -12.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition LeftArm = WeepingCherubFix.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(53, 53).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, -21.5F, 0.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition RightArm = WeepingCherubFix.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(50, 17).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.0F, -21.5F, 0.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition Body = WeepingCherubFix.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(33, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(29, 30).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 15.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offsetAndRotation(0.0F, -24.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition Head = WeepingCherubFix.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 17).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offsetAndRotation(0.0F, -24.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition LeftWing = WeepingCherubFix.addOrReplaceChild("LeftWing", CubeListBuilder.create().texOffs(0, 50).addBox(0.0F, -7.0F, 0.0F, 9.0F, 15.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -19.0F, 2.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition RightWing = WeepingCherubFix.addOrReplaceChild("RightWing", CubeListBuilder.create().texOffs(0, 34).addBox(-9.0F, -7.0F, 0.0F, 9.0F, 15.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -19.0F, 2.0F, 0.0F, 0.7854F, 0.0F));
        return LayerDefinition.create(meshdefinition, 67, 69);
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
    public Pair<ModelPart, Vector3d> getHeadData(HeadPlacement placement) {
        if(placement == HeadPlacement.SANTA) return null;

        if(headData == null){
            headData = new Pair<>(Head, new Vector3d(0,0,0));
        }
        return headData;
    }



    @Override
    public void setupAnim(WeepingAngel weepingAngel, float v, float v1, float v2, float v3, float v4) {
        WeepingAngelPose pose = weepingAngelPose;
        if (weepingAngel != null) {
            pose = WeepingAngelPose.getPose(weepingAngel.getAngelPose());
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
        WeepingCherubFix.render(matrixStack, buffer, packedLight, showHurt ? packedOverlay : OverlayTexture.NO_OVERLAY);
        matrixStack.popPose();
    }

    @Override
    public Iterable<ModelPart> parts() {
        return ImmutableList.of(this.Body, this.LeftWing, this.RightWing, this.Head, this.LeftArm, this.RightArm, this.LeftLeg, this.RightLeg, this.WeepingCherubFix);
    }

    @Override
    public ResourceLocation getTextureForPose(Object angel, WeepingAngelPose pose) {
        if (angel instanceof WeepingAngel weepingAngel) {
            return generateTex(pose, weepingAngel.getVariant());
        }

        if (angel instanceof StatueBlockEntity weepingAngelEntity) {
            return generateTex(weepingAngelEntity.getPose(), weepingAngelEntity.getAngelVarients());
        }

        if (angel instanceof PlinthBlockEntity weepingAngelEntity) {
            return generateTex(weepingAngelEntity.getPose(), weepingAngelEntity.getVariant());
        }

        return generateTex(WeepingAngelPose.APPROACH, AngelTypes.NORMAL.get());
    }

    @Override
    public boolean toggleHurt(boolean hurtShow) {
        return showHurt;
    }

    @Override
    public ResourceLocation generateTex(WeepingAngelPose pose, AbstractVariant angelVariants) {
        String location = "textures/entities/cherub/angel_cherub_";
        WeepingAngelPose.Emotion emotion = pose.getEmotion();
        String suffix = emotion.name().toLowerCase();
        return new ResourceLocation(WeepingAngels.MODID, location + suffix + ".png");
    }

	@Override
	public Iterable<ModelPart> wings(PoseStack pose) {
		return new ArrayList();
	}

}
