package me.suff.mc.angels.compat.tardis;

import com.mojang.blaze3d.matrix.MatrixStack;

import me.suff.mc.angels.common.tileentities.CoffinTile;
import me.suff.mc.angels.compat.tardis.exteriors.AbPropModel;
import me.suff.mc.angels.utils.TextureUtil;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.client.TRenderTypes;
import net.tardis.mod.client.renderers.exteriors.ExteriorRenderer;

public class AbPropRender extends ExteriorRenderer<AbPropTile> {
    public static final ResourceLocation TEXTURE = TextureUtil.getCoffinTexture(CoffinTile.Coffin.PTB);
    public static AbPropModel MODEL = new AbPropModel();

    public AbPropRender(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void renderExterior(AbPropTile tile, float v, MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int i, int i1, float v1) {
        matrixStack.pushPose();
        matrixStack.translate(0, -1 - 0.08, 0);
        matrixStack.scale(0.7F, 0.7F, 0.7F);
        ResourceLocation texture = tile.getVariant() != null ? tile.getVariant().getTexture() : TEXTURE;
        MODEL.render(tile, 0.25F, matrixStack, iRenderTypeBuffer.getBuffer(TRenderTypes.getTardis(texture)), i, i1, v1);
        matrixStack.popPose();
    }
}