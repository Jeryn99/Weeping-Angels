package me.suff.mc.angels.common;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.blockentities.CoffinBlockEntity;
import me.suff.mc.angels.common.blockentities.PlinthBlockEntity;
import me.suff.mc.angels.common.blockentities.SnowAngelBlockEntity;
import me.suff.mc.angels.common.blockentities.StatueBlockEntity;
import me.suff.mc.angels.common.blocks.*;
import me.suff.mc.angels.common.entities.ChronodyneGeneratorProjectile;
import me.suff.mc.angels.common.entities.Portal;
import me.suff.mc.angels.common.entities.WeepingAngel;
import me.suff.mc.angels.common.items.AngelSpawnerItem;
import me.suff.mc.angels.common.items.ChiselItem;
import me.suff.mc.angels.common.items.ChronodyneGeneratorItem;
import me.suff.mc.angels.common.items.DetectorItem;
import me.suff.mc.angels.common.misc.WATabs;
import me.suff.mc.angels.utils.WADamageSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

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

    private static SoundEvent setUpSound(String soundName) {
        return new SoundEvent(new ResourceLocation(WeepingAngels.MODID, soundName));
    }

    @SubscribeEvent
    public static void regBlockItems(RegistryEvent.Register<Item> e) {
        genBlockItems(Blocks.WALL_ARM.get(), Blocks.COFFIN.get(), Blocks.SNOW_ANGEL.get(), Blocks.KONTRON_ORE.get(), Blocks.KONTRON_ORE_DEEPSLATE.get(), Blocks.PLINTH.get(), Blocks.STATUE.get());
    }

    // Tile Creation
    private static <T extends BlockEntity> BlockEntityType<T> registerTiles(BlockEntityType.BlockEntitySupplier<T> tile, Block validBlock) {
        return BlockEntityType.Builder.of(tile, validBlock).build(null);
    }

    // Entity Creation
    private static <T extends Entity> EntityType<T> registerBase(EntityType.EntityFactory<T> factory, IClientSpawner<T> client, MobCategory classification, float width, float height, int trackingRange, int updateFreq, boolean sendUpdate, String name) {
        ResourceLocation loc = new ResourceLocation(WeepingAngels.MODID, name);
        EntityType.Builder<T> builder = EntityType.Builder.of(factory, classification);
        builder.setShouldReceiveVelocityUpdates(sendUpdate);
        builder.setTrackingRange(trackingRange);
        builder.setUpdateInterval(updateFreq);
        builder.clientTrackingRange(10);
        builder.sized(width, height);
        builder.setCustomClientFactory((spawnEntity, world) -> client.spawn(world));
        return builder.build(loc.toString());
    }

    // Fire Resistant Entity Creation
    private static <T extends Entity> EntityType<T> registerFireImmuneBase(EntityType.EntityFactory<T> factory, IClientSpawner<T> client, MobCategory classification, float width, float height, int trackingRange, int updateFreq, boolean sendUpdate, String name) {
        ResourceLocation loc = new ResourceLocation(WeepingAngels.MODID, name);
        EntityType.Builder<T> builder = EntityType.Builder.of(factory, classification);
        builder.setShouldReceiveVelocityUpdates(sendUpdate);
        builder.setTrackingRange(trackingRange);
        builder.setUpdateInterval(updateFreq);
        builder.fireImmune();
        builder.clientTrackingRange(10);
        builder.canSpawnFarFromPlayer();
        builder.sized(width, height);
        builder.setCustomClientFactory((spawnEntity, world) -> client.spawn(world));
        return builder.build(loc.toString());
    }

    private static <T extends Entity> EntityType<T> registerFireResistMob(EntityType.EntityFactory<T> factory, IClientSpawner<T> client, MobCategory classification, float width, float height, String name, boolean velocity) {
        return registerFireImmuneBase(factory, client, classification, width, height, 80, 2, velocity, name);
    }

    public static <T extends Entity> EntityType<T> registerStatic(EntityType.EntityFactory<T> factory, IClientSpawner<T> client, MobCategory classification, float width, float height, String name) {
        return registerBase(factory, client, classification, width, height, 64, 40, false, name);
    }

    public static <T extends Entity> EntityType<T> registerMob(EntityType.EntityFactory<T> factory, IClientSpawner<T> client, MobCategory classification, float width, float height, String name, boolean velocity) {
        return registerBase(factory, client, classification, width, height, 80, 3, velocity, name);
    }

    public interface IClientSpawner<T> {
        T spawn(Level world);
    }

    public static class Tiles {
        public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, WeepingAngels.MODID);

        public static RegistryObject<BlockEntityType<SnowAngelBlockEntity>> SNOW_ANGEL = TILES.register("snow_angel", () -> registerTiles(SnowAngelBlockEntity::new, Blocks.SNOW_ANGEL.get()));
        public static RegistryObject<BlockEntityType<PlinthBlockEntity>> PLINTH = TILES.register("plinth", () -> registerTiles(PlinthBlockEntity::new, Blocks.PLINTH.get()));
        public static RegistryObject<BlockEntityType<StatueBlockEntity>> STATUE = TILES.register("statue", () -> registerTiles(StatueBlockEntity::new, Blocks.STATUE.get()));
        public static RegistryObject<BlockEntityType<CoffinBlockEntity>> COFFIN = TILES.register("coffin", () -> registerTiles(CoffinBlockEntity::new, Blocks.COFFIN.get()));
    }

    public static class Blocks {
        public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WeepingAngels.MODID);
        public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WeepingAngels.MODID);

        public static final RegistryObject<Block> SNOW_ANGEL = BLOCKS.register("snow_angel", () -> setUpBlock(new SnowAngelBlock()));
        public static final RegistryObject<Block> CHRONODYNE_GENERATOR = BLOCKS.register("chronodyne_generator", () -> setUpBlock(new ChronodyneGeneratorBlock()));
        public static final RegistryObject<Block> PLINTH = BLOCKS.register("plinth", () -> setUpBlock(new PlinthBlock()));
        public static final RegistryObject<Block> KONTRON_ORE = BLOCKS.register("kontron_ore", () -> setUpBlock(new MineableBlock(Material.STONE, SoundType.STONE, 3, 3)));
        public static final RegistryObject<Block> KONTRON_ORE_DEEPSLATE = BLOCKS.register("kontron_ore_deepslate", () -> setUpBlock(new MineableBlock(Material.STONE, SoundType.DEEPSLATE, 4.5F, 3)));
        public static final RegistryObject<Block> STATUE = BLOCKS.register("statue", () -> setUpBlock(new StatueBlock()));
        public static final RegistryObject<Block> COFFIN = BLOCKS.register("coffin", () -> setUpBlock(new CoffinBlock(BlockBehaviour.Properties.copy(net.minecraft.world.level.block.Blocks.ACACIA_WOOD).noOcclusion().emissiveRendering((p_61036_, p_61037_, p_61038_) -> {
            if(p_61037_.getBlockEntity(p_61038_) instanceof CoffinBlockEntity coffinBlockEntity) {
                return coffinBlockEntity.getCoffin().isPoliceBox();
            }
            return false;
        }))));

        public static final RegistryObject<Block> WALL_ARM = BLOCKS.register("wall_arm", () -> setUpBlock(new ArmBlock(BlockBehaviour.Properties.copy(net.minecraft.world.level.block.Blocks.STONE).noCollission().noOcclusion())));

    }

    public static class Items {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WeepingAngels.MODID);

        public static final RegistryObject<Item> TIMEY_WIMEY_DETECTOR = ITEMS.register("timey_wimey_detector", DetectorItem::new);
        public static final RegistryObject<Item> CHRONODYNE_GENERATOR = ITEMS.register("chronodyne_generator", ChronodyneGeneratorItem::new);
        public static final RegistryObject<Item> ANGEL_SPAWNER = ITEMS.register("weeping_angel", () -> setUpItem(new AngelSpawnerItem()));
        public static final RegistryObject<Item> KONTRON_INGOT = ITEMS.register("kontron_ingot", () -> setUpItem(new Item(new Item.Properties().tab(WATabs.MAIN_TAB))));
        public static final RegistryObject<Item> CHISEL = ITEMS.register("chisel", () -> setUpItem(new ChiselItem(new Item.Properties().stacksTo(1).tab(WATabs.MAIN_TAB))));
        public static final RegistryObject<Item> SALLY = ITEMS.register("music_disc_sally", () -> setUpItem(new RecordItem(6, Sounds.DISC_SALLY, (new Item.Properties()).stacksTo(1).tab(CreativeModeTab.TAB_MISC).rarity(Rarity.RARE))));
        public static final RegistryObject<Item> TIME_PREVAILS = ITEMS.register("music_disc_time_prevails", () -> setUpItem(new RecordItem(6, Sounds.DISC_TIME_PREVAILS, (new Item.Properties()).stacksTo(1).tab(CreativeModeTab.TAB_MISC).rarity(Rarity.RARE))));
    }

    // Sounds
    public static class Sounds {
        public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, WeepingAngels.MODID);

        public static final RegistryObject<SoundEvent> ANGEL_SEEN = SOUNDS.register("angel_seen", () -> setUpSound("angel_seen"));
        public static final RegistryObject<SoundEvent> STONE_SCRAPE = SOUNDS.register("stone_scrape", () -> setUpSound("stone_scrape"));
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
        public static final RegistryObject<SoundEvent> KNOCK = SOUNDS.register("knock", () -> setUpSound("knock"));
    }

    // Entities
    public static class EntityEntries {
        public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, WeepingAngels.MODID);

        public static final RegistryObject<EntityType<WeepingAngel>> WEEPING_ANGEL = ENTITIES.register("weeping_angel", () -> registerFireResistMob(WeepingAngel::new, WeepingAngel::new, MobCategory.MONSTER, 1F, 1.75F, "weeping_angel", false));
        public static final RegistryObject<EntityType<Portal>> ANOMALY = ENTITIES.register("anomaly", () -> registerMob(Portal::new, Portal::new, MobCategory.MONSTER, 1F, 1.75F, "anomaly", false));
        public static final RegistryObject<EntityType<ChronodyneGeneratorProjectile>> CHRONODYNE_GENERATOR = ENTITIES.register("chronodyne_generator", () -> registerMob(ChronodyneGeneratorProjectile::new, ChronodyneGeneratorProjectile::new, MobCategory.MISC, 0.5F, 0.5F, "chronodyne_generator", true));
    }


}
