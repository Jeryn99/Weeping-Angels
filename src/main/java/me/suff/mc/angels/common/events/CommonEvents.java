package me.suff.mc.angels.common.events;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.blockentities.StatueBlockEntity;
import me.suff.mc.angels.common.entities.QuantumLockedLifeform;
import me.suff.mc.angels.common.entities.WeepingAngel;
import me.suff.mc.angels.config.WAConfig;
import me.suff.mc.angels.network.Network;
import me.suff.mc.angels.network.messages.MessageCatacomb;
import me.suff.mc.angels.utils.AngelUtil;
import me.suff.mc.angels.utils.DamageType;
import me.suff.mc.angels.utils.PlayerUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.VersionChecker;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

@Mod.EventBusSubscriber
public class CommonEvents {

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
        ServerLevel world = (ServerLevel) event.getWorld();
        BlockPos pos = event.getPos();
        Player player = event.getPlayer();

        boolean isGraveYard = false;//TODO !!!!!world.structureFeatureManager().getStructureAt(pos, WAWorld.GRAVEYARD.get()).isValid();

        if (!isGraveYard) return;

        if (world.getBlockState(pos).getBlock() instanceof ChestBlock chestBlock) {
            if (isGraveYard && !player.getAbilities().instabuild) {
                //TODO !!!!! BoundingBox box = world.structureFeatureManager().getStructureAt(pos, WAWorld.GRAVEYARD.get()).getBoundingBox();
                BoundingBox box = null;
                boolean canPlaySound = false;
                for (Iterator<BlockPos> iterator = BlockPos.betweenClosedStream(new BlockPos(box.maxX(), box.maxY(), box.maxZ()), new BlockPos(box.minX(), box.minY(), box.minZ())).iterator(); iterator.hasNext(); ) {
                    BlockPos blockPos = iterator.next();
                    BlockState blockState = world.getBlockState(blockPos);
                    if (blockState.getBlock() == WAObjects.Blocks.STATUE.get()) {
                        canPlaySound = true;
                        StatueBlockEntity statueTile = (StatueBlockEntity) world.getBlockEntity(blockPos);
                        WeepingAngel angel = new WeepingAngel(world);
                        angel.setType(statueTile.getAngelType());
                        angel.moveTo(blockPos.getX() + 0.5D, blockPos.getY(), blockPos.getZ() + 0.5D, 0, 0);
                        angel.setPose(statueTile.getPose());
                        world.addFreshEntity(angel);
                        world.removeBlock(blockPos, false);
                    }
                }
                if (canPlaySound) {
                    world.playSound(null, pos, WAObjects.Sounds.ANGEL_AMBIENT.get(), SoundSource.BLOCKS, 0.2F, 1);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLive(LivingEvent.LivingUpdateEvent livingUpdateEvent) {
        LivingEntity living = livingUpdateEvent.getEntityLiving();
        if (living instanceof Player && !living.level.isClientSide()) {
            ServerPlayer serverPlayerEntity = (ServerPlayer) living;
            if (serverPlayerEntity.tickCount % 40 == 0) {
                Network.sendTo(new MessageCatacomb(AngelUtil.isInCatacomb(serverPlayerEntity)), serverPlayerEntity);
            }
        }
    }

    @SubscribeEvent
    public static void onDamage(LivingAttackEvent event) {
        if (event.getEntityLiving().level.isClientSide) return;

        DamageType configValue = WAConfig.CONFIG.damageType.get();
        DamageSource source = event.getSource();
        Entity attacker = event.getSource().getEntity();

        if (source == DamageSource.OUT_OF_WORLD || source.isExplosion()) {
            return;
        }
        LivingEntity living = event.getEntityLiving();

        if (living.getType() == WAObjects.EntityEntries.WEEPING_ANGEL.get()) {
            WeepingAngel hurt = (WeepingAngel) event.getEntityLiving();

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
                        doHurt(hurt, attacker, livingEntity.getItemBySlot(EquipmentSlot.MAINHAND));
                    } else {
                        event.setCanceled(true);
                    }
                    break;
                case ANY_PICKAXE_AND_GENERATOR_ONLY:
                    //Pickaxe
                    if (isAttackerHoldingPickaxe(attacker)) {
                        LivingEntity livingEntity = (LivingEntity) attacker;
                        event.setCanceled(false);
                        doHurt(hurt, attacker, livingEntity.getItemBySlot(EquipmentSlot.MAINHAND));
                    }
                    break;
                case DIAMOND_AND_ABOVE_PICKAXE_ONLY:
                    if (isAttackerHoldingPickaxe(attacker)) {
                        LivingEntity livingEntity = (LivingEntity) attacker;
                        PickaxeItem pickaxe = (PickaxeItem) livingEntity.getItemBySlot(EquipmentSlot.MAINHAND).getItem();
                        boolean isDiamondAndAbove = Objects.equals(pickaxe.getTier().getTag(), BlockTags.NEEDS_DIAMOND_TOOL);
                        if (isDiamondAndAbove) {
                            doHurt(hurt, attacker, livingEntity.getItemBySlot(EquipmentSlot.MAINHAND));
                        }
                        event.setCanceled(!isDiamondAndAbove);
                    }
                    break;
            }

            if (!isAttackerHoldingPickaxe(attacker) || configValue == DamageType.NOTHING || configValue == DamageType.GENERATOR_ONLY) {
                if (hurt.level.random.nextInt(100) <= 20) {
                    hurt.playSound(hurt.isCherub() ? WAObjects.Sounds.LAUGHING_CHILD.get() : WAObjects.Sounds.ANGEL_MOCKING.get(), 1, hurt.getLaugh());
                }
                if (attacker != null) {
                    attacker.hurt(WAObjects.STONE, 2F);
                }
            }
        }
    }

    public static void doHurt(QuantumLockedLifeform weepingAngel, @Nullable Entity attacker, ItemStack stack) {
        ServerLevel serverWorld = (ServerLevel) weepingAngel.level;
        weepingAngel.playSound(SoundEvents.STONE_BREAK, 1.0F, 1.0F);
        serverWorld.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.STONE.defaultBlockState()), weepingAngel.getX(), weepingAngel.getY(0.5D), weepingAngel.getZ(), 5, 0.1D, 0.0D, 0.1D, 0.2D);

        if (attacker instanceof LivingEntity livingEntity) {
            stack.hurtAndBreak(serverWorld.random.nextInt(4), livingEntity, living -> {
                if (weepingAngel instanceof WeepingAngel angel) {
                    weepingAngel.playSound(angel.isCherub() ? WAObjects.Sounds.LAUGHING_CHILD.get() : WAObjects.Sounds.ANGEL_MOCKING.get(), 1, angel.getLaugh());
                }
                livingEntity.broadcastBreakEvent(InteractionHand.MAIN_HAND);
            });
        }

    }


    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onBiomeLoad(BiomeLoadingEvent biomeLoadingEvent) {
        ResourceKey<Biome> biomeRegistryKey = ResourceKey.create(Registry.BIOME_REGISTRY, Objects.requireNonNull(biomeLoadingEvent.getName()));
        Biome.BiomeCategory biomeCategory = biomeLoadingEvent.getCategory();


        //Angel Mob Spawns. Use this event to allow spawn rate to be customised on world options screen and not require restart.
        for (String biome : WAConfig.CONFIG.allowedBiomes.get()) {
            if (biome.equalsIgnoreCase(biomeLoadingEvent.getName().toString())) {
                System.out.println("Added Weeping Angel Spawns to " + biomeLoadingEvent.getName());
                biomeLoadingEvent.getSpawns().addSpawn(WAConfig.CONFIG.spawnType.get(), new MobSpawnSettings.SpawnerData(WAObjects.EntityEntries.WEEPING_ANGEL.get(), WAConfig.CONFIG.spawnWeight.get(), WAConfig.CONFIG.minSpawn.get(), WAConfig.CONFIG.maxSpawn.get()));
            }
        }

        if (WAConfig.CONFIG.arms.get()) {
            if (biomeCategory == Biome.BiomeCategory.ICY || biomeRegistryKey.getRegistryName().toString().contains("snow")) {
                System.out.println("Added Snow Angels to: " + biomeLoadingEvent.getName());
                //TODO !!!!!  biomeLoadingEvent.getGeneration().addFeature(GenerationStep.Decoration.RAW_GENERATION, WAWorld.ConfiguredFeatures.CONFIGURED_SNOW_ANGEL).build();
            }
        }

        if (biomeCategory != Biome.BiomeCategory.NETHER && biomeCategory != Biome.BiomeCategory.THEEND) {
            if (WAConfig.CONFIG.genOres.get()) {
                //TODO !!!!!   biomeLoadingEvent.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WAWorld.ConfiguredFeatures.CONFIGURED_KONTRON_ORE.feature());
            }
            if (biomeCategory != Biome.BiomeCategory.NONE && biomeCategory != Biome.BiomeCategory.OCEAN) {

                //Graveyard Spawning - We MUST use a COMMON Config option because only COMMON config is fired early enough. Server Configs fire too late to allow us to use them to configure world gen stuff.
                boolean shouldAdd = biomeCategory != Biome.BiomeCategory.ICY && biomeCategory != Biome.BiomeCategory.MUSHROOM && biomeCategory != Biome.BiomeCategory.JUNGLE && biomeCategory != Biome.BiomeCategory.OCEAN && biomeCategory != Biome.BiomeCategory.RIVER && biomeCategory != Biome.BiomeCategory.DESERT;
                if (WAConfig.CONFIG.genGraveyard.get()) {
                    if (shouldAdd) {
                        //TODO !!!!!    biomeLoadingEvent.getGeneration().getStructures().add(() -> WAWorld.ConfiguredFeatures.CONFIGURED_GRAVEYARD);
                        System.out.println("Added Graveyard to: " + biomeLoadingEvent.getName());
                    }
                }

                //Catacombs Spawning - We MUST use a COMMON Config option because only COMMON config is fired early enough. Server Configs fire too late to allow us to use them to configure world gen stuff.
                if (WAConfig.CONFIG.genCatacombs.get()) {
                    if (shouldAdd) {
                        //TODO !!!!!biomeLoadingEvent.getGeneration().getStructures().add(() -> WAWorld.ConfiguredFeatures.CONFIGURED_CATACOMBS);
                        System.out.println("Added Catacombs to: " + biomeLoadingEvent.getName());
                    }
                }
            }
        }
    }


    @SubscribeEvent
    public static void onLoad(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof Player playerEntity) {
            versionCheck(playerEntity);
        }
    }

    public static void versionCheck(Player playerEntity) {
        VersionChecker.CheckResult version = VersionChecker.getResult(ModList.get().getModFileById(WeepingAngels.MODID).getMods().get(0));
        if (version.status() == VersionChecker.Status.OUTDATED) {
            TranslatableComponent click = new TranslatableComponent("Download");
            click.setStyle(Style.EMPTY.setUnderlined(true).withColor(ChatFormatting.GREEN).withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.curseforge.com/minecraft/mc-mods/weeping-angels-mod")));

            TranslatableComponent translationTextComponent = new TranslatableComponent(ChatFormatting.BOLD + "[" + ChatFormatting.RESET + ChatFormatting.YELLOW + "Weeping Angels" + ChatFormatting.RESET + ChatFormatting.BOLD + "]");
            translationTextComponent.append(new TranslatableComponent(" New Update Found: (" + version.target() + ") ").append(click));
            PlayerUtil.sendMessageToPlayer(playerEntity, translationTextComponent, false);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void addDimensionalSpacing(final WorldEvent.Load event) {
        if (event.getWorld() instanceof ServerLevel serverWorld) {

            /* Prevent spawning our structure in Vanilla's superflat world as
             * people seem to want their superflat worlds free of modded structures.
             * Also, vanilla superflat is really tricky and buggy to work with as mentioned in WAObjects#registerConfiguredStructure
             * BiomeModificationEvent does not seem to fire for superflat biomes...you can't add structures to superflat without mixin it seems.
             * */
            if (serverWorld.getChunkSource().getGenerator() instanceof FlatLevelSource && serverWorld.dimension().equals(Level.OVERWORLD)) {
                return;
            }
            //Only spawn Graveyards in the Overworld structure list
            if (serverWorld.dimension().equals(Level.OVERWORLD)) {
                Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(serverWorld.getChunkSource().getGenerator().getSettings().structureConfig());
                //TODO !!!!!  tempMap.put(WAWorld.GRAVEYARD.get(), StructureSettings.DEFAULTS.get(WAWorld.GRAVEYARD.get()));
                //TODO !!!!! tempMap.put(WAWorld.CATACOMBS.get(), StructureSettings.DEFAULTS.get(WAWorld.CATACOMBS.get()));
                serverWorld.getChunkSource().getGenerator().getSettings().structureConfig = tempMap;
            }
        }
    }

    public static boolean isAttackerHoldingPickaxe(Entity entity) {
        if (entity instanceof LivingEntity livingEntity) {
            return livingEntity.getItemBySlot(EquipmentSlot.MAINHAND).getItem() instanceof PickaxeItem;
        }
        return false;
    }


}
	
