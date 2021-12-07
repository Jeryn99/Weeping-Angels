package me.suff.mc.angels.common.events;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.blockentities.CoffinBlockEntity;
import me.suff.mc.angels.common.blockentities.IPlinth;
import me.suff.mc.angels.common.blockentities.StatueBlockEntity;
import me.suff.mc.angels.common.entities.QuantumLockedLifeform;
import me.suff.mc.angels.common.entities.WeepingAngel;
import me.suff.mc.angels.common.items.ChiselItem;
import me.suff.mc.angels.config.WAConfig;
import me.suff.mc.angels.network.Network;
import me.suff.mc.angels.network.messages.MessageCatacomb;
import me.suff.mc.angels.utils.AngelUtil;
import me.suff.mc.angels.utils.DamageType;
import me.suff.mc.angels.utils.PlayerUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TranslatableComponent;
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
import net.minecraft.world.item.DebugStickItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.VersionChecker;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.Iterator;
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
    public static void onBreak(BlockEvent.BreakEvent event) {
        Player playerEntity = event.getPlayer();
        LevelAccessor world = event.getWorld();
        BlockPos pos = event.getPos();

        // Plinth
        boolean isPlinth = world.getBlockEntity(pos) instanceof IPlinth;
        boolean hasChisel = playerEntity.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof ChiselItem;
        if (isPlinth && playerEntity.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof ChiselItem) {
            IPlinth plinth = (IPlinth) world.getBlockEntity(pos);
            event.setCanceled(true);
            plinth.setAbstractVariant(plinth.getCurrentType().getWeightedHandler().getRandom(null));
            plinth.sendUpdatesToClient();
            PlayerUtil.sendMessageToPlayer(playerEntity, new TranslatableComponent("Changed variant to " + plinth.getVariant().getRegistryName()), true);
        }

        if (playerEntity.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof DebugStickItem && hasChisel) {
            if (world.getBlockEntity(pos) instanceof CoffinBlockEntity coffinTile) {
                event.setCanceled(true);
                coffinTile.setCoffin(CoffinBlockEntity.Coffin.next(coffinTile.getCoffin()));
                PlayerUtil.sendMessageToPlayer(playerEntity, new TranslatableComponent(coffinTile.getCoffin().name()), true);
            }
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

        //Angel Mob Spawns. Use this event to allow spawn rate to be customised on world options screen and not require restart.
        for (String biome : WAConfig.CONFIG.allowedBiomes.get()) {
            if (biome.equalsIgnoreCase(biomeLoadingEvent.getName().toString())) {
                WeepingAngels.LOGGER.info("Added Weeping Angel Spawns to " + biomeLoadingEvent.getName());
                biomeLoadingEvent.getSpawns().addSpawn(WAConfig.CONFIG.spawnType.get(), new MobSpawnSettings.SpawnerData(WAObjects.EntityEntries.WEEPING_ANGEL.get(), WAConfig.CONFIG.spawnWeight.get(), WAConfig.CONFIG.minSpawn.get(), WAConfig.CONFIG.maxSpawn.get()));
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

    public static boolean isAttackerHoldingPickaxe(Entity entity) {
        if (entity instanceof LivingEntity livingEntity) {
            return livingEntity.getItemBySlot(EquipmentSlot.MAINHAND).getItem() instanceof PickaxeItem;
        }
        return false;
    }


}
	
