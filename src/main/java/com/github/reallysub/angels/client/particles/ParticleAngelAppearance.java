package com.github.reallysub.angels.client.particles;

import com.github.reallysub.angels.common.entities.EntityAngel;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleAngelAppearance extends Particle {
	private EntityLivingBase entity;
	
	public ParticleAngelAppearance(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.0D, 0.0D, 0.0D);
		this.particleRed = 1.0F;
		this.particleGreen = 1.0F;
		this.particleBlue = 1.0F;
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
		this.particleGravity = 0.0F;
		this.particleMaxAge = 30;
	}
	
	/**
	 * Retrieve what effect layer (what texture) the particle should be rendered with. 0 for the particle sprite sheet, 1 for the main Texture atlas, and 3 for a custom texture
	 */
	public int getFXLayer() {
		return 3;
	}
	
	public void onUpdate() {
		super.onUpdate();
		
		if (this.entity == null) {
			EntityAngel entityAngel = new EntityAngel(this.world);
			entityAngel.setAngry(true);
			entityAngel.updateBlocked = true;
			this.entity = entityAngel;
		}
	}
	
	/**
	 * Renders the particle
	 */
	@Override
	public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
		if (this.entity != null) {
			RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
			rendermanager.setRenderPosition(Particle.interpPosX, Particle.interpPosY, Particle.interpPosZ);
			float f = 0.42553192F;
			float f1 = ((float) this.particleAge + partialTicks) / (float) this.particleMaxAge;
			GlStateManager.depthMask(true);
			GlStateManager.enableBlend();
			GlStateManager.enableDepth();
			GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
			GlStateManager.pushMatrix();
			float f3 = 0.05F + 0.5F * MathHelper.sin(f1 * (float) Math.PI);
			GlStateManager.color(1.0F, 1.0F, 1.0F, f3);
			GlStateManager.translate(0.0F, 1.8F, 0.0F);
			GlStateManager.rotate(180.0F - entityIn.rotationYaw, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(60.0F - 150.0F * f1 - entityIn.rotationPitch, 1.0F, 0.0F, 0.0F);
			GlStateManager.translate(0.0F, -0.4F, -1.5F);
			GlStateManager.scale(1.0, 1.0, 1.0);
			this.entity.rotationYaw = 0.0F;
			this.entity.rotationYawHead = 0.0F;
			this.entity.prevRotationYaw = 0.0F;
			this.entity.prevRotationYawHead = 0.0F;
			rendermanager.renderEntity(this.entity, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks, false);
			GlStateManager.popMatrix();
			GlStateManager.enableDepth();
		}
	}
	
}
