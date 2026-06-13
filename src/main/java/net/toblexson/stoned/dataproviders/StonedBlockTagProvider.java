package net.toblexson.stoned.dataproviders;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.toblexson.stoned.Stoned;
import net.toblexson.stoned.registers.StonedBlocks;
import net.toblexson.stoned.tags.StonedTags;

import java.util.concurrent.CompletableFuture;

public class StonedBlockTagProvider extends BlockTagsProvider
{
    public StonedBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup)
    {
        super(output, lookup, Stoned.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookup)
    {
        /* STONED */
        tag(StonedTags.Blocks.CHALK)
                .add(StonedBlocks.CHALK.get())
                .add(StonedBlocks.CHALK_BRICKS.get());

        tag(StonedTags.Blocks.LIMESTONE)
                .add(StonedBlocks.LIMESTONE.get())
                .add(StonedBlocks.LIMESTONE_BRICKS.get());

        /* MINING */
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(StonedBlocks.CHALK.get())
                .add(StonedBlocks.CHALK_STAIRS.get())
                .add(StonedBlocks.CHALK_SLAB.get())
                .add(StonedBlocks.CHALK_WALL.get())

                .add(StonedBlocks.CHALK_BRICKS.get())
                .add(StonedBlocks.CHALK_BRICKS_STAIRS.get())
                .add(StonedBlocks.CHALK_BRICKS_SLAB.get())
                .add(StonedBlocks.CHALK_BRICKS_WALL.get())

                .add(StonedBlocks.LIMESTONE.get())
                .add(StonedBlocks.LIMESTONE_STAIRS.get())
                .add(StonedBlocks.LIMESTONE_SLAB.get())
                .add(StonedBlocks.LIMESTONE_WALL.get())

                .add(StonedBlocks.LIMESTONE_BRICKS.get())
                .add(StonedBlocks.LIMESTONE_BRICKS_STAIRS.get())
                .add(StonedBlocks.LIMESTONE_BRICKS_SLAB.get())
                .add(StonedBlocks.LIMESTONE_BRICKS_WALL.get());

        /* VANILLA/FORGE */
        tag(Tags.Blocks.STONES)
                .add(StonedBlocks.CHALK.get())
                .add(StonedBlocks.LIMESTONE.get());

        tag(BlockTags.BASE_STONE_OVERWORLD)
                .add(StonedBlocks.CHALK.get())
                .add(StonedBlocks.LIMESTONE.get());

        tag(BlockTags.STONE_ORE_REPLACEABLES)
                .add(StonedBlocks.CHALK.get())
                .add(StonedBlocks.LIMESTONE.get());

        tag(BlockTags.STONE_BRICKS)
                .add(StonedBlocks.CHALK_BRICKS.get())
                .add(StonedBlocks.LIMESTONE_BRICKS.get());

        tag(BlockTags.STAIRS)
                .add(StonedBlocks.CHALK_STAIRS.get())
                .add(StonedBlocks.CHALK_BRICKS_STAIRS.get())
                .add(StonedBlocks.LIMESTONE_STAIRS.get())
            .add(StonedBlocks.LIMESTONE_BRICKS_STAIRS.get());

        tag(BlockTags.SLABS)
                .add(StonedBlocks.CHALK_BRICKS_SLAB.get())
                .add(StonedBlocks.CHALK_SLAB.get())
                .add(StonedBlocks.LIMESTONE_BRICKS_SLAB.get())
                .add(StonedBlocks.LIMESTONE_SLAB.get());

        tag(BlockTags.WALLS)
                .add(StonedBlocks.CHALK_BRICKS_WALL.get())
                .add(StonedBlocks.CHALK_WALL.get())
                .add(StonedBlocks.LIMESTONE_BRICKS_WALL.get())
                .add(StonedBlocks.LIMESTONE_WALL.get());
    }
}
