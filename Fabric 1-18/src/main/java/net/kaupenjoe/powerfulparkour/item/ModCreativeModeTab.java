package net.kaupenjoe.powerfulparkour.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.kaupenjoe.powerfulparkour.ParkourBlockMod;
import net.kaupenjoe.powerfulparkour.block.ModBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab PARKOUR_TAB = FabricItemGroupBuilder.create(new ResourceLocation(ParkourBlockMod.MOD_ID,"parkourtab"))
            .icon(() -> new ItemStack(ModBlocks.EFFECT_BLOCK)).build();
}