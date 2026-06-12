package net.toblexson.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.toblexson.registers.ModBlocks;
import net.toblexson.stoned.Stoned;

public class ModBlockStateProvider extends BlockStateProvider
{

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper fileHelper)
    {
        super(output, Stoned.MODID, fileHelper);
    }

    @Override
    protected void registerStatesAndModels()
    {
        block(ModBlocks.CHALK);
        block(ModBlocks.CHALK_BRICKS);
    }

    private void block(DeferredBlock<?> deferredBlock)
    {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
