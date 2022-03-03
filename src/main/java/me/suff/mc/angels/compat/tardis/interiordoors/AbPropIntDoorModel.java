package me.suff.mc.angels.compat.tardis.interiordoors;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.utils.EnumDoorTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.tardis.mod.cap.Capabilities;
import net.tardis.mod.client.models.interiordoors.AbstractInteriorDoorModel;
import net.tardis.mod.client.renderers.boti.BOTIRenderer;
import net.tardis.mod.client.renderers.boti.PortalInfo;
import net.tardis.mod.client.renderers.entity.DoorRenderer;
import net.tardis.mod.entity.DoorEntity;
import net.tardis.mod.enums.EnumDoorState;
import net.tardis.mod.helper.TardisHelper;
import net.tardis.mod.helper.WorldHelper;
import net.tardis.mod.tileentities.ConsoleTile;

public class AbPropIntDoorModel extends AbstractInteriorDoorModel {
    public static final ResourceLocation BLU = new ResourceLocation(WeepingAngels.MODID, "textures/exteriors/ab_door_blue.png");
    public static final ResourceLocation YELLOW = new ResourceLocation(WeepingAngels.MODID, "textures/exteriors/ab_door_yellow.png");
    public static final ResourceLocation WAR = new ResourceLocation(WeepingAngels.MODID, "textures/exteriors/ab_door_war.png");
    private final ModelRenderer IntDoors;
    private final ModelRenderer Posts;
    private final ModelRenderer cube_r1;
    private final ModelRenderer PPCB;
    private final ModelRenderer Doors;
    private final ModelRenderer RDoor;
    private final ModelRenderer LDoor;
    private final ModelRenderer Panels;
    private final ModelRenderer boti;


    public AbPropIntDoorModel() {
        texWidth = 128;
        texHeight = 128;

        IntDoors = new ModelRenderer(this);
        IntDoors.setPos(0.0F, 24.0F, 1.0F);


        Posts = new ModelRenderer(this);
        Posts.setPos(-14.0F, -3.0F, -15.0F);
        IntDoors.addChild(Posts);
        Posts.texOffs(56, 12).addBox(-3.0F, -54.0F, 6.0F, 3.0F, 57.0F, 5.0F, 0.0F, false);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setPos(14.0F, 6.0F, 23.0F);
        Posts.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0F, -1.5708F, 0.0F);
        cube_r1.texOffs(0, 63).addBox(-17.0F, -60.0F, -17.0F, 5.0F, 57.0F, 3.0F, 0.0F, false);

        PPCB = new ModelRenderer(this);
        PPCB.setPos(0.0F, 0.0F, 0.0F);
        IntDoors.addChild(PPCB);
        PPCB.texOffs(0, 0).addBox(-15.0F, -56.0F, -10.0F, 30.0F, 5.0F, 7.0F, 0.0F, false);
        PPCB.texOffs(28, 76).addBox(-15.0F, -56.0F, -6.0F, 30.0F, 5.0F, 0.0F, 0.0F, false);

        Doors = new ModelRenderer(this);
        Doors.setPos(0.0F, 0.0F, 1.0F);
        IntDoors.addChild(Doors);


        RDoor = new ModelRenderer(this);
        RDoor.setPos(13.0F, 0.0F, -7.0F);
        Doors.addChild(RDoor);
        RDoor.texOffs(28, 12).addBox(-13.0F, -50.0F, -1.0F, 13.0F, 50.0F, 1.0F, 0.0F, false);
        RDoor.texOffs(3, 3).addBox(-12.0F, -33.0F, -2.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        RDoor.texOffs(67, 2).addBox(-13.0F, -27.0F, 0.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);

        LDoor = new ModelRenderer(this);
        LDoor.setPos(-13.0F, 0.0F, -7.0F);
        Doors.addChild(LDoor);
        LDoor.texOffs(0, 12).addBox(0.0F, -50.0F, -1.0F, 13.0F, 50.0F, 1.0F, 0.0F, false);
        LDoor.texOffs(0, 3).addBox(11.5F, -2.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
        LDoor.texOffs(24, 63).addBox(3.0F, -34.0F, 0.0F, 6.0F, 7.0F, 2.0F, 0.0F, false);
        LDoor.texOffs(40, 63).addBox(3.0F, -36.0F, 1.0F, 7.0F, 8.0F, 0.0F, 0.0F, false);
        LDoor.texOffs(56, 12).addBox(10.0F, -32.0F, -2.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        LDoor.texOffs(0, 0).addBox(12.0F, -27.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        Panels = new ModelRenderer(this);
        Panels.setPos(0.0F, -12.0F, 0.0F);
        IntDoors.addChild(Panels);
        Panels.texOffs(20, 63).addBox(-14.0F, -39.0F, -7.0F, 1.0F, 51.0F, 1.0F, 0.0F, false);
        Panels.texOffs(16, 63).addBox(13.0F, -39.0F, -7.0F, 1.0F, 51.0F, 1.0F, 0.0F, false);
        Panels.texOffs(67, 0).addBox(-13.0F, -39.0F, -7.0F, 26.0F, 1.0F, 1.0F, 0.0F, false);

        boti = new ModelRenderer(this);
        boti.setPos(0.0F, 24.0F, 0.0F);
        boti.texOffs(70, 76).addBox(-13.5F, -50.1F, -7.9F, 27.0F, 50.0F, 2.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(Entity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {

    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    @Override
    public void render(DoorEntity door, MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay) {
        this.renderBones(door, matrixStack, buffer, packedLight, packedOverlay);
        this.renderBoti(door, matrixStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public void renderBones(DoorEntity door, MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay) {
        EnumDoorState state = door.getOpenState();
        matrixStack.pushPose();
        matrixStack.translate(0.0D, 0.6f - 0.08, -0.1f);
        matrixStack.scale(0.65F, 0.65F, 0.65F);
        switch (state) {
            case ONE:
                this.RDoor.yRot = (float) Math.toRadians(EnumDoorTypes.ABPROP.getRotationForState(EnumDoorState.ONE));
                this.LDoor.yRot = (float) Math.toRadians(EnumDoorTypes.ABPROP.getRotationForState(EnumDoorState.CLOSED));
                break;
            case BOTH:
                this.RDoor.yRot = (float) Math.toRadians(EnumDoorTypes.ABPROP.getRotationForState(EnumDoorState.ONE));
                this.LDoor.yRot = (float) Math.toRadians(EnumDoorTypes.ABPROP.getRotationForState(EnumDoorState.BOTH));
                break;
            case CLOSED:
                this.RDoor.yRot = (float) Math.toRadians(EnumDoorTypes.ABPROP.getRotationForState(EnumDoorState.CLOSED));
                this.LDoor.yRot = (float) Math.toRadians(EnumDoorTypes.ABPROP.getRotationForState(EnumDoorState.CLOSED));
        }

        IntDoors.render(matrixStack, buffer, packedLight, packedOverlay);
        matrixStack.popPose();
    }

    @Override
    public void renderBoti(DoorEntity door, MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay) {
        if (Minecraft.getInstance().level != null && door.getOpenState() != EnumDoorState.CLOSED) {
            Minecraft.getInstance().level.getCapability(Capabilities.TARDIS_DATA).ifPresent(data -> {
                matrixStack.pushPose();
                PortalInfo info = new PortalInfo();
                info.setPosition(door.position());
                info.setWorldShell(data.getBotiWorld());
                info.setTranslate(matrix -> {
//                    matrix.scale(1.1f, 1.1f, 1.2f);
                    matrix.translate(0.055, -0.5, 0);
                    DoorRenderer.applyTranslations(matrix, door.yRot - 180, door.getDirection());
                });
                info.setTranslatePortal(matrix -> {
                	
                    matrix.mulPose(Vector3f.ZN.rotationDegrees(180));
                    matrix.mulPose(Vector3f.YP.rotationDegrees(WorldHelper.getAngleFromFacing(data.getBotiWorld().getPortalDirection())));
                    matrix.translate(-0.5, -1, -0.5);
                });

                info.setRenderPortal((matrix, impl) -> {
                    matrix.pushPose();
                    float botiScale = 0.9f;
                    matrix.scale(botiScale - 0.3F, botiScale - 0.25F, botiScale + 0.1F);
//                    matrix.translate(0, 1.0, 0.5f);
                    this.boti.render(matrix, impl.getBuffer(RenderType.entityCutout(this.getTexture())), packedLight, packedOverlay);
                    matrix.popPose();
                });

                BOTIRenderer.addPortal(info);
                matrixStack.popPose();
            });
        }
    }

    @Override
    public void renderToBuffer(MatrixStack p_225598_1_, IVertexBuilder p_225598_2_, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {

    }

    @Override
    public ResourceLocation getTexture() {
    	ConsoleTile tile = TardisHelper.getConsoleInWorld(Minecraft.getInstance().level).orElse(null);
        if (tile != null) {
            int index = tile.getExteriorManager().getExteriorVariant();
            if (tile.getExteriorType().getVariants() != null && index < tile.getExteriorType().getVariants().length)
                return tile.getExteriorType().getVariants()[index].getInteriorDoorTexture();
        }
        return BLU;
    }
}