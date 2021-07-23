package me.suff.mc.angels.client.renders.entities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import me.suff.mc.angels.client.models.entity.IAngelModel;
import me.suff.mc.angels.client.models.entity.ModelAngelaAngel;
import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.entities.AngelEnums;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import me.suff.mc.angels.utils.ClientUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class AngelRender extends MobRenderer<WeepingAngelEntity, EntityModel<WeepingAngelEntity>> implements EntityRendererProvider<WeepingAngelEntity> {
    public AngelRender(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, ClientUtil.getModelForAngel(AngelEnums.AngelType.ANGELA_MC), 1);
        addLayer(new AngelCrackedLayer(this));
    }

    @Override
    protected float getWhiteOverlayProgress(WeepingAngelEntity livingEntityIn, float partialTicks) {
        return 0;
    }

    @Override
    public void render(WeepingAngelEntity weepingAngelEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStackIn, MultiBufferSource pBufferIn, int pPackedLightIn) {
        model = ClientUtil.getModelForAngel(weepingAngelEntity.getAngelType());

        ItemStack key = weepingAngelEntity.getMainHandItem();
        pMatrixStackIn.pushPose();
        float offset = Mth.cos(weepingAngelEntity.tickCount * 0.1F) * -0.09F;
        pMatrixStackIn.scale(0.5F, 0.5F, 0.5F);
        pMatrixStackIn.translate(0, 5, 0);
        pMatrixStackIn.translate(0, offset, 0);
        pMatrixStackIn.mulPose(Vector3f.YP.rotation(weepingAngelEntity.level.getGameTime() / 20F));
        renderItem(weepingAngelEntity, key, ItemTransforms.TransformType.FIXED, false, pMatrixStackIn, pBufferIn, pPackedLightIn);
        pMatrixStackIn.popPose();

        pMatrixStackIn.pushPose();

        /*
        //TODO This messes up Optifine Shaders, not 100% sure if their fault or mine
        if (calcOverlay(weepingAngelEntity.getHealth()) != -1) {
            MatrixStack.Entry matrixEntry = pMatrixStackIn.last();
            IVertexBuilder ivertexbuilder = new MatrixApplyingVertexBuilder(Minecraft.getInstance().levelRenderer.renderBuffers.crumblingBufferSource().getBuffer(ModelBakery.DESTROY_TYPES.get(calcOverlay(weepingAngelEntity.getHealth()))), matrixEntry.pose(), matrixEntry.normal());
            IRenderTypeBuffer finalIRenderTypeBuffer = pBufferIn;
            pBufferIn = (renderType) -> {
                IVertexBuilder vertexBuilder = finalIRenderTypeBuffer.getBuffer(renderType);
                return renderType.affectsCrumbling() ? VertexBuilderUtils.create(ivertexbuilder, vertexBuilder) : vertexBuilder;
            };
        }*/
        super.render(weepingAngelEntity, pEntityYaw, pPartialTicks, pMatrixStackIn, pBufferIn, pPackedLightIn);
        pMatrixStackIn.popPose();
    }

    @Override
    protected float getFlipDegrees(WeepingAngelEntity entityLivingBaseIn) {
        return 90;
    }

    @Override
    protected void setupRotations(WeepingAngelEntity entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
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
    public ResourceLocation getTextureLocation(WeepingAngelEntity weepingAngelEntity) {
        IAngelModel iAngelModel = (IAngelModel) model;
        return iAngelModel.getTextureForPose(weepingAngelEntity, WeepingAngelPose.getPose(weepingAngelEntity.getAngelPose()));
    }

    private void renderItem(LivingEntity livingEntityIn, ItemStack itemStackIn, ItemTransforms.TransformType transformTypeIn, boolean leftHand, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn) {
        if (!itemStackIn.isEmpty()) {
            Minecraft.getInstance().getItemInHandRenderer().renderItem(livingEntityIn, itemStackIn, transformTypeIn, leftHand, matrixStackIn, bufferIn, combinedLightIn);
        }
    }

    @Override
    public EntityRenderer<WeepingAngelEntity> create(Context context) {
        return new AngelRender(context);
    }
}
