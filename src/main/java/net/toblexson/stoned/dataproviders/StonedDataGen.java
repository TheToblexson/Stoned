package net.toblexson.stoned.dataproviders;

import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.toblexson.stoned.Stoned;

import java.util.Collections;
import java.util.List;

@EventBusSubscriber(modid = Stoned.MOD_ID)
public class StonedDataGen
{
    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event)
    {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var lookup = event.getLookupProvider();

        generator.addProvider(true, new StonedModelProvider(output));
        generator.addProvider(true, new StonedBlockTagProvider(output, lookup));
        generator.addProvider(true, new LootTableProvider(output, Collections.emptySet(), List.of(
                new LootTableProvider.SubProviderEntry(StonedBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookup));
        generator.addProvider(true, new StonedRecipeProvider.Runner(output, lookup));
        generator.addProvider(true, new StonedDatapackProvider(output, lookup));
    }
}
