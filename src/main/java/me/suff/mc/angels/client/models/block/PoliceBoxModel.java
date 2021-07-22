package me.suff.mc.angels.client.models.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;

public class PoliceBoxModel extends EntityModel {
    public final ModelPart DoorRight;
    private final ModelPart Base;
    private final ModelPart Pillars;
    private final ModelPart Frame;
    private final ModelPart SideDoors;
    private final ModelPart PPCBSign;
    private final ModelPart Roof;
    private final ModelPart Lamp;
    private final ModelPart DoorLeft;

    public PoliceBoxModel() {
        texWidth = 256;
        texHeight = 256;

        Base = new ModelPart(this);
        Base.setPos(0.0F, 24.0F, 0.0F);
        Base.texOffs(0, 0).addBox(-18.5F, -3.0F, -18.5F, 37.0F, 3.0F, 37.0F, 0.0F, false);

        Pillars = new ModelPart(this);
        Pillars.setPos(0.0F, 24.0F, 0.0F);
        Pillars.texOffs(0, 193).addBox(-17.5F, -60.0F, -17.5F, 4.0F, 57.0F, 4.0F, 0.0F, false);
        Pillars.texOffs(140, 191).addBox(13.5F, -60.0F, -17.5F, 4.0F, 57.0F, 4.0F, 0.0F, false);
        Pillars.texOffs(124, 191).addBox(13.5F, -60.0F, 13.5F, 4.0F, 57.0F, 4.0F, 0.0F, false);
        Pillars.texOffs(108, 191).addBox(-17.5F, -60.0F, 13.5F, 4.0F, 57.0F, 4.0F, 0.0F, false);
        Pillars.texOffs(99, 40).addBox(-16.5F, -62.0F, -16.5F, 33.0F, 2.0F, 33.0F, 0.0F, false);

        Frame = new ModelPart(this);
        Frame.setPos(0.0F, 24.0F, 0.0F);
        Frame.texOffs(209, 181).addBox(-13.5F, -53.0F, -17.0F, 1.0F, 50.0F, 2.0F, 0.0F, false);
        Frame.texOffs(215, 181).addBox(-17.0F, -53.0F, 12.5F, 2.0F, 50.0F, 1.0F, 0.0F, false);
        Frame.texOffs(209, 181).addBox(12.5F, -53.0F, 15.0F, 1.0F, 50.0F, 2.0F, 0.0F, false);
        Frame.texOffs(215, 181).addBox(15.0F, -53.0F, -13.5F, 2.0F, 50.0F, 1.0F, 0.0F, false);
        Frame.texOffs(209, 181).addBox(12.5F, -53.0F, -17.0F, 1.0F, 50.0F, 2.0F, 0.0F, false);
        Frame.texOffs(215, 181).addBox(-17.0F, -53.0F, -13.5F, 2.0F, 50.0F, 1.0F, 0.0F, false);
        Frame.texOffs(209, 181).addBox(-13.5F, -53.0F, 15.0F, 1.0F, 50.0F, 2.0F, 0.0F, false);
        Frame.texOffs(215, 181).addBox(15.0F, -53.0F, 12.5F, 2.0F, 50.0F, 1.0F, 0.0F, false);
        Frame.texOffs(221, 181).addBox(-17.0F, -53.0F, -0.5F, 1.0F, 50.0F, 1.0F, 0.0F, false);
        Frame.texOffs(221, 181).addBox(-0.5F, -53.0F, 16.0F, 1.0F, 50.0F, 1.0F, 0.0F, false);
        Frame.texOffs(221, 181).addBox(16.0F, -53.0F, -0.5F, 1.0F, 50.0F, 1.0F, 0.0F, false);
        Frame.texOffs(0, 221).addBox(-17.0F, -54.0F, -17.0F, 34.0F, 1.0F, 34.0F, 0.0F, false);

        SideDoors = new ModelPart(this);
        SideDoors.setPos(1.0F, 21.0F, -16.5F);
        SideDoors.texOffs(52, 118).addBox(-17.0F, -50.0F, 4.0F, 1.0F, 50.0F, 25.0F, 0.0F, false);
        SideDoors.texOffs(104, 140).addBox(-13.5F, -50.0F, 31.5F, 25.0F, 50.0F, 1.0F, 0.0F, false);
        SideDoors.texOffs(0, 118).addBox(14.0F, -50.0F, 4.0F, 1.0F, 50.0F, 25.0F, 0.0F, false);

        PPCBSign = new ModelPart(this);
        PPCBSign.setPos(0.0F, 24.0F, 0.0F);
        PPCBSign.texOffs(0, 40).addBox(-15.5F, -59.0F, -18.5F, 31.0F, 5.0F, 37.0F, 0.0F, false);
        PPCBSign.texOffs(0, 82).addBox(-18.5F, -59.0F, -15.5F, 37.0F, 5.0F, 31.0F, 0.0F, false);

        Roof = new ModelPart(this);
        Roof.setPos(17.5F, -35.0F, -17.5F);
        Roof.texOffs(111, 0).addBox(-32.0F, -6.0F, 3.0F, 29.0F, 2.0F, 29.0F, 0.0F, false);
        Roof.texOffs(105, 105).addBox(-33.0F, -4.0F, 2.0F, 31.0F, 4.0F, 31.0F, 0.0F, false);

        Lamp = new ModelPart(this);
        Lamp.setPos(17.5F, -41.0F, -17.5F);
        Lamp.texOffs(0, 0).addBox(-20.0F, -2.0F, 15.0F, 5.0F, 2.0F, 5.0F, 0.0F, false);
        Lamp.texOffs(0, 13).addBox(-20.0F, -7.5F, 15.0F, 5.0F, 6.0F, 5.0F, -0.5F, false);
        Lamp.texOffs(0, 7).addBox(-20.0F, -8.0F, 15.0F, 5.0F, 1.0F, 5.0F, 0.0F, false);
        Lamp.texOffs(16, 13).addBox(-19.0F, -9.0F, 16.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);

        DoorLeft = new ModelPart(this);
        DoorLeft.setPos(12.5F, 21.0F, -15.0F);
        DoorLeft.texOffs(182, 182).addBox(-12.0F, -50.0F, -1.0F, 12.0F, 50.0F, 1.0F, 0.0F, false);
        DoorLeft.texOffs(0, 24).addBox(-12.0F, -34.0F, -2.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);
        DoorLeft.texOffs(4, 24).addBox(-12.0F, -26.0F, -1.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        DoorRight = new ModelPart(this);
        DoorRight.setPos(-12.5F, 21.0F, -15.0F);
        DoorRight.texOffs(156, 156).addBox(0.0F, -50.0F, -1.0F, 12.0F, 50.0F, 1.0F, 0.0F, false);
        DoorRight.texOffs(221, 181).addBox(12.0F, -50.0F, -2.0F, 1.0F, 50.0F, 1.0F, 0.0F, false);
        DoorRight.texOffs(0, 30).addBox(10.0F, -33.0F, -1.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
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