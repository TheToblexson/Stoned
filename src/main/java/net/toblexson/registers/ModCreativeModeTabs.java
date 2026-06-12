package net.toblexson.registers;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.toblexson.stoned.Stoned;

import java.util.function.Supplier;

public class ModCreativeModeTabs
{
    public static final DeferredRegister<CreativeModeTab> REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Stoned.MODID);

    public static final Supplier<CreativeModeTab> STONED_TAB = REGISTER.register("stoned_tab", () ->
            CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModBlocks.CHALK))
                    .title(Component.translatable("creativetab.stoned.stoned_tab"))
                    .displayItems((itemDisplayParameters, output) ->
                                  {
                                      output.accept(ModBlocks.CHALK);
                                      output.accept(ModBlocks.CHALK_STAIRS);
                                      output.accept(ModBlocks.CHALK_SLAB);
                                      output.accept(ModBlocks.CHALK_WALL);

                                      output.accept(ModBlocks.CHALK_BRICKS);
                                      output.accept(ModBlocks.CHALK_BRICKS_STAIRS);
                                      output.accept(ModBlocks.CHALK_BRICKS_SLAB);
                                      output.accept(ModBlocks.CHALK_BRICKS_WALL);
                                  })
                    .build());

    public static void register(IEventBus eventBus)
    {
        REGISTER.register(eventBus);
    }
}
