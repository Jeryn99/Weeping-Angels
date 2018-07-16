package me.sub.angels.utils;

import com.google.common.collect.Lists;
import me.sub.angels.common.entities.EntityQuantumLockBase;
import me.sub.angels.common.entities.EntityWeepingAngel;
import me.sub.angels.config.WAConfig;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.Collection;

public class AngelUtils {

    public static ArrayList<Item> lightItems = new ArrayList<Item>();

    /**
     * Method that detects whether a tile is the the view sight of viewer
     *
     * @param viewer The viewer entity
     * @param tile   The tile being watched by viewer
     */
    public static boolean isInSightTile(EntityLivingBase viewer, TileEntity tile) {
        double dx = tile.getPos().getX() - viewer.posX;
        double dz;
        for (dz = tile.getPos().getX() - viewer.posZ; dx * dx + dz * dz < 1.0E-4D; dz = (Math.random() - Math.random()) * 0.01D) {
            dx = (Math.random() - Math.random()) * 0.01D;
        }
        while (viewer.rotationYaw > 360) {
            viewer.rotationYaw -= 360;
        }
        while (viewer.rotationYaw < -360) {
            viewer.rotationYaw += 360;
        }
        float yaw = (float) (Math.atan2(dz, dx) * 180.0D / Math.PI) - viewer.rotationYaw;
        yaw = yaw - 90;
        while (yaw < -180) {
            yaw += 360;
        }
        while (yaw >= 180) {
            yaw -= 360;
        }

        return yaw < 60 && yaw > -60;
    }

    public static boolean isInSight(EntityLivingBase livingBase, EntityLivingBase angel) {
        double dx = angel.posX - livingBase.posX;
        double dz;
        for (dz = angel.posZ - livingBase.posZ; dx * dx + dz * dz < 1.0E-4D; dz = (Math.random() - Math.random()) * 0.01D) {
            dx = (Math.random() - Math.random()) * 0.01D;
        }
        while (livingBase.rotationYaw > 360) {
            livingBase.rotationYaw -= 360;
        }
        while (livingBase.rotationYaw < -360) {
            livingBase.rotationYaw += 360;
        }
        float yaw = (float) (Math.atan2(dz, dx) * 180.0D / Math.PI) - livingBase.rotationYaw;
        yaw = yaw - 90;
        while (yaw < -180) {
            yaw += 360;
        }
        while (yaw >= 180) {
            yaw -= 360;
        }

        return yaw < 60 && yaw > -60 && livingBase.canEntityBeSeen(angel);
    }

    public static boolean isDarkForPlayer(EntityQuantumLockBase angel, EntityPlayer living) {
        return !living.isPotionActive(MobEffects.NIGHT_VISION) && angel.world.getLight(angel.getPosition()) == 0 && !AngelUtils.handLightCheck(living);
    }

    public static void setupLightItems() {
        for (Block block : ForgeRegistries.BLOCKS.getValuesCollection()) {

            if (block.getLightValue(block.getDefaultState()) > 7) {
                lightItems.add(Item.getItemFromBlock(block));
            }

            lightItems.add(Item.getItemFromBlock(Blocks.REDSTONE_TORCH));
        }
    }

    public static boolean handLightCheck(EntityPlayer player) {
        for (Item item : lightItems) {
            if (PlayerUtils.isInEitherHand(player, item)) {
                return true;
            }
        }
        return false;
    }

    // Spawn Set up
    public static void setUpSpawns() {
        Collection<Biome> biomes = ForgeRegistries.BIOMES.getValuesCollection();
        ArrayList<Biome> spawn = Lists.newArrayList();
        spawn.addAll(biomes);

        for (String rs : WAConfig.spawn.notAllowedBiomes) {
            if (Biome.REGISTRY.containsKey(new ResourceLocation(rs))) {
                Biome removedBiome = Biome.REGISTRY.getObject(new ResourceLocation(rs));
                spawn.remove(removedBiome);
            }
        }

        for (Biome biome : spawn) {
            if (biome != null) {
                EntityRegistry.addSpawn(EntityWeepingAngel.class, WAConfig.spawn.spawnProbability, WAConfig.spawn.minimumSpawn, WAConfig.spawn.maximumSpawn, WAConfig.spawn.spawnType, biome);
            }
        }
    }
	
}
