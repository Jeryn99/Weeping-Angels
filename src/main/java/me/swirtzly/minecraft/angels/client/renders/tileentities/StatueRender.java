package me.swirtzly.minecraft.angels.client.renders.tileentities;

import com.mojang.blaze3d.matrix.MatrixStack;
import me.swirtzly.minecraft.angels.WeepingAngels;
import me.swirtzly.minecraft.angels.client.models.entity.ModelAngelEd;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import me.swirtzly.minecraft.angels.common.tileentities.StatueTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

/**
 * Created by Swirtzly on 17/02/2020 @ 12:18
 */
public class StatueRender extends TileEntityRenderer<StatueTile> {
	
	private ModelAngelEd<WeepingAngelEntity> ed = new ModelAngelEd<WeepingAngelEntity>();
	private ResourceLocation ARM_TEX = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_2.png");

	public StatueRender() {
		super(TileEntityRendererDispatcher.instance);
	}

	@Override
	public void render(StatueTile statueTile, float v, MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int i, int i1) {
		matrixStack.push();
		matrixStack.rotate(Vector3f.ZP.rotation(180));
		matrixStack.rotate(Vector3f.YP.rotation(statueTile.getRotation()));
		Minecraft.getInstance().getTextureManager().bindTexture(ARM_TEX);
		ed.quickRender(0.0625f, statueTile.getPose());
		matrixStack.pop();
	}
}
