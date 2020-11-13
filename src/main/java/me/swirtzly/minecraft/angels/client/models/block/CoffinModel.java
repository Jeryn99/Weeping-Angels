package me.swirtzly.minecraft.angels.client.models.block;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class CoffinModel extends EntityModel<Entity> {
    public final ModelRenderer Body;
    public final ModelRenderer Door;

    public CoffinModel() {
        textureWidth = 128;
        textureHeight = 128;

        Body = new ModelRenderer(this);
        Body.setRotationPoint(7.0F, 57.0F, -6.0F);
        Body.setTextureOffset(0, 38).addBox(-15.0F, -51.0F, 3.0F, 1.0F, 17.0F, 11.0F, 0.0F, true);
        Body.setTextureOffset(0, 38).addBox(0.0F, -51.0F, 3.0F, 1.0F, 17.0F, 11.0F, 0.0F, false);
        Body.setTextureOffset(59, 61).addBox(-15.0F, -68.0F, 3.0F, 1.0F, 6.0F, 11.0F, 0.0F, true);
        Body.setTextureOffset(59, 61).addBox(0.0F, -68.0F, 3.0F, 1.0F, 6.0F, 11.0F, 0.0F, false);
        Body.setTextureOffset(13, 61).addBox(-16.0F, -63.0F, 3.0F, 1.0F, 13.0F, 11.0F, 0.0F, true);
        Body.setTextureOffset(13, 61).addBox(1.0F, -63.0F, 3.0F, 1.0F, 13.0F, 11.0F, 0.0F, false);
        Body.setTextureOffset(36, 0).addBox(-15.0F, -69.0F, 3.0F, 16.0F, 1.0F, 11.0F, 0.0F, true);
        Body.setTextureOffset(36, 0).addBox(-15.0F, -34.0F, 3.0F, 16.0F, 1.0F, 11.0F, 0.0F, false);
        Body.setTextureOffset(36, 36).addBox(-15.0F, -69.0F, 13.0F, 16.0F, 35.0F, 1.0F, 0.0F, false);

        Door = new ModelRenderer(this);
        Door.setRotationPoint(9.0F, 4.0F, -3.0F);
        Door.setTextureOffset(0, 0).addBox(-17.0F, -16.0F, -2.0F, 16.0F, 36.0F, 2.0F, 0.0F, false);
        Door.setTextureOffset(36, 12).addBox(-18.0F, -10.0F, -2.0F, 18.0F, 13.0F, 2.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        Body.render(matrixStack, buffer, packedLight, packedOverlay);
        Door.render(matrixStack, buffer, packedLight, packedOverlay);
    }
}