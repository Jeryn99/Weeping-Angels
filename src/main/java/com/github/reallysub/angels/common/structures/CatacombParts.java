package com.github.reallysub.angels.common.structures;

import com.github.reallysub.angels.main.WeepingAngels;

import net.minecraft.util.ResourceLocation;

import java.util.Random;

public class CatacombParts {
	
	public static ResourceLocation partStraight = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_0");
	public static ResourceLocation partCorner1 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_1");
	public static ResourceLocation partTSection = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_2");
	public static ResourceLocation partCrossSection = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_3");
	
	public static ResourceLocation catacomb_hallway_flat_1 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_flat_1");
	public static ResourceLocation catacomb_hallway_clean_1 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_clean_1");
	public static ResourceLocation catacomb_hallway_broken_1 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_broken_1");
	
	public static ResourceLocation[] allParts = new ResourceLocation[] { partCorner1, partStraight, partTSection, partCrossSection };
	
	public static ResourceLocation[] allStraightParts = new ResourceLocation[] { catacomb_hallway_flat_1, partStraight, catacomb_hallway_clean_1, catacomb_hallway_broken_1 };
	
	public static ResourceLocation test = new ResourceLocation(WeepingAngels.MODID, "house/angel_house_room1");
	
	public static ResourceLocation getStraightPart() {
		Random rand = new Random();
		return CatacombParts.allStraightParts[rand.nextInt(CatacombParts.allStraightParts.length)];
	}
	
}
