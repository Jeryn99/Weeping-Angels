package me.swirtzly.minecraft.angels.client.renders.entities;

import com.mojang.blaze3d.matrix.MatrixStack;
import me.swirtzly.minecraft.angels.client.models.tile.ModelCG;
import me.swirtzly.minecraft.angels.common.entities.ChronodyneGeneratorEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;

/**
 * Created by Swirtzly on 01/02/2020 @ 22:22
 */
public class CGRender extends SpriteRenderer<ChronodyneGeneratorEntity> {

	private ModelCG model = new ModelCG();

	public CGRender(EntityRendererManager p_i226035_1_, ItemRenderer p_i226035_2_, float p_i226035_3_, boolean p_i226035_4_) {
		super(p_i226035_1_, p_i226035_2_, p_i226035_3_, p_i226035_4_);
	}

	@Override
	public void render(ChronodyneGeneratorEntity cg, float p_225623_2_, float p_225623_3_, MatrixStack matrixStack, IRenderTypeBuffer p_225623_5_, int p_225623_6_) {
		matrixStack.push();
		matrixStack.translate(cg.getPosX(), cg.getPosY() - 1.3, cg.getPosZ());
		matrixStack.disableFog();
		matrixStack.disableLighting();
		model.render(cg, 0, 0, 0, 0, 0, 0.0625F);
		matrixStack.enableFog();
		matrixStack.enableLighting();
		matrixStack.pop();
	}

}
