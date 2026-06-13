package net.toblexson.stoned.dataproviders;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.toblexson.stoned.registers.StonedBlocks;

import java.util.Set;

public class StonedBlockLootTableProvider extends BlockLootSubProvider
{
    public StonedBlockLootTableProvider(HolderLookup.Provider registries)
    {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate()
    {
        //Chalk
        dropSelf(StonedBlocks.CHALK);
        dropSelf(StonedBlocks.CHALK_STAIRS);
        dropSlab(StonedBlocks.CHALK_SLAB);
        dropSelf(StonedBlocks.CHALK_WALL);

        dropSelf(StonedBlocks.CHALK_BRICKS);
        dropSelf(StonedBlocks.CHALK_BRICKS_STAIRS);
        dropSlab(StonedBlocks.CHALK_BRICKS_SLAB);
        dropSelf(StonedBlocks.CHALK_BRICKS_WALL);
        
        //Limestone
        dropSelf(StonedBlocks.LIMESTONE);
        dropSelf(StonedBlocks.LIMESTONE_STAIRS);
        dropSlab(StonedBlocks.LIMESTONE_SLAB);
        dropSelf(StonedBlocks.LIMESTONE_WALL);

        dropSelf(StonedBlocks.LIMESTONE_BRICKS);
        dropSelf(StonedBlocks.LIMESTONE_BRICKS_STAIRS);
        dropSlab(StonedBlocks.LIMESTONE_BRICKS_SLAB);
        dropSelf(StonedBlocks.LIMESTONE_BRICKS_WALL);
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
        return StonedBlocks.REGISTER.getEntries().stream().map(Holder::value)::iterator;
    }
}
