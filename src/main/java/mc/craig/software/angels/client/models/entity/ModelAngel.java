package mc.craig.software.angels.client.models.entity;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.client.poses.WeepingAngelPose;
import mc.craig.software.angels.common.entities.WeepingAngelEntity;
import mc.craig.software.angels.common.variants.AbstractVariant;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

/**
 * Angel Type: 0
 * Created by Ford Created using Techne
 */
public class ModelAngel extends EntityModel<WeepingAngelEntity> implements IAngelModel {

    private final ResourceLocation TEXTURE = new ResourceLocation(WeepingAngels.MODID,
            "textures/entities/angel.png");

    private final ModelRenderer AngryLeftArm2;
    private final ModelRenderer AngryRightArm2;
    private final ModelRenderer AngryLeftArm1;
    private final ModelRenderer AngryRightArm1;
    private final ModelRenderer LeftEyebrow;
    private final ModelRenderer RightEyebrow;
    private final ModelRenderer Headband;
    private final ModelRenderer Nose;
    private final ModelRenderer Chin;
    private final ModelRenderer ChinAngleLeft;
    private final ModelRenderer ChinAngleRight;
    private final ModelRenderer CheekLeft;
    private final ModelRenderer CheekRight;
    private final ModelRenderer HeadMain;
    private final ModelRenderer HeadUnder;
    private final ModelRenderer Teeth;
    private final ModelRenderer Hair1;
    private final ModelRenderer Hair2;
    private final ModelRenderer Hair3;
    private final ModelRenderer Hair4;
    private final ModelRenderer Hair5;
    private final ModelRenderer Hair6;
    private final ModelRenderer Hair7;
    private final ModelRenderer Hair8;
    private final ModelRenderer Hair9;
    private final ModelRenderer Hair10;
    private final ModelRenderer Hair11;
    private final ModelRenderer Hair12;
    private final ModelRenderer TorsoMain;
    private final ModelRenderer Dress1;
    private final ModelRenderer Dress2;
    private final ModelRenderer Dress3;
    private final ModelRenderer Dress4;
    private final ModelRenderer Dress5;
    private final ModelRenderer Dress6;
    private final ModelRenderer Dress7;
    private final ModelRenderer Dress8;
    private final ModelRenderer Dress9;
    private final ModelRenderer Dress10;
    private final ModelRenderer Dress11;
    private final ModelRenderer Dress12;
    private final ModelRenderer Dress13;
    private final ModelRenderer RightArm1;
    private final ModelRenderer RightArm2;
    private final ModelRenderer LeftArm1;
    private final ModelRenderer LeftArm2;
    private final ModelRenderer LeftWing1;
    private final ModelRenderer LeftWing2;
    private final ModelRenderer LeftWing3;
    private final ModelRenderer LeftWing4;
    private final ModelRenderer LeftWing5;
    private final ModelRenderer LeftWing6;
    private final ModelRenderer LeftWing7;
    private final ModelRenderer LeftWing8;
    private final ModelRenderer LeftWing9;
    private final ModelRenderer RightWing1;
    private final ModelRenderer RightWing2;
    private final ModelRenderer RightWing3;
    private final ModelRenderer RightWing4;
    private final ModelRenderer RightWing5;
    private final ModelRenderer RightWing6;
    private final ModelRenderer RightWing7;
    private final ModelRenderer RightWing8;
    private final ModelRenderer RightWing9;

    //TODO Until I make this better, we're going to ignore this model and pretend theres nothing wrong
    private WeepingAngelEntity angelEntity;

    private WeepingAngelPose weepingAngelPose = WeepingAngelPose.ANGRY;

    /**
     * Angel Type: 0
     */
    public ModelAngel() {
        texWidth = 128;
        texHeight = 128;

        LeftEyebrow = new ModelRenderer(this, 50, 8);
        LeftEyebrow.addBox(-2F, -1F, 0F, 2, 1, 1);
        LeftEyebrow.setPos(-1F, -4F, -4.2F);
        LeftEyebrow.setTexSize(128, 128);
        LeftEyebrow.mirror = true;
        setRotation(LeftEyebrow, -0.3490659F, 0F, 0F);
        RightEyebrow = new ModelRenderer(this, 50, 8);
        RightEyebrow.addBox(0F, -1F, 0F, 2, 1, 1);
        RightEyebrow.setPos(1F, -4F, -4.2F);
        RightEyebrow.setTexSize(128, 128);
        RightEyebrow.mirror = true;
        setRotation(RightEyebrow, -0.3490659F, 0F, 0F);
        Headband = new ModelRenderer(this, 76, 0);
        Headband.addBox(-4.5F, 0F, -0.5F, 9, 1, 9);
        Headband.setPos(0F, -7.5F, -4F);
        Headband.setTexSize(128, 128);
        Headband.mirror = true;
        setRotation(Headband, -0.1745329F, 0F, 0F);
        Nose = new ModelRenderer(this, 62, 8);
        Nose.addBox(-1F, -3F, -4.1F, 2, 1, 1);
        Nose.setPos(0F, 0F, 0F);
        Nose.setTexSize(128, 128);
        Nose.mirror = true;
        setRotation(Nose, 0F, 0F, 0F);
        Chin = new ModelRenderer(this, 62, 2);
        Chin.addBox(-2F, -0.7F, -4F, 4, 1, 2);
        Chin.setPos(0F, 0F, 0F);
        Chin.setTexSize(128, 128);
        Chin.mirror = true;
        setRotation(Chin, 0F, 0F, 0F);
        ChinAngleLeft = new ModelRenderer(this, 62, 5);
        ChinAngleLeft.addBox(-2F, -1F, 0F, 2, 1, 2);
        ChinAngleLeft.setPos(-2F, 0.3F, -4F);
        ChinAngleLeft.setTexSize(128, 128);
        ChinAngleLeft.mirror = true;
        setRotation(ChinAngleLeft, 0F, 0F, 0.2792527F);
        ChinAngleRight = new ModelRenderer(this, 62, 5);
        ChinAngleRight.addBox(0F, -1F, 0F, 2, 1, 2);
        ChinAngleRight.setPos(2F, 0.3F, -4F);
        ChinAngleRight.setTexSize(128, 128);
        ChinAngleRight.mirror = true;
        setRotation(ChinAngleRight, 0F, 0F, -0.2792527F);
        CheekLeft = new ModelRenderer(this, 62, 0);
        CheekLeft.addBox(-4F, -1F, -4F, 3, 1, 1);
        CheekLeft.setPos(0F, 0F, 0F);
        CheekLeft.setTexSize(128, 128);
        CheekLeft.mirror = true;
        setRotation(CheekLeft, 0F, 0F, 0F);
        CheekRight = new ModelRenderer(this, 62, 0);
        CheekRight.addBox(1F, -1F, -4F, 3, 1, 1);
        CheekRight.setPos(0F, 0F, 0F);
        CheekRight.setTexSize(128, 128);
        CheekRight.mirror = true;
        setRotation(CheekRight, 0F, 0F, 0F);
        HeadMain = new ModelRenderer(this, 0, 0);
        HeadMain.addBox(-4F, -8F, -4F, 8, 7, 8);
        HeadMain.setPos(0F, 0F, 0F);
        HeadMain.setTexSize(128, 128);
        HeadMain.mirror = true;
        setRotation(HeadMain, 0F, 0F, 0F);
        HeadUnder = new ModelRenderer(this, 32, 0);
        HeadUnder.addBox(-4F, -1F, -3F, 8, 1, 7);
        HeadUnder.setPos(0F, 0F, 0F);
        HeadUnder.setTexSize(128, 128);
        HeadUnder.mirror = true;
        setRotation(HeadUnder, 0F, 0F, 0F);
        Teeth = new ModelRenderer(this, 70, 0);
        Teeth.addBox(-1F, -1.7F, -3.9F, 2, 1, 1);
        Teeth.setPos(0F, 0F, 0F);
        Teeth.setTexSize(128, 128);
        Teeth.mirror = true;
        setRotation(Teeth, 0F, 0F, 0F);
        Hair1 = new ModelRenderer(this, 32, 8);
        Hair1.addBox(-4F, 0F, 0F, 8, 1, 1);
        Hair1.setPos(0F, -7F, -4F);
        Hair1.setTexSize(128, 128);
        Hair1.mirror = true;
        setRotation(Hair1, -0.0872665F, 0F, 0F);
        Hair2 = new ModelRenderer(this, 32, 10);
        Hair2.addBox(-4F, 1F, 0F, 1, 1, 1);
        Hair2.setPos(0F, -7F, -4F);
        Hair2.setTexSize(128, 128);
        Hair2.mirror = true;
        setRotation(Hair2, -0.0872665F, 0F, 0F);
        Hair3 = new ModelRenderer(this, 32, 10);
        Hair3.addBox(3F, 1F, 0F, 1, 1, 1);
        Hair3.setPos(0F, -7F, -4F);
        Hair3.setTexSize(128, 128);
        Hair3.mirror = true;
        setRotation(Hair3, -0.0872665F, 0F, 0F);
        Hair4 = new ModelRenderer(this, 32, 12);
        Hair4.addBox(-1F, 0F, -4F, 1, 2, 1);
        Hair4.setPos(4F, -7F, 0F);
        Hair4.setTexSize(128, 128);
        Hair4.mirror = true;
        setRotation(Hair4, 0F, 0F, -0.0872665F);
        Hair5 = new ModelRenderer(this, 32, 12);
        Hair5.addBox(-1F, 2F, -3F, 1, 2, 1);
        Hair5.setPos(4F, -7F, 0F);
        Hair5.setTexSize(128, 128);
        Hair5.mirror = true;
        setRotation(Hair5, 0F, 0F, -0.0872665F);
        Hair6 = new ModelRenderer(this, 32, 12);
        Hair6.addBox(-1F, 4F, -2F, 1, 2, 1);
        Hair6.setPos(4F, -7F, 0F);
        Hair6.setTexSize(128, 128);
        Hair6.mirror = true;
        setRotation(Hair6, 0F, 0F, -0.0872665F);
        Hair7 = new ModelRenderer(this, 36, 10);
        Hair7.addBox(-1F, 6F, -1F, 1, 1, 5);
        Hair7.setPos(4F, -7F, 0F);
        Hair7.setTexSize(128, 128);
        Hair7.mirror = true;
        setRotation(Hair7, 0F, 0F, -0.0872665F);
        Hair8 = new ModelRenderer(this, 48, 10);
        Hair8.addBox(-4F, 6F, -1F, 8, 1, 1);
        Hair8.setPos(0F, -7F, 4F);
        Hair8.setTexSize(128, 128);
        Hair8.mirror = true;
        setRotation(Hair8, 0.0872665F, 0F, 0F);
        Hair9 = new ModelRenderer(this, 36, 10);
        Hair9.addBox(0F, 6F, -1F, 1, 1, 5);
        Hair9.setPos(-4F, -7F, 0F);
        Hair9.setTexSize(128, 128);
        Hair9.mirror = true;
        setRotation(Hair9, 0F, 0F, 0.0872665F);
        Hair10 = new ModelRenderer(this, 32, 12);
        Hair10.addBox(0F, 4F, -2F, 1, 2, 1);
        Hair10.setPos(-4F, -7F, 0F);
        Hair10.setTexSize(128, 128);
        Hair10.mirror = true;
        setRotation(Hair10, 0F, 0F, 0.0872665F);
        Hair11 = new ModelRenderer(this, 32, 12);
        Hair11.addBox(0F, 2F, -3F, 1, 2, 1);
        Hair11.setPos(-4F, -7F, 0F);
        Hair11.setTexSize(128, 128);
        Hair11.mirror = true;
        setRotation(Hair11, 0F, 0F, 0.0872665F);
        Hair12 = new ModelRenderer(this, 32, 12);
        Hair12.addBox(0F, 0F, -4F, 1, 2, 1);
        Hair12.setPos(-4F, -7F, 0F);
        Hair12.setTexSize(128, 128);
        Hair12.mirror = true;
        setRotation(Hair12, 0F, 0F, 0.0872665F);
        TorsoMain = new ModelRenderer(this, 16, 16);
        TorsoMain.addBox(-4F, 0F, -2F, 8, 23, 4);
        TorsoMain.setPos(0F, 0F, 0F);
        TorsoMain.setTexSize(128, 128);
        TorsoMain.mirror = true;
        setRotation(TorsoMain, 0F, 0F, 0F);
        Dress1 = new ModelRenderer(this, 76, 16);
        Dress1.addBox(-4F, 0F, 0F, 8, 8, 1);
        Dress1.setPos(0F, 2F, -2F);
        Dress1.setTexSize(128, 128);
        Dress1.mirror = true;
        setRotation(Dress1, -0.0523599F, 0F, 0F);
        Dress2 = new ModelRenderer(this, 94, 16);
        Dress2.addBox(-1F, 0F, -2F, 1, 8, 4);
        Dress2.setPos(4F, 2F, 0F);
        Dress2.setTexSize(128, 128);
        Dress2.mirror = true;
        setRotation(Dress2, 0F, 0F, -0.0523599F);
        Dress3 = new ModelRenderer(this, 76, 16);
        Dress3.addBox(-4F, 0F, -1F, 8, 8, 1);
        Dress3.setPos(0F, 2F, 2F);
        Dress3.setTexSize(128, 128);
        Dress3.mirror = true;
        setRotation(Dress3, 0.0523599F, 0F, 0F);
        Dress4 = new ModelRenderer(this, 94, 16);
        Dress4.addBox(0F, 0F, -2F, 1, 8, 4);
        Dress4.setPos(-4F, 2F, 0F);
        Dress4.setTexSize(128, 128);
        Dress4.mirror = true;
        setRotation(Dress4, 0F, 0F, 0.0523599F);
        Dress5 = new ModelRenderer(this, 76, 25);
        Dress5.addBox(-4F, 0F, 0F, 8, 12, 1);
        Dress5.setPos(0F, 11F, -2F);
        Dress5.setTexSize(128, 128);
        Dress5.mirror = true;
        setRotation(Dress5, -0.0523599F, 0F, 0F);
        Dress6 = new ModelRenderer(this, 76, 38);
        Dress6.addBox(-1F, 0F, -2F, 1, 12, 4);
        Dress6.setPos(4F, 11F, 0F);
        Dress6.setTexSize(128, 128);
        Dress6.mirror = true;
        setRotation(Dress6, 0F, 0F, -0.0523599F);
        Dress7 = new ModelRenderer(this, 76, 25);
        Dress7.addBox(-4F, 0F, -1F, 8, 12, 1);
        Dress7.setPos(0F, 11F, 2F);
        Dress7.setTexSize(128, 128);
        Dress7.mirror = true;
        setRotation(Dress7, 0.0523599F, 0F, 0F);
        Dress8 = new ModelRenderer(this, 76, 38);
        Dress8.addBox(0F, 0F, -2F, 1, 12, 4);
        Dress8.setPos(-4F, 11F, 0F);
        Dress8.setTexSize(128, 128);
        Dress8.mirror = true;
        setRotation(Dress8, 0F, 0F, 0.0523599F);
        Dress9 = new ModelRenderer(this, 76, 59);
        Dress9.addBox(-4F, 0F, 0F, 8, 1, 3);
        Dress9.setPos(0F, 23F, -3F);
        Dress9.setTexSize(128, 128);
        Dress9.mirror = true;
        setRotation(Dress9, 1.27409F, 0F, 0F);
        Dress10 = new ModelRenderer(this, 76, 63);
        Dress10.addBox(-3F, 0F, -2F, 3, 1, 4);
        Dress10.setPos(5F, 23F, 0F);
        Dress10.setTexSize(128, 128);
        Dress10.mirror = true;
        setRotation(Dress10, 0F, 0F, 1.27409F);
        Dress11 = new ModelRenderer(this, 76, 59);
        Dress11.addBox(-4F, 0F, -3F, 8, 1, 3);
        Dress11.setPos(0F, 23F, 3F);
        Dress11.setTexSize(128, 128);
        Dress11.mirror = true;
        setRotation(Dress11, -1.27409F, 0F, 0F);
        Dress12 = new ModelRenderer(this, 76, 63);
        Dress12.addBox(0F, 0F, -2F, 3, 1, 4);
        Dress12.setPos(-5F, 23F, 0F);
        Dress12.setTexSize(128, 128);
        Dress12.mirror = true;
        setRotation(Dress12, 0F, 0F, -1.27409F);
        Dress13 = new ModelRenderer(this, 76, 68);
        Dress13.addBox(-5F, 23F, -3F, 10, 1, 6);
        Dress13.setPos(0F, 0F, 0F);
        Dress13.setTexSize(128, 128);
        Dress13.mirror = true;
        setRotation(Dress13, 0F, 0F, 0F);
        RightArm1 = new ModelRenderer(this, 58, 16);
        RightArm1.addBox(-3F, -3F, 0F, 3, 3, 6);
        RightArm1.setPos(-5F, 3F, -5F);
        RightArm1.setTexSize(128, 128);
        RightArm1.mirror = true;
        setRotation(RightArm1, -0.2268928F, 0F, 0.5235988F);
        RightArm2 = new ModelRenderer(this, 58, 25);
        RightArm2.addBox(-3F, -3F, -6F, 3, 3, 6);
        RightArm2.setPos(-5F, 3F, -5F);
        RightArm2.setTexSize(128, 128);
        RightArm2.mirror = true;
        setRotation(RightArm2, -0.9250245F, 0F, 0.5235988F);
        LeftArm1 = new ModelRenderer(this, 40, 16);
        LeftArm1.addBox(0F, -3F, 0F, 3, 3, 6);
        LeftArm1.setPos(5F, 3F, -5F);
        LeftArm1.setTexSize(128, 128);
        LeftArm1.mirror = true;
        setRotation(LeftArm1, -0.2268928F, 0F, -0.5235988F);
        LeftArm2 = new ModelRenderer(this, 40, 25);
        LeftArm2.addBox(0F, -3F, -6F, 3, 3, 6);
        LeftArm2.setPos(5F, 3F, -5F);
        LeftArm2.setTexSize(128, 128);
        LeftArm2.mirror = true;
        setRotation(LeftArm2, -0.9250245F, 0F, -0.5235988F);
        LeftWing1 = new ModelRenderer(this, 0, 43);
        LeftWing1.addBox(-3F, 0F, -1F, 3, 6, 1);
        LeftWing1.setPos(3F, 1.5F, 4F);
        LeftWing1.setTexSize(128, 128);
        LeftWing1.mirror = true;
        setRotation(LeftWing1, 0F, -0.8726646F, 0F);
        LeftWing2 = new ModelRenderer(this, 10, 43);
        LeftWing2.addBox(0F, 0F, -1F, 3, 6, 1);
        LeftWing2.setPos(3F, 1.5F, 4F);
        LeftWing2.setTexSize(128, 128);
        LeftWing2.mirror = true;
        setRotation(LeftWing2, 0F, -0.122173F, 0F);
        LeftWing3 = new ModelRenderer(this, 20, 43);
        LeftWing3.addBox(3F, -2F, -1F, 1, 10, 1);
        LeftWing3.setPos(3F, 1.5F, 4F);
        LeftWing3.setTexSize(128, 128);
        LeftWing3.mirror = true;
        setRotation(LeftWing3, 0F, -0.122173F, 0F);
        LeftWing4 = new ModelRenderer(this, 26, 43);
        LeftWing4.addBox(4F, -3F, -1F, 1, 13, 1);
        LeftWing4.setPos(3F, 1.5F, 4F);
        LeftWing4.setTexSize(128, 128);
        LeftWing4.mirror = true;
        setRotation(LeftWing4, 0F, -0.122173F, 0F);
        LeftWing5 = new ModelRenderer(this, 32, 43);
        LeftWing5.addBox(5F, -4F, -1F, 1, 16, 1);
        LeftWing5.setPos(3F, 1.5F, 4F);
        LeftWing5.setTexSize(128, 128);
        LeftWing5.mirror = true;
        setRotation(LeftWing5, 0F, -0.122173F, 0F);
        LeftWing6 = new ModelRenderer(this, 38, 43);
        LeftWing6.addBox(6F, -4F, -1F, 1, 18, 1);
        LeftWing6.setPos(3F, 1.5F, 4F);
        LeftWing6.setTexSize(128, 128);
        LeftWing6.mirror = true;
        setRotation(LeftWing6, 0F, -0.122173F, 0F);
        LeftWing7 = new ModelRenderer(this, 44, 43);
        LeftWing7.addBox(7F, -3F, -1F, 1, 19, 1);
        LeftWing7.setPos(3F, 1.5F, 4F);
        LeftWing7.setTexSize(128, 128);
        LeftWing7.mirror = true;
        setRotation(LeftWing7, 0F, -0.122173F, 0F);
        LeftWing8 = new ModelRenderer(this, 50, 43);
        LeftWing8.addBox(8F, -2F, -1F, 1, 17, 1);
        LeftWing8.setPos(3F, 1.5F, 4F);
        LeftWing8.setTexSize(128, 128);
        LeftWing8.mirror = true;
        setRotation(LeftWing8, 0F, -0.122173F, 0F);
        LeftWing9 = new ModelRenderer(this, 56, 43);
        LeftWing9.addBox(9F, 0F, -1F, 1, 13, 1);
        LeftWing9.setPos(3F, 1.5F, 4F);
        LeftWing9.setTexSize(128, 128);
        LeftWing9.mirror = true;
        setRotation(LeftWing9, 0F, -0.122173F, 0F);
        RightWing1 = new ModelRenderer(this, 0, 43);
        RightWing1.addBox(0F, 0F, -1F, 3, 6, 1);
        RightWing1.setPos(-3F, 1.5F, 4F);
        RightWing1.setTexSize(128, 128);
        RightWing1.mirror = true;
        setRotation(RightWing1, 0F, 0.8726646F, 0F);
        RightWing2 = new ModelRenderer(this, 10, 43);
        RightWing2.addBox(-3F, 0F, -1F, 3, 6, 1);
        RightWing2.setPos(-3F, 1.5F, 4F);
        RightWing2.setTexSize(128, 128);
        RightWing2.mirror = true;
        setRotation(RightWing2, 0F, 0.122173F, 0F);
        RightWing3 = new ModelRenderer(this, 20, 43);
        RightWing3.addBox(-4F, -2F, -1F, 1, 10, 1);
        RightWing3.setPos(-3F, 1.5F, 4F);
        RightWing3.setTexSize(128, 128);
        RightWing3.mirror = true;
        setRotation(RightWing3, 0F, 0.122173F, 0F);
        RightWing4 = new ModelRenderer(this, 26, 43);
        RightWing4.addBox(-5F, -3F, -1F, 1, 13, 1);
        RightWing4.setPos(-3F, 1.5F, 4F);
        RightWing4.setTexSize(128, 128);
        RightWing4.mirror = true;
        setRotation(RightWing4, 0F, 0.122173F, 0F);
        RightWing5 = new ModelRenderer(this, 32, 43);
        RightWing5.addBox(-6F, -4F, -1F, 1, 16, 1);
        RightWing5.setPos(-3F, 1.5F, 4F);
        RightWing5.setTexSize(128, 128);
        RightWing5.mirror = true;
        setRotation(RightWing5, 0F, 0.122173F, 0F);
        RightWing6 = new ModelRenderer(this, 38, 43);
        RightWing6.addBox(-7F, -4F, -1F, 1, 18, 1);
        RightWing6.setPos(-3F, 1.5F, 4F);
        RightWing6.setTexSize(128, 128);
        RightWing6.mirror = true;
        setRotation(RightWing6, 0F, 0.122173F, 0F);
        RightWing7 = new ModelRenderer(this, 44, 43);
        RightWing7.addBox(-8F, -3F, -1F, 1, 19, 1);
        RightWing7.setPos(-3F, 1.5F, 4F);
        RightWing7.setTexSize(128, 128);
        RightWing7.mirror = true;
        setRotation(RightWing7, 0F, 0.122173F, 0F);
        RightWing8 = new ModelRenderer(this, 50, 43);
        RightWing8.addBox(-9F, -2F, -1F, 1, 17, 1);
        RightWing8.setPos(-3F, 1.5F, 4F);
        RightWing8.setTexSize(128, 128);
        RightWing8.mirror = true;
        setRotation(RightWing8, 0F, 0.122173F, 0F);
        RightWing9 = new ModelRenderer(this, 56, 43);
        RightWing9.addBox(-10F, 0F, -1F, 1, 13, 1);
        RightWing9.setPos(-3F, 1.5F, 4F);
        RightWing9.setTexSize(128, 128);
        RightWing9.mirror = true;
        setRotation(RightWing9, 0F, 0.122173F, 0F);

        AngryRightArm1 = new ModelRenderer(this, 58, 16);
        AngryRightArm1.addBox(-3F, -3F, 0F, 3, 3, 6);
        AngryRightArm1.setPos(-4F, 5F, -4F);
        AngryRightArm1.setTexSize(128, 128);
        AngryRightArm1.mirror = true;
        setRotation(AngryRightArm1, 0.2792527F, 0F, 0F);
        AngryRightArm2 = new ModelRenderer(this, 58, 25);
        AngryRightArm2.addBox(-3F, -3F, -6F, 3, 3, 6);
        AngryRightArm2.setPos(-4F, 5F, -4F);
        AngryRightArm2.setTexSize(128, 128);
        AngryRightArm2.mirror = true;
        setRotation(AngryRightArm2, -0.5235988F, 0F, 0F);
        AngryLeftArm1 = new ModelRenderer(this, 40, 16);
        AngryLeftArm1.addBox(0F, -3F, 0F, 3, 3, 6);
        AngryLeftArm1.setPos(4F, 5F, -4F);
        AngryLeftArm1.setTexSize(128, 128);
        AngryLeftArm1.mirror = true;
        setRotation(AngryLeftArm1, 0.2792527F, 0F, 0F);
        AngryLeftArm2 = new ModelRenderer(this, 40, 25);
        AngryLeftArm2.addBox(0F, -3F, -6F, 3, 3, 6);
        AngryLeftArm2.setPos(4F, 5F, -4F);
        AngryLeftArm2.setTexSize(128, 128);
        AngryLeftArm2.mirror = true;
        setRotation(AngryLeftArm2, -0.5235988F, 0F, 0F);

    }

    @Override
    public void setupAnim(WeepingAngelEntity weepingAngelEntity, float v, float v1, float v2, float v3, float v4) {
        angelEntity = weepingAngelEntity;
    }


    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    @Override
    public Iterable<ModelRenderer> wings(MatrixStack pose) {
        return ImmutableList.of(LeftWing1, LeftWing2, LeftWing3, LeftWing4, LeftWing5, LeftWing6, LeftWing7, LeftWing8, LeftWing9, RightWing1, RightWing2, RightWing3, RightWing4, RightWing5, RightWing6, RightWing7, RightWing8, RightWing9);
    }

    @Override
    public ModelRenderer getSantaAttachment(MatrixStack pose, boolean b) {
        return null;
    }

    @Override
    public ResourceLocation getTextureForPose(Object weepingAngelEntity, WeepingAngelPose pose) {
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

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        WeepingAngelPose pose = weepingAngelPose;
        if (angelEntity != null) {
            pose = WeepingAngelPose.getPose(angelEntity.getAngelPose());
        }


        // Head
        matrixStack.pushPose(); // PUSH 1

        matrixStack.pushPose(); // PUSH 2
        if (pose != WeepingAngelPose.ANGRY) {
            matrixStack.mulPose(Vector3f.XP.rotationDegrees(7));
        }
        LeftEyebrow.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        RightEyebrow.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Headband.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Nose.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Chin.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        ChinAngleLeft.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        ChinAngleRight.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        CheekLeft.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        CheekRight.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        HeadMain.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        HeadUnder.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Teeth.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Hair1.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Hair2.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Hair3.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Hair4.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Hair5.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Hair6.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Hair7.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Hair8.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Hair9.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Hair10.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Hair11.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        Hair12.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        matrixStack.popPose();

        if (pose == WeepingAngelPose.ANGRY) {
            AngryRightArm1.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
            AngryRightArm2.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
            AngryLeftArm1.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
            AngryLeftArm2.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        } else {
            RightArm1.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
            RightArm2.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
            LeftArm1.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
            LeftArm2.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        }

        // Body
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
        LeftWing1.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        LeftWing2.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        LeftWing3.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        LeftWing4.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        LeftWing5.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        LeftWing6.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        LeftWing7.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        LeftWing8.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        LeftWing9.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        RightWing1.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        RightWing2.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        RightWing3.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        RightWing4.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        RightWing5.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        RightWing6.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        RightWing7.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        RightWing8.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        RightWing9.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);

        matrixStack.popPose(); // POP 2
    }

}
