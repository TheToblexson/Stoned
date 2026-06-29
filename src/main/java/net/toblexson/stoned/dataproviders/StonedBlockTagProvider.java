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
        familyTags(StonedBlocks.CHALK_FAMILY);
        familyTags(StonedBlocks.LIMESTONE_FAMILY);
    }

    private void familyTags(StonedBlocks.StoneFamily family)
    {
        /* MOD */
        tag(StonedTags.Blocks.CHALK).addAll(StonedBlocks.CHALK_FAMILY.getBlocks());

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
