package me.suff.mc.angels.util;

import me.suff.mc.angels.common.blockentity.CoffinTile;
import me.suff.mc.angels.common.objects.WASounds;
import me.suff.mc.angels.enums.WeepingAngelVariants;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

import java.util.Random;

import static me.suff.mc.angels.common.blockentity.CoffinTile.Coffin.*;

/* Created by Craig on 18/02/2021 */
public class AngelUtils {

    public static final Random RAND = new Random();

    public static void playBreakEvent(LivingEntity entity, BlockPos pos, BlockState blockState) {
        if (!entity.world.isClient) {
            ServerWorld serverWorld = (ServerWorld) entity.world;
            serverWorld.spawnParticles(new BlockStateParticleEffect(ParticleTypes.BLOCK, blockState), pos.getX(), pos.getY(), pos.getZ(), 0, 0, 0, 0, 0);
            entity.playSound(WASounds.LIGHT_BREAK, 1, 1.0F);
            ItemStack blockStack = new ItemStack(entity.world.getBlockState(pos).getBlock());
            ItemEntity itemEntity = new ItemEntity(entity.world, pos.getX(), pos.getY(), pos.getZ(), blockStack);
            serverWorld.spawnEntity(itemEntity);
            entity.world.setBlockState(pos, blockState);
        }
    }


    public static WeepingAngelVariants randomVarient() {
        int pick = RAND.nextInt(WeepingAngelVariants.values().length);
        return WeepingAngelVariants.values()[pick];
    }

    public static CoffinTile.Coffin randomCoffin() {
        CoffinTile.Coffin[] coffins = new CoffinTile.Coffin[]{NEW, WEATHERED, SLIGHTLY_WEATHERED, HEAVILY_WEATHERED};
        int pick = RAND.nextInt(coffins.length);
        return coffins[pick];
    }

    public static ServerWorld getRandomDimension(MinecraftServer minecraftServer) {
        Iterable< ServerWorld > keys = minecraftServer.getWorlds();
        for (ServerWorld key : keys) {
            if (RAND.nextBoolean() && !key.getRegistryKey().getValue().equals(DimensionType.THE_END_ID)) {
                return key;
            }
        }
        return minecraftServer.getWorld(World.OVERWORLD);
    }


    public static BlockPos getGoodY(ServerWorld world, BlockPos pos) {
        for (int y = world.getHeight(); y > 0; --y) {
            BlockPos newPos = new BlockPos(pos.getX(), y, pos.getZ());
            if (!isSafe(world, newPos) && !isPosBelowOrAboveWorld(world, newPos.getY())) {
                // return newPos;
            }
        }
        return world.getTopPosition(Heightmap.Type.MOTION_BLOCKING, pos);
    }

    public static boolean isPosBelowOrAboveWorld(World dim, int y) {
        if (dim.getRegistryKey() == World.NETHER) {
            return y <= 0 || y >= 126;
        }
        return y <= 0 || y >= 256;
    }

    public static boolean isSafe(World world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        return state.shouldSuffocate(world, pos) && !world.getBlockState(pos.down()).isAir();
    }
}
