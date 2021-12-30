package me.suff.mc.angels.client.renders.tileentities;

import com.mojang.blaze3d.matrix.MatrixStack;
import me.suff.mc.angels.client.models.block.exteriors.AbPropModel;
import me.suff.mc.angels.common.tileentities.AbPropTile;
import me.suff.mc.angels.common.tileentities.CoffinTile;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.client.TRenderTypes;
import net.tardis.mod.client.renderers.exteriors.ExteriorRenderer;
import net.tardis.mod.misc.WorldText;

public class AbPropRender extends ExteriorRenderer<AbPropTile> {
    public static final ResourceLocation TEXTURE = CoffinRenderer.getTexture(CoffinTile.Coffin.PTB);
    public static AbPropModel MODEL = new AbPropModel();

    public AbPropRender(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void renderExterior(AbPropTile tile, float v, MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int i, int i1, float v1) {
        matrixStack.pushPose();
        matrixStack.translate(0, -1.3, 0);
        matrixStack.scale(1.2f, 1.2f, 1.2f);
        ResourceLocation texture = tile.getVariant() != null ? tile.getVariant().getTexture() : TEXTURE;
        MODEL.render(tile, 0.25F, matrixStack, iRenderTypeBuffer.getBuffer(TRenderTypes.getTardis(texture)), i, i1, v1);
        matrixStack.popPose();
    }
}