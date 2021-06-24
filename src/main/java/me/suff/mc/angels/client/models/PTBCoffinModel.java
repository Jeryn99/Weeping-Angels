// Made with Blockbench 3.8.4
// Exported for Minecraft version 1.17
// Paste this class into your mod and generate all require
package me.suff.mc.angels.client.models;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class PTBCoffinModel extends EntityModel<Entity> {
    private final ModelPart Base;
    private final ModelPart Pillars;
    private final ModelPart Frame;
    private final ModelPart SideDoors;
    private final ModelPart PPCBSign;
    private final ModelPart Roof;
    private final ModelPart Lamp;
    private final ModelPart DoorLeft;
    private final ModelPart DoorRight;

    public PTBCoffinModel(ModelPart root) {
        Base = root.getChild("Base");
        Pillars = root.getChild("Pillars");
        Frame = root.getChild("Frame");
        SideDoors = root.getChild("SideDoors");
        PPCBSign = root.getChild("PPCBSign");
        Roof = root.getChild("Roof");
        Lamp = root.getChild("Lamp");
        DoorLeft = root.getChild("DoorLeft");
        DoorRight = root.getChild("DoorRight");
    }

    public static ModelData getModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData Base = modelPartData.addChild("Base", ModelPartBuilder.create().uv(0, 0).cuboid(-18.5F, -3.0F, -18.5F, 37.0F, 3.0F, 37.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        ModelPartData Pillars = modelPartData.addChild("Pillars", ModelPartBuilder.create().uv(0, 193).cuboid(-17.5F, -60.0F, -17.5F, 4.0F, 57.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
                .uv(140, 191).cuboid(13.5F, -60.0F, -17.5F, 4.0F, 57.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
                .uv(124, 191).cuboid(13.5F, -60.0F, 13.5F, 4.0F, 57.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
                .uv(108, 191).cuboid(-17.5F, -60.0F, 13.5F, 4.0F, 57.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
                .uv(99, 40).cuboid(-16.5F, -62.0F, -16.5F, 33.0F, 2.0F, 33.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        ModelPartData Frame = modelPartData.addChild("Frame", ModelPartBuilder.create().uv(209, 181).cuboid(-13.5F, -53.0F, -17.0F, 1.0F, 50.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
                .uv(215, 181).cuboid(-17.0F, -53.0F, 12.5F, 2.0F, 50.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
                .uv(209, 181).cuboid(12.5F, -53.0F, 15.0F, 1.0F, 50.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
                .uv(215, 181).cuboid(15.0F, -53.0F, -13.5F, 2.0F, 50.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
                .uv(209, 181).cuboid(12.5F, -53.0F, -17.0F, 1.0F, 50.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
                .uv(215, 181).cuboid(-17.0F, -53.0F, -13.5F, 2.0F, 50.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
                .uv(209, 181).cuboid(-13.5F, -53.0F, 15.0F, 1.0F, 50.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
                .uv(215, 181).cuboid(15.0F, -53.0F, 12.5F, 2.0F, 50.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
                .uv(221, 181).cuboid(-17.0F, -53.0F, -0.5F, 1.0F, 50.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
                .uv(221, 181).cuboid(-0.5F, -53.0F, 16.0F, 1.0F, 50.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
                .uv(221, 181).cuboid(16.0F, -53.0F, -0.5F, 1.0F, 50.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
                .uv(0, 221).cuboid(-17.0F, -54.0F, -17.0F, 34.0F, 1.0F, 34.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        ModelPartData SideDoors = modelPartData.addChild("SideDoors", ModelPartBuilder.create().uv(52, 118).cuboid(-17.0F, -50.0F, 4.0F, 1.0F, 50.0F, 25.0F, new Dilation(0.0F)).mirrored(false)
                .uv(104, 140).cuboid(-13.5F, -50.0F, 31.5F, 25.0F, 50.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
                .uv(0, 118).cuboid(14.0F, -50.0F, 4.0F, 1.0F, 50.0F, 25.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(1.0F, 21.0F, -16.5F, 0.0F, 0.0F, 0.0F));

        ModelPartData PPCBSign = modelPartData.addChild("PPCBSign", ModelPartBuilder.create().uv(0, 40).cuboid(-15.5F, -59.0F, -18.5F, 31.0F, 5.0F, 37.0F, new Dilation(0.0F)).mirrored(false)
                .uv(0, 82).cuboid(-18.5F, -59.0F, -15.5F, 37.0F, 5.0F, 31.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        ModelPartData Roof = modelPartData.addChild("Roof", ModelPartBuilder.create().uv(111, 0).cuboid(-32.0F, -6.0F, 3.0F, 29.0F, 2.0F, 29.0F, new Dilation(0.0F)).mirrored(false)
                .uv(105, 105).cuboid(-33.0F, -4.0F, 2.0F, 31.0F, 4.0F, 31.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(17.5F, -35.0F, -17.5F, 0.0F, 0.0F, 0.0F));

        ModelPartData Lamp = modelPartData.addChild("Lamp", ModelPartBuilder.create().uv(0, 0).cuboid(-20.0F, -2.0F, 15.0F, 5.0F, 2.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
                .uv(0, 13).cuboid(-20.0F, -7.5F, 15.0F, 5.0F, 6.0F, 5.0F, new Dilation(-0.5F)).mirrored(false)
                .uv(0, 7).cuboid(-20.0F, -8.0F, 15.0F, 5.0F, 1.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
                .uv(16, 13).cuboid(-19.0F, -9.0F, 16.0F, 3.0F, 1.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(17.5F, -41.0F, -17.5F, 0.0F, 0.0F, 0.0F));

        ModelPartData DoorLeft = modelPartData.addChild("DoorLeft", ModelPartBuilder.create().uv(182, 182).cuboid(-12.0F, -50.0F, -1.0F, 12.0F, 50.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
                .uv(0, 24).cuboid(-12.0F, -34.0F, -2.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
                .uv(4, 24).cuboid(-12.0F, -26.0F, -1.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(12.5F, 21.0F, -15.0F, 0.0F, 0.0F, 0.0F));

        ModelPartData DoorRight = modelPartData.addChild("DoorRight", ModelPartBuilder.create().uv(156, 156).cuboid(0.0F, -50.0F, -1.0F, 12.0F, 50.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
                .uv(221, 181).cuboid(12.0F, -50.0F, -2.0F, 1.0F, 50.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
                .uv(0, 30).cuboid(10.0F, -33.0F, -1.5F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-12.5F, 21.0F, -15.0F, 0.0F, 0.0F, 0.0F));
        return modelData;
    }


    public static TexturedModelData getTexturedModelData() {
        return TexturedModelData.of(getModelData(), 256, 256);
    }

    @Override
    public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        Base.render(matrixStack, buffer, packedLight, packedOverlay);
        Pillars.render(matrixStack, buffer, packedLight, packedOverlay);
        Frame.render(matrixStack, buffer, packedLight, packedOverlay);
        SideDoors.render(matrixStack, buffer, packedLight, packedOverlay);
        PPCBSign.render(matrixStack, buffer, packedLight, packedOverlay);
        Roof.render(matrixStack, buffer, packedLight, packedOverlay);
        Lamp.render(matrixStack, buffer, packedLight, packedOverlay);
        DoorLeft.render(matrixStack, buffer, packedLight, packedOverlay);
        DoorRight.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelPart bone, float x, float y, float z) {
        bone.pitch = x;
        bone.yaw = y;
        bone.roll = z;
    }

}