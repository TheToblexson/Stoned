package net.toblexson.stoned.registers;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.toblexson.stoned.Stoned;

import java.util.function.Function;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class StonedBlocks
{
    public static final DeferredRegister.Blocks REGISTER = DeferredRegister.createBlocks(Stoned.MOD_ID);

    private static final Properties PROPERTIES_CHALK = Properties.of()
            .mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5f);
    private static final Properties PROPERTIES_LIMESTONE = Properties.of()
            .mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.0f);

    /* CHALK */
    public static final DeferredBlock<Block> CHALK = block("chalk", PROPERTIES_CHALK);
    public static final DeferredBlock<StairBlock> CHALK_STAIRS = stairs(StonedBlocks.CHALK, PROPERTIES_CHALK);
    public static final DeferredBlock<SlabBlock> CHALK_SLAB = slab(StonedBlocks.CHALK, PROPERTIES_CHALK);
    public static final DeferredBlock<WallBlock> CHALK_WALL = wall(StonedBlocks.CHALK, PROPERTIES_CHALK);

    public static final DeferredBlock<Block> CHALK_BRICKS = block("chalk_bricks", PROPERTIES_CHALK);
    public static final DeferredBlock<StairBlock> CHALK_BRICKS_STAIRS = stairs(StonedBlocks.CHALK_BRICKS, PROPERTIES_CHALK);
    public static final DeferredBlock<SlabBlock> CHALK_BRICKS_SLAB = slab(StonedBlocks.CHALK_BRICKS, PROPERTIES_CHALK);
    public static final DeferredBlock<WallBlock> CHALK_BRICKS_WALL = wall(StonedBlocks.CHALK_BRICKS, PROPERTIES_CHALK);

    /* LIMESTONE */
    public static final DeferredBlock<Block> LIMESTONE = block("limestone", PROPERTIES_LIMESTONE);
    public static final DeferredBlock<StairBlock> LIMESTONE_STAIRS = stairs(StonedBlocks.LIMESTONE, PROPERTIES_LIMESTONE);
    public static final DeferredBlock<SlabBlock> LIMESTONE_SLAB = slab(StonedBlocks.LIMESTONE, PROPERTIES_LIMESTONE);
    public static final DeferredBlock<WallBlock> LIMESTONE_WALL = wall(StonedBlocks.LIMESTONE, PROPERTIES_LIMESTONE);

    public static final DeferredBlock<Block> LIMESTONE_BRICKS = block("limestone_bricks", PROPERTIES_LIMESTONE);
    public static final DeferredBlock<StairBlock> LIMESTONE_BRICKS_STAIRS = stairs(StonedBlocks.LIMESTONE_BRICKS, PROPERTIES_LIMESTONE);
    public static final DeferredBlock<SlabBlock> LIMESTONE_BRICKS_SLAB = slab(StonedBlocks.LIMESTONE_BRICKS, PROPERTIES_LIMESTONE);
    public static final DeferredBlock<WallBlock> LIMESTONE_BRICKS_WALL = wall(StonedBlocks.LIMESTONE_BRICKS, PROPERTIES_LIMESTONE);

    private static DeferredBlock<WallBlock> wall(DeferredBlock<Block> sourceBlock, Properties properties)
    {
        String name = sourceBlock.getId().getPath() + "_wall";
        return registerBlockWithItem(name, properties, WallBlock::new);
    }

    private static DeferredBlock<SlabBlock> slab(DeferredBlock<Block> sourceBlock, Properties properties)
    {
        String name = sourceBlock.getId().getPath() + "_slab";
        return registerBlockWithItem(name, properties, SlabBlock::new);
    }

    private static DeferredBlock<StairBlock> stairs(DeferredBlock<Block> sourceBlock, Properties properties)
    {
        String name = sourceBlock.getId().getPath() + "_stairs";
        return registerBlockWithItem(name, properties, p -> new StairBlock(sourceBlock.get().defaultBlockState(), p));
    }

    private static DeferredBlock<Block> block(String name, Properties properties)
    {
        return registerBlockWithItem(name, properties, Block::new);
    }

    private static <T extends Block> DeferredBlock<T> registerBlockWithItem(String name, Properties properties, Function<Properties, T> function)
    {
        DeferredBlock<T> registeredBlock = REGISTER.registerBlock(name, function, () -> properties);
        registerBlockItem(name, registeredBlock);
        return registeredBlock;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block)
    {
        StonedItems.REGISTER.registerSimpleBlockItem(name, block);
    }

    public static void register(IEventBus bus)
    {
        REGISTER.register(bus);
    }
}
