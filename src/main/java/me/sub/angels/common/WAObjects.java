package me.sub.angels.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;

import me.sub.angels.client.TabAngels;
import me.sub.angels.client.models.entity.ModelAngelEd;
import me.sub.angels.client.render.entity.RenderAngel;
import me.sub.angels.client.render.entity.RenderAngelPainting;
import me.sub.angels.client.render.entity.RenderCG;
import me.sub.angels.client.render.tiles.RenderSnowArm;
import me.sub.angels.client.render.tiles.RenderTileCG;
import me.sub.angels.common.blocks.BlockCG;
import me.sub.angels.common.blocks.BlockSnowArm;
import me.sub.angels.common.entities.EntityAngel;
import me.sub.angels.common.entities.EntityAngelPainting;
import me.sub.angels.common.entities.EntityChronodyneGenerator;
import me.sub.angels.common.items.ItemChronodyneGenerator;
import me.sub.angels.common.items.ItemDetector;
import me.sub.angels.common.items.ItemHanging;
import me.sub.angels.common.tiles.TileCG;
import me.sub.angels.common.tiles.TileSnowArm;
import me.sub.angels.main.WeepingAngels;
import me.sub.angels.main.config.WAConfig;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
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

@Mod.EventBusSubscriber(modid = WeepingAngels.MODID)
public class WAObjects {
	
	public static CreativeTabs angelTab = new TabAngels();
	
	// Sounds
	public static class Sounds {
		public static SoundEvent angelSeen = setUpSound("angel_seen_1");
		public static SoundEvent angelSeen_2 = setUpSound("angel_seen_2");
		public static SoundEvent angelSeen_3 = setUpSound("angel_seen_3");
		public static SoundEvent angelSeen_4 = setUpSound("angel_seen_4");
		public static SoundEvent angelSeen_5 = setUpSound("angel_seen_5");
		public static SoundEvent stone_scrap = setUpSound("stone_scrap");
		public static SoundEvent child_run = setUpSound("child_run");
		public static SoundEvent laughing_child = setUpSound("laughing_child");
		public static SoundEvent light_break = setUpSound("light_break");
		public static SoundEvent angel_teleport = setUpSound("angel_teleport");
		public static SoundEvent angel_ambience = setUpSound("angel_ambient");
		public static SoundEvent ding = setUpSound("ding");
		public static SoundEvent blow = setUpSound("blow");
		public static SoundEvent angelDeath = setUpSound("angel_death");
	}
	
	private static SoundEvent setUpSound(String soundName) {
		SoundEvent sound = new SoundEvent(new ResourceLocation(WeepingAngels.MODID + ":" + soundName)).setRegistryName(soundName);
		return sound;
	}
	
	public static class EntityEntries {
		public static final EntityEntry weepingAngel = EntityEntryBuilder.create().entity(EntityAngel.class).id(new ResourceLocation(WeepingAngels.MODID, "weepingangel"), 0).egg(184, 286).name("angel").tracker(80, 3, false).build();
		public static final EntityEntry weepingAngelPainting = EntityEntryBuilder.create().entity(EntityAngelPainting.class).id(new ResourceLocation(WeepingAngels.MODID, "weepingAngelpainting"), 1).name("weepingAngelpainting").tracker(80, Integer.MAX_VALUE, false).build();
		public static final EntityEntry chronodyne_generator = EntityEntryBuilder.create().entity(EntityChronodyneGenerator.class).id(new ResourceLocation(WeepingAngels.MODID, "chronodyne_generator"), 2).name("chronodyne_generator").tracker(80, 3, true).build();
	}
	
	@SideOnly(Side.CLIENT)
	public static void setUpRenders() {
		RenderingRegistry.registerEntityRenderingHandler(EntityAngel.class, manager -> new RenderAngel(manager, new ModelAngelEd()));
		RenderingRegistry.registerEntityRenderingHandler(EntityAngelPainting.class, manager -> new RenderAngelPainting(manager));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityChronodyneGenerator.class, new RenderCG());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileSnowArm.class, new RenderSnowArm());
		ClientRegistry.bindTileEntitySpecialRenderer(TileCG.class, new RenderTileCG());
	}
	
	public static void setUpSpawns() {
		List<Biome> biomes = ForgeRegistries.BIOMES.getValues();
		List<Biome> spawn = Lists.newArrayList();
		spawn.addAll(biomes);
		spawn.remove(Biomes.SKY);
		spawn.remove(Biomes.VOID);
		spawn.remove(Biomes.HELL);
		spawn.remove(Biomes.DEEP_OCEAN);
		spawn.remove(Biomes.OCEAN);
		Iterator<Biome> iterator = spawn.iterator();
		while (iterator.hasNext()) {
			Biome biome = iterator.next();
			if (biome != null) {
				EntityRegistry.addSpawn(EntityAngel.class, WAConfig.spawn.spawnProbability, WAConfig.spawn.minimumSpawn, WAConfig.spawn.maximumSpawn, EnumCreatureType.MONSTER, biome);
			}
		}
	}
	
	// Blocks
	public static class WABlocks {
		public static Block angelArm = new BlockSnowArm().setCreativeTab(angelTab);
		public static Block cg = new BlockCG().setCreativeTab(angelTab);
	}
	
	// Items
	public static class WAItems {
		public static Item angelPainting = createItem(new ItemHanging(), "angel_painting").setCreativeTab(angelTab);
		public static Item angelArmItem = createItem(new ItemBlock(WABlocks.angelArm), "arm").setCreativeTab(angelTab);
		public static Item unLitTorch = createItem(new Item(), "unlit_torch");
		public static Item timeyWimeyDetector = createItem(new ItemDetector(), "timey_wimey_detector").setCreativeTab(angelTab);
		
		public static Item chronodyneGenerator = createItem(new ItemChronodyneGenerator(), "chronodyne_generator");
	}
	
	private static Item createItem(Item item, String name) {
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
		
		// Tiles + entities
		setUpRenders();
		
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
