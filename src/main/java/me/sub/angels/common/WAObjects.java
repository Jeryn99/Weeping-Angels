package me.sub.angels.common;

import me.sub.angels.WeepingAngels;
import me.sub.angels.common.blocks.BlockAngelStatue;
import me.sub.angels.common.blocks.BlockChronodyneGenerator;
import me.sub.angels.common.blocks.BlockSnowArm;
import me.sub.angels.common.entities.EntityAnomaly;
import me.sub.angels.common.entities.EntityChronodyneGenerator;
import me.sub.angels.common.entities.EntityWeepingAngel;
import me.sub.angels.common.items.ItemAngelSpawner;
import me.sub.angels.common.items.ItemChronodyneGenerator;
import me.sub.angels.common.items.ItemDetector;
import me.sub.angels.common.misc.AngelEnums;
import me.sub.angels.common.misc.WADamageSource;
import me.sub.angels.common.tileentities.TileEntityChronodyneGenerator;
import me.sub.angels.common.tileentities.TileEntityPlinth;
import me.sub.angels.common.tileentities.TileEntitySnowArm;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class WAObjects implements ModInitializer
{

	public static final DamageSource ANGEL = new WADamageSource("backintime");
	public static final DamageSource STONE = new WADamageSource("punch_stone");
	public static final DamageSource ANGEL_NECK_SNAP = new WADamageSource("neck_snap");
//TODO fabric
//	public static CreativeTabs ANGEL_TAB = new CreativeTabAngel("angels");
	// Helper, gets reset after init


	@Override public void onInitialize()
	{
		Blocks.ARM = Registry.BLOCK.register(new Identifier(WeepingAngels.MODID, "arm"), new BlockSnowArm());
		Blocks.CG = Registry.BLOCK.register(new Identifier(WeepingAngels.MODID, "cg"), new BlockChronodyneGenerator());
		Blocks.PLINTH = Registry.BLOCK.register(new Identifier(WeepingAngels.MODID, "plinth"), new BlockAngelStatue());

		BlockEntities.ARM = Registry.register(Registry.BLOCK_ENTITY, new Identifier(WeepingAngels.MODID, "arm_entity").toString(), BlockEntityType.Builder.create(() -> new TileEntitySnowArm(BlockEntities.ARM)).build(null));
		BlockEntities.CG = Registry.register(Registry.BLOCK_ENTITY, new Identifier(WeepingAngels.MODID, "cg_entity").toString(), BlockEntityType.Builder.create(() -> new TileEntityChronodyneGenerator(BlockEntities.CG)).build(null));
		BlockEntities.PLINTH = Registry.register(Registry.BLOCK_ENTITY, new Identifier(WeepingAngels.MODID, "plinth").toString(), BlockEntityType.Builder.create(() -> new TileEntityPlinth(BlockEntities.PLINTH)).build(null));

//		Items.ANGEL_PAINTING = Registry.ITEM.register(new Identifier(WeepingAngels.MODID, "angel_painting"), new ItemHanging());
		Items.UNLIT_TORCH = Registry.ITEM.register(new Identifier(WeepingAngels.MODID, "unlit_torch"), new Item(new Item.Settings()));
		Items.TIMEY_WIMEY_DETECTOR = Registry.ITEM.register(new Identifier(WeepingAngels.MODID, "timey_wimey_detector"), new ItemDetector());
		Items.CHRONODYNE_GENERATOR = Registry.ITEM.register(new Identifier(WeepingAngels.MODID, "chronodyne_generator"), new ItemChronodyneGenerator());
		Items.ANGEL_0 = Registry.ITEM.register(new Identifier(WeepingAngels.MODID, "angel_0"), new ItemAngelSpawner<>(AngelEnums.AngelType.ANGEL_ONE, EntityWeepingAngel::new));
		Items.ANGEL_1 = Registry.ITEM.register(new Identifier(WeepingAngels.MODID, "angel_1"), new ItemAngelSpawner<>(AngelEnums.AngelType.ANGEL_TWO, EntityWeepingAngel::new));
		Items.ANGEL_CHILD = Registry.ITEM.register(new Identifier(WeepingAngels.MODID, "angel_child"), new ItemAngelSpawner<>(AngelEnums.AngelType.ANGEL_CHILD, EntityWeepingAngel::new));
		//TODO register items for blocks
		EntityEntries.WEEPING_ANGEL = Registry.ENTITY_TYPE.register(new Identifier(WeepingAngels.MODID, "weeping_angel"), FabricEntityTypeBuilder.create(EntityCategory.MONSTER, EntityWeepingAngel::new).trackable(64, 1, true).build());
//		EntityEntries.WEEPING_ANGEL_PAINTING = Registry.ENTITY_TYPE.register(new Identifier(WeepingAngels.MODID, "angel_painting"), FabricEntityTypeBuilder.create(EntityCategory.MISC, EntityAngelPainting::new).trackable(64, 1, true).build());
		EntityEntries.CHRONODYNE_GENERATOR = Registry.ENTITY_TYPE.register(new Identifier(WeepingAngels.MODID, "chronodyne_generator"), FabricEntityTypeBuilder.create(EntityCategory.MONSTER, EntityChronodyneGenerator::new).trackable(64, 1, true).build());
		EntityEntries.ANOMALY = Registry.ENTITY_TYPE.register(new Identifier(WeepingAngels.MODID, "anomaly"), FabricEntityTypeBuilder.create(EntityCategory.MONSTER, EntityAnomaly::new).trackable(64, 1, true).build());

//		GameRegistry.registerWorldGenerator(new WorldGenCatacombs(), 8);

//		AngelUtils.setUpSpawns();
	}

	public static class Blocks {
		public static Block ARM = null;
		public static Block CG = null;
		public static Block PLINTH = null;
	}

	public static class Items {
		public static Item ANGEL_PAINTING = null;
		public static Item UNLIT_TORCH = null;
		public static Item TIMEY_WIMEY_DETECTOR = null;
		public static Item CHRONODYNE_GENERATOR = null;
		public static Item ANGEL_0 = null;
		public static Item ANGEL_1 = null;
		public static Item ANGEL_CHILD = null;
	}

	public static class BlockEntities {
		public static BlockEntityType<TileEntitySnowArm> ARM = null;
		public static BlockEntityType<TileEntityChronodyneGenerator> CG = null;
		public static BlockEntityType<TileEntityPlinth> PLINTH = null;
	}

	public static class EntityEntries {
		public static EntityType<EntityWeepingAngel> WEEPING_ANGEL = null;
//		public static EntityType<EntityAngelPainting> WEEPING_ANGEL_PAINTING;
		public static EntityType<EntityChronodyneGenerator> CHRONODYNE_GENERATOR;
		public static EntityType<EntityAnomaly> ANOMALY;
	}

	// Sounds TODO fabric sounds
//	public static class Sounds {
//		public static final SoundEvent ANGEL_SEEN_1 = null;
//		public static final SoundEvent ANGEL_SEEN_2 = null;
//		public static final SoundEvent ANGEL_SEEN_3 = null;
//		public static final SoundEvent ANGEL_SEEN_4 = null;
//		public static final SoundEvent ANGEL_SEEN_5 = null;
//		public static final SoundEvent STONE_SCRAP = null;
//		public static final SoundEvent CHILD_RUN = null;
//		public static final SoundEvent LAUGHING_CHILD = null;
//		public static final SoundEvent LIGHT_BREAK = null;
//		public static final SoundEvent ANGEL_TELEPORT = null;
//		public static final SoundEvent ANGEL_AMBIENT = null;
//		public static final SoundEvent DING = null;
//		public static final SoundEvent BLOW = null;
//		public static final SoundEvent ANGEL_DEATH = null;
//	}
//	@SubscribeEvent
//	public static void addSounds(RegistryEvent.Register<SoundEvent> e) {
//		e.getRegistry().registerAll(setUpSound("angel_seen_1"), setUpSound("angel_seen_2"), setUpSound("angel_seen_3"), setUpSound("angel_seen_4"), setUpSound("angel_seen_5"), setUpSound("stone_scrap"), setUpSound("child_run"), setUpSound("laughing_child"), setUpSound("light_break"), setUpSound("angel_teleport"), setUpSound("angel_ambient"), setUpSound("ding"), setUpSound("blow"), setUpSound("angel_death"));
//	}
//
//	private static SoundEvent setUpSound(String soundName) {
//		return new SoundEvent(new ResourceLocation(WeepingAngels.MODID, soundName)).setRegistryName(soundName);
//	}


}
