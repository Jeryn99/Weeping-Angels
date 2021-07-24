package me.suff.mc.angels.client.models.block;// Made with Blockbench 3.8.4
// Exported for Minecraft version 1.15 - 1.16
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class CoffinModel extends EntityModel<Entity> {
    public final ModelRenderer door;
    public final ModelRenderer goodHandle;
    public final ModelRenderer badHandle;
    private final ModelRenderer main;
    private final ModelRenderer cube_r1;

    public CoffinModel() {
        texWidth = 128;
        texHeight = 128;

        main = new ModelRenderer(this);
        main.setPos(7.0F, 57.0F, -6.0F);
        main.texOffs(0, 38).addBox(-15.0F, -51.0F, 3.0F, 1.0F, 17.0F, 11.0F, 0.0F, true);
        main.texOffs(0, 38).addBox(0.0F, -51.0F, 3.0F, 1.0F, 17.0F, 11.0F, 0.0F, false);
        main.texOffs(59, 61).addBox(-15.0F, -68.0F, 3.0F, 1.0F, 6.0F, 11.0F, 0.0F, true);
        main.texOffs(59, 61).addBox(0.0F, -68.0F, 3.0F, 1.0F, 6.0F, 11.0F, 0.0F, false);
        main.texOffs(13, 61).addBox(-16.0F, -63.0F, 3.0F, 1.0F, 13.0F, 11.0F, 0.0F, true);
        main.texOffs(13, 61).addBox(1.0F, -63.0F, 3.0F, 1.0F, 13.0F, 11.0F, 0.0F, false);
        main.texOffs(70, 28).addBox(-15.0F, -69.0F, 3.0F, 16.0F, 1.0F, 11.0F, 0.0F, false);
        main.texOffs(36, 0).addBox(-15.0F, -34.0F, 3.0F, 16.0F, 1.0F, 11.0F, 0.0F, false);
        main.texOffs(36, 36).addBox(-15.0F, -69.0F, 13.0F, 16.0F, 35.0F, 1.0F, 0.0F, false);

        door = new ModelRenderer(this);
        door.setPos(9.0F, 4.0F, -3.0F);
        door.texOffs(0, 0).addBox(-17.0F, -16.0F, -2.0F, 16.0F, 36.0F, 2.0F, 0.0F, false);
        door.texOffs(36, 12).addBox(-18.0F, -10.0F, -2.0F, 18.0F, 13.0F, 2.0F, 0.0F, false);

        goodHandle = new ModelRenderer(this);
        goodHandle.setPos(-17.25F, -1.725F, -2.625F);
        door.addChild(goodHandle);
        goodHandle.texOffs(37, 29).addBox(0.25F, -3.275F, -0.125F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        badHandle = new ModelRenderer(this);
        badHandle.setPos(-9.0F, 20.0F, 3.0F);
        door.addChild(badHandle);


        cube_r1 = new ModelRenderer(this);
        cube_r1.setPos(-8.25F, -21.725F, -5.625F);
        badHandle.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.1745F, 0.0F, 0.2182F);
        cube_r1.texOffs(41, 29).addBox(0.1927F, -3.2401F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
    }

    /**
     * Sets this entity's model rotation angles
     *
     * @param pEntityIn
     * @param pLimbSwing
     * @param pLimbSwingAmount
     * @param pAgeInTicks
     * @param pNetHeadYaw
     * @param pHeadPitch
     */
    @Override
    public void setupAnim(Entity pEntityIn, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        main.render(matrixStack, buffer, packedLight, packedOverlay);
        door.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}