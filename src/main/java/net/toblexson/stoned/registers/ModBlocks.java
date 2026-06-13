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

import java.util.function.Supplier;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class ModBlocks
{
    public static final DeferredRegister.Blocks REGISTER = DeferredRegister.createBlocks(Stoned.MODID);

    private static final Properties PROPERTIES_CHALK = Properties.of()
            .mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5f);
    private static final Properties PROPERTIES_LIMESTONE = Properties.of()
            .mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.0f);

    //Chalk
    public static final DeferredBlock<Block> CHALK = block("chalk", PROPERTIES_CHALK);
    public static final DeferredBlock<StairBlock> CHALK_STAIRS = stairs("chalk_stairs", ModBlocks.CHALK, PROPERTIES_CHALK);
    public static final DeferredBlock<SlabBlock> CHALK_SLAB = slab("chalk_slab", PROPERTIES_CHALK);
    public static final DeferredBlock<WallBlock> CHALK_WALL = wall("chalk_wall", PROPERTIES_CHALK);

    public static final DeferredBlock<Block> CHALK_BRICKS = block("chalk_bricks", PROPERTIES_CHALK);
    public static final DeferredBlock<StairBlock> CHALK_BRICKS_STAIRS = stairs("chalk_bricks_stairs", ModBlocks.CHALK, PROPERTIES_CHALK);
    public static final DeferredBlock<SlabBlock> CHALK_BRICKS_SLAB = slab("chalk_bricks_slab", PROPERTIES_CHALK);
    public static final DeferredBlock<WallBlock> CHALK_BRICKS_WALL = wall("chalk_bricks_wall", PROPERTIES_CHALK);

    //Limestone
    public static final DeferredBlock<Block> LIMESTONE = block("limestone", PROPERTIES_LIMESTONE);
    public static final DeferredBlock<StairBlock> LIMESTONE_STAIRS = stairs("limestone_stairs", ModBlocks.LIMESTONE, PROPERTIES_LIMESTONE);
    public static final DeferredBlock<SlabBlock> LIMESTONE_SLAB = slab("limestone_slab", PROPERTIES_LIMESTONE);
    public static final DeferredBlock<WallBlock> LIMESTONE_WALL = wall("limestone_wall", PROPERTIES_LIMESTONE);

    public static final DeferredBlock<Block> LIMESTONE_BRICKS = block("limestone_bricks", PROPERTIES_LIMESTONE);
    public static final DeferredBlock<StairBlock> LIMESTONE_BRICKS_STAIRS = stairs("limestone_bricks_stairs", ModBlocks.LIMESTONE, PROPERTIES_LIMESTONE);
    public static final DeferredBlock<SlabBlock> LIMESTONE_BRICKS_SLAB = slab("limestone_bricks_slab", PROPERTIES_LIMESTONE);
    public static final DeferredBlock<WallBlock> LIMESTONE_BRICKS_WALL = wall("limestone_bricks_wall", PROPERTIES_LIMESTONE);

    private static DeferredBlock<WallBlock> wall(String name, Properties properties)
    {
        return registerBlockWithItem(name, () -> new WallBlock(properties));
    }

    private static DeferredBlock<SlabBlock> slab(String name, Properties properties)
    {
        return registerBlockWithItem(name, () -> new SlabBlock(properties));
    }

    private static DeferredBlock<StairBlock> stairs(String name, DeferredBlock<Block> sourceDefBlock, Properties properties)
    {
        return registerBlockWithItem(name, () -> new StairBlock(sourceDefBlock.get().defaultBlockState(), properties));
    }

    private static DeferredBlock<Block> block(String name, Properties properties)
    {
        return registerBlockWithItem(name, () -> new Block(properties));
    }

    private static <T extends Block> DeferredBlock<T> registerBlockWithItem(String name, Supplier<T> block)
    {
        DeferredBlock<T> registeredBlock = REGISTER.register(name, block);
        ModItems.REGISTER.registerSimpleBlockItem(registeredBlock);
        return registeredBlock;
    }

    public static void register(IEventBus bus)
    {
        REGISTER.register(bus);
    }
}
