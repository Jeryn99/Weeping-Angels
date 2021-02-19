package me.suff.mc.angels.common.block;

import net.minecraft.block.OreBlock;

import java.util.Random;

/* Created by Craig on 19/02/2021 */
public class WAOreBlock extends OreBlock {
    public WAOreBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected int getExperienceWhenMined(Random random) {
        return random.nextInt(4);
    }


}
