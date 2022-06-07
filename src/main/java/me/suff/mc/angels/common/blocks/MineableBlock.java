package me.suff.mc.angels.common.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class MineableBlock extends Block {

    public MineableBlock(Material material, SoundType soundType, float hardness, float resistance) {
        super(Properties.of(material).strength(hardness, resistance).sound(soundType).requiresCorrectToolForDrops());
    }
}
