package net.toblexson.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.toblexson.registers.ModBlocks;
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
        dropSelf(ModBlocks.CHALK_BRICKS);
    }

    public void dropSelf(DeferredBlock<Block> deferredBlock)
    {
        dropSelf(deferredBlock.get());
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks()
    {
        return ModBlocks.REGISTER.getEntries().stream().map(Holder::value)::iterator;
    }
}
