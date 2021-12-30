package me.suff.mc.angels.client.models.block.compat;

import com.mojang.blaze3d.matrix.MatrixStack;
import me.suff.mc.angels.client.renders.tileentities.CoffinRenderer;
import me.suff.mc.angels.common.tileentities.CoffinTile;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.tardis.mod.client.TRenderTypes;
import net.tardis.mod.client.renderers.exteriors.ExteriorRenderer;

public class RenderTardisExterior extends ExteriorRenderer<PTBExterior> {

    TardisExterior MODEL = new TardisExterior();

    public RenderTardisExterior(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void renderExterior(PTBExterior tile, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn, float alpha) {
        MODEL.render(tile, 0.25F, matrixStackIn, bufferIn.getBuffer(TRenderTypes.getTardis(CoffinRenderer.getTexture(CoffinTile.Coffin.PTB))), combinedLightIn, combinedOverlayIn, alpha);
    }

}
