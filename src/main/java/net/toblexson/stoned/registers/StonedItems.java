package net.toblexson.stoned.registers;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.toblexson.stoned.Stoned;

public class StonedItems
{
    public static final DeferredRegister.Items REGISTER = DeferredRegister.createItems(Stoned.MOD_ID);

    public static void register(IEventBus bus)
    {
        REGISTER.register(bus);
    }
}
