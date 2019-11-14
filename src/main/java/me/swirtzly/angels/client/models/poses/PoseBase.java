package me.swirtzly.angels.client.models.poses;

import me.swirtzly.angels.common.entities.EntityWeepingAngel;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class PoseBase {

    private float limbSwing, limbSwingAmount, ageInTicks, netheadYaw, headPitch, swingProgress;
    private EntityWeepingAngel angel;
    private String registryName = "MISSINGNAME";

    public PoseBase() {
    }

    public PoseBase(EntityWeepingAngel angel, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float swingProgress) {
        this.limbSwing = limbSwing;
        this.limbSwingAmount = limbSwingAmount;
        this.ageInTicks = ageInTicks;
        this.netheadYaw = netheadYaw;
        this.headPitch = headPitch;
        this.swingProgress = swingProgress;
        this.angel = angel;
    }

    public String getRegistryName() {
        return registryName;
    }

    public void setRegistryName(String registryName) {
        this.registryName = registryName;
    }

    /**
     * Used to set the Models arm angles
     */
    public abstract void setArmAngles(ModelRenderer left_arm, ModelRenderer right_arm, ModelRenderer wrist_left, ModelRenderer wrist_right);

    /**
     * Used to set the Models head angles
     */
    public abstract void setHeadAngles(ModelRenderer head);

    /**
     * Determines angry face
     */
    public abstract boolean angryFace();

    /**
     * Determines body angles, I wouldn't really reccomend messing with this, it's not pretty
     */
    public abstract void setBodyAngles(ModelRenderer body);

    /**
     * Basically I never use this, it's there for the sake of it, used to set wing angles
     */
    public abstract void setWingAngles(ModelRenderer left_wing, ModelRenderer right_wing);

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
    public EntityWeepingAngel getAngel() {
        return angel;
    }

    /**
     * Converts degrees to radians, because fuck radians
     */
    public float degreeToRadian(float degree) {
        return (float) (degree * Math.PI / 180);
    }

    @SideOnly(Side.CLIENT)
    public void resetAngles(ModelRenderer model) {
        model.rotateAngleX = 0;
        model.rotateAngleY = 0;
        model.rotateAngleY = 0;
    }

}
