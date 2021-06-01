package me.suff.mc.angels.client.renders.tileentities;

import com.mojang.blaze3d.platform.GlStateManager;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.client.models.entity.ModelAngelEd;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import me.suff.mc.angels.common.tileentities.PlinthTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.util.ResourceLocation;

public class PlinthTileRender extends TileEntityRenderer<PlinthTile> {

    private ModelAngelEd<WeepingAngelEntity> ed = new ModelAngelEd< WeepingAngelEntity >();
    private ResourceLocation ARM_TEX = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_2.png");

    @Override
    public void render(PlinthTile tile, double x, double y, double z, float partialTicks, int destroyStage) {
        GlStateManager.pushMatrix();

        GlStateManager.translatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GlStateManager.rotatef(180, 0.0F, 0.0F, 1.0F);

        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.translatef(0, -1, 0);

        GlStateManager.rotatef(tile.getRotation(), 0, 1, 0);

        if (!tile.getHasSpawned()) {
            Minecraft.getInstance().getTextureManager().bindTexture(ARM_TEX);
            ed.quickRender(0.0625f, tile.getPose());
        }

        GlStateManager.popMatrix();
    }

}
