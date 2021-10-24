package me.suff.mc.angels.conversion.particle;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import me.suff.mc.angels.client.models.entity.ModelDisasterAngel;
import me.suff.mc.angels.client.models.entity.WAModels;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AngelParticle extends Particle {
    private final ModelDisasterAngel model;
    private final RenderType renderType = RenderType.entityTranslucent(ModelDisasterAngel.ANGRY);

    private AngelParticle(ClientLevel p_i232405_1_, double p_i232405_2_, double p_i232405_4_, double p_i232405_6_) {
        super(p_i232405_1_, p_i232405_2_, p_i232405_4_, p_i232405_6_);
        this.gravity = 0.0F;
        this.lifetime = 30;
        model = new ModelDisasterAngel(Minecraft.getInstance().getEntityModels().bakeLayer(WAModels.ANGEL_DISASTER));
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.CUSTOM;
    }

    public void render(VertexConsumer p_225606_1_, Camera p_225606_2_, float p_225606_3_) {
        float f = ((float) this.age + p_225606_3_) / (float) this.lifetime;
        float f1 = 0.05F + 0.5F * Mth.sin(f * (float) Math.PI);
        PoseStack matrixstack = new PoseStack();
        matrixstack.mulPose(p_225606_2_.rotation());
        matrixstack.mulPose(Vector3f.XP.rotationDegrees(150.0F * f - 60.0F));
        matrixstack.scale(-1.0F, -1.0F, 1.0F);
        matrixstack.translate(0.0D, -1.101F, 1.5D);
        MultiBufferSource.BufferSource irendertypebuffer$impl = Minecraft.getInstance().renderBuffers().bufferSource();
        VertexConsumer ivertexbuilder = irendertypebuffer$impl.getBuffer(this.renderType);
        this.model.renderToBuffer(matrixstack, ivertexbuilder, 15728880, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, f1);
        irendertypebuffer$impl.endBatch();
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements ParticleProvider<SimpleParticleType> {
        public Particle createParticle(SimpleParticleType p_199234_1_, ClientLevel p_199234_2_, double p_199234_3_, double p_199234_5_, double p_199234_7_, double p_199234_9_, double p_199234_11_, double p_199234_13_) {
            return new AngelParticle(p_199234_2_, p_199234_3_, p_199234_5_, p_199234_7_);
        }
    }
}
