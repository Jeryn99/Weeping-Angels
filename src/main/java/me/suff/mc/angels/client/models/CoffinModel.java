// Made with Blockbench 3.8.4
// Exported for Minecraft version 1.17
// Paste this class into your mod and generate all required imports

package me.suff.mc.angels.client.models;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class CoffinModel extends EntityModel<Entity> {
    private final ModelPart coffinBody;
    public final ModelPart door;

    public CoffinModel(ModelPart root) {
        coffinBody = root.getChild("coffinBody");
        door = root.getChild("door");
    }

    public static ModelData getModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData coffinBody = modelPartData.addChild("coffinBody", ModelPartBuilder.create().uv(0, 38).cuboid(-15.0F, -51.0F, 3.0F, 1.0F, 17.0F, 11.0F, new Dilation(0.0F)).mirrored(true)
                .uv(0, 38).cuboid(0.0F, -51.0F, 3.0F, 1.0F, 17.0F, 11.0F, new Dilation(0.0F)).mirrored(false)
                .uv(59, 61).cuboid(-15.0F, -68.0F, 3.0F, 1.0F, 6.0F, 11.0F, new Dilation(0.0F)).mirrored(true)
                .uv(59, 61).cuboid(0.0F, -68.0F, 3.0F, 1.0F, 6.0F, 11.0F, new Dilation(0.0F)).mirrored(false)
                .uv(13, 61).cuboid(-16.0F, -63.0F, 3.0F, 1.0F, 13.0F, 11.0F, new Dilation(0.0F)).mirrored(true)
                .uv(13, 61).cuboid(1.0F, -63.0F, 3.0F, 1.0F, 13.0F, 11.0F, new Dilation(0.0F)).mirrored(false)
                .uv(36, 0).cuboid(-15.0F, -69.0F, 3.0F, 16.0F, 1.0F, 11.0F, new Dilation(0.0F)).mirrored(true)
                .uv(36, 0).cuboid(-15.0F, -34.0F, 3.0F, 16.0F, 1.0F, 11.0F, new Dilation(0.0F)).mirrored(false)
                .uv(36, 36).cuboid(-15.0F, -69.0F, 13.0F, 16.0F, 35.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(7.0F, 57.0F, -6.0F, 0.0F, 0.0F, 0.0F));

        ModelPartData door = modelPartData.addChild("door", ModelPartBuilder.create().uv(0, 0).cuboid(-17.0F, -16.0F, -2.0F, 16.0F, 36.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
                .uv(36, 12).cuboid(-18.0F, -10.0F, -2.0F, 18.0F, 13.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(9.0F, 4.0F, -3.0F, 0.0F, 0.0F, 0.0F));
        return modelData;
    }


    public static TexturedModelData getTexturedModelData() {
        return TexturedModelData.of(getModelData(), 128, 128);
    }

    @Override
    public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        coffinBody.render(matrixStack, buffer, packedLight, packedOverlay);
        door.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelPart bone, float x, float y, float z) {
        bone.pitch = x;
        bone.yaw = y;
        bone.roll = z;
    }

}