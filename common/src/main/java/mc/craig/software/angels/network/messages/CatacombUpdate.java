package mc.craig.software.angels.network.messages;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.CatacombTracker;
import mc.craig.software.angels.network.WANetworkManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record CatacombUpdate(
        boolean isInCatacomb) implements CustomPacketPayload, WANetworkManager.Handler<CatacombUpdate> {

    public static final CustomPacketPayload.Type<CatacombUpdate> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(WeepingAngels.MODID, "catacomb_update"));

    public static final StreamCodec<FriendlyByteBuf, CatacombUpdate> STREAM_CODEC = StreamCodec.of((buf, ref) -> {
        buf.writeBoolean(ref.isInCatacomb());
    }, buf -> {
        var isInCatacomb = buf.readBoolean();
        return new CatacombUpdate(isInCatacomb);
    });

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Environment(EnvType.CLIENT)
    private static void updateCatacomb(CatacombUpdate catacombUpdate) {
        CatacombTracker.setIsInCatacomb(catacombUpdate.isInCatacomb());
    }

    @Override
    public void receive(CatacombUpdate catacombUpdate, WANetworkManager.Context context) {
        updateCatacomb(catacombUpdate);
    }
}
