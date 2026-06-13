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
import net.toblexson.stoned.registers.StonedBlocks;

import java.util.concurrent.CompletableFuture;

public class StonedRecipeProvider extends RecipeProvider
{
    public StonedRecipeProvider(HolderLookup.Provider registries, RecipeOutput output)
    {
        super(registries, output);
    }

    @Override
    protected void buildRecipes()
    {
        //Chalk
        stairs(StonedBlocks.CHALK_STAIRS, StonedBlocks.CHALK);
        slab(StonedBlocks.CHALK_SLAB, StonedBlocks.CHALK);
        wall(StonedBlocks.CHALK_WALL, StonedBlocks.CHALK);

        bricks(StonedBlocks.CHALK_BRICKS, StonedBlocks.CHALK);
        stairs(StonedBlocks.CHALK_BRICKS_STAIRS, StonedBlocks.CHALK_BRICKS);
        slab(StonedBlocks.CHALK_BRICKS_SLAB, StonedBlocks.CHALK_BRICKS);
        wall(StonedBlocks.CHALK_BRICKS_WALL, StonedBlocks.CHALK_BRICKS);
        
        //Limestone
        stairs(StonedBlocks.LIMESTONE_STAIRS, StonedBlocks.LIMESTONE);
        slab(StonedBlocks.LIMESTONE_SLAB, StonedBlocks.LIMESTONE);
        wall(StonedBlocks.LIMESTONE_WALL, StonedBlocks.LIMESTONE);

        bricks(StonedBlocks.LIMESTONE_BRICKS, StonedBlocks.LIMESTONE);
        stairs(StonedBlocks.LIMESTONE_BRICKS_STAIRS, StonedBlocks.LIMESTONE_BRICKS);
        slab(StonedBlocks.LIMESTONE_BRICKS_SLAB, StonedBlocks.LIMESTONE_BRICKS);
        wall(StonedBlocks.LIMESTONE_BRICKS_WALL, StonedBlocks.LIMESTONE_BRICKS);
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
