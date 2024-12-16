package dev.jeryn.angels.client.models.entity.angel;


import com.google.common.collect.ImmutableList;
import dev.jeryn.angels.WeepingAngels;
import dev.jeryn.angels.client.screen.ChiselScreen;
import dev.jeryn.angels.common.blockentity.StatueBlockEntity;
import dev.jeryn.angels.common.entity.angel.WeepingAngel;
import dev.jeryn.angels.common.entity.angel.ai.AngelEmotion;
import dev.jeryn.angels.common.entity.angel.ai.AngelVariant;
import net.minecraft.client.Minecraft;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import static dev.jeryn.angels.client.models.entity.angel.AliceAngelModel.ANIMATION_STREAM;

public class DoctorAngelModel extends AngelModel {
    private final ModelPart Angel;
    private final ModelPart root;
    private final ModelPart leftWing;
    private final ModelPart rightWing;

    public DoctorAngelModel(ModelPart root) {
        this.root = root;
        this.Angel = root.getChild("Angel");
        this.leftWing = Angel.getChild("Body").getChild("leftWing");
        this.rightWing = Angel.getChild("Body").getChild("rightWing");
    }

    public static LayerDefinition meshLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Angel = partdefinition.addOrReplaceChild("Angel", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition Body = Angel.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(32, 17).addBox(-4.0F, -12.0F, -2.0F, 8.0F, 8.0F, 4.0F, new CubeDeformation(0.5F))
                .texOffs(56, 17).addBox(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(41, 0).addBox(-5.0F, -3.5F, -2.5F, 10.0F, 11.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 0.0F));

        PartDefinition leftArm = Body.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(24, 59).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(28, 35).mirror().addBox(-2.0F, -2.0F, -2.0F, 3.0F, 10.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offset(-5.0F, -10.0F, 0.0F));

        PartDefinition rightArm = Body.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(10, 59).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(28, 35).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 10.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(5.0F, -10.0F, 0.0F));

        PartDefinition head = Body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 17).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(72, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, -12.0F, 0.0F));

        PartDefinition leftWing = Body.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(0, 101).addBox(-1.0F, -2.5F, 0.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(6, 83).addBox(-1.0F, -7.4F, 5.0F, 2.0F, 14.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(18, 83).addBox(-1.0F, -5.4F, 3.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(8, 33).addBox(-1.0F, -9.4F, 6.0F, 2.0F, 21.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 33).addBox(-1.0F, -8.5F, 9.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(38, 59).addBox(-1.0F, -6.5F, 11.0F, 2.0F, 17.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -8.5F, 2.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition rightWing = Body.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(10, 101).addBox(-1.0F, -2.5F, 0.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(12, 83).addBox(-1.0F, -7.4F, 5.0F, 2.0F, 14.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(26, 83).addBox(-1.0F, -5.4F, 3.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(18, 33).addBox(-1.0F, -8.5F, 9.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 83).addBox(-1.0F, -6.5F, 11.0F, 2.0F, 17.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 59).addBox(-1.0F, -9.4F, 6.0F, 2.0F, 21.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -8.5F, 2.0F, 0.0F, 0.7854F, 0.0F));

        PartDefinition Legs = Angel.addOrReplaceChild("Legs", CubeListBuilder.create(), PartPose.offset(0.0F, -14.75F, 0.0F));

        PartDefinition LLeg = Legs.addOrReplaceChild("LLeg", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(48, 50).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offset(-2.0F, 2.75F, 0.0F));

        PartDefinition RLeg = Legs.addOrReplaceChild("RLeg", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(67, 50).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(2.0F, 2.75F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public ModelPart root() {
        return root;
    }
    @Override
    public ModelPart getHead() {
        return Angel.getChild("Body").getChild("head");
    }


    @Override
    public Iterable<ModelPart> getWings() {
        return ImmutableList.of(rightWing, leftWing);
    }

    @Override
    public ResourceLocation texture(AngelEmotion angelEmotion, AngelVariant angelVariant) {
        return new ResourceLocation(WeepingAngels.MODID, "textures/entity/angel/doctor/doctor_angel_idle.png");
    }

    @Override
    public void animateTile(StatueBlockEntity statueBlockEntity) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        animate(statueBlockEntity.getAnimationState(), poseForId(statueBlockEntity.getAnimation()), Minecraft.getInstance().player.tickCount);
    }

    @Override
    public AnimationDefinition poseForId(int index) {
        return getAnimationDefinition(index);
    }


    @Override
    public void setupAnim(WeepingAngel weepingAngel, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        if (weepingAngel.getFakeAnimation() != -1) {
            animate(ChiselScreen.POSE_ANIMATION_STATE, getAnimationDefinition(weepingAngel.getFakeAnimation()), Minecraft.getInstance().player.tickCount, 1000);
            return;
        }

        int playbackSpeed = Mth.clamp(weepingAngel.level.random.nextInt(7), 2, 7);
        if (isBlockPosBehindPlayer(Minecraft.getInstance().player, weepingAngel.blockPosition()) ||weepingAngel.isHooked() || weepingAngel.getSeenTime() > 0 || weepingAngel.tickCount < 200) {
            playbackSpeed = 0;
        }

        animate(weepingAngel.POSE_ANIMATION_STATE, ANIMATION_STREAM, weepingAngel.tickCount, playbackSpeed);
    }
}