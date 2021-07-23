package me.suff.mc.angels.client.renders.tileentities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import me.suff.mc.angels.client.models.entity.IAngelModel;
import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.blocks.StatueBlock;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import me.suff.mc.angels.common.tileentities.StatueTile;
import me.suff.mc.angels.utils.ClientUtil;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Created by Craig on 17/02/2020 @ 12:18
 */
public class StatueRender implements BlockEntityRenderer<StatueTile>, BlockEntityRendererProvider<StatueTile> {

    public StatueRender(Context p_173571_) {
    }

    @Override
    public void render(StatueTile statueTile, float partialTicks, PoseStack matrixStack, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStack.pushPose();
        matrixStack.translate(0.5F, 1.5F, 0.5F);
        matrixStack.mulPose(Vector3f.ZP.rotationDegrees(180F));
        BlockState blockstate = statueTile.getBlockState();
        float rotation = 22.5F * (float) blockstate.getValue(StatueBlock.ROTATION);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(rotation));
        EntityModel<WeepingAngelEntity> angel = ClientUtil.getModelForAngel(statueTile.getAngelType());
        ResourceLocation texture = DefaultPlayerSkin.getDefaultSkin();

        WeepingAngelPose pose = statueTile.getPose();
        if (angel instanceof IAngelModel) {
            IAngelModel angelModel = (IAngelModel) angel;
            angelModel.setAngelPose(pose);
            texture = angelModel.getTextureForPose(statueTile, pose);
        }
        angel.setupAnim(null, 0, 0, 0, 0, 0);
        angel.renderToBuffer(matrixStack, bufferIn.getBuffer(RenderType.entityCutout(texture)), combinedLightIn, combinedOverlayIn, 1, 1, 1, 1);
        matrixStack.popPose();
    }

    @Override
    public BlockEntityRenderer<StatueTile> create(Context p_173571_) {
        return new StatueRender(p_173571_);
    }
}
