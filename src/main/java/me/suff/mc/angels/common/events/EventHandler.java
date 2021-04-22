package me.suff.mc.angels.common.events;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import me.suff.mc.angels.common.tileentities.StatueTile;
import me.suff.mc.angels.config.WAConfig;
import me.suff.mc.angels.network.Network;
import me.suff.mc.angels.network.messages.MessageCatacomb;
import me.suff.mc.angels.utils.AngelUtils;
import me.suff.mc.angels.utils.DamageType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.Registry;
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
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
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
        if (event.getWorld().isClientSide) return;
        ServerWorld world = (ServerWorld) event.getWorld();
        BlockPos pos = event.getPos();
        PlayerEntity player = event.getPlayer();

        boolean isGraveYard = world.structureFeatureManager().getStructureAt(pos, true, WAObjects.Structures.GRAVEYARD.get()).isValid();

        if (world.getBlockState(pos).getBlock() instanceof ChestBlock) {
            if (isGraveYard && !player.abilities.instabuild) {
                MutableBoundingBox box = world.structureFeatureManager().getStructureAt(pos, true, WAObjects.Structures.GRAVEYARD.get()).getBoundingBox();
                boolean canPlaySound = false;
                for (Iterator< BlockPos > iterator = BlockPos.betweenClosedStream(new BlockPos(box.x1, box.y1, box.z1), new BlockPos(box.x0, box.y0, box.z0)).iterator(); iterator.hasNext(); ) {
                    BlockPos blockPos = iterator.next();
                    BlockState blockState = world.getBlockState(blockPos);
                    if (blockState.getBlock() == WAObjects.Blocks.STATUE.get()) {
                        canPlaySound = true;
                        StatueTile statueTile = (StatueTile) world.getBlockEntity(blockPos);
                        WeepingAngelEntity angel = new WeepingAngelEntity(world);
                        angel.setType(statueTile.getAngelType());
                        angel.moveTo(blockPos.getX() + 0.5D, blockPos.getY(), blockPos.getZ() + 0.5D, 0, 0);
                        angel.setPose(statueTile.getPose());
                        world.addFreshEntity(angel);
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
        RegistryKey< Biome > biomeRegistryKey = RegistryKey.create(Registry.BIOME_REGISTRY, biomeLoadingEvent.getName());
        Biome.Category biomeCategory = biomeLoadingEvent.getCategory();
        if (WAConfig.CONFIG.arms.get()) {
            if (biomeCategory == Biome.Category.ICY || biomeCategory.getName().contains("snow")) {
                WeepingAngels.LOGGER.info("Added Snow Angels to: " + biomeLoadingEvent.getName());
                biomeLoadingEvent.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, WAObjects.ConfiguredFeatures.ARM_SNOW_FEATURE).build();
            }
        }

        if (biomeCategory != Biome.Category.NETHER && biomeCategory != Biome.Category.THEEND) {
            if (WAConfig.CONFIG.genOres.get()) {
                biomeLoadingEvent.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, WAObjects.ConfiguredFeatures.KONTRON_ORE);
            }
            if (biomeCategory != Biome.Category.NONE && biomeCategory != Biome.Category.OCEAN) {

                //Graveyard Spawning - We MUST use a COMMON Config option because only COMMON config is fired early enough. Server Configs fire too late to allow us to use them to configure world gen stuff.
                boolean shouldAdd = biomeCategory != Biome.Category.ICY && biomeCategory != Biome.Category.MUSHROOM && biomeCategory != Biome.Category.JUNGLE && biomeCategory != Biome.Category.OCEAN && biomeCategory != Biome.Category.RIVER && biomeCategory != Biome.Category.DESERT;
                if (WAConfig.CONFIG.genGraveyard.get()) {
                    if (shouldAdd) {
                        biomeLoadingEvent.getGeneration().getStructures().add(() -> WAObjects.ConfiguredStructures.CONFIGURED_GRAVEYARD);
                        WeepingAngels.LOGGER.info("Added Graveyard to: " + biomeLoadingEvent.getName());
                    }
                }

                //Graveyard Spawning - We MUST use a COMMON Config option because only COMMON config is fired early enough. Server Configs fire too late to allow us to use them to configure world gen stuff.
                if (WAConfig.CONFIG.genCatacombs.get()) {
                    if (shouldAdd) {
                        biomeLoadingEvent.getGeneration().getStructures().add(() -> WAObjects.ConfiguredStructures.CONFIGURED_CATACOMBS);
                        WeepingAngels.LOGGER.info("Added Catacombs to: " + biomeLoadingEvent.getName());
                    }
                }


                //Angel Mob Spawns. Use this event to allow spawn rate to be customised on world options screen and not require restart.
                WAConfig.CONFIG.allowedBiomes.get().forEach(rl -> {
                    if (rl.equalsIgnoreCase(biomeRegistryKey.location().toString())) {
                        biomeLoadingEvent.getSpawns().addSpawn(WAConfig.CONFIG.spawnType.get(), new MobSpawnInfo.Spawners(WAObjects.EntityEntries.WEEPING_ANGEL.get(), WAConfig.CONFIG.spawnWeight.get(), WAConfig.CONFIG.minSpawn.get(), WAConfig.CONFIG.maxSpawn.get()));
                    }
                });
            }
        }
    }

    /**
     * Adds the structure's spacing for modded code made dimensions so that the structure's spacing remains
     * correct in any dimension or worldtype instead of not spawning.
     * In {@link WAObjects#setupStructure(Structure, StructureSeparationSettings, boolean)} we call {@link DimensionStructuresSettings#DEFAULTS}
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
            if (serverWorld.getChunkSource().getGenerator() instanceof FlatChunkGenerator && serverWorld.dimension().equals(World.OVERWORLD)) {
                return;
            }
            //Only spawn Graveyards in the Overworld structure list
            if (serverWorld.dimension().equals(World.OVERWORLD)) {
                Map< Structure< ? >, StructureSeparationSettings > tempMap = new HashMap<>(serverWorld.getChunkSource().generator.getSettings().structureConfig());
                tempMap.put(WAObjects.Structures.GRAVEYARD.get(), DimensionStructuresSettings.DEFAULTS.get(WAObjects.Structures.GRAVEYARD.get()));
                tempMap.put(WAObjects.Structures.CATACOMBS.get(), DimensionStructuresSettings.DEFAULTS.get(WAObjects.Structures.CATACOMBS.get()));
                serverWorld.getChunkSource().generator.getSettings().structureConfig = tempMap;
            }
        }
    }


    @SubscribeEvent
    public static void onLive(LivingEvent.LivingUpdateEvent livingUpdateEvent) {
        LivingEntity living = livingUpdateEvent.getEntityLiving();
        if (living instanceof PlayerEntity && !living.level.isClientSide()) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) living;
            if (serverPlayerEntity.tickCount % 40 == 0) {
                Network.sendTo(new MessageCatacomb(AngelUtils.isInCatacomb(serverPlayerEntity)), serverPlayerEntity);
            }
        }
    }

    @SubscribeEvent
    public static void onDamage(LivingAttackEvent event) {
        if (event.getEntityLiving().level.isClientSide) return;

        DamageType configValue = WAConfig.CONFIG.damageType.get();
        DamageSource source = event.getSource();
        Entity attacker = event.getSource().getEntity();
        LivingEntity hurt = event.getEntityLiving();

        if (hurt.getType() == WAObjects.EntityEntries.WEEPING_ANGEL.get()) {
            WeepingAngelEntity weepingAngelEntity = (WeepingAngelEntity) hurt;

            switch (configValue) {
                case NOTHING:
                    event.setCanceled(true);
                    break;
                case GENERATOR_ONLY:
                    event.setCanceled(source != WAObjects.GENERATOR);
                    break;
                case ANY_PICKAXE_ONLY:
                    if (isAttackerHoldingPickaxe(attacker)) {
                        LivingEntity livingEntity = (LivingEntity) attacker;
                        event.setCanceled(false);
                        doHurt(weepingAngelEntity, attacker, livingEntity.getItemBySlot(EquipmentSlotType.MAINHAND));
                    } else {
                        event.setCanceled(true);
                    }
                    break;
                case ANY_PICKAXE_AND_GENERATOR_ONLY:

                    boolean shouldCancel = true;

                    //Pickaxe
                    if (isAttackerHoldingPickaxe(attacker)) {
                        LivingEntity livingEntity = (LivingEntity) attacker;
                        shouldCancel = false;
                        doHurt(weepingAngelEntity, attacker, livingEntity.getItemBySlot(EquipmentSlotType.MAINHAND));
                    }

                    //Generator
                    if(source == WAObjects.GENERATOR){
                        shouldCancel = false;
                    }

                    event.setCanceled(shouldCancel);

                    break;
                case DIAMOND_AND_ABOVE_PICKAXE_ONLY:
                    if (isAttackerHoldingPickaxe(attacker)) {
                        LivingEntity livingEntity = (LivingEntity) attacker;
                        PickaxeItem pickaxe = (PickaxeItem) livingEntity.getItemBySlot(EquipmentSlotType.MAINHAND).getItem();
                        boolean isDiamondAndAbove = pickaxe.getTier().getLevel() >= 3;
                        if (isDiamondAndAbove) {
                            doHurt(weepingAngelEntity, attacker, livingEntity.getItemBySlot(EquipmentSlotType.MAINHAND));
                        }
                        event.setCanceled(!isDiamondAndAbove);
                    }
                    break;
            }

            if (!isAttackerHoldingPickaxe(attacker) || configValue == DamageType.NOTHING || configValue == DamageType.GENERATOR_ONLY) {
                if (weepingAngelEntity.level.random.nextInt(100) <= 20) {
                    weepingAngelEntity.playSound(weepingAngelEntity.isCherub() ? WAObjects.Sounds.LAUGHING_CHILD.get() : WAObjects.Sounds.ANGEL_MOCKING.get(), 1, weepingAngelEntity.getLaugh());
                }
                if(attacker != null){
                    attacker.hurt(WAObjects.STONE, 2F);
                }
            }
        }
    }

    public static void doHurt(WeepingAngelEntity weepingAngelEntity, @Nullable Entity attacker, ItemStack stack) {
        ServerWorld serverWorld = (ServerWorld) weepingAngelEntity.level;
        weepingAngelEntity.playSound(SoundEvents.STONE_BREAK, 1.0F, 1.0F);
        serverWorld.sendParticles(new BlockParticleData(ParticleTypes.BLOCK, Blocks.STONE.defaultBlockState()), weepingAngelEntity.getX(), weepingAngelEntity.getY(0.5D), weepingAngelEntity.getZ(), 5, 0.1D, 0.0D, 0.1D, 0.2D);

        if (attacker instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) attacker;
            stack.hurtAndBreak(serverWorld.random.nextInt(4), livingEntity, living -> {
                boolean isCherub = weepingAngelEntity.isCherub();
                weepingAngelEntity.playSound(isCherub ? WAObjects.Sounds.LAUGHING_CHILD.get() : WAObjects.Sounds.ANGEL_MOCKING.get(), 1, weepingAngelEntity.getLaugh());
                livingEntity.broadcastBreakEvent(Hand.MAIN_HAND);
            });
        }

    }


    public static boolean isAttackerHoldingPickaxe(Entity entity) {
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            return livingEntity.getItemBySlot(EquipmentSlotType.MAINHAND).getItem() instanceof PickaxeItem;
        }
        return false;
    }


    /*@SubscribeEvent
    public static void onAngelDamage(LivingAttackEvent e) {
        if (!WAConfig.CONFIG.pickaxeOnly.get() || e.getEntityLiving().world.isRemote) return;

      *//*  Entity source = e.getSource().getTrueSource();
        if (source instanceof LivingEntity) {
            LivingEntity attacker = (LivingEntity) source;
            LivingEntity victim = e.getEntityLiving();

            if (victim instanceof WeepingAngelEntity && attacker instanceof PlayerEntity) {
                WeepingAngelEntity weepingAngelEntity = (WeepingAngelEntity) victim;

                ItemStack item = attacker.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
                boolean isPic = item.getItem() instanceof PickaxeItem;
                e.setCanceled(!isPic);
                if (!isPic) {

                    if (victim.world.rand.nextInt(100) <= 20) {
                        weepingAngelEntity.playSound(weepingAngelEntity.isCherub() ? WAObjects.Sounds.LAUGHING_CHILD.get() : WAObjects.Sounds.ANGEL_MOCKING.get(), 1, weepingAngelEntity.getLaugh());
                    }
                    attacker.attackEntityFrom(WAObjects.STONE, 2F);
                } else {
                    PickaxeItem pick = (PickaxeItem) item.getItem();
                    if (pick.getTier().getHarvestLevel() < 3 && WAConfig.CONFIG.hardcoreMode.get()) {
                        return;
                    }
                    ServerWorld serverWorld = (ServerWorld) attacker.world;
                    //Spawn Stone Particles
                    serverWorld.spawnParticle(new BlockParticleData(ParticleTypes.BLOCK, Blocks.STONE.getDefaultState()), victim.getPosX(), victim.getPosYHeight(0.5D), victim.getPosZ(), 5, 0.1D, 0.0D, 0.1D, 0.2D);
                    //Play hit sound
                    victim.playSound(SoundEvents.BLOCK_STONE_BREAK, 1.0F, 1.0F);
                    //Damage Pickaxe
                    item.damageItem(serverWorld.rand.nextInt(4), attacker, livingEntity -> {
                        boolean isCherub = weepingAngelEntity.isCherub();
                        weepingAngelEntity.playSound(isCherub ? WAObjects.Sounds.LAUGHING_CHILD.get() : WAObjects.Sounds.ANGEL_MOCKING.get(), 1, weepingAngelEntity.getLaugh());
                        attacker.sendBreakAnimation(Hand.MAIN_HAND);
                    });
                }
            }*//*
        }
    }*/
}
	
