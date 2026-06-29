package net.toblexson.stoned.dataproviders;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

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

        bricks(family.bricksBlock, family.block);
        stairs(family.bricksStairs, family.bricksBlock);
        slab(family.bricksSlab, family.bricksBlock);
        wall(family.bricksWall, family.bricksBlock);
    }

    private void wall(DeferredBlock<WallBlock> wall, DeferredBlock<Block> ingredient)
    {
        wallBuilder(RecipeCategory.BUILDING_BLOCKS, wall.get(), Ingredient.of(ingredient))
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .group(getSimpleRecipeName(wall))
                .save(output);
    }

    private void slab(DeferredBlock<SlabBlock> slab, DeferredBlock<Block> ingredient)
    {
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, slab.get(), Ingredient.of(ingredient))
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .group(getSimpleRecipeName(slab))
                .save(output);
    }

    private void stairs(DeferredBlock<StairBlock> stairs, DeferredBlock<Block> ingredient)
    {
        stairBuilder(stairs.get(), Ingredient.of(ingredient))
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .group(getSimpleRecipeName(stairs))
                .save(output);
    }

    private void bricks(DeferredBlock<?> bricks, DeferredBlock<?> ingredient)
    {
        shaped(RecipeCategory.BUILDING_BLOCKS, bricks)
                .pattern("##")
                .pattern("##")
                .define('#', ingredient)
                .unlockedBy(getHasName(ingredient), this.has(ingredient))
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
