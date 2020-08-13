package me.swirtzly.minecraft.angels.client.poses;

import me.swirtzly.minecraft.angels.WeepingAngels;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nullable;

@OnlyIn(value = Dist.CLIENT, _interface = IPoseAngles.class)
public abstract class PoseBase implements IForgeRegistryEntry<PoseBase>, IPoseAngles {

	protected String name;
	private float limbSwing, limbSwingAmount, ageInTicks, netheadYaw, headPitch, swingProgress;
	private WeepingAngelEntity angel;
	private ResourceLocation location;

	public PoseBase(String name) {
		this.name = name;
	}

	public PoseBase(WeepingAngelEntity angel, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float swingProgress) {
		this.limbSwing = limbSwing;
		this.limbSwingAmount = limbSwingAmount;
		this.ageInTicks = ageInTicks;
		this.netheadYaw = netheadYaw;
		this.headPitch = headPitch;
		this.swingProgress = swingProgress;
		this.angel = angel;
	}
	
	
	/**
	 * Determines angry face
	 */
	public abstract boolean angryFace();
	
	
	/**
	 * Returns the entities Limb Swing
	 */
	public float getLimbSwing() {
		return limbSwing;
	}
	
	/**
	 * Returns the entities Limb Swing amount
	 */
	public float getLimbSwingAmount() {
		return limbSwingAmount;
	}
	
	/**
	 * Returns the entities age in ticks
	 */
	public float getAgeInTicks() {
		return ageInTicks;
	}
	
	/**
	 * Returns the entities head pitch
	 */
	public float getHeadPitch() {
		return headPitch;
	}
	
	/**
	 * Returns the entities head yaw
	 */
	public float getNetheadYaw() {
		return netheadYaw;
	}
	
	/**
	 * Returns the entities Swing progress
	 */
	public float getSwingProgress() {
		return swingProgress;
	}
	
	/**
	 * Returns the entities in use
	 */
	public WeepingAngelEntity getAngel() {
		return angel;
	}
	
	/**
	 * Converts degrees to radians, because fuck radians
	 */
	public float degreeToRadian(float degree) {
		return (float) (degree * Math.PI / 180);
	}
	
	@OnlyIn(Dist.CLIENT)
	public void resetAngles(RendererModel model) {
		model.rotateAngleX = 0;
		model.rotateAngleY = 0;
		model.rotateAngleY = 0;
	}

	@Override
	public PoseBase setRegistryName(ResourceLocation resourceLocation) {
		this.location = resourceLocation;
		return this;
	}

	@Nullable
	@Override
	public ResourceLocation getRegistryName() {
		return new ResourceLocation(WeepingAngels.MODID, name);
	}

	@Override
	public Class<PoseBase> getRegistryType() {
		return PoseBase.class;
	}
}
