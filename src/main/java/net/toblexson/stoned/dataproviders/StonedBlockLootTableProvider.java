package net.toblexson.stoned.dataproviders;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.Set;

import static net.toblexson.stoned.registers.StonedBlocks.*;

public class StonedBlockLootTableProvider extends BlockLootSubProvider
{
    public StonedBlockLootTableProvider(HolderLookup.Provider registries)
    {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate()
    {
        familyDrops(CHALK_FAMILY);
        familyDrops(LIMESTONE_FAMILY);
        familyDrops(SLATE_FAMILY);
    }

    private void familyDrops(StoneFamily family)
    {
        dropSelf(family.block);
        dropSelf(family.stairs);
        dropSlab(family.slab);
        dropSelf(family.wall);

        dropSelf(family.polishedBlock);
        dropSelf(family.polishedStairs);
        dropSlab(family.polishedSlab);
        dropSelf(family.polishedWall);

        dropSelf(family.bricksBlock);
        dropSelf(family.bricksStairs);
        dropSlab(family.bricksSlab);
        dropSelf(family.bricksWall);
    }

    public void dropSlab(DeferredBlock<SlabBlock> deferredBlock)
    {
        add(deferredBlock.get(), _ -> createSlabItemTable(deferredBlock.get()));
    }

    public void dropSelf(DeferredBlock<?> deferredBlock)
    {
        dropSelf(deferredBlock.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return REGISTER.getEntries().stream().map(Holder::value)::iterator;
    }
}
