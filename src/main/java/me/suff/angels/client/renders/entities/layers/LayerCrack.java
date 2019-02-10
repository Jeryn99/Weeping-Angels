package me.suff.angels.client.renders.entities.layers;

import me.suff.angels.WeepingAngels;
import me.suff.angels.client.models.entity.ModelAngel;
import me.suff.angels.client.models.entity.ModelAngelChild;
import me.suff.angels.client.models.entity.ModelAngelEd;
import me.suff.angels.client.models.entity.ModelAngelMel;
import me.suff.angels.client.models.entity.ModelClassicAngel;
import me.suff.angels.client.renders.entities.RenderWeepingAngel;
import me.suff.angels.common.entities.EntityWeepingAngel;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerCrack implements LayerRenderer<EntityWeepingAngel> {
	
	private static final ResourceLocation CRACK_TEX = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_crack.png");
	private static final ResourceLocation CRACK_TEX_2 = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_2_cracked.png");
	private final RenderWeepingAngel angelRenderer;
	
	private ModelBase modelOne = new ModelAngel();
	private ModelBase modelTwo = new ModelAngelEd();
	private ModelBase modelChild = new ModelAngelChild();
	private ModelBase modelClassic = new ModelClassicAngel();
	private ModelBase modelMel = new ModelAngelMel();
	private ModelBase modelMain = modelMel;
	
	
	public LayerCrack(RenderWeepingAngel angelRendererIn) {
		angelRenderer = angelRendererIn;
	}
	
	@Override
	public void doRenderLayer(EntityWeepingAngel angel, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		
		switch (angel.getType()) {
			case -1:
				modelMain = modelChild;
				break;
			case 0:
				modelMain = modelOne;
				break;
			case 1:
				modelMain = modelTwo;
				break;
			case 2:
				modelMain = modelClassic;
				break;
			case 3:
				modelMain = modelMel;
				break;
		}
		
		
		if (angel.getHealth() <= 5 && angel.getHealth() > 0 || angel.hurtTime > 0) {
			GlStateManager.pushMatrix();
			
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
