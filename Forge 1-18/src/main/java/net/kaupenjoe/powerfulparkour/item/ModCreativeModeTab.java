package net.kaupenjoe.powerfulparkour.item;

import net.kaupenjoe.powerfulparkour.block.ModBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab EFFECT_TAB = new CreativeModeTab("parkourtab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.EFFECT_BLOCK.get());
        }
    };
}