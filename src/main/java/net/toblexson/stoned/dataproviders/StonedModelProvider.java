package net.toblexson.stoned.dataproviders;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.data.PackOutput;
import net.toblexson.stoned.Stoned;
import net.toblexson.stoned.registers.StonedBlocks;

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

        /* CHALK */
        blockModels.family(CHALK.get())
                .stairs(StonedBlocks.CHALK_STAIRS.get())
                .slab(StonedBlocks.CHALK_SLAB.get())
                .wall(StonedBlocks.CHALK_WALL.get());
        blockModels.family(StonedBlocks.CHALK_BRICKS.get())
                .stairs(StonedBlocks.CHALK_BRICKS_STAIRS.get())
                .slab(StonedBlocks.CHALK_BRICKS_SLAB.get())
                .wall(StonedBlocks.CHALK_BRICKS_WALL.get());

        /* LIMESTONE */
        blockModels.family(LIMESTONE.get())
                .stairs(StonedBlocks.LIMESTONE_STAIRS.get())
                .slab(StonedBlocks.LIMESTONE_SLAB.get())
                .wall(StonedBlocks.LIMESTONE_WALL.get());
        blockModels.family(StonedBlocks.LIMESTONE_BRICKS.get())
                .stairs(StonedBlocks.LIMESTONE_BRICKS_STAIRS.get())
                .slab(StonedBlocks.LIMESTONE_BRICKS_SLAB.get())
                .wall(StonedBlocks.LIMESTONE_BRICKS_WALL.get());
    }
}
