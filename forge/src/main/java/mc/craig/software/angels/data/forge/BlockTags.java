package mc.craig.software.angels.data.forge;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.blocks.WABlocks;
import mc.craig.software.angels.util.WATags;
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

        tag(net.minecraft.tags.BlockTags.MINEABLE_WITH_PICKAXE).add(WABlocks.KONTRON_ORE.get(), WABlocks.KONTRON_ORE_DEEPSLATE.get());
        tag(Tags.Blocks.NEEDS_WOOD_TOOL).add(WABlocks.KONTRON_ORE.get(), WABlocks.KONTRON_ORE_DEEPSLATE.get());
        tag(WATags.NO_BREAKING).add(Blocks.GLOWSTONE, Blocks.LAVA, Blocks.SEA_LANTERN, Blocks.MAGMA_BLOCK);
        tag(net.minecraft.tags.BlockTags.UNSTABLE_BOTTOM_CENTER).add(WABlocks.CHRONODYNE_GENERATOR.get());


    }

}
