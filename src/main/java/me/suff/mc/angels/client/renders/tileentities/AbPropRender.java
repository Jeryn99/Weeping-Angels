package me.suff.mc.angels.client.renders.tileentities;

import com.mojang.blaze3d.matrix.MatrixStack;
import me.suff.mc.angels.client.models.block.AbPropModel;
import me.suff.mc.angels.common.tileentities.AbPropTile;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.client.TRenderTypes;
import net.tardis.mod.client.renderers.exteriors.ExteriorRenderer;
import net.tardis.mod.misc.WorldText;

public class AbPropRender extends ExteriorRenderer<AbPropTile> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("weeping_angels", "textures/exteriors/abprop.png");
    public static AbPropModel MODEL = new AbPropModel();
    public static WorldText TEXT = new WorldText(0.87F, 0.125F, 0.015F, 0x000000);

    public AbPropRender(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void renderExterior(AbPropTile tile, float v, MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int i, int i1, float v1) {
        matrixStack.pushPose();
        matrixStack.translate(0, -1.3, 0);
        matrixStack.scale(1.2f,1.2f,1.2f);
        MODEL.render(tile, 0.25F, matrixStack, iRenderTypeBuffer.getBuffer(TRenderTypes.getTardis(TEXTURE)), i, i1, v1);
        matrixStack.popPose();
    }
}