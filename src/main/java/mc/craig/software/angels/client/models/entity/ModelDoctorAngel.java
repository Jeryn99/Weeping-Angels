package mc.craig.software.angels.client.models.entity;


import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3d;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.client.poses.WeepingAngelPose;
import mc.craig.software.angels.common.blockentities.PlinthBlockEntity;
import mc.craig.software.angels.common.blockentities.StatueBlockEntity;
import mc.craig.software.angels.common.entities.WeepingAngel;
import mc.craig.software.angels.common.variants.AbstractVariant;
import mc.craig.software.angels.utils.Pair;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.HumanoidArm;

public class ModelDoctorAngel extends EntityModel<WeepingAngel> implements IAngelModel, HeadedModel, ArmedModel {

    public static final ResourceLocation IDLE = new ResourceLocation(WeepingAngels.MODID, "textures/entities/disaster/doctor/doctor_angel_idle.png");
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart leftArm;
    private final ModelPart rightArm;
    private final ModelPart Legs;
    private final ModelPart leftWing;
    private final ModelPart rightWing;
    private WeepingAngelPose weepingAngelPose = WeepingAngelPose.ANGRY;
    private boolean showHurt = false;
    private Pair<ModelPart, Vector3d> headData;

    public ModelDoctorAngel(ModelPart root) {
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.leftArm = root.getChild("leftArm");
        this.rightArm = root.getChild("rightArm");
        this.Legs = root.getChild("Legs");
        this.leftWing = root.getChild("leftWing");
        this.rightWing = root.getChild("rightWing");
    }

    public static LayerDefinition getModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 17).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(72, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(56, 17).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(32, 17).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 8.0F, 4.0F, new CubeDeformation(0.5F))
                .texOffs(41, 0).addBox(-5.0F, 8.5F, -2.5F, 10.0F, 11.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition leftArm = partdefinition.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(24, 59).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(28, 35).mirror().addBox(-2.0F, -2.0F, -2.0F, 3.0F, 10.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offset(-5.0F, 2.0F, 0.0F));

        PartDefinition rightArm = partdefinition.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(10, 59).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(28, 35).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 10.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(5.0F, 2.0F, 0.0F));

        PartDefinition Legs = partdefinition.addOrReplaceChild("Legs", CubeListBuilder.create(), PartPose.offset(0.0F, 9.25F, 0.0F));

        PartDefinition LLeg = Legs.addOrReplaceChild("LLeg", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(48, 50).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offset(-2.0F, 2.75F, 0.0F));

        PartDefinition RLeg = Legs.addOrReplaceChild("RLeg", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(67, 50).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(2.0F, 2.75F, 0.0F));

        PartDefinition leftWing = partdefinition.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(0, 101).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(6, 83).addBox(-1.0F, -8.9F, 5.0F, 2.0F, 14.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(18, 83).addBox(-1.0F, -6.9F, 3.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(8, 33).addBox(-1.0F, -10.9F, 6.0F, 2.0F, 21.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 33).addBox(-1.0F, -10.0F, 9.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(38, 59).addBox(-1.0F, -8.0F, 11.0F, 2.0F, 17.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 5.0F, 2.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition rightWing = partdefinition.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(10, 101).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(12, 83).addBox(-1.0F, -8.9F, 5.0F, 2.0F, 14.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(26, 83).addBox(-1.0F, -6.9F, 3.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(18, 33).addBox(-1.0F, -10.0F, 9.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 83).addBox(-1.0F, -8.0F, 11.0F, 2.0F, 17.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 59).addBox(-1.0F, -10.9F, 6.0F, 2.0F, 21.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 5.0F, 2.0F, 0.0F, 0.7854F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    public WeepingAngelPose getWeepingAngelPose() {
        return weepingAngelPose;
    }

    public void setWeepingAngelPose(WeepingAngelPose weepingAngelPose) {
        this.weepingAngelPose = weepingAngelPose;
    }

    @Override
    public void setupAnim(WeepingAngel weepingAngel, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        WeepingAngelPose pose = weepingAngelPose;
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
        rightWing.yRot = (float) Math.toRadians(angleY);
        rightWing.zRot = (float) Math.toRadians(angleZ);
        leftWing.xRot = (float) Math.toRadians(angleX);
        leftWing.yRot = (float) Math.toRadians(-angleY);
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
            rightArm.yRot = (float) Math.toRadians(20);
            rightArm.zRot = (float) Math.toRadians(12.5);

            leftArm.xRot = (float) Math.toRadians(-105);
            leftArm.yRot = (float) Math.toRadians(-20);
            leftArm.zRot = (float) Math.toRadians(-12.5);
            return;
        }

        if (pose == WeepingAngelPose.APPROACH) {
            rightArm.xRot = -1.04533F;
            rightArm.yRot = -0.55851F;
            rightArm.zRot = 0.0F;
            leftArm.xRot = -1.04533F;
            leftArm.yRot = 0.55851F;
            leftArm.zRot = 0.0F;
            return;
        }

        if (pose == WeepingAngelPose.IDLE) {
            head.xRot = (float) Math.toRadians(0);
            head.yRot = (float) Math.toRadians(0);
            head.zRot = (float) Math.toRadians(0);

            rightArm.xRot = (float) Math.toRadians(0);
            rightArm.yRot = (float) Math.toRadians(0);
            rightArm.zRot = (float) Math.toRadians(-7.5);

            leftArm.xRot = (float) Math.toRadians(0);
            leftArm.yRot = (float) Math.toRadians(0);
            leftArm.zRot = (float) Math.toRadians(7.5);
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
            return;
        }
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(poseStack, buffer, packedLight, packedOverlay);
        body.render(poseStack, buffer, packedLight, packedOverlay);
        leftArm.render(poseStack, buffer, packedLight, packedOverlay);
        rightArm.render(poseStack, buffer, packedLight, packedOverlay);
        Legs.render(poseStack, buffer, packedLight, packedOverlay);
        leftWing.render(poseStack, buffer, packedLight, packedOverlay);
        rightWing.render(poseStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public boolean toggleHurt(boolean hurtShow) {
        this.showHurt = hurtShow;
        return showHurt;
    }

    @Override
    public ResourceLocation generateTex(WeepingAngelPose pose, AbstractVariant abstractVariant) {
        return IDLE;
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
        return IDLE;
    }

    @Override
    public WeepingAngelPose getAngelPose() {
        return weepingAngelPose;
    }

    @Override
    public void setAngelPose(WeepingAngelPose angelPose) {
        this.weepingAngelPose = angelPose;
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

    @Override
    public void translateToHand(HumanoidArm p_102108_, PoseStack p_102109_) {

    }

    @Override
    public ModelPart getHead() {
        return head;
    }


}