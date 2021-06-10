package me.suff.mc.angels.common;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.blocks.*;
import me.suff.mc.angels.common.entities.AnomalyEntity;
import me.suff.mc.angels.common.entities.ChronodyneGeneratorEntity;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import me.suff.mc.angels.common.items.AngelSpawnerItem;
import me.suff.mc.angels.common.items.ChiselItem;
import me.suff.mc.angels.common.items.ChronodyneGeneratorItem;
import me.suff.mc.angels.common.items.DetectorItem;
import me.suff.mc.angels.common.misc.WATabs;
import me.suff.mc.angels.common.tileentities.CoffinTile;
import me.suff.mc.angels.common.tileentities.PlinthTile;
import me.suff.mc.angels.common.tileentities.SnowArmTile;
import me.suff.mc.angels.common.tileentities.StatueTile;
import me.suff.mc.angels.common.world.SnowAngelGeneration;
import me.suff.mc.angels.common.world.structures.CatacombStructure;
import me.suff.mc.angels.common.world.structures.GraveyardStructure;
import me.suff.mc.angels.common.world.structures.GraveyardStructurePieces;
import me.suff.mc.angels.utils.WADamageSource;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.World;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collection;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = WeepingAngels.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WAObjects {

    public static DamageSource ANGEL = new WADamageSource("backintime"), GENERATOR = new WADamageSource("generator"), STONE = new WADamageSource("punch_stone"), ANGEL_NECK_SNAP = new WADamageSource("neck_snap");

    public static ResourceLocation CRYPT_LOOT = new ResourceLocation(WeepingAngels.MODID, "chests/crypt");

    private static Item setUpItem(Item item) {
        return item;
    }

    private static Block setUpBlock(Block block) {
        return block;
    }

    private static void genBlockItems(Block... blocks) {
        for (Block block : blocks) {
            Blocks.BLOCK_ITEMS.register(block.getRegistryName().getPath(), () -> setUpItem(new BlockItem(block, new Item.Properties().tab(WATabs.MAIN_TAB))));
        }
    }

    @SuppressWarnings("unused")
    private static void genBlockItems(Collection<RegistryObject<Block>> collection) {
        for (RegistryObject<Block> block : collection) {
            ItemGroup itemGroup = WATabs.MAIN_TAB;
            Blocks.BLOCK_ITEMS.register(block.get().getRegistryName().getPath(), () -> setUpItem(new BlockItem(block.get(), new Item.Properties().tab(itemGroup))));
        }
    }

    private static SoundEvent setUpSound(String soundName) {
        return new SoundEvent(new ResourceLocation(WeepingAngels.MODID, soundName));
    }

    @SubscribeEvent
    public static void regBlockItems(RegistryEvent.Register<Item> e) {
        genBlockItems(Blocks.COFFIN.get(), Blocks.SNOW_ANGEL.get(), Blocks.KONTRON_ORE.get(), Blocks.PLINTH.get(), Blocks.STATUE.get());
    }

    /**
     * Setup the structure and add the rarity settings.
     * <br> Call this in CommonSetup in a deferred work task to reduce concurrent modification issues as we are modifying multiple maps we ATed
     */
    public static void setupStructures() {
        setupStructure(Structures.GRAVEYARD.get(), new StructureSeparationSettings(200, 100, 1234567890), true); //Maximum of 200 chunks apart, minimum 100 chunks apart, chunk seed respectively
        setupStructure(Structures.CATACOMBS.get(), new StructureSeparationSettings(300, 100, 234567890), false); //Maximum of 200 chunks apart, minimum 100 chunks apart, chunk seed respectively
    }

    private static <T extends Structure<?>> void registerConfiguredStructure(String registryName, Supplier<T> structure, StructureFeature<?, ?> configuredStructure) {
        Registry<StructureFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;
        Registry.register(registry, new ResourceLocation(WeepingAngels.MODID, registryName), configuredStructure);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(structure.get(), configuredStructure);
    }

    private static <T extends Structure<?>> RegistryObject<T> setupStructure(String name, Supplier<T> structure) {
        return Structures.STRUCTURES.register(name, structure);
    }

    public static <F extends Structure<?>> void setupStructure(F structure, StructureSeparationSettings structureSeparationSettings, boolean transformSurroundingLand) {
        Structure.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);
        if (transformSurroundingLand) {
            Structure.NOISE_AFFECTING_FEATURES = ImmutableList.<Structure<?>>builder().addAll(Structure.NOISE_AFFECTING_FEATURES).add(structure).build();
        }
        DimensionStructuresSettings.DEFAULTS = ImmutableMap.<Structure<?>, StructureSeparationSettings>builder().putAll(DimensionStructuresSettings.DEFAULTS).put(structure, structureSeparationSettings).build();
    }

    public static IStructurePieceType registerStructurePiece(IStructurePieceType type, String key) {
        return Registry.register(Registry.STRUCTURE_PIECE, new ResourceLocation(WeepingAngels.MODID, key), type);
    }

    private static <T extends Feature<?>> void registerConfiguredFeature(String registryName, ConfiguredFeature<?, ?> configuredFeature) {
        Registry<ConfiguredFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_FEATURE;
        Registry.register(registry, new ResourceLocation(WeepingAngels.MODID, registryName), configuredFeature);
    }

    // Tile Creation
    private static <T extends TileEntity> TileEntityType<T> registerTiles(Supplier<T> tile, Block... validBlock) {
        return TileEntityType.Builder.of(tile, validBlock).build(null);
    }

    // Entity Creation
    private static <T extends Entity> EntityType<T> registerBase(EntityType.IFactory<T> factory, IClientSpawner<T> client, EntityClassification classification, float width, float height, int trackingRange, int updateFreq, boolean sendUpdate, String name) {
        ResourceLocation loc = new ResourceLocation(WeepingAngels.MODID, name);
        EntityType.Builder<T> builder = EntityType.Builder.of(factory, classification);
        builder.setShouldReceiveVelocityUpdates(sendUpdate);
        builder.setTrackingRange(trackingRange);
        builder.setUpdateInterval(updateFreq);
        builder.sized(width, height);
        builder.setCustomClientFactory((spawnEntity, world) -> client.spawn(world));
        return builder.build(loc.toString());
    }

    // Fire Resistant Entity Creation
    private static <T extends Entity> EntityType<T> registerFireImmuneBase(EntityType.IFactory<T> factory, IClientSpawner<T> client, EntityClassification classification, float width, float height, int trackingRange, int updateFreq, boolean sendUpdate, String name) {
        ResourceLocation loc = new ResourceLocation(WeepingAngels.MODID, name);
        EntityType.Builder<T> builder = EntityType.Builder.of(factory, classification);
        builder.setShouldReceiveVelocityUpdates(sendUpdate);
        builder.setTrackingRange(trackingRange);
        builder.setUpdateInterval(updateFreq);
        builder.fireImmune();
        builder.sized(width, height);
        builder.setCustomClientFactory((spawnEntity, world) -> client.spawn(world));
        return builder.build(loc.toString());
    }

    private static <T extends Entity> EntityType<T> registerFireResistMob(EntityType.IFactory<T> factory, IClientSpawner<T> client, EntityClassification classification, float width, float height, String name, boolean velocity) {
        return registerFireImmuneBase(factory, client, classification, width, height, 80, 3, velocity, name);
    }

    public static <T extends Entity> EntityType<T> registerStatic(EntityType.IFactory<T> factory, IClientSpawner<T> client, EntityClassification classification, float width, float height, String name) {
        return registerBase(factory, client, classification, width, height, 64, 40, false, name);
    }

    public static <T extends Entity> EntityType<T> registerMob(EntityType.IFactory<T> factory, IClientSpawner<T> client, EntityClassification classification, float width, float height, String name, boolean velocity) {
        return registerBase(factory, client, classification, width, height, 80, 3, velocity, name);
    }

    public interface IClientSpawner<T> {
        T spawn(World world);
    }

    public static class Tiles {
        public static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, WeepingAngels.MODID);

        public static RegistryObject<TileEntityType<SnowArmTile>> SNOW_ANGEL = TILES.register("snow_angel", () -> registerTiles(SnowArmTile::new, Blocks.SNOW_ANGEL.get()));
        public static RegistryObject<TileEntityType<PlinthTile>> PLINTH = TILES.register("plinth", () -> registerTiles(PlinthTile::new, Blocks.PLINTH.get()));
        public static RegistryObject<TileEntityType<StatueTile>> STATUE = TILES.register("statue", () -> registerTiles(StatueTile::new, Blocks.STATUE.get()));
        public static RegistryObject<TileEntityType<CoffinTile>> COFFIN = TILES.register("coffin", () -> registerTiles(CoffinTile::new, Blocks.COFFIN.get()));
    }

    public static class Blocks {
        public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WeepingAngels.MODID);
        public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WeepingAngels.MODID);

        public static final RegistryObject<Block> SNOW_ANGEL = BLOCKS.register("snow_angel", () -> setUpBlock(new SnowArmBlock()));
        public static final RegistryObject<Block> CHRONODYNE_GENERATOR = BLOCKS.register("chronodyne_generator", () -> setUpBlock(new ChronodyneGeneratorBlock()));
        public static final RegistryObject<Block> PLINTH = BLOCKS.register("plinth", () -> setUpBlock(new PlinthBlock()));
        public static final RegistryObject<Block> KONTRON_ORE = BLOCKS.register("kontron_ore", () -> setUpBlock(new MineableBlock(null)));
        public static final RegistryObject<Block> STATUE = BLOCKS.register("statue", () -> setUpBlock(new StatueBlock()));
        public static final RegistryObject<Block> COFFIN = BLOCKS.register("coffin", () -> setUpBlock(new CoffinBlock(AbstractBlock.Properties.of(Material.WOOD).noOcclusion())));
    }

    public static class Items {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WeepingAngels.MODID);

        public static final RegistryObject<Item> TIMEY_WIMEY_DETECTOR = ITEMS.register("timey_wimey_detector", DetectorItem::new);
        public static final RegistryObject<Item> CHRONODYNE_GENERATOR = ITEMS.register("chronodyne_generator", ChronodyneGeneratorItem::new);
        public static final RegistryObject<Item> ANGEL_SPAWNER = ITEMS.register("weeping_angel", () -> setUpItem(new AngelSpawnerItem<>()));
        public static final RegistryObject<Item> KONTRON_INGOT = ITEMS.register("kontron_ingot", () -> setUpItem(new Item(new Item.Properties().tab(WATabs.MAIN_TAB))));
        public static final RegistryObject<Item> CHISEL = ITEMS.register("chisel", () -> setUpItem(new ChiselItem(new Item.Properties().stacksTo(1).tab(WATabs.MAIN_TAB))));
        public static final RegistryObject<Item> SALLY = ITEMS.register("music_disc_sally", () -> setUpItem(new MusicDiscItem(6, Sounds.DISC_SALLY::get, (new Item.Properties()).stacksTo(1).tab(ItemGroup.TAB_MISC).rarity(Rarity.RARE))));
        public static final RegistryObject<Item> TIME_PREVAILS = ITEMS.register("music_disc_time_prevails", () -> setUpItem(new MusicDiscItem(6, Sounds.DISC_TIME_PREVAILS::get, (new Item.Properties()).stacksTo(1).tab(ItemGroup.TAB_MISC).rarity(Rarity.RARE))));
    }

    // Sounds
    public static class Sounds {
        public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, WeepingAngels.MODID);

        public static final RegistryObject<SoundEvent> ANGEL_SEEN = SOUNDS.register("angel_seen", () -> setUpSound("angel_seen"));
        public static final RegistryObject<SoundEvent> STONE_SCRAP = SOUNDS.register("stone_scrap", () -> setUpSound("stone_scrap"));
        public static final RegistryObject<SoundEvent> CHILD_RUN = SOUNDS.register("child_run", () -> setUpSound("child_run"));
        public static final RegistryObject<SoundEvent> LAUGHING_CHILD = SOUNDS.register("laughing_child", () -> setUpSound("laughing_child"));
        public static final RegistryObject<SoundEvent> LIGHT_BREAK = SOUNDS.register("light_break", () -> setUpSound("light_break"));
        public static final RegistryObject<SoundEvent> ANGEL_AMBIENT = SOUNDS.register("angel_ambient", () -> setUpSound("angel_ambient"));
        public static final RegistryObject<SoundEvent> DING = SOUNDS.register("ding", () -> setUpSound("ding"));
        public static final RegistryObject<SoundEvent> BLOW = SOUNDS.register("blow", () -> setUpSound("blow"));
        public static final RegistryObject<SoundEvent> ANGEL_DEATH = SOUNDS.register("angel_death", () -> setUpSound("angel_death"));
        public static final RegistryObject<SoundEvent> ANGEL_NECK_SNAP = SOUNDS.register("angel_neck_snap", () -> setUpSound("angel_neck_snap"));
        public static final RegistryObject<SoundEvent> PROJECTOR = SOUNDS.register("projector", () -> setUpSound("projector"));
        public static final RegistryObject<SoundEvent> TELEPORT = SOUNDS.register("teleport", () -> setUpSound("teleport"));
        public static final RegistryObject<SoundEvent> ANGEL_MOCKING = SOUNDS.register("angel_mocking", () -> setUpSound("angel_mocking"));
        public static final RegistryObject<SoundEvent> TARDIS_TAKEOFF = SOUNDS.register("tardis_takeoff", () -> setUpSound("tardis_takeoff"));
        public static final RegistryObject<SoundEvent> DISC_SALLY = SOUNDS.register("disc_sally", () -> setUpSound("disc_sally"));
        public static final RegistryObject<SoundEvent> DISC_TIME_PREVAILS = SOUNDS.register("disc_time_prevails", () -> setUpSound("disc_time_prevails"));
        public static final RegistryObject<SoundEvent> CATACOMB = SOUNDS.register("catacomb", () -> setUpSound("catacomb"));
    }

    public static class WorldGenEntries {
        public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, WeepingAngels.MODID);
        public static final RegistryObject<Feature<ProbabilityConfig>> ARM_SNOW_FEATURE = FEATURES.register("arm_snow_feature", () -> new SnowAngelGeneration(ProbabilityConfig.CODEC));
        public static final RegistryObject<Feature<OreFeatureConfig>> KONTRON_ORE = FEATURES.register("kontron_ore", () -> new OreFeature(OreFeatureConfig.CODEC));
    }

    public static class ConfiguredFeatures {
        public static final ConfiguredFeature<?, ?> ARM_SNOW_FEATURE = WorldGenEntries.ARM_SNOW_FEATURE.get().configured(new ProbabilityConfig(0.1F)).decorated(Features.Placements.HEIGHTMAP_SQUARE);
        public static final ConfiguredFeature<?, ?> KONTRON_ORE = WorldGenEntries.KONTRON_ORE.get().configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, WAObjects.Blocks.KONTRON_ORE.get().defaultBlockState(), 10)).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(6, 0, 34))).squared().count(5);

        public static void registerConfiguredFeatures() {
            registerConfiguredFeature("arm_snow_feature", ARM_SNOW_FEATURE);
            registerConfiguredFeature("kontron_ore", KONTRON_ORE);
        }
    }


    /**
     * ===Structure Registration Start===
     */

    public static class Structures {
        public static final DeferredRegister<Structure<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, WeepingAngels.MODID);

        /**
         * The Structure registry object. This isn't actually setup yet, see {@link WAObjects#setupStructure(Structure, StructureSeparationSettings, boolean)}
         */
        public static final RegistryObject<Structure<ProbabilityConfig>> GRAVEYARD = setupStructure("graveyard", () -> (new GraveyardStructure(ProbabilityConfig.CODEC)));
        public static final RegistryObject<Structure<NoFeatureConfig>> CATACOMBS = setupStructure("catacombs", () -> (new CatacombStructure(NoFeatureConfig.CODEC)));
        /**
         * Static instance of our structure so we can reference it before registry stuff happens and use it to make configured structures in ConfiguredStructures
         */
        public static IStructurePieceType GRAVEYARD_PIECE = registerStructurePiece(GraveyardStructurePieces.Piece::new, "graveyard_piece");

    }

    /**
     * Configure the structure so it can be placed in the world. <br> Register Configured Structures in Common Setup. There is currently no Forge Registry for configured structures because configure structures are a dynamic registry and can cause issues if it were a Forge registry.
     */
    public static class ConfiguredStructures {
        /**
         * Static instance of our configured structure feature so we can reference it for registration
         */
        public static StructureFeature<?, ?> CONFIGURED_GRAVEYARD = Structures.GRAVEYARD.get().configured(new ProbabilityConfig(5));
        public static StructureFeature<?, ?> CONFIGURED_CATACOMBS = Structures.CATACOMBS.get().configured(NoFeatureConfig.INSTANCE);

        public static void registerConfiguredStructures() {
            registerConfiguredStructure("configured_graveyard", Structures.GRAVEYARD, CONFIGURED_GRAVEYARD); //We have to add this to flatGeneratorSettings to account for mods that add custom chunk generators or superflat world type
            registerConfiguredStructure("configured_catacombs", Structures.CATACOMBS, CONFIGURED_CATACOMBS);
        }
    }

    // Entities
    public static class EntityEntries {
        public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, WeepingAngels.MODID);

        public static final RegistryObject<EntityType<WeepingAngelEntity>> WEEPING_ANGEL = ENTITIES.register("weeping_angel", () -> registerFireResistMob(WeepingAngelEntity::new, WeepingAngelEntity::new, EntityClassification.MONSTER, 1F, 1.75F, "weeping_angel", false));
        public static final RegistryObject<EntityType<AnomalyEntity>> ANOMALY = ENTITIES.register("anomaly", () -> registerMob(AnomalyEntity::new, AnomalyEntity::new, EntityClassification.MONSTER, 1F, 1.75F, "anomaly", false));
        public static final RegistryObject<EntityType<ChronodyneGeneratorEntity>> CHRONODYNE_GENERATOR = ENTITIES.register("chronodyne_generator", () -> registerMob(ChronodyneGeneratorEntity::new, ChronodyneGeneratorEntity::new, EntityClassification.MISC, 0.5F, 0.5F, "chronodyne_generator", true));
    }


}
