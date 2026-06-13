package net.toblexson.stoned.tags;

import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.toblexson.stoned.Stoned;

public class StonedTags
{
    public static class Blocks
    {
        public static final TagKey<Block> CHALK = createTag("chalk");
        public static final TagKey<Block> LIMESTONE = createTag("limestone");

        private static TagKey<Block> createTag(String name)
        {
            return BlockTags.create(Identifier.fromNamespaceAndPath(Stoned.MOD_ID, name));
        }
    }
}
