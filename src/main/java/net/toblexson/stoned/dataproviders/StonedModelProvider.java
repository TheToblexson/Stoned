package net.toblexson.stoned.dataproviders;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.toblexson.stoned.Stoned;

import static net.toblexson.stoned.registers.StonedBlocks.*;

public class StonedModelProvider extends ModelProvider
{
    public StonedModelProvider(PackOutput output)
    {
        super(output, Stoned.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels)
    {
        /* ITEMS */
        //itemModels.generateFlatItem(item, ModelTemplates.FLAT_ITEM);

        /* BLOCKS */
        blockModels.familyWithExistingFullBlock(Blocks.STONE)
                .wall(STONE_WALL.get());
        blockModels.familyWithExistingFullBlock(Blocks.CRACKED_STONE_BRICKS)
                .stairs(CRACKED_STONE_BRICKS_STAIRS.get())
                .slab(CRACKED_STONE_BRICKS_SLAB.get())
                .wall(CRACKED_STONE_BRICKS_WALL.get());
        blockModels.familyWithExistingFullBlock(Blocks.CHISELED_STONE_BRICKS)
                .stairs(CHISELED_STONE_BRICKS_STAIRS.get())
                .slab(CHISELED_STONE_BRICKS_SLAB.get())
                .wall(CHISELED_STONE_BRICKS_WALL.get());
        blockModels.familyWithExistingFullBlock(Blocks.SMOOTH_STONE)
                .stairs(SMOOTH_STONE_STAIRS.get())
                .wall(SMOOTH_STONE_WALL.get());

        familyModels(blockModels, CHALK_FAMILY);
        familyModels(blockModels, LIMESTONE_FAMILY);
        familyModels(blockModels, SLATE_FAMILY);
    }

    private void familyModels(BlockModelGenerators blockModels, StoneFamily family)
    {
        subFamily(blockModels, family.block, family.stairs, family.slab, family.wall);
        subFamily(blockModels, family.polishedBlock, family.polishedStairs, family.polishedSlab, family.polishedWall);
        subFamily(blockModels, family.bricksBlock, family.bricksStairs, family.bricksSlab, family.bricksWall);
        subFamily(blockModels, family.tilesBlock, family.tilesStairs, family.tilesSlab, family.tilesWall);
    }

    private void subFamily(BlockModelGenerators blockModels, DeferredBlock<Block> block, DeferredBlock<StairBlock> stairs, DeferredBlock<SlabBlock> slab, DeferredBlock<WallBlock> wall)
    {
        blockModels.family(block.get())
                .stairs(stairs.get())
                .slab(slab.get())
                .wall(wall.get());
    }
}
