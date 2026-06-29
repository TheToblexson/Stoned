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
