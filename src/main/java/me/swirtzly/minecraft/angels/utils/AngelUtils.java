package me.swirtzly.minecraft.angels.utils;

import me.swirtzly.minecraft.angels.WeepingAngels;
import me.swirtzly.minecraft.angels.common.WAObjects;
import me.swirtzly.minecraft.angels.common.entities.AngelEnums;
import me.swirtzly.minecraft.angels.common.entities.QuantumLockBaseEntity;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import me.swirtzly.minecraft.angels.common.tileentities.CoffinTile;
import me.swirtzly.minecraft.angels.common.tileentities.SnowArmTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.*;
import net.minecraft.loot.functions.ILootFunction;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effects;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.server.ServerWorld;

import java.util.List;
import java.util.Random;

import static me.swirtzly.minecraft.angels.common.tileentities.CoffinTile.Coffin.*;

public class AngelUtils {

    public static ITag.INamedTag< Item > KEYS = makeItem(WeepingAngels.MODID, "angel_theft");
    public static ITag.INamedTag< Item > HELD_LIGHT_ITEMS = makeItem(WeepingAngels.MODID, "held_light_items");
    public static ITag.INamedTag< Block > BANNED_BLOCKS = makeBlock(WeepingAngels.MODID, "angel_proof");
    public static ITag.INamedTag< Block > POTTED_PLANTS = makeBlock(WeepingAngels.MODID, "grave_plants");
    public static ITag.INamedTag< Block > ANGEL_IGNORE = makeBlock(WeepingAngels.MODID, "angel_ignore");
    public static Structure[] END_STRUCTURES = new Structure[]{Structure.END_CITY};
    public static Structure[] OVERWORLD_STRUCTURES = new Structure[]{

            Structure.PILLAGER_OUTPOST,
            Structure.MINESHAFT,
            Structure.WOODLAND_MANSION,
            Structure.JUNGLE_PYRAMID,
            Structure.DESERT_PYRAMID,
            Structure.IGLOO,
            Structure.RUINED_PORTAL,
            Structure.SHIPWRECK,
            Structure.SWAMP_HUT,
            Structure.STRONGHOLD,
            Structure.MONUMENT,
            Structure.BURIED_TREASURE,
            Structure.VILLAGE
    };
    public static Structure[] NETHER_STRUCTURES = new Structure[]{Structure.BASTION_REMNANT, Structure.NETHER_FOSSIL, Structure.FORTRESS};
    public static Random RAND = new Random();

    public static ITag.INamedTag< Item > makeItem(String domain, String path) {
        return ItemTags.makeWrapperTag(new ResourceLocation(domain, path).toString());
    }

    public static ITag.INamedTag< Block > makeBlock(String domain, String path) {
        return BlockTags.makeWrapperTag(new ResourceLocation(domain, path).toString());
    }

    public static boolean isDarkForPlayer(QuantumLockBaseEntity angel, LivingEntity living) {
        return !living.isPotionActive(Effects.NIGHT_VISION) && angel.world.getLight(angel.getPosition()) <= 0 && angel.world.getDimensionKey().getRegistryName() != World.OVERWORLD.getRegistryName() && !AngelUtils.handLightCheck(living);
    }

    public static void playBreakEvent(LivingEntity entity, BlockPos pos, BlockState blockState) {
        if (!entity.world.isRemote) {
            ServerWorld serverWorld = (ServerWorld) entity.world;
            serverWorld.spawnParticle(new BlockParticleData(ParticleTypes.BLOCK, blockState), pos.getX(), pos.getY(), pos.getZ(), 0, 0, 0, 0, 0);
            entity.playSound(WAObjects.Sounds.LIGHT_BREAK.get(), 1, 1.0F);
            InventoryHelper.spawnItemStack(entity.world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(entity.world.getBlockState(pos).getBlock()));
            entity.world.setBlockState(pos, blockState);
        }
    }

    /**
     * Checks if the entity has a item that emites light in their hand
     */
    public static boolean handLightCheck(LivingEntity player) {
        for (Item item : AngelUtils.HELD_LIGHT_ITEMS.getAllElements()) {
            if (PlayerUtils.isInEitherHand(player, item)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isOutsideOfBorder(World world, BlockPos p) {
        return !world.getWorldBorder().contains(p);
    }

    /**
     * Converts seconds into ticks
     */
    public static int secondsToTicks(int seconds) {
        return 20 * seconds;
    }

    public static void removeLightFromHand(ServerPlayerEntity playerMP, WeepingAngelEntity angel) {
        if (playerMP.getDistanceSq(angel) < 1) {
            for (Hand enumHand : Hand.values()) {
                ItemStack stack = playerMP.getHeldItem(enumHand);
                if (lightCheck(stack, angel)) {
                    stack.shrink(1);
                    angel.playSound(WAObjects.Sounds.BLOW.get(), 1.0F, 1.0F);
                    return;
                }
            }
        }
    }

    public static int getLightValue(Block block) {
        return block.getDefaultState().getLightValue();
    }

    private static boolean lightCheck(ItemStack stack, WeepingAngelEntity angel) {
        if (stack.getItem().isIn(AngelUtils.HELD_LIGHT_ITEMS)) {
            angel.entityDropItem(stack);
            return true;
        }
        return false;
    }

    public static AngelEnums.AngelType randomType() {
        int pick = RAND.nextInt(AngelEnums.AngelType.values().length);
        return AngelEnums.AngelType.values()[pick];
    }

    public static WeepingAngelEntity.AngelVariants randomVarient() {
        int pick = RAND.nextInt(WeepingAngelEntity.AngelVariants.values().length);
        return WeepingAngelEntity.AngelVariants.values()[pick];
    }

    public static SnowArmTile.SnowAngelStages randowSnowStage() {
        int pick = RAND.nextInt(SnowArmTile.SnowAngelStages.values().length);
        return SnowArmTile.SnowAngelStages.values()[pick];
    }

    public static CoffinTile.Coffin randomCoffin() {
        CoffinTile.Coffin[] coffins = new CoffinTile.Coffin[]{NEW, WEATHERED, SLIGHTLY_WEATHERED, HEAVILY_WEATHERED};
        int pick = RAND.nextInt(coffins.length);
        return coffins[pick];
    }

    public static int getFortuneModifier(LivingEntity entityIn) {
        return EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.FORTUNE, entityIn);
    }

    public static LootFunctionType registerFunction(ResourceLocation resourceLocation, ILootSerializer< ? extends ILootFunction > serialiser) {
        return Registry.register(Registry.LOOT_FUNCTION_TYPE, resourceLocation, new LootFunctionType(serialiser));
    }

    public static void dropEntityLoot(Entity target, PlayerEntity attacker) {
        LivingEntity targeted = (LivingEntity) target;
        ResourceLocation resourcelocation = targeted.getLootTableResourceLocation();
        LootTable loot_table = target.world.getServer().getLootTableManager().getLootTableFromLocation(resourcelocation);
        LootContext.Builder lootContextBuilder = getLootContextBuilder(true, DamageSource.GENERIC, targeted, attacker);
        LootContext ctx = lootContextBuilder.build(LootParameterSets.ENTITY);
        List< ItemStack > generatedTable = loot_table.generate(ctx);
        if (target instanceof WeepingAngelEntity) {
            WeepingAngelEntity weepingAngelEntity = (WeepingAngelEntity) target;
            if (weepingAngelEntity.getAngelType() == AngelEnums.AngelType.ANGELA_MC) {
                WeepingAngelEntity.AngelVariants angelVarient = WeepingAngelEntity.AngelVariants.valueOf(weepingAngelEntity.getVarient());
                generatedTable.add(angelVarient.getDropStack());
            }
        }

        generatedTable.forEach(target::entityDropItem);
    }

    public static LootContext.Builder getLootContextBuilder(boolean p_213363_1_, DamageSource damageSourceIn, LivingEntity entity, PlayerEntity attacker) {
        LootContext.Builder builder = (new LootContext.Builder((ServerWorld) entity.world)).withRandom(entity.world.rand).withParameter(LootParameters.THIS_ENTITY, entity).withParameter(LootParameters.field_237457_g_, entity.getPositionVec()).withParameter(LootParameters.DAMAGE_SOURCE, damageSourceIn).withNullableParameter(LootParameters.KILLER_ENTITY, damageSourceIn.getTrueSource()).withNullableParameter(LootParameters.DIRECT_KILLER_ENTITY, damageSourceIn.getImmediateSource());
        if (p_213363_1_ && entity.getAttackingEntity() != null) {
            attacker = (PlayerEntity) entity.getAttackingEntity();
            builder = builder.withParameter(LootParameters.LAST_DAMAGE_PLAYER, attacker).withLuck(attacker.getLuck());
        }
        return builder;
    }

    public enum EnumTeleportType {
        STRUCTURES, RANDOM_PLACE, DONT
    }
}
