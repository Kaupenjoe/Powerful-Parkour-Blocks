package net.kaupenjoe.greygoo.item;

import net.kaupenjoe.greygoo.block.ModBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab GREY_GOO_TAB = new CreativeModeTab("greygootab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.GREY_GOO.get());
        }
    };
}