package me.suff.mc.angels.client.models;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class CoffinModel extends EntityModel< Entity > {
    public final ModelPart Door;
    private final ModelPart Body;

    public CoffinModel() {
        textureWidth = 128;
        textureHeight = 128;
        Body = new ModelPart(this);
        Body.setPivot(7.0F, 57.0F, -6.0F);
        Body.setTextureOffset(0, 38).addCuboid(-15.0F, -51.0F, 3.0F, 1.0F, 17.0F, 11.0F, 0.0F, true);
        Body.setTextureOffset(0, 38).addCuboid(0.0F, -51.0F, 3.0F, 1.0F, 17.0F, 11.0F, 0.0F, false);
        Body.setTextureOffset(59, 61).addCuboid(-15.0F, -68.0F, 3.0F, 1.0F, 6.0F, 11.0F, 0.0F, true);
        Body.setTextureOffset(59, 61).addCuboid(0.0F, -68.0F, 3.0F, 1.0F, 6.0F, 11.0F, 0.0F, false);
        Body.setTextureOffset(13, 61).addCuboid(-16.0F, -63.0F, 3.0F, 1.0F, 13.0F, 11.0F, 0.0F, true);
        Body.setTextureOffset(13, 61).addCuboid(1.0F, -63.0F, 3.0F, 1.0F, 13.0F, 11.0F, 0.0F, false);
        Body.setTextureOffset(36, 0).addCuboid(-15.0F, -69.0F, 3.0F, 16.0F, 1.0F, 11.0F, 0.0F, true);
        Body.setTextureOffset(36, 0).addCuboid(-15.0F, -34.0F, 3.0F, 16.0F, 1.0F, 11.0F, 0.0F, false);
        Body.setTextureOffset(36, 36).addCuboid(-15.0F, -69.0F, 13.0F, 16.0F, 35.0F, 1.0F, 0.0F, false);

        Door = new ModelPart(this);
        Door.setPivot(9.0F, 4.0F, -3.0F);
        Door.setTextureOffset(0, 0).addCuboid(-17.0F, -16.0F, -2.0F, 16.0F, 36.0F, 2.0F, 0.0F, false);
        Door.setTextureOffset(36, 12).addCuboid(-18.0F, -10.0F, -2.0F, 18.0F, 13.0F, 2.0F, 0.0F, false);
    }

    @Override
    public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Body.render(matrixStack, buffer, packedLight, packedOverlay);
        Door.render(matrixStack, buffer, packedLight, packedOverlay);
    }

}