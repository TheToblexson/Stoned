package net.toblexson.stoned.registers;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.toblexson.stoned.Stoned;

import java.util.function.Supplier;

public class StonedCreativeModeTabs
{
    public static final DeferredRegister<CreativeModeTab> REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Stoned.MOD_ID);

    public static final Supplier<CreativeModeTab> STONED_TAB = REGISTER.register("stoned_tab", () ->
            CreativeModeTab.builder()
                    .icon(() -> new ItemStack(StonedBlocks.CHALK_FAMILY.block))
                    .title(Component.translatable("creativetab.stoned.stoned_tab"))
                    .displayItems((_, output) ->
                                          StonedItems.REGISTER.getEntries().stream().map(DeferredHolder::get).forEach(output::accept))
                    .build());

    public static void register(IEventBus eventBus)
    {
        REGISTER.register(eventBus);
    }
}
