package me.sub.angels.common.world.generation;

import me.sub.angels.WeepingAngels;
import me.sub.angels.config.WAConfig;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

@Mod.EventBusSubscriber(modid = WeepingAngels.MODID)
public class WorldGenCatacombs {

	private static ResourceLocation partStraight = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_0");
	private static ResourceLocation partCorner1 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_1");
	private static ResourceLocation partTSection = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_2");
	private static ResourceLocation partCrossSection = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_3");
	private static ResourceLocation catacomb_hallway_flat_1 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_flat_1");
	private static ResourceLocation catacomb_hallway_flat_2 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_flat_2");
	private static ResourceLocation catacomb_hallway_flat_3 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_flat_3");

	private static ResourceLocation catacomb_hallway_clean_1 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_clean_1");
	private static ResourceLocation catacomb_hallway_clean_2 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_clean_2");
	private static ResourceLocation catacomb_hallway_clean_3 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_clean_3");
	private static ResourceLocation catacomb_hallway_clean_4 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_clean_4");

	private static ResourceLocation catacomb_hallway_broken_1 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_broken_1");
	private static ResourceLocation catacomb_hallway_broken_2 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_broken_2");
	private static ResourceLocation catacomb_hallway_broken_3 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_broken_3");

	public static ResourceLocation[] allParts = new ResourceLocation[]{partCorner1, partStraight, partTSection, partCrossSection};
	
	public static ResourceLocation[] allStraightParts = new ResourceLocation[] { catacomb_hallway_flat_1, catacomb_hallway_flat_2, catacomb_hallway_flat_3, partStraight, catacomb_hallway_clean_1, catacomb_hallway_clean_2, catacomb_hallway_clean_3, catacomb_hallway_clean_4, catacomb_hallway_broken_1, catacomb_hallway_broken_2, catacomb_hallway_broken_3 };

	public static ResourceLocation getStraightPart(Random rand) {
		return allStraightParts[rand.nextInt(allStraightParts.length)];
	}

	@SubscribeEvent
	public static void generate(DecorateBiomeEvent e) {
		if (WAConfig.worldGen.genCatacombs) {
			// TODO - Re-write Catacomb generation
		}
	}
}
