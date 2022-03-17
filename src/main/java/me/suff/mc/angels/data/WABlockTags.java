package me.suff.mc.angels.data;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.utils.AngelUtil;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

import static net.minecraft.tags.BlockTags.NEEDS_STONE_TOOL;

public class WABlockTags extends BlockTagsProvider {


    public WABlockTags(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, WeepingAngels.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        add(Tags.Blocks.ORES, WAObjects.Blocks.KONTRON_ORE.get(), WAObjects.Blocks.KONTRON_ORE_DEEPSLATE.get());
        add(Tags.Blocks.ORES_IN_GROUND_STONE, WAObjects.Blocks.KONTRON_ORE.get(), WAObjects.Blocks.KONTRON_ORE_DEEPSLATE.get());
        add(NEEDS_STONE_TOOL, WAObjects.Blocks.KONTRON_ORE.get(), WAObjects.Blocks.KONTRON_ORE_DEEPSLATE.get());

        add(BlockTags.STONE_BRICKS, WAObjects.Blocks.SNOW_ANGEL.get(), WAObjects.Blocks.STATUE.get(), WAObjects.Blocks.PLINTH.get());

        add(AngelUtil.BANNED_BLOCKS, Blocks.MAGMA_BLOCK, Blocks.GLOWSTONE, Blocks.SEA_LANTERN);

        add(BlockTags.MINEABLE_WITH_PICKAXE, WAObjects.Blocks.KONTRON_ORE.get(), WAObjects.Blocks.KONTRON_ORE_DEEPSLATE.get(), WAObjects.Blocks.PLINTH.get(), WAObjects.Blocks.STATUE.get(), WAObjects.Blocks.SNOW_ANGEL.get());


        for (Block block : ForgeRegistries.BLOCKS.getValues()) {
            if (Objects.requireNonNull(block.getRegistryName()).getNamespace().contains("tardis")) continue;

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

    public void add(TagKey<Block> branch, Block block) {
        this.tag(branch).add(block);
    }

    public void add(TagKey<Block> branch, Block... block) {
        this.tag(branch).add(block);
    }
}
