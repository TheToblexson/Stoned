package net.toblexson.stoned.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.toblexson.stoned.Stoned;

import java.util.List;

public class StonedPlacedFeatures
{
    public static final HeightRangePlacement UPPER_RANGE = HeightRangePlacement.uniform(VerticalAnchor.absolute(64), VerticalAnchor.absolute(128));
    public static final HeightRangePlacement LOWER_RANGE = HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60));
    public static final List<PlacementModifier> UPPER_PLACEMENT = StonedOrePlacement.rareOrePlacement(6, UPPER_RANGE);
    public static final List<PlacementModifier> LOWER_PLACEMENT = StonedOrePlacement.commonOrePlacement(2, LOWER_RANGE);

    public static final ResourceKey<PlacedFeature> CHALK_UPPER = registerKey("chalk_upper");
    public static final ResourceKey<PlacedFeature> CHALK_LOWER = registerKey("chalk_lower");
    public static final ResourceKey<PlacedFeature> LIMESTONE_UPPER = registerKey("limestone_upper");
    public static final ResourceKey<PlacedFeature> LIMESTONE_LOWER = registerKey("limestone_lower");

    public static void bootstrap(BootstrapContext<PlacedFeature> context)
    {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, CHALK_UPPER, configuredFeatures.getOrThrow(StonedConfiguredFeatures.CHALK),
                 UPPER_PLACEMENT);
        register(context, CHALK_LOWER, configuredFeatures.getOrThrow(StonedConfiguredFeatures.CHALK),
                 LOWER_PLACEMENT);

        register(context, LIMESTONE_UPPER, configuredFeatures.getOrThrow(StonedConfiguredFeatures.LIMESTONE),
                 UPPER_PLACEMENT);
        register(context, LIMESTONE_LOWER, configuredFeatures.getOrThrow(StonedConfiguredFeatures.LIMESTONE),
                 LOWER_PLACEMENT);
    }

    public static ResourceKey<PlacedFeature> registerKey(String name)
    {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(Stoned.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?,?>> configuration, List<PlacementModifier> modifiers)
    {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
