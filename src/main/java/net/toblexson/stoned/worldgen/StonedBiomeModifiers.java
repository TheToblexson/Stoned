package net.toblexson.stoned.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.toblexson.stoned.Stoned;

public class StonedBiomeModifiers
{
    public static final ResourceKey<BiomeModifier> CHALK = registerKey("chalk");
    public static final ResourceKey<BiomeModifier> LIMESTONE = registerKey("limestone");

    public static void bootstrap(BootstrapContext<BiomeModifier> context)
    {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(CHALK, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(StonedPlacedFeatures.CHALK_LOWER), placedFeatures.getOrThrow(StonedPlacedFeatures.CHALK_UPPER)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(LIMESTONE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(StonedPlacedFeatures.LIMESTONE_LOWER), placedFeatures.getOrThrow(StonedPlacedFeatures.LIMESTONE_UPPER)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name)
    {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, Identifier.fromNamespaceAndPath(Stoned.MOD_ID, name));
    }
}
