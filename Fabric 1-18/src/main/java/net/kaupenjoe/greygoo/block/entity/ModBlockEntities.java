package net.kaupenjoe.greygoo.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.kaupenjoe.greygoo.GreyGooMod;
import net.kaupenjoe.greygoo.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {
    public static BlockEntityType<GreyGooBlockEntity> GREY_GOO;
    public static BlockEntityType<DarkGreyGooBlockEntity> DARK_GREY_GOO;
    public static BlockEntityType<PinkGooBlockEntity> PINK_GOO;
    public static BlockEntityType<PurpleGooBlockEntity> PURPLE_GOO;


    public static void registerBlockEntities() {
        GREY_GOO = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new ResourceLocation(GreyGooMod.MOD_ID, "grey_goo"),
                FabricBlockEntityTypeBuilder.create(GreyGooBlockEntity::new,
                        ModBlocks.GREY_GOO).build(null));
        DARK_GREY_GOO = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new ResourceLocation(GreyGooMod.MOD_ID, "dark_grey_goo"),
                FabricBlockEntityTypeBuilder.create(DarkGreyGooBlockEntity::new,
                        ModBlocks.DARK_GREY_GOO).build(null));
        PINK_GOO = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new ResourceLocation(GreyGooMod.MOD_ID, "pink_goo"),
                FabricBlockEntityTypeBuilder.create(PinkGooBlockEntity::new,
                        ModBlocks.PINK_GOO).build(null));
        PURPLE_GOO = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new ResourceLocation(GreyGooMod.MOD_ID, "purple_goo"),
                FabricBlockEntityTypeBuilder.create(PurpleGooBlockEntity::new,
                        ModBlocks.PURPLE_GOO).build(null));
    }
}
