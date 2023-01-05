package net.kaupenjoe.greygoo.block.entity;

import net.kaupenjoe.greygoo.GreyGooMod;
import net.kaupenjoe.greygoo.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, GreyGooMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<GreyGooBlockEntity>> GREY_GOO =
            BLOCK_ENTITIES.register("grey_goo", () ->
                    BlockEntityType.Builder.of(GreyGooBlockEntity::new,
                            ModBlocks.GREY_GOO.get()).build(null));

    public static final RegistryObject<BlockEntityType<DarkGreyGooBlockEntity>> DARK_GREY_GOO =
            BLOCK_ENTITIES.register("dark_grey_goo", () ->
                    BlockEntityType.Builder.of(DarkGreyGooBlockEntity::new,
                            ModBlocks.DARK_GREY_GOO.get()).build(null));

    public static final RegistryObject<BlockEntityType<PinkGooBlockEntity>> PINK_GOO =
            BLOCK_ENTITIES.register("pink_goo", () ->
                    BlockEntityType.Builder.of(PinkGooBlockEntity::new,
                            ModBlocks.PINK_GOO.get()).build(null));

    public static final RegistryObject<BlockEntityType<PurpleGooBlockEntity>> PURPLE_GOO =
            BLOCK_ENTITIES.register("purple_goo", () ->
                    BlockEntityType.Builder.of(PurpleGooBlockEntity::new,
                            ModBlocks.PURPLE_GOO.get()).build(null));



    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
