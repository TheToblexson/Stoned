package net.toblexson.stoned.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.toblexson.stoned.Stoned;
import net.toblexson.stoned.registers.ModBlocks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider
{
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup, @Nullable ExistingFileHelper fileHelper)
    {
        super(output, lookup, Stoned.MODID, fileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider lookup)
    {
        //mining
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                //chalk
                .add(ModBlocks.CHALK.get())
                .add(ModBlocks.CHALK_STAIRS.get())
                .add(ModBlocks.CHALK_SLAB.get())
                .add(ModBlocks.CHALK_WALL.get())

                .add(ModBlocks.CHALK_BRICKS.get())
                .add(ModBlocks.CHALK_BRICKS_STAIRS.get())
                .add(ModBlocks.CHALK_BRICKS_SLAB.get())
                .add(ModBlocks.CHALK_BRICKS_WALL.get())
                
                //limestone
                .add(ModBlocks.LIMESTONE.get())
                .add(ModBlocks.LIMESTONE_STAIRS.get())
                .add(ModBlocks.LIMESTONE_SLAB.get())
                .add(ModBlocks.LIMESTONE_WALL.get())

                .add(ModBlocks.LIMESTONE_BRICKS.get())
                .add(ModBlocks.LIMESTONE_BRICKS_STAIRS.get())
                .add(ModBlocks.LIMESTONE_BRICKS_SLAB.get())
                .add(ModBlocks.LIMESTONE_BRICKS_WALL.get());

        //Vanilla/Forge
        tag(Tags.Blocks.STONES)
                .add(ModBlocks.CHALK.get())
                .add(ModBlocks.LIMESTONE.get());

        tag(BlockTags.BASE_STONE_OVERWORLD)
                .add(ModBlocks.CHALK.get())
                .add(ModBlocks.LIMESTONE.get());

        tag(BlockTags.STONE_ORE_REPLACEABLES)
                .add(ModBlocks.CHALK.get())
                .add(ModBlocks.LIMESTONE.get());

        tag(BlockTags.STONE_BRICKS)
                .add(ModBlocks.CHALK_BRICKS.get())
                .add(ModBlocks.LIMESTONE_BRICKS.get());

        tag(BlockTags.STAIRS)
                .add(ModBlocks.CHALK_STAIRS.get())
                .add(ModBlocks.CHALK_BRICKS_STAIRS.get())
                .add(ModBlocks.LIMESTONE_STAIRS.get())
            .add(ModBlocks.LIMESTONE_BRICKS_STAIRS.get());

        tag(BlockTags.SLABS)
                .add(ModBlocks.CHALK_BRICKS_SLAB.get())
                .add(ModBlocks.CHALK_SLAB.get())
                .add(ModBlocks.LIMESTONE_BRICKS_SLAB.get())
                .add(ModBlocks.LIMESTONE_SLAB.get());

        tag(BlockTags.WALLS)
                .add(ModBlocks.CHALK_BRICKS_WALL.get())
                .add(ModBlocks.CHALK_WALL.get())
                .add(ModBlocks.LIMESTONE_BRICKS_WALL.get())
                .add(ModBlocks.LIMESTONE_WALL.get());
    }
}
