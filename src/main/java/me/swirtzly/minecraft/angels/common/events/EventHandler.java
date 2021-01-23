package me.swirtzly.minecraft.angels.common.events;

import me.swirtzly.minecraft.angels.WeepingAngels;
import me.swirtzly.minecraft.angels.common.WAObjects;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import me.swirtzly.minecraft.angels.common.tileentities.StatueTile;
import me.swirtzly.minecraft.angels.config.WAConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.DamageSource;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Mod.EventBusSubscriber
public class EventHandler {

    @SubscribeEvent
    public static void onKilled(LivingDeathEvent event) {
        LivingEntity killed = event.getEntityLiving();
        DamageSource damageSource = event.getSource();
        if (damageSource == WAObjects.ANGEL_NECK_SNAP) {
            killed.playSound(WAObjects.Sounds.ANGEL_NECK_SNAP.get(), 1, 1);
        }
    }

    @SubscribeEvent
    public static void onOpenChestInGraveYard(PlayerInteractEvent.RightClickBlock event) {
        if (event.getWorld().isRemote) return;
        ServerWorld world = (ServerWorld) event.getWorld();
        BlockPos pos = event.getPos();
        PlayerEntity player = event.getPlayer();

        boolean isGraveYard = world.func_241112_a_().getStructureStart(pos, true, WAObjects.Structures.GRAVEYARD.get()).isValid();

        if (world.getBlockState(pos).getBlock() instanceof ChestBlock) {
            if (isGraveYard && !player.abilities.isCreativeMode) {
                MutableBoundingBox box = world.func_241112_a_().getStructureStart(pos, true, WAObjects.Structures.GRAVEYARD.get()).getBoundingBox();
                boolean canPlaySound = false;
                for (Iterator<BlockPos> iterator = BlockPos.getAllInBox(new BlockPos(box.maxX, box.maxY, box.maxZ), new BlockPos(box.minX, box.minY, box.minZ)).iterator(); iterator.hasNext(); ) {
                    BlockPos blockPos = iterator.next();
                    BlockState blockState = world.getBlockState(blockPos);
                    if (blockState.getBlock() == WAObjects.Blocks.STATUE.get()) {
                        canPlaySound = true;
                        StatueTile statueTile = (StatueTile) world.getTileEntity(blockPos);
                        WeepingAngelEntity angel = new WeepingAngelEntity(world);
                        angel.setType(statueTile.getAngelType());
                        float rotation = statueTile.getRotation();
                        angel.setLocationAndAngles(blockPos.getX() + 0.5D, blockPos.getY(), blockPos.getZ() + 0.5D, rotation, rotation);
                        angel.setPose(statueTile.getPose());
                        world.addEntity(angel);
                        world.removeBlock(blockPos, false);
                    }
                }
                if (canPlaySound) {
                    world.playSound(null, pos, WAObjects.Sounds.ANGEL_AMBIENT.get(), SoundCategory.BLOCKS, 0.2F, 1);
                }
            }
        }
    }


    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onBiomeLoad(BiomeLoadingEvent biomeLoadingEvent) {
        RegistryKey<Biome> biomeRegistryKey = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, biomeLoadingEvent.getName());
        Biome.Category biomeCategory = biomeLoadingEvent.getCategory();
        if (WAConfig.CONFIG.arms.get()) {
            if (biomeCategory == Biome.Category.ICY || biomeCategory.getName().contains("snow")) {
                WeepingAngels.LOGGER.info("Added Arms to: " + biomeLoadingEvent.getName());
                biomeLoadingEvent.getGeneration().withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, WAObjects.ConfiguredFeatures.ARM_SNOW_FEATURE).build();
            }
        }

        if (biomeCategory != Biome.Category.NETHER && biomeCategory != Biome.Category.THEEND) {
            if (WAConfig.CONFIG.genOres.get()) {
                biomeLoadingEvent.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, WAObjects.ConfiguredFeatures.KONTRON_ORE);
            }
            if (biomeCategory != Biome.Category.NONE && biomeCategory != Biome.Category.OCEAN) {
                //Graveyard Spawning - We MUST use a COMMON Config option because only COMMON config is fired early enough. Server Configs fire too late to allow us to use them to configure world gen stuff.
                if (WAConfig.CONFIG.genGraveyard.get()) {
                    if (biomeCategory != Biome.Category.ICY && biomeCategory != Biome.Category.MUSHROOM && biomeCategory != Biome.Category.JUNGLE && biomeCategory != Biome.Category.OCEAN && biomeCategory != Biome.Category.RIVER && biomeCategory != Biome.Category.DESERT) {
                        biomeLoadingEvent.getGeneration().getStructures().add(() -> WAObjects.ConfiguredStructures.CONFIGURED_GRAVEYARD);
                        WeepingAngels.LOGGER.info("Added Graveyard to: " + biomeLoadingEvent.getName());
                    }
                }
                //Angel Mob Spawns. Use this event to allow spawn rate to be customised on world options screen and not require restart.
                WAConfig.CONFIG.allowedBiomes.get().forEach(rl -> {
                    if (rl.equalsIgnoreCase(biomeRegistryKey.getLocation().toString())) {
                        biomeLoadingEvent.getSpawns().withSpawner(EntityClassification.valueOf(WAConfig.CONFIG.spawnType.get()), new MobSpawnInfo.Spawners(WAObjects.EntityEntries.WEEPING_ANGEL.get(), WAConfig.CONFIG.spawnWeight.get(), WAConfig.CONFIG.minSpawn.get(), WAConfig.CONFIG.maxSpawn.get()));
                        WeepingAngels.LOGGER.info("Added Weeping Angels Spawns to: [" + biomeRegistryKey.getLocation() + "]" + "\nEntity Classification: " + WAConfig.CONFIG.spawnType.get() + "\nSpawn Weight: " + WAConfig.CONFIG.spawnWeight.get() + "\nMin Spawn: " + WAConfig.CONFIG.minSpawn.get() + "\nMax Spawn: " + WAConfig.CONFIG.maxSpawn.get());
                    }
                });
            }
        }
    }

    /**
     * Adds the structure's spacing for modded code made dimensions so that the structure's spacing remains
     * correct in any dimension or worldtype instead of not spawning.
     * In {@link WAObjects#setupStructure(Structure, StructureSeparationSettings, boolean)} we call {@link DimensionStructuresSettings#field_236191_b_}
     * but this sometimes does not work in code made dimensions.
     */
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void addDimensionalSpacing(final WorldEvent.Load event) {
        if (event.getWorld() instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) event.getWorld();

            /* Prevent spawning our structure in Vanilla's superflat world as
             * people seem to want their superflat worlds free of modded structures.
             * Also, vanilla superflat is really tricky and buggy to work with as mentioned in WAObjects#registerConfiguredStructure
             * BiomeModificationEvent does not seem to fire for superflat biomes...you can't add structures to superflat without mixin it seems.
             * */
            if (serverWorld.getChunkProvider().getChunkGenerator() instanceof FlatChunkGenerator &&
                    serverWorld.getDimensionKey().equals(World.OVERWORLD)) {
                return;
            }
            //Only spawn Graveyards in the Overworld structure list
            if (serverWorld.getDimensionKey().equals(World.OVERWORLD)) {
                Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(serverWorld.getChunkProvider().generator.func_235957_b_().func_236195_a_());
                tempMap.put(WAObjects.Structures.GRAVEYARD.get(), DimensionStructuresSettings.field_236191_b_.get(WAObjects.Structures.GRAVEYARD.get()));
                serverWorld.getChunkProvider().generator.func_235957_b_().field_236193_d_ = tempMap;
            }
        }
    }

    @SubscribeEvent
    public static void onAngelDamage(LivingAttackEvent e) {
        if (!WAConfig.CONFIG.pickaxeOnly.get()) return;

        Entity source = e.getSource().getTrueSource();
        if (source instanceof LivingEntity) {
            LivingEntity attacker = (LivingEntity) source;
            LivingEntity victim = e.getEntityLiving();

            if (victim instanceof WeepingAngelEntity && attacker instanceof PlayerEntity) {

                if (WAConfig.CONFIG.hardcoreMode.get()) {
                    e.setCanceled(true);
                    return;
                }

                ItemStack item = attacker.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
                boolean isPic = item.getItem() instanceof PickaxeItem || item.getItem().getRegistryName().toString().contains("pickaxe");
                e.setCanceled(!isPic);

                if (!isPic) {
                    attacker.attackEntityFrom(WAObjects.STONE, 2F);
                } else {
                    Item pick = item.getItem();

                    if (pick != Items.DIAMOND_PICKAXE && victim.world.getDifficulty() == Difficulty.HARD) {
                        e.setCanceled(true);
                    }

                    victim.playSound(SoundEvents.BLOCK_STONE_BREAK, 1.0F, 1.0F);
                }

                if (!(source instanceof LivingEntity)) {
                    e.setCanceled(true);
                }
            }
        }
    }
}
	
