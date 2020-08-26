package me.swirtzly.minecraft.angels.client.renders.tileentities;

import com.mojang.blaze3d.matrix.MatrixStack;
import me.swirtzly.minecraft.angels.client.models.tile.ModelCG;
import me.swirtzly.minecraft.angels.common.misc.WAConstants;
import me.swirtzly.minecraft.angels.common.tileentities.ChronodyneGeneratorTile;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.math.vector.Vector3f;

public class CGTileRender extends TileEntityRenderer<ChronodyneGeneratorTile> {

    private ModelCG model = new ModelCG();

    public CGTileRender(TileEntityRendererDispatcher rendererDispatcher) {
        super(rendererDispatcher);
    }


    @Override
    public void render(ChronodyneGeneratorTile chronodyneGeneratorTile, float v, MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int i, int i1) {
        matrixStack.push();
        if (chronodyneGeneratorTile.getTileData().contains(WAConstants.ABS_X) && chronodyneGeneratorTile.getTileData().contains(WAConstants.ABS_Z)) {
            double presX = chronodyneGeneratorTile.getPos().getX() - chronodyneGeneratorTile.getTileData().getDouble(WAConstants.ABS_X);
            double presZ = chronodyneGeneratorTile.getPos().getZ() - chronodyneGeneratorTile.getTileData().getDouble(WAConstants.ABS_Z);
            matrixStack.translate(presX, 0, presZ);
        }
        matrixStack.rotate(Vector3f.ZP.rotation(180));
        model.render(matrixStack, iRenderTypeBuffer.getBuffer(RenderType.getSolid()), i, i1, 1, 1, 1, 1);
        matrixStack.pop();
    }
}
