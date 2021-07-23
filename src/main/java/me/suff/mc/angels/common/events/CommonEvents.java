package me.suff.mc.angels.common.events;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import me.suff.mc.angels.common.tileentities.StatueTile;
import me.suff.mc.angels.config.WAConfig;
import me.suff.mc.angels.network.Network;
import me.suff.mc.angels.network.messages.MessageCatacomb;
import me.suff.mc.angels.utils.AngelUtil;
import me.suff.mc.angels.utils.DamageType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.DefaultAttributes;
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
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
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
public class CommonEvents {

    @SubscribeEvent
    public static void onKilled(LivingDeathEvent event) {
        LivingEntity killed = event.getEntityLiving();
        DamageSource damageSource = event.getSource();
        if (damageSource == WAObjects.ANGEL_NECK_SNAP) {
            killed.playSound(WAObjects.Sounds.ANGEL_NECK_SNAP.get(), 1, 1);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onBiomeLoad(BiomeLoadingEvent biomeLoadingEvent) {
        ResourceKey<Biome> biomeRegistryKey = ResourceKey.create(Registry.BIOME_REGISTRY, biomeLoadingEvent.getName());
        Biome.BiomeCategory biomeCategory = biomeLoadingEvent.getCategory();

        if (biomeCategory != Biome.BiomeCategory.NETHER && biomeCategory != Biome.BiomeCategory.THEEND) {

                //Angel Mob Spawns. Use this event to allow spawn rate to be customised on world options screen and not require restart.
                WAConfig.CONFIG.allowedBiomes.get().forEach(rl -> {
                    if (rl.equalsIgnoreCase(biomeRegistryKey.location().toString())) {
                        biomeLoadingEvent.getSpawns().addSpawn(WAConfig.CONFIG.spawnType.get(), new MobSpawnSettings.SpawnerData(WAObjects.EntityEntries.WEEPING_ANGEL.get(), WAConfig.CONFIG.spawnWeight.get(), WAConfig.CONFIG.minSpawn.get(), WAConfig.CONFIG.maxSpawn.get()));
                    }
                });
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
        LivingEntity hurt = event.getEntityLiving();

        if (source == DamageSource.OUT_OF_WORLD || source.isExplosion()) {
            return;
        }

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
                        doHurt(weepingAngelEntity, attacker, livingEntity.getItemBySlot(EquipmentSlot.MAINHAND));
                    } else {
                        event.setCanceled(true);
                    }
                    break;
                case ANY_PICKAXE_AND_GENERATOR_ONLY:
                    //Pickaxe
                    if (isAttackerHoldingPickaxe(attacker)) {
                        LivingEntity livingEntity = (LivingEntity) attacker;
                        event.setCanceled(false);
                        doHurt(weepingAngelEntity, attacker, livingEntity.getItemBySlot(EquipmentSlot.MAINHAND));
                    }
                    break;
                case DIAMOND_AND_ABOVE_PICKAXE_ONLY:
                    if (isAttackerHoldingPickaxe(attacker)) {
                        LivingEntity livingEntity = (LivingEntity) attacker;
                        PickaxeItem pickaxe = (PickaxeItem) livingEntity.getItemBySlot(EquipmentSlot.MAINHAND).getItem();
                        boolean isDiamondAndAbove = pickaxe.getTier().getLevel() >= 3;
                        if (isDiamondAndAbove) {
                            doHurt(weepingAngelEntity, attacker, livingEntity.getItemBySlot(EquipmentSlot.MAINHAND));
                        }
                        event.setCanceled(!isDiamondAndAbove);
                    }
                    break;
            }

            if (!isAttackerHoldingPickaxe(attacker) || configValue == DamageType.NOTHING || configValue == DamageType.GENERATOR_ONLY) {
                if (weepingAngelEntity.level.random.nextInt(100) <= 20) {
                    weepingAngelEntity.playSound(weepingAngelEntity.isCherub() ? WAObjects.Sounds.LAUGHING_CHILD.get() : WAObjects.Sounds.ANGEL_MOCKING.get(), 1, weepingAngelEntity.getLaugh());
                }
                if (attacker != null) {
                    attacker.hurt(WAObjects.STONE, 2F);
                }
            }
        }
    }

    public static void doHurt(WeepingAngelEntity weepingAngelEntity, @Nullable Entity attacker, ItemStack stack) {
        ServerLevel serverWorld = (ServerLevel) weepingAngelEntity.level;
        weepingAngelEntity.playSound(SoundEvents.STONE_BREAK, 1.0F, 1.0F);
        serverWorld.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.STONE.defaultBlockState()), weepingAngelEntity.getX(), weepingAngelEntity.getY(0.5D), weepingAngelEntity.getZ(), 5, 0.1D, 0.0D, 0.1D, 0.2D);

        if (attacker instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) attacker;
            stack.hurtAndBreak(serverWorld.random.nextInt(4), livingEntity, living -> {
                boolean isCherub = weepingAngelEntity.isCherub();
                weepingAngelEntity.playSound(isCherub ? WAObjects.Sounds.LAUGHING_CHILD.get() : WAObjects.Sounds.ANGEL_MOCKING.get(), 1, weepingAngelEntity.getLaugh());
                livingEntity.broadcastBreakEvent(InteractionHand.MAIN_HAND);
            });
        }

    }

    @SubscribeEvent
    public static void onAttributeAssign(EntityAttributeCreationEvent event){
        event.put(WAObjects.EntityEntries.WEEPING_ANGEL.get(), WeepingAngelEntity.createAttributes().build());
        event.put(WAObjects.EntityEntries.ANOMALY.get(), WeepingAngelEntity.createAttributes().build());
    }


    public static boolean isAttackerHoldingPickaxe(Entity entity) {
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            return livingEntity.getItemBySlot(EquipmentSlot.MAINHAND).getItem() instanceof PickaxeItem;
        }
        return false;
    }
}
	
