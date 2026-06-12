package net.toblexson.stoned.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.toblexson.stoned.registers.ModBlocks;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder
{
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup)
    {
        super(output, lookup);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput output)
    {
        //Chalk
        stairs(ModBlocks.CHALK_STAIRS, ModBlocks.CHALK, output);
        slab(ModBlocks.CHALK_SLAB, ModBlocks.CHALK, output);
        wall(ModBlocks.CHALK_WALL, ModBlocks.CHALK, output);

        //Chalk Bricks
        bricks(ModBlocks.CHALK_BRICKS, ModBlocks.CHALK, output);
        stairs(ModBlocks.CHALK_BRICKS_STAIRS, ModBlocks.CHALK_BRICKS, output);
        slab(ModBlocks.CHALK_BRICKS_SLAB, ModBlocks.CHALK_BRICKS, output);
        wall(ModBlocks.CHALK_BRICKS_WALL, ModBlocks.CHALK_BRICKS, output);
    }

    private static void wall(DeferredBlock<WallBlock> wall, DeferredBlock<Block> ingredient, RecipeOutput output)
    {
        wall(output, RecipeCategory.BUILDING_BLOCKS, wall.get(), ingredient.get());
    }

    private void slab(DeferredBlock<SlabBlock> slab, DeferredBlock<Block> ingredient, RecipeOutput output)
    {
        slab(output, RecipeCategory.BUILDING_BLOCKS, slab.get(), ingredient.get());
    }

    private static void stairs(DeferredBlock<StairBlock> stairs, DeferredBlock<Block> ingredient, RecipeOutput output)
    {
        stairBuilder(stairs.get(), Ingredient.of(ingredient))
                .unlockedBy(getHasName(ingredient), has(ingredient)).save(output);
    }

    private static void bricks(DeferredBlock<?> bricks, DeferredBlock<?> ingredient, RecipeOutput output)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, bricks.get())
                .pattern("##")
                .pattern("##")
                .define('#', ingredient.get())
                .unlockedBy(getHasName(ingredient), has(ingredient)).save(output);
    }
}
