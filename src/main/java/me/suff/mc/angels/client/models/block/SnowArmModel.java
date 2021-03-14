package me.suff.mc.angels.client.models.block;// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class SnowArmModel extends EntityModel< Entity > {
    private final ModelRenderer rightArm;

    public SnowArmModel() {
        textureWidth = 128;
        textureHeight = 128;

        rightArm = new ModelRenderer(this);
        rightArm.setRotationPoint(0.0F, 16.0F, -4.0F);
        setRotationAngle(rightArm, 0.3927F, 0.0F, 0.0F);
        rightArm.setTextureOffset(10, 59).addBox(-2.0F, -3.0F, 0.0F, 3.0F, 12.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}