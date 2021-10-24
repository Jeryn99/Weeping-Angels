package me.suff.mc.angels.utils;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.blockentities.CoffinBlockEntity;
import me.suff.mc.angels.common.blockentities.SnowAngelBlockEntity;
import me.suff.mc.angels.common.entities.AngelEnums;
import me.suff.mc.angels.common.entities.QuantumLockedLifeform;
import me.suff.mc.angels.common.entities.WeepingAngel;
import me.suff.mc.angels.common.variants.AbstractVariant;
import me.suff.mc.angels.common.level.WAWorld;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import static me.suff.mc.angels.common.blockentities.CoffinBlockEntity.Coffin.*;

public class AngelUtil {

    public static Tag.Named<Item> THEFT = makeItem(WeepingAngels.MODID, "angel_theft");
    public static Tag.Named<Item> HELD_LIGHT_ITEMS = makeItem(WeepingAngels.MODID, "held_light_items");
    public static Tag.Named<Block> BANNED_BLOCKS = makeBlock(WeepingAngels.MODID, "angel_proof");
    public static Tag.Named<Block> POTTED_PLANTS = makeBlock(WeepingAngels.MODID, "grave_plants");
    public static Tag.Named<Block> ANGEL_IGNORE = makeBlock(WeepingAngels.MODID, "angel_ignore");
    public static StructureFeature[] END_STRUCTURES = new StructureFeature[]{StructureFeature.END_CITY};
    public static StructureFeature[] OVERWORLD_STRUCTURES = new StructureFeature[]{

            StructureFeature.PILLAGER_OUTPOST,
            StructureFeature.MINESHAFT,
            StructureFeature.WOODLAND_MANSION,
            StructureFeature.JUNGLE_TEMPLE,
            StructureFeature.DESERT_PYRAMID,
            StructureFeature.IGLOO,
            StructureFeature.RUINED_PORTAL,
            StructureFeature.SHIPWRECK,
            StructureFeature.SWAMP_HUT,
            StructureFeature.STRONGHOLD,
            StructureFeature.OCEAN_MONUMENT,
            StructureFeature.BURIED_TREASURE,
            StructureFeature.VILLAGE
    };
    public static StructureFeature[] NETHER_STRUCTURES = new StructureFeature[]{StructureFeature.BASTION_REMNANT, StructureFeature.NETHER_FOSSIL, StructureFeature.NETHER_BRIDGE};
    public static Random RAND = new Random();

    public static Tag.Named<Item> makeItem(String domain, String path) {
        return ItemTags.bind(new ResourceLocation(domain, path).toString());
    }

    public static Tag.Named<Block> makeBlock(String domain, String path) {
        return BlockTags.bind(new ResourceLocation(domain, path).toString());
    }

    public static boolean isDarkForPlayer(QuantumLockedLifeform angel, LivingEntity living) {
        return !living.hasEffect(MobEffects.NIGHT_VISION) && angel.level.getMaxLocalRawBrightness(angel.blockPosition()) <= 0 && angel.level.dimension().getRegistryName() != Level.OVERWORLD.getRegistryName() && !AngelUtil.handLightCheck(living);
    }

    public static void updateBlock(LivingEntity entity, BlockPos pos, BlockState blockState, boolean breakBlock) {
        if (!entity.level.isClientSide) {
            ServerLevel serverWorld = (ServerLevel) entity.level;
            serverWorld.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, blockState), pos.getX(), pos.getY(), pos.getZ(), 0, 0, 0, 0, 0);
            serverWorld.playSound(null, pos.getX(), pos.getY(), pos.getZ(), blockState.getBlock().getSoundType(blockState).getBreakSound(), SoundSource.BLOCKS, 1.0F, 1.0F);
            if (breakBlock) {
                Containers.dropItemStack(entity.level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(entity.level.getBlockState(pos).getBlock()));
            }
            entity.level.setBlock(pos, blockState, 2);
        }
    }

    /**
     * Checks if the entity has a item that emites light in their hand
     */
    public static boolean handLightCheck(LivingEntity player) {
        for (Item item : AngelUtil.HELD_LIGHT_ITEMS.getValues()) {
            if (PlayerUtil.isInEitherHand(player, item)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isOutsideOfBorder(Level world, BlockPos p) {
        return !world.getWorldBorder().isWithinBounds(p);
    }

    /**
     * Converts seconds into ticks
     */
    public static int secondsToTicks(int seconds) {
        return 20 * seconds;
    }

    public static void extinguishHand(ServerPlayer playerMP, WeepingAngel angel) {
        if (playerMP.distanceToSqr(angel) < 1) {
            for (InteractionHand enumHand : InteractionHand.values()) {
                ItemStack stack = playerMP.getItemInHand(enumHand);
                if (lightCheck(stack, angel)) {
                    stack.shrink(1);
                    angel.playSound(WAObjects.Sounds.BLOW.get(), 1.0F, 1.0F);
                    return;
                }
            }
        }
    }

    public static int getLightValue(Block block) {
        return block.defaultBlockState().getLightEmission();
    }

    private static boolean lightCheck(ItemStack stack, WeepingAngel angel) {
        if (stack.is(AngelUtil.HELD_LIGHT_ITEMS)) {
            angel.spawnAtLocation(stack);
            return true;
        }
        return false;
    }

    public static AngelEnums.AngelType randomType() {
        int pick = RAND.nextInt(AngelEnums.AngelType.values().length);
        return AngelEnums.AngelType.values()[pick];
    }

    public static SnowAngelBlockEntity.SnowAngelStages randowSnowStage() {
        int pick = RAND.nextInt(SnowAngelBlockEntity.SnowAngelStages.values().length);
        return SnowAngelBlockEntity.SnowAngelStages.values()[pick];
    }

    public static CoffinBlockEntity.Coffin randomCoffin() {
        CoffinBlockEntity.Coffin[] coffins = new CoffinBlockEntity.Coffin[]{NEW, WEATHERED, SLIGHTLY_WEATHERED, HEAVILY_WEATHERED};
        int pick = RAND.nextInt(coffins.length);
        return coffins[pick];
    }

    public static int getFortuneModifier(LivingEntity entityIn) {
        return EnchantmentHelper.getEnchantmentLevel(Enchantments.BLOCK_FORTUNE, entityIn);
    }

    public static LootItemFunctionType registerFunction(ResourceLocation resourceLocation, Serializer<? extends LootItemFunction> serialiser) {
        return Registry.register(Registry.LOOT_FUNCTION_TYPE, resourceLocation, new LootItemFunctionType(serialiser));
    }

    public static void dropEntityLoot(Entity target, Player attacker) {
        LivingEntity targeted = (LivingEntity) target;
        ResourceLocation resourcelocation = targeted.getLootTable();
        LootTable loot_table = target.level.getServer().getLootTables().get(resourcelocation);
        LootContext.Builder lootContextBuilder = getLootContextBuilder(true, DamageSource.GENERIC, targeted, attacker);
        LootContext ctx = lootContextBuilder.create(LootContextParamSets.ENTITY);
        List<ItemStack> generatedTable = loot_table.getRandomItems(ctx);
        if (target instanceof WeepingAngel) {
            WeepingAngel weepingAngel = (WeepingAngel) target;
            if (weepingAngel.getAngelType() == AngelEnums.AngelType.DISASTER_MC) {
                AbstractVariant variant = weepingAngel.getVariant();
                if (variant.shouldDrop(DamageSource.playerAttack(attacker), weepingAngel)) {
                    weepingAngel.spawnAtLocation(variant.stackDrop().getItem());
                }
            }
        }

        generatedTable.forEach(target::spawnAtLocation);
    }

    public static LootContext.Builder getLootContextBuilder(boolean p_213363_1_, DamageSource damageSourceIn, LivingEntity entity, Player attacker) {
        LootContext.Builder builder = (new LootContext.Builder((ServerLevel) entity.level)).withRandom(entity.level.random).withParameter(LootContextParams.THIS_ENTITY, entity).withParameter(LootContextParams.ORIGIN, entity.position()).withParameter(LootContextParams.DAMAGE_SOURCE, damageSourceIn).withOptionalParameter(LootContextParams.KILLER_ENTITY, damageSourceIn.getEntity()).withOptionalParameter(LootContextParams.DIRECT_KILLER_ENTITY, damageSourceIn.getDirectEntity());
        if (p_213363_1_ && entity.getKillCredit() != null) {
            attacker = (Player) entity.getKillCredit();
            builder = builder.withParameter(LootContextParams.LAST_DAMAGE_PLAYER, attacker).withLuck(attacker.getLuck());
        }
        return builder;
    }

    public static boolean isInCatacomb(LivingEntity playerEntity) {
        if (playerEntity.level instanceof ServerLevel serverWorld) {
            boolean isCatacomb = serverWorld.structureFeatureManager().getStructureAt(playerEntity.blockPosition(), true, WAWorld.CATACOMBS.get()).isValid();

            if (isCatacomb) {
                BoundingBox box = serverWorld.structureFeatureManager().getStructureAt(playerEntity.blockPosition(), true, WAWorld.CATACOMBS.get()).getBoundingBox();
                return intersects(playerEntity.getBoundingBox(), new Vec3(box.minX(), box.minY(), box.minZ()), new Vec3(box.maxX(), box.maxY(), box.maxZ()));
            }
        }
        return false;
    }

    public static boolean intersects(AABB bb, Vec3 min, Vec3 max) {
        return bb.intersects(Math.min(min.x, max.x), Math.min(min.y, max.y), Math.min(min.z, max.z), Math.max(min.x, max.x), Math.max(min.y, max.y), Math.max(min.z, max.z));
    }

    public static boolean isHalloween() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) == Calendar.OCTOBER;
    }

    public enum EnumTeleportType {
        STRUCTURES, RANDOM_PLACE, DONT
    }
}
