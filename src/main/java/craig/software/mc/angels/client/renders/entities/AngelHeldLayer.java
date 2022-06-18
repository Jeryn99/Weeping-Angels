package craig.software.mc.angels.client.renders.entities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import craig.software.mc.angels.common.entities.WeepingAngel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class AngelHeldLayer<T extends WeepingAngel, M extends EntityModel<T> & ArmedModel> extends RenderLayer<T, M> {
    public AngelHeldLayer(RenderLayerParent<T, M> p_117183_) {
        super(p_117183_);
    }

    @Override
    public void render(@NotNull PoseStack pose, @NotNull MultiBufferSource p_117205_, int p_117206_, WeepingAngel weepingAngel, float p_117208_, float p_117209_, float p_117210_, float p_117211_, float p_117212_, float p_117213_) {

        if (!weepingAngel.getAngelType().canHoldThings()) return;

        boolean var11 = weepingAngel.getMainArm() == HumanoidArm.RIGHT;
        ItemStack var12 = var11 ? weepingAngel.getOffhandItem() : weepingAngel.getMainHandItem();
        ItemStack var13 = var11 ? weepingAngel.getMainHandItem() : weepingAngel.getOffhandItem();
        if (!var12.isEmpty() || !var13.isEmpty()) {
            pose.pushPose();
            if (this.getParentModel().young) {
                pose.translate(0.0D, 0.75D, 0.0D);
                pose.scale(0.5F, 0.5F, 0.5F);
            }
            this.renderArmWithItem(weepingAngel, var13, TransformType.THIRD_PERSON_RIGHT_HAND, HumanoidArm.RIGHT, pose, p_117205_, p_117206_);
            this.renderArmWithItem(weepingAngel, var12, TransformType.THIRD_PERSON_LEFT_HAND, HumanoidArm.LEFT, pose, p_117205_, p_117206_);
            pose.popPose();
        }
    }

    protected void renderArmWithItem(LivingEntity livingEntity, ItemStack p_117186_, TransformType p_117187_, HumanoidArm p_117188_, PoseStack poseStack, MultiBufferSource multiBufferSource, int light) {
        if (!p_117186_.isEmpty()) {
            poseStack.pushPose();
            this.getParentModel().translateToHand(p_117188_, poseStack);
            poseStack.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
            poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
            boolean var8 = p_117188_ == HumanoidArm.LEFT;
            poseStack.translate((float) (var8 ? -1 : 1) / 16.0F, 0.125D, -0.625D);
            Minecraft.getInstance().getItemRenderer().renderStatic(p_117186_, ItemTransforms.TransformType.FIXED, light, OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, livingEntity.getId());
            poseStack.popPose();
        }
    }

    @Override
    public @NotNull M getParentModel() {
        return super.getParentModel();
    }
}
