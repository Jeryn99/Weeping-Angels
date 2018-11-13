package me.fril.angels.client.models.entity;

import me.fril.angels.client.models.poses.PoseBase;
import me.fril.angels.client.models.poses.PoseManager;
import me.fril.angels.client.models.poses.PoseThinking;
import me.fril.angels.common.entities.EntityWeepingAngel;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBluAngel extends ModelBiped {
	
	ModelRenderer leftleg;
	ModelRenderer rightleg;
	ModelRenderer torso;
	ModelRenderer Block;
	ModelRenderer Block1;
	ModelRenderer Block2;
	ModelRenderer Block3;
	ModelRenderer Block4;
	ModelRenderer Block5;
	ModelRenderer Block6;
	ModelRenderer Block7;
	ModelRenderer Block8;
	ModelRenderer Block9;
	ModelRenderer head;
	ModelRenderer Block10;
	ModelRenderer Block11;
	ModelRenderer Block12;
	ModelRenderer Block13;
	ModelRenderer Block14;
	ModelRenderer Block15;
	ModelRenderer Block16;
	ModelRenderer Block17;
	ModelRenderer Block18;
	ModelRenderer Block19;
	ModelRenderer Block20;
	ModelRenderer Block21;
	ModelRenderer browleft;
	ModelRenderer Block22;
	ModelRenderer Block23;
	ModelRenderer Block24;
	ModelRenderer Block25;
	ModelRenderer Block26;
	ModelRenderer Block27;
	ModelRenderer browright;
	ModelRenderer skirt;
	ModelRenderer Block28;
	ModelRenderer Block29;
	ModelRenderer Block30;
	ModelRenderer Block31;
	ModelRenderer Block32;
	ModelRenderer Block33;
	ModelRenderer Block34;
	ModelRenderer Block35;
	ModelRenderer Block36;
	ModelRenderer Block37;
	ModelRenderer Block38;
	ModelRenderer Block39;
	ModelRenderer Block40;
	ModelRenderer Block41;
	ModelRenderer Block42;
	ModelRenderer Block43;
	ModelRenderer Block44;
	ModelRenderer Block45;
	ModelRenderer leftwing;
	ModelRenderer Block46;
	ModelRenderer Block47;
	ModelRenderer Block48;
	ModelRenderer Block49;
	ModelRenderer Block50;
	ModelRenderer Block51;
	ModelRenderer Block52;
	ModelRenderer Block53;
	ModelRenderer Block54;
	ModelRenderer Block55;
	ModelRenderer rightwing;
	ModelRenderer Block56;
	ModelRenderer Block57;
	ModelRenderer Block58;
	ModelRenderer Block59;
	ModelRenderer Block60;
	ModelRenderer Block61;
	ModelRenderer Block62;
	ModelRenderer Block63;
	ModelRenderer Block64;
	ModelRenderer Block65;
	ModelRenderer waist;
	ModelRenderer Block66;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightwrist;
	ModelRenderer leftwrist;
	
	public ModelBluAngel() {
		this(0.0f);
	}
	
	public ModelBluAngel(float par1) {
		leftleg = new ModelRenderer(this, 42, 108);
		leftleg.setTextureSize(128, 128);
		leftleg.addBox(-4F, -12F, -2F, 4, 12, 4);
		leftleg.setRotationPoint(0F, 24F, 0F);
		rightleg = new ModelRenderer(this, 42, 108);
		rightleg.setTextureSize(128, 128);
		rightleg.addBox(0F, -12F, -2F, 4, 12, 4);
		rightleg.setRotationPoint(0F, 24F, 0F);
		torso = new ModelRenderer(this, 6, 75);
		torso.setTextureSize(128, 128);
		torso.addBox(-4.5F, -26F, -3F, 9, 12, 6);
		torso.setRotationPoint(0F, 24F, 0F);
		Block = new ModelRenderer(this, 42, 111);
		Block.setTextureSize(128, 128);
		Block.addBox(3F, -20F, -3.5F, 1, 6, 7);
		Block.setRotationPoint(0F, 24F, 0F);
		Block1 = new ModelRenderer(this, 42, 111);
		Block1.setTextureSize(128, 128);
		Block1.addBox(2F, -25F, -3.5F, 1, 6, 7);
		Block1.setRotationPoint(0F, 24F, 0F);
		Block2 = new ModelRenderer(this, 42, 111);
		Block2.setTextureSize(128, 128);
		Block2.addBox(0.5F, -20F, -3.5F, 1, 4, 7);
		Block2.setRotationPoint(0F, 24F, 0F);
		Block3 = new ModelRenderer(this, 42, 111);
		Block3.setTextureSize(128, 128);
		Block3.addBox(-2.5F, -22F, -3.5F, 3, 2, 7);
		Block3.setRotationPoint(0F, 24F, 0F);
		Block4 = new ModelRenderer(this, 42, 111);
		Block4.setTextureSize(128, 128);
		Block4.addBox(1F, -24F, -3.5F, 1, 2, 7);
		Block4.setRotationPoint(0F, 24F, 0F);
		Block5 = new ModelRenderer(this, 42, 111);
		Block5.setTextureSize(128, 128);
		Block5.addBox(3F, -26F, -3.5F, 1, 2, 7);
		Block5.setRotationPoint(0F, 24F, 0F);
		Block6 = new ModelRenderer(this, 48, 117);
		Block6.setTextureSize(128, 128);
		Block6.addBox(-5F, -26F, -2.5F, 10, 12, 1);
		Block6.setRotationPoint(0F, 24F, 0F);
		Block7 = new ModelRenderer(this, 42, 111);
		Block7.setTextureSize(128, 128);
		Block7.addBox(-1.5F, -20F, -3.5F, 2, 4, 7);
		Block7.setRotationPoint(0F, 24F, 0F);
		Block8 = new ModelRenderer(this, 42, 111);
		Block8.setTextureSize(128, 128);
		Block8.addBox(-3.5F, -20F, -3.5F, 1, 6, 7);
		Block8.setRotationPoint(0F, 24F, 0F);
		Block9 = new ModelRenderer(this, 42, 111);
		Block9.setTextureSize(128, 128);
		Block9.addBox(-4.5F, -25F, -3.5F, 1, 4, 7);
		Block9.setRotationPoint(0F, 24F, 0F);
		head = new ModelRenderer(this, 11, 57);
		head.setTextureSize(128, 128);
		head.addBox(-4F, -34F, -4F, 8, 8, 8);
		head.setRotationPoint(0F, 24F, 0F);
		Block10 = new ModelRenderer(this, 87, 79);
		Block10.setTextureSize(128, 128);
		Block10.addBox(-4.5F, -32F, -0.25F, 9, 1, 2);
		Block10.setRotationPoint(0F, 21.5F, -4F);
		Block11 = new ModelRenderer(this, 93, 58);
		Block11.setTextureSize(128, 128);
		Block11.addBox(-0.5F, -0.75F, 1.5F, 1, 1, 7);
		Block11.setRotationPoint(0F, -10F, -4F);
		Block12 = new ModelRenderer(this, 86, 83);
		Block12.setTextureSize(128, 128);
		Block12.addBox(-4.25F, -32.5F, 1F, 4, 3, 8);
		Block12.setRotationPoint(0F, 21.5F, -4F);
		Block13 = new ModelRenderer(this, 91, 114);
		Block13.setTextureSize(128, 128);
		Block13.addBox(-3.5F, -31.5F, -0.5F, 7, 1, 2);
		Block13.setRotationPoint(0F, 21.5F, -4F);
		Block14 = new ModelRenderer(this, 104, 87);
		Block14.setTextureSize(128, 128);
		Block14.addBox(0.25F, -32.5F, 1F, 4, 3, 8);
		Block14.setRotationPoint(0F, 21.5F, -4F);
		Block15 = new ModelRenderer(this, 91, 65);
		Block15.setTextureSize(128, 128);
		Block15.addBox(-4F, -0.5F, -0.5F, 8, 1, 1);
		Block15.setRotationPoint(0F, -10.25F, -3F);
		Block16 = new ModelRenderer(this, 89, 103);
		Block16.setTextureSize(128, 128);
		Block16.addBox(0.25F, -29.5F, 5F, 5, 5, 4);
		Block16.setRotationPoint(0F, 21.5F, -4F);
		Block17 = new ModelRenderer(this, 89, 94);
		Block17.setTextureSize(128, 128);
		Block17.addBox(-5.25F, -29.5F, 5F, 5, 5, 4);
		Block17.setRotationPoint(0F, 21.5F, -4F);
		Block18 = new ModelRenderer(this, 99, 58);
		Block18.setTextureSize(128, 128);
		Block18.addBox(-0.5F, -0.25F, 7.5F, 1, 7, 1);
		Block18.setRotationPoint(0F, -10F, -4F);
		Block19 = new ModelRenderer(this, 84, 94);
		Block19.setTextureSize(128, 128);
		Block19.addBox(-0.5F, -27.5F, -5F, 1, 2, 1);
		Block19.setRotationPoint(0F, 21.5F, -4F);
		Block20 = new ModelRenderer(this, 102, 85);
		Block20.setTextureSize(128, 128);
		Block20.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Block20.setRotationPoint(-0.75F, -3.65F, -3.4F);
		Block21 = new ModelRenderer(this, 102, 85);
		Block21.setTextureSize(128, 128);
		Block21.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Block21.setRotationPoint(0.75F, -3.65F, -3.4F);
		browleft = new ModelRenderer(this, 85, 86);
		browleft.setTextureSize(128, 128);
		browleft.addBox(-1.5F, -0.5F, -0.5F, 3, 1, 1);
		browleft.setRotationPoint(2.1F, -8F, -3.55F);
		Block22 = new ModelRenderer(this, 88, 73);
		Block22.setTextureSize(128, 128);
		Block22.addBox(-1F, -28.5F, 8F, 2, 3, 2);
		Block22.setRotationPoint(0F, 21.5F, -4F);
		Block23 = new ModelRenderer(this, 42, 111);
		Block23.setTextureSize(128, 128);
		Block23.addBox(2F, -15F, -3.5F, 1, 1, 7);
		Block23.setRotationPoint(0F, 24F, 0F);
		Block24 = new ModelRenderer(this, 42, 111);
		Block24.setTextureSize(128, 128);
		Block24.addBox(-0.5F, -16F, -3.5F, 1, 2, 7);
		Block24.setRotationPoint(0F, 24F, 0F);
		Block25 = new ModelRenderer(this, 42, 111);
		Block25.setTextureSize(128, 128);
		Block25.addBox(-1.5F, -26F, -3.5F, 1, 4, 7);
		Block25.setRotationPoint(0F, 24F, 0F);
		Block26 = new ModelRenderer(this, 48, 117);
		Block26.setTextureSize(128, 128);
		Block26.addBox(-5F, -26F, -1F, 10, 12, 1);
		Block26.setRotationPoint(0F, 24F, 0F);
		Block27 = new ModelRenderer(this, 47, 116);
		Block27.setTextureSize(128, 128);
		Block27.addBox(-5F, -26F, 0.5F, 10, 12, 2);
		Block27.setRotationPoint(0F, 24F, 0F);
		browright = new ModelRenderer(this, 85, 86);
		browright.setTextureSize(128, 128);
		browright.addBox(-1.5F, -0.5F, -0.5F, 3, 1, 1);
		browright.setRotationPoint(-2.1F, -8F, -3.55F);
		skirt = new ModelRenderer(this, 40, 91);
		skirt.setTextureSize(128, 128);
		skirt.addBox(-4.5F, -13F, -3F, 9, 6, 6);
		skirt.setRotationPoint(0F, 24F, 0F);
		Block28 = new ModelRenderer(this, 39, 91);
		Block28.setTextureSize(128, 128);
		Block28.addBox(-5F, -7F, -3.5F, 10, 6, 7);
		Block28.setRotationPoint(0F, 24F, 0F);
		Block29 = new ModelRenderer(this, 41, 110);
		Block29.setTextureSize(128, 128);
		Block29.addBox(1F, -7F, -4F, 2, 6, 8);
		Block29.setRotationPoint(0F, 24F, 0F);
		Block30 = new ModelRenderer(this, 42, 111);
		Block30.setTextureSize(128, 128);
		Block30.addBox(2.5F, -13F, -3.5F, 1, 6, 7);
		Block30.setRotationPoint(0F, 24F, 0F);
		Block31 = new ModelRenderer(this, 42, 111);
		Block31.setTextureSize(128, 128);
		Block31.addBox(1F, -13F, -3.5F, 1, 6, 7);
		Block31.setRotationPoint(0F, 24F, 0F);
		Block32 = new ModelRenderer(this, 42, 111);
		Block32.setTextureSize(128, 128);
		Block32.addBox(-1.5F, -13F, -3.5F, 2, 6, 7);
		Block32.setRotationPoint(0F, 24F, 0F);
		Block33 = new ModelRenderer(this, 42, 111);
		Block33.setTextureSize(128, 128);
		Block33.addBox(-4F, -13F, -3.5F, 2, 6, 7);
		Block33.setRotationPoint(0F, 24F, 0F);
		Block34 = new ModelRenderer(this, 48, 117);
		Block34.setTextureSize(128, 128);
		Block34.addBox(-5F, -13F, -2.5F, 10, 6, 1);
		Block34.setRotationPoint(0F, 24F, 0F);
		Block35 = new ModelRenderer(this, 47, 116);
		Block35.setTextureSize(128, 128);
		Block35.addBox(-5F, -13F, -1F, 10, 6, 2);
		Block35.setRotationPoint(0F, 24F, 0F);
		Block36 = new ModelRenderer(this, 48, 117);
		Block36.setTextureSize(128, 128);
		Block36.addBox(-5F, -13F, 1.5F, 10, 6, 1);
		Block36.setRotationPoint(0F, 24F, 0F);
		Block37 = new ModelRenderer(this, 41, 110);
		Block37.setTextureSize(128, 128);
		Block37.addBox(-2F, -7F, -4F, 2, 6, 8);
		Block37.setRotationPoint(0F, 24F, 0F);
		Block38 = new ModelRenderer(this, 48, 117);
		Block38.setTextureSize(128, 128);
		Block38.addBox(-4.5F, -7F, 3F, 2, 6, 1);
		Block38.setRotationPoint(0F, 24F, 0F);
		Block39 = new ModelRenderer(this, 47, 116);
		Block39.setTextureSize(128, 128);
		Block39.addBox(-5.5F, -7F, 1F, 11, 6, 2);
		Block39.setRotationPoint(0F, 24F, 0F);
		Block40 = new ModelRenderer(this, 47, 116);
		Block40.setTextureSize(128, 128);
		Block40.addBox(-5.5F, -7F, -1.5F, 11, 6, 2);
		Block40.setRotationPoint(0F, 24F, 0F);
		Block41 = new ModelRenderer(this, 48, 117);
		Block41.setTextureSize(128, 128);
		Block41.addBox(-5.5F, -7F, -3.5F, 1, 6, 1);
		Block41.setRotationPoint(0F, 24F, 0F);
		Block42 = new ModelRenderer(this, 48, 117);
		Block42.setTextureSize(128, 128);
		Block42.addBox(-5F, -7F, -4F, 2, 6, 1);
		Block42.setRotationPoint(0F, 24F, 0F);
		Block43 = new ModelRenderer(this, 48, 117);
		Block43.setTextureSize(128, 128);
		Block43.addBox(4F, -7F, -4F, 1, 6, 1);
		Block43.setRotationPoint(0F, 24F, 0F);
		Block44 = new ModelRenderer(this, 48, 117);
		Block44.setTextureSize(128, 128);
		Block44.addBox(4.5F, -7F, -3.5F, 1, 6, 1);
		Block44.setRotationPoint(0F, 24F, 0F);
		Block45 = new ModelRenderer(this, 45, 49);
		Block45.setTextureSize(128, 128);
		Block45.addBox(-6F, -1F, -4.5F, 12, 1, 9);
		Block45.setRotationPoint(0F, 24F, 0F);
		leftwing = new ModelRenderer(this, 52, 9);
		leftwing.setTextureSize(128, 128);
		leftwing.addBox(7.25F, -29F, 2.5F, 4, 16, 1);
		leftwing.setRotationPoint(0F, 24F, 0F);
		Block46 = new ModelRenderer(this, 65, 13);
		Block46.setTextureSize(128, 128);
		Block46.addBox(6.5F, -25F, 1.5F, 1, 1, 1);
		Block46.setRotationPoint(-0.5003005F, 23F, 0.9012215F);
		Block47 = new ModelRenderer(this, 37, 11);
		Block47.setTextureSize(128, 128);
		Block47.addBox(8F, -29F, 1.5F, 3, 1, 1);
		Block47.setRotationPoint(-0.5003005F, 23F, 0.9012215F);
		Block48 = new ModelRenderer(this, 15, 15);
		Block48.setTextureSize(128, 128);
		Block48.addBox(6.5F, -15F, 1.5F, 1, 2, 1);
		Block48.setRotationPoint(-0.5003005F, 23F, 0.9012215F);
		Block49 = new ModelRenderer(this, 63, 27);
		Block49.setTextureSize(128, 128);
		Block49.addBox(7.5F, -12F, 1.5F, 4, 1, 1);
		Block49.setRotationPoint(-0.5003005F, 23F, 0.9012215F);
		Block50 = new ModelRenderer(this, 67, 21);
		Block50.setTextureSize(128, 128);
		Block50.addBox(8.5F, -11F, 1.5F, 3, 2, 1);
		Block50.setRotationPoint(-0.5003005F, 23F, 0.9012215F);
		Block51 = new ModelRenderer(this, 40, 31);
		Block51.setTextureSize(128, 128);
		Block51.addBox(9.5F, -9F, 1.5F, 3, 2, 1);
		Block51.setRotationPoint(-0.5003005F, 23F, 0.9012215F);
		Block52 = new ModelRenderer(this, 22, 22);
		Block52.setTextureSize(128, 128);
		Block52.addBox(11.5F, -27F, 1.5F, 1, 18, 1);
		Block52.setRotationPoint(-0.5003005F, 23F, 0.9012215F);
		Block53 = new ModelRenderer(this, 29, 11);
		Block53.setTextureSize(128, 128);
		Block53.addBox(12.5F, -25F, 1.5F, 1, 17, 1);
		Block53.setRotationPoint(-0.5003005F, 23F, 0.9012215F);
		Block54 = new ModelRenderer(this, 53, 31);
		Block54.setTextureSize(128, 128);
		Block54.addBox(3.5F, -24F, 1.5F, 4, 9, 1);
		Block54.setRotationPoint(-0.1035266F, 23F, 0.3863707F);
		Block55 = new ModelRenderer(this, 45, 16);
		Block55.setTextureSize(128, 128);
		Block55.addBox(2.5F, -24F, 1.5F, 1, 8, 1);
		Block55.setRotationPoint(-0.1035266F, 23F, 0.3863707F);
		rightwing = new ModelRenderer(this, 74, 28);
		rightwing.setTextureSize(128, 128);
		rightwing.addBox(-11.3F, -29F, 2.5F, 4, 16, 1);
		rightwing.setRotationPoint(0F, 24F, 0F);
		Block56 = new ModelRenderer(this, 65, 13);
		Block56.setTextureSize(128, 128);
		Block56.addBox(11.5F, -25F, 1.5F, 1, 1, 1);
		Block56.setRotationPoint(-17.90059F, 23F, 5.831725F);
		Block57 = new ModelRenderer(this, 37, 11);
		Block57.setTextureSize(128, 128);
		Block57.addBox(8F, -29F, 1.5F, 3, 1, 1);
		Block57.setRotationPoint(-17.90059F, 23F, 5.831725F);
		Block58 = new ModelRenderer(this, 15, 15);
		Block58.setTextureSize(128, 128);
		Block58.addBox(11.5F, -15F, 1.5F, 1, 2, 1);
		Block58.setRotationPoint(-17.90059F, 23F, 5.831725F);
		Block59 = new ModelRenderer(this, 63, 30);
		Block59.setTextureSize(128, 128);
		Block59.addBox(7.5F, -12F, 1.5F, 4, 1, 1);
		Block59.setRotationPoint(-17.90059F, 23F, 5.831725F);
		Block60 = new ModelRenderer(this, 67, 17);
		Block60.setTextureSize(128, 128);
		Block60.addBox(7.5F, -11F, 1.5F, 3, 2, 1);
		Block60.setRotationPoint(-17.90059F, 23F, 5.831725F);
		Block61 = new ModelRenderer(this, 40, 31);
		Block61.setTextureSize(128, 128);
		Block61.addBox(6.5F, -9F, 1.5F, 3, 2, 1);
		Block61.setRotationPoint(-17.90059F, 23F, 5.831725F);
		Block62 = new ModelRenderer(this, 22, 22);
		Block62.setTextureSize(128, 128);
		Block62.addBox(6.5F, -27F, 1.5F, 1, 18, 1);
		Block62.setRotationPoint(-17.90059F, 23F, 5.831725F);
		Block63 = new ModelRenderer(this, 29, 11);
		Block63.setTextureSize(128, 128);
		Block63.addBox(5.5F, -25F, 1.5F, 1, 17, 1);
		Block63.setRotationPoint(-17.90059F, 23F, 5.831725F);
		Block64 = new ModelRenderer(this, 77, 15);
		Block64.setTextureSize(128, 128);
		Block64.addBox(11.65F, -24F, 3F, 4, 9, 1);
		Block64.setRotationPoint(-18.49054F, 23F, 5.368637F);
		Block65 = new ModelRenderer(this, 45, 16);
		Block65.setTextureSize(128, 128);
		Block65.addBox(7.5F, -24F, 1.5F, 1, 8, 1);
		Block65.setRotationPoint(-10.31902F, 23F, 3.990711F);
		waist = new ModelRenderer(this, 6, 100);
		waist.setTextureSize(128, 128);
		waist.addBox(-4.5F, -14F, -3.5F, 9, 1, 7);
		waist.setRotationPoint(0F, 24F, 0F);
		Block66 = new ModelRenderer(this, 7, 101);
		Block66.setTextureSize(128, 128);
		Block66.addBox(-5F, -14F, -3F, 10, 1, 6);
		Block66.setRotationPoint(0F, 24F, 0F);
		rightarm = new ModelRenderer(this, 4, 20);
		rightarm.setTextureSize(128, 128);
		rightarm.addBox(-7.5F, -25.5F, -2F, 3, 6, 4);
		rightarm.setRotationPoint(0F, 24F, 0F);
		leftarm = new ModelRenderer(this, 4, 20);
		leftarm.setTextureSize(128, 128);
		leftarm.addBox(4.5F, -25.5F, -2F, 3, 6, 4);
		leftarm.setRotationPoint(0F, 24F, 0F);
		rightwrist = new ModelRenderer(this, 4, 20);
		rightwrist.setTextureSize(128, 128);
		rightwrist.addBox(-7.5F, -19.5F, -2F, 3, 6, 4);
		rightwrist.setRotationPoint(0F, 24F, 0F);
		leftwrist = new ModelRenderer(this, 4, 20);
		leftwrist.setTextureSize(128, 128);
		leftwrist.addBox(4.5F, -19.5F, -2F, 3, 6, 4);
		leftwrist.setRotationPoint(0F, 24F, 0F);
	}
	
	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		
		angelAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		
		leftleg.renderWithRotation(par7);
		rightleg.renderWithRotation(par7);
		
		torso.rotateAngleX = 0F;
		torso.rotateAngleY = 0F;
		torso.rotateAngleZ = 0F;
		torso.renderWithRotation(par7);
		
		Block.rotateAngleX = 0F;
		Block.rotateAngleY = 0F;
		Block.rotateAngleZ = 0F;
		Block.renderWithRotation(par7);
		
		Block1.rotateAngleX = 0F;
		Block1.rotateAngleY = 0F;
		Block1.rotateAngleZ = 0F;
		Block1.renderWithRotation(par7);
		
		Block2.rotateAngleX = 0F;
		Block2.rotateAngleY = 0F;
		Block2.rotateAngleZ = 0F;
		Block2.renderWithRotation(par7);
		
		Block3.rotateAngleX = 0F;
		Block3.rotateAngleY = 0F;
		Block3.rotateAngleZ = 0F;
		Block3.renderWithRotation(par7);
		
		Block4.rotateAngleX = 0F;
		Block4.rotateAngleY = 0F;
		Block4.rotateAngleZ = 0F;
		Block4.renderWithRotation(par7);
		
		Block5.rotateAngleX = 0F;
		Block5.rotateAngleY = 0F;
		Block5.rotateAngleZ = 0F;
		Block5.renderWithRotation(par7);
		
		Block6.rotateAngleX = 0F;
		Block6.rotateAngleY = 0F;
		Block6.rotateAngleZ = 0F;
		Block6.renderWithRotation(par7);
		
		Block7.rotateAngleX = 0F;
		Block7.rotateAngleY = 0F;
		Block7.rotateAngleZ = 0F;
		Block7.renderWithRotation(par7);
		
		Block8.rotateAngleX = 0F;
		Block8.rotateAngleY = 0F;
		Block8.rotateAngleZ = 0F;
		Block8.renderWithRotation(par7);
		
		Block9.rotateAngleX = 0F;
		Block9.rotateAngleY = 0F;
		Block9.rotateAngleZ = 0F;
		Block9.renderWithRotation(par7);
		
		head.renderWithRotation(par7);
		
		Block10.rotateAngleX = 4.454685E-09F;
		Block10.rotateAngleY = 0F;
		Block10.rotateAngleZ = 0F;
		Block10.renderWithRotation(par7);
		
		Block11.rotateAngleX = 0F;
		Block11.rotateAngleY = 0F;
		Block11.rotateAngleZ = 0F;
		Block11.renderWithRotation(par7);
		
		Block12.rotateAngleX = 4.454685E-09F;
		Block12.rotateAngleY = 0F;
		Block12.rotateAngleZ = 0F;
		Block12.renderWithRotation(par7);
		
		Block13.rotateAngleX = 4.454685E-09F;
		Block13.rotateAngleY = 0F;
		Block13.rotateAngleZ = 0F;
		Block13.renderWithRotation(par7);
		
		Block14.rotateAngleX = 4.454685E-09F;
		Block14.rotateAngleY = 0F;
		Block14.rotateAngleZ = 0F;
		Block14.renderWithRotation(par7);
		
		Block15.rotateAngleX = 0F;
		Block15.rotateAngleY = 0F;
		Block15.rotateAngleZ = 0F;
		Block15.renderWithRotation(par7);
		
		Block16.rotateAngleX = 4.454685E-09F;
		Block16.rotateAngleY = 0F;
		Block16.rotateAngleZ = 0F;
		Block16.renderWithRotation(par7);
		
		Block17.rotateAngleX = 4.454685E-09F;
		Block17.rotateAngleY = 0F;
		Block17.rotateAngleZ = 0F;
		Block17.renderWithRotation(par7);
		
		Block18.rotateAngleX = 0F;
		Block18.rotateAngleY = 0F;
		Block18.rotateAngleZ = 0F;
		Block18.renderWithRotation(par7);
		
		Block19.rotateAngleX = -0.1745329F;
		Block19.rotateAngleY = 0F;
		Block19.rotateAngleZ = 0F;
		Block19.renderWithRotation(par7);
		
		Block20.rotateAngleX = 0.2617994F;
		Block20.rotateAngleY = -0.7853982F;
		Block20.rotateAngleZ = -0.2617994F;
		Block20.renderWithRotation(par7);
		
		Block21.rotateAngleX = 0.2617994F;
		Block21.rotateAngleY = -0.7853982F;
		Block21.rotateAngleZ = -0.2617994F;
		Block21.renderWithRotation(par7);
		
		browleft.renderWithRotation(par7);
		
		Block22.rotateAngleX = 4.454685E-09F;
		Block22.rotateAngleY = 0F;
		Block22.rotateAngleZ = 0F;
		Block22.renderWithRotation(par7);
		
		Block23.rotateAngleX = 0F;
		Block23.rotateAngleY = 0F;
		Block23.rotateAngleZ = 0F;
		Block23.renderWithRotation(par7);
		
		Block24.rotateAngleX = 0F;
		Block24.rotateAngleY = 0F;
		Block24.rotateAngleZ = 0F;
		Block24.renderWithRotation(par7);
		
		Block25.rotateAngleX = 0F;
		Block25.rotateAngleY = 0F;
		Block25.rotateAngleZ = 0F;
		Block25.renderWithRotation(par7);
		
		Block26.rotateAngleX = 0F;
		Block26.rotateAngleY = 0F;
		Block26.rotateAngleZ = 0F;
		Block26.renderWithRotation(par7);
		
		Block27.rotateAngleX = 0F;
		Block27.rotateAngleY = 0F;
		Block27.rotateAngleZ = 0F;
		Block27.renderWithRotation(par7);
		
		browright.renderWithRotation(par7);
		
		skirt.renderWithRotation(par7);
		
		Block28.rotateAngleX = 0F;
		Block28.rotateAngleY = 0F;
		Block28.rotateAngleZ = 0F;
		Block28.renderWithRotation(par7);
		
		Block29.rotateAngleX = 0F;
		Block29.rotateAngleY = 0F;
		Block29.rotateAngleZ = 0F;
		Block29.renderWithRotation(par7);
		
		Block30.rotateAngleX = 0F;
		Block30.rotateAngleY = 0F;
		Block30.rotateAngleZ = 0F;
		Block30.renderWithRotation(par7);
		
		Block31.rotateAngleX = 0F;
		Block31.rotateAngleY = 0F;
		Block31.rotateAngleZ = 0F;
		Block31.renderWithRotation(par7);
		
		Block32.rotateAngleX = 0F;
		Block32.rotateAngleY = 0F;
		Block32.rotateAngleZ = 0F;
		Block32.renderWithRotation(par7);
		
		Block33.rotateAngleX = 0F;
		Block33.rotateAngleY = 0F;
		Block33.rotateAngleZ = 0F;
		Block33.renderWithRotation(par7);
		
		Block34.rotateAngleX = 0F;
		Block34.rotateAngleY = 0F;
		Block34.rotateAngleZ = 0F;
		Block34.renderWithRotation(par7);
		
		Block35.rotateAngleX = 0F;
		Block35.rotateAngleY = 0F;
		Block35.rotateAngleZ = 0F;
		Block35.renderWithRotation(par7);
		
		Block36.rotateAngleX = 0F;
		Block36.rotateAngleY = 0F;
		Block36.rotateAngleZ = 0F;
		Block36.renderWithRotation(par7);
		
		Block37.rotateAngleX = 0F;
		Block37.rotateAngleY = 0F;
		Block37.rotateAngleZ = 0F;
		Block37.renderWithRotation(par7);
		
		Block38.rotateAngleX = 0F;
		Block38.rotateAngleY = 0F;
		Block38.rotateAngleZ = 0F;
		Block38.renderWithRotation(par7);
		
		Block39.rotateAngleX = 0F;
		Block39.rotateAngleY = 0F;
		Block39.rotateAngleZ = 0F;
		Block39.renderWithRotation(par7);
		
		Block40.rotateAngleX = 0F;
		Block40.rotateAngleY = 0F;
		Block40.rotateAngleZ = 0F;
		Block40.renderWithRotation(par7);
		
		Block41.rotateAngleX = 0F;
		Block41.rotateAngleY = 0F;
		Block41.rotateAngleZ = 0F;
		Block41.renderWithRotation(par7);
		
		Block42.rotateAngleX = 0F;
		Block42.rotateAngleY = 0F;
		Block42.rotateAngleZ = 0F;
		Block42.renderWithRotation(par7);
		
		Block43.rotateAngleX = 0F;
		Block43.rotateAngleY = 0F;
		Block43.rotateAngleZ = 0F;
		Block43.renderWithRotation(par7);
		
		Block44.rotateAngleX = 0F;
		Block44.rotateAngleY = 0F;
		Block44.rotateAngleZ = 0F;
		Block44.renderWithRotation(par7);
		
		Block45.rotateAngleX = 0F;
		Block45.rotateAngleY = 0F;
		Block45.rotateAngleZ = 0F;
		Block45.renderWithRotation(par7);
		
		leftwing.renderWithRotation(par7);
		
		Block46.rotateAngleX = 0F;
		Block46.rotateAngleY = -0.2617994F;
		Block46.rotateAngleZ = 0F;
		Block46.renderWithRotation(par7);
		
		Block47.rotateAngleX = 0F;
		Block47.rotateAngleY = -0.2617994F;
		Block47.rotateAngleZ = 0F;
		Block47.renderWithRotation(par7);
		
		Block48.rotateAngleX = 0F;
		Block48.rotateAngleY = -0.2617994F;
		Block48.rotateAngleZ = 0F;
		Block48.renderWithRotation(par7);
		
		Block49.rotateAngleX = 0F;
		Block49.rotateAngleY = -0.2617994F;
		Block49.rotateAngleZ = 0F;
		Block49.renderWithRotation(par7);
		
		Block50.rotateAngleX = 0F;
		Block50.rotateAngleY = -0.2617994F;
		Block50.rotateAngleZ = 0F;
		Block50.renderWithRotation(par7);
		
		Block51.rotateAngleX = 0F;
		Block51.rotateAngleY = -0.2617994F;
		Block51.rotateAngleZ = 0F;
		Block51.renderWithRotation(par7);
		
		Block52.rotateAngleX = 0F;
		Block52.rotateAngleY = -0.2617994F;
		Block52.rotateAngleZ = 0F;
		Block52.renderWithRotation(par7);
		
		Block53.rotateAngleX = 0F;
		Block53.rotateAngleY = -0.2617994F;
		Block53.rotateAngleZ = 0F;
		Block53.renderWithRotation(par7);
		
		Block54.rotateAngleX = 0F;
		Block54.rotateAngleY = -0.3490658F;
		Block54.rotateAngleZ = 0F;
		Block54.renderWithRotation(par7);
		
		Block55.rotateAngleX = 0F;
		Block55.rotateAngleY = -0.3490658F;
		Block55.rotateAngleZ = 0F;
		Block55.renderWithRotation(par7);
		
		rightwing.renderWithRotation(par7);
		
		Block56.rotateAngleX = 0F;
		Block56.rotateAngleY = 0.2617994F;
		Block56.rotateAngleZ = 0F;
		Block56.renderWithRotation(par7);
		
		Block57.rotateAngleX = 0F;
		Block57.rotateAngleY = 0.2617994F;
		Block57.rotateAngleZ = 0F;
		Block57.renderWithRotation(par7);
		
		Block58.rotateAngleX = 0F;
		Block58.rotateAngleY = 0.2617994F;
		Block58.rotateAngleZ = 0F;
		Block58.renderWithRotation(par7);
		
		Block59.rotateAngleX = 0F;
		Block59.rotateAngleY = 0.2617994F;
		Block59.rotateAngleZ = 0F;
		Block59.renderWithRotation(par7);
		
		Block60.rotateAngleX = 0F;
		Block60.rotateAngleY = 0.2617994F;
		Block60.rotateAngleZ = 0F;
		Block60.renderWithRotation(par7);
		
		Block61.rotateAngleX = 0F;
		Block61.rotateAngleY = 0.2617994F;
		Block61.rotateAngleZ = 0F;
		Block61.renderWithRotation(par7);
		
		Block62.rotateAngleX = 0F;
		Block62.rotateAngleY = 0.2617994F;
		Block62.rotateAngleZ = 0F;
		Block62.renderWithRotation(par7);
		
		Block63.rotateAngleX = 0F;
		Block63.rotateAngleY = 0.2617994F;
		Block63.rotateAngleZ = 0F;
		Block63.renderWithRotation(par7);
		
		Block64.rotateAngleX = 0F;
		Block64.rotateAngleY = 0.3490659F;
		Block64.rotateAngleZ = 0F;
		Block64.renderWithRotation(par7);
		
		Block65.rotateAngleX = 0F;
		Block65.rotateAngleY = 0.3490659F;
		Block65.rotateAngleZ = 0F;
		Block65.renderWithRotation(par7);
		
		waist.renderWithRotation(par7);
		
		Block66.rotateAngleX = 0F;
		Block66.rotateAngleY = 0F;
		Block66.rotateAngleZ = 0F;
		Block66.renderWithRotation(par7);
		
		rightarm.renderWithRotation(par7);
		
		leftarm.renderWithRotation(par7);
		
		
		rightwrist.renderWithRotation(par7);
		
		leftwrist.renderWithRotation(par7);
	}
	
	private void angelAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float scaleFactor, Entity entity) {
		
		if (entity instanceof EntityWeepingAngel) {
			
			head.rotateAngleY = netheadYaw * 0.017453292F;
			head.rotateAngleX = headPitch * 0.017453292F;
			
			rightarm.rotationPointY = 2.5F;
			leftarm.rotationPointY = 2.5F;
			leftarm.rotateAngleX = 0;
			leftarm.rotateAngleY = 0;
			leftarm.rotateAngleZ = 0;
			rightarm.rotateAngleX = 0;
			rightarm.rotateAngleY = 0;
			rightarm.rotateAngleZ = 0;
			EntityWeepingAngel angel = (EntityWeepingAngel) entity;
			
			PoseBase pose = PoseManager.getPoseFromString(angel.getPose());
			
			if (pose != null) {
				//angry_mouth.isHidden = !pose.angryFace();
				pose.setArmAngles(leftarm, rightarm, leftwrist, rightwrist);
				pose.setHeadAngles(head);
				
				if (pose.angryFace()) {
					browright.rotateAngleZ = (float) (20 * Math.PI / 180);
					browleft.rotateAngleZ = (float) (-20 * Math.PI / 180);
				} else {
					browright.rotateAngleZ = (float) (0 * Math.PI / 180);
					browleft.rotateAngleZ = (float) (0 * Math.PI / 180);
				}
				
				if (pose instanceof PoseThinking) {
					browright.rotateAngleZ = 0.15F;
					browright.rotationPointY = -4.5F;
					browleft.rotationPointY = -4.2F;
				}
			}
		} else {
			PoseBase pose = PoseManager.POSE_SHY;
			pose.setArmAngles(leftarm, rightarm, leftwrist, rightwrist);
			pose.setHeadAngles(head);
		}
	}
}
