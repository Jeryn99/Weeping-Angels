package mc.craig.software.angels.data.neoforge;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.blocks.WABlocks;
import mc.craig.software.angels.registry.RegistryHolder;
import mc.craig.software.angels.util.WATags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BlockTags extends BlockTagsProvider {


    public BlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, WeepingAngels.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        for (RegistryHolder<Block> blocksEntry : WABlocks.BLOCKS.getEntries()) {
            Block block = blocksEntry.get();

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
