package net.toblexson.stoned.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.toblexson.stoned.Stoned;

import static net.toblexson.stoned.worldgen.StonedPlacedFeatures.*;

public class StonedBiomeModifiers
{
    public static final ResourceKey<BiomeModifier> CHALK = registerKey("chalk");
    public static final ResourceKey<BiomeModifier> LIMESTONE = registerKey("limestone");
    public static final ResourceKey<BiomeModifier> SLATE = registerKey("slate");

    public static void bootstrap(BootstrapContext<BiomeModifier> context)
    {
        registerStone(context, CHALK, CHALK_LOWER, CHALK_UPPER);
        registerStone(context, LIMESTONE, LIMESTONE_LOWER, LIMESTONE_UPPER);
        registerStone(context, SLATE, SLATE_LOWER, SLATE_UPPER);
    }

    private static void registerStone(BootstrapContext<BiomeModifier> context, ResourceKey<BiomeModifier> key, ResourceKey<PlacedFeature> lower, ResourceKey<PlacedFeature> upper)
    {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(key, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(lower), placedFeatures.getOrThrow(upper)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name)
    {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, Identifier.fromNamespaceAndPath(Stoned.MOD_ID, name));
    }
}
