package me.suff.mc.angels.client.models.entity;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3d;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.entities.WeepingAngel;
import me.suff.mc.angels.common.variants.AbstractVariant;
import me.suff.mc.angels.utils.Pair;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;

public class ModelAplan extends PlayerModel<WeepingAngel> implements IAngelModel {

    public static final ResourceLocation ANGRY = new ResourceLocation(WeepingAngels.MODID, "textures/entities/disaster/dying/normal/normal_dying_angel_angry.png");
    public static final ResourceLocation IDLE = new ResourceLocation(WeepingAngels.MODID, "textures/entities/disaster/dying/normal/normal_dying_angel_idle.png");
    public static final ResourceLocation SCREAM = new ResourceLocation(WeepingAngels.MODID, "textures/entities/disaster/dying/normal/normal_dying_angel_scream.png");
    private WeepingAngelPose weepingAngelPose = WeepingAngelPose.ANGRY;
    private Pair<ModelPart, Vector3d> headData;


    public ModelAplan(ModelPart p_170821_, boolean p_170822_) {
        super(p_170821_, p_170822_);
        young = false;
    }

    @Override
    public void setupAnim(WeepingAngel weepingAngel, float p_102867_, float p_102868_, float p_102869_, float p_102870_, float p_102871_) {

        young = false;

        WeepingAngelPose pose = weepingAngelPose;
        if (weepingAngel != null) {
            pose = WeepingAngelPose.getPose(weepingAngel.getAngelPose());
        }

        head.xRot = (float) Math.toRadians(0);
        head.yRot = (float) Math.toRadians(0);
        head.zRot = (float) Math.toRadians(0);

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
            hat.copyFrom(head);
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
            hat.copyFrom(head);
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
            hat.copyFrom(head);
            return;
        }

        if (pose == WeepingAngelPose.APPROACH) {
            rightArm.xRot = -1.04533F;
            rightArm.yRot = -0.55851F;
            rightArm.zRot = 0.0F;
            leftArm.xRot = -1.04533F;
            leftArm.yRot = 0.55851F;
            leftArm.zRot = 0.0F;
            hat.copyFrom(head);
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
            hat.copyFrom(head);
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
            hat.copyFrom(head);
            return;
        }
    }


    @Override
    public boolean toggleHurt(boolean hurtShow) {
        return false;
    }

    @Override
    public ResourceLocation generateTex(WeepingAngelPose pose, AbstractVariant angelVariants) {
        return getTextureForPose(null, pose);
    }

    @Override
    public ResourceLocation getTextureForPose(Object angel, WeepingAngelPose pose) {
        return switch (pose.getEmotion()) {
            case ANGRY -> ANGRY;
            case IDLE -> IDLE;
            case SCREAM -> SCREAM;
        };
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
        if(headData == null) {
            headData = new Pair<>(head, new Vector3d(0, 0, 0));
        }
        return headData;
    }

    @Override
	public Iterable<ModelPart> wings(PoseStack pose) {
        return new ArrayList<>();
    }
}
