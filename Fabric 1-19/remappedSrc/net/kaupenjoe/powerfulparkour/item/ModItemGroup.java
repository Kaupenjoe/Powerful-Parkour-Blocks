package net.kaupenjoe.powerfulparkour.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.kaupenjoe.powerfulparkour.ParkourBlockMod;
import net.kaupenjoe.powerfulparkour.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static ItemGroup PARKOUR;

    public static void registerItemGroups() {
        PARKOUR = FabricItemGroup.builder(new Identifier(ParkourBlockMod.MOD_ID, "parkour"))
                .displayName(Text.translatable("itemgroup.parkour"))
                .icon(() -> new ItemStack(ModBlocks.EFFECT_BLOCK)).build();
    }
}