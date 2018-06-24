package me.sub.angels.common;

import com.google.common.collect.Lists;
import me.sub.angels.client.TabAngels;
import me.sub.angels.client.models.item.RenderItemStackBase;
import me.sub.angels.client.models.item.models.ModelDetector;
import me.sub.angels.client.render.entity.RenderAngel;
import me.sub.angels.client.render.entity.RenderAngelPainting;
import me.sub.angels.client.render.entity.RenderAnomaly;
import me.sub.angels.client.render.entity.RenderChronodyneGenerator;
import me.sub.angels.client.render.tileentity.RenderTileEntityCG;
import me.sub.angels.client.render.tileentity.RenderTileEntityPlinth;
import me.sub.angels.client.render.tileentity.RenderTileEntitySnowArm;
import me.sub.angels.common.blocks.BlockAngelStatue;
import me.sub.angels.common.blocks.BlockChronodyneGenerator;
import me.sub.angels.common.blocks.BlockSnowArm;
import me.sub.angels.common.entities.EntityAngel;
import me.sub.angels.common.entities.EntityAngelPainting;
import me.sub.angels.common.entities.EntityAnomaly;
import me.sub.angels.common.entities.EntityChronodyneGenerator;
import me.sub.angels.common.items.ItemAngelSpawner;
import me.sub.angels.common.items.ItemChronodyneGenerator;
import me.sub.angels.common.items.ItemDetector;
import me.sub.angels.common.items.ItemHanging;
import me.sub.angels.common.tiles.TileEntityChronodyneGenerator;
import me.sub.angels.common.tiles.TileEntityPlinth;
import me.sub.angels.common.tiles.TileEntitySnowArm;
import me.sub.angels.main.WeepingAngels;
import me.sub.angels.main.config.WAConfig;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Mod.EventBusSubscriber(modid = WeepingAngels.MODID)
public class WAObjects {
	
	public static final CreativeTabs ANGEL_TAB = new TabAngels();

    /**
	 * Damagesources
	 */
	public static final DamageSource ANGEL = new WADamageSource("backintime");
	public static final DamageSource STONE = new WADamageSource("punch_stone");
	public static final DamageSource ANGEL_NECK_SNAP = new WADamageSource("neck_snap");
	// Helper, gets reset after init
	private static List<Item> itemBlocks = new ArrayList<Item>();
	private static List<Item> items = new ArrayList<Item>();

    /**
	 * Set up the rendering for entities and tileentities
	 */
	@SideOnly(Side.CLIENT)
	public static void setUpRenders() {

        // Entities
		RenderingRegistry.registerEntityRenderingHandler(EntityAngel.class, new RenderAngel());
		RenderingRegistry.registerEntityRenderingHandler(EntityAngelPainting.class, new RenderAngelPainting());
		RenderingRegistry.registerEntityRenderingHandler(EntityAnomaly.class, new RenderAnomaly());

        // Items
		WAItems.TIMEY_WIMEY_DETECTOR.setTileEntityItemStackRenderer(new RenderItemStackBase(new ModelDetector()));

        // Projectiles
		RenderingRegistry.registerEntityRenderingHandler(EntityChronodyneGenerator.class, new RenderChronodyneGenerator());

        // TESRS
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySnowArm.class, new RenderTileEntitySnowArm());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChronodyneGenerator.class, new RenderTileEntityCG());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlinth.class, new RenderTileEntityPlinth());
	}

    private static SoundEvent setUpSound(String soundName) {
		return new SoundEvent(new ResourceLocation(WeepingAngels.MODID + ":" + soundName)).setRegistryName(soundName);
	}

    // Spawn Set up
	public static void setUpSpawns() {
		Collection<Biome> biomes = ForgeRegistries.BIOMES.getValuesCollection();
		ArrayList<Biome> spawn = Lists.newArrayList();
		spawn.addAll(biomes);

        for (String rs : WAConfig.spawn.notAllowedBiomes) {
			if (Biome.REGISTRY.containsKey(new ResourceLocation(rs))) {
				Biome removedBiome = Biome.REGISTRY.getObject(new ResourceLocation(rs));
				spawn.remove(removedBiome);
			}
		}

        for (Biome biome : spawn) {
			if (biome != null) {
				EntityRegistry.addSpawn(EntityAngel.class, WAConfig.spawn.spawnProbability, WAConfig.spawn.minimumSpawn, WAConfig.spawn.maximumSpawn, WAConfig.spawn.spawnType, biome);
			}
		}
	}
	
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent ev) {
		for (Item item : items) {
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}
		items = new ArrayList<>();
	}

    private static void registerBlockNoItems(IForgeRegistry<Block> reg, Block... blocks) {
		reg.registerAll(blocks);
	}

    private static Item registerItem(Item item, String name) {
		item.setRegistryName(WeepingAngels.MODID, name);
		item.setUnlocalizedName(name);
		return item;
	}
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		registerBlocks(event.getRegistry(), ANGEL_TAB, new BlockSnowArm("arm"), new BlockAngelStatue("plinth"));

        registerBlockNoItems(event.getRegistry(), new BlockChronodyneGenerator("cg"));
	}

    @SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		registerItems(event.getRegistry(), ANGEL_TAB, itemBlocks.toArray(new Item[itemBlocks.size()]));

        itemBlocks = new ArrayList<>();

        registerItems(event.getRegistry(), ANGEL_TAB, registerItem(new ItemHanging(), "angel_painting"), registerItem(new Item(), "unlit_torch"), registerItem(new ItemDetector(), "timey_wimey_detector"), registerItem(new ItemChronodyneGenerator(), "chronodyne_generator"), registerItem(new ItemAngelSpawner<>(0, EntityAngel::new), "angel_0"), registerItem(new ItemAngelSpawner<>(1, EntityAngel::new), "angel_1"), registerItem(new ItemAngelSpawner<>(0, EntityAngel::new, true), "angel_child"));
	}
	
	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
		event.getRegistry().registerAll(EntityEntries.ANOMALY, EntityEntries.CHRONODYNE_GENERATOR, EntityEntries.WEEPING_ANGEL, EntityEntries.WEEPING_ANGEL_PAINTING);
	}

    // Helper Methods
	private static void registerBlocks(IForgeRegistry<Block> reg, CreativeTabs tab, Block... blocks) {
		reg.registerAll(blocks);
		for (Block block : blocks) {
			block.setCreativeTab(tab);
			itemBlocks.add(new ItemBlock(block).setRegistryName(block.getRegistryName()).setUnlocalizedName(block.getUnlocalizedName()));
		}
	}
	
	// Sounds
	@ObjectHolder(WeepingAngels.MODID)
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
	
	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
		event.getRegistry().registerAll(setUpSound("angel_seen_1"), setUpSound("angel_seen_2"), setUpSound("angel_seen_3"), setUpSound("angel_seen_4"), setUpSound("angel_seen_5"), setUpSound("stone_scrap"), setUpSound("child_run"), setUpSound("laughing_child"), setUpSound("light_break"), setUpSound("angel_teleport"), setUpSound("angel_ambient"), setUpSound("ding"), setUpSound("blow"), setUpSound("angel_death"));
	}

    private static void registerItems(IForgeRegistry<Item> reg, CreativeTabs tab, Item... items) {
		reg.registerAll(items);
		for (Item item : items) {
			item.setCreativeTab(tab);
			WAObjects.items.add(item);
		}
	}
	
	// Blocks
	@ObjectHolder(WeepingAngels.MODID)
	public static class WABlocks {
		public static final Block ARM = null;
		public static final Block CG = null;
		public static final Block PLINTH = null;
	}
	
	// Items
	@ObjectHolder(WeepingAngels.MODID)
	public static class WAItems {
		public static final Item ANGEL_PAINTING = null;
		public static final Item UNLIT_TORCH = null;
		public static final Item TIMEY_WIMEY_DETECTOR = null;
		public static final Item CHRONODYNE_GENERATOR = null;
		public static final Item ANGEL_0 = null;
		public static final Item ANGEL_1 = null;
		public static final Item ANGEL_CHILD = null;
	}

    // Entities
	@ObjectHolder(WeepingAngels.MODID)
	public static class EntityEntries {
		public static final EntityEntry WEEPING_ANGEL = EntityEntryBuilder.create().entity(EntityAngel.class).id(new ResourceLocation(WeepingAngels.MODID, "weepingangel"), 0).name("angel").tracker(80, 3, false).build();
		public static final EntityEntry WEEPING_ANGEL_PAINTING = EntityEntryBuilder.create().entity(EntityAngelPainting.class).id(new ResourceLocation(WeepingAngels.MODID, "weepingAngelpainting"), 1).name("weepingAngelpainting").tracker(80, Integer.MAX_VALUE, false).build();
		public static final EntityEntry CHRONODYNE_GENERATOR = EntityEntryBuilder.create().entity(EntityChronodyneGenerator.class).id(new ResourceLocation(WeepingAngels.MODID, "chronodyne_generator"), 2).name("chronodyne_generator").tracker(80, 3, true).build();
		public static final EntityEntry ANOMALY = EntityEntryBuilder.create().entity(EntityAnomaly.class).id(new ResourceLocation(WeepingAngels.MODID, "anomaly"), 3).name("anomaly").tracker(80, 3, true).build();

    }
}
