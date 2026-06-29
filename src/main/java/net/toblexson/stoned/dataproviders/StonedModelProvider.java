package net.toblexson.stoned.dataproviders;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
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
        familyModels(blockModels, CHALK_FAMILY);
        familyModels(blockModels, LIMESTONE_FAMILY);
        familyModels(blockModels, SLATE_FAMILY);
    }

    private void familyModels(BlockModelGenerators blockModels, StoneFamily family)
    {
        subFamily(blockModels, family.block, family.stairs, family.slab, family.wall);
        subFamily(blockModels, family.polishedBlock, family.polishedStairs, family.polishedSlab, family.polishedWall);
        subFamily(blockModels, family.bricksBlock, family.bricksStairs, family.bricksSlab, family.bricksWall);
    }

    private void subFamily(BlockModelGenerators blockModels, DeferredBlock<Block> block, DeferredBlock<StairBlock> stairs, DeferredBlock<SlabBlock> slab, DeferredBlock<WallBlock> wall)
    {
        blockModels.family(block.get())
                .stairs(stairs.get())
                .slab(slab.get())
                .wall(wall.get());
    }
}
