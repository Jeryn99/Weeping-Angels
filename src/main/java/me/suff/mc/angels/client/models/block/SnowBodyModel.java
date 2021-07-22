package me.suff.mc.angels.client.models.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.suff.mc.angels.client.models.entity.ModelAngelaAngel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class SnowBodyModel extends EntityModel<Entity> {
    private final ModelPart All;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart leftArm;
    private final ModelPart rightArm;
    private final ModelPart leftWing;
    private final ModelPart rightWing;
    private final ModelPart Legs;

    public SnowBodyModel() {
        texWidth = 128;
        texHeight = 128;

        All = new ModelPart(this);
        All.setPos(0.0F, 25.0F, 0.0F);
        setRotationAngle(All, 0.3054F, 0.0F, 0.0F);


        head = new ModelPart(this);
        head.setPos(0.0F, -12.0F, 0.0F);
        All.addChild(head);
        setRotationAngle(head, -0.5236F, 0.0F, 0.0F);
        head.texOffs(0, 17).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
        head.texOffs(72, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F, false);

        body = new ModelPart(this);
        body.setPos(0.0F, -12.0F, 0.0F);
        All.addChild(body);
        body.texOffs(56, 17).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);
        body.texOffs(32, 17).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.5F, false);

        leftArm = new ModelPart(this);
        leftArm.setPos(-5.0F, -10.0F, 0.0F);
        All.addChild(leftArm);
        setRotationAngle(leftArm, -1.9635F, 0.0F, -0.3491F);
        leftArm.texOffs(24, 59).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.0F, false);

        rightArm = new ModelPart(this);
        rightArm.setPos(5.0F, -10.0F, 0.0F);
        All.addChild(rightArm);
        setRotationAngle(rightArm, -2.1817F, 0.0F, 0.3054F);
        rightArm.texOffs(10, 59).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.0F, false);

        leftWing = new ModelPart(this);
        leftWing.setPos(-1.0F, -7.0F, 2.0F);
        All.addChild(leftWing);
        setRotationAngle(leftWing, 0.0F, -0.7854F, 0.0F);
        leftWing.texOffs(0, 101).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 5.0F, 3.0F, 0.0F, false);
        leftWing.texOffs(6, 83).addBox(-1.0F, -8.9F, 5.0F, 2.0F, 14.0F, 1.0F, 0.0F, false);
        leftWing.texOffs(18, 83).addBox(-1.0F, -6.9F, 3.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);
        leftWing.texOffs(8, 33).addBox(-1.0F, -10.9F, 6.0F, 2.0F, 21.0F, 3.0F, 0.0F, false);
        leftWing.texOffs(0, 33).addBox(-1.0F, -10.0F, 9.0F, 2.0F, 24.0F, 2.0F, 0.0F, false);
        leftWing.texOffs(38, 59).addBox(-1.0F, -8.0F, 11.0F, 2.0F, 17.0F, 1.0F, 0.0F, false);

        rightWing = new ModelPart(this);
        rightWing.setPos(1.0F, -7.0F, 2.0F);
        All.addChild(rightWing);
        setRotationAngle(rightWing, 0.0F, 0.7854F, 0.0F);
        rightWing.texOffs(10, 101).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 5.0F, 3.0F, 0.0F, false);
        rightWing.texOffs(12, 83).addBox(-1.0F, -8.9F, 5.0F, 2.0F, 14.0F, 1.0F, 0.0F, false);
        rightWing.texOffs(26, 83).addBox(-1.0F, -6.9F, 3.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);
        rightWing.texOffs(18, 33).addBox(-1.0F, -10.0F, 9.0F, 2.0F, 24.0F, 2.0F, 0.0F, false);
        rightWing.texOffs(0, 83).addBox(-1.0F, -8.0F, 11.0F, 2.0F, 17.0F, 1.0F, 0.0F, false);
        rightWing.texOffs(0, 59).addBox(-1.0F, -10.9F, 6.0F, 2.0F, 21.0F, 3.0F, 0.0F, false);

        Legs = new ModelPart(this);
        Legs.setPos(0.0F, 9.25F, 0.0F);

    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        All.render(matrixStack, buffer, packedLight, packedOverlay);
        Legs.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    public ResourceLocation getTexture() {
        return ModelAngelaAngel.ANGRY;
    }
}