package net.kaupenjoe.greygoo.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.kaupenjoe.greygoo.GreyGooMod;
import net.kaupenjoe.greygoo.block.ModBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab GREY_GOO_TAB = FabricItemGroupBuilder.create(new ResourceLocation(GreyGooMod.MOD_ID,"greygootab"))
            .icon(() -> new ItemStack(ModBlocks.GREY_GOO)).build();
}