package me.swirtzly.angels.common;

import me.swirtzly.angels.WeepingAngels;
import me.swirtzly.angels.common.blocks.BlockAngelStatue;
import me.swirtzly.angels.common.blocks.BlockChronodyneGenerator;
import me.swirtzly.angels.common.blocks.BlockMineable;
import me.swirtzly.angels.common.blocks.BlockSnowArm;
import me.swirtzly.angels.common.entities.AngelEnums;
import me.swirtzly.angels.common.entities.EntityAnomaly;
import me.swirtzly.angels.common.entities.EntityWeepingAngel;
import me.swirtzly.angels.common.items.ItemAngelSpawner;
import me.swirtzly.angels.common.items.ItemChronodyneGenerator;
import me.swirtzly.angels.common.items.ItemDetector;
import me.swirtzly.angels.common.items.ItemHanging;
import me.swirtzly.angels.common.misc.WATabs;
import me.swirtzly.angels.common.tileentities.TileEntityChronodyneGenerator;
import me.swirtzly.angels.common.tileentities.TileEntityPlinth;
import me.swirtzly.angels.common.tileentities.TileEntitySnowArm;
import me.swirtzly.angels.utils.AngelUtils;
import me.swirtzly.angels.utils.WADamageSource;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static me.swirtzly.angels.WeepingAngels.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WAObjects {
	
	public static DamageSource ANGEL = new WADamageSource("backintime"),
			STONE = new WADamageSource("punch_stone"),
			ANGEL_NECK_SNAP = new WADamageSource("neck_snap");
	
	public static List<Item> ITEMS = new ArrayList<>();
	// Helper, gets reset after init
	private static List<Item> ITEM_BLOCKS = new ArrayList<>();
	
	@SubscribeEvent
	public static void addItems(RegistryEvent.Register<Item> e) {
		IForgeRegistry<Item> reg = e.getRegistry();
		ITEM_BLOCKS.forEach(reg::register);
		reg.registerAll(
				setUpItem(new ItemHanging(), "angel_painting"),
				setUpItem(new ItemDetector(), "timey_wimey_detector"),
				setUpItem(new ItemChronodyneGenerator(), "chronodyne_generator"),
				setUpItem(new ItemAngelSpawner<>(AngelEnums.AngelType.ANGEL_ONE, EntityWeepingAngel::new), "angel_0"),
				setUpItem(new ItemAngelSpawner<>(AngelEnums.AngelType.ANGEL_TWO, EntityWeepingAngel::new), "angel_1"),
				setUpItem(new ItemAngelSpawner<>(AngelEnums.AngelType.ANGEL_CHILD, EntityWeepingAngel::new), "angel_child"),
				setUpItem(new Item(new Item.Properties().group(WATabs.MAIN_TAB)), "kontron_ingot"),
				setUpItem(new ItemAngelSpawner<>(AngelEnums.AngelType.ANGEL_THREE, EntityWeepingAngel::new), "angel_2"),
				setUpItem(new ItemAngelSpawner<>(AngelEnums.AngelType.ANGEL_FOUR, EntityWeepingAngel::new), "angel_3")
		);
	}
	
	@SubscribeEvent
	public static void addBlocks(RegistryEvent.Register<Block> e) {
		IForgeRegistry<Block> reg = e.getRegistry();
		registerBlocks(reg, setUpBlock(new BlockSnowArm(), "arm"), setUpBlock(new BlockAngelStatue(), "plinth"), setUpBlock(new BlockMineable(() -> new ItemStack(Items.KONTRON_INGOT), 2, 1), "kontron_ore"));
		reg.register(setUpBlock(new BlockChronodyneGenerator(), "cg"));
	}
	
	@SubscribeEvent
	public static void regTiles(RegistryEvent.Register<TileEntityType<?>> e) {
		IForgeRegistry<TileEntityType<?>> reg = e.getRegistry();
		reg.registerAll(Tiles.ARM, Tiles.CG, Tiles.PLINTH);
	}
	
	@SubscribeEvent
	public static void addEntities(RegistryEvent.Register<EntityType<?>> e) {
		IForgeRegistry<EntityType<?>> reg = e.getRegistry();
		reg.registerAll(EntityEntries.WEEPING_ANGEL, EntityEntries.ANOMALY);
		AngelUtils.setUpSpawns();
	}
	
	@SubscribeEvent
	public static void addSounds(RegistryEvent.Register<SoundEvent> e) {
		IForgeRegistry<SoundEvent> reg = e.getRegistry();
		reg.registerAll(setUpSound("angel_seen_1"), setUpSound("angel_seen_2"), setUpSound("angel_seen_3"), setUpSound("angel_seen_4"), setUpSound("angel_seen_5"), setUpSound("angel_seen_6"), setUpSound("angel_seen_7"), setUpSound("angel_seen_8"), setUpSound("stone_scrap"), setUpSound("child_run"), setUpSound("laughing_child"), setUpSound("light_break"), setUpSound("angel_teleport"), setUpSound("angel_ambient"), setUpSound("ding"), setUpSound("blow"), setUpSound("angel_death"), setUpSound("angel_neck_snap"));
	}
	
	private static SoundEvent setUpSound(String soundName) {
		return new SoundEvent(new ResourceLocation(MODID, soundName)).setRegistryName(soundName);
	}
	
	private static Item setUpItem(Item item, String name) {
		item.setRegistryName(MODID, name);
		WAObjects.ITEMS.add(item);
		return item;
	}
	
	private static Block setUpBlock(Block block, String name) {
		block.setRegistryName(MODID, name);
		return block;
	}
	
	private static void registerBlocks(IForgeRegistry<Block> reg, Block... blocks) {
		reg.registerAll(blocks);
		for (Block block : blocks) {
			ITEM_BLOCKS.add(new BlockItem(block, new Item.Properties().group(WATabs.MAIN_TAB)).setRegistryName(block.getRegistryName()));
		}
	}
	
	public static class Tiles {
		public static TileEntityType<?> ARM = register(TileEntitySnowArm::new, "snow_arm", Blocks.ARM);
		public static TileEntityType<?> CG = register(TileEntityChronodyneGenerator::new, "cg", Blocks.CG);
		public static TileEntityType<?> PLINTH = register(TileEntityPlinth::new, "plinth", Blocks.PLINTH);
	}
	
	@ObjectHolder(MODID)
	public static class Blocks {
		public static final Block ARM = null;
		public static final Block CG = null;
		public static final Block PLINTH = null;
		public static final Block KONTRON_ORE = null;
	}
	
	@ObjectHolder(MODID)
	public static class Items {
		public static final Item ANGEL_PAINTING = null;
		public static final Item TIMEY_WIMEY_DETECTOR = null;
		public static final Item CHRONODYNE_GENERATOR = null;
		public static final Item ANGEL_0 = null;
		public static final Item ANGEL_1 = null;
		public static final Item ANGEL_2 = null;
		public static final Item ANGEL_3 = null;
		public static final Item ANGEL_CHILD = null;
		public static final Item KONTRON_INGOT = null;
	}
	
	// Sounds
	@ObjectHolder(MODID)
	public static class Sounds {
		public static final SoundEvent ANGEL_SEEN_1 = null;
		public static final SoundEvent ANGEL_SEEN_2 = null;
		public static final SoundEvent ANGEL_SEEN_3 = null;
		public static final SoundEvent ANGEL_SEEN_4 = null;
		public static final SoundEvent ANGEL_SEEN_5 = null;
		public static final SoundEvent ANGEL_SEEN_6 = null;
		public static final SoundEvent ANGEL_SEEN_7 = null;
		public static final SoundEvent ANGEL_SEEN_8 = null;
		public static final SoundEvent STONE_SCRAP = null;
		public static final SoundEvent CHILD_RUN = null;
		public static final SoundEvent LAUGHING_CHILD = null;
		public static final SoundEvent LIGHT_BREAK = null;
		public static final SoundEvent ANGEL_TELEPORT = null;
		public static final SoundEvent ANGEL_AMBIENT = null;
		public static final SoundEvent DING = null;
		public static final SoundEvent BLOW = null;
		public static final SoundEvent ANGEL_DEATH = null;
		public static final SoundEvent ANGEL_NECK_SNAP = null;
	}
	
	// Entities
	@ObjectHolder(MODID)
	public static class EntityEntries {

		public static final EntityType<EntityWeepingAngel> WEEPING_ANGEL = registerFireResistMob(EntityWeepingAngel::new, EntityWeepingAngel::new, EntityClassification.MONSTER, 1F, 1.75F, "weeping_angel", false);
		public static final EntityType<EntityAnomaly> ANOMALY = registerMob(EntityAnomaly::new, EntityAnomaly::new, EntityClassification.MONSTER, 1F, 1.75F, "anomaly", false);

		//	public static final EntityEntry CHRONODYNE_GENERATOR = EntityEntryBuilder.create().entity(EntityChronodyneGenerator.class).id(new ResourceLocation(MODID, "chronodyne_generator"), 2).name("chronodyne_generator").tracker(80, 3, true).build();
	}


	public static List<TileEntityType<?>> TYPES = new ArrayList<TileEntityType<?>>();

	@SubscribeEvent
	public static void register(RegistryEvent.Register<TileEntityType<?>> event) {
		for(TileEntityType<?> type : TYPES) {
			event.getRegistry().register(type);
		}
	}


	//Tile Creation
	public static <T extends TileEntity> TileEntityType<T> register(Supplier<T> tile, String name, Block... validBlock) {
		TileEntityType<T> type = TileEntityType.Builder.create(tile, validBlock).build(null);
		type.setRegistryName(WeepingAngels.MODID, name);
		TYPES.add(type);
		return type;
	}



	//Entity Creation
	public static <T extends Entity> EntityType<T> registerBase(EntityType.IFactory<T> factory, IClientSpawner<T> client, EntityClassification classification, float width, float height, int trackingRange, int updateFreq, boolean sendUpdate, String name){
		ResourceLocation loc = new ResourceLocation(WeepingAngels.MODID, name);
		EntityType.Builder<T> builder = EntityType.Builder.create(factory, classification);
		builder.setShouldReceiveVelocityUpdates(sendUpdate);
		builder.setTrackingRange(trackingRange);
		builder.setUpdateInterval(updateFreq);
		builder.size(width, height);
		builder.setCustomClientFactory((spawnEntity, world) -> client.spawn(world));
		EntityType<T> type = builder.build(loc.toString());
		type.setRegistryName(loc);
		return type;
	}
	//Fire Resistant Entity Creation
	public static <T extends Entity> EntityType<T> registerFireImmuneBase(EntityType.IFactory<T> factory, IClientSpawner<T> client, EntityClassification classification, float width, float height, int trackingRange, int updateFreq, boolean sendUpdate,String name){
		ResourceLocation loc = new ResourceLocation(WeepingAngels.MODID, name);
		EntityType.Builder<T> builder = EntityType.Builder.create(factory, classification);
		builder.setShouldReceiveVelocityUpdates(sendUpdate);
		builder.setTrackingRange(trackingRange);
		builder.setUpdateInterval(updateFreq);
		builder.immuneToFire();
		builder.size(width, height);
		builder.setCustomClientFactory((spawnEntity, world) -> client.spawn(world));
		EntityType<T> type = builder.build(loc.toString());
		type.setRegistryName(loc);
		return type;
	}

	public static <T extends Entity> EntityType<T> registerStatic(EntityType.IFactory<T> factory, IClientSpawner<T> client, EntityClassification classification, float width, float height, String name){
		return registerBase(factory, client, classification, width, height, 64, 40, false, name);
	}

	public static <T extends Entity> EntityType<T> registerMob(EntityType.IFactory<T> factory, IClientSpawner<T> client, EntityClassification classification, float width, float height, String name, boolean velocity) {
		return registerBase(factory, client, classification, width, height, 80, 3, velocity, name);
	}
	/**Fire Resistant Variant of entity
	 * 
	 * @param <T>
	 * @param factory
	 * @param client
	 * @param classification
	 * @param width
	 * @param height
	 * @param name
	 * @param velocity
	 * @param trackingRange
	 * @param updateFreq
	 * @return
	 */
	public static <T extends Entity> EntityType<T> registerFireResistMob(EntityType.IFactory<T> factory, IClientSpawner<T> client, EntityClassification classification, float width, float height, String name, boolean velocity) {
		return registerFireImmuneBase(factory, client, classification,width, height,80, 3, velocity, name);
	}

	public interface IClientSpawner<T> {
		T spawn(World world);
	}


}
