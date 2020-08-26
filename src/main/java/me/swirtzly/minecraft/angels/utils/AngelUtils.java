package me.swirtzly.minecraft.angels.utils;

import java.util.Random;

import me.swirtzly.minecraft.angels.common.WAObjects;
import me.swirtzly.minecraft.angels.common.entities.AngelEnums;
import me.swirtzly.minecraft.angels.common.entities.QuantumLockBaseEntity;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import me.swirtzly.minecraft.angels.data.WAItemTags;
import net.minecraft.block.AbstractBlock;
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
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.LootTable;
import net.minecraft.network.play.server.SSpawnParticlePacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class AngelUtils {

    public static Structure[] END_STRUCTURES = new Structure[]{Structure.field_236379_o_};
    public static Structure[] OVERWORLD_STRUCTURES = new Structure[]{

            Structure.field_236366_b_,
            Structure.field_236367_c_,
            Structure.field_236368_d_,
            Structure.field_236369_e_,
            Structure.field_236370_f_,
            Structure.field_236371_g_,
            Structure.field_236372_h_,
            Structure.field_236373_i_,
            Structure.field_236374_j_,
            Structure.field_236375_k_,
            Structure.field_236376_l_,
            Structure.field_236377_m_,
            Structure.field_236380_p_,
            Structure.field_236381_q_
    };

    public static Structure[] NETHER_STRUCTURES = new Structure[]{Structure.field_236383_s_, Structure.field_236382_r_, Structure.field_236378_n_};
    public static Random RAND = new Random();
   // static BiomeDictionary.Type[] BANNED = new BiomeDictionary.Type[]{BiomeDictionary.Type.VOID, BiomeDictionary.Type.WATER};

    /**
     * Method that detects whether a tile is the the view sight of viewer
     *
     * @param angel Angel involved (Used for checking if there is light around the angel)
     * @param angel The entity being watched by viewer
     */
    public static boolean isDarkForPlayer(QuantumLockBaseEntity angel, LivingEntity living) {
        return !living.isPotionActive(Effects.NIGHT_VISION) && angel.world.getLight(angel.getPosition()) <= 0;// && angel.world.getDimension().hasSkyLight() && !AngelUtils.handLightCheck(living);
    }

    public static void playBreakEvent(LivingEntity entity, BlockPos pos, Block blockState) {
        if (!entity.world.isRemote) {
            entity.playSound(WAObjects.Sounds.LIGHT_BREAK.get(), 0.5F, 1.0F);
            InventoryHelper.spawnItemStack(entity.world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(entity.world.getBlockState(pos).getBlock()));
            entity.world.setBlockState(pos, blockState.getDefaultState());
            entity.world.getPlayers().forEach(player -> {
                if (player instanceof ServerPlayerEntity) {
                    ServerPlayerEntity playerMP = (ServerPlayerEntity) player;
                    if (playerMP.getDistanceSq(pos.getX(), pos.getY(), pos.getZ()) < 45) {
                        playerMP.connection.sendPacket(new SSpawnParticlePacket(ParticleTypes.CRIT, false, pos.getX(), pos.getY(), pos.getZ(), 0, 0, 0, 1.0F, 11));
                    }
                }
            });
        }
    }

    /**
     * Checks if the entity has a item that emites light in their hand
     */
    public static boolean handLightCheck(LivingEntity player) {
        for (Item item : WAItemTags.HELD_LIGHT_ITEMS.getAllElements()) {
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
     * Sets up weeping angel spawns
     */
    public static void setUpSpawns() {
		/*
		 * for (String s : WAConfig.CONFIG.allowedBiomes.get()) { Biome biome =
		 * ForgeRegistries.BIOMES.getValue(new ResourceLocation(s)); if (biome != null)
		 * { if (!isABannedBiomeType(biome)) {
		 * WeepingAngels.LOGGER.info("Weeping angels will now spawn in [" +
		 * biome.getRegistryName() + "]");
		 * biome.getSpawns(EntityClassification.valueOf(WAConfig.CONFIG.spawnType.get())
		 * ).add((new Biome.SpawnListEntry(WAObjects.EntityEntries.WEEPING_ANGEL.get(),
		 * WAConfig.CONFIG.spawnWeight.get(), WAConfig.CONFIG.minSpawn.get(),
		 * WAConfig.CONFIG.maxSpawn.get()))); } } }
		 */
    }

	/*
	 * public static boolean isABannedBiomeType(Biome biome) { for
	 * (BiomeDictionary.Type check : BANNED) { if (BiomeDictionary.hasType(biome,
	 * check)) { WeepingAngels.LOGGER.info("[" + biome.getRegistryName() +
	 * "] has the banned Biome Type [" + check.getName() +
	 * "], Weeping Angels will not spawn here, ever."); return true; } } return
	 * false; }
	 */
	
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
		if (stack.getItem().isIn(WAItemTags.HELD_LIGHT_ITEMS)) {
			angel.entityDropItem(stack);
			stack.shrink(1);
			return true;
		}
		
		return false;
	}
	
	public static AngelEnums.AngelType randomType() {
		int pick = RAND.nextInt(AngelEnums.AngelType.values().length);
		return AngelEnums.AngelType.values()[pick];
	}
	
	public enum EnumTeleportType {
		STRUCTURES, RANDOM_PLACE, DONT
	}

    public static int getFortuneModifier(LivingEntity entityIn) {
        return EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.FORTUNE, entityIn);
    }


    public static void dropEntityLoot(Entity target, PlayerEntity attacker) {
        LivingEntity targeted = (LivingEntity) target;
        ResourceLocation resourcelocation = targeted.getLootTableResourceLocation();
        LootTable loot_table = target.world.getServer().getLootTableManager().getLootTableFromLocation(resourcelocation);
        LootContext.Builder lootcontext$builder = getLootContextBuilder(true, DamageSource.GENERIC, targeted, attacker);
        LootContext ctx = lootcontext$builder.build(LootParameterSets.ENTITY);
        loot_table.generate(ctx).forEach(target::entityDropItem);
    }

    public static LootContext.Builder getLootContextBuilder(boolean p_213363_1_, DamageSource damageSourceIn, LivingEntity entity, PlayerEntity attacker) {
        LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerWorld) entity.world)).withRandom(entity.world.rand).withParameter(LootParameters.THIS_ENTITY, entity).withParameter(LootParameters.field_237457_g_, entity.getPositionVec()).withParameter(LootParameters.DAMAGE_SOURCE, damageSourceIn).withNullableParameter(LootParameters.KILLER_ENTITY, damageSourceIn.getTrueSource()).withNullableParameter(LootParameters.DIRECT_KILLER_ENTITY, damageSourceIn.getImmediateSource());
        if (p_213363_1_ && entity.getAttackingEntity() != null) {
            attacker = (PlayerEntity) entity.getAttackingEntity();
            lootcontext$builder = lootcontext$builder.withParameter(LootParameters.LAST_DAMAGE_PLAYER, attacker).withLuck(attacker.getLuck());
        }
        return lootcontext$builder;
    }
}
