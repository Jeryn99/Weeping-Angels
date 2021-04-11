package me.suff.mc.angels.client.renderer.entiity;

import me.suff.mc.angels.client.models.PortalModel;
import me.suff.mc.angels.common.entity.PortalEntity;
import me.suff.mc.angels.util.Constants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.NoRenderParticle;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.AreaEffectCloudEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Quaternion;

/* Created by Craig on 21/02/2021 */
public class PortalRenderer extends LivingEntityRenderer< PortalEntity, PortalModel > {

    private static final Identifier TEXTURE = new Identifier(Constants.MODID, "textures/entities/anomaly.png");


    public PortalRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher, new PortalModel(), 0);
    }


    @Override
    public Identifier getTexture(PortalEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(PortalEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        Quaternion rotation = MinecraftClient.getInstance().gameRenderer.getCamera().getRotation();
        matrixStack.multiply(rotation);
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
        matrixStack.pop();
    }

    @Override
    protected void renderLabelIfPresent(PortalEntity entity, Text text, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {

    }
}
