package me.suff.mc.angels.utils;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.blockentities.CoffinBlockEntity;
import me.suff.mc.angels.common.blockentities.SnowAngelBlockEntity;
import me.suff.mc.angels.common.entities.AngelType;
import me.suff.mc.angels.common.entities.QuantumLockedLifeform;
import me.suff.mc.angels.common.entities.WeepingAngel;
import me.suff.mc.angels.common.level.WAFeatures;
import me.suff.mc.angels.common.variants.AbstractVariant;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
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
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import static me.suff.mc.angels.common.blockentities.CoffinBlockEntity.Coffin.*;

public class AngelUtil {

    public static final Random RAND = new Random();
    public static TagKey<Item> THEFT = makeItem(WeepingAngels.MODID, "angel_theft");
    public static TagKey<Item> HELD_LIGHT_ITEMS = makeItem(WeepingAngels.MODID, "held_light_items");
    public static TagKey<Block> BANNED_BLOCKS = makeBlock(WeepingAngels.MODID, "angel_proof");
    public static TagKey<Block> POTTED_PLANTS = makeBlock(WeepingAngels.MODID, "grave_plants");
    public static TagKey<Block> ANGEL_IGNORE = makeBlock(WeepingAngels.MODID, "angel_ignore");

    public static TagKey<Biome> STRUCTURE_SPAWNS = makeBiome(WeepingAngels.MODID, "has_structure/angel_structure_biomes");
    public static TagKey<Structure> TELEPORT_STRUCTURES = makeStructure(WeepingAngels.MODID, "teleport_structures");
    public static TagKey<Structure> CATACOMBS = makeStructure(WeepingAngels.MODID, "haunted_structures");

    public static TagKey<Item> makeItem(String domain, String path) {
        return ItemTags.create(new ResourceLocation(domain, path));
    }

    public static TagKey<Block> makeBlock(String domain, String path) {
        return BlockTags.create(new ResourceLocation(domain, path));
    }

    public static TagKey<Structure> makeStructure(String domain, String path) {
        return TagKey.create(Registry.STRUCTURE_REGISTRY, new ResourceLocation(domain, path));
    }

    public static TagKey<Biome> makeBiome(String domain, String path) {
        return TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(domain, path));
    }

    public static boolean isDarkForPlayer(QuantumLockedLifeform angel, LivingEntity living) {
        return !living.hasEffect(MobEffects.NIGHT_VISION) && angel.level.getLightEmission(angel.blockPosition()) <= 0 && !angel.level.dimensionType().hasCeiling() && !AngelUtil.handLightCheck(living);
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

    //TODO This could do with being converted proper tag logic
    public static boolean handLightCheck(LivingEntity player) {
        for (Object o : TagUtil.getValues(Registry.ITEM, AngelUtil.HELD_LIGHT_ITEMS)) {
            Holder<Item> value = (Holder<Item>) o;
            if (PlayerUtil.isInEitherHand(player, value.value())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isOutsideOfBorder(Level world, BlockPos p) {
        return !world.getWorldBorder().isWithinBounds(p);
    }

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

    public static AngelType randomType() {
        int pick = RAND.nextInt(AngelType.values().length);
        return AngelType.values()[pick];
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
        if (target instanceof WeepingAngel weepingAngel) {
            if (weepingAngel.getAngelType() == AngelType.DISASTER_MC) {
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
            boolean isCatacomb = serverWorld.structureManager().getStructureAt(playerEntity.blockPosition(), getConfigured(serverWorld, WAFeatures.CATACOMB.getId())).isValid();

            if (isCatacomb) {
                BoundingBox box = serverWorld.structureManager().getStructureAt(playerEntity.blockPosition(), getConfigured(serverWorld, WAFeatures.CATACOMB.getId())).getBoundingBox();
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

    public static Structure getConfigured(ServerLevel level, ResourceLocation resourceLocation) {
        Registry<Structure> registry = level.registryAccess().registryOrThrow(Registry.STRUCTURE_REGISTRY);
        return registry.get(resourceLocation);
    }

    public enum EnumTeleportType {
        RANDOM_PLACE, DONT, STRUCTURE
    }
}
