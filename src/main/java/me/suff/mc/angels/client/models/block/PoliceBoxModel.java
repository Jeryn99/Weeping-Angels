package me.suff.mc.angels.client.models.block;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class PoliceBoxModel extends EntityModel<Entity> {
    private final ModelRenderer Posts;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer Panels;
    private final ModelRenderer cube_r4;
    private final ModelRenderer cube_r5;
    private final ModelRenderer PPCB;
    private final ModelRenderer cube_r6;
    private final ModelRenderer cube_r7;
    private final ModelRenderer cube_r8;
    private final ModelRenderer Roof;
    private final ModelRenderer Lamp;
    private final ModelRenderer Doors;
    private final ModelRenderer RDoor;
    private final ModelRenderer LDoor;
    private final ModelRenderer bb_main;

    public PoliceBoxModel() {
        texWidth = 256;
        texHeight = 256;

        Posts = new ModelRenderer(this);
        Posts.setPos(-14.0F, 21.0F, -15.0F);
        Posts.texOffs(28, 102).addBox(-3.0F, -57.0F, -2.0F, 3.0F, 57.0F, 3.0F, 0.0F, false);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setPos(14.0F, 3.0F, 15.0F);
        Posts.addChild(cube_r1);
        setRotationAngle(cube_r1, -3.1416F, 0.0F, 3.1416F);
        cube_r1.texOffs(28, 102).addBox(-17.0F, -60.0F, -17.0F, 3.0F, 57.0F, 3.0F, 0.0F, false);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setPos(14.0F, 3.0F, 15.0F);
        Posts.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0F, -1.5708F, 0.0F);
        cube_r2.texOffs(28, 102).addBox(-17.0F, -60.0F, -17.0F, 3.0F, 57.0F, 3.0F, 0.0F, false);

        cube_r3 = new ModelRenderer(this);
        cube_r3.setPos(14.0F, 3.0F, 15.0F);
        Posts.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.0F, 1.5708F, 0.0F);
        cube_r3.texOffs(28, 102).addBox(-17.0F, -60.0F, -17.0F, 3.0F, 57.0F, 3.0F, 0.0F, false);

        Panels = new ModelRenderer(this);
        Panels.setPos(0.0F, 12.0F, 0.0F);
        Panels.texOffs(84, 74).addBox(-15.0F, -42.0F, -14.0F, 1.0F, 51.0F, 28.0F, 0.0F, false);
        Panels.texOffs(40, 102).addBox(-14.0F, -42.0F, -15.0F, 1.0F, 51.0F, 1.0F, 0.0F, false);
        Panels.texOffs(44, 102).addBox(13.0F, -42.0F, -15.0F, 1.0F, 51.0F, 1.0F, 0.0F, false);
        Panels.texOffs(84, 72).addBox(-13.0F, -42.0F, -15.0F, 26.0F, 1.0F, 1.0F, 0.0F, false);

        cube_r4 = new ModelRenderer(this);
        cube_r4.setPos(0.0F, 12.0F, 0.0F);
        Panels.addChild(cube_r4);
        setRotationAngle(cube_r4, -3.1416F, 0.0F, 3.1416F);
        cube_r4.texOffs(84, 74).addBox(-15.0F, -54.0F, -14.0F, 1.0F, 51.0F, 28.0F, 0.0F, false);

        cube_r5 = new ModelRenderer(this);
        cube_r5.setPos(0.0F, 12.0F, 0.0F);
        Panels.addChild(cube_r5);
        setRotationAngle(cube_r5, 0.0F, 1.5708F, 0.0F);
        cube_r5.texOffs(84, 74).addBox(-15.0F, -54.0F, -14.0F, 1.0F, 51.0F, 28.0F, 0.0F, false);

        PPCB = new ModelRenderer(this);
        PPCB.setPos(0.0F, 24.0F, 0.0F);
        PPCB.texOffs(90, 39).addBox(-15.0F, -59.0F, -18.0F, 30.0F, 5.0F, 3.0F, 0.0F, false);

        cube_r6 = new ModelRenderer(this);
        cube_r6.setPos(0.0F, 0.0F, 0.0F);
        PPCB.addChild(cube_r6);
        setRotationAngle(cube_r6, 0.0F, -1.5708F, 0.0F);
        cube_r6.texOffs(90, 39).addBox(-15.0F, -59.0F, -18.0F, 30.0F, 5.0F, 3.0F, 0.0F, false);

        cube_r7 = new ModelRenderer(this);
        cube_r7.setPos(0.0F, 0.0F, 0.0F);
        PPCB.addChild(cube_r7);
        setRotationAngle(cube_r7, -3.1416F, 0.0F, 3.1416F);
        cube_r7.texOffs(90, 39).addBox(-15.0F, -59.0F, -18.0F, 30.0F, 5.0F, 3.0F, 0.0F, false);

        cube_r8 = new ModelRenderer(this);
        cube_r8.setPos(0.0F, 0.0F, 0.0F);
        PPCB.addChild(cube_r8);
        setRotationAngle(cube_r8, 0.0F, 1.5708F, 0.0F);
        cube_r8.texOffs(90, 39).addBox(-15.0F, -59.0F, -18.0F, 30.0F, 5.0F, 3.0F, 0.0F, false);

        Roof = new ModelRenderer(this);
        Roof.setPos(0.0F, 25.0F, 0.0F);
        Roof.texOffs(0, 39).addBox(-15.0F, -63.0F, -15.0F, 30.0F, 3.0F, 30.0F, 0.0F, false);
        Roof.texOffs(0, 72).addBox(-14.0F, -65.0F, -14.0F, 28.0F, 2.0F, 28.0F, 0.0F, false);

        Lamp = new ModelRenderer(this);
        Lamp.setPos(0.0F, -40.0F, 0.0F);
        Lamp.texOffs(0, 0).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 2.0F, 6.0F, 0.0F, false);
        Lamp.texOffs(0, 15).addBox(-2.5F, -7.0F, -2.5F, 5.0F, 5.0F, 5.0F, 0.0F, false);
        Lamp.texOffs(0, 8).addBox(-3.0F, -8.0F, -3.0F, 6.0F, 1.0F, 6.0F, 0.0F, false);
        Lamp.texOffs(20, 11).addBox(-2.0F, -9.0F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);

        Doors = new ModelRenderer(this);
        Doors.setPos(0.0F, 24.0F, 1.0F);


        RDoor = new ModelRenderer(this);
        RDoor.setPos(13.0F, -3.0F, -15.0F);
        Doors.addChild(RDoor);
        RDoor.texOffs(48, 102).addBox(-13.0F, -50.0F, -1.0F, 13.0F, 50.0F, 1.0F, 0.0F, false);
        RDoor.texOffs(0, 0).addBox(-12.0F, -33.0F, -2.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        RDoor.texOffs(24, 0).addBox(-13.0F, -27.0F, 0.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);

        LDoor = new ModelRenderer(this);
        LDoor.setPos(-13.0F, -3.0F, -15.0F);
        Doors.addChild(LDoor);
        LDoor.texOffs(0, 102).addBox(0.0F, -50.0F, -1.0F, 13.0F, 50.0F, 1.0F, 0.0F, false);
        LDoor.texOffs(15, 55).addBox(11.5F, -2.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
        LDoor.texOffs(0, 51).addBox(3.0F, -34.0F, 0.0F, 6.0F, 7.0F, 2.0F, 0.0F, false);
        LDoor.texOffs(0, 40).addBox(3.0F, -36.0F, 1.0F, 7.0F, 8.0F, 0.0F, 0.0F, false);
        LDoor.texOffs(0, 8).addBox(10.0F, -32.0F, -2.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        LDoor.texOffs(28, 5).addBox(12.0F, -27.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        bb_main = new ModelRenderer(this);
        bb_main.setPos(0.0F, 24.0F, 0.0F);
        bb_main.texOffs(0, 0).addBox(-18.0F, -3.0F, -18.0F, 36.0F, 3.0F, 36.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Posts.render(matrixStack, buffer, packedLight, packedOverlay);
        Panels.render(matrixStack, buffer, packedLight, packedOverlay);
        PPCB.render(matrixStack, buffer, packedLight, packedOverlay);
        Roof.render(matrixStack, buffer, packedLight, packedOverlay);
        Lamp.render(matrixStack, buffer, packedLight, packedOverlay);
        Doors.render(matrixStack, buffer, packedLight, packedOverlay);
        bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}