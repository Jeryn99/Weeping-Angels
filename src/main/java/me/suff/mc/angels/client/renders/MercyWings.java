package me.suff.mc.angels.client.renders;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class MercyWings extends EntityModel<LivingEntity> {

    public final ModelPart Lwing;
    public final ModelPart RWing;

    public final ModelPart LWingFeathers1, LWingFeathers2, LWingFeathers3, LWingFeathers4, LWingFeathers5;
    public final ModelPart RWingFeathers1, RWingFeathers2, RWingFeathers3, RWingFeathers4, RWingFeathers5;
    private final ModelPart rightMain;
    private final ModelPart leftMain;

    public MercyWings(ModelPart root) {
        this.Lwing = root.getChild("LWing");
        ModelPart leftFeathers = Lwing.getChild("Main");
        this.leftMain = leftFeathers;
        this.LWingFeathers1 = leftFeathers.getChild("L1").getChild("Feather6");
        this.LWingFeathers2 = leftFeathers.getChild("L2").getChild("Feather7");
        this.LWingFeathers3 = leftFeathers.getChild("L3").getChild("Feather8");
        this.LWingFeathers4 = leftFeathers.getChild("L4").getChild("Feather9");
        this.LWingFeathers5 = leftFeathers.getChild("L5").getChild("Feather10");


        this.RWing = root.getChild("RWing");

        ModelPart rightFeathers = RWing.getChild("Main2");
        this.rightMain = rightFeathers;
        this.RWingFeathers1 = rightFeathers.getChild("R1").getChild("Feather");
        this.RWingFeathers2 = rightFeathers.getChild("R2").getChild("Feather2");
        this.RWingFeathers3 = rightFeathers.getChild("R3").getChild("Feather3");
        this.RWingFeathers4 = rightFeathers.getChild("R4").getChild("Feather4");
        this.RWingFeathers5 = rightFeathers.getChild("R5").getChild("Feather5");

    }

    public static LayerDefinition getModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition LWing = partdefinition.addOrReplaceChild("LWing", CubeListBuilder.create().texOffs(8, 0).addBox(-3.0F, -22.0F, 1.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 23.0F, 0.5F));

        PartDefinition Main = LWing.addOrReplaceChild("Main", CubeListBuilder.create().texOffs(19, 0).addBox(-1.0F, -6.75F, -0.25F, 2.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 20).addBox(-1.0F, -5.75F, -0.25F, 2.0F, 7.0F, 3.0F, new CubeDeformation(-0.2F))
                .texOffs(0, 0).addBox(-1.0F, -6.75F, 0.75F, 2.0F, 8.0F, 4.0F, new CubeDeformation(-0.21F)), PartPose.offsetAndRotation(-2.0F, -21.5F, 2.25F, -0.2618F, 0.4363F, 0.0F));

        PartDefinition LCap = Main.addOrReplaceChild("LCap", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -6.5F, 3.75F, -0.9599F, 0.0F, 0.0F));

        PartDefinition OpenRotPoint2 = LCap.addOrReplaceChild("OpenRotPoint2", CubeListBuilder.create().texOffs(37, 37).addBox(-1.0F, -1.0F, -1.75F, 2.0F, 2.0F, 10.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.0F, 0.0F, -2.0F));

        PartDefinition L1 = Main.addOrReplaceChild("L1", CubeListBuilder.create().texOffs(19, 3).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 3.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.5F, 3.75F, -1.0908F, 0.0F, 0.0F));

        PartDefinition Feather6 = L1.addOrReplaceChild("Feather6", CubeListBuilder.create().texOffs(0, 32).mirror().addBox(0.0F, -1.0F, -2.0F, 0.0F, 4.0F, 28.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.25F, 2.0F));

        PartDefinition L2 = Main.addOrReplaceChild("L2", CubeListBuilder.create().texOffs(0, 0).addBox(-0.55F, -1.0F, -2.0F, 1.0F, 3.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.5F, 2.75F, -1.0908F, 0.0F, 0.0F));

        PartDefinition Feather7 = L2.addOrReplaceChild("Feather7", CubeListBuilder.create().texOffs(0, 32).mirror().addBox(-0.05F, -1.0F, 0.0F, 0.0F, 4.0F, 28.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.25F, 1.0F));

        PartDefinition L3 = Main.addOrReplaceChild("L3", CubeListBuilder.create().texOffs(0, 20).addBox(-0.6F, -1.0F, -1.0F, 1.0F, 3.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 1.75F, -1.0908F, 0.0F, 0.0F));

        PartDefinition Feather8 = L3.addOrReplaceChild("Feather8", CubeListBuilder.create().texOffs(0, 36).mirror().addBox(-0.1F, -1.0F, 1.0F, 0.0F, 4.0F, 24.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.25F, 1.0F));

        PartDefinition L4 = Main.addOrReplaceChild("L4", CubeListBuilder.create().texOffs(19, 25).addBox(-0.65F, -1.0F, -1.0F, 1.0F, 3.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, 1.75F, -1.1781F, 0.0F, 0.0F));

        PartDefinition Feather9 = L4.addOrReplaceChild("Feather9", CubeListBuilder.create().texOffs(0, 34).mirror().addBox(-0.15F, -0.5F, 1.5F, 0.0F, 3.0F, 21.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -0.25F, 0.5F));

        PartDefinition L5 = Main.addOrReplaceChild("L5", CubeListBuilder.create().texOffs(34, 23).addBox(-0.7F, -1.0F, -1.0F, 1.0F, 3.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, 1.75F, -1.2654F, 0.0F, 0.0F));

        PartDefinition Feather10 = L5.addOrReplaceChild("Feather10", CubeListBuilder.create().texOffs(0, 40).mirror().addBox(-0.2F, -0.75F, 1.5F, 0.0F, 2.0F, 15.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.25F, 0.5F));

        PartDefinition RWing = partdefinition.addOrReplaceChild("RWing", CubeListBuilder.create().texOffs(8, 0).mirror().addBox(1.0F, -22.0F, 1.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, 23.0F, 0.5F));

        PartDefinition Main2 = RWing.addOrReplaceChild("Main2", CubeListBuilder.create().texOffs(19, 0).mirror().addBox(-1.0F, -6.75F, -0.25F, 2.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 20).mirror().addBox(-1.0F, -5.75F, -0.25F, 2.0F, 7.0F, 3.0F, new CubeDeformation(-0.2F)).mirror(false)
                .texOffs(0, 0).mirror().addBox(-1.0F, -6.75F, 0.75F, 2.0F, 8.0F, 4.0F, new CubeDeformation(-0.21F)).mirror(false), PartPose.offsetAndRotation(2.0F, -21.5F, 2.25F, -0.2618F, -0.4363F, 0.0F));

        PartDefinition RCap = Main2.addOrReplaceChild("RCap", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -6.5F, 3.75F, -0.9599F, 0.0F, 0.0F));

        PartDefinition OpenRotPoint = RCap.addOrReplaceChild("OpenRotPoint", CubeListBuilder.create().texOffs(37, 37).mirror().addBox(-1.0F, -1.0F, -1.75F, 2.0F, 2.0F, 10.0F, new CubeDeformation(-0.1F)).mirror(false), PartPose.offset(0.0F, 0.0F, -2.0F));

        PartDefinition R1 = Main2.addOrReplaceChild("R1", CubeListBuilder.create().texOffs(19, 3).mirror().addBox(-0.5F, -1.0F, -1.0F, 1.0F, 3.0F, 17.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -5.5F, 3.75F, -1.0908F, 0.0F, 0.0F));

        PartDefinition Feather = R1.addOrReplaceChild("Feather", CubeListBuilder.create().texOffs(0, 32).addBox(0.0F, -1.0F, -2.0F, 0.0F, 4.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.25F, 2.0F));

        PartDefinition R2 = Main2.addOrReplaceChild("R2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.45F, -1.0F, -2.0F, 1.0F, 3.0F, 17.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -4.5F, 2.75F, -1.0908F, 0.0F, 0.0F));

        PartDefinition Feather2 = R2.addOrReplaceChild("Feather2", CubeListBuilder.create().texOffs(0, 32).addBox(0.05F, -1.0F, 0.0F, 0.0F, 4.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.25F, 1.0F));

        PartDefinition R3 = Main2.addOrReplaceChild("R3", CubeListBuilder.create().texOffs(0, 20).mirror().addBox(-0.4F, -1.0F, -1.0F, 1.0F, 3.0F, 15.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -3.5F, 1.75F, -1.0908F, 0.0F, 0.0F));

        PartDefinition Feather3 = R3.addOrReplaceChild("Feather3", CubeListBuilder.create().texOffs(0, 36).addBox(0.1F, -1.0F, 1.0F, 0.0F, 4.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.25F, 1.0F));

        PartDefinition R4 = Main2.addOrReplaceChild("R4", CubeListBuilder.create().texOffs(19, 25).mirror().addBox(-0.35F, -1.0F, -1.0F, 1.0F, 3.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -2.5F, 1.75F, -1.1781F, 0.0F, 0.0F));

        PartDefinition Feather4 = R4.addOrReplaceChild("Feather4", CubeListBuilder.create().texOffs(0, 34).addBox(0.15F, -0.5F, 1.5F, 0.0F, 3.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.25F, 0.5F));

        PartDefinition R5 = Main2.addOrReplaceChild("R5", CubeListBuilder.create().texOffs(34, 23).mirror().addBox(-0.3F, -1.0F, -1.0F, 1.0F, 3.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -1.5F, 1.75F, -1.2654F, 0.0F, 0.0F));

        PartDefinition Feather5 = R5.addOrReplaceChild("Feather5", CubeListBuilder.create().texOffs(0, 40).addBox(0.2F, -0.75F, 1.5F, 0.0F, 2.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.25F, 0.5F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(LivingEntity james, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean isFlying = james.isFallFlying();
        LWingFeathers1.visible = LWingFeathers2.visible = LWingFeathers3.visible = LWingFeathers4.visible = LWingFeathers5.visible = isFlying;
        RWingFeathers1.visible = RWingFeathers2.visible = RWingFeathers3.visible = RWingFeathers4.visible = RWingFeathers5.visible = isFlying;

        leftMain.yRot = isFlying ? (float) Math.toRadians(Mth.clamp(james.getFallFlyingTicks() * 2, 0, 45)) : -25;
        rightMain.yRot = isFlying ? (float) Math.toRadians(-Mth.clamp(james.getFallFlyingTicks() * 2, 0, 45)) : 25;

    }


    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Lwing.render(poseStack, buffer, packedLight, packedOverlay);
        RWing.render(poseStack, buffer, packedLight, packedOverlay);
    }
}