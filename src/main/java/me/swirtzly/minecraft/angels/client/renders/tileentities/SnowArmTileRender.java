package me.swirtzly.minecraft.angels.client.renders.tileentities;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.matrix.matrixStack;
import com.mojang.blaze3d.platform.matrixStack;

import me.swirtzly.minecraft.angels.client.models.block.SnowArmModel;
import me.swirtzly.minecraft.angels.common.tileentities.SnowArmTile;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;

public class SnowArmTileRender extends TileEntityRenderer<SnowArmTile> {
	
	private SnowArmModel arm = new SnowArmModel();

	public SnowArmTileRender(TileEntityRendererDispatcher p_i226006_1_) {
		super(p_i226006_1_);
	}

	@Override
	public void render(SnowArmTile snowArmTile, float v, MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int i, int i1) {
		matrixStack.push();
		matrixStack.rotate(180, 0.0F, 0.0F, 1.0F);
		arm.render(0.0625f);
		matrixStack.pop();
	}
}
