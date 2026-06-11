package net.toblexson.registers;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.toblexson.stoned.Stoned;

import java.util.function.Supplier;

public class ModBlocks
{
    public static final DeferredRegister.Blocks REGISTER = DeferredRegister.createBlocks(Stoned.MODID);

    public static final DeferredBlock<Block> CHALK = registerBlock("chalk", () ->
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

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
