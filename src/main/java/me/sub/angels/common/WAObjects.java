package me.sub.angels.common;

import me.sub.angels.WeepingAngels;
import me.sub.angels.common.blocks.BlockAngelStatue;
import me.sub.angels.common.blocks.BlockChronodyneGenerator;
import me.sub.angels.common.blocks.BlockSnowArm;
import me.sub.angels.common.entities.EntityAngelPainting;
import me.sub.angels.common.entities.EntityAnomaly;
import me.sub.angels.common.entities.EntityChronodyneGenerator;
import me.sub.angels.common.entities.EntityWeepingAngel;
import me.sub.angels.common.items.ItemAngelSpawner;
import me.sub.angels.common.items.ItemChronodyneGenerator;
import me.sub.angels.common.items.ItemDetector;
import me.sub.angels.common.items.ItemHanging;
import me.sub.angels.common.misc.AngelEnums;
import me.sub.angels.common.misc.CreativeTabAngel;
import me.sub.angels.common.misc.WADamageSource;
import me.sub.angels.common.tileentities.TileEntityChronodyneGenerator;
import me.sub.angels.common.tileentities.TileEntityPlinth;
import me.sub.angels.common.tileentities.TileEntitySnowArm;
import me.sub.angels.common.world.generation.WorldGenCatacombs;
import me.sub.angels.utils.AngelUtils;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class WAObjects {

    public static final DamageSource ANGEL = new WADamageSource("backintime");
	public static final DamageSource STONE = new WADamageSource("punch_stone");
	public static final DamageSource ANGEL_NECK_SNAP = new WADamageSource("neck_snap");

    public static CreativeTabs ANGEL_TAB = new CreativeTabAngel("angels");
	public static List<Item> items = new ArrayList<>();
	// Helper, gets reset after init
	private static List<Item> itemBlocks = new ArrayList<>();

    @SubscribeEvent
	public static void addItems(RegistryEvent.Register<Item> e) {
		registerItems(e.getRegistry(), ANGEL_TAB, itemBlocks.toArray(new Item[itemBlocks.size()]));

        e.getRegistry().registerAll(setUpItem(new ItemHanging(), "angel_painting", true), setUpItem(new Item(), "unlit_torch", false), setUpItem(new ItemDetector(), "timey_wimey_detector", true), setUpItem(new ItemChronodyneGenerator(), "chronodyne_generator", true), setUpItem(new ItemAngelSpawner<>(AngelEnums.AngelType.ANGEL_ONE, EntityWeepingAngel::new), "angel_0", true), setUpItem(new ItemAngelSpawner<>(AngelEnums.AngelType.ANGEL_TWO, EntityWeepingAngel::new), "angel_1", true), setUpItem(new ItemAngelSpawner<>(AngelEnums.AngelType.ANGEL_CHILD, EntityWeepingAngel::new), "angel_child", true));
	}

    @SubscribeEvent
	public static void addBlocks(RegistryEvent.Register<Block> e) {
		registerBlocks(e.getRegistry(), new BlockSnowArm("arm"), new BlockAngelStatue("plinth"));

        e.getRegistry().register(new BlockChronodyneGenerator("cg"));

        GameRegistry.registerWorldGenerator(new WorldGenCatacombs(), 8);

        GameRegistry.registerTileEntity(TileEntitySnowArm.class, new ResourceLocation(WeepingAngels.MODID + ":snowarm"));
		GameRegistry.registerTileEntity(TileEntityChronodyneGenerator.class, new ResourceLocation(WeepingAngels.MODID + ":cg"));
		GameRegistry.registerTileEntity(TileEntityPlinth.class, new ResourceLocation(WeepingAngels.MODID + ":plinth"));
	}

    @SubscribeEvent
	public static void addEntities(RegistryEvent.Register<EntityEntry> e) {
		e.getRegistry().registerAll(EntityEntries.ANOMALY, EntityEntries.CHRONODYNE_GENERATOR, EntityEntries.WEEPING_ANGEL, EntityEntries.WEEPING_ANGEL_PAINTING);

        AngelUtils.setUpSpawns();
	}

    @SubscribeEvent
	public static void addSounds(RegistryEvent.Register<SoundEvent> e) {
		e.getRegistry().registerAll(setUpSound("angel_seen_1"), setUpSound("angel_seen_2"), setUpSound("angel_seen_3"), setUpSound("angel_seen_4"), setUpSound("angel_seen_5"), setUpSound("stone_scrap"), setUpSound("child_run"), setUpSound("laughing_child"), setUpSound("light_break"), setUpSound("angel_teleport"), setUpSound("angel_ambient"), setUpSound("ding"), setUpSound("blow"), setUpSound("angel_death"));
	}

    private static SoundEvent setUpSound(String soundName) {
		return new SoundEvent(new ResourceLocation(WeepingAngels.MODID, soundName)).setRegistryName(soundName);
	}

    private static Item setUpItem(Item item, String name, boolean addToTab) {
		item.setRegistryName(WeepingAngels.MODID, name);
		item.setUnlocalizedName(name);

        if (addToTab) {
			item.setCreativeTab(ANGEL_TAB);
		}

        WAObjects.items.add(item);
		return item;
	}

    private static void registerItems(IForgeRegistry<Item> reg, CreativeTabs tab, Item[] items) {
		reg.registerAll(items);
		for (Item item : items) {
			item.setCreativeTab(tab);
			WAObjects.items.add(item);
		}
	}

    private static void registerBlocks(IForgeRegistry<Block> reg, Block... blocks) {
		reg.registerAll(blocks);
		for (Block block : blocks) {
			block.setCreativeTab(ANGEL_TAB);
			itemBlocks.add(new ItemBlock(block).setRegistryName(block.getRegistryName()).setUnlocalizedName(block.getUnlocalizedName()));
		}
	}

    @GameRegistry.ObjectHolder(WeepingAngels.MODID)
	public static class Blocks {
		public static final Block ARM = null;
		public static final Block CG = null;
		public static final Block PLINTH = null;
	}

    @GameRegistry.ObjectHolder(WeepingAngels.MODID)
	public static class Items {
		public static final Item ANGEL_PAINTING = null;
		public static final Item UNLIT_TORCH = null;
		public static final Item TIMEY_WIMEY_DETECTOR = null;
		public static final Item CHRONODYNE_GENERATOR = null;
		public static final Item ANGEL_0 = null;
		public static final Item ANGEL_1 = null;
		public static final Item ANGEL_CHILD = null;
	}

    // Sounds
	@GameRegistry.ObjectHolder(WeepingAngels.MODID)
	public static class Sounds {
		public static final SoundEvent ANGEL_SEEN_1 = null;
		public static final SoundEvent ANGEL_SEEN_2 = null;
		public static final SoundEvent ANGEL_SEEN_3 = null;
		public static final SoundEvent ANGEL_SEEN_4 = null;
		public static final SoundEvent ANGEL_SEEN_5 = null;
		public static final SoundEvent STONE_SCRAP = null;
		public static final SoundEvent CHILD_RUN = null;
		public static final SoundEvent LAUGHING_CHILD = null;
		public static final SoundEvent LIGHT_BREAK = null;
		public static final SoundEvent ANGEL_TELEPORT = null;
		public static final SoundEvent ANGEL_AMBIENT = null;
		public static final SoundEvent DING = null;
		public static final SoundEvent BLOW = null;
		public static final SoundEvent ANGEL_DEATH = null;
	}

    // Entities
	@GameRegistry.ObjectHolder(WeepingAngels.MODID)
	public static class EntityEntries {
		public static final EntityEntry WEEPING_ANGEL = EntityEntryBuilder.create().entity(EntityWeepingAngel.class).id(new ResourceLocation(WeepingAngels.MODID, "weepingangel"), 0).name("angel").tracker(80, 3, false).build();
		public static final EntityEntry WEEPING_ANGEL_PAINTING = EntityEntryBuilder.create().entity(EntityAngelPainting.class).id(new ResourceLocation(WeepingAngels.MODID, "weepingAngelpainting"), 1).name("weepingAngelpainting").tracker(80, Integer.MAX_VALUE, false).build();
		public static final EntityEntry CHRONODYNE_GENERATOR = EntityEntryBuilder.create().entity(EntityChronodyneGenerator.class).id(new ResourceLocation(WeepingAngels.MODID, "chronodyne_generator"), 2).name("chronodyne_generator").tracker(80, 3, true).build();
		public static final EntityEntry ANOMALY = EntityEntryBuilder.create().entity(EntityAnomaly.class).id(new ResourceLocation(WeepingAngels.MODID, "anomaly"), 3).name("anomaly").tracker(80, 3, true).build();
	}
}
