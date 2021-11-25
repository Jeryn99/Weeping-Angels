package me.suff.mc.angels.client.renders;// Made with Blockbench 4.0.5
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class MercyWings extends EntityModel<LivingEntity> {

    public final ModelPart Lwing;
    public final ModelPart RWing;

    public MercyWings(ModelPart root) {
        this.Lwing = root.getChild("Lwing");
        this.RWing = root.getChild("RWing");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Lwing = partdefinition.addOrReplaceChild("Lwing", CubeListBuilder.create().texOffs(8, 0).addBox(-3.0F, -22.0F, 1.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 24.0F, 0.25F));

        PartDefinition Main = Lwing.addOrReplaceChild("Main", CubeListBuilder.create().texOffs(19, 0).addBox(-1.0F, -6.75F, -0.25F, 2.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 20).addBox(-1.0F, -5.75F, -0.25F, 2.0F, 7.0F, 3.0F, new CubeDeformation(-0.2F))
                .texOffs(0, 0).addBox(-1.0F, -6.75F, 0.75F, 2.0F, 8.0F, 4.0F, new CubeDeformation(-0.21F)), PartPose.offsetAndRotation(-2.0F, -21.5F, 2.25F, -0.2618F, 0.3927F, 0.0F));

        PartDefinition LCap = Main.addOrReplaceChild("LCap", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -6.5F, 3.75F, -0.9599F, 0.0F, 0.0F));

        PartDefinition OpenRotPoint2 = LCap.addOrReplaceChild("OpenRotPoint2", CubeListBuilder.create().texOffs(37, 37).addBox(-3.0F, -1.0F, -1.75F, 2.0F, 2.0F, 10.0F, new CubeDeformation(-0.1F)), PartPose.offset(2.0F, 0.0F, -2.0F));

        PartDefinition _1 = Main.addOrReplaceChild("_1", CubeListBuilder.create().texOffs(19, 3).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 3.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.5F, 3.75F, -1.0908F, 0.0F, 0.0F));

        PartDefinition _2 = Main.addOrReplaceChild("_2", CubeListBuilder.create().texOffs(0, 0).addBox(-0.55F, -1.0F, -2.0F, 1.0F, 3.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.5F, 2.75F, -1.0908F, 0.0F, 0.0F));

        PartDefinition _3 = Main.addOrReplaceChild("_3", CubeListBuilder.create().texOffs(0, 20).addBox(-0.6F, -1.0F, -1.0F, 1.0F, 3.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 1.75F, -1.0908F, 0.0F, 0.0F));

        PartDefinition _4 = Main.addOrReplaceChild("_4", CubeListBuilder.create().texOffs(19, 25).addBox(-0.65F, -1.0F, -1.0F, 1.0F, 3.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, 1.75F, -1.1781F, 0.0F, 0.0F));

        PartDefinition _5 = Main.addOrReplaceChild("_5", CubeListBuilder.create().texOffs(34, 23).addBox(-0.7F, -1.0F, -1.0F, 1.0F, 3.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, 1.75F, -1.2654F, 0.0F, 0.0F));

        PartDefinition RWing = partdefinition.addOrReplaceChild("RWing", CubeListBuilder.create().texOffs(8, 0).mirror().addBox(1.0F, -22.0F, 1.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, 24.0F, 0.25F));

        PartDefinition Main2 = RWing.addOrReplaceChild("Main2", CubeListBuilder.create().texOffs(19, 0).mirror().addBox(-1.0F, -6.75F, -0.25F, 2.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 20).mirror().addBox(-1.0F, -5.75F, -0.25F, 2.0F, 7.0F, 3.0F, new CubeDeformation(-0.2F)).mirror(false)
                .texOffs(0, 0).mirror().addBox(-1.0F, -6.75F, 0.75F, 2.0F, 8.0F, 4.0F, new CubeDeformation(-0.21F)).mirror(false), PartPose.offsetAndRotation(2.0F, -21.5F, 2.25F, -0.2618F, -0.3927F, 0.0F));

        PartDefinition RCap = Main2.addOrReplaceChild("RCap", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -6.5F, 3.75F, -0.9599F, 0.0F, 0.0F));

        PartDefinition OpenRotPoint = RCap.addOrReplaceChild("OpenRotPoint", CubeListBuilder.create().texOffs(37, 37).mirror().addBox(-1.0F, -1.0F, -1.75F, 2.0F, 2.0F, 10.0F, new CubeDeformation(-0.1F)).mirror(false), PartPose.offset(0.0F, 0.0F, -2.0F));

        PartDefinition A = Main2.addOrReplaceChild("A", CubeListBuilder.create().texOffs(19, 3).mirror().addBox(-0.5F, -1.0F, -1.0F, 1.0F, 3.0F, 17.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -5.5F, 3.75F, -1.0908F, 0.0F, 0.0F));

        PartDefinition B = Main2.addOrReplaceChild("B", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.45F, -1.0F, -2.0F, 1.0F, 3.0F, 17.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -4.5F, 2.75F, -1.0908F, 0.0F, 0.0F));

        PartDefinition C = Main2.addOrReplaceChild("C", CubeListBuilder.create().texOffs(0, 20).mirror().addBox(-0.4F, -1.0F, -1.0F, 1.0F, 3.0F, 15.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -3.5F, 1.75F, -1.0908F, 0.0F, 0.0F));

        PartDefinition D = Main2.addOrReplaceChild("D", CubeListBuilder.create().texOffs(19, 25).mirror().addBox(-0.35F, -1.0F, -1.0F, 1.0F, 3.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -2.5F, 1.75F, -1.1781F, 0.0F, 0.0F));

        PartDefinition E = Main2.addOrReplaceChild("E", CubeListBuilder.create().texOffs(34, 23).mirror().addBox(-0.3F, -1.0F, -1.0F, 1.0F, 3.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -1.5F, 1.75F, -1.2654F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Lwing.render(poseStack, buffer, packedLight, packedOverlay);
        RWing.render(poseStack, buffer, packedLight, packedOverlay);
    }
}