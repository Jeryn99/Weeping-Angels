package me.swirtzly.minecraft.angels.client.models.block;// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import me.swirtzly.minecraft.angels.client.models.entity.ModelAngelaAngel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class SnowBodyModel extends EntityModel<Entity> {
    private final ModelRenderer All;
    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer leftArm;
    private final ModelRenderer rightArm;
    private final ModelRenderer leftWing;
    private final ModelRenderer rightWing;
    private final ModelRenderer Legs;

    public SnowBodyModel() {
        textureWidth = 128;
        textureHeight = 128;

        All = new ModelRenderer(this);
        All.setRotationPoint(0.0F, 25.0F, 0.0F);
        setRotationAngle(All, 0.3054F, 0.0F, 0.0F);


        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, -12.0F, 0.0F);
        All.addChild(head);
        setRotationAngle(head, -0.5236F, 0.0F, 0.0F);
        head.setTextureOffset(0, 17).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
        head.setTextureOffset(72, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, -12.0F, 0.0F);
        All.addChild(body);
        body.setTextureOffset(56, 17).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);
        body.setTextureOffset(32, 17).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.5F, false);

        leftArm = new ModelRenderer(this);
        leftArm.setRotationPoint(-5.0F, -10.0F, 0.0F);
        All.addChild(leftArm);
        setRotationAngle(leftArm, -1.9635F, 0.0F, -0.3491F);
        leftArm.setTextureOffset(24, 59).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.0F, false);

        rightArm = new ModelRenderer(this);
        rightArm.setRotationPoint(5.0F, -10.0F, 0.0F);
        All.addChild(rightArm);
        setRotationAngle(rightArm, -2.1817F, 0.0F, 0.3054F);
        rightArm.setTextureOffset(10, 59).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.0F, false);

        leftWing = new ModelRenderer(this);
        leftWing.setRotationPoint(-1.0F, -7.0F, 2.0F);
        All.addChild(leftWing);
        setRotationAngle(leftWing, 0.0F, -0.7854F, 0.0F);
        leftWing.setTextureOffset(0, 101).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 5.0F, 3.0F, 0.0F, false);
        leftWing.setTextureOffset(6, 83).addBox(-1.0F, -8.9F, 5.0F, 2.0F, 14.0F, 1.0F, 0.0F, false);
        leftWing.setTextureOffset(18, 83).addBox(-1.0F, -6.9F, 3.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);
        leftWing.setTextureOffset(8, 33).addBox(-1.0F, -10.9F, 6.0F, 2.0F, 21.0F, 3.0F, 0.0F, false);
        leftWing.setTextureOffset(0, 33).addBox(-1.0F, -10.0F, 9.0F, 2.0F, 24.0F, 2.0F, 0.0F, false);
        leftWing.setTextureOffset(38, 59).addBox(-1.0F, -8.0F, 11.0F, 2.0F, 17.0F, 1.0F, 0.0F, false);

        rightWing = new ModelRenderer(this);
        rightWing.setRotationPoint(1.0F, -7.0F, 2.0F);
        All.addChild(rightWing);
        setRotationAngle(rightWing, 0.0F, 0.7854F, 0.0F);
        rightWing.setTextureOffset(10, 101).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 5.0F, 3.0F, 0.0F, false);
        rightWing.setTextureOffset(12, 83).addBox(-1.0F, -8.9F, 5.0F, 2.0F, 14.0F, 1.0F, 0.0F, false);
        rightWing.setTextureOffset(26, 83).addBox(-1.0F, -6.9F, 3.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);
        rightWing.setTextureOffset(18, 33).addBox(-1.0F, -10.0F, 9.0F, 2.0F, 24.0F, 2.0F, 0.0F, false);
        rightWing.setTextureOffset(0, 83).addBox(-1.0F, -8.0F, 11.0F, 2.0F, 17.0F, 1.0F, 0.0F, false);
        rightWing.setTextureOffset(0, 59).addBox(-1.0F, -10.9F, 6.0F, 2.0F, 21.0F, 3.0F, 0.0F, false);

        Legs = new ModelRenderer(this);
        Legs.setRotationPoint(0.0F, 9.25F, 0.0F);

    }

    @Override
    public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        All.render(matrixStack, buffer, packedLight, packedOverlay);
        Legs.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    public ResourceLocation getTexture() {
        return ModelAngelaAngel.ANGRY;
    }
}