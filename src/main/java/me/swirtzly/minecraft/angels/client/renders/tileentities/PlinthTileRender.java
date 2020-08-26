package me.swirtzly.minecraft.angels.client.renders.tileentities;

import com.mojang.blaze3d.matrix.MatrixStack;
import me.swirtzly.minecraft.angels.WeepingAngels;
import me.swirtzly.minecraft.angels.client.models.entity.ModelAngelEd;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import me.swirtzly.minecraft.angels.common.tileentities.PlinthTile;
import me.swirtzly.minecraft.angels.common.tileentities.StatueTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class PlinthTileRender extends TileEntityRenderer<PlinthTile> {
	
	private ModelAngelEd<WeepingAngelEntity> ed = new ModelAngelEd<WeepingAngelEntity>();
	private ResourceLocation ARM_TEX = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_2.png");

	public PlinthTileRender(TileEntityRendererDispatcher tileEntityRendererDispatcher) {
		super(tileEntityRendererDispatcher);
	}

	@Override
	public void render(PlinthTile plinthTile, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		matrixStack.push();
		matrixStack.translate(0.5F, 2.5F, 0.5F);
		matrixStack.rotate(Vector3f.ZP.rotationDegrees(180F));
		matrixStack.rotate(Vector3f.YP.rotation(plinthTile.getRotation()));
		this.ed.quickRender(matrixStack, bufferIn.getBuffer(RenderType.getEntityCutout(ARM_TEX)), combinedLightIn, combinedOverlayIn, plinthTile.getPose());
		matrixStack.pop();
	}
}
