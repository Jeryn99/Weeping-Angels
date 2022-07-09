package craig.software.mc.angels.client.models.entity;


import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3d;
import craig.software.mc.angels.client.poses.WeepingAngelPose;
import craig.software.mc.angels.common.blockentities.PlinthBlockEntity;
import craig.software.mc.angels.common.blockentities.StatueBlockEntity;
import craig.software.mc.angels.common.entities.WeepingAngel;
import craig.software.mc.angels.common.variants.AngelVariants;
import craig.software.mc.angels.common.variants.AngelVariant;
import craig.software.mc.angels.utils.Pair;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ModelVAWeepingAngel extends EntityModel<WeepingAngel> implements IAngelModel {


    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart leftArm;
    private final ModelPart rightArm;
    private final ModelPart Legs;
    private final ModelPart leftWing;
    private final ModelPart rightWing;
    private WeepingAngelPose weepingPose = WeepingAngelPose.ANGRY;
    private Pair<ModelPart, Vector3d> headData;

    public ModelVAWeepingAngel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.leftArm = root.getChild("leftArm");
        this.rightArm = root.getChild("rightArm");
        this.Legs = root.getChild("Legs");
        this.leftWing = root.getChild("leftWing");
        this.rightWing = root.getChild("rightWing");
    }

    public static LayerDefinition getModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition headband_r1 = head.addOrReplaceChild("headband_r1", CubeListBuilder.create().texOffs(28, 53).addBox(-4.5F, 0.0F, -4.75F, 9.0F, 1.0F, 9.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -6.5F, 0.25F, -0.1745F, 0.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(26, 18).addBox(-4.0F, -24.0F, -2.0F, 8.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition leftArm = partdefinition.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(0, 46).addBox(-1.0F, -1.5F, -1.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(35, 45).addBox(-1.0F, 5.5F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 1.5F, 0.0F));

        PartDefinition rightArm = partdefinition.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(47, 43).addBox(-2.0F, -1.5F, -1.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(19, 46).addBox(-2.0F, 5.5F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 1.5F, 0.0F));

        PartDefinition Legs = partdefinition.addOrReplaceChild("Legs", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -14.0F, -2.5F, 8.0F, 12.0F, 5.0F, new CubeDeformation(0.3F))
                .texOffs(25, 9).addBox(-5.0F, -2.0F, -3.5F, 10.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 56).addBox(-3.5F, -8.0F, -1.5F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(16, 55).addBox(0.5F, -8.0F, -1.5F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition SKIRT_r1 = Legs.addOrReplaceChild("SKIRT_r1", CubeListBuilder.create().texOffs(38, 63).addBox(-5.25F, -2.0F, -3.5F, 10.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.0F, 0.0F, 0.1309F));

        PartDefinition leftWing = partdefinition.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(28, 32).mirror().addBox(-0.5F, -2.0F, 1.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 0).mirror().addBox(-0.5F, 8.0F, 6.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 5).mirror().addBox(-0.5F, 11.0F, 7.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(50, 2).mirror().addBox(-0.5F, 5.0F, 4.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(12, 33).mirror().addBox(-0.5F, 3.0F, 4.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(43, 25).mirror().addBox(-0.5F, 1.0F, 2.0F, 1.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 34).mirror().addBox(-0.5F, -1.0F, -1.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(48, 34).mirror().addBox(-0.5F, -3.0F, 3.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(50, 18).mirror().addBox(-0.5F, -4.0F, 4.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.5F, 2.5F, 2.0F));

        PartDefinition rightWing = partdefinition.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(28, 32).addBox(-0.5F, -2.0F, 1.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(21, 16).addBox(-0.5F, 8.0F, 6.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 16).addBox(-0.5F, 11.0F, 7.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 2).addBox(-0.5F, 5.0F, 4.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(12, 33).addBox(-0.5F, 3.0F, 4.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(43, 25).addBox(-0.5F, 1.0F, 2.0F, 1.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 34).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(48, 34).addBox(-0.5F, -3.0F, 3.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(50, 18).addBox(-0.5F, -4.0F, 4.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 2.5F, 2.0F));

        return LayerDefinition.create(meshdefinition, 72, 72);
    }

    @Override
    public void setupAnim(@Nullable WeepingAngel weepingAngel, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        WeepingAngelPose pose = getAngelPose();
        if (weepingAngel != null) {
            pose = WeepingAngelPose.getPose(weepingAngel.getAngelPose());
        }

        boolean isAngry = pose.getEmotion() == WeepingAngelPose.Emotion.ANGRY || pose.getEmotion() == WeepingAngelPose.Emotion.SCREAM;
        float angleX = isAngry ? 20F : 0;
        float angleY = isAngry ? 60F : 45F;
        float angleZ = 0;

        if (pose.getEmotion() == WeepingAngelPose.Emotion.SCREAM) {
            angleY += 10F;
            angleX -= 10F;
        }

        head.xRot = (float) Math.toRadians(0);
        head.yRot = (float) Math.toRadians(0);
        head.zRot = (float) Math.toRadians(0);

        rightWing.xRot = (float) Math.toRadians(angleX);
        rightWing.yRot = (float) Math.toRadians(-angleY);
        rightWing.zRot = (float) Math.toRadians(angleZ);
        leftWing.xRot = (float) Math.toRadians(angleX);
        leftWing.yRot = (float) Math.toRadians(angleY);
        leftWing.zRot = (float) Math.toRadians(angleZ);


        if (pose == WeepingAngelPose.FURIOUS) {
            rightArm.xRot = (float) Math.toRadians(-115);
            rightArm.yRot = (float) Math.toRadians(0);
            rightArm.zRot = (float) Math.toRadians(0);

            leftArm.xRot = (float) Math.toRadians(-55);
            leftArm.yRot = (float) Math.toRadians(0);
            leftArm.zRot = (float) Math.toRadians(0);

            head.xRot = (float) Math.toRadians(17.5);
            head.yRot = (float) Math.toRadians(0);
            head.zRot = (float) Math.toRadians(-10);
            return;
        }


        if (pose == WeepingAngelPose.ANGRY) {
            rightArm.xRot = (float) Math.toRadians(-90);
            rightArm.yRot = (float) Math.toRadians(-20);
            rightArm.zRot = (float) Math.toRadians(30);

            leftArm.xRot = (float) Math.toRadians(-90);
            leftArm.yRot = (float) Math.toRadians(25);
            leftArm.zRot = (float) Math.toRadians(-17.5);

            head.xRot = (float) Math.toRadians(0);
            head.yRot = (float) Math.toRadians(-12.5);
            head.zRot = (float) Math.toRadians(0);
            return;
        }


        if (pose == WeepingAngelPose.HIDING) {
            head.xRot = (float) Math.toRadians(20);
            head.yRot = (float) Math.toRadians(0);
            head.zRot = (float) Math.toRadians(0);

            rightArm.xRot = (float) Math.toRadians(-105);
            rightArm.yRot = (float) Math.toRadians(-20);
            rightArm.zRot = (float) Math.toRadians(12.5);

            leftArm.xRot = (float) Math.toRadians(-105);
            leftArm.yRot = (float) Math.toRadians(20);
            leftArm.zRot = (float) Math.toRadians(-12.5);
            return;
        }

        if (pose == WeepingAngelPose.APPROACH) {
            rightArm.xRot = -1.04533F;
            rightArm.yRot = 0.55851F;
            rightArm.zRot = 0.0F;
            leftArm.xRot = -1.04533F;
            leftArm.yRot = -0.55851F;
            leftArm.zRot = 0.0F;
            return;
        }

        if (pose == WeepingAngelPose.IDLE) {
            head.xRot = (float) Math.toRadians(0);
            head.yRot = (float) Math.toRadians(0);
            head.zRot = (float) Math.toRadians(0);

            rightArm.xRot = (float) Math.toRadians(0);
            rightArm.yRot = (float) Math.toRadians(0);
            rightArm.zRot = (float) Math.toRadians(7.5);

            leftArm.xRot = (float) Math.toRadians(0);
            leftArm.yRot = (float) Math.toRadians(0);
            leftArm.zRot = (float) Math.toRadians(-7.5);
            return;
        }

        if (pose == WeepingAngelPose.SHY) {
            rightArm.xRot = (float) Math.toRadians(-90);
            rightArm.yRot = (float) Math.toRadians(-1.5);
            rightArm.zRot = (float) Math.toRadians(-20);

            leftArm.xRot = (float) Math.toRadians(-120);
            leftArm.yRot = (float) Math.toRadians(-36);
            leftArm.zRot = (float) Math.toRadians(10);

            head.xRot = (float) Math.toRadians(20);
            head.yRot = (float) Math.toRadians(-40);
            head.zRot = (float) Math.toRadians(-20);
        }
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, buffer, packedLight, packedOverlay);
        head.render(poseStack, buffer, packedLight, packedOverlay);
        leftArm.render(poseStack, buffer, packedLight, packedOverlay);
        rightArm.render(poseStack, buffer, packedLight, packedOverlay);
        Legs.render(poseStack, buffer, packedLight, packedOverlay);
        leftWing.render(poseStack, buffer, packedLight, packedOverlay);
        rightWing.render(poseStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public boolean toggleHurt(boolean hurtShow) {
        return false;
    }

    @Override
    public ResourceLocation generateTex(WeepingAngelPose pose, AngelVariant angelVariant) {
        String variant = angelVariant.getRegistryName().getPath() + "_angel_";
        String coreFolder = "textures/entities/spare_time/";
        coreFolder = coreFolder + angelVariant.getRegistryName().getPath() + "/";
        WeepingAngelPose.Emotion emotion = pose.getEmotion();
        String suffix = angelVariant.isHeadless() ? "headless" : emotion.name().toLowerCase();
        return new ResourceLocation(angelVariant.getRegistryName().getNamespace(), coreFolder + variant + suffix + ".png");
    }

    @Override
    public ResourceLocation getTextureForPose(Object angel, WeepingAngelPose pose) {

        if (angel instanceof WeepingAngel weepingAngel) {
            return generateTex(pose, weepingAngel.getVariant());
        }

        if (angel instanceof StatueBlockEntity statueBlockEntity) {
            return generateTex(statueBlockEntity.getPose(), statueBlockEntity.getAngelVarients());
        }

        if (angel instanceof PlinthBlockEntity plinthBlockEntity) {
            return generateTex(plinthBlockEntity.getPose(), plinthBlockEntity.getVariant());
        }
        return generateTex(WeepingAngelPose.ANGRY, AngelVariants.NORMAL.get());
    }

    @Override
    public WeepingAngelPose getAngelPose() {
        return weepingPose;
    }

    @Override
    public void setAngelPose(WeepingAngelPose angelPose) {
        this.weepingPose = angelPose;
    }

    @Override
    public Pair<ModelPart, Vector3d> getHeadData(HeadPlacement placement) {
        if (headData == null) {
            headData = new Pair<>(head, new Vector3d(0, 0, 0));
        }
        return headData;
    }

    @Override
    public Iterable<ModelPart> wings(PoseStack pose) {
        return ImmutableList.of(leftWing, rightWing);
    }
}