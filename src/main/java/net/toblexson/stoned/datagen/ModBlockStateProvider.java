package net.toblexson.stoned.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.toblexson.stoned.registers.ModBlocks;
import net.toblexson.stoned.Stoned;
import org.jetbrains.annotations.NotNull;

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
        stairsBlock(ModBlocks.CHALK_STAIRS, ModBlocks.CHALK);
        slabBlock(ModBlocks.CHALK_SLAB, ModBlocks.CHALK);
        wallBlock(ModBlocks.CHALK_WALL, ModBlocks.CHALK);

        block(ModBlocks.CHALK_BRICKS);
        stairsBlock(ModBlocks.CHALK_BRICKS_STAIRS, ModBlocks.CHALK_BRICKS);
        slabBlock(ModBlocks.CHALK_BRICKS_SLAB, ModBlocks.CHALK_BRICKS);
        wallBlock(ModBlocks.CHALK_BRICKS_WALL, ModBlocks.CHALK_BRICKS);
    }

    private void slabBlock(DeferredBlock<SlabBlock> slabDefBlock, DeferredBlock<Block> sourceDefBlock)
    {
        ResourceLocation rl = blockTexture(sourceDefBlock.get());
        slabBlock(slabDefBlock.get(), rl, rl);
        itemModels().slab(path(slabDefBlock),rl, rl, rl);
    }

    private void wallBlock(DeferredBlock<WallBlock> wallDefBlock, DeferredBlock<Block> sourceDefBlock)
    {
        ResourceLocation rl = blockTexture(sourceDefBlock.get());
        wallBlock(wallDefBlock.get(), rl);
        itemModels().wallInventory(path(wallDefBlock), rl);
    }

    private void stairsBlock(DeferredBlock<StairBlock> stairsDefBlock, DeferredBlock<Block> sourceDefBlock)
    {
        ResourceLocation rl = blockTexture(sourceDefBlock.get());
        stairsBlock(stairsDefBlock.get(), rl);
        itemModels().stairs(path(stairsDefBlock), rl, rl, rl);
    }

    private void block(DeferredBlock<?> defBlock)
    {
        simpleBlockWithItem(defBlock.get(), cubeAll(defBlock.get()));
    }

    private static @NotNull String path(DeferredBlock<?> defBlock)
    {
        return defBlock.getId().getPath();
    }
}
