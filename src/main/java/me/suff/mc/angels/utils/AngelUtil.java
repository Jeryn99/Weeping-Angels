package me.suff.mc.angels.utils;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.entities.AngelEnums;
import me.suff.mc.angels.common.entities.QuantumLockEntity;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import me.suff.mc.angels.common.tileentities.CoffinTile;
import me.suff.mc.angels.common.tileentities.SnowArmTile;
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
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.server.ServerWorld;

import java.util.List;
import java.util.Random;

import static me.suff.mc.angels.common.tileentities.CoffinTile.Coffin.*;

public class AngelUtil {

    public static ITag.INamedTag<Item> THEFT = makeItem(WeepingAngels.MODID, "angel_theft");
    public static ITag.INamedTag<Item> HELD_LIGHT_ITEMS = makeItem(WeepingAngels.MODID, "held_light_items");
    public static ITag.INamedTag<Block> BANNED_BLOCKS = makeBlock(WeepingAngels.MODID, "angel_proof");
    public static ITag.INamedTag<Block> POTTED_PLANTS = makeBlock(WeepingAngels.MODID, "grave_plants");
    public static ITag.INamedTag<Block> ANGEL_IGNORE = makeBlock(WeepingAngels.MODID, "angel_ignore");
    public static Structure[] END_STRUCTURES = new Structure[]{Structure.END_CITY};
    public static Structure[] OVERWORLD_STRUCTURES = new Structure[]{

            Structure.PILLAGER_OUTPOST,
            Structure.MINESHAFT,
            Structure.WOODLAND_MANSION,
            Structure.JUNGLE_TEMPLE,
            Structure.DESERT_PYRAMID,
            Structure.IGLOO,
            Structure.RUINED_PORTAL,
            Structure.SHIPWRECK,
            Structure.SWAMP_HUT,
            Structure.STRONGHOLD,
            Structure.OCEAN_MONUMENT,
            Structure.BURIED_TREASURE,
            Structure.VILLAGE
    };
    public static Structure[] NETHER_STRUCTURES = new Structure[]{Structure.BASTION_REMNANT, Structure.NETHER_FOSSIL, Structure.NETHER_BRIDGE};
    public static Random RAND = new Random();

    public static ITag.INamedTag<Item> makeItem(String domain, String path) {
        return ItemTags.bind(new ResourceLocation(domain, path).toString());
    }

    public static ITag.INamedTag<Block> makeBlock(String domain, String path) {
        return BlockTags.bind(new ResourceLocation(domain, path).toString());
    }

    public static boolean isDarkForPlayer(QuantumLockEntity angel, LivingEntity living) {
        return !living.hasEffect(Effects.NIGHT_VISION) && angel.level.getMaxLocalRawBrightness(angel.blockPosition()) <= 0 && angel.level.dimension().getRegistryName() != World.OVERWORLD.getRegistryName() && !AngelUtil.handLightCheck(living);
    }

    public static void breakBlock(LivingEntity entity, BlockPos pos, BlockState blockState) {
        if (!entity.level.isClientSide) {
            ServerWorld serverWorld = (ServerWorld) entity.level;
            serverWorld.sendParticles(new BlockParticleData(ParticleTypes.BLOCK, blockState), pos.getX(), pos.getY(), pos.getZ(), 0, 0, 0, 0, 0);
            entity.playSound(SoundEvents.REDSTONE_TORCH_BURNOUT, 0.2F, 1.0F);
            InventoryHelper.dropItemStack(entity.level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(entity.level.getBlockState(pos).getBlock()));
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

    public static boolean isOutsideOfBorder(World world, BlockPos p) {
        return !world.getWorldBorder().isWithinBounds(p);
    }

    /**
     * Converts seconds into ticks
     */
    public static int secondsToTicks(int seconds) {
        return 20 * seconds;
    }

    public static void removeLightFromHand(ServerPlayerEntity playerMP, WeepingAngelEntity angel) {
        if (playerMP.distanceToSqr(angel) < 1) {
            for (Hand enumHand : Hand.values()) {
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

    private static boolean lightCheck(ItemStack stack, WeepingAngelEntity angel) {
        if (stack.getItem().is(AngelUtil.HELD_LIGHT_ITEMS)) {
            angel.spawnAtLocation(stack);
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
        return EnchantmentHelper.getEnchantmentLevel(Enchantments.BLOCK_FORTUNE, entityIn);
    }

    public static LootFunctionType registerFunction(ResourceLocation resourceLocation, ILootSerializer<? extends ILootFunction> serialiser) {
        return Registry.register(Registry.LOOT_FUNCTION_TYPE, resourceLocation, new LootFunctionType(serialiser));
    }

    public static void dropEntityLoot(Entity target, PlayerEntity attacker) {
        LivingEntity targeted = (LivingEntity) target;
        ResourceLocation resourcelocation = targeted.getLootTable();
        LootTable loot_table = target.level.getServer().getLootTables().get(resourcelocation);
        LootContext.Builder lootContextBuilder = getLootContextBuilder(true, DamageSource.GENERIC, targeted, attacker);
        LootContext ctx = lootContextBuilder.create(LootParameterSets.ENTITY);
        List<ItemStack> generatedTable = loot_table.getRandomItems(ctx);
        if (target instanceof WeepingAngelEntity) {
            WeepingAngelEntity weepingAngelEntity = (WeepingAngelEntity) target;
            if (weepingAngelEntity.getAngelType() == AngelEnums.AngelType.ANGELA_MC) {
                WeepingAngelEntity.AngelVariants angelVarient = WeepingAngelEntity.AngelVariants.valueOf(weepingAngelEntity.getVarient());
                generatedTable.add(angelVarient.getDropStack());
            }
        }

        generatedTable.forEach(target::spawnAtLocation);
    }

    public static LootContext.Builder getLootContextBuilder(boolean p_213363_1_, DamageSource damageSourceIn, LivingEntity entity, PlayerEntity attacker) {
        LootContext.Builder builder = (new LootContext.Builder((ServerWorld) entity.level)).withRandom(entity.level.random).withParameter(LootParameters.THIS_ENTITY, entity).withParameter(LootParameters.ORIGIN, entity.position()).withParameter(LootParameters.DAMAGE_SOURCE, damageSourceIn).withOptionalParameter(LootParameters.KILLER_ENTITY, damageSourceIn.getEntity()).withOptionalParameter(LootParameters.DIRECT_KILLER_ENTITY, damageSourceIn.getDirectEntity());
        if (p_213363_1_ && entity.getKillCredit() != null) {
            attacker = (PlayerEntity) entity.getKillCredit();
            builder = builder.withParameter(LootParameters.LAST_DAMAGE_PLAYER, attacker).withLuck(attacker.getLuck());
        }
        return builder;
    }

    public static boolean isInCatacomb(LivingEntity playerEntity) {
        if (playerEntity.level instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) playerEntity.level;
            boolean isCatacomb = serverWorld.structureFeatureManager().getStructureAt(playerEntity.blockPosition(), true, WAObjects.Structures.CATACOMBS.get()).isValid();

            if (isCatacomb) {
                MutableBoundingBox box = serverWorld.structureFeatureManager().getStructureAt(playerEntity.blockPosition(), true, WAObjects.Structures.CATACOMBS.get()).getBoundingBox();
                return intersects(playerEntity.getBoundingBox(), new Vector3d(box.x0, box.y0, box.z0), new Vector3d(box.x1, box.y1, box.z1));
            }
        }

        return false;
    }

    public static boolean intersects(AxisAlignedBB bb, Vector3d min, Vector3d max) {
        return bb.intersects(Math.min(min.x, max.x), Math.min(min.y, max.y), Math.min(min.z, max.z), Math.max(min.x, max.x), Math.max(min.y, max.y), Math.max(min.z, max.z));
    }

    public enum EnumTeleportType {
        STRUCTURES, RANDOM_PLACE, DONT
    }
}
