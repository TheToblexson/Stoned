package net.toblexson.stoned.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.toblexson.stoned.Stoned;
import net.toblexson.stoned.registers.StonedBlocks;

public class StonedConfiguredFeatures
{
    public static final ResourceKey<ConfiguredFeature<?,?>> CHALK = registerKey("chalk");
    public static final ResourceKey<ConfiguredFeature<?,?>> LIMESTONE = registerKey("limestone");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?,?>> context)
    {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);

        register(context, CHALK, Feature.ORE, new OreConfiguration(
                stoneReplaceables, StonedBlocks.CHALK.get().defaultBlockState(), 64));

        register(context, LIMESTONE, Feature.ORE, new OreConfiguration(
                stoneReplaceables, StonedBlocks.LIMESTONE.get().defaultBlockState(), 64));
    }

    public static ResourceKey<ConfiguredFeature<?,?>> registerKey(String name)
    {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(Stoned.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?,?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?,?>> key, F feature, FC configuration)
    {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
