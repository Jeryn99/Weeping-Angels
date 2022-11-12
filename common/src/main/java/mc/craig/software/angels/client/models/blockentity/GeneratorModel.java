package mc.craig.software.angels.client.models.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mc.craig.software.angels.common.blockentity.GeneratorBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class GeneratorModel extends HierarchicalModel {
//TODO: Fix this
//    public static final AnimationDefinition CHARGE_TIMER = AnimationDefinition.Builder.withLength(5f).looping().addAnimation("Needle", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(5f, KeyframeAnimations.degreeVec(0f, 300f, 0f), AnimationChannel.Interpolations.CATMULLROM))).build();
    private final ModelPart Generator;
    private final ModelPart root;

    public GeneratorModel(ModelPart root) {
        this.root = root;
        this.Generator = root.getChild("Generator");
    }

    public static LayerDefinition meshLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Generator = partdefinition.addOrReplaceChild("Generator", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition Crystal = Generator.addOrReplaceChild("Crystal", CubeListBuilder.create().texOffs(12, 4).addBox(-1.0F, -2.75F, -2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(-0.05F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition Light = Generator.addOrReplaceChild("Light", CubeListBuilder.create().texOffs(26, 0).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -1.0F, 1.0F));

        PartDefinition Needle = Generator.addOrReplaceChild("Needle", CubeListBuilder.create().texOffs(0, 3).addBox(-0.5F, -0.25F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(12, 0).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 1.0F, 0.0F, -2.618F, 0.0F));

        PartDefinition Casing = Generator.addOrReplaceChild("Casing", CubeListBuilder.create().texOffs(0, 9).addBox(-2.0F, -2.0F, -3.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.1F))
                .texOffs(12, 0).addBox(-2.0F, -2.0F, -3.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.0F, -1.5F, -4.0F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(12, 2).addBox(-1.0F, -2.5F, -2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Generator.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return root;
    }

    public void animateTile(GeneratorBlockEntity generatorBlockEntity){
//TODO: Fix this
//        this.root().getAllParts().forEach(ModelPart::resetPose);
//        if(generatorBlockEntity.isActivated()) {
//            animate(generatorBlockEntity.ANIMATION, CHARGE_TIMER, Minecraft.getInstance().player.tickCount);
//        }
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }
}