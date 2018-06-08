package me.sub.angels.client.render.entity;

import me.sub.angels.client.models.entity.ModelCG;
import me.sub.angels.common.WAObjects.WAItems;
import me.sub.angels.common.entities.EntityChronodyneGenerator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderSnowball;

public class RenderCG extends RenderSnowball<EntityChronodyneGenerator> {
	
	public RenderCG() {
		super(Minecraft.getMinecraft().getRenderManager(), WAItems.chronodyneGenerator, null);
	}
	
	private ModelCG model = new ModelCG();
	
	@Override
	public void doRender(EntityChronodyneGenerator entity, double x, double y, double z, float par8, float par9) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y - 1.3, z);
		GlStateManager.enableRescaleNormal();
		// GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		// GlStateManager.rotate((float) (this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		GlStateManager.disableFog();
		GlStateManager.disableLighting();
		model.render(entity, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.enableFog();
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
	}
	
}
