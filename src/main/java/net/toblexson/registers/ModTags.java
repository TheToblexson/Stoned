package net.toblexson.registers;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.toblexson.stoned.Stoned;

public class ModTags
{
    public static class Blocks
    {
        public static final TagKey<Block> CHALK_BLOCK = createTag("chalk_block");

        private static TagKey<Block> createTag(String name)
        {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Stoned.MODID, name));
        }

    }

    public static class Items
    {
        private static TagKey<Item> createTag(String name)
        {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Stoned.MODID, name));
        }
    }
}
