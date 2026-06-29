package net.toblexson.stoned.dataproviders;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
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
        familyRecipes(CHALK_FAMILY);
        familyRecipes(LIMESTONE_FAMILY);
    }

    private void familyRecipes(StoneFamily family)
    {
        stairs(family.stairs, family.block);
        slab(family.slab, family.block);
        wall(family.wall, family.block);

        smelting(family.polishedBlock, family.block);

        bricks(family.bricksBlock, family.polishedBlock);
        stairs(family.bricksStairs, family.bricksBlock);
        slab(family.bricksSlab, family.bricksBlock);
        wall(family.bricksWall, family.bricksBlock);
    }

    private void smelting(DeferredBlock<Block> result, DeferredBlock<Block> input)
    {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), RecipeCategory.MISC, CookingBookCategory.MISC, result, 1.0f, 200)
                .group(getSimpleRecipeName(result))
                .unlockedBy(getHasName(input), has(input))
                .save(output, Stoned.MOD_ID + ":" + getItemName(result) + "_from_smelting_" + getItemName(input));
    }

    private void wall(DeferredBlock<WallBlock> wall, DeferredBlock<Block> input)
    {
        wallBuilder(RecipeCategory.BUILDING_BLOCKS, wall.get(), Ingredient.of(input))
                .unlockedBy(getHasName(input), has(input))
                .group(getSimpleRecipeName(wall))
                .save(output);
    }

    private void slab(DeferredBlock<SlabBlock> slab, DeferredBlock<Block> input)
    {
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, slab.get(), Ingredient.of(input))
                .unlockedBy(getHasName(input), has(input))
                .group(getSimpleRecipeName(slab))
                .save(output);
    }

    private void stairs(DeferredBlock<StairBlock> stairs, DeferredBlock<Block> input)
    {
        stairBuilder(stairs.get(), Ingredient.of(input))
                .unlockedBy(getHasName(input), has(input))
                .group(getSimpleRecipeName(stairs))
                .save(output);
    }

    private void bricks(DeferredBlock<?> bricks, DeferredBlock<?> input)
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
