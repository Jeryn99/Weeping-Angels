package me.swirtzly.minecraft.angels.data;

import me.swirtzly.minecraft.angels.common.WAObjects;
import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.Tag;

public class WABlockTags extends BlockTagsProvider {

    public WABlockTags(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void registerTags() {
        add(BlockTags.STONE_BRICKS, WAObjects.Blocks.ARM.get());
        add(BlockTags.STONE_BRICKS, WAObjects.Blocks.STATUE.get());
        add(BlockTags.STONE_BRICKS, WAObjects.Blocks.PLINTH.get());
    }

    public void add(ITag.INamedTag<Block> branch, Block block) {
        this.func_240522_a_(branch).func_240534_a_(block);
    }
}
