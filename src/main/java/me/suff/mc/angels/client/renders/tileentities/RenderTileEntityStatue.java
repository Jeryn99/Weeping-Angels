package me.suff.mc.angels.client.renders.tileentities;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.client.models.poses.PoseManager;
import me.suff.mc.angels.common.tileentities.TileEntityStatue;
import me.suff.mc.angels.client.models.entity.ModelAngelEd;
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

        Minecraft.getMinecraft().renderEngine.bindTexture(ARM_TEX);
        ed.setupAngles(0, 0, 0, 0, 0, 0, PoseManager.getPoseFromString(tile.getPose()));
        ed.renderQuickly(PoseManager.getPoseFromString(tile.getPose()), 0.06125F);
        GlStateManager.popMatrix();
    }

}
