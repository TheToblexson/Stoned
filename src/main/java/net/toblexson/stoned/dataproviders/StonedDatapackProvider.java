package net.toblexson.stoned.dataproviders;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.toblexson.stoned.Stoned;
import net.toblexson.stoned.worldgen.StonedBiomeModifiers;
import net.toblexson.stoned.worldgen.StonedConfiguredFeatures;
import net.toblexson.stoned.worldgen.StonedPlacedFeatures;
//import net.toblexson.stoned.Stoned;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class StonedDatapackProvider extends DatapackBuiltinEntriesProvider
{
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, StonedConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, StonedPlacedFeatures::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, StonedBiomeModifiers::bootstrap);

    public StonedDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(output, registries, BUILDER, Set.of(Stoned.MOD_ID));
    }
}
