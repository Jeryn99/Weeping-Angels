package me.swirtzly.angels.client.renders.tileentities;

import me.swirtzly.angels.client.models.entity.ModelCG;
import me.swirtzly.angels.common.misc.WAConstants;
import me.swirtzly.angels.common.tileentities.TileEntityChronodyneGenerator;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderTileEntityCG extends TileEntitySpecialRenderer<TileEntityChronodyneGenerator> {

    private ModelCG model = new ModelCG();

    @Override
    public void render(TileEntityChronodyneGenerator tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        if (tile.getTileData().hasKey(WAConstants.ABS_X) && tile.getTileData().hasKey(WAConstants.ABS_Z)) {
            double presX = tile.getPos().getX() - tile.getTileData().getDouble(WAConstants.ABS_X);
            double presZ = tile.getPos().getZ() - tile.getTileData().getDouble(WAConstants.ABS_Z);
            GlStateManager.translate(presX, 0, presZ);
        }
        GlStateManager.rotate(180, 0.0F, 0.0F, 1.0F);
        model.render(null, 0, 0, 0, 0, 0, 0.0625F);
        GlStateManager.popMatrix();
    }
}
