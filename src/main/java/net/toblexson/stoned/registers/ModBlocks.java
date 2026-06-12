package net.toblexson.stoned.registers;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.toblexson.stoned.Stoned;

import java.util.function.Supplier;

public class ModBlocks
{
    public static final DeferredRegister.Blocks REGISTER = DeferredRegister.createBlocks(Stoned.MODID);

    private static final BlockBehaviour.Properties PROPERTIES_CHALK = BlockBehaviour.Properties.of()
            .mapColor(MapColor.QUARTZ)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(0.5f);
    private static final BlockBehaviour.Properties PROPERTIES_LIMESTONE = BlockBehaviour.Properties.of()
            .mapColor(MapColor.TERRACOTTA_WHITE)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(1.0f);

    //Chalk
    public static final DeferredBlock<Block> CHALK = registerBlock("chalk", () ->
            new Block(PROPERTIES_CHALK));
    public static final DeferredBlock<StairBlock> CHALK_STAIRS = registerBlock("chalk_stairs", () ->
            new StairBlock(ModBlocks.CHALK.get().defaultBlockState(), PROPERTIES_CHALK));
    public static final DeferredBlock<SlabBlock> CHALK_SLAB = registerBlock("chalk_slab", () ->
            new SlabBlock(PROPERTIES_CHALK));
    public static final DeferredBlock<WallBlock> CHALK_WALL = registerBlock("chalk_wall", () ->
            new WallBlock(PROPERTIES_CHALK));

    public static final DeferredBlock<Block> CHALK_BRICKS = registerBlock("chalk_bricks", () ->
            new Block(PROPERTIES_CHALK));
    public static final DeferredBlock<StairBlock> CHALK_BRICKS_STAIRS = registerBlock("chalk_bricks_stairs", () ->
            new StairBlock(ModBlocks.CHALK.get().defaultBlockState(), PROPERTIES_CHALK));
    public static final DeferredBlock<SlabBlock> CHALK_BRICKS_SLAB = registerBlock("chalk_bricks_slab", () ->
            new SlabBlock(PROPERTIES_CHALK));
    public static final DeferredBlock<WallBlock> CHALK_BRICKS_WALL = registerBlock("chalk_bricks_wall", () ->
            new WallBlock(PROPERTIES_CHALK));

    //Limestone
    public static final DeferredBlock<Block> LIMESTONE = registerBlock("limestone", () ->
            new Block(PROPERTIES_LIMESTONE));
    public static final DeferredBlock<StairBlock> LIMESTONE_STAIRS = registerBlock("limestone_stairs", () ->
            new StairBlock(ModBlocks.LIMESTONE.get().defaultBlockState(), PROPERTIES_LIMESTONE));
    public static final DeferredBlock<SlabBlock> LIMESTONE_SLAB = registerBlock("limestone_slab", () ->
            new SlabBlock(PROPERTIES_LIMESTONE));
    public static final DeferredBlock<WallBlock> LIMESTONE_WALL = registerBlock("limestone_wall", () ->
            new WallBlock(PROPERTIES_LIMESTONE));

    public static final DeferredBlock<Block> LIMESTONE_BRICKS = registerBlock("limestone_bricks", () ->
            new Block(PROPERTIES_LIMESTONE));
    public static final DeferredBlock<StairBlock> LIMESTONE_BRICKS_STAIRS = registerBlock("limestone_bricks_stairs", () ->
            new StairBlock(ModBlocks.LIMESTONE.get().defaultBlockState(), PROPERTIES_LIMESTONE));
    public static final DeferredBlock<SlabBlock> LIMESTONE_BRICKS_SLAB = registerBlock("limestone_bricks_slab", () ->
            new SlabBlock(PROPERTIES_CHALK));
    public static final DeferredBlock<WallBlock> LIMESTONE_BRICKS_WALL = registerBlock("limestone_bricks_wall", () ->
            new WallBlock(PROPERTIES_CHALK));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block)
    {
        DeferredBlock<T> registeredBlock = REGISTER.register(name, block);
        registerBlockItem(registeredBlock);
        return registeredBlock;
    }

    private static <T extends Block> void registerBlockItem(DeferredBlock<T> block)
    {
        ModItems.REGISTER.registerSimpleBlockItem(block);
    }

    public static void register(IEventBus bus)
    {
        REGISTER.register(bus);
    }
}
