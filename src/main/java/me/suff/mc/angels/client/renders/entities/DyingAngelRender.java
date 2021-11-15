package me.suff.mc.angels.client.renders.entities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import me.suff.mc.angels.client.models.entity.ModelAplan;
import me.suff.mc.angels.client.models.entity.WAModels;
import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.entities.DyingAngel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

public class DyingAngelRender extends MobRenderer<DyingAngel, ModelAplan> implements EntityRendererProvider<DyingAngel> {

    public DyingAngelRender(Context context) {
        super(context, new ModelAplan(context.bakeLayer(WAModels.DYING_ANGEL), true), 0.5F);
        addLayer(new ItemInHandLayer<>(this));
    }

    @Override
    public void render(DyingAngel p_115455_, float p_115456_, float p_115457_, PoseStack poseStack, MultiBufferSource p_115459_, int p_115460_) {
        poseStack.pushPose();
        poseStack.mulPose(Vector3f.YP.rotationDegrees(p_115455_.yBodyRot));
        super.render(p_115455_, p_115456_, p_115457_, poseStack, p_115459_, p_115460_);
        poseStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(DyingAngel p_114482_) {
        WeepingAngelPose pose = WeepingAngelPose.getPose(p_114482_.getAngelPose());
        if (pose.getEmotion() == WeepingAngelPose.Emotion.ANGRY) {
            return ModelAplan.ANGRY;
        }

        if (pose.getEmotion() == WeepingAngelPose.Emotion.IDLE) {
            return ModelAplan.IDLE;
        }

        return ModelAplan.SCREAM;
    }

    @Override
    public EntityRenderer<DyingAngel> create(Context p_174010_) {
        return new DyingAngelRender(p_174010_);
    }
}
