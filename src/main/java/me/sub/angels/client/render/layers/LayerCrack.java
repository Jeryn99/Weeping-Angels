package me.sub.angels.client.render.layers;

import me.sub.angels.client.models.entity.ModelAngel;
import me.sub.angels.client.models.entity.ModelAngelEd;
import me.sub.angels.client.render.entity.RenderAngel;
import me.sub.angels.common.entities.EntityAngel;
import me.sub.angels.main.WeepingAngels;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerCrack implements LayerRenderer<EntityAngel> {
	
	private static final ResourceLocation CRACK_TEX = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_crack.png");
	private static final ResourceLocation CRACK_TEX_2 = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_2_cracked.png");
	private final RenderAngel angelRenderer;

    private final ModelBase modelOne = new ModelAngel();
    private final ModelBase modelTwo = new ModelAngelEd();
    private ModelBase modelMain = modelTwo;
	
	public LayerCrack(RenderAngel angelRendererIn) {
		this.angelRenderer = angelRendererIn;
	}
	
	@Override
	public void doRenderLayer(EntityAngel angel, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		
		if (angel.getType() == 0) {
			modelMain = modelOne;
		}
		
		if (angel.getType() == 1) {
			modelMain = modelTwo;
		}
		
		if (angel.getHealth() <= 5 && angel.getHealth() > 0 || angel.hurtTime > 0) {
			GlStateManager.pushMatrix();
			
			boolean flag = angel.isInvisible();
			GlStateManager.depthMask(!flag);
			if (!angel.isChild()) {
				if (angel.getType() == 1) {
                    angelRenderer.bindTexture(CRACK_TEX_2);
				} else {
                    angelRenderer.bindTexture(CRACK_TEX);
				}

                modelMain.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
			}
			GlStateManager.popMatrix();
		}
	}
	
	@Override
	public boolean shouldCombineTextures() {
		return false;
	}
}
