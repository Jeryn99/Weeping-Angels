package me.sub.angels.common;

import com.google.common.collect.Lists;
import me.sub.angels.client.TabAngels;
import me.sub.angels.client.models.entity.ModelAngelEd;
import me.sub.angels.client.models.item.ModelDetector;
import me.sub.angels.client.models.item.RenderItemStackBase;
import me.sub.angels.client.render.entity.RenderAngel;
import me.sub.angels.client.render.entity.RenderAngelPainting;
import me.sub.angels.client.render.entity.RenderAnomaly;
import me.sub.angels.client.render.entity.RenderCG;
import me.sub.angels.client.render.tileentity.RenderTileEntityCG;
import me.sub.angels.client.render.tileentity.RenderTileEntityPlinth;
import me.sub.angels.client.render.tileentity.RenderTileEntitySnowArm;
import me.sub.angels.common.blocks.BlockAngelStatue;
import me.sub.angels.common.blocks.BlockCG;
import me.sub.angels.common.blocks.BlockSnowArm;
import me.sub.angels.common.entities.EntityAngel;
import me.sub.angels.common.entities.EntityAngelPainting;
import me.sub.angels.common.entities.EntityAnomaly;
import me.sub.angels.common.entities.EntityChronodyneGenerator;
import me.sub.angels.common.items.ItemAngelSpawner;
import me.sub.angels.common.items.ItemChronodyneGenerator;
import me.sub.angels.common.items.ItemDetector;
import me.sub.angels.common.items.ItemHanging;
import me.sub.angels.common.tiles.TileCG;
import me.sub.angels.common.tiles.TileEntityPlinth;
import me.sub.angels.common.tiles.TileSnowArm;
import me.sub.angels.main.WeepingAngels;
import me.sub.angels.main.config.WAConfig;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Mod.EventBusSubscriber(modid = WeepingAngels.MODID)
public class WAObjects {
	
	public static CreativeTabs angelTab = new TabAngels();
	
	/**
     * Set up the rendering for entities and tileentities
	 */
	@SideOnly(Side.CLIENT)
    public static void setUpRenders() {

        //Entities
        RenderingRegistry.registerEntityRenderingHandler(EntityAngel.class, manager -> new RenderAngel(new ModelAngelEd()));
        RenderingRegistry.registerEntityRenderingHandler(EntityAngelPainting.class, manager -> new RenderAngelPainting());
        RenderingRegistry.registerEntityRenderingHandler(EntityAnomaly.class, manager -> new RenderAnomaly());

        //Items
        WAItems.TIMEY_WIMEY_DETECTOR.setTileEntityItemStackRenderer(new RenderItemStackBase(new ModelDetector()));

		// Projectiles
		RenderingRegistry.registerEntityRenderingHandler(EntityChronodyneGenerator.class, manager -> new RenderCG());

        //TESRS
        ClientRegistry.bindTileEntitySpecialRenderer(TileSnowArm.class, new RenderTileEntitySnowArm());
        ClientRegistry.bindTileEntitySpecialRenderer(TileCG.class, new RenderTileEntityCG());
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
		spawn.remove(Biomes.SKY);
		spawn.remove(Biomes.VOID);
		spawn.remove(Biomes.HELL);
		spawn.remove(Biomes.DEEP_OCEAN);
		spawn.remove(Biomes.OCEAN);
		for (Biome biome : spawn) {
			if (biome != null) {
				EntityRegistry.addSpawn(EntityAngel.class, WAConfig.spawn.spawnProbability, WAConfig.spawn.minimumSpawn, WAConfig.spawn.maximumSpawn, EnumCreatureType.valueOf(WAConfig.spawn.spawnType), biome);
			}
		}
	}

    // Sounds
    public static class Sounds {
        public static SoundEvent ANGEL_SEEN = setUpSound("angel_seen_1");
        public static SoundEvent ANGEL_SEEN_2 = setUpSound("angel_seen_2");
        public static SoundEvent ANGEL_SEEN_3 = setUpSound("angel_seen_3");
        public static SoundEvent ANGEL_SEEN_4 = setUpSound("angel_seen_4");
        public static SoundEvent ANGEL_SEEN_5 = setUpSound("angel_seen_5");
        public static SoundEvent STONE_SCRAP = setUpSound("stone_scrap");
        public static SoundEvent CHILD_RUNNING = setUpSound("child_run");
        public static SoundEvent CHILD_LAUGHING = setUpSound("laughing_child");
        public static SoundEvent ANGEL_LIGHT_BREAK = setUpSound("light_break");
        public static SoundEvent ANGEL_TELEPORT = setUpSound("angel_teleport");
        public static SoundEvent ANGEL_SPAWN = setUpSound("angel_ambient");
        public static SoundEvent ITEM_DING = setUpSound("ding");
        public static SoundEvent CHILD_BLOW = setUpSound("blow");
        public static SoundEvent ANGEL_DEATH = setUpSound("angel_death");
    }

    // Entities
    public static class EntityEntries {
        public static final EntityEntry WEEPING_ANGEL = EntityEntryBuilder.create().entity(EntityAngel.class).id(new ResourceLocation(WeepingAngels.MODID, "weepingangel"), 0).name("angel").tracker(80, 3, false).build();
        public static final EntityEntry WEEPING_ANGEL_PAINTING = EntityEntryBuilder.create().entity(EntityAngelPainting.class).id(new ResourceLocation(WeepingAngels.MODID, "weepingAngelpainting"), 1).name("weepingAngelpainting").tracker(80, Integer.MAX_VALUE, false).build();
        public static final EntityEntry CHRONODYNE_GENERATOR = EntityEntryBuilder.create().entity(EntityChronodyneGenerator.class).id(new ResourceLocation(WeepingAngels.MODID, "chronodyne_generator"), 2).name("chronodyne_generator").tracker(80, 3, true).build();
        public static final EntityEntry ANOMALY = EntityEntryBuilder.create().entity(EntityAnomaly.class).id(new ResourceLocation(WeepingAngels.MODID, "anomaly"), 3).name("anomaly").tracker(80, 3, true).build();

    }
	
	// Blocks
	public static class WABlocks {
        public static Block ANGEL_ARM = new BlockSnowArm().setCreativeTab(angelTab);
        public static Block CG = new BlockCG().setCreativeTab(angelTab);
        public static Block PLINTH = new BlockAngelStatue().setCreativeTab(angelTab);
    }

	// Items
	public static class WAItems {
        public static Item ANGEL_PAINTING = registerItem(new ItemHanging(EntityPainting::new), "angel_painting").setCreativeTab(angelTab);
        public static Item ANGEL_ARM = registerItem(new ItemBlock(WABlocks.ANGEL_ARM), "arm").setCreativeTab(angelTab);
        public static Item UNLIT_TORCH = registerItem(new Item(), "unlit_torch");
        public static Item TIMEY_WIMEY_DETECTOR = registerItem(new ItemDetector(), "timey_wimey_detector").setCreativeTab(angelTab);
        public static Item CHRONODYNE_GENERATOR = registerItem(new ItemChronodyneGenerator(), "chronodyne_generator");
        public static Item PLINTH = registerItem(new ItemBlock(WABlocks.PLINTH), "plinth").setCreativeTab(angelTab);
        public static Item SPAWNER_ANGEL_0 = registerItem(new ItemAngelSpawner<>(0, EntityAngel::new), "angel_0");
        public static Item SPAWNER_ANGEL_1 = registerItem(new ItemAngelSpawner<>(1, EntityAngel::new), "angel_1");
        public static Item SPAWNER_ANGEL_CHILD = registerItem(new ItemAngelSpawner<>(0, EntityAngel::new, true), "angel_child");
	}
	
	private static Item registerItem(Item item, String name) {
		item.setRegistryName(WeepingAngels.MODID, name);
		item.setUnlocalizedName(name);
		return item;
	}
	
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent ev) {
		
		// Items
		for (Field f : WAItems.class.getDeclaredFields()) {
			try {
				Item item = (Item) f.get(null);
				ModelResourceLocation loc = new ModelResourceLocation(item.getRegistryName(), "inventory");
				ModelLoader.setCustomModelResourceLocation(item, 0, loc);
			} catch (IllegalAccessException | ClassCastException e) {
				throw new RuntimeException("Incorrect field in item sub-class", e);
			}
		}
		
		// Blocks
		for (Field f : WABlocks.class.getDeclaredFields()) {
			try {
				Block block = (Block) f.get(null);
				Item item = Item.getItemFromBlock(block);
				ModelResourceLocation loc = new ModelResourceLocation(item.getRegistryName(), "inventory");
				ModelLoader.setCustomModelResourceLocation(item, 0, loc);
			} catch (IllegalAccessException | ClassCastException e) {
				throw new RuntimeException("Incorrect field in item sub-class", e);
			}
		}
	}
	
	@SubscribeEvent
	public static void registerObjects(RegistryEvent ev) {
		if (!(ev instanceof RegistryEvent.Register)) return;
		IForgeRegistry registry = ((RegistryEvent.Register) ev).getRegistry();
		
		for (Class<?> aClass : WAObjects.class.getDeclaredClasses()) {
			if (Arrays.stream(aClass.getDeclaredFields()).noneMatch(field -> registry.getRegistrySuperType().isAssignableFrom(field.getType()))) continue;
			ArrayList<IForgeRegistryEntry> entries = new ArrayList<>();
			
			for (Field field : aClass.getDeclaredFields())
				try {
					entries.add((IForgeRegistryEntry) field.get(null));
				} catch (IllegalAccessException | ClassCastException e) {
					throw new RuntimeException("Incorrect field in object sub-class", e);
				}
			
			if (Arrays.stream(aClass.getDeclaredFields()).anyMatch(field -> Items.class.isAssignableFrom(field.getType()))) {
				for (Field f : Blocks.class.getDeclaredFields()) {
					try {
						Block block = (Block) f.get(null);
						entries.add(new ItemBlock(block).setRegistryName(block.getRegistryName()).setUnlocalizedName(block.getUnlocalizedName()));
					} catch (IllegalAccessException | ClassCastException e) {
						throw new RuntimeException("Incorrect field in object sub-class", e);
					}
				}
			}
			
			entries.forEach(registry::register);
		}
	}
	
	// Damage Sources
	public static DamageSource ANGEL = new WADamageSource("backintime");
	public static DamageSource STONE = new WADamageSource("punch_stone");
	public static DamageSource ANGEL_NECK_SNAP = new WADamageSource("neck_snap");
	
}
