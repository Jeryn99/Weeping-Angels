package mc.craig.software.angels.data.forge;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.blocks.WABlocks;
import mc.craig.software.angels.util.WATags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class BlockTags extends BlockTagsProvider {


    public BlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, WeepingAngels.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
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
