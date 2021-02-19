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
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

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
        int pick = RAND.nextInt(CoffinTile.Coffin.values().length);
        return CoffinTile.Coffin.values()[pick];
    }
}
