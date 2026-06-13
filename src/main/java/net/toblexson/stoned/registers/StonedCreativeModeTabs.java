package net.toblexson.stoned.registers;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.toblexson.stoned.Stoned;

import java.util.function.Supplier;

public class StonedCreativeModeTabs
{
    public static final DeferredRegister<CreativeModeTab> REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Stoned.MOD_ID);

    //StonedBlocks.REGISTER.getEntries().stream().map(Holder::value)::iterator

    public static final Supplier<CreativeModeTab> STONED_TAB = REGISTER.register("stoned_tab", () ->
            CreativeModeTab.builder()
                    .icon(() -> new ItemStack(StonedBlocks.CHALK))
                    .title(Component.translatable("creativetab.stoned.stoned_tab"))
                    .displayItems((itemDisplayParameters, output) ->
                                  {
                                      /* CHALK */
                                      output.accept(StonedBlocks.CHALK);
                                      output.accept(StonedBlocks.CHALK_STAIRS);
                                      output.accept(StonedBlocks.CHALK_SLAB);
                                      output.accept(StonedBlocks.CHALK_WALL);

                                      output.accept(StonedBlocks.CHALK_BRICKS);
                                      output.accept(StonedBlocks.CHALK_BRICKS_STAIRS);
                                      output.accept(StonedBlocks.CHALK_BRICKS_SLAB);
                                      output.accept(StonedBlocks.CHALK_BRICKS_WALL);

                                      /* LIMESTONE */
                                      output.accept(StonedBlocks.LIMESTONE);
                                      output.accept(StonedBlocks.LIMESTONE_STAIRS);
                                      output.accept(StonedBlocks.LIMESTONE_SLAB);
                                      output.accept(StonedBlocks.LIMESTONE_WALL);

                                      output.accept(StonedBlocks.LIMESTONE_BRICKS);
                                      output.accept(StonedBlocks.LIMESTONE_BRICKS_STAIRS);
                                      output.accept(StonedBlocks.LIMESTONE_BRICKS_SLAB);
                                      output.accept(StonedBlocks.LIMESTONE_BRICKS_WALL);
                                  })
                    .build());

    public static void register(IEventBus eventBus)
    {
        REGISTER.register(eventBus);
    }
}
