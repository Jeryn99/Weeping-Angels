package me.sub.angels.common.structures;

import java.util.Random;

import me.sub.angels.main.WeepingAngels;
import net.minecraft.util.ResourceLocation;

public class CatacombParts {
	
	private static Random rand = new Random();
	
	public static ResourceLocation partStraight = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_0");
	public static ResourceLocation partCorner1 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_1");
	public static ResourceLocation partTSection = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_2");
	public static ResourceLocation partCrossSection = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_3");
	
	public static ResourceLocation catacomb_hallway_flat_1 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_flat_1");
	public static ResourceLocation catacomb_hallway_flat_2 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_flat_2");
	public static ResourceLocation catacomb_hallway_flat_3 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_flat_3");
	
	public static ResourceLocation catacomb_hallway_clean_1 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_clean_1");
	public static ResourceLocation catacomb_hallway_clean_2 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_clean_2");
	public static ResourceLocation catacomb_hallway_clean_3 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_clean_3");
	public static ResourceLocation catacomb_hallway_clean_4 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_clean_4");
	
	public static ResourceLocation catacomb_hallway_broken_1 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_broken_1");
	public static ResourceLocation catacomb_hallway_broken_2 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_broken_2");
	public static ResourceLocation catacomb_hallway_broken_3 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_broken_3");
	
	public static ResourceLocation[] allParts = new ResourceLocation[] { partCorner1, partStraight, partTSection, partCrossSection };
	
	public static ResourceLocation[] allStraightParts = new ResourceLocation[] { catacomb_hallway_flat_1, catacomb_hallway_flat_2, catacomb_hallway_flat_3, partStraight, catacomb_hallway_clean_1, catacomb_hallway_clean_2, catacomb_hallway_clean_3, catacomb_hallway_clean_4, catacomb_hallway_broken_1, catacomb_hallway_broken_2, catacomb_hallway_broken_3 };
	
	public static ResourceLocation getStraightPart() {
		return CatacombParts.allStraightParts[rand.nextInt(CatacombParts.allStraightParts.length)];
	}
	
}
