package dev.jeryn.angels.data.forge;


import dev.jeryn.angels.common.blocks.WABlocks;
import dev.jeryn.angels.common.items.WAItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class RecipeProvider extends net.minecraft.data.recipes.RecipeProvider {


    public RecipeProvider(PackOutput arg) {
        super(arg);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, WAItems.TIMEY_WIMEY_DETECTOR.get()).pattern("#R#").pattern("COV").pattern("BBB").define('#', Blocks.REPEATER.asItem()).define('R', Blocks.REDSTONE_TORCH.asItem()).define('C', Items.CLOCK).define('O', ItemTags.PLANKS).define('V', Items.REDSTONE).define('B', Blocks.BRICKS).unlockedBy("has_clock", has(Items.CLOCK)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, WABlocks.STATUE.get()).pattern("#").pattern("S").define('#', Blocks.STONE.asItem()).define('S', Blocks.SMOOTH_STONE.asItem()).unlockedBy("has_stone", has(Tags.Items.STONE)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, WABlocks.COFFIN.get()).pattern("WWW").pattern("WBW").pattern("WWW").define('W', ItemTags.LOGS).define('B', Items.BONE.asItem()).unlockedBy("has_bone", has(Tags.Items.BONES)).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, WAItems.CHISEL.get()).requires(ItemTags.STONE_CRAFTING_MATERIALS).requires(Items.STRING).requires(Items.STICK).unlockedBy("has_stone", has(ItemTags.STONE_CRAFTING_MATERIALS)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, WABlocks.PLINTH.get().asItem()).pattern("#").pattern("S").pattern("Q").define('#', Blocks.STONE.asItem()).define('S', Blocks.SMOOTH_STONE.asItem()).define('Q', Blocks.QUARTZ_PILLAR).unlockedBy("has_crafting_table", has(Tags.Items.STONE)).save(consumer);

    }
}