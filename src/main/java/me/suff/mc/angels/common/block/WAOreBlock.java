package me.suff.mc.angels.common.block;

import net.minecraft.block.OreBlock;
import net.minecraft.util.math.intprovider.UniformIntProvider;

/* Created by Craig on 19/02/2021 */
public class WAOreBlock extends OreBlock {


    public WAOreBlock(Settings settings) {
        super(settings);
    }

    public WAOreBlock(Settings settings, UniformIntProvider experienceDropped) {
        super(settings, experienceDropped);
    }
}
