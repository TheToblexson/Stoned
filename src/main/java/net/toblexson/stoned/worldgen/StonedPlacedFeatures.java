package net.toblexson.stoned.worldgen;

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
    public static final ResourceKey<PlacedFeature> SLATE_UPPER = registerKey("slate_upper");
    public static final ResourceKey<PlacedFeature> SLATE_LOWER = registerKey("slate_lower");

    public static void bootstrap(BootstrapContext<PlacedFeature> context)
    {
        register(context, CHALK_UPPER, CHALK_LOWER, StonedConfiguredFeatures.CHALK);
        register(context, LIMESTONE_UPPER, LIMESTONE_LOWER,StonedConfiguredFeatures.LIMESTONE);
        register(context, SLATE_UPPER, SLATE_LOWER,StonedConfiguredFeatures.SLATE);
    }

    public static ResourceKey<PlacedFeature> registerKey(String name)
    {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(Stoned.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> upper, ResourceKey<PlacedFeature> lower, ResourceKey<ConfiguredFeature<?,?>> feature)
    {
        register(context, upper, feature,UPPER_PLACEMENT);
        register(context, lower, feature,LOWER_PLACEMENT);
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, ResourceKey<ConfiguredFeature<?,?>> featureKey,
                                 List<PlacementModifier> modifiers)
    {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        context.register(key, new PlacedFeature(configuredFeatures.getOrThrow(featureKey), List.copyOf(modifiers)));
    }
}
