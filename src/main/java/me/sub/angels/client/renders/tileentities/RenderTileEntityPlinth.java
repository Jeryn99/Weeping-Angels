package me.sub.angels.client.renders.tileentities;

import com.mojang.blaze3d.platform.GlStateManager;
import me.sub.angels.WeepingAngels;
import me.sub.angels.client.models.entity.ModelAngelEd;
import me.sub.angels.common.tileentities.TileEntityPlinth;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.util.Identifier;

public class RenderTileEntityPlinth extends BlockEntityRenderer<TileEntityPlinth>
{

    private ModelAngelEd ed = new ModelAngelEd();
    private Identifier ARM_TEX = new Identifier(WeepingAngels.MODID, "textures/entities/angel_2.png");

    @Override
    public void render(TileEntityPlinth tile, double x, double y, double z, float partialTicks, int destroyStage) {
        GlStateManager.pushMatrix();
        GlStateManager.translatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GlStateManager.rotatef(180, 0.0F, 0.0F, 1.0F);

        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.translated(0, -1, 0);

        GlStateManager.rotated(-tile.getRotation(), 0, 1, 0);

        if (!tile.getHasSpawned()) {

            // GlStateManager.enableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);
            // GlStateManager.color(0.2f, 0.2f, 1, 0.5f);

            // if (getWorld().rand.nextInt(5) == 1) {
            // GlStateManager.translate(0, this.getWorld().rand.nextInt(3) / 100.0f, 0);
            // GlStateManager.translate(this.getWorld().rand.nextInt(3) / 100.0f, 0, 0);
            // }
            // if (getWorld().rand.nextInt(10) == 1) {
            // GlStateManager.scale(1, 1 + this.getWorld().rand.nextInt(5) / 100.0f, 1);
            // }

            MinecraftClient.getInstance().getTextureManager().bindTexture(ARM_TEX);
            ed.quickRender(0.0625f, tile);
            //TODO
//            GlStateManager.disableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);
        }

        GlStateManager.popMatrix();
    }
	
}
