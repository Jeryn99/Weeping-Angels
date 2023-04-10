package mc.craig.software.angels.forge.compat.tardis;

import com.mojang.blaze3d.vertex.PoseStack;
import mc.craig.software.angels.common.blockentity.CoffinBlockEntity;
import mc.craig.software.angels.forge.compat.tardis.exteriors.AbPropModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.tardis.mod.client.TRenderTypes;
import net.tardis.mod.client.renderers.exteriors.ExteriorRenderer;

public class AbPropRenderer extends ExteriorRenderer<AbPropTile> {
    public static final ResourceLocation TEXTURE = WATexVariants.getCoffinTexture(CoffinBlockEntity.Coffin.PTB);
    public AbPropModel MODEL;

    public AbPropRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn);
        this.MODEL = new AbPropModel(rendererDispatcherIn.bakeLayer(AbPropModel.LAYER_LOCATION));
    }

    @Override
    public void renderExterior(AbPropTile tile, float v, PoseStack matrixStack, MultiBufferSource bufferSource, int i, int i1, float v1) {
        matrixStack.pushPose();
        matrixStack.translate(0, -1 - 0.08, 0);
        matrixStack.scale(0.7F, 0.7F, 0.7F);
        ResourceLocation texture = tile.getVariant() != null ? tile.getVariant().getTexture() : TEXTURE;
        MODEL.render(tile, 0.25F, matrixStack, bufferSource.getBuffer(TRenderTypes.getTardis(texture)), i, i1, v1);
        matrixStack.popPose();
    }
}
