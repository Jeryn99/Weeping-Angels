package me.swirtzly.minecraft.angels.client.models.entity;

import me.swirtzly.minecraft.angels.client.poses.AngelPoses;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

/**
 * Angel Type: 4
 * Created by Swirtzly on 11/03/2020 @ 20:58
 */
public class ModelAngela <T extends LivingEntity> extends EntityModel<T> {
	private final RendererModel Head;
	private final RendererModel Face;
	private final RendererModel Eyes;
	private final RendererModel Eyebrows;
	private final RendererModel Right;
	private final RendererModel Left2;
	private final RendererModel Nose;
	private final RendererModel Mouth;
	private final RendererModel Hair;
	private final RendererModel Hair2;
	private final RendererModel bone6;
	private final RendererModel Body;
	private final RendererModel Legs;
	private final RendererModel Skirt;
	private final RendererModel bone2;
	private final RendererModel bone3;
	private final RendererModel bone4;
	private final RendererModel bone;
	private final RendererModel Front;
	private final RendererModel Front3;
	private final RendererModel Front4;
	private final RendererModel Front2;
	private final RendererModel Wings;
	private final RendererModel rightarm;
	private final RendererModel leftarm;
	private final RendererModel LeftWing;
	private final RendererModel Left;
	private final RendererModel bone13;
	private final RendererModel bone5;
	private final RendererModel bone7;
	private final RendererModel bone11;
	private final RendererModel bone12;
	private final RendererModel bone10;
	private final RendererModel bone9;
	private final RendererModel bone8;
	private final RendererModel RightWing;
	private final RendererModel Right2;
	private final RendererModel bone14;
	private final RendererModel bone15;
	private final RendererModel bone16;
	private final RendererModel bone17;
	private final RendererModel bone18;
	private final RendererModel bone19;
	private final RendererModel bone20;
	private final RendererModel bone21;
	/**
	 * Angel Type: 4
	 */
	public ModelAngela() {
		textureWidth = 16;
		textureHeight = 16;
		
		Head = new RendererModel(this);
		Head.setRotationPoint(0.0F, -1.0F, 0.0F);
		Head.cubeList.add(new ModelBox(Head, 0, 0, -4.0F, -8.0F, -3.0F, 8, 8, 7, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 0, 0, -4.0F, -8.0F, -4.0F, 8, 3, 1, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 0, 0, -4.0F, -1.0F, -4.0F, 8, 1, 1, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 0, 0, -4.0F, -5.0F, -4.0F, 1, 4, 1, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 0, 0, 3.0F, -5.0F, -4.0F, 1, 4, 1, 0.0F, false));
		
		Face = new RendererModel(this);
		Face.setRotationPoint(0.0F, 25.0F, 0.0F);
		Head.addChild(Face);
		
		Eyes = new RendererModel(this);
		Eyes.setRotationPoint(0.0F, 0.0F, 0.0F);
		Face.addChild(Eyes);
		Eyes.cubeList.add(new ModelBox(Eyes, 10, 3, -3.0F, -29.0F, -4.0F, 6, 3, 1, 0.0F, false));
		Eyes.cubeList.add(new ModelBox(Eyes, 10, 3, -1.0F, -30.0F, -4.0F, 2, 1, 1, 0.0F, false));
		Eyes.cubeList.add(new ModelBox(Eyes, 10, 3, -3.0F, -30.0F, -3.55F, 6, 1, 1, 0.0F, false));
		
		Eyebrows = new RendererModel(this);
		Eyebrows.setRotationPoint(0.0F, 0.0F, 0.0F);
		Face.addChild(Eyebrows);
		
		Right = new RendererModel(this);
		Right.setRotationPoint(-5.0F, -1.0F, 0.0F);
		setRotationAngle(Right, 0.0F, 0.0F, 0.1745F);
		Eyebrows.addChild(Right);
		Right.cubeList.add(new ModelBox(Right, 0, 0, 1.0F, -30.0F, -4.2F, 2, 0, 1, 0.0F, false));
		
		Left2 = new RendererModel(this);
		Left2.setRotationPoint(6.0F, -0.2F, 0.0F);
		setRotationAngle(Left2, 0.0F, 0.0F, -0.1745F);
		Eyebrows.addChild(Left2);
		Left2.cubeList.add(new ModelBox(Left2, 0, 0, -4.0F, -31.0F, -4.2F, 2, 0, 1, 0.0F, false));
		
		Nose = new RendererModel(this);
		Nose.setRotationPoint(0.0F, 0.0F, -3.0F);
		setRotationAngle(Nose, -0.1745F, 0.0F, 0.0F);
		Face.addChild(Nose);
		Nose.cubeList.add(new ModelBox(Nose, 0, 0, -0.5F, -29.2904F, -6.1994F, 1, 2, 1, 0.0F, false));
		
		Mouth = new RendererModel(this);
		Mouth.setRotationPoint(0.0F, 0.0F, 0.0F);
		Face.addChild(Mouth);
		Mouth.cubeList.add(new ModelBox(Mouth, 0, 0, -1.5F, -27.0F, -4.05F, 3, 1, 1, 0.0F, false));
		
		Hair = new RendererModel(this);
		Hair.setRotationPoint(0.0F, 25.0F, 0.0F);
		Head.addChild(Hair);
		
		Hair2 = new RendererModel(this);
		Hair2.setRotationPoint(0.0F, 0.0F, 0.0F);
		Hair.addChild(Hair2);
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.2F, -33.5F, -1.7F, 1, 1, 4, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.2F, -31.3F, 3.4F, 1, 4, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.2F, -33.1F, -3.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.2F, -33.1F, -3.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.1F, -33.1F, -3.6F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 2.5F, -33.6F, -3.6F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 2.5F, -33.6F, -1.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 2.5F, -28.2F, 3.3F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.4F, -32.7F, -0.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.4F, -32.7F, -0.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -3.7F, -33.6F, 0.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -3.7F, -30.2F, 3.3F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.3F, -32.7F, -1.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.3F, -32.7F, -1.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -3.7F, -33.6F, -0.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -3.7F, -29.2F, 3.3F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.4F, -33.4F, 2.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.4F, -32.8F, 3.5F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.4F, -33.4F, 2.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.4F, -32.8F, 3.5F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -1.1F, -33.6F, -3.6F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -1.1F, -33.6F, -1.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -1.1F, -28.2F, 3.3F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -0.8F, -33.4F, 1.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -0.8F, -31.2F, 3.5F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.4F, -30.8F, 1.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.4F, -30.8F, 1.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -1.8F, -33.6F, 2.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -1.8F, -33.5F, -4.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -1.8F, -32.2F, 3.3F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.1F, -31.2F, -1.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.1F, -31.2F, -1.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.1F, -28.9F, 0.7F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.1F, -28.9F, 0.7F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 0.1F, -33.9F, 1.1F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 0.1F, -31.1F, 3.0F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.1F, -26.9F, 2.9F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.1F, -26.9F, 2.9F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -0.1F, -33.425F, -0.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -0.1F, -29.2F, 3.475F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.575F, -29.8F, 2.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.575F, -29.8F, 2.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -0.8F, -33.425F, 3.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -0.8F, -33.2F, 3.475F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.275F, -30.8F, -0.3F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.275F, -30.8F, -0.3F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.275F, -28.5F, 1.6F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.275F, -28.5F, 1.6F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 0.5F, -33.725F, 2.0F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 0.5F, -33.625F, -4.4F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 0.5F, -32.0F, 3.175F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 0.5F, -33.725F, 0.0F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 0.5F, -30.0F, 3.175F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.0F, -33.6F, -3.6F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.0F, -33.6F, -1.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.0F, -28.2F, 3.3F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 2.8F, -33.6F, -0.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 2.8F, -29.2F, 3.3F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.4F, -30.8F, 0.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.4F, -30.8F, 0.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -1.8F, -33.6F, 1.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -1.8F, -31.2F, 3.3F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.1F, -33.1F, -3.6F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 1.8F, -33.3F, -3.6F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 1.4F, -33.175F, -4.3F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 1.8F, -33.3F, -1.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.7F, -31.8F, -0.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.7F, -31.8F, -0.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -2.8F, -33.3F, 0.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -2.8F, -30.2F, 3.6F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 2.2F, -33.3F, 0.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 2.2F, -30.2F, 3.6F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -3.0F, -33.3F, -0.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -3.0F, -29.2F, 3.6F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.7F, -32.7F, 2.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.7F, -32.7F, 2.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -3.7F, -33.3F, 3.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -3.7F, -33.2F, 3.6F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.4F, -32.1F, -2.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.4F, -32.1F, -2.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.4F, -29.8F, -0.3F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.4F, -29.8F, -0.3F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.4F, -27.8F, 2.6F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.4F, -27.8F, 2.6F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 1.2F, -33.6F, 3.0F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 1.2F, -33.0F, 3.3F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 1.2F, -33.6F, 1.0F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 1.2F, -31.0F, 3.3F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -3.0F, -33.3F, -3.6F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -3.0F, -33.3F, -1.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -3.0F, -28.2F, 3.6F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.7F, -32.7F, 1.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.7F, -32.7F, 1.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -3.7F, -33.3F, 2.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -3.7F, -33.2F, -4.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -3.7F, -32.2F, 3.6F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.2F, -33.3F, 2.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.2F, -33.2F, -4.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.2F, -32.2F, 3.6F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.4F, -32.1F, -3.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.4F, -32.1F, -3.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 1.8F, -33.3F, -0.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 1.8F, -29.2F, 3.6F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.6F, -31.8F, 0.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.6F, -31.8F, 0.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -2.8F, -33.4F, 1.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -2.8F, -31.2F, 3.5F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 0.9F, -33.8F, -3.6F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 0.9F, -33.8F, -1.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 0.9F, -28.2F, 3.1F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.2F, -28.8F, 1.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -2.1F, -33.65F, -0.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -2.1F, -29.2F, 3.25F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.35F, -31.8F, 2.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.35F, -31.8F, 2.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -2.8F, -33.65F, 3.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -2.8F, -33.2F, 3.25F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.05F, -31.2F, -2.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.05F, -31.2F, -2.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.05F, -28.9F, -0.3F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.05F, -28.9F, -0.3F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.05F, -26.9F, 2.6F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.05F, -26.9F, 2.6F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 2.1F, -33.35F, 3.0F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 2.1F, -33.0F, 3.55F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -2.1F, -33.8F, -3.6F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -2.1F, -33.8F, -1.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -2.1F, -28.2F, 3.1F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.2F, -31.8F, 1.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.2F, -31.8F, 1.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -2.8F, -33.8F, 2.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -2.8F, -33.7F, -4.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -2.8F, -32.2F, 3.1F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -3.9F, -31.2F, -3.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 0.9F, -33.5F, -0.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 0.9F, -29.2F, 3.4F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.5F, -32.7F, 0.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.5F, -32.7F, 0.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -3.7F, -33.5F, 1.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -3.7F, -31.2F, 3.4F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -0.1F, -33.5F, -3.6F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -0.1F, -33.5F, -1.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -0.1F, -28.2F, 3.4F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.5F, -29.8F, 1.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.5F, -29.8F, 1.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -0.8F, -33.5F, 2.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -0.8F, -33.4F, -4.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -0.8F, -32.2F, 3.4F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 2.2F, -33.5F, 2.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 2.2F, -33.4F, -4.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 2.2F, -32.2F, 3.4F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -0.8F, -33.5F, 0.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -0.8F, -30.2F, 3.4F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -1.1F, -33.725F, -0.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -1.1F, -29.2F, 3.175F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.275F, -30.8F, 2.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.275F, -30.8F, 2.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -1.8F, -33.725F, 3.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -1.8F, -33.2F, 3.175F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -1.8F, -33.725F, 0.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -1.8F, -30.2F, 3.175F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -3.975F, -30.2F, -2.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.275F, -28.8F, 2.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 3.275F, -28.8F, 2.8F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 0.2F, -33.725F, 3.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, 0.2F, -33.2F, 3.175F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -3.975F, -28.2F, -2.2F, 1, 1, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.2F, -33.5F, -1.7F, 1, 1, 4, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.2F, -31.3F, 3.4F, 1, 4, 1, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -4.2F, -33.5F, -1.7F, 1, 1, 4, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -1.5F, -28.334F, 3.5573F, 3, 3, 2, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -1.025F, -29.234F, 2.8573F, 2, 4, 2, 0.0F, false));
		Hair2.cubeList.add(new ModelBox(Hair2, 0, 0, -2.0F, -27.934F, 2.8573F, 4, 2, 2, 0.0F, false));
		
		bone6 = new RendererModel(this);
		bone6.setRotationPoint(0.0F, -2.6F, -10.0F);
		setRotationAngle(bone6, -0.7854F, 0.0F, 0.0F);
		Hair.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 0, 0, -4.5F, -27.734F, -16.2427F, 9, 1, 9, 0.0F, false));
		
		Body = new RendererModel(this);
		Body.setRotationPoint(0.0F, 24.0F, 0.0F);
		Body.cubeList.add(new ModelBox(Body, 0, 1, -4.0F, -20.0F, -1.875F, 8, 2, 3, 0.0F, false));
		Body.cubeList.add(new ModelBox(Body, 0, 1, -4.0F, -25.0F, -1.5F, 8, 12, 3, 0.0F, false));
		
		Legs = new RendererModel(this);
		Legs.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.addChild(Legs);
		Legs.cubeList.add(new ModelBox(Legs, 0, 0, -4.0F, -13.0F, -2.0F, 8, 12, 4, 0.0F, false));
		Legs.cubeList.add(new ModelBox(Legs, 0, 1, -4.0F, -23.0F, -2.0F, 8, 3, 3, 0.0F, false));
		Legs.cubeList.add(new ModelBox(Legs, 0, 1, -4.0F, -18.0F, -2.0F, 8, 3, 3, 0.0F, false));
		Legs.cubeList.add(new ModelBox(Legs, 0, 0, -4.0F, -25.0F, -2.0F, 2, 2, 3, 0.0F, false));
		Legs.cubeList.add(new ModelBox(Legs, 0, 0, -3.0F, -15.0F, -2.0F, 6, 2, 3, 0.0F, false));
		Legs.cubeList.add(new ModelBox(Legs, 0, 0, 2.0F, -25.0F, -2.0F, 2, 2, 3, 0.0F, false));
		Legs.cubeList.add(new ModelBox(Legs, 0, 0, -5.0F, -1.806F, -3.0055F, 10, 2, 6, 0.0F, false));
		Legs.cubeList.add(new ModelBox(Legs, 0, 0, -5.8716F, -0.806F, -2.0F, 11, 1, 4, 0.0F, false));
		Legs.cubeList.add(new ModelBox(Legs, 0, 0, -4.0F, -0.806F, -3.8716F, 8, 1, 7, 0.0F, false));
		
		Skirt = new RendererModel(this);
		Skirt.setRotationPoint(0.0F, -15.0F, 0.0F);
		Legs.addChild(Skirt);
		
		bone2 = new RendererModel(this);
		bone2.setRotationPoint(-4.0F, 15.0F, 0.0F);
		setRotationAngle(bone2, 0.0F, 0.0F, 0.5236F);
		Skirt.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 0, 0, -2.2738F, -2.1953F, -2.0F, 2, 2, 4, 0.0F, false));
		
		bone3 = new RendererModel(this);
		bone3.setRotationPoint(-4.0F, 15.0F, 0.0F);
		setRotationAngle(bone3, 0.0F, 0.0F, -0.5236F);
		Skirt.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 0, 0, 7.202F, 1.8047F, -2.0F, 2, 2, 4, 0.0F, false));
		
		bone4 = new RendererModel(this);
		bone4.setRotationPoint(10.0F, 15.0F, 0.0F);
		setRotationAngle(bone4, 0.0F, 0.0F, -0.0873F);
		Skirt.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 0, 0, -8.8441F, -13.4735F, -2.0F, 4, 11, 4, 0.0F, false));
		
		bone = new RendererModel(this);
		bone.setRotationPoint(0.0F, 15.0F, 0.0F);
		setRotationAngle(bone, 0.0F, 0.0F, 0.0873F);
		Skirt.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 0, 0, -5.1178F, -12.6019F, -2.0F, 4, 11, 4, 0.0F, false));
		
		Front = new RendererModel(this);
		Front.setRotationPoint(0.0F, 15.0F, 0.0F);
		setRotationAngle(Front, -0.0873F, 0.0F, 0.0F);
		Skirt.addChild(Front);
		Front.cubeList.add(new ModelBox(Front, 0, 0, -4.0F, -14.7762F, -3.1254F, 8, 13, 3, 0.0F, false));
		
		Front3 = new RendererModel(this);
		Front3.setRotationPoint(0.0F, 15.0F, 0.0F);
		setRotationAngle(Front3, -0.5236F, 0.0F, 0.0F);
		Skirt.addChild(Front3);
		Front3.cubeList.add(new ModelBox(Front3, 0, 0, -4.0F, -1.1953F, -4.0059F, 8, 2, 2, 0.0F, false));
		
		Front4 = new RendererModel(this);
		Front4.setRotationPoint(0.0F, 15.0F, 0.0F);
		setRotationAngle(Front4, 0.5236F, 0.0F, 0.0F);
		Skirt.addChild(Front4);
		Front4.cubeList.add(new ModelBox(Front4, 0, 0, -4.0F, -1.1953F, 2.0059F, 8, 2, 2, 0.0F, false));
		
		Front2 = new RendererModel(this);
		Front2.setRotationPoint(0.0F, 15.0F, 0.0F);
		setRotationAngle(Front2, 0.0873F, 0.0F, 0.0F);
		Skirt.addChild(Front2);
		Front2.cubeList.add(new ModelBox(Front2, 0, 1, -4.0F, -12.7762F, -0.8746F, 8, 11, 4, 0.0F, true));
		
		Wings = new RendererModel(this);
		Wings.setRotationPoint(0.0F, -1.0F, 0.0F);
		Body.addChild(Wings);
		Wings.cubeList.add(new ModelBox(Wings, 0, 0, 0.5F, -23.0F, 2.0F, 1, 2, 1, 0.0F, false));
		Wings.cubeList.add(new ModelBox(Wings, 0, 0, -1.5F, -23.0F, 2.0F, 1, 2, 1, 0.0F, false));
		
		rightarm = new RendererModel(this);
		rightarm.setRotationPoint(-5.5F, -1.0F, 0.0F);
		rightarm.cubeList.add(new ModelBox(rightarm, 0, 0, -1.5F, 0.0F, -1.3F, 3, 12, 3, 0.0F, false));
		
		leftarm = new RendererModel(this);
		leftarm.setRotationPoint(5.5F, -1.0F, 0.0F);
		leftarm.cubeList.add(new ModelBox(leftarm, 2, 0, -1.5F, 0.0F, -1.3F, 3, 12, 3, 0.0F, false));
		
		LeftWing = new RendererModel(this);
		LeftWing.setRotationPoint(1.0F, 1.0F, 3.0F);
		
		Left = new RendererModel(this);
		Left.setRotationPoint(-6.0F, 22.3F, -2.8F);
		setRotationAngle(Left, 0.0F, 1.0472F, 0.0F);
		LeftWing.addChild(Left);
		
		bone13 = new RendererModel(this);
		bone13.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone13, 0.0873F, 0.0F, 0.0F);
		Left.addChild(bone13);
		bone13.cubeList.add(new ModelBox(bone13, 0, 0, 0.5F, -21.8171F, 8.2218F, 1, 1, 4, 0.0F, false));
		bone13.cubeList.add(new ModelBox(bone13, 0, 0, 0.5F, -27.2595F, 21.2465F, 1, 23, 1, 0.0F, false));
		bone13.cubeList.add(new ModelBox(bone13, 0, 0, 0.4768F, -27.2281F, 20.6049F, 1, 22, 1, 0.0F, false));
		bone13.cubeList.add(new ModelBox(bone13, 0, 0, 0.5F, -29.2595F, 20.2465F, 1, 4, 1, 0.0F, false));
		bone13.cubeList.add(new ModelBox(bone13, 0, 0, 0.5917F, -29.254F, 20.3087F, 0, 20, 1, 0.0F, false));
		bone13.cubeList.add(new ModelBox(bone13, 6, 1, 0.5917F, -29.254F, 19.3087F, 0, 3, 1, 0.0F, false));
		bone13.cubeList.add(new ModelBox(bone13, 0, 0, 0.7786F, -30.2383F, 18.4888F, 0, 15, 1, 0.0F, false));
		bone13.cubeList.add(new ModelBox(bone13, 0, 0, 0.7786F, -23.2345F, 14.576F, 0, 5, 1, 0.0F, false));
		bone13.cubeList.add(new ModelBox(bone13, 0, 0, 0.7786F, -22.2345F, 12.576F, 0, 6, 1, 0.0F, false));
		bone13.cubeList.add(new ModelBox(bone13, 0, 0, 0.7853F, -22.2247F, 9.6872F, 0, 7, 1, 0.0F, false));
		bone13.cubeList.add(new ModelBox(bone13, 0, 0, 0.7786F, -22.2345F, 13.576F, 0, 5, 1, 0.0F, false));
		bone13.cubeList.add(new ModelBox(bone13, 12, 9, 0.7786F, -30.2383F, 17.4888F, 0, 4, 1, 0.0F, false));
		bone13.cubeList.add(new ModelBox(bone13, 14, 11, 0.7853F, -29.2323F, 16.5129F, 0, 4, 1, 0.0F, false));
		bone13.cubeList.add(new ModelBox(bone13, 0, 0, 0.8795F, -30.2358F, 17.5171F, 0, 14, 1, 0.0F, false));
		bone13.cubeList.add(new ModelBox(bone13, 0, 0, 0.8795F, -30.2358F, 16.5171F, 0, 13, 1, 0.0F, false));
		bone13.cubeList.add(new ModelBox(bone13, 0, 0, 0.8795F, -29.2358F, 15.5171F, 0, 11, 1, 0.0F, false));
		bone13.cubeList.add(new ModelBox(bone13, 0, 0, 0.8844F, -22.2272F, 11.6589F, 0, 7, 1, 0.0F, false));
		bone13.cubeList.add(new ModelBox(bone13, 0, 0, 0.8752F, -21.9254F, 10.6668F, 0, 7, 1, 0.0F, false));
		bone13.cubeList.add(new ModelBox(bone13, 0, 0, 0.8661F, -21.9224F, 8.7008F, 0, 6, 1, 0.0F, false));
		bone13.cubeList.add(new ModelBox(bone13, 0, 0, 0.6835F, -30.2486F, 19.371F, 0, 17, 1, 0.0F, false));
		bone13.cubeList.add(new ModelBox(bone13, 0, 0, 0.6835F, -29.9459F, 18.432F, 0, 3, 1, 0.0F, false));
		
		bone5 = new RendererModel(this);
		bone5.setRotationPoint(0.0F, 0.0F, 12.0F);
		setRotationAngle(bone5, 0.4363F, 0.0F, 0.0F);
		Left.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 0, 0, 0.5F, -21.3659F, 7.9682F, 1, 1, 2, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 0, 0, 0.5F, -28.599F, 15.0688F, 1, 2, 1, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 0, 0, 0.5F, -25.0782F, 17.3524F, 1, 2, 2, 0.0F, false));
		
		bone7 = new RendererModel(this);
		bone7.setRotationPoint(0.0F, 0.0F, 9.0F);
		setRotationAngle(bone7, 0.8727F, 0.0F, 0.0F);
		Left.addChild(bone7);
		bone7.cubeList.add(new ModelBox(bone7, 0, 0, 0.5F, -12.8002F, 19.8842F, 1, 1, 2, 0.0F, false));
		bone7.cubeList.add(new ModelBox(bone7, 0, 0, 0.5F, -14.2517F, 28.0661F, 1, 2, 2, 0.0F, false));
		bone7.cubeList.add(new ModelBox(bone7, 0, 0, 0.5F, -17.253F, 26.6718F, 1, 1, 1, 0.0F, false));
		
		bone11 = new RendererModel(this);
		bone11.setRotationPoint(0.0F, -23.0F, 8.0F);
		setRotationAngle(bone11, 1.309F, 0.0F, 0.0F);
		Left.addChild(bone11);
		bone11.cubeList.add(new ModelBox(bone11, 0, 0, 0.5F, 4.7088F, 9.3147F, 1, 2, 2, 0.0F, false));
		bone11.cubeList.add(new ModelBox(bone11, 0, 0, 0.5F, 4.6347F, 3.2105F, 1, 1, 2, 0.0F, false));
		bone11.cubeList.add(new ModelBox(bone11, 0, 0, 0.5F, 2.5542F, 7.5068F, 1, 2, 2, 0.0F, false));
		
		bone12 = new RendererModel(this);
		bone12.setRotationPoint(0.0F, -25.0F, 13.0F);
		setRotationAngle(bone12, 1.7453F, 0.0F, 0.0F);
		Left.addChild(bone12);
		bone12.cubeList.add(new ModelBox(bone12, 0, 0, 0.5F, 0.2161F, 1.6227F, 1, 2, 3, 0.0F, false));
		
		bone10 = new RendererModel(this);
		bone10.setRotationPoint(0.0F, 0.0F, 0.0F);
		Left.addChild(bone10);
		bone10.cubeList.add(new ModelBox(bone10, 0, 0, 0.5F, -32.7105F, 14.4768F, 1, 2, 1, 0.0F, false));
		
		bone9 = new RendererModel(this);
		bone9.setRotationPoint(0.0F, 0.0F, 0.0F);
		Left.addChild(bone9);
		
		bone8 = new RendererModel(this);
		bone8.setRotationPoint(0.0F, 0.0F, 0.0F);
		Left.addChild(bone8);
		
		RightWing = new RendererModel(this);
		RightWing.setRotationPoint(-1.0F, 1.0F, 3.0F);
		
		Right2 = new RendererModel(this);
		Right2.setRotationPoint(5.0F, 22.3F, -4.5F);
		setRotationAngle(Right2, 0.0F, -1.0472F, 0.0F);
		RightWing.addChild(Right2);
		
		bone14 = new RendererModel(this);
		bone14.setRotationPoint(-0.866F, 0.0F, -0.5F);
		setRotationAngle(bone14, 0.0873F, 0.0F, 0.0F);
		Right2.addChild(bone14);
		bone14.cubeList.add(new ModelBox(bone14, 0, 0, 1.366F, -21.8171F, 8.7218F, 1, 1, 4, 0.0F, false));
		bone14.cubeList.add(new ModelBox(bone14, 0, 0, 1.366F, -27.2595F, 21.7465F, 1, 23, 1, 0.0F, false));
		bone14.cubeList.add(new ModelBox(bone14, 0, 0, 1.3609F, -27.2195F, 21.2036F, 1, 22, 1, 0.0F, false));
		bone14.cubeList.add(new ModelBox(bone14, 0, 0, 1.366F, -29.2595F, 20.7465F, 1, 4, 1, 0.0F, false));
		bone14.cubeList.add(new ModelBox(bone14, 0, 0, 1.4578F, -29.254F, 20.8087F, 0, 20, 1, 0.0F, false));
		bone14.cubeList.add(new ModelBox(bone14, 0, 0, 1.4578F, -29.254F, 19.8087F, 0, 3, 1, 0.0F, false));
		bone14.cubeList.add(new ModelBox(bone14, 0, 0, 1.6446F, -30.2383F, 18.9888F, 0, 15, 1, 0.0F, false));
		bone14.cubeList.add(new ModelBox(bone14, 0, 0, 1.6446F, -23.2345F, 15.076F, 0, 5, 1, 0.0F, false));
		bone14.cubeList.add(new ModelBox(bone14, 0, 0, 1.6446F, -22.2345F, 13.076F, 0, 6, 1, 0.0F, false));
		bone14.cubeList.add(new ModelBox(bone14, 0, 0, 1.6513F, -22.2247F, 10.1872F, 0, 7, 1, 0.0F, false));
		bone14.cubeList.add(new ModelBox(bone14, 0, 0, 1.6446F, -22.2345F, 14.076F, 0, 5, 1, 0.0F, false));
		bone14.cubeList.add(new ModelBox(bone14, 0, 0, 1.6446F, -30.2383F, 17.9888F, 0, 4, 1, 0.0F, false));
		bone14.cubeList.add(new ModelBox(bone14, 0, 0, 1.6513F, -29.2323F, 17.0129F, 0, 4, 1, 0.0F, false));
		bone14.cubeList.add(new ModelBox(bone14, 0, 0, 1.7455F, -30.2358F, 18.0171F, 0, 14, 1, 0.0F, false));
		bone14.cubeList.add(new ModelBox(bone14, 0, 0, 1.7455F, -30.2358F, 17.0171F, 0, 13, 1, 0.0F, false));
		bone14.cubeList.add(new ModelBox(bone14, 0, 0, 1.7455F, -29.2358F, 16.0171F, 0, 11, 1, 0.0F, false));
		bone14.cubeList.add(new ModelBox(bone14, 0, 0, 1.7504F, -22.2272F, 12.1589F, 0, 7, 1, 0.0F, false));
		bone14.cubeList.add(new ModelBox(bone14, 0, 0, 1.7413F, -21.9254F, 11.1668F, 0, 7, 1, 0.0F, false));
		bone14.cubeList.add(new ModelBox(bone14, 0, 0, 1.7321F, -21.9224F, 9.2008F, 0, 6, 1, 0.0F, false));
		bone14.cubeList.add(new ModelBox(bone14, 0, 0, 1.5495F, -30.2486F, 19.871F, 0, 17, 1, 0.0F, false));
		bone14.cubeList.add(new ModelBox(bone14, 0, 0, 1.5495F, -29.9459F, 18.932F, 0, 3, 1, 0.0F, false));
		
		bone15 = new RendererModel(this);
		bone15.setRotationPoint(0.0F, 0.0F, 12.0F);
		setRotationAngle(bone15, 0.4363F, 0.0F, 0.0F);
		Right2.addChild(bone15);
		bone15.cubeList.add(new ModelBox(bone15, 0, 0, 0.5F, -21.3659F, 7.9682F, 1, 1, 2, 0.0F, false));
		bone15.cubeList.add(new ModelBox(bone15, 0, 0, 0.5F, -28.599F, 15.0688F, 1, 2, 1, 0.0F, false));
		bone15.cubeList.add(new ModelBox(bone15, 0, 0, 0.5F, -25.0782F, 17.3524F, 1, 2, 2, 0.0F, false));
		
		bone16 = new RendererModel(this);
		bone16.setRotationPoint(0.0F, 0.0F, 9.0F);
		setRotationAngle(bone16, 0.8727F, 0.0F, 0.0F);
		Right2.addChild(bone16);
		bone16.cubeList.add(new ModelBox(bone16, 0, 0, 0.5F, -12.8002F, 19.8842F, 1, 1, 2, 0.0F, false));
		bone16.cubeList.add(new ModelBox(bone16, 0, 0, 0.5F, -14.2517F, 28.0661F, 1, 2, 2, 0.0F, false));
		bone16.cubeList.add(new ModelBox(bone16, 0, 0, 0.5F, -17.253F, 26.6718F, 1, 1, 1, 0.0F, false));
		
		bone17 = new RendererModel(this);
		bone17.setRotationPoint(0.0F, -23.0F, 8.0F);
		setRotationAngle(bone17, 1.309F, 0.0F, 0.0F);
		Right2.addChild(bone17);
		bone17.cubeList.add(new ModelBox(bone17, 0, 0, 0.5F, 4.7088F, 9.3147F, 1, 2, 2, 0.0F, false));
		bone17.cubeList.add(new ModelBox(bone17, 0, 0, 0.5F, 4.6347F, 3.2105F, 1, 1, 2, 0.0F, false));
		bone17.cubeList.add(new ModelBox(bone17, 0, 0, 0.5F, 2.5542F, 7.5068F, 1, 2, 2, 0.0F, false));
		
		bone18 = new RendererModel(this);
		bone18.setRotationPoint(0.0F, -25.0F, 13.0F);
		setRotationAngle(bone18, 1.7453F, 0.0F, 0.0F);
		Right2.addChild(bone18);
		bone18.cubeList.add(new ModelBox(bone18, 0, 0, 0.5F, 0.2161F, 1.6227F, 1, 2, 3, 0.0F, false));
		
		bone19 = new RendererModel(this);
		bone19.setRotationPoint(0.0F, 0.0F, 0.0F);
		Right2.addChild(bone19);
		bone19.cubeList.add(new ModelBox(bone19, 0, 0, 0.5F, -32.7105F, 14.4768F, 1, 2, 1, 0.0F, false));
		
		bone20 = new RendererModel(this);
		bone20.setRotationPoint(0.0F, 0.0F, 0.0F);
		Right2.addChild(bone20);
		
		bone21 = new RendererModel(this);
		bone21.setRotationPoint(0.0F, 0.0F, 0.0F);
		Right2.addChild(bone21);
	}
	
	@Override
	public void render(LivingEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		setRotationAngles(entity);
		Head.render(f5);
		Body.render(f5);
		rightarm.render(f5);
		leftarm.render(f5);
		LeftWing.render(f5);
		RightWing.render(f5);
	}
	
	public void setRotationAngle(RendererModel RendererModel, float x, float y, float z) {
		RendererModel.rotateAngleX = x;
		RendererModel.rotateAngleY = y;
		RendererModel.rotateAngleZ = z;
	}
	
	public void setRotationAngles(Entity entity) {
		if (entity instanceof WeepingAngelEntity) {
			WeepingAngelEntity angel = (WeepingAngelEntity) entity;
			if (angel.getAngelPose().equals(AngelPoses.POSE_ANGRY.getRegistryName())) {
				float f6 = MathHelper.sin(angel.ticksExisted / 50 * 3.141593F);
				rightarm.rotateAngleZ = 0.0F;
				leftarm.rotateAngleZ = 0.0F;
				rightarm.rotateAngleY = -(0.1F - f6 * 0.6F);
				leftarm.rotateAngleY = 0.1F - f6 * 0.6F;
				rightarm.rotateAngleX = -1.570796F;
				leftarm.rotateAngleX = -1.570796F;
			}
			
			if (angel.getAngelPose().equals(AngelPoses.POSE_HIDING_FACE.getRegistryName())) {
				leftarm.rotateAngleX = toRadians(-100f);
				leftarm.rotateAngleY = toRadians(20f);
				leftarm.rotateAngleZ = toRadians(0);
				rightarm.rotateAngleX = toRadians(-100f);
				rightarm.rotateAngleY = toRadians(-20f);
				rightarm.rotateAngleZ = toRadians(0);
				Head.rotateAngleX = toRadians(10f);
			} else {
				rightarm.rotateAngleX = -1.74533F;
				rightarm.rotateAngleY = -0.55851F;
				rightarm.rotateAngleZ = 0.0F;
				leftarm.rotateAngleX = -1.74533F;
				leftarm.rotateAngleY = 0.55851F;
				leftarm.rotateAngleZ = 0.0F;
			}
		} else {
			rightarm.rotateAngleX = -1.74533F;
			rightarm.rotateAngleY = -0.55851F;
			rightarm.rotateAngleZ = 0.0F;
			leftarm.rotateAngleX = -1.74533F;
			leftarm.rotateAngleY = 0.55851F;
			leftarm.rotateAngleZ = 0.0F;
		}
	}
	
	private float toRadians(float f) {
		return (float) Math.toRadians(f);
	}
}
