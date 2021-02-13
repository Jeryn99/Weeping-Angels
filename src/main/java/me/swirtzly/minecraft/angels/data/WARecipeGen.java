package me.swirtzly.minecraft.angels.data;

import me.swirtzly.minecraft.angels.WeepingAngels;
import me.swirtzly.minecraft.angels.common.WAObjects;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;

import java.util.function.Consumer;

public class WARecipeGen extends RecipeProvider {
    public WARecipeGen(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer< IFinishedRecipe > consumer) {
        ShapedRecipeBuilder.shapedRecipe(WAObjects.Blocks.PLINTH.get().asItem()).patternLine("#").patternLine("S").patternLine("Q").key('#', Blocks.STONE.asItem()).key('S', Blocks.SMOOTH_STONE.asItem()).key('Q', Blocks.QUARTZ_PILLAR).addCriterion("has_crafting_table", hasItem(Tags.Items.STONE)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(WAObjects.Items.TIMEY_WIMEY_DETECTOR.get()).patternLine("#R#").patternLine("COV").patternLine("BBB").key('#', Blocks.REPEATER.asItem()).key('R', Blocks.REDSTONE_TORCH.asItem()).key('C', Items.CLOCK).key('O', Blocks.SPRUCE_PLANKS).key('V', Items.REDSTONE).key('B', Blocks.BRICKS).addCriterion("has_clock", hasItem(Items.CLOCK)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(WAObjects.Blocks.STATUE.get()).patternLine("#").patternLine("S").key('#', Blocks.STONE.asItem()).key('S', Blocks.SMOOTH_STONE.asItem()).addCriterion("has_stone", hasItem(Tags.Items.STONE)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(WAObjects.Blocks.COFFIN.get()).patternLine("WWW").patternLine("WBW").patternLine("WWW").key('W', ItemTags.LOGS).key('B', Items.BONE.asItem()).addCriterion("has_bone", hasItem(Tags.Items.BONES)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(WAObjects.Items.CHRONODYNE_GENERATOR.get()).patternLine("IPI").patternLine("EKE").patternLine("IRI").key('I', Items.IRON_INGOT).key('E', Items.ENDER_PEARL.asItem()).key('R', Items.REDSTONE).key('P', Blocks.STONE_PRESSURE_PLATE).key('K', WAObjects.Items.KONTRON_INGOT.get()).addCriterion("has_kontron", hasItem(WAObjects.Blocks.KONTRON_ORE.get())).build(consumer);
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(WAObjects.Blocks.KONTRON_ORE.get().asItem()), WAObjects.Items.KONTRON_INGOT.get(), 0.7F, 300).addCriterion("has_any_kontron", hasItem(WAObjects.Blocks.KONTRON_ORE.get().asItem())).build(consumer, new ResourceLocation(WeepingAngels.MODID, "smelt_kontron"));
        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(WAObjects.Blocks.KONTRON_ORE.get().asItem()), WAObjects.Items.KONTRON_INGOT.get(), 0.8F, 150).addCriterion("has_any_kontron", hasItem(WAObjects.Blocks.KONTRON_ORE.get().asItem())).build(consumer, new ResourceLocation(WeepingAngels.MODID, "blast_kontron"));
    }
}
