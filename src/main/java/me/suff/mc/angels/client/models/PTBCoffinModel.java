// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

package me.suff.mc.angels.client.models;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class PTBCoffinModel extends EntityModel< Entity > {
    private final ModelPart Base;
    private final ModelPart Pillars;
    private final ModelPart Frame;
    private final ModelPart SideDoors;
    private final ModelPart PPCBSign;
    private final ModelPart Roof;
    private final ModelPart Lamp;
    private final ModelPart DoorLeft;
    private final ModelPart DoorRight;

    public PTBCoffinModel() {
        textureWidth = 256;
        textureHeight = 256;
        Base = new ModelPart(this);
        Base.setPivot(0.0F, 24.0F, 0.0F);
        Base.setTextureOffset(0, 0).addCuboid(-18.5F, -3.0F, -18.5F, 37.0F, 3.0F, 37.0F, 0.0F, false);

        Pillars = new ModelPart(this);
        Pillars.setPivot(0.0F, 24.0F, 0.0F);
        Pillars.setTextureOffset(0, 193).addCuboid(-17.5F, -60.0F, -17.5F, 4.0F, 57.0F, 4.0F, 0.0F, false);
        Pillars.setTextureOffset(140, 191).addCuboid(13.5F, -60.0F, -17.5F, 4.0F, 57.0F, 4.0F, 0.0F, false);
        Pillars.setTextureOffset(124, 191).addCuboid(13.5F, -60.0F, 13.5F, 4.0F, 57.0F, 4.0F, 0.0F, false);
        Pillars.setTextureOffset(108, 191).addCuboid(-17.5F, -60.0F, 13.5F, 4.0F, 57.0F, 4.0F, 0.0F, false);
        Pillars.setTextureOffset(99, 40).addCuboid(-16.5F, -62.0F, -16.5F, 33.0F, 2.0F, 33.0F, 0.0F, false);

        Frame = new ModelPart(this);
        Frame.setPivot(0.0F, 24.0F, 0.0F);
        Frame.setTextureOffset(209, 181).addCuboid(-13.5F, -53.0F, -17.0F, 1.0F, 50.0F, 2.0F, 0.0F, false);
        Frame.setTextureOffset(215, 181).addCuboid(-17.0F, -53.0F, 12.5F, 2.0F, 50.0F, 1.0F, 0.0F, false);
        Frame.setTextureOffset(209, 181).addCuboid(12.5F, -53.0F, 15.0F, 1.0F, 50.0F, 2.0F, 0.0F, false);
        Frame.setTextureOffset(215, 181).addCuboid(15.0F, -53.0F, -13.5F, 2.0F, 50.0F, 1.0F, 0.0F, false);
        Frame.setTextureOffset(209, 181).addCuboid(12.5F, -53.0F, -17.0F, 1.0F, 50.0F, 2.0F, 0.0F, false);
        Frame.setTextureOffset(215, 181).addCuboid(-17.0F, -53.0F, -13.5F, 2.0F, 50.0F, 1.0F, 0.0F, false);
        Frame.setTextureOffset(209, 181).addCuboid(-13.5F, -53.0F, 15.0F, 1.0F, 50.0F, 2.0F, 0.0F, false);
        Frame.setTextureOffset(215, 181).addCuboid(15.0F, -53.0F, 12.5F, 2.0F, 50.0F, 1.0F, 0.0F, false);
        Frame.setTextureOffset(221, 181).addCuboid(-17.0F, -53.0F, -0.5F, 1.0F, 50.0F, 1.0F, 0.0F, false);
        Frame.setTextureOffset(221, 181).addCuboid(-0.5F, -53.0F, 16.0F, 1.0F, 50.0F, 1.0F, 0.0F, false);
        Frame.setTextureOffset(221, 181).addCuboid(16.0F, -53.0F, -0.5F, 1.0F, 50.0F, 1.0F, 0.0F, false);
        Frame.setTextureOffset(0, 221).addCuboid(-17.0F, -54.0F, -17.0F, 34.0F, 1.0F, 34.0F, 0.0F, false);

        SideDoors = new ModelPart(this);
        SideDoors.setPivot(1.0F, 21.0F, -16.5F);
        SideDoors.setTextureOffset(52, 118).addCuboid(-17.0F, -50.0F, 4.0F, 1.0F, 50.0F, 25.0F, 0.0F, false);
        SideDoors.setTextureOffset(104, 140).addCuboid(-13.5F, -50.0F, 31.5F, 25.0F, 50.0F, 1.0F, 0.0F, false);
        SideDoors.setTextureOffset(0, 118).addCuboid(14.0F, -50.0F, 4.0F, 1.0F, 50.0F, 25.0F, 0.0F, false);

        PPCBSign = new ModelPart(this);
        PPCBSign.setPivot(0.0F, 24.0F, 0.0F);
        PPCBSign.setTextureOffset(0, 40).addCuboid(-15.5F, -59.0F, -18.5F, 31.0F, 5.0F, 37.0F, 0.0F, false);
        PPCBSign.setTextureOffset(0, 82).addCuboid(-18.5F, -59.0F, -15.5F, 37.0F, 5.0F, 31.0F, 0.0F, false);

        Roof = new ModelPart(this);
        Roof.setPivot(17.5F, -35.0F, -17.5F);
        Roof.setTextureOffset(111, 0).addCuboid(-32.0F, -6.0F, 3.0F, 29.0F, 2.0F, 29.0F, 0.0F, false);
        Roof.setTextureOffset(105, 105).addCuboid(-33.0F, -4.0F, 2.0F, 31.0F, 4.0F, 31.0F, 0.0F, false);

        Lamp = new ModelPart(this);
        Lamp.setPivot(17.5F, -41.0F, -17.5F);
        Lamp.setTextureOffset(0, 0).addCuboid(-20.0F, -2.0F, 15.0F, 5.0F, 2.0F, 5.0F, 0.0F, false);
        Lamp.setTextureOffset(0, 13).addCuboid(-20.0F, -7.5F, 15.0F, 5.0F, 6.0F, 5.0F, -0.5F, false);
        Lamp.setTextureOffset(0, 7).addCuboid(-20.0F, -8.0F, 15.0F, 5.0F, 1.0F, 5.0F, 0.0F, false);
        Lamp.setTextureOffset(16, 13).addCuboid(-19.0F, -9.0F, 16.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);

        DoorLeft = new ModelPart(this);
        DoorLeft.setPivot(12.5F, 21.0F, -15.0F);
        DoorLeft.setTextureOffset(182, 182).addCuboid(-12.0F, -50.0F, -1.0F, 12.0F, 50.0F, 1.0F, 0.0F, false);
        DoorLeft.setTextureOffset(0, 24).addCuboid(-12.0F, -34.0F, -2.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);
        DoorLeft.setTextureOffset(4, 24).addCuboid(-12.0F, -26.0F, -1.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        DoorRight = new ModelPart(this);
        DoorRight.setPivot(-12.5F, 21.0F, -15.0F);
        DoorRight.setTextureOffset(156, 156).addCuboid(0.0F, -50.0F, -1.0F, 12.0F, 50.0F, 1.0F, 0.0F, false);
        DoorRight.setTextureOffset(221, 181).addCuboid(12.0F, -50.0F, -2.0F, 1.0F, 50.0F, 1.0F, 0.0F, false);
        DoorRight.setTextureOffset(0, 30).addCuboid(10.0F, -33.0F, -1.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
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