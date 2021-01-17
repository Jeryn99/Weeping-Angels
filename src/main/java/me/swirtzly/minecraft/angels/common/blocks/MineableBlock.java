package me.swirtzly.minecraft.angels.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

public class MineableBlock extends Block {

    public MineableBlock(ResourceLocation lootLocation) {
        super(Properties.create(Material.ROCK).hardnessAndResistance(3).sound(SoundType.STONE).setRequiresTool());
        this.lootTable = lootLocation;
    }

}
