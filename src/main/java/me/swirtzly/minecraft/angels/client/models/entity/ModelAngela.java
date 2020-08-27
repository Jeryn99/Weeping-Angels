package me.swirtzly.minecraft.angels.client.models.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import me.swirtzly.minecraft.angels.WeepingAngels;
import me.swirtzly.minecraft.angels.client.poses.AngelPoses;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;

/**
 * Angel Type: 4 Created by Swirtzly on 11/03/2020 @ 20:58
 */
public class ModelAngela<T extends WeepingAngelEntity> extends EntityModel<WeepingAngelEntity> implements IAngelModel{
	
	private final ResourceLocation TEXTURE = new ResourceLocation(WeepingAngels.MODID,
			"textures/entities/angel_angela.png");
	
	private final ModelRenderer Head;
	private final ModelRenderer Face;
	private final ModelRenderer Eyes;
	private final ModelRenderer Eyebrows;
	private final ModelRenderer Right;
	private final ModelRenderer Left2;
	private final ModelRenderer Nose;
	private final ModelRenderer Mouth;
	private final ModelRenderer Hair;
	private final ModelRenderer Hair2;
	private final ModelRenderer bone6;
	private final ModelRenderer Body;
	private final ModelRenderer Legs;
	private final ModelRenderer Skirt;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone;
	private final ModelRenderer Front;
	private final ModelRenderer Front3;
	private final ModelRenderer Front4;
	private final ModelRenderer Front2;
	private final ModelRenderer Wings;
	private final ModelRenderer rightarm;
	private final ModelRenderer leftarm;
	private final ModelRenderer LeftWing;
	private final ModelRenderer Left;
	private final ModelRenderer bone13;
	private final ModelRenderer bone5;
	private final ModelRenderer bone7;
	private final ModelRenderer bone11;
	private final ModelRenderer bone12;
	private final ModelRenderer bone10;
	private final ModelRenderer bone9;
	private final ModelRenderer bone8;
	private final ModelRenderer RightWing;
	private final ModelRenderer Right2;
	private final ModelRenderer bone14;
	private final ModelRenderer bone15;
	private final ModelRenderer bone16;
	private final ModelRenderer bone17;
	private final ModelRenderer bone18;
	private final ModelRenderer bone19;
	private final ModelRenderer bone20;
	private final ModelRenderer bone21;

	/**
	 * Angel Type: 4
	 */
	public ModelAngela() {
		textureWidth = 16;
		textureHeight = 16;

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, -1.0F, 0.0F);
		Head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -3.0F, 8.0F, 8.0F, 7.0F, 0.0F, false);
		Head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 3.0F, 1.0F, 0.0F, false);
		Head.setTextureOffset(0, 0).addBox(-4.0F, -1.0F, -4.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
		Head.setTextureOffset(0, 0).addBox(-4.0F, -5.0F, -4.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		Head.setTextureOffset(0, 0).addBox(3.0F, -5.0F, -4.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);

		Face = new ModelRenderer(this);
		Face.setRotationPoint(0.0F, 25.0F, 0.0F);
		Head.addChild(Face);


		Eyes = new ModelRenderer(this);
		Eyes.setRotationPoint(0.0F, 0.0F, 0.0F);
		Face.addChild(Eyes);
		Eyes.setTextureOffset(10, 3).addBox(-3.0F, -29.0F, -4.0F, 6.0F, 3.0F, 1.0F, 0.0F, false);
		Eyes.setTextureOffset(10, 3).addBox(-1.0F, -30.0F, -4.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Eyes.setTextureOffset(10, 3).addBox(-3.0F, -30.0F, -3.55F, 6.0F, 1.0F, 1.0F, 0.0F, false);

		Eyebrows = new ModelRenderer(this);
		Eyebrows.setRotationPoint(0.0F, 0.0F, 0.0F);
		Face.addChild(Eyebrows);


		Right = new ModelRenderer(this);
		Right.setRotationPoint(-5.0F, -1.0F, 0.0F);
		Eyebrows.addChild(Right);
		setRotationAngle(Right, 0.0F, 0.0F, 0.1745F);
		Right.setTextureOffset(0, 0).addBox(1.0F, -30.0F, -4.2F, 2.0F, 0.0F, 1.0F, 0.0F, false);

		Left2 = new ModelRenderer(this);
		Left2.setRotationPoint(6.0F, -0.2F, 0.0F);
		Eyebrows.addChild(Left2);
		setRotationAngle(Left2, 0.0F, 0.0F, -0.1745F);
		Left2.setTextureOffset(0, 0).addBox(-4.0F, -31.0F, -4.2F, 2.0F, 0.0F, 1.0F, 0.0F, false);

		Nose = new ModelRenderer(this);
		Nose.setRotationPoint(0.0F, 0.0F, -3.0F);
		Face.addChild(Nose);
		setRotationAngle(Nose, -0.1745F, 0.0F, 0.0F);
		Nose.setTextureOffset(0, 0).addBox(-0.5F, -29.2904F, -6.1994F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		Mouth = new ModelRenderer(this);
		Mouth.setRotationPoint(0.0F, 0.0F, 0.0F);
		Face.addChild(Mouth);
		Mouth.setTextureOffset(0, 0).addBox(-1.5F, -27.0F, -4.05F, 3.0F, 1.0F, 1.0F, 0.0F, false);

		Hair = new ModelRenderer(this);
		Hair.setRotationPoint(0.0F, 25.0F, 0.0F);
		Head.addChild(Hair);


		Hair2 = new ModelRenderer(this);
		Hair2.setRotationPoint(0.0F, 0.0F, 0.0F);
		Hair.addChild(Hair2);
		Hair2.setTextureOffset(0, 0).addBox(3.2F, -33.5F, -1.7F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.2F, -31.3F, 3.4F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.2F, -33.1F, -3.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.2F, -33.1F, -3.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.1F, -33.1F, -3.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(2.5F, -33.6F, -3.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(2.5F, -33.6F, -1.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(2.5F, -28.2F, 3.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.4F, -32.7F, -0.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.4F, -32.7F, -0.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-3.7F, -33.6F, 0.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-3.7F, -30.2F, 3.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.3F, -32.7F, -1.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.3F, -32.7F, -1.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-3.7F, -33.6F, -0.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-3.7F, -29.2F, 3.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.4F, -33.4F, 2.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.4F, -32.8F, 3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.4F, -33.4F, 2.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.4F, -32.8F, 3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-1.1F, -33.6F, -3.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-1.1F, -33.6F, -1.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-1.1F, -28.2F, 3.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-0.8F, -33.4F, 1.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-0.8F, -31.2F, 3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.4F, -30.8F, 1.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.4F, -30.8F, 1.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-1.8F, -33.6F, 2.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-1.8F, -33.5F, -4.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-1.8F, -32.2F, 3.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.1F, -31.2F, -1.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.1F, -31.2F, -1.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.1F, -28.9F, 0.7F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.1F, -28.9F, 0.7F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(0.1F, -33.9F, 1.1F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(0.1F, -31.1F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.1F, -26.9F, 2.9F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.1F, -26.9F, 2.9F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-0.1F, -33.425F, -0.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-0.1F, -29.2F, 3.475F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.575F, -29.8F, 2.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.575F, -29.8F, 2.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-0.8F, -33.425F, 3.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-0.8F, -33.2F, 3.475F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.275F, -30.8F, -0.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.275F, -30.8F, -0.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.275F, -28.5F, 1.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.275F, -28.5F, 1.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(0.5F, -33.725F, 2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(0.5F, -33.625F, -4.4F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(0.5F, -32.0F, 3.175F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(0.5F, -33.725F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(0.5F, -30.0F, 3.175F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.0F, -33.6F, -3.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.0F, -33.6F, -1.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.0F, -28.2F, 3.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(2.8F, -33.6F, -0.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(2.8F, -29.2F, 3.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.4F, -30.8F, 0.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.4F, -30.8F, 0.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-1.8F, -33.6F, 1.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-1.8F, -31.2F, 3.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.1F, -33.1F, -3.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(1.8F, -33.3F, -3.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(1.4F, -33.175F, -4.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(1.8F, -33.3F, -1.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.7F, -31.8F, -0.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.7F, -31.8F, -0.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-2.8F, -33.3F, 0.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-2.8F, -30.2F, 3.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(2.2F, -33.3F, 0.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(2.2F, -30.2F, 3.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-3.0F, -33.3F, -0.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-3.0F, -29.2F, 3.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.7F, -32.7F, 2.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.7F, -32.7F, 2.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-3.7F, -33.3F, 3.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-3.7F, -33.2F, 3.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.4F, -32.1F, -2.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.4F, -32.1F, -2.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.4F, -29.8F, -0.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.4F, -29.8F, -0.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.4F, -27.8F, 2.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.4F, -27.8F, 2.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(1.2F, -33.6F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(1.2F, -33.0F, 3.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(1.2F, -33.6F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(1.2F, -31.0F, 3.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-3.0F, -33.3F, -3.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-3.0F, -33.3F, -1.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-3.0F, -28.2F, 3.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.7F, -32.7F, 1.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.7F, -32.7F, 1.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-3.7F, -33.3F, 2.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-3.7F, -33.2F, -4.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-3.7F, -32.2F, 3.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.2F, -33.3F, 2.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.2F, -33.2F, -4.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.2F, -32.2F, 3.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.4F, -32.1F, -3.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.4F, -32.1F, -3.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(1.8F, -33.3F, -0.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(1.8F, -29.2F, 3.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.6F, -31.8F, 0.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.6F, -31.8F, 0.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-2.8F, -33.4F, 1.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-2.8F, -31.2F, 3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(0.9F, -33.8F, -3.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(0.9F, -33.8F, -1.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(0.9F, -28.2F, 3.1F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.2F, -28.8F, 1.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-2.1F, -33.65F, -0.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-2.1F, -29.2F, 3.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.35F, -31.8F, 2.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.35F, -31.8F, 2.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-2.8F, -33.65F, 3.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-2.8F, -33.2F, 3.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.05F, -31.2F, -2.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.05F, -31.2F, -2.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.05F, -28.9F, -0.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.05F, -28.9F, -0.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.05F, -26.9F, 2.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.05F, -26.9F, 2.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(2.1F, -33.35F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(2.1F, -33.0F, 3.55F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-2.1F, -33.8F, -3.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-2.1F, -33.8F, -1.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-2.1F, -28.2F, 3.1F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.2F, -31.8F, 1.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.2F, -31.8F, 1.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-2.8F, -33.8F, 2.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-2.8F, -33.7F, -4.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-2.8F, -32.2F, 3.1F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-3.9F, -31.2F, -3.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(0.9F, -33.5F, -0.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(0.9F, -29.2F, 3.4F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.5F, -32.7F, 0.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.5F, -32.7F, 0.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-3.7F, -33.5F, 1.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-3.7F, -31.2F, 3.4F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-0.1F, -33.5F, -3.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-0.1F, -33.5F, -1.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-0.1F, -28.2F, 3.4F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.5F, -29.8F, 1.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.5F, -29.8F, 1.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-0.8F, -33.5F, 2.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-0.8F, -33.4F, -4.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-0.8F, -32.2F, 3.4F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(2.2F, -33.5F, 2.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(2.2F, -33.4F, -4.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(2.2F, -32.2F, 3.4F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-0.8F, -33.5F, 0.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-0.8F, -30.2F, 3.4F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-1.1F, -33.725F, -0.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-1.1F, -29.2F, 3.175F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.275F, -30.8F, 2.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.275F, -30.8F, 2.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-1.8F, -33.725F, 3.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-1.8F, -33.2F, 3.175F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-1.8F, -33.725F, 0.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-1.8F, -30.2F, 3.175F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-3.975F, -30.2F, -2.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.275F, -28.8F, 2.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(3.275F, -28.8F, 2.8F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(0.2F, -33.725F, 3.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(0.2F, -33.2F, 3.175F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-3.975F, -28.2F, -2.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.2F, -33.5F, -1.7F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.2F, -31.3F, 3.4F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-4.2F, -33.5F, -1.7F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-1.5F, -28.334F, 3.5573F, 3.0F, 3.0F, 2.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-1.025F, -29.234F, 2.8573F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		Hair2.setTextureOffset(0, 0).addBox(-2.0F, -27.934F, 2.8573F, 4.0F, 2.0F, 2.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.0F, -2.6F, -10.0F);
		Hair.addChild(bone6);
		setRotationAngle(bone6, -0.7854F, 0.0F, 0.0F);
		bone6.setTextureOffset(0, 0).addBox(-4.5F, -27.734F, -16.2427F, 9.0F, 1.0F, 9.0F, 0.0F, false);

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 24.0F, 0.0F);
		Body.setTextureOffset(0, 1).addBox(-4.0F, -20.0F, -1.875F, 8.0F, 2.0F, 3.0F, 0.0F, false);
		Body.setTextureOffset(0, 1).addBox(-4.0F, -25.0F, -1.5F, 8.0F, 12.0F, 3.0F, 0.0F, false);

		Legs = new ModelRenderer(this);
		Legs.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.addChild(Legs);
		Legs.setTextureOffset(0, 0).addBox(-4.0F, -13.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);
		Legs.setTextureOffset(0, 1).addBox(-4.0F, -23.0F, -2.0F, 8.0F, 3.0F, 3.0F, 0.0F, false);
		Legs.setTextureOffset(0, 1).addBox(-4.0F, -18.0F, -2.0F, 8.0F, 3.0F, 3.0F, 0.0F, false);
		Legs.setTextureOffset(0, 0).addBox(-4.0F, -25.0F, -2.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
		Legs.setTextureOffset(0, 0).addBox(-3.0F, -15.0F, -2.0F, 6.0F, 2.0F, 3.0F, 0.0F, false);
		Legs.setTextureOffset(0, 0).addBox(2.0F, -25.0F, -2.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
		Legs.setTextureOffset(0, 0).addBox(-5.0F, -1.806F, -3.0055F, 10.0F, 2.0F, 6.0F, 0.0F, false);
		Legs.setTextureOffset(0, 0).addBox(-5.8716F, -0.806F, -2.0F, 11.0F, 1.0F, 4.0F, 0.0F, false);
		Legs.setTextureOffset(0, 0).addBox(-4.0F, -0.806F, -3.8716F, 8.0F, 1.0F, 7.0F, 0.0F, false);

		Skirt = new ModelRenderer(this);
		Skirt.setRotationPoint(0.0F, -15.0F, 0.0F);
		Legs.addChild(Skirt);


		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(-4.0F, 15.0F, 0.0F);
		Skirt.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.0F, 0.5236F);
		bone2.setTextureOffset(0, 0).addBox(-2.2738F, -2.1953F, -2.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(-4.0F, 15.0F, 0.0F);
		Skirt.addChild(bone3);
		setRotationAngle(bone3, 0.0F, 0.0F, -0.5236F);
		bone3.setTextureOffset(0, 0).addBox(7.202F, 1.8047F, -2.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(10.0F, 15.0F, 0.0F);
		Skirt.addChild(bone4);
		setRotationAngle(bone4, 0.0F, 0.0F, -0.0873F);
		bone4.setTextureOffset(0, 0).addBox(-8.8441F, -13.4735F, -2.0F, 4.0F, 11.0F, 4.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 15.0F, 0.0F);
		Skirt.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.0F, 0.0873F);
		bone.setTextureOffset(0, 0).addBox(-5.1178F, -12.6019F, -2.0F, 4.0F, 11.0F, 4.0F, 0.0F, false);

		Front = new ModelRenderer(this);
		Front.setRotationPoint(0.0F, 15.0F, 0.0F);
		Skirt.addChild(Front);
		setRotationAngle(Front, -0.0873F, 0.0F, 0.0F);
		Front.setTextureOffset(0, 0).addBox(-4.0F, -14.7762F, -3.1254F, 8.0F, 13.0F, 3.0F, 0.0F, false);

		Front3 = new ModelRenderer(this);
		Front3.setRotationPoint(0.0F, 15.0F, 0.0F);
		Skirt.addChild(Front3);
		setRotationAngle(Front3, -0.5236F, 0.0F, 0.0F);
		Front3.setTextureOffset(0, 0).addBox(-4.0F, -1.1953F, -4.0059F, 8.0F, 2.0F, 2.0F, 0.0F, false);

		Front4 = new ModelRenderer(this);
		Front4.setRotationPoint(0.0F, 15.0F, 0.0F);
		Skirt.addChild(Front4);
		setRotationAngle(Front4, 0.5236F, 0.0F, 0.0F);
		Front4.setTextureOffset(0, 0).addBox(-4.0F, -1.1953F, 2.0059F, 8.0F, 2.0F, 2.0F, 0.0F, false);

		Front2 = new ModelRenderer(this);
		Front2.setRotationPoint(0.0F, 15.0F, 0.0F);
		Skirt.addChild(Front2);
		setRotationAngle(Front2, 0.0873F, 0.0F, 0.0F);
		Front2.setTextureOffset(0, 1).addBox(-4.0F, -12.7762F, -0.8746F, 8.0F, 11.0F, 4.0F, 0.0F, true);

		Wings = new ModelRenderer(this);
		Wings.setRotationPoint(0.0F, -1.0F, 0.0F);
		Body.addChild(Wings);
		Wings.setTextureOffset(0, 0).addBox(0.5F, -23.0F, 2.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		Wings.setTextureOffset(0, 0).addBox(-1.5F, -23.0F, 2.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		rightarm = new ModelRenderer(this);
		rightarm.setRotationPoint(-5.5F, -1.0F, 0.0F);
		rightarm.setTextureOffset(0, 0).addBox(-1.5F, 0.0F, -1.3F, 3.0F, 12.0F, 3.0F, 0.0F, false);

		leftarm = new ModelRenderer(this);
		leftarm.setRotationPoint(5.5F, -1.0F, 0.0F);
		leftarm.setTextureOffset(2, 0).addBox(-1.5F, 0.0F, -1.3F, 3.0F, 12.0F, 3.0F, 0.0F, false);

		LeftWing = new ModelRenderer(this);
		LeftWing.setRotationPoint(1.0F, 1.0F, 3.0F);


		Left = new ModelRenderer(this);
		Left.setRotationPoint(-6.0F, 22.3F, -2.8F);
		LeftWing.addChild(Left);
		setRotationAngle(Left, 0.0F, 1.0472F, 0.0F);


		bone13 = new ModelRenderer(this);
		bone13.setRotationPoint(0.0F, 0.0F, 0.0F);
		Left.addChild(bone13);
		setRotationAngle(bone13, 0.0873F, 0.0F, 0.0F);
		bone13.setTextureOffset(0, 0).addBox(0.5F, -21.8171F, 8.2218F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		bone13.setTextureOffset(0, 0).addBox(0.5F, -27.2595F, 21.2465F, 1.0F, 23.0F, 1.0F, 0.0F, false);
		bone13.setTextureOffset(0, 0).addBox(0.4768F, -27.2281F, 20.6049F, 1.0F, 22.0F, 1.0F, 0.0F, false);
		bone13.setTextureOffset(0, 0).addBox(0.5F, -29.2595F, 20.2465F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone13.setTextureOffset(0, 0).addBox(0.5917F, -29.254F, 20.3087F, 0.0F, 20.0F, 1.0F, 0.0F, false);
		bone13.setTextureOffset(6, 1).addBox(0.5917F, -29.254F, 19.3087F, 0.0F, 3.0F, 1.0F, 0.0F, false);
		bone13.setTextureOffset(0, 0).addBox(0.7786F, -30.2383F, 18.4888F, 0.0F, 15.0F, 1.0F, 0.0F, false);
		bone13.setTextureOffset(0, 0).addBox(0.7786F, -23.2345F, 14.576F, 0.0F, 5.0F, 1.0F, 0.0F, false);
		bone13.setTextureOffset(0, 0).addBox(0.7786F, -22.2345F, 12.576F, 0.0F, 6.0F, 1.0F, 0.0F, false);
		bone13.setTextureOffset(0, 0).addBox(0.7853F, -22.2247F, 9.6872F, 0.0F, 7.0F, 1.0F, 0.0F, false);
		bone13.setTextureOffset(0, 0).addBox(0.7786F, -22.2345F, 13.576F, 0.0F, 5.0F, 1.0F, 0.0F, false);
		bone13.setTextureOffset(12, 9).addBox(0.7786F, -30.2383F, 17.4888F, 0.0F, 4.0F, 1.0F, 0.0F, false);
		bone13.setTextureOffset(14, 11).addBox(0.7853F, -29.2323F, 16.5129F, 0.0F, 4.0F, 1.0F, 0.0F, false);
		bone13.setTextureOffset(0, 0).addBox(0.8795F, -30.2358F, 17.5171F, 0.0F, 14.0F, 1.0F, 0.0F, false);
		bone13.setTextureOffset(0, 0).addBox(0.8795F, -30.2358F, 16.5171F, 0.0F, 13.0F, 1.0F, 0.0F, false);
		bone13.setTextureOffset(0, 0).addBox(0.8795F, -29.2358F, 15.5171F, 0.0F, 11.0F, 1.0F, 0.0F, false);
		bone13.setTextureOffset(0, 0).addBox(0.8844F, -22.2272F, 11.6589F, 0.0F, 7.0F, 1.0F, 0.0F, false);
		bone13.setTextureOffset(0, 0).addBox(0.8752F, -21.9254F, 10.6668F, 0.0F, 7.0F, 1.0F, 0.0F, false);
		bone13.setTextureOffset(0, 0).addBox(0.8661F, -21.9224F, 8.7008F, 0.0F, 6.0F, 1.0F, 0.0F, false);
		bone13.setTextureOffset(0, 0).addBox(0.6835F, -30.2486F, 19.371F, 0.0F, 17.0F, 1.0F, 0.0F, false);
		bone13.setTextureOffset(0, 0).addBox(0.6835F, -29.9459F, 18.432F, 0.0F, 3.0F, 1.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.0F, 0.0F, 12.0F);
		Left.addChild(bone5);
		setRotationAngle(bone5, 0.4363F, 0.0F, 0.0F);
		bone5.setTextureOffset(0, 0).addBox(0.5F, -21.3659F, 7.9682F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		bone5.setTextureOffset(0, 0).addBox(0.5F, -28.599F, 15.0688F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		bone5.setTextureOffset(0, 0).addBox(0.5F, -25.0782F, 17.3524F, 1.0F, 2.0F, 2.0F, 0.0F, false);

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(0.0F, 0.0F, 9.0F);
		Left.addChild(bone7);
		setRotationAngle(bone7, 0.8727F, 0.0F, 0.0F);
		bone7.setTextureOffset(0, 0).addBox(0.5F, -12.8002F, 19.8842F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		bone7.setTextureOffset(0, 0).addBox(0.5F, -14.2517F, 28.0661F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		bone7.setTextureOffset(0, 0).addBox(0.5F, -17.253F, 26.6718F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(0.0F, -23.0F, 8.0F);
		Left.addChild(bone11);
		setRotationAngle(bone11, 1.309F, 0.0F, 0.0F);
		bone11.setTextureOffset(0, 0).addBox(0.5F, 4.7088F, 9.3147F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		bone11.setTextureOffset(0, 0).addBox(0.5F, 4.6347F, 3.2105F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		bone11.setTextureOffset(0, 0).addBox(0.5F, 2.5542F, 7.5068F, 1.0F, 2.0F, 2.0F, 0.0F, false);

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(0.0F, -25.0F, 13.0F);
		Left.addChild(bone12);
		setRotationAngle(bone12, 1.7453F, 0.0F, 0.0F);
		bone12.setTextureOffset(0, 0).addBox(0.5F, 0.2161F, 1.6227F, 1.0F, 2.0F, 3.0F, 0.0F, false);

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(0.0F, 0.0F, 0.0F);
		Left.addChild(bone10);
		bone10.setTextureOffset(0, 0).addBox(0.5F, -32.7105F, 14.4768F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(0.0F, 0.0F, 0.0F);
		Left.addChild(bone9);


		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(0.0F, 0.0F, 0.0F);
		Left.addChild(bone8);


		RightWing = new ModelRenderer(this);
		RightWing.setRotationPoint(-1.0F, 1.0F, 3.0F);


		Right2 = new ModelRenderer(this);
		Right2.setRotationPoint(5.0F, 22.3F, -4.5F);
		RightWing.addChild(Right2);
		setRotationAngle(Right2, 0.0F, -1.0472F, 0.0F);


		bone14 = new ModelRenderer(this);
		bone14.setRotationPoint(-0.866F, 0.0F, -0.5F);
		Right2.addChild(bone14);
		setRotationAngle(bone14, 0.0873F, 0.0F, 0.0F);
		bone14.setTextureOffset(0, 0).addBox(1.366F, -21.8171F, 8.7218F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		bone14.setTextureOffset(0, 0).addBox(1.366F, -27.2595F, 21.7465F, 1.0F, 23.0F, 1.0F, 0.0F, false);
		bone14.setTextureOffset(0, 0).addBox(1.3609F, -27.2195F, 21.2036F, 1.0F, 22.0F, 1.0F, 0.0F, false);
		bone14.setTextureOffset(0, 0).addBox(1.366F, -29.2595F, 20.7465F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone14.setTextureOffset(0, 0).addBox(1.4578F, -29.254F, 20.8087F, 0.0F, 20.0F, 1.0F, 0.0F, false);
		bone14.setTextureOffset(0, 0).addBox(1.4578F, -29.254F, 19.8087F, 0.0F, 3.0F, 1.0F, 0.0F, false);
		bone14.setTextureOffset(0, 0).addBox(1.6446F, -30.2383F, 18.9888F, 0.0F, 15.0F, 1.0F, 0.0F, false);
		bone14.setTextureOffset(0, 0).addBox(1.6446F, -23.2345F, 15.076F, 0.0F, 5.0F, 1.0F, 0.0F, false);
		bone14.setTextureOffset(0, 0).addBox(1.6446F, -22.2345F, 13.076F, 0.0F, 6.0F, 1.0F, 0.0F, false);
		bone14.setTextureOffset(0, 0).addBox(1.6513F, -22.2247F, 10.1872F, 0.0F, 7.0F, 1.0F, 0.0F, false);
		bone14.setTextureOffset(0, 0).addBox(1.6446F, -22.2345F, 14.076F, 0.0F, 5.0F, 1.0F, 0.0F, false);
		bone14.setTextureOffset(0, 0).addBox(1.6446F, -30.2383F, 17.9888F, 0.0F, 4.0F, 1.0F, 0.0F, false);
		bone14.setTextureOffset(0, 0).addBox(1.6513F, -29.2323F, 17.0129F, 0.0F, 4.0F, 1.0F, 0.0F, false);
		bone14.setTextureOffset(0, 0).addBox(1.7455F, -30.2358F, 18.0171F, 0.0F, 14.0F, 1.0F, 0.0F, false);
		bone14.setTextureOffset(0, 0).addBox(1.7455F, -30.2358F, 17.0171F, 0.0F, 13.0F, 1.0F, 0.0F, false);
		bone14.setTextureOffset(0, 0).addBox(1.7455F, -29.2358F, 16.0171F, 0.0F, 11.0F, 1.0F, 0.0F, false);
		bone14.setTextureOffset(0, 0).addBox(1.7504F, -22.2272F, 12.1589F, 0.0F, 7.0F, 1.0F, 0.0F, false);
		bone14.setTextureOffset(0, 0).addBox(1.7413F, -21.9254F, 11.1668F, 0.0F, 7.0F, 1.0F, 0.0F, false);
		bone14.setTextureOffset(0, 0).addBox(1.7321F, -21.9224F, 9.2008F, 0.0F, 6.0F, 1.0F, 0.0F, false);
		bone14.setTextureOffset(0, 0).addBox(1.5495F, -30.2486F, 19.871F, 0.0F, 17.0F, 1.0F, 0.0F, false);
		bone14.setTextureOffset(0, 0).addBox(1.5495F, -29.9459F, 18.932F, 0.0F, 3.0F, 1.0F, 0.0F, false);

		bone15 = new ModelRenderer(this);
		bone15.setRotationPoint(0.0F, 0.0F, 12.0F);
		Right2.addChild(bone15);
		setRotationAngle(bone15, 0.4363F, 0.0F, 0.0F);
		bone15.setTextureOffset(0, 0).addBox(0.5F, -21.3659F, 7.9682F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		bone15.setTextureOffset(0, 0).addBox(0.5F, -28.599F, 15.0688F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		bone15.setTextureOffset(0, 0).addBox(0.5F, -25.0782F, 17.3524F, 1.0F, 2.0F, 2.0F, 0.0F, false);

		bone16 = new ModelRenderer(this);
		bone16.setRotationPoint(0.0F, 0.0F, 9.0F);
		Right2.addChild(bone16);
		setRotationAngle(bone16, 0.8727F, 0.0F, 0.0F);
		bone16.setTextureOffset(0, 0).addBox(0.5F, -12.8002F, 19.8842F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		bone16.setTextureOffset(0, 0).addBox(0.5F, -14.2517F, 28.0661F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		bone16.setTextureOffset(0, 0).addBox(0.5F, -17.253F, 26.6718F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		bone17 = new ModelRenderer(this);
		bone17.setRotationPoint(0.0F, -23.0F, 8.0F);
		Right2.addChild(bone17);
		setRotationAngle(bone17, 1.309F, 0.0F, 0.0F);
		bone17.setTextureOffset(0, 0).addBox(0.5F, 4.7088F, 9.3147F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		bone17.setTextureOffset(0, 0).addBox(0.5F, 4.6347F, 3.2105F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		bone17.setTextureOffset(0, 0).addBox(0.5F, 2.5542F, 7.5068F, 1.0F, 2.0F, 2.0F, 0.0F, false);

		bone18 = new ModelRenderer(this);
		bone18.setRotationPoint(0.0F, -25.0F, 13.0F);
		Right2.addChild(bone18);
		setRotationAngle(bone18, 1.7453F, 0.0F, 0.0F);
		bone18.setTextureOffset(0, 0).addBox(0.5F, 0.2161F, 1.6227F, 1.0F, 2.0F, 3.0F, 0.0F, false);

		bone19 = new ModelRenderer(this);
		bone19.setRotationPoint(0.0F, 0.0F, 0.0F);
		Right2.addChild(bone19);
		bone19.setTextureOffset(0, 0).addBox(0.5F, -32.7105F, 14.4768F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		bone20 = new ModelRenderer(this);
		bone20.setRotationPoint(0.0F, 0.0F, 0.0F);
		Right2.addChild(bone20);


		bone21 = new ModelRenderer(this);
		bone21.setRotationPoint(0.0F, 0.0F, 0.0F);
		Right2.addChild(bone21);

	}

	@Override
	public void setRotationAngles(WeepingAngelEntity weepingAngelEntity, float v, float v1, float v2, float v3, float v4) {
		AngelPoses pose = AngelPoses.getPoseFromString(weepingAngelEntity.getAngelPose());

		if(pose == AngelPoses.POSE_ANGRY_TWO) {
			rightarm.rotateAngleX = (float) Math.toRadians(-115);
			rightarm.rotateAngleY = (float) Math.toRadians(0);
			rightarm.rotateAngleZ = (float) Math.toRadians(0);

			leftarm.rotateAngleX = (float) Math.toRadians(-55);
			leftarm.rotateAngleY = (float) Math.toRadians(0);
			leftarm.rotateAngleZ = (float) Math.toRadians(0);

			Head.rotateAngleX = (float) Math.toRadians(17.5);
			Head.rotateAngleY = (float) Math.toRadians(0);
			Head.rotateAngleZ = (float) Math.toRadians(-10);
			return;
		}


		if (pose.create().isAngry()) {
			rightarm.rotateAngleX = (float) Math.toRadians(-90);
			rightarm.rotateAngleY = (float) Math.toRadians(-20);
			rightarm.rotateAngleZ = (float) Math.toRadians(30);

			leftarm.rotateAngleX = (float) Math.toRadians(-90);
			leftarm.rotateAngleY = (float) Math.toRadians(25);
			leftarm.rotateAngleZ = (float) Math.toRadians(-17.5);

			Head.rotateAngleX = (float) Math.toRadians(0);
			Head.rotateAngleY = (float) Math.toRadians(-12.5);
			Head.rotateAngleZ = (float) Math.toRadians(0);
			return;
		}

		if (pose == AngelPoses.POSE_HIDING_FACE) {
			Head.rotateAngleX = (float) Math.toRadians(20);
			Head.rotateAngleY = (float) Math.toRadians(0);
			Head.rotateAngleZ = (float) Math.toRadians(0);

			rightarm.rotateAngleX = (float) Math.toRadians(-105);
			rightarm.rotateAngleY = (float) Math.toRadians(20);
			rightarm.rotateAngleZ = (float) Math.toRadians(12.5);

			leftarm.rotateAngleX = (float) Math.toRadians(-105);
			leftarm.rotateAngleY = (float) Math.toRadians(-20);
			leftarm.rotateAngleZ = (float) Math.toRadians(-12.5);
			return;
		}

		if (pose == AngelPoses.POSE_IDLE) {
			Head.rotateAngleX = (float) Math.toRadians(0);
			Head.rotateAngleY = (float) Math.toRadians(0);
			Head.rotateAngleZ = (float) Math.toRadians(0);

			rightarm.rotateAngleX = (float) Math.toRadians(0);
			rightarm.rotateAngleY = (float) Math.toRadians(0);
			rightarm.rotateAngleZ = (float) Math.toRadians(7.5);

			leftarm.rotateAngleX = (float) Math.toRadians(0);
			leftarm.rotateAngleY = (float) Math.toRadians(0);
			leftarm.rotateAngleZ = (float) Math.toRadians(-7.5);
			return;
		}

		if (pose == AngelPoses.POSE_SHY) {
			rightarm.rotateAngleX = (float) Math.toRadians(-90);
			rightarm.rotateAngleY = (float) Math.toRadians(-1.5);
			rightarm.rotateAngleZ = (float) Math.toRadians(-20);

			leftarm.rotateAngleX = (float) Math.toRadians(-120);
			leftarm.rotateAngleY = (float) Math.toRadians(-36);
			leftarm.rotateAngleZ = (float) Math.toRadians(10);

			Head.rotateAngleX = (float) Math.toRadians(20);
			Head.rotateAngleY = (float) Math.toRadians(-40);
			Head.rotateAngleZ = (float) Math.toRadians(-20);
			return;
		}
	}

	public void setRotationAngle(ModelRenderer ModelRenderer, float x, float y, float z) {
		ModelRenderer.rotateAngleX = x;
		ModelRenderer.rotateAngleY = y;
		ModelRenderer.rotateAngleZ = z;
	}

	@Override
	public ResourceLocation getTextureForPose(WeepingAngelEntity angel) {
		return TEXTURE;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		Head.render(matrixStack, buffer, packedLight, packedOverlay);
		Body.render(matrixStack, buffer, packedLight, packedOverlay);
		rightarm.render(matrixStack, buffer, packedLight, packedOverlay);
		leftarm.render(matrixStack, buffer, packedLight, packedOverlay);
		LeftWing.render(matrixStack, buffer, packedLight, packedOverlay);
		RightWing.render(matrixStack, buffer, packedLight, packedOverlay);
	}

}
