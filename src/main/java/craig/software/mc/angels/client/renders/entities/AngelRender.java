package craig.software.mc.angels.client.renders.entities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import craig.software.mc.angels.client.models.entity.IAngelModel;
import craig.software.mc.angels.client.poses.WeepingAngelPose;
import craig.software.mc.angels.common.entities.WeepingAngel;
import craig.software.mc.angels.common.entities.WeepingAngelTypes;
import craig.software.mc.angels.utils.ClientUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class AngelRender extends MobRenderer<WeepingAngel, EntityModel<WeepingAngel>> implements EntityRendererProvider<WeepingAngel> {
    public AngelRender(EntityRendererProvider.Context context) {
        super(context, ClientUtil.getModelForAngel(WeepingAngelTypes.DISASTER_MC), 0.5F);
        addLayer(new AngelCrackedLayer(this));
        addLayer(new AngelHeldLayer(this));
        addLayer(new SeasonalLayer(this));
    }

    @Override
    protected float getWhiteOverlayProgress(@NotNull WeepingAngel livingEntityIn, float partialTicks) {
        return 0;
    }


    @Override
    public void render(WeepingAngel weepingAngel, float pEntityYaw, float pPartialTicks, @NotNull PoseStack pMatrixStackIn, @NotNull MultiBufferSource pBufferIn, int pPackedLightIn) {
        model = ClientUtil.getModelForAngel(weepingAngel.getAngelType());

        if (model instanceof IAngelModel iAngelModel) {
            iAngelModel.toggleHurt(false);
        }

        if (!weepingAngel.getAngelType().canHoldThings()) {
            ItemStack key = weepingAngel.getMainHandItem();
            pMatrixStackIn.pushPose();
            float offset = Mth.cos(weepingAngel.tickCount * 0.1F) * -0.09F;
            pMatrixStackIn.scale(0.5F, 0.5F, 0.5F);
            pMatrixStackIn.translate(0, 5, 0);
            pMatrixStackIn.translate(0, offset, 0);
            pMatrixStackIn.mulPose(Vector3f.YP.rotation(weepingAngel.level.getGameTime() / 20F));
            renderItem(weepingAngel, key, ItemTransforms.TransformType.FIXED, false, pMatrixStackIn, pBufferIn, pPackedLightIn);
            pMatrixStackIn.popPose();
        }

        pMatrixStackIn.pushPose();
        super.render(weepingAngel, pEntityYaw, pPartialTicks, pMatrixStackIn, pBufferIn, pPackedLightIn);
        pMatrixStackIn.popPose();

    }


    @Override
    protected float getFlipDegrees(@NotNull WeepingAngel entityLivingBaseIn) {
        return 90;
    }

    @Override
    protected void setupRotations(WeepingAngel entityLiving, @NotNull PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        if (entityLiving.deathTime > 0) {
            float deathRotation = ((float) entityLiving.deathTime + partialTicks - 1.0F) / 20.0F * 1.6F;
            deathRotation = Mth.sqrt(deathRotation);
            if (deathRotation > 1.0F) {
                deathRotation = 1.0F;
            }
            matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(deathRotation * this.getFlipDegrees(entityLiving)));
            return;
        }

        super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull WeepingAngel weepingAngel) {
        IAngelModel iAngelModel = (IAngelModel) model;
        return iAngelModel.getTextureForPose(weepingAngel, WeepingAngelPose.getPose(weepingAngel.getAngelPose()));
    }

    private void renderItem(LivingEntity livingEntityIn, ItemStack itemStackIn, ItemTransforms.TransformType transformTypeIn, boolean leftHand, PoseStack poseStack, MultiBufferSource bufferIn, int combinedLightIn) {
        if (!itemStackIn.isEmpty()) {
            Minecraft.getInstance().getItemRenderer().renderStatic(itemStackIn, ItemTransforms.TransformType.FIXED, combinedLightIn, OverlayTexture.NO_OVERLAY, poseStack, bufferIn, livingEntityIn.getId());
        }
    }

    @Override
    public @NotNull EntityRenderer<WeepingAngel> create(@NotNull Context context) {
        return new AngelRender(context);
    }
}
