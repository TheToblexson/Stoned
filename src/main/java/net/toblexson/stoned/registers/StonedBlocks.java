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

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class StonedBlocks
{
    public static final DeferredRegister.Blocks REGISTER = DeferredRegister.createBlocks(Stoned.MOD_ID);

    public static StoneFamily CHALK_FAMILY = new StoneFamily("chalk", Properties.of()
            .mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5f));
    public static StoneFamily LIMESTONE_FAMILY = new StoneFamily("limestone", Properties.of()
            .mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.0f));

    private static DeferredBlock<WallBlock> wall(DeferredBlock<Block> sourceBlock, Properties properties) {
        String name = sourceBlock.getId().getPath() + "_wall";
        return registerBlockWithItem(name, properties, WallBlock::new);
    }

    private static DeferredBlock<SlabBlock> slab(DeferredBlock<Block> sourceBlock, Properties properties) {
        String name = sourceBlock.getId().getPath() + "_slab";
        return registerBlockWithItem(name, properties, SlabBlock::new);
    }

    private static DeferredBlock<StairBlock> stairs(DeferredBlock<Block> sourceBlock, Properties properties) {
        String name = sourceBlock.getId().getPath() + "_stairs";
        return registerBlockWithItem(name, properties, p -> new StairBlock(sourceBlock.get().defaultBlockState(), p));
    }

    private static DeferredBlock<Block> block(String name, Properties properties) {
        return registerBlockWithItem(name, properties, Block::new);
    }

    private static <T extends Block> DeferredBlock<T> registerBlockWithItem(String name, Properties properties, Function<Properties, T> function) {
        DeferredBlock<T> registeredBlock = REGISTER.registerBlock(name, function, () -> properties);
        registerBlockItem(name, registeredBlock);
        return registeredBlock;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        StonedItems.REGISTER.registerSimpleBlockItem(name, block);
    }

    public static void register(IEventBus bus) {
        REGISTER.register(bus);
    }

    public static class StoneFamily {

        public final DeferredBlock<Block> block;

        public final DeferredBlock<StairBlock> stairs;
        public final DeferredBlock<SlabBlock> slab;
        public final DeferredBlock<WallBlock> wall;

        public final DeferredBlock<Block> bricksBlock;
        public final DeferredBlock<StairBlock> bricksStairs;
        public final DeferredBlock<SlabBlock> bricksSlab;
        public final DeferredBlock<WallBlock> bricksWall;

        public StoneFamily(String baseName, Properties properties) {
            block = block(baseName, properties);
            stairs = stairs(block, properties);
            slab = slab(block, properties);
            wall = wall(block, properties);

            bricksBlock = block(baseName + "_bricks", properties);
            bricksStairs = stairs(bricksBlock, properties);
            bricksSlab = slab(bricksBlock, properties);
            bricksWall = wall(bricksBlock, properties);
        }

        public Collection<Block> getAll() {
            return List.of(block.get(), stairs.get(), slab.get(), wall.get(),
                           bricksBlock.get(), bricksStairs.get(), bricksSlab.get(), bricksWall.get());
        }

        public Collection<Block> getBlocks() {
            return List.of(block.get(), bricksBlock.get());
        }

        public Collection<Block> getNatural() {
            return List.of(block.get());
        }

        public Collection<Block> getBricks() {
            return List.of(bricksBlock.get());
        }

        public Collection<Block> getStairs() {
            return List.of(stairs.get(), bricksStairs.get());
        }

        public Collection<Block> getSlabs() {
            return List.of(slab.get(), bricksSlab.get());
        }

        public Collection<Block> getWalls() {
            return List.of(wall.get(), bricksWall.get());
        }
    }
}
