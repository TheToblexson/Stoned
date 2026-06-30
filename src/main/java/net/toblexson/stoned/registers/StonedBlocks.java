package net.toblexson.stoned.registers;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.toblexson.stoned.Stoned;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class StonedBlocks
{
    public static final DeferredRegister.Blocks REGISTER = DeferredRegister.createBlocks(Stoned.MOD_ID);

    /* EXPANDED VANILLA */
    public static final DeferredBlock<WallBlock> STONE_WALL = wall("stone", Properties.ofFullCopy(Blocks.STONE));

    public static final DeferredBlock<StairBlock> CRACKED_STONE_BRICKS_STAIRS =
            stairs("cracked_stone_bricks", () -> Blocks.CRACKED_STONE_BRICKS, Properties.ofFullCopy(Blocks.CRACKED_STONE_BRICKS));
    public static final DeferredBlock<SlabBlock> CRACKED_STONE_BRICKS_SLAB =
            slab("cracked_stone_bricks", Properties.ofFullCopy(Blocks.CRACKED_STONE_BRICKS));
    public static final DeferredBlock<WallBlock> CRACKED_STONE_BRICKS_WALL =
            wall("cracked_stone_bricks", Properties.ofFullCopy(Blocks.CRACKED_STONE_BRICKS));

    public static final DeferredBlock<StairBlock> CHISELED_STONE_BRICKS_STAIRS =
            stairs("chiseled_stone_bricks", () -> Blocks.CHISELED_STONE_BRICKS, Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS));
    public static final DeferredBlock<SlabBlock> CHISELED_STONE_BRICKS_SLAB =
            slab("chiseled_stone_bricks", Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS));
    public static final DeferredBlock<WallBlock> CHISELED_STONE_BRICKS_WALL =
            wall("chiseled_stone_bricks", Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS));

    public static final DeferredBlock<StairBlock> SMOOTH_STONE_STAIRS =
            stairs("smooth_stone", () -> Blocks.SMOOTH_STONE, Properties.ofFullCopy(Blocks.SMOOTH_STONE));
    public static final DeferredBlock<WallBlock> SMOOTH_STONE_WALL =
            wall("smooth_stone", Properties.ofFullCopy(Blocks.SMOOTH_STONE));

    /* ADDITIONAL */
    public static final StoneFamily CHALK_FAMILY = new StoneFamily("chalk", Properties.of()
            .mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5f));
    public static final StoneFamily LIMESTONE_FAMILY = new StoneFamily("limestone", Properties.of()
            .mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.0f));
    public static final StoneFamily SLATE_FAMILY = new StoneFamily("slate", Properties.of()
            .mapColor(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.0f));

    private static DeferredBlock<WallBlock> wall(String baseName, Properties properties) {
        return registerBlockWithItem(baseName + "_wall", properties, WallBlock::new);
    }

    private static DeferredBlock<SlabBlock> slab(String baseName, Properties properties) {
        return registerBlockWithItem(baseName + "_slab", properties, SlabBlock::new);
    }

    private static DeferredBlock<StairBlock> stairs(String baseName, Supplier<Block> sourceBlockSupplier, Properties properties) {
        return registerBlockWithItem(baseName + "_stairs", properties, p -> new StairBlock(sourceBlockSupplier.get().defaultBlockState(), p));
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

        public final DeferredBlock<Block> polishedBlock;
        public final DeferredBlock<StairBlock> polishedStairs;
        public final DeferredBlock<SlabBlock> polishedSlab;
        public final DeferredBlock<WallBlock>polishedWall;

        public final DeferredBlock<Block> bricksBlock;
        public final DeferredBlock<StairBlock> bricksStairs;
        public final DeferredBlock<SlabBlock> bricksSlab;
        public final DeferredBlock<WallBlock> bricksWall;

        public StoneFamily(String baseName, Properties properties) {
            var name = baseName;
            block = block(name, properties);
            stairs = stairs(name, block, properties);
            slab = slab(name, properties);
            wall = wall(name, properties);

            name = "polished_" + baseName;
            polishedBlock = block(name, properties);
            polishedStairs = stairs(name,polishedBlock, properties);
            polishedSlab = slab(name, properties);
            polishedWall = wall(name, properties);

            name = baseName + "_bricks";
            bricksBlock = block(name, properties);
            bricksStairs = stairs(name, bricksBlock, properties);
            bricksSlab = slab(name, properties);
            bricksWall = wall(name, properties);
        }

        public Collection<Block> getAll() {
            return List.of(block.get(), stairs.get(), slab.get(), wall.get(),
                           polishedBlock.get(), polishedStairs.get(), polishedSlab.get(), polishedWall.get(),
                           bricksBlock.get(), bricksStairs.get(), bricksSlab.get(), bricksWall.get());
        }

        public Collection<Block> getBlocks() {
            return List.of(block.get(),polishedBlock.get(),bricksBlock.get());
        }

        public Collection<Block> getNatural() {
            return List.of(block.get());
        }

        public Collection<Block> getBricks() {
            return List.of(bricksBlock.get());
        }

        public Collection<Block> getStairs() {
            return List.of(stairs.get(),polishedStairs.get(),bricksStairs.get());
        }

        public Collection<Block> getSlabs() {
            return List.of(slab.get(),polishedSlab.get(),bricksSlab.get());
        }

        public Collection<Block> getWalls() {
            return List.of(wall.get(),polishedWall.get(), bricksWall.get());
        }
    }
}
