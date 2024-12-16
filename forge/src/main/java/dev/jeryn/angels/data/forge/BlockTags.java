package dev.jeryn.angels.data.forge;

import dev.jeryn.angels.WeepingAngels;
import dev.jeryn.angels.common.blocks.WABlocks;
import dev.jeryn.angels.util.WATags;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;

public class BlockTags extends net.minecraft.data.tags.BlockTagsProvider {

    public BlockTags(DataGenerator dataGenerator, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, WeepingAngels.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {

        for (Map.Entry<ResourceKey<Block>, Block> blocksEntry : ForgeRegistries.BLOCKS.getEntries()) {
            Block block = blocksEntry.getValue();

            if (block instanceof FireBlock || block instanceof AirBlock) {
                tag(WATags.NO_BREAKING).add(block);
            }
        }

        tag(WATags.NO_BREAKING).add(Blocks.GLOWSTONE, Blocks.LAVA, Blocks.SEA_LANTERN, Blocks.MAGMA_BLOCK);
        tag(net.minecraft.tags.BlockTags.UNSTABLE_BOTTOM_CENTER).add(WABlocks.CHRONODYNE_GENERATOR.get());


    }

}