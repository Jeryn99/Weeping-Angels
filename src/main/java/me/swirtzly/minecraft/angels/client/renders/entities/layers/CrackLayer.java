package me.swirtzly.minecraft.angels.client.renders.entities.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import me.swirtzly.minecraft.angels.WeepingAngels;
import me.swirtzly.minecraft.angels.client.models.entity.ModelAngel;
import me.swirtzly.minecraft.angels.client.models.entity.ModelAngelChild;
import me.swirtzly.minecraft.angels.client.models.entity.ModelAngelEd;
import me.swirtzly.minecraft.angels.client.models.entity.ModelAngelMel;
import me.swirtzly.minecraft.angels.client.models.entity.ModelAngela;
import me.swirtzly.minecraft.angels.client.models.entity.ModelClassicAngel;
import me.swirtzly.minecraft.angels.client.renders.entities.AngelRender;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;

public class CrackLayer extends LayerRenderer<WeepingAngelEntity, EntityModel<WeepingAngelEntity>> {
	
	private static final ResourceLocation CRACK_TEX = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_crack.png");
	private static final ResourceLocation CRACK_TEX_2 = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_2_cracked.png");
	private final AngelRender angelRenderer;
	
	private EntityModel<WeepingAngelEntity> modelOne = new ModelAngel<WeepingAngelEntity>();
	private EntityModel<WeepingAngelEntity> modelTwo = new ModelAngelEd<WeepingAngelEntity>();
	private EntityModel<WeepingAngelEntity> modelChild = new ModelAngelChild<WeepingAngelEntity>();
	private EntityModel<WeepingAngelEntity> modelClassic = new ModelClassicAngel<WeepingAngelEntity>();
	private EntityModel<WeepingAngelEntity> modelMel = new ModelAngelMel<WeepingAngelEntity>();
	private EntityModel<WeepingAngelEntity> modelAngela = new ModelAngela<WeepingAngelEntity>();
	private EntityModel<WeepingAngelEntity> modelMain = modelMel;
	
	@SuppressWarnings("unchecked")
	public CrackLayer(AngelRender angelRendererIn) {
		super(angelRendererIn);
		angelRenderer = angelRendererIn;
	}

	@Override
	public void render(MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int i, WeepingAngelEntity weepingAngelEntity, float v, float v1, float v2, float v3, float v4, float v5) {
		switch (angel.getAngelType()) {
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
			case 4:
				modelMain = modelAngela;
				break;
		}

		if (angel.getHealth() <= 5 && angel.getHealth() > 0 || angel.hurtTime > 0) {
			if (!angel.isChild()) {
				if (angel.getAngelType() == 1) {
					angelRenderer.bindTexture(CRACK_TEX_2);
				} else {
					angelRenderer.bindTexture(CRACK_TEX);
				}
				modelMain.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
			}
		}
	}
}
