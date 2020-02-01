package me.swirtzly.angels.client.renders.entities;

import com.mojang.blaze3d.platform.GlStateManager;
import me.swirtzly.angels.client.models.entity.ModelCG;
import me.swirtzly.angels.common.entities.EntityChronodyneGenerator;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;

/**
 * Created by Swirtzly
 * on 01/02/2020 @ 22:22
 */
public class RenderCG extends SpriteRenderer<EntityChronodyneGenerator> {

    private ModelCG model = new ModelCG();

    public RenderCG(EntityRendererManager p_i50956_1_, ItemRenderer p_i50956_2_, float p_i50956_3_) {
        super(p_i50956_1_, p_i50956_2_, p_i50956_3_);
    }

    @Override
    public void doRender(EntityChronodyneGenerator entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.translated(x, y - 1.3, z);
        GlStateManager.enableRescaleNormal();
        GlStateManager.disableFog();
        GlStateManager.disableLighting();
        model.render(entity, 0, 0, 0, 0, 0, 0.0625F);
        GlStateManager.enableFog();
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
    }
}
