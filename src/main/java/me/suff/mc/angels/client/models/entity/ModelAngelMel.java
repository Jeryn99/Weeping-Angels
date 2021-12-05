package me.suff.mc.angels.client.models.entity;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.client.models.entity.arms.ModelArmsAngry;
import me.suff.mc.angels.client.models.entity.arms.ModelArmsCovering;
import me.suff.mc.angels.client.models.entity.arms.ModelArmsIdle;
import me.suff.mc.angels.client.models.entity.arms.ModelArmsPointing;
import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import me.suff.mc.angels.common.variants.AbstractVariant;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

public class ModelAngelMel extends EntityModel<WeepingAngelEntity> implements IAngelModel {

    public static ResourceLocation TEXTURE = new ResourceLocation(WeepingAngels.MODID,
            "textures/entities/angel_4.png");

    ModelRenderer Eye1;
    ModelRenderer Eye2;
    ModelRenderer EyeBacking;
    ModelRenderer EyeMiddle;
    ModelRenderer Hair1;
    ModelRenderer Hair2;
    ModelRenderer Hair3;
    ModelRenderer Hair4;
    ModelRenderer Hair5;
    ModelRenderer Hair6;
    ModelRenderer Hair7;
    ModelRenderer Hair8;
    ModelRenderer EarLeft;
    ModelRenderer EarRight;
    ModelRenderer Nose;
    ModelRenderer HeadBack;
    ModelRenderer MouthAngle1;
    ModelRenderer MouthAngle2;
    ModelRenderer Chin;
    ModelRenderer Noselower;
    ModelRenderer MouthInner;
    ModelRenderer MouthUpper;
    ModelRenderer EyeAngle1;
    ModelRenderer EyeAngle2;
    ModelRenderer MouthUpper2;
    ModelRenderer Forehead2;
    ModelRenderer Forehead5;
    ModelRenderer MouthUpper3;
    ModelRenderer Tooth3;
    ModelRenderer Tooth1;
    ModelRenderer Tooth2;
    ModelRenderer HeadBand2;
    ModelRenderer Head2;
    ModelRenderer Headband1;
    ModelRenderer HeadBand3;
    ModelRenderer Head3;
    ModelRenderer HeadCap1;
    ModelRenderer HeadCap2;
    ModelRenderer Neck;
    ModelRenderer TorsoMain;
    ModelRenderer Dress1;
    ModelRenderer Dress2;
    ModelRenderer Dress3;
    ModelRenderer Dress4;
    ModelRenderer Dress5;
    ModelRenderer Dress6;
    ModelRenderer Dress7;
    ModelRenderer Dress8;
    ModelRenderer Dress9;
    ModelRenderer Dress10;
    ModelRenderer Dress11;
    ModelRenderer Dress12;
    ModelRenderer Dress13;
    ModelRenderer Dress14;
    ModelRenderer LeftWing1;
    ModelRenderer LeftWing2;
    ModelRenderer LeftWing3;
    ModelRenderer LeftWing4;
    ModelRenderer LeftWing5;
    ModelRenderer LeftWing6;
    ModelRenderer LeftWing7;
    ModelRenderer RightWing1;
    ModelRenderer RightWing2;
    ModelRenderer RightWing3;
    ModelRenderer RightWing4;
    ModelRenderer RightWing5;
    ModelRenderer RightWing6;
    ModelRenderer RightWing7;

    ModelArmsCovering armsCovering = new ModelArmsCovering();
    private ModelArmsIdle armsIdle = new ModelArmsIdle();
    private ModelArmsAngry armsAngry = new ModelArmsAngry();
    private ModelArmsPointing armsPoint = new ModelArmsPointing();
    private WeepingAngelEntity angel;

    private WeepingAngelPose weepingAngelPose = WeepingAngelPose.ANGRY;


    public ModelAngelMel() {
        texWidth = 128;
        texHeight = 128;
        Eye1 = new ModelRenderer(this, 10, 17);
        Eye1.addBox(-1.5F, -4.25F, -3.1F, 1, 1, 1);
        Eye1.setPos(0F, -0.5F, 0F);
        Eye1.setTexSize(128, 128);
        Eye1.mirror = true;
        setRotation(Eye1, 0F, 0.0872665F, 0F);
        Eye2 = new ModelRenderer(this, 10, 19);
        Eye2.addBox(0.5F, -4.25F, -3.1F, 1, 1, 1);
        Eye2.setPos(0F, -0.5F, 0F);
        Eye2.setTexSize(128, 128);
        Eye2.mirror = true;
        setRotation(Eye2, 0F, -0.0872665F, 0F);
        EyeBacking = new ModelRenderer(this, 0, 6);
        EyeBacking.addBox(-2F, -4.5F, -2.9F, 4, 2, 1);
        EyeBacking.setPos(0F, -0.5F, 0F);
        EyeBacking.setTexSize(128, 128);
        EyeBacking.mirror = true;
        setRotation(EyeBacking, 0F, 0F, 0F);
        EyeMiddle = new ModelRenderer(this, 0, 21);
        EyeMiddle.addBox(-0.5F, -4.5F, -3F, 1, 2, 1);
        EyeMiddle.setPos(0F, -0.5F, 0F);
        EyeMiddle.setTexSize(128, 128);
        EyeMiddle.mirror = true;
        setRotation(EyeMiddle, 0F, 0F, 0F);
        Hair1 = new ModelRenderer(this, 30, 9);
        Hair1.addBox(-2F, -6F, -3.25F, 4, 1, 1);
        Hair1.setPos(0F, -0.5F, 0F);
        Hair1.setTexSize(128, 128);
        Hair1.mirror = true;
        setRotation(Hair1, 0F, 0F, 0F);
        Hair2 = new ModelRenderer(this, 34, 0);
        Hair2.addBox(2.25F, -4F, -2F, 1, 1, 1);
        Hair2.setPos(0F, -0.5F, 0F);
        Hair2.setTexSize(128, 128);
        Hair2.mirror = true;
        setRotation(Hair2, 0F, 0F, 0F);
        Hair3 = new ModelRenderer(this, 32, 2);
        Hair3.addBox(2.25F, -5F, -1F, 1, 1, 2);
        Hair3.setPos(0F, -0.5F, 0F);
        Hair3.setTexSize(128, 128);
        Hair3.mirror = true;
        setRotation(Hair3, 0F, 0F, 0F);
        Hair4 = new ModelRenderer(this, 32, 5);
        Hair4.addBox(2.25F, -4F, 1F, 1, 3, 1);
        Hair4.setPos(0F, -0.5F, 0F);
        Hair4.setTexSize(128, 128);
        Hair4.mirror = true;
        setRotation(Hair4, 0F, 0F, 0F);
        Hair5 = new ModelRenderer(this, 34, 0);
        Hair5.addBox(-3.25F, -4F, -2F, 1, 1, 1);
        Hair5.setPos(0F, -0.5F, 0F);
        Hair5.setTexSize(128, 128);
        Hair5.mirror = true;
        setRotation(Hair5, 0F, 0F, 0F);
        Hair6 = new ModelRenderer(this, 32, 2);
        Hair6.addBox(-3.25F, -5F, -1F, 1, 1, 2);
        Hair6.setPos(0F, -0.5F, 0F);
        Hair6.setTexSize(128, 128);
        Hair6.mirror = true;
        setRotation(Hair6, 0F, 0F, 0F);
        Hair7 = new ModelRenderer(this, 32, 5);
        Hair7.addBox(-3.25F, -4F, 1F, 1, 3, 1);
        Hair7.setPos(0F, -0.5F, 0F);
        Hair7.setTexSize(128, 128);
        Hair7.mirror = true;
        setRotation(Hair7, 0F, 0F, 0F);
        Hair8 = new ModelRenderer(this, 30, 11);
        Hair8.addBox(-2F, -2F, 1.75F, 4, 1, 1);
        Hair8.setPos(0F, -0.5F, 0F);
        Hair8.setTexSize(128, 128);
        Hair8.mirror = true;
        setRotation(Hair8, 0F, 0F, 0F);
        EarLeft = new ModelRenderer(this, 60, 0);
        EarLeft.addBox(0F, -4F, 0F, 1, 2, 2);
        EarLeft.setPos(-3F, -0.5F, -1F);
        EarLeft.setTexSize(128, 128);
        EarLeft.mirror = true;
        setRotation(EarLeft, 0F, -0.0872665F, 0F);
        EarRight = new ModelRenderer(this, 60, 0);
        EarRight.addBox(-1F, -4F, 0F, 1, 2, 2);
        EarRight.setPos(3F, -0.5F, -1F);
        EarRight.setTexSize(128, 128);
        EarRight.mirror = true;
        setRotation(EarRight, 0F, 0.0872665F, 0F);
        Nose = new ModelRenderer(this, 10, 2);
        Nose.addBox(-0.5F, 0F, -1F, 1, 1, 1);
        Nose.setPos(0F, -4F, -3F);
        Nose.setTexSize(128, 128);
        Nose.mirror = true;
        setRotation(Nose, 1.396263F, 0F, 0F);
        HeadBack = new ModelRenderer(this, 0, 0);
        HeadBack.addBox(-2F, -7F, 2F, 4, 5, 1);
        HeadBack.setPos(0F, -0.5F, 0F);
        HeadBack.setTexSize(128, 128);
        HeadBack.mirror = true;
        setRotation(HeadBack, 0F, 0F, 0F);
        MouthAngle1 = new ModelRenderer(this, 14, 2);
        MouthAngle1.addBox(-1F, 0F, -1F, 1, 1, 1);
        MouthAngle1.setPos(-0.5F, -1.5F, -2F);
        MouthAngle1.setTexSize(128, 128);
        MouthAngle1.mirror = true;
        setRotation(MouthAngle1, 0F, 0F, 1.047198F);
        MouthAngle2 = new ModelRenderer(this, 14, 2);
        MouthAngle2.addBox(0F, 0F, -1F, 1, 1, 1);
        MouthAngle2.setPos(0.5F, -1.5F, -2F);
        MouthAngle2.setTexSize(128, 128);
        MouthAngle2.mirror = true;
        setRotation(MouthAngle2, 0F, 0F, -1.047198F);
        Chin = new ModelRenderer(this, 10, 11);
        Chin.addBox(-1.5F, -1F, -3F, 3, 1, 1);
        Chin.setPos(0F, -0.5F, 0F);
        Chin.setTexSize(128, 128);
        Chin.mirror = true;
        setRotation(Chin, 0F, 0F, 0F);
        Noselower = new ModelRenderer(this, 0, 10);
        Noselower.addBox(-2F, -3F, -3F, 4, 1, 1);
        Noselower.setPos(0F, -0.5F, 0F);
        Noselower.setTexSize(128, 128);
        Noselower.mirror = true;
        setRotation(Noselower, 0F, 0F, 0F);
        MouthInner = new ModelRenderer(this, 10, 0);
        MouthInner.addBox(-1.75F, -2F, -2.7F, 3, 1, 1);
        MouthInner.setPos(0F, -0.5F, 0F);
        MouthInner.setTexSize(128, 128);
        MouthInner.mirror = true;
        setRotation(MouthInner, 0F, 0F, 0F);
        MouthUpper = new ModelRenderer(this, 10, 9);
        MouthUpper.addBox(-1.5F, -2.5F, -3F, 3, 1, 1);
        MouthUpper.setPos(0F, -0.5F, 0F);
        MouthUpper.setTexSize(128, 128);
        MouthUpper.mirror = true;
        setRotation(MouthUpper, 0F, 0F, 0F);
        EyeAngle1 = new ModelRenderer(this, 4, 21);
        EyeAngle1.addBox(0F, -1F, 0F, 2, 1, 1);
        EyeAngle1.setPos(-2F, -5F, -3F);
        EyeAngle1.setTexSize(128, 128);
        EyeAngle1.mirror = true;
        setRotation(EyeAngle1, 0F, 0F, 0.0872665F);
        EyeAngle2 = new ModelRenderer(this, 4, 21);
        EyeAngle2.addBox(-2F, -1F, 0F, 2, 1, 1);
        EyeAngle2.setPos(2F, -5F, -3F);
        EyeAngle2.setTexSize(128, 128);
        EyeAngle2.mirror = true;
        setRotation(EyeAngle2, 0F, 0F, -0.0872665F);
        MouthUpper2 = new ModelRenderer(this, 10, 6);
        MouthUpper2.addBox(1F, -2F, -3F, 1, 2, 1);
        MouthUpper2.setPos(0F, -0.5F, 0F);
        MouthUpper2.setTexSize(128, 128);
        MouthUpper2.mirror = true;
        setRotation(MouthUpper2, 0F, 0F, 0F);
        Forehead2 = new ModelRenderer(this, 0, 19);
        Forehead2.addBox(-2F, -5.5F, -3F, 4, 1, 1);
        Forehead2.setPos(0F, -0.5F, 0F);
        Forehead2.setTexSize(128, 128);
        Forehead2.mirror = true;
        setRotation(Forehead2, 0F, 0F, 0F);
        Forehead5 = new ModelRenderer(this, 60, 14);
        Forehead5.addBox(-2.5F, -7.5F, -2F, 5, 1, 4);
        Forehead5.setPos(0F, -0.5F, 0F);
        Forehead5.setTexSize(128, 128);
        Forehead5.mirror = true;
        setRotation(Forehead5, 0F, 0F, 0F);
        MouthUpper3 = new ModelRenderer(this, 10, 6);
        MouthUpper3.addBox(-2F, -2F, -3F, 1, 2, 1);
        MouthUpper3.setPos(0F, -0.5F, 0F);
        MouthUpper3.setTexSize(128, 128);
        MouthUpper3.mirror = true;
        setRotation(MouthUpper3, 0F, 0F, 0F);
        Tooth3 = new ModelRenderer(this, 14, 4);
        Tooth3.addBox(-1F, -1F, -0.8F, 1, 1, 1);
        Tooth3.setPos(0.5F, -1.8F, -2F);
        Tooth3.setTexSize(128, 128);
        Tooth3.mirror = true;
        setRotation(Tooth3, 0F, 0F, 0.7853982F);
        Tooth1 = new ModelRenderer(this, 14, 4);
        Tooth1.addBox(-1F, -1F, -0.8F, 1, 1, 1);
        Tooth1.setPos(-0.5F, -1.8F, -2F);
        Tooth1.setTexSize(128, 128);
        Tooth1.mirror = true;
        setRotation(Tooth1, 0F, 0F, 0.7853982F);
        Tooth2 = new ModelRenderer(this, 14, 4);
        Tooth2.addBox(-1F, -1F, -0.8F, 1, 1, 1);
        Tooth2.setPos(0F, -1.8F, -2F);
        Tooth2.setTexSize(128, 128);
        Tooth2.mirror = true;
        setRotation(Tooth2, 0F, 0F, 0.7853982F);
        HeadBand2 = new ModelRenderer(this, 90, 0);
        HeadBand2.addBox(-3F, -5F, -5F, 6, 1, 6);
        HeadBand2.setPos(0F, -0.5F, 0F);
        HeadBand2.setTexSize(128, 128);
        HeadBand2.mirror = true;
        setRotation(HeadBand2, -0.4363323F, 0F, 0F);
        Head2 = new ModelRenderer(this, 40, 11);
        Head2.addBox(-2.5F, -7F, -2.5F, 5, 7, 5);
        Head2.setPos(0F, -0.5F, 0F);
        Head2.setTexSize(128, 128);
        Head2.mirror = true;
        setRotation(Head2, 0F, 0F, 0F);
        Headband1 = new ModelRenderer(this, 66, 0);
        Headband1.addBox(-2.5F, -5F, -5.5F, 5, 1, 7);
        Headband1.setPos(0F, -0.5F, 0F);
        Headband1.setTexSize(128, 128);
        Headband1.mirror = true;
        setRotation(Headband1, -0.4363323F, 0F, 0F);
        HeadBand3 = new ModelRenderer(this, 90, 7);
        HeadBand3.addBox(-3.5F, -5F, -4.5F, 7, 1, 5);
        HeadBand3.setPos(0F, -0.5F, 0F);
        HeadBand3.setTexSize(128, 128);
        HeadBand3.mirror = true;
        setRotation(HeadBand3, -0.4363323F, 0F, 0F);
        Head3 = new ModelRenderer(this, 40, 0);
        Head3.addBox(-3F, -7F, -2F, 6, 7, 4);
        Head3.setPos(0F, -0.5F, 0F);
        Head3.setTexSize(128, 128);
        Head3.mirror = true;
        setRotation(Head3, 0F, 0F, 0F);
        HeadCap1 = new ModelRenderer(this, 0, 17);
        HeadCap1.addBox(-2F, -7F, -3F, 4, 1, 1);
        HeadCap1.setPos(0F, -0.5F, 0F);
        HeadCap1.setTexSize(128, 128);
        HeadCap1.mirror = true;
        setRotation(HeadCap1, 0F, 0F, 0F);
        HeadCap2 = new ModelRenderer(this, 60, 8);
        HeadCap2.addBox(-2F, -7.5F, -2.5F, 4, 1, 5);
        HeadCap2.setPos(0F, -0.5F, 0F);
        HeadCap2.setTexSize(128, 128);
        HeadCap2.mirror = true;
        setRotation(HeadCap2, 0F, 0F, 0F);
        Neck = new ModelRenderer(this, 0, 13);
        Neck.addBox(-2F, -1F, -1.5F, 4, 1, 3);
        Neck.setPos(0F, 0F, 0F);
        Neck.setTexSize(128, 128);
        Neck.mirror = true;
        setRotation(Neck, 0F, 0F, 0F);
        TorsoMain = new ModelRenderer(this, 39, 25);
        TorsoMain.addBox(-3.5F, 0F, -2F, 7, 10, 4);
        TorsoMain.setPos(0F, 0F, 0F);
        TorsoMain.setTexSize(128, 128);
        TorsoMain.mirror = true;
        setRotation(TorsoMain, 0F, 0F, 0F);
        Dress1 = new ModelRenderer(this, 42, 40);
        Dress1.addBox(-3.5F, 0F, 0F, 7, 8, 1);
        Dress1.setPos(0F, 2F, -2F);
        Dress1.setTexSize(128, 128);
        Dress1.mirror = true;
        setRotation(Dress1, -0.0872665F, 0F, 0F);
        Dress2 = new ModelRenderer(this, 42, 40);
        Dress2.addBox(-3.5F, 0F, -1F, 7, 8, 1);
        Dress2.setPos(0F, 2F, 2F);
        Dress2.setTexSize(128, 128);
        Dress2.mirror = true;
        setRotation(Dress2, 0.0872665F, 0F, 0F);
        Dress3 = new ModelRenderer(this, 43, 50);
        Dress3.addBox(-3F, 0F, 0F, 6, 14, 1);
        Dress3.setPos(0F, 10F, -2.5F);
        Dress3.setTexSize(128, 128);
        Dress3.mirror = true;
        setRotation(Dress3, -0.0523599F, 0F, 0F);
        Dress4 = new ModelRenderer(this, 43, 66);
        Dress4.addBox(-3F, 9F, 1.5F, 6, 5, 1);
        Dress4.setPos(0F, 10F, -2.5F);
        Dress4.setTexSize(128, 128);
        Dress4.mirror = true;
        setRotation(Dress4, -0.2094395F, 0F, 0F);
        Dress5 = new ModelRenderer(this, 43, 73);
        Dress5.addBox(-3F, 10F, 4F, 6, 4, 1);
        Dress5.setPos(0F, 10F, -2.5F);
        Dress5.setTexSize(128, 128);
        Dress5.mirror = true;
        setRotation(Dress5, -0.4363323F, 0F, 0F);
        Dress6 = new ModelRenderer(this, 43, 79);
        Dress6.addBox(-2F, 0F, -2.5F, 2, 14, 5);
        Dress6.setPos(3.5F, 10F, 0F);
        Dress6.setTexSize(128, 128);
        Dress6.mirror = true;
        setRotation(Dress6, 0F, 0F, -0.0523599F);
        Dress7 = new ModelRenderer(this, 43, 99);
        Dress7.addBox(-3.5F, 9F, -2.5F, 2, 5, 5);
        Dress7.setPos(3.5F, 10F, 0F);
        Dress7.setTexSize(128, 128);
        Dress7.mirror = true;
        setRotation(Dress7, 0F, 0F, -0.2094395F);
        Dress8 = new ModelRenderer(this, 43, 110);
        Dress8.addBox(-6F, 10F, -2.5F, 2, 4, 5);
        Dress8.setPos(3.5F, 10F, 0F);
        Dress8.setTexSize(128, 128);
        Dress8.mirror = true;
        setRotation(Dress8, 0F, 0F, -0.4363323F);
        Dress9 = new ModelRenderer(this, 43, 50);
        Dress9.addBox(-3F, 0F, -1F, 6, 14, 1);
        Dress9.setPos(0F, 10F, 2.5F);
        Dress9.setTexSize(128, 128);
        Dress9.mirror = true;
        setRotation(Dress9, 0.0523599F, 0F, 0F);
        Dress10 = new ModelRenderer(this, 43, 66);
        Dress10.addBox(-3F, 9F, -2.5F, 6, 5, 1);
        Dress10.setPos(0F, 10F, 2.5F);
        Dress10.setTexSize(128, 128);
        Dress10.mirror = true;
        setRotation(Dress10, 0.2094395F, 0F, 0F);
        Dress11 = new ModelRenderer(this, 43, 73);
        Dress11.addBox(-3F, 10F, -5F, 6, 4, 1);
        Dress11.setPos(0F, 10F, 2.5F);
        Dress11.setTexSize(128, 128);
        Dress11.mirror = true;
        setRotation(Dress11, 0.4363323F, 0F, 0F);
        Dress12 = new ModelRenderer(this, 43, 79);
        Dress12.addBox(0F, 0F, -2.5F, 2, 14, 5);
        Dress12.setPos(-3.5F, 10F, 0F);
        Dress12.setTexSize(128, 128);
        Dress12.mirror = true;
        setRotation(Dress12, 0F, 0F, 0.0523599F);
        Dress13 = new ModelRenderer(this, 43, 99);
        Dress13.addBox(1.5F, 9F, -2.5F, 2, 5, 5);
        Dress13.setPos(-3.5F, 10F, 0F);
        Dress13.setTexSize(128, 128);
        Dress13.mirror = true;
        setRotation(Dress13, 0F, 0F, 0.2094395F);
        Dress14 = new ModelRenderer(this, 43, 110);
        Dress14.addBox(4F, 10F, -2.5F, 2, 4, 5);
        Dress14.setPos(-3.5F, 10F, 0F);
        Dress14.setTexSize(128, 128);
        Dress14.mirror = true;
        setRotation(Dress14, 0F, 0F, 0.4363323F);
        LeftWing1 = new ModelRenderer(this, 8, 40);
        LeftWing1.addBox(6F, 0F, -1F, 1, 16, 1);
        LeftWing1.setPos(2F, 0F, 4F);
        LeftWing1.setTexSize(128, 128);
        LeftWing1.mirror = true;
        setRotation(LeftWing1, 0F, -0.2617994F, 0F);
        LeftWing2 = new ModelRenderer(this, 12, 40);
        LeftWing2.addBox(5F, -1F, -1F, 1, 16, 1);
        LeftWing2.setPos(2F, 0F, 4F);
        LeftWing2.setTexSize(128, 128);
        LeftWing2.mirror = true;
        setRotation(LeftWing2, 0F, -0.2617994F, 0F);
        LeftWing3 = new ModelRenderer(this, 16, 40);
        LeftWing3.addBox(4F, -2F, -1F, 1, 16, 1);
        LeftWing3.setPos(2F, 0F, 4F);
        LeftWing3.setTexSize(128, 128);
        LeftWing3.mirror = true;
        setRotation(LeftWing3, 0F, -0.2617994F, 0F);
        LeftWing4 = new ModelRenderer(this, 20, 40);
        LeftWing4.addBox(3F, -2F, -1F, 1, 14, 1);
        LeftWing4.setPos(2F, 0F, 4F);
        LeftWing4.setTexSize(128, 128);
        LeftWing4.mirror = true;
        setRotation(LeftWing4, 0F, -0.2617994F, 0F);
        LeftWing5 = new ModelRenderer(this, 24, 40);
        LeftWing5.addBox(2F, -1F, -1F, 1, 11, 1);
        LeftWing5.setPos(2F, 0F, 4F);
        LeftWing5.setTexSize(128, 128);
        LeftWing5.mirror = true;
        setRotation(LeftWing5, 0F, -0.2617994F, 0F);
        LeftWing6 = new ModelRenderer(this, 28, 40);
        LeftWing6.addBox(0F, 1F, -1F, 2, 5, 1);
        LeftWing6.setPos(2F, 0F, 4F);
        LeftWing6.setTexSize(128, 128);
        LeftWing6.mirror = true;
        setRotation(LeftWing6, 0F, -0.2617994F, 0F);
        LeftWing7 = new ModelRenderer(this, 34, 40);
        LeftWing7.addBox(0F, 1F, -3F, 1, 5, 3);
        LeftWing7.setPos(2F, 0F, 4F);
        LeftWing7.setTexSize(128, 128);
        LeftWing7.mirror = true;
        setRotation(LeftWing7, 0F, 0.6108652F, 0F);
        RightWing1 = new ModelRenderer(this, 8, 40);
        RightWing1.addBox(-7F, 0F, -1F, 1, 16, 1);
        RightWing1.setPos(-2F, 0F, 4F);
        RightWing1.setTexSize(128, 128);
        RightWing1.mirror = true;
        setRotation(RightWing1, 0F, 0.2617994F, 0F);
        RightWing2 = new ModelRenderer(this, 12, 40);
        RightWing2.addBox(-6F, -1F, -1F, 1, 16, 1);
        RightWing2.setPos(-2F, 0F, 4F);
        RightWing2.setTexSize(128, 128);
        RightWing2.mirror = true;
        setRotation(RightWing2, 0F, 0.2617994F, 0F);
        RightWing3 = new ModelRenderer(this, 16, 40);
        RightWing3.addBox(-5F, -2F, -1F, 1, 16, 1);
        RightWing3.setPos(-2F, 0F, 4F);
        RightWing3.setTexSize(128, 128);
        RightWing3.mirror = true;
        setRotation(RightWing3, 0F, 0.2617994F, 0F);
        RightWing4 = new ModelRenderer(this, 20, 40);
        RightWing4.addBox(-4F, -2F, -1F, 1, 14, 1);
        RightWing4.setPos(-2F, 0F, 4F);
        RightWing4.setTexSize(128, 128);
        RightWing4.mirror = true;
        setRotation(RightWing4, 0F, 0.2617994F, 0F);
        RightWing5 = new ModelRenderer(this, 24, 40);
        RightWing5.addBox(-3F, -1F, -1F, 1, 11, 1);
        RightWing5.setPos(-2F, 0F, 4F);
        RightWing5.setTexSize(128, 128);
        RightWing5.mirror = true;
        setRotation(RightWing5, 0F, 0.2617994F, 0F);
        RightWing6 = new ModelRenderer(this, 66, 40);
        RightWing6.addBox(-2F, 1F, -1F, 2, 5, 1);
        RightWing6.setPos(-2F, 0F, 4F);
        RightWing6.setTexSize(128, 128);
        RightWing6.mirror = true;
        setRotation(RightWing6, 0F, 0.2617994F, 0F);
        RightWing7 = new ModelRenderer(this, 58, 40);
        RightWing7.addBox(-1F, 1F, -3F, 1, 5, 3);
        RightWing7.setPos(-2F, 0F, 4F);
        RightWing7.setTexSize(128, 128);
        RightWing7.mirror = true;
        setRotation(RightWing7, 0F, -0.6108652F, 0F);
    }

    @Override
    public void setupAnim(WeepingAngelEntity weepingAngelEntity, float v, float v1, float v2, float v3, float v4) {
        angel = weepingAngelEntity;
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Eye1.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Eye2.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        EyeBacking.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        EyeMiddle.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Hair1.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Hair2.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Hair3.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Hair4.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Hair5.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Hair6.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Hair7.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Hair8.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        EarLeft.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        EarRight.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Nose.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        HeadBack.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        MouthAngle1.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        MouthAngle2.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Chin.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Noselower.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        MouthInner.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        MouthUpper.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        EyeAngle1.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        EyeAngle2.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        MouthUpper2.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Forehead2.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Forehead5.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        MouthUpper3.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Tooth3.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Tooth1.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Tooth2.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        HeadBand2.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Head2.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Headband1.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        HeadBand3.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Head3.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        HeadCap1.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        HeadCap2.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Neck.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        TorsoMain.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Dress1.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Dress2.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Dress3.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Dress4.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Dress5.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Dress6.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Dress7.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Dress8.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Dress9.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Dress10.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Dress11.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Dress12.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Dress13.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Dress14.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        LeftWing1.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        LeftWing2.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        LeftWing3.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        LeftWing4.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        LeftWing5.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        LeftWing6.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        LeftWing7.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        RightWing1.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        RightWing2.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        RightWing3.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        RightWing4.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        RightWing5.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        RightWing6.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        RightWing7.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);

        WeepingAngelPose pose = weepingAngelPose;
        if (angel != null) {
            pose = WeepingAngelPose.getPose(angel.getAngelPose());
        }

        // Covering Face arms render/
        if (pose == WeepingAngelPose.HIDING) {
            armsCovering.renderToBuffer(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
            return;
        }

        // Idle render
        if (pose == WeepingAngelPose.IDLE) {
            armsIdle.renderToBuffer(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
            return;
        }

        // Shriek render
        if (pose == WeepingAngelPose.ANGRY || pose == WeepingAngelPose.FURIOUS) {
            armsAngry.renderToBuffer(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
            return;
        }

        if (pose == WeepingAngelPose.SHY) {
            armsPoint.renderToBuffer(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
            return;
        }

        armsCovering.renderToBuffer(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
    }

    @Override
    public Iterable<ModelRenderer> wings(MatrixStack pose) {
        return ImmutableList.of(LeftWing1, LeftWing2, LeftWing3, LeftWing4, LeftWing5, LeftWing6, LeftWing7, RightWing1, RightWing2, RightWing3, RightWing4, RightWing5, RightWing6, RightWing7);
    }

    @Override
    public ModelRenderer getSantaAttachment(MatrixStack pose, boolean b) {
        return null;
    }

    @Override
    public ResourceLocation getTextureForPose(Object angel, WeepingAngelPose pose) {
        return TEXTURE;
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
    public ResourceLocation generateTex(WeepingAngelPose pose, AbstractVariant angelVariants) {
        return TEXTURE;
    }
}
