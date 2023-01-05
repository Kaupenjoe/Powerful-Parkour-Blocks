package net.kaupenjoe.greygoo.item;

import net.kaupenjoe.greygoo.GreyGooMod;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ModItems {


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new ResourceLocation(GreyGooMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        System.out.println("Registering Mod Items for " + GreyGooMod.MOD_ID);
    }
}
