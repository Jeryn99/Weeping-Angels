package me.sub.angels.client.renders.entities;

import com.mojang.blaze3d.platform.GlStateManager;
import me.sub.angels.WeepingAngels;
import me.sub.angels.client.models.entity.ModelAngel;
import me.sub.angels.client.models.entity.ModelAngelChild;
import me.sub.angels.client.models.entity.ModelAngelEd;
import me.sub.angels.client.renders.entities.layers.LayerCrack;
import me.sub.angels.common.entities.EntityWeepingAngel;
import me.sub.angels.common.misc.AngelEnums;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.Identifier;


public class RenderWeepingAngel extends LivingEntityRenderer<EntityWeepingAngel, EntityModel<EntityWeepingAngel>>
{

	private Identifier TEXTURE_ONE = new Identifier(WeepingAngels.MODID, "textures/entities/angel.png");
	private Identifier TEXTURE_TWO = new Identifier(WeepingAngels.MODID, "textures/entities/angel_2.png");
	private Identifier TEXTURE_CHILD = new Identifier(WeepingAngels.MODID, "textures/entities/angel_child.png");

	private EntityModel<EntityWeepingAngel> modelOne = new ModelAngel();
	private EntityModel<EntityWeepingAngel> modelTwo = new ModelAngelEd();
	private ModelAngelChild modelChild = new ModelAngelChild();

	public RenderWeepingAngel(EntityRenderDispatcher entityRenderDispatcher_1) {
		super(entityRenderDispatcher_1, new ModelAngelEd(), 0.0F);
		this.model = modelTwo;
		addFeature(new LayerCrack(this));
		//TODO
//		addFeature(new HeldItemFeatureRenderer<EntityWeepingAngel, ? extends EntityModel<En>>())
	}

	@Override protected Identifier getTexture(EntityWeepingAngel angel)
	{
		return null;
	}

	private static void bindAngelTex(Identifier loc) {
		MinecraftClient.getInstance().getTextureManager().bindTexture(loc);
	}

	/**
	 * Renders the model in RenderLiving
	 */
	@Override
	protected void render(EntityWeepingAngel angel, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		GlStateManager.pushMatrix();
		//TODO
//		RenderHelper.enableStandardItemLighting();
		if (angel.getHealth() > 0.0F) {

			if (angel.isChild()) {
				bindAngelTex(TEXTURE_CHILD);
				modelChild.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
			} else {

				if (angel.getAngelType() == AngelEnums.AngelType.ANGEL_ONE.getId()) {
					bindAngelTex(TEXTURE_ONE);
					modelOne.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
				}

				if (angel.getAngelType() == AngelEnums.AngelType.ANGEL_TWO.getId()) {
					bindAngelTex(TEXTURE_TWO);
					modelTwo.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
				}
			}
		}
		//TODO
//		RenderHelper.disableStandardItemLighting();
		GlStateManager.popMatrix();
	}
	
}
