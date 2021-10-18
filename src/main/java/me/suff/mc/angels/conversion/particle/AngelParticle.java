package me.suff.mc.angels.conversion.particle;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import me.suff.mc.angels.client.models.entity.ModelDisasterAngel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AngelParticle extends Particle {
    private final Model model = new ModelDisasterAngel();
    private final RenderType renderType = RenderType.entityTranslucent(ModelDisasterAngel.ANGRY);

    private AngelParticle(ClientWorld p_i232405_1_, double p_i232405_2_, double p_i232405_4_, double p_i232405_6_) {
        super(p_i232405_1_, p_i232405_2_, p_i232405_4_, p_i232405_6_);
        this.gravity = 0.0F;
        this.lifetime = 30;
    }

    public IParticleRenderType getRenderType() {
        return IParticleRenderType.CUSTOM;
    }

    public void render(IVertexBuilder p_225606_1_, ActiveRenderInfo p_225606_2_, float p_225606_3_) {
        float f = ((float) this.age + p_225606_3_) / (float) this.lifetime;
        float f1 = 0.05F + 0.5F * MathHelper.sin(f * (float) Math.PI);
        MatrixStack matrixstack = new MatrixStack();
        matrixstack.mulPose(p_225606_2_.rotation());
        matrixstack.mulPose(Vector3f.XP.rotationDegrees(150.0F * f - 60.0F));
        matrixstack.scale(-1.0F, -1.0F, 1.0F);
        matrixstack.translate(0.0D, (double) -1.101F, 1.5D);
        IRenderTypeBuffer.Impl irendertypebuffer$impl = Minecraft.getInstance().renderBuffers().bufferSource();
        IVertexBuilder ivertexbuilder = irendertypebuffer$impl.getBuffer(this.renderType);
        this.model.renderToBuffer(matrixstack, ivertexbuilder, 15728880, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, f1);
        irendertypebuffer$impl.endBatch();
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        public Particle createParticle(BasicParticleType p_199234_1_, ClientWorld p_199234_2_, double p_199234_3_, double p_199234_5_, double p_199234_7_, double p_199234_9_, double p_199234_11_, double p_199234_13_) {
            return new AngelParticle(p_199234_2_, p_199234_3_, p_199234_5_, p_199234_7_);
        }
    }
}
