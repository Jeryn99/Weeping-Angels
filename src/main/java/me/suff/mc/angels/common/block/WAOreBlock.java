package me.suff.mc.angels.common.block;

import net.minecraft.block.OreBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.IntRange;

import java.util.Random;

/* Created by Craig on 19/02/2021 */
public class WAOreBlock extends OreBlock {


    public WAOreBlock(Settings settings) {
        super(settings);
    }

    public WAOreBlock(Settings settings, IntRange experienceDropped) {
        super(settings, experienceDropped);
    }
}
