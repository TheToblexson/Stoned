package net.toblexson.registers;

import net.minecraft.world.level.block.Block;
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
            .mapColor(MapColor.TERRACOTTA_WHITE)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(0.5f);


    public static final DeferredBlock<Block> CHALK = registerBlock("chalk", () ->
            new Block(PROPERTIES_CHALK));


    public static final DeferredBlock<Block> CHALK_BRICKS = registerBlock("chalk_bricks", () ->
            new Block(PROPERTIES_CHALK));

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
