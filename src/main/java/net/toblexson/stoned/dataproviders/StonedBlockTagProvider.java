package net.toblexson.stoned.dataproviders;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.toblexson.stoned.Stoned;

import java.util.concurrent.CompletableFuture;

import static net.toblexson.stoned.registers.StonedBlocks.*;
import static net.toblexson.stoned.tags.StonedTags.Blocks.CHALK;
import static net.toblexson.stoned.tags.StonedTags.Blocks.LIMESTONE;
import static net.toblexson.stoned.tags.StonedTags.Blocks.SLATE;

public class StonedBlockTagProvider extends BlockTagsProvider
{
    public StonedBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup)
    {
        super(output, lookup, Stoned.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookup)
    {
        /* EXPANDED VANILLA */
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(STONE_WALL.get())
                .add(CRACKED_STONE_BRICKS_STAIRS.get())
                .add(CRACKED_STONE_BRICKS_SLAB.get())
                .add(CRACKED_STONE_BRICKS_WALL.get())
                .add(CHISELED_STONE_BRICKS_STAIRS.get())
                .add(CHISELED_STONE_BRICKS_SLAB.get())
                .add(CHISELED_STONE_BRICKS_WALL.get())
                .add(SMOOTH_STONE_BRICKS_STAIRS.get())
                .add(SMOOTH_STONE_BRICKS_WALL.get());

        tag(BlockTags.STAIRS)
                .add(CRACKED_STONE_BRICKS_STAIRS.get())
                .add(CHISELED_STONE_BRICKS_STAIRS.get())
                .add(SMOOTH_STONE_BRICKS_STAIRS.get());
        tag(BlockTags.SLABS)
                .add(CRACKED_STONE_BRICKS_SLAB.get())
                .add(CHISELED_STONE_BRICKS_SLAB.get());
        tag(BlockTags.WALLS)
                .add(STONE_WALL.get())
                .add(CRACKED_STONE_BRICKS_WALL.get())
                .add(CHISELED_STONE_BRICKS_WALL.get())
                .add(SMOOTH_STONE_BRICKS_WALL.get());

        /* ADDITIONAL FAMILIES */
        familyTags(CHALK_FAMILY, CHALK);
        familyTags(LIMESTONE_FAMILY, LIMESTONE);
        familyTags(SLATE_FAMILY, SLATE);
    }

    private void familyTags(StoneFamily family, TagKey<Block> stonedTag)
    {
        /* MOD */
        tag(stonedTag).addAll(family.getBlocks());

        /* MINING */
        tag(BlockTags.MINEABLE_WITH_PICKAXE).addAll(family.getAll());

        /* VANILLA/FORGE */
        tag(Tags.Blocks.STONES).addAll(family.getNatural());
        tag(BlockTags.BASE_STONE_OVERWORLD).addAll(family.getNatural());
        tag(BlockTags.STONE_ORE_REPLACEABLES).addAll(family.getNatural());
        tag(BlockTags.STONE_BRICKS).addAll(family.getBricks());
        tag(BlockTags.STAIRS).addAll(family.getStairs());
        tag(BlockTags.SLABS).addAll(family.getSlabs());
        tag(BlockTags.WALLS).addAll(family.getWalls());
    }
}
