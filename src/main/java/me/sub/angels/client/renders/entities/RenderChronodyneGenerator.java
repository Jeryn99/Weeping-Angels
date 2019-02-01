package me.sub.angels.client.renders.entities;

import com.mojang.blaze3d.platform.GlStateManager;
import me.sub.angels.client.models.entity.ModelCG;
import me.sub.angels.common.entities.EntityChronodyneGenerator;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.util.Identifier;

public class RenderChronodyneGenerator extends EntityRenderer<EntityChronodyneGenerator>
{

	private ModelCG model = new ModelCG();

	public RenderChronodyneGenerator(EntityRenderDispatcher entityRenderDispatcher_1)
	{
		super(entityRenderDispatcher_1);
	}

	@Override protected Identifier getTexture(EntityChronodyneGenerator entityChronodyneGenerator)
	{
		return null;
	}

	@Override
	public void render(EntityChronodyneGenerator entity, double x, double y, double z, float par8, float par9) {
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
