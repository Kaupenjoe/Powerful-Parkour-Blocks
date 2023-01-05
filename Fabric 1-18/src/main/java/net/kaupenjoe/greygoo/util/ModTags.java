package net.kaupenjoe.greygoo.util;

import net.kaupenjoe.greygoo.GreyGooMod;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> DOWSING_ROD_VALUABLES
                = tag("dowsing_rod_valuables");
        public static final TagKey<Block> GREY_GOO_NON_EATABLES
                = tag("grey_goo_non_eatables");
        public static final TagKey<Block> PINK_GOO_EATABLES
                = tag("pink_goo_eatables");
        public static final TagKey<Block> DARK_GREY_GOO_NON_EATABLES
                = tag("dark_grey_goo_non_eatables");

        private static TagKey<Block> tag(String name) {
            return TagKey.create(Registry.BLOCK.key(), new ResourceLocation(GreyGooMod.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return TagKey.create(Registry.BLOCK.key(), new ResourceLocation("c", name));
        }
    }
}
