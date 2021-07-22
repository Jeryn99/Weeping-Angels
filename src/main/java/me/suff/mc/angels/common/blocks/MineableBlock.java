package me.suff.mc.angels.common.blocks;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class MineableBlock extends Block {

    public MineableBlock(ResourceLocation lootLocation) {
        super(Properties.of(Material.STONE).strength(3).sound(SoundType.STONE).requiresCorrectToolForDrops());
        this.drops = lootLocation;
    }
}
