package mc.craig.software.angels.client.renders.entities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import mc.craig.software.angels.common.entities.WeepingAngel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AngelHeldLayer<T extends WeepingAngel, M extends EntityModel<T> & ArmedModel> extends RenderLayer<T, M> {
    public AngelHeldLayer(RenderLayerParent<T, M> p_117183_) {
        super(p_117183_);
    }

    @Override
    public void render(PoseStack pose, MultiBufferSource p_117205_, int p_117206_, WeepingAngel weepingAngel, float p_117208_, float p_117209_, float p_117210_, float p_117211_, float p_117212_, float p_117213_) {

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

    protected void renderArmWithItem(LivingEntity p_117185_, ItemStack p_117186_, TransformType p_117187_, HumanoidArm p_117188_, PoseStack p_117189_, MultiBufferSource p_117190_, int p_117191_) {
        if (!p_117186_.isEmpty()) {
            p_117189_.pushPose();
            this.getParentModel().translateToHand(p_117188_, p_117189_);
            p_117189_.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
            p_117189_.mulPose(Vector3f.YP.rotationDegrees(180.0F));
            boolean var8 = p_117188_ == HumanoidArm.LEFT;
            p_117189_.translate((float) (var8 ? -1 : 1) / 16.0F, 0.125D, -0.625D);
            Minecraft.getInstance().getItemInHandRenderer().renderItem(p_117185_, p_117186_, p_117187_, var8, p_117189_, p_117190_, p_117191_);
            p_117189_.popPose();
        }
    }

    @Override
    public M getParentModel() {
        return super.getParentModel();
    }
}
