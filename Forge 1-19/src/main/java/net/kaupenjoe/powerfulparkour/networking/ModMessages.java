package net.kaupenjoe.powerfulparkour.networking;

import net.kaupenjoe.powerfulparkour.ParkourBlockMod;
import net.kaupenjoe.powerfulparkour.networking.packets.EffectC2SSync;
import net.kaupenjoe.powerfulparkour.networking.packets.EffectDataS2CSync;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(ParkourBlockMod.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(EffectC2SSync.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(EffectC2SSync::new)
                .encoder(EffectC2SSync::toBytes)
                .consumerMainThread(EffectC2SSync::handle)
                .add();

        net.messageBuilder(EffectDataS2CSync.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(EffectDataS2CSync::new)
                .encoder(EffectDataS2CSync::toBytes)
                .consumerMainThread(EffectDataS2CSync::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sendToClients(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }
}
