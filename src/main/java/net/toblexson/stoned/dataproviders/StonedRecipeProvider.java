package net.toblexson.stoned.dataproviders;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.toblexson.stoned.Stoned;

import java.util.concurrent.CompletableFuture;

import static net.toblexson.stoned.registers.StonedBlocks.*;

public class StonedRecipeProvider extends RecipeProvider
{
    public StonedRecipeProvider(HolderLookup.Provider registries, RecipeOutput output)
    {
        super(registries, output);
    }

    @Override
    protected void buildRecipes()
    {
        /* EXPANDED VANILLA */
        wall(STONE_WALL, Blocks.STONE);
        stairs(CRACKED_STONE_BRICKS_STAIRS, Blocks.CRACKED_STONE_BRICKS);
        slab(CRACKED_STONE_BRICKS_SLAB, Blocks.CRACKED_STONE_BRICKS);
        wall(CRACKED_STONE_BRICKS_WALL, Blocks.CRACKED_STONE_BRICKS);
        stairs(CHISELED_STONE_BRICKS_STAIRS, Blocks.CHISELED_STONE_BRICKS);
        slab(CHISELED_STONE_BRICKS_SLAB, Blocks.CHISELED_STONE_BRICKS);
        wall(CHISELED_STONE_BRICKS_WALL, Blocks.CHISELED_STONE_BRICKS);
        stairs(SMOOTH_STONE_STAIRS, Blocks.SMOOTH_STONE);
        wall(SMOOTH_STONE_WALL, Blocks.SMOOTH_STONE);

        /* ADDITIONAL */
        familyRecipes(CHALK_FAMILY);
        familyRecipes(LIMESTONE_FAMILY);
        familyRecipes(SLATE_FAMILY);
    }

    private void familyRecipes(StoneFamily family)
    {
        /* BASIC */
        stairs(family.stairs, family.block);
        slab(family.slab, family.block);
        wall(family.wall, family.block);
        smelting(family.polishedBlock, family.block);
        stonecutter(family.block,family.stairs, family.slab, family.wall);

        /* POLISHED */
        bricks(family.bricksBlock, family.polishedBlock);
        stairs(family.polishedStairs, family.polishedBlock);
        slab(family.polishedSlab, family.polishedBlock);
        wall(family.polishedWall, family.polishedBlock);
        stonecutter(family.polishedBlock, family.bricksBlock, family.polishedStairs, family.polishedSlab, family.polishedWall);

        /* BRICKS */
        stairs(family.bricksStairs, family.bricksBlock);
        slab(family.bricksSlab, family.bricksBlock);
        wall(family.bricksWall, family.bricksBlock);
        stonecutter(family.bricksBlock, family.bricksStairs, family.bricksSlab, family.bricksWall);
    }

    private void stonecutter(ItemLike input, ItemLike... outputs)
    {
        for (ItemLike output : outputs)
        {
            if (output instanceof SlabBlock)
                stonecutter(input, output, 2);
            else
                stonecutter(input, output, 1);
        }
    }

    private void stonecutter(ItemLike input, ItemLike output, int count)
    {
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, output, input, count);
    }

    private void smelting(DeferredBlock<Block> result, DeferredBlock<Block> input)
    {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), RecipeCategory.MISC, CookingBookCategory.MISC, result, 1.0f, 200)
                .group(getSimpleRecipeName(result))
                .unlockedBy(getHasName(input), has(input))
                .save(output, Stoned.MOD_ID + ":" + getItemName(result) + "_from_smelting_" + getItemName(input));
    }

    private void wall(DeferredBlock<WallBlock> wall, ItemLike input)
    {
        wallBuilder(RecipeCategory.BUILDING_BLOCKS, wall.get(), Ingredient.of(input))
                .unlockedBy(getHasName(input), has(input))
                .group(getSimpleRecipeName(wall))
                .save(output);
    }

    private void slab(DeferredBlock<SlabBlock> slab, ItemLike input)
    {
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, slab.get(), Ingredient.of(input))
                .unlockedBy(getHasName(input), has(input))
                .group(getSimpleRecipeName(slab))
                .save(output);
    }

    private void stairs(DeferredBlock<StairBlock> stairs, ItemLike input)
    {
        stairBuilder(stairs.get(), Ingredient.of(input))
                .unlockedBy(getHasName(input), has(input))
                .group(getSimpleRecipeName(stairs))
                .save(output);
    }

    private void bricks(DeferredBlock<?> bricks, ItemLike input)
    {
        shaped(RecipeCategory.BUILDING_BLOCKS, bricks)
                .pattern("##")
                .pattern("##")
                .define('#', input)
                .unlockedBy(getHasName(input), this.has(input))
                .group(getSimpleRecipeName(bricks))
                .save(output);
    }


    public static class Runner extends RecipeProvider.Runner
    {

        protected Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
        {
            super(output, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output)
        {
            return new StonedRecipeProvider(registries, output);
        }

        @Override
        public String getName()
        {
            return "Stoned Recipes";
        }
    }
}
