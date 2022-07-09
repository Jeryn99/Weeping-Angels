package craig.software.mc.angels.data;

import craig.software.mc.angels.WeepingAngels;
import craig.software.mc.angels.common.WAObjects;
import craig.software.mc.angels.utils.AngelUtil;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.material.Material;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class WABlockTags extends BlockTagsProvider {


    public WABlockTags(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, WeepingAngels.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        add(BlockTags.STONE_BRICKS, WAObjects.Blocks.SNOW_ANGEL.get(), WAObjects.Blocks.STATUE.get(), WAObjects.Blocks.PLINTH.get());
        add(AngelUtil.BANNED_BLOCKS, Blocks.MAGMA_BLOCK, Blocks.GLOWSTONE, Blocks.SEA_LANTERN, WAObjects.Blocks.COFFIN.get());

        for (Block block : ForgeRegistries.BLOCKS.getValues()) {
            if (block.getRegistryName().getNamespace().contains("tardis")) continue;
            if (block.defaultBlockState().getMaterial() == Material.AIR || block instanceof FireBlock) {
                add(AngelUtil.BANNED_BLOCKS, block);
            }

            if (block instanceof FlowerPotBlock) {
                add(AngelUtil.POTTED_PLANTS, block);
            }

            if (!block.defaultBlockState().canOcclude()) {
                add(AngelUtil.ANGEL_IGNORE, block);
            }
        }

    }

    public void add(ITag.INamedTag<Block> branch, Block block) {
        this.tag(branch).add(block);
    }

    public void add(ITag.INamedTag<Block> branch, Block... block) {
        this.tag(branch).add(block);
    }
}
