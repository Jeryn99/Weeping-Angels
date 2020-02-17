package me.swirtzly.angels.utils;

import com.google.common.collect.Lists;
import me.swirtzly.angels.config.WAConfig;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

import java.util.ArrayList;
import java.util.Random;

public class WATeleporter {

    public static int yCoordSanity(World world, BlockPos spawn) {
        for (int i = 0; i < world.getActualHeight(); i++) {
            BlockPos pos = spawn.up(i);
            if (world.getBlockState(pos).getMaterial() != Material.LAVA && !world.getBlockState(pos).causesSuffocation(world, pos) && !world.getBlockState(pos.up()).causesSuffocation(world, pos.up())) {
                return pos.getY();
            }
        }
        return 65;
    }

    public static DimensionType getRandomDimension(Random rand) {
        Iterable<DimensionType> dimensions = DimensionType.getAll();
        ArrayList<DimensionType> allowedDimensions = Lists.newArrayList(DimensionType.getAll());

        for (DimensionType dimension : dimensions) {
            for (String dimName : WAConfig.CONFIG.notAllowedDimensions.get()) {
                if (dimension.getRegistryName().toString().equalsIgnoreCase(dimName)) {
                    allowedDimensions.remove(dimension);
                }
            }
        }

        return allowedDimensions.get(rand.nextInt(allowedDimensions.size()));
    }

    public static void handleStructures(ServerPlayerEntity player) {

        String[] targetStructure = null;

        switch (player.world.dimension.getType().getId()) {
            case 0:
                targetStructure = AngelUtils.OVERWORLD_STRUCTURES;
                break;

            case 1:
                targetStructure = AngelUtils.END_STRUCTURES;
                break;

            case -1:
                targetStructure = AngelUtils.NETHER_STRUCTURES;
                break;
        }

        if (targetStructure != null) {
            BlockPos bPos = player.getEntityWorld().findNearestStructure(targetStructure[player.world.rand.nextInt(targetStructure.length)], player.getPosition(), Integer.MAX_VALUE, false);
            if (bPos != null) {
                player.teleport(player.getServerWorld(), bPos.getX(), yCoordSanity(player.world, bPos), bPos.getZ(), player.rotationYaw, player.rotationPitch);
            }
        }
    }



}
