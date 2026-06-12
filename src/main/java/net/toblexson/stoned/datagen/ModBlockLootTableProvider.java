package net.toblexson.stoned.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.toblexson.stoned.registers.ModBlocks;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider
{
    protected ModBlockLootTableProvider(HolderLookup.Provider registries)
    {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate()
    {
        dropSelf(ModBlocks.CHALK);
        dropSelf(ModBlocks.CHALK_STAIRS);
        dropSlab(ModBlocks.CHALK_SLAB);
        dropSelf(ModBlocks.CHALK_WALL);

        dropSelf(ModBlocks.CHALK_BRICKS);
        dropSelf(ModBlocks.CHALK_BRICKS_STAIRS);
        dropSlab(ModBlocks.CHALK_BRICKS_SLAB);
        dropSelf(ModBlocks.CHALK_BRICKS_WALL);
    }

    public void dropSlab(DeferredBlock<SlabBlock> deferredBlock)
    {
        add(deferredBlock.get(), block -> createSlabItemTable(deferredBlock.get()));
    }

    public void dropSelf(DeferredBlock<?> deferredBlock)
    {
        dropSelf(deferredBlock.get());
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks()
    {
        return ModBlocks.REGISTER.getEntries().stream().map(Holder::value)::iterator;
    }
}
