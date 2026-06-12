package net.toblexson.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.toblexson.registers.ModBlocks;
import net.toblexson.registers.ModTags;
import net.toblexson.stoned.Stoned;
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
                .add(ModBlocks.CHALK.get())
                .add(ModBlocks.CHALK_STAIRS.get())
                .add(ModBlocks.CHALK_SLAB.get())
                .add(ModBlocks.CHALK_WALL.get())

                .add(ModBlocks.CHALK_BRICKS.get())
                .add(ModBlocks.CHALK_BRICKS_STAIRS.get())
                .add(ModBlocks.CHALK_BRICKS_SLAB.get())
                .add(ModBlocks.CHALK_BRICKS_WALL.get());

        //Vanilla/Forge
        tag(Tags.Blocks.STONES)
                .add(ModBlocks.CHALK.get());
        tag(BlockTags.STONE_BRICKS)
                .add(ModBlocks.CHALK_BRICKS.get());
        tag(BlockTags.STAIRS)
                .add(ModBlocks.CHALK_STAIRS.get())
                .add(ModBlocks.CHALK_BRICKS_STAIRS.get());
        tag(BlockTags.SLABS)
                .add(ModBlocks.CHALK_BRICKS_SLAB.get())
                .add(ModBlocks.CHALK_SLAB.get());
        tag(BlockTags.WALLS)
                .add(ModBlocks.CHALK_BRICKS_WALL.get())
                .add(ModBlocks.CHALK_WALL.get());

        //Mod
        tag(ModTags.Blocks.CHALK_BLOCK)
                .add(ModBlocks.CHALK.get())
                .add(ModBlocks.CHALK_BRICKS.get());
    }
}
