package me.swirtzly.minecraft.angels.client.renders.tileentities;

import com.mojang.blaze3d.matrix.MatrixStack;
import me.swirtzly.minecraft.angels.WeepingAngels;
import me.swirtzly.minecraft.angels.client.models.block.CoffinModel;
import me.swirtzly.minecraft.angels.common.blocks.CoffinBlock;
import me.swirtzly.minecraft.angels.common.tileentities.CoffinTile;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.block.SkullBlock;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class CoffinRenderer extends TileEntityRenderer<CoffinTile> {

    public CoffinRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    CoffinModel coffinModel = new CoffinModel();

    @Override
    public void render(CoffinTile tileEntityIn, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStack.push();
        matrixStack.translate(0.5F, 0.5F, 0.5F); //Translate to blockpos
        Direction face = tileEntityIn.getBlockState().get(BlockStateProperties.HORIZONTAL_FACING); //Get facing direction
        matrixStack.rotate(Vector3f.YP.rotationDegrees(face.getHorizontalAngle() - 180)); //Adjust rotation
        matrixStack.rotate(Vector3f.ZP.rotationDegrees(180F)); // Make model not upside down

        //Horizontal Placement
        if(!tileEntityIn.getBlockState().get(CoffinBlock.UPRIGHT)) {
            matrixStack.rotate(Vector3f.XP.rotationDegrees(-90F));
        } else {
            matrixStack.translate(0F, -1F, 0F);
        }

        //Handle actual rendering
        ResourceLocation texture = getTexture(tileEntityIn.getCoffin());
        coffinModel.Door.rotateAngleY = -(tileEntityIn.getOpenAmount() * ((float)Math.PI / 3F));
        coffinModel.render(matrixStack, bufferIn.getBuffer(RenderType.getEntityCutout(texture)), combinedLightIn, combinedOverlayIn, 1, 1, 1, 1);
        matrixStack.pop();
    }

    public ResourceLocation getTexture(CoffinTile.Coffin coffin) {
        return new ResourceLocation(WeepingAngels.MODID, "textures/tiles/coffin/" + coffin.name().toLowerCase() + ".png");
    }
}
