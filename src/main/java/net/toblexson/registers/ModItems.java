package net.toblexson.registers;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.toblexson.stoned.Stoned;

public class ModItems
{
    public static final DeferredRegister.Items REGISTER = DeferredRegister.createItems(Stoned.MODID);

    public static void register(IEventBus bus)
    {
        REGISTER.register(bus);
    }
}
