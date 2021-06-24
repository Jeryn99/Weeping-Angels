package me.suff.mc.angels.client.renderer.tile;

import me.suff.mc.angels.client.ClientAngels;
import me.suff.mc.angels.client.models.CoffinModel;
import me.suff.mc.angels.client.models.PTBCoffinModel;
import me.suff.mc.angels.common.block.CoffinBlock;
import me.suff.mc.angels.common.block.StatueBlock;
import me.suff.mc.angels.common.blockentity.CoffinTile;
import me.suff.mc.angels.util.Constants;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3f;

/* Created by Craig on 19/02/2021 */
public class CoffinTileRenderer implements BlockEntityRenderer<CoffinTile>, BlockEntityRendererFactory<CoffinTile> {

    private static PTBCoffinModel ptbCoffinModel;
    private static CoffinModel coffinModel;
    private final Context ctx;

    public CoffinTileRenderer(Context ctx) {
        this.ctx = ctx;
        coffinModel = new CoffinModel(this.ctx.getLayerModelPart(ClientAngels.COFFIN));
        ptbCoffinModel = new PTBCoffinModel(this.ctx.getLayerModelPart(ClientAngels.TARDIS));
    }

    @Override
    public void render(CoffinTile coffinTile, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (coffinTile.getCoffin() == null) return;
        matrices.push();
        matrices.translate(0.5F, 1.5F, 0.5F);
        matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(180F));
        BlockState blockstate = coffinTile.getCachedState();
        float rotation = 22.5F * (float) blockstate.get(StatueBlock.ROTATION);
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(rotation));

        if (!coffinTile.getCachedState().get(CoffinBlock.UPRIGHT)) {
            matrices.translate(0F, 1F, 0F);
            matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(-90F));
        }

        if (!coffinTile.getCoffin().name().contains("PTB")) {
            coffinModel.door.yaw = -(coffinTile.getOpenAmount() * ((float) Math.PI / 3F));
            coffinModel.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityCutout(getTexture(coffinTile.getCoffin()))), light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
        } else {
            matrices.translate(0, 0.5, 0);
            matrices.scale(0.7F, 0.7F, 0.7F);
            ptbCoffinModel.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(getTexture(coffinTile.getCoffin()))), light, OverlayTexture.DEFAULT_UV, 1, 1, 1, coffinTile.getAlpha());
        }
        matrices.pop();
    }

    public Identifier getTexture(CoffinTile.Coffin coffin) {
        return new Identifier(Constants.MODID, "textures/tiles/coffin/" + coffin.name().toLowerCase() + ".png");
    }

    @Override
    public BlockEntityRenderer<CoffinTile> create(Context ctx) {
        return new CoffinTileRenderer(ctx);
    }
}
