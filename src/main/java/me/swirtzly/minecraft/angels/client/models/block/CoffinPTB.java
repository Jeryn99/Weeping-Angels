package me.swirtzly.minecraft.angels.client.models.block;// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class CoffinPTB extends EntityModel {
    private final ModelRenderer Base;
    private final ModelRenderer Pillars;
    private final ModelRenderer Frame;
    private final ModelRenderer SideDoors;
    private final ModelRenderer PPCBSign;
    private final ModelRenderer Roof;
    private final ModelRenderer Lamp;
    private final ModelRenderer DoorLeft;
    public final ModelRenderer DoorRight;

    public CoffinPTB() {
        textureWidth = 256;
        textureHeight = 256;

        Base = new ModelRenderer(this);
        Base.setRotationPoint(0.0F, 24.0F, 0.0F);
        Base.setTextureOffset(0, 0).addBox(-18.5F, -3.0F, -18.5F, 37.0F, 3.0F, 37.0F, 0.0F, false);

        Pillars = new ModelRenderer(this);
        Pillars.setRotationPoint(0.0F, 24.0F, 0.0F);
        Pillars.setTextureOffset(0, 193).addBox(-17.5F, -60.0F, -17.5F, 4.0F, 57.0F, 4.0F, 0.0F, false);
        Pillars.setTextureOffset(140, 191).addBox(13.5F, -60.0F, -17.5F, 4.0F, 57.0F, 4.0F, 0.0F, false);
        Pillars.setTextureOffset(124, 191).addBox(13.5F, -60.0F, 13.5F, 4.0F, 57.0F, 4.0F, 0.0F, false);
        Pillars.setTextureOffset(108, 191).addBox(-17.5F, -60.0F, 13.5F, 4.0F, 57.0F, 4.0F, 0.0F, false);
        Pillars.setTextureOffset(99, 40).addBox(-16.5F, -62.0F, -16.5F, 33.0F, 2.0F, 33.0F, 0.0F, false);

        Frame = new ModelRenderer(this);
        Frame.setRotationPoint(0.0F, 24.0F, 0.0F);
        Frame.setTextureOffset(209, 181).addBox(-13.5F, -53.0F, -17.0F, 1.0F, 50.0F, 2.0F, 0.0F, false);
        Frame.setTextureOffset(215, 181).addBox(-17.0F, -53.0F, 12.5F, 2.0F, 50.0F, 1.0F, 0.0F, false);
        Frame.setTextureOffset(209, 181).addBox(12.5F, -53.0F, 15.0F, 1.0F, 50.0F, 2.0F, 0.0F, false);
        Frame.setTextureOffset(215, 181).addBox(15.0F, -53.0F, -13.5F, 2.0F, 50.0F, 1.0F, 0.0F, false);
        Frame.setTextureOffset(209, 181).addBox(12.5F, -53.0F, -17.0F, 1.0F, 50.0F, 2.0F, 0.0F, false);
        Frame.setTextureOffset(215, 181).addBox(-17.0F, -53.0F, -13.5F, 2.0F, 50.0F, 1.0F, 0.0F, false);
        Frame.setTextureOffset(209, 181).addBox(-13.5F, -53.0F, 15.0F, 1.0F, 50.0F, 2.0F, 0.0F, false);
        Frame.setTextureOffset(215, 181).addBox(15.0F, -53.0F, 12.5F, 2.0F, 50.0F, 1.0F, 0.0F, false);
        Frame.setTextureOffset(221, 181).addBox(-17.0F, -53.0F, -0.5F, 1.0F, 50.0F, 1.0F, 0.0F, false);
        Frame.setTextureOffset(221, 181).addBox(-0.5F, -53.0F, 16.0F, 1.0F, 50.0F, 1.0F, 0.0F, false);
        Frame.setTextureOffset(221, 181).addBox(16.0F, -53.0F, -0.5F, 1.0F, 50.0F, 1.0F, 0.0F, false);
        Frame.setTextureOffset(0, 221).addBox(-17.0F, -54.0F, -17.0F, 34.0F, 1.0F, 34.0F, 0.0F, false);

        SideDoors = new ModelRenderer(this);
        SideDoors.setRotationPoint(1.0F, 21.0F, -16.5F);
        SideDoors.setTextureOffset(52, 118).addBox(-17.0F, -50.0F, 4.0F, 1.0F, 50.0F, 25.0F, 0.0F, false);
        SideDoors.setTextureOffset(104, 140).addBox(-13.5F, -50.0F, 31.5F, 25.0F, 50.0F, 1.0F, 0.0F, false);
        SideDoors.setTextureOffset(0, 118).addBox(14.0F, -50.0F, 4.0F, 1.0F, 50.0F, 25.0F, 0.0F, false);

        PPCBSign = new ModelRenderer(this);
        PPCBSign.setRotationPoint(0.0F, 24.0F, 0.0F);
        PPCBSign.setTextureOffset(0, 40).addBox(-15.5F, -59.0F, -18.5F, 31.0F, 5.0F, 37.0F, 0.0F, false);
        PPCBSign.setTextureOffset(0, 82).addBox(-18.5F, -59.0F, -15.5F, 37.0F, 5.0F, 31.0F, 0.0F, false);

        Roof = new ModelRenderer(this);
        Roof.setRotationPoint(17.5F, -35.0F, -17.5F);
        Roof.setTextureOffset(111, 0).addBox(-32.0F, -6.0F, 3.0F, 29.0F, 2.0F, 29.0F, 0.0F, false);
        Roof.setTextureOffset(105, 105).addBox(-33.0F, -4.0F, 2.0F, 31.0F, 4.0F, 31.0F, 0.0F, false);

        Lamp = new ModelRenderer(this);
        Lamp.setRotationPoint(17.5F, -41.0F, -17.5F);
        Lamp.setTextureOffset(0, 0).addBox(-20.0F, -2.0F, 15.0F, 5.0F, 2.0F, 5.0F, 0.0F, false);
        Lamp.setTextureOffset(0, 13).addBox(-20.0F, -7.5F, 15.0F, 5.0F, 6.0F, 5.0F, -0.5F, false);
        Lamp.setTextureOffset(0, 7).addBox(-20.0F, -8.0F, 15.0F, 5.0F, 1.0F, 5.0F, 0.0F, false);
        Lamp.setTextureOffset(16, 13).addBox(-19.0F, -9.0F, 16.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);

        DoorLeft = new ModelRenderer(this);
        DoorLeft.setRotationPoint(12.5F, 21.0F, -15.0F);
        DoorLeft.setTextureOffset(182, 182).addBox(-12.0F, -50.0F, -1.0F, 12.0F, 50.0F, 1.0F, 0.0F, false);
        DoorLeft.setTextureOffset(0, 24).addBox(-12.0F, -34.0F, -2.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);
        DoorLeft.setTextureOffset(4, 24).addBox(-12.0F, -26.0F, -1.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        DoorRight = new ModelRenderer(this);
        DoorRight.setRotationPoint(-12.5F, 21.0F, -15.0F);
        DoorRight.setTextureOffset(156, 156).addBox(0.0F, -50.0F, -1.0F, 12.0F, 50.0F, 1.0F, 0.0F, false);
        DoorRight.setTextureOffset(221, 181).addBox(12.0F, -50.0F, -2.0F, 1.0F, 50.0F, 1.0F, 0.0F, false);
        DoorRight.setTextureOffset(0, 30).addBox(10.0F, -33.0F, -1.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        Base.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        Pillars.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        Frame.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        SideDoors.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        PPCBSign.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        Roof.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        Lamp.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        DoorLeft.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        DoorRight.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}