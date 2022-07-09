package me.suff.mc.angels.events;

import com.mojang.serialization.Codec;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import me.suff.mc.angels.common.items.ChiselItem;
import me.suff.mc.angels.common.tileentities.CoffinTile;
import me.suff.mc.angels.common.tileentities.IPlinth;
import me.suff.mc.angels.common.tileentities.StatueTile;
import me.suff.mc.angels.config.WAConfig;
import me.suff.mc.angels.network.Network;
import me.suff.mc.angels.network.messages.MessageCatacomb;
import me.suff.mc.angels.utils.AngelUtil;
import me.suff.mc.angels.utils.DamageType;
import me.suff.mc.angels.utils.PlayerUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.DebugStickItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.VersionChecker;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

@Mod.EventBusSubscriber
public class CommonEvents {

    private static Method GETCODEC_METHOD;

    @SubscribeEvent
    public static void onKilled(LivingDeathEvent event) {
        LivingEntity killed = event.getEntityLiving();
        DamageSource damageSource = event.getSource();
        if (damageSource == WAObjects.ANGEL_NECK_SNAP) {
            killed.playSound(WAObjects.Sounds.ANGEL_NECK_SNAP.get(), 1, 1);
        }
    }

    @SubscribeEvent
    public static void onBreak(BlockEvent.BreakEvent event) {
        PlayerEntity playerEntity = event.getPlayer();
        IWorld world = event.getWorld();
        BlockPos pos = event.getPos();

        // Plinth
        boolean isPlinth = world.getBlockEntity(pos) instanceof IPlinth;
        boolean hasChisel = playerEntity.getItemInHand(Hand.MAIN_HAND).getItem() instanceof ChiselItem;
        if (isPlinth && hasChisel) {
            IPlinth plinth = (IPlinth) world.getBlockEntity(pos);
            event.setCanceled(true);
            plinth.setAbstractVariant(plinth.getCurrentType().getRandom());
            plinth.sendUpdatesToClient();
            PlayerUtil.sendMessageToPlayer(playerEntity, new TranslationTextComponent("Changed variant to " + plinth.getVariant().getRegistryName()), true);
        }

        if (playerEntity.getItemBySlot(EquipmentSlotType.HEAD).getItem() instanceof DebugStickItem && hasChisel) {
            if (world.getBlockEntity(pos) instanceof CoffinTile) {
                event.setCanceled(true);
                CoffinTile coffinTile = (CoffinTile) world.getBlockEntity(pos);
                coffinTile.setCoffin(CoffinTile.Coffin.next(coffinTile.getCoffin()));
                PlayerUtil.sendMessageToPlayer(playerEntity, new TranslationTextComponent(coffinTile.getCoffin().name()), true);
            }
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
                for (Iterator<BlockPos> iterator = BlockPos.betweenClosedStream(new BlockPos(box.x1, box.y1, box.z1), new BlockPos(box.x0, box.y0, box.z0)).iterator(); iterator.hasNext(); ) {
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
        RegistryKey<Biome> biomeRegistryKey = RegistryKey.create(Registry.BIOME_REGISTRY, biomeLoadingEvent.getName());
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
                        biomeLoadingEvent.getSpawns().addSpawn(WAConfig.CONFIG.spawnType.get(), new MobSpawnInfo.Spawners(WAObjects.EntityEntries.WEEPING_ANGEL.get(), WAConfig.CONFIG.spawnWeight.get(), WAConfig.CONFIG.minCount.get(), WAConfig.CONFIG.maxCount.get()));
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

            try {
                if (GETCODEC_METHOD == null)
                    GETCODEC_METHOD = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "codec");
                ChunkGenerator chunkGenerator = serverWorld.getChunkSource().getGenerator();
                ResourceLocation cgRL = Registry.CHUNK_GENERATOR.getKey((Codec<? extends ChunkGenerator>) GETCODEC_METHOD.invoke(chunkGenerator));
                if (cgRL != null && cgRL.getNamespace().equals("terraforged")) return;
            } catch (Exception e) {
                WeepingAngels.LOGGER.error("Was unable to check if " + serverWorld.dimension().location() + " is using Terraforged's ChunkGenerator.");
            }

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
                Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(serverWorld.getChunkSource().generator.getSettings().structureConfig());
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
                Network.sendTo(new MessageCatacomb(AngelUtil.isInCatacomb(serverPlayerEntity)), serverPlayerEntity);
            }
        }
    }

 /*   @SubscribeEvent
    public static void on(BlockEvent.EntityPlaceEvent event){
        if(event.getPlacedBlock().getBlock() instanceof WallSignBlock){
            BlockPos pos = event.getBlockSnapshot().getPos();
            event.getWorld().setBlock(pos.above(), Blocks.STRUCTURE_BLOCK.defaultBlockState(), 4);
            TileEntity tile = event.getWorld().getBlockEntity(pos.above());
            if(tile instanceof StructureBlockTileEntity){
                StructureBlockTileEntity structureBlockTileEntity = (StructureBlockTileEntity) tile;
                structureBlockTileEntity.setMode(StructureMode.DATA);
                structureBlockTileEntity.setMetaData("sign");
            }
        }
    }*/


    @SubscribeEvent
    public static void onLoad(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity) event.getEntity();
            versionCheck(playerEntity);
        }
    }

    public static void versionCheck(PlayerEntity playerEntity) {
        VersionChecker.CheckResult version = VersionChecker.getResult(ModList.get().getModFileById(WeepingAngels.MODID).getMods().get(0));
        if (version.status == VersionChecker.Status.OUTDATED) {
            TranslationTextComponent click = new TranslationTextComponent("Download");
            click.setStyle(Style.EMPTY.setUnderlined(true).withColor(TextFormatting.GREEN).withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.curseforge.com/minecraft/mc-mods/weeping-angels-mod")));

            TranslationTextComponent translationTextComponent = new TranslationTextComponent(TextFormatting.BOLD + "[" + TextFormatting.RESET + TextFormatting.YELLOW + "Weeping Angels" + TextFormatting.RESET + TextFormatting.BOLD + "]");
            translationTextComponent.append(new TranslationTextComponent(" New Update Found: (" + version.target + ") ").append(click));
            PlayerUtil.sendMessageToPlayer(playerEntity, translationTextComponent, false);
        }
    }

    @SubscribeEvent
    public static void onDamage(LivingAttackEvent event) {
        if (event.getEntityLiving().level.isClientSide) return;

        DamageType configValue = WAConfig.CONFIG.damageType.get();
        DamageSource source = event.getSource();
        Entity attacker = event.getSource().getEntity();

        if (source == DamageSource.OUT_OF_WORLD) {
            return;
        }

        LivingEntity living = event.getEntityLiving();


        if (living instanceof WeepingAngelEntity) {
            WeepingAngelEntity hurt = (WeepingAngelEntity) living;
            if (source.isProjectile() && configValue != DamageType.EVERYTHING) {
                event.setCanceled(true);
                return;
            }

            switch (configValue) {
                case EVERYTHING:
                    break;
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
                        doHurt(hurt, attacker, livingEntity.getItemBySlot(EquipmentSlotType.MAINHAND));
                    } else {
                        event.setCanceled(true);
                    }
                    break;
                case ANY_PICKAXE_AND_GENERATOR_ONLY:
                    //Pickaxe
                    if (isAttackerHoldingPickaxe(attacker)) {
                        LivingEntity livingEntity = (LivingEntity) attacker;
                        event.setCanceled(false);
                        doHurt(hurt, attacker, livingEntity.getItemBySlot(EquipmentSlotType.MAINHAND));
                        return;
                    }
                    //Generator
                    event.setCanceled(source != WAObjects.GENERATOR);
                    break;
                case DIAMOND_AND_ABOVE_PICKAXE_ONLY:
                    if (isAttackerHoldingPickaxe(attacker)) {
                        LivingEntity livingEntity = (LivingEntity) attacker;
                        PickaxeItem pickaxe = (PickaxeItem) livingEntity.getItemBySlot(EquipmentSlotType.MAINHAND).getItem();
                        boolean isDiamondAndAbove = pickaxe.getTier().getLevel() >= 3;
                        if (isDiamondAndAbove) {
                            doHurt(hurt, attacker, livingEntity.getItemBySlot(EquipmentSlotType.MAINHAND));
                        }
                        event.setCanceled(!isDiamondAndAbove);
                    }
                    break;
            }

            if (!isAttackerHoldingPickaxe(attacker) || configValue == DamageType.NOTHING || configValue == DamageType.GENERATOR_ONLY) {
                if (hurt.level.random.nextInt(100) <= 10) {
                    hurt.playSound(hurt.isCherub() ? WAObjects.Sounds.LAUGHING_CHILD.get() : WAObjects.Sounds.ANGEL_MOCKING.get(), 1, hurt.getLaugh());
                }
                if (attacker != null) {
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
}

