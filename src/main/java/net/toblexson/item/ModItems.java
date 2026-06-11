package net.toblexson.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.toblexson.stoned.Stoned;

public class ModItems
{
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Stoned.MODID);

    public static final DeferredItem<Item> TEST = ITEMS.registerSimpleItem("test");

    public static void register(IEventBus bus)
    {
        ITEMS.register(bus);
    }
}
