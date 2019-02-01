package me.sub.angels.utils;

import me.sub.angels.common.entities.EntityQuantumLockBase;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.LightType;

import java.util.ArrayList;

public class AngelUtils {

    public static ArrayList<Item> lightItems = new ArrayList<Item>();

    /**
     * Method that detects whether a tile is the the view sight of viewer
     *
     * @param viewer The viewer entity
     * @param tile   The tile being watched by viewer
     */
    public static boolean isInSightTile(LivingEntity viewer, BlockEntity tile) {
        double dx = tile.getPos().getX() - viewer.x;
        double dz;
        for (dz = tile.getPos().getX() - viewer.z; dx * dx + dz * dz < 1.0E-4D; dz = (Math.random() - Math.random()) * 0.01D) {
            dx = (Math.random() - Math.random()) * 0.01D;
        }
        while (viewer.yaw > 360) {
            viewer.yaw -= 360;
        }
        while (viewer.yaw < -360) {
            viewer.yaw += 360;
        }
        float yaw = (float) (Math.atan2(dz, dx) * 180.0D / Math.PI) - viewer.yaw;
        yaw = yaw - 90;
        while (yaw < -180) {
            yaw += 360;
        }
        while (yaw >= 180) {
            yaw -= 360;
        }

        return yaw < 60 && yaw > -60;
    }

    public static boolean isInSight(LivingEntity livingBase, LivingEntity angel) {
        double dx = angel.x - livingBase.x;
        double dz;
        for (dz = angel.z - livingBase.z; dx * dx + dz * dz < 1.0E-4D; dz = (Math.random() - Math.random()) * 0.01D) {
            dx = (Math.random() - Math.random()) * 0.01D;
        }
        while (livingBase.yaw > 360) {
            livingBase.yaw -= 360;
        }
        while (livingBase.yaw < -360) {
            livingBase.yaw += 360;
        }
        float yaw = (float) (Math.atan2(dz, dx) * 180.0D / Math.PI) - livingBase.yaw;
        yaw = yaw - 90;
        while (yaw < -180) {
            yaw += 360;
        }
        while (yaw >= 180) {
            yaw -= 360;
        }

        return yaw < 60 && yaw > -60 && livingBase.canSee(angel);
    }

    public static boolean isDarkForPlayer(EntityQuantumLockBase angel, PlayerEntity living) {
        return !living.hasPotionEffect(StatusEffects.NIGHT_VISION) && angel.world.getLightLevel(LightType.BLOCK_LIGHT, angel.getPos()) == 0 && angel.world.getLightLevel(LightType.SKY_LIGHT, angel.getPos()) == 0 && !AngelUtils.handLightCheck(living);
    }

    public static void setupLightItems() {
//        for (Block block : ForgeRegistries.BLOCKS.getValuesCollection()) {
//
//            if (block.getLightValue(block.getDefaultState()) > 7) {
//                lightItems.add(Item.getItemFromBlock(block));
//            }
//
//            lightItems.add(Item.getItemFromBlock(Blocks.REDSTONE_TORCH));
//        }
    }

    public static boolean handLightCheck(PlayerEntity player) {
        for (Item item : lightItems) {
            if (PlayerUtils.isInEitherHand(player, item)) {
                return true;
            }
        }
        return false;
    }

    // Spawn Set up
    //TODO fabric
//    public static void setUpSpawns() {
//        Collection<Biome> biomes = ForgeRegistries.BIOMES.getValuesCollection();
//        ArrayList<Biome> spawn = Lists.newArrayList();
//        spawn.addAll(biomes);
//
//        for (String rs : WAConfig.spawn.notAllowedBiomes) {
//            if (Biome.REGISTRY.containsKey(new ResourceLocation(rs))) {
//                Biome removedBiome = Biome.REGISTRY.getObject(new ResourceLocation(rs));
//                spawn.remove(removedBiome);
//            }
//        }
//
//        for (Biome biome : spawn) {
//            if (biome != null) {
//                EntityRegistry.addSpawn(EntityWeepingAngel.class, WAConfig.spawn.spawnProbability, WAConfig.spawn.minimumSpawn, WAConfig.spawn.maximumSpawn, WAConfig.spawn.spawnType, biome);
//            }
//        }
//    }
	
}
