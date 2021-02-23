package me.suff.mc.angels.client.renderer.tile;

import me.suff.mc.angels.client.ClientAngels;
import me.suff.mc.angels.client.models.WeepingAngelModel;
import me.suff.mc.angels.client.renderer.entiity.WeepingAngelRenderer;
import me.suff.mc.angels.common.block.PlinthBlock;
import me.suff.mc.angels.common.blockentity.PlinthTile;
import me.suff.mc.angels.common.blockentity.StatueTile;
import me.suff.mc.angels.enums.WeepingAngelPose;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.model.SheepEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3f;

/* Created by Craig on 18/02/2021 */
public class PlinthTileRenderer implements BlockEntityRenderer< PlinthTile >, BlockEntityRendererFactory< PlinthTile > {

    private final Context ctx;
    WeepingAngelModel weepingAngelModel;

    public PlinthTileRenderer(Context ctx) {
        this.ctx = ctx;
        weepingAngelModel = new WeepingAngelModel(this.ctx.getLayerModelPart(ClientAngels.ANGELS));;
    }

    @Override
    public void render(PlinthTile statueTile, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();
        matrices.translate(0.5F, 2.5F, 0.5F);
        matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(180F));
        BlockState blockstate = statueTile.getCachedState();
        float rotation = 22.5F * (float) blockstate.get(PlinthBlock.ROTATION);
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(rotation));
        WeepingAngelModel angel = weepingAngelModel;
        Identifier texture;

        WeepingAngelPose pose = statueTile.getAngelPose();
        if (angel != null) {
            angel.setWeepingAngelPose(pose);
            texture = WeepingAngelRenderer.generateTex(pose, statueTile.getAngelVariant());
            angel.setAngles(null, 0, 0, 0, 0, 0);
            angel.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityCutout(texture)), light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
        }
        matrices.pop();
    }

    @Override
    public BlockEntityRenderer< PlinthTile > create(Context ctx) {
        return new PlinthTileRenderer(ctx);
    }
}
