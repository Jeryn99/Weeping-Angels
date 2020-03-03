package me.swirtzly.angels.client.renders.tileentities;

import me.swirtzly.angels.WeepingAngels;
import me.swirtzly.angels.client.models.entity.ModelAngelEd;
import me.swirtzly.angels.common.tileentities.TileEntityStatue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderTileEntityStatue extends TileEntitySpecialRenderer<TileEntityStatue> {

    private ModelAngelEd ed = new ModelAngelEd();
    private ResourceLocation ARM_TEX = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_2.png");

    @Override
    public void render(TileEntityStatue tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GlStateManager.pushMatrix();

        GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GlStateManager.rotate(180, 0.0F, 0.0F, 1.0F);

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        GlStateManager.rotate(tile.getRotation(), 0, 1, 0);

        Minecraft.getMinecraft().getTextureManager().bindTexture(ARM_TEX);
        ed.quickRender(0.0625f, tile.getPose());

        GlStateManager.popMatrix();
    }

}
