package me.suff.mc.angels.client.models.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.suff.mc.angels.client.models.entity.ModelAngelaAngel;
import me.suff.mc.angels.client.models.entity.WAModels;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class SnowBodyModel extends EntityModel<Entity> {
    private final ModelPart All = null;
    private final ModelPart head = null;
    private final ModelPart body = null;
    private final ModelPart leftArm = null;
    private final ModelPart rightArm = null;
    private final ModelPart leftWing = null;
    private final ModelPart rightWing = null;
    private final ModelPart Legs = null;

    public SnowBodyModel() {

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