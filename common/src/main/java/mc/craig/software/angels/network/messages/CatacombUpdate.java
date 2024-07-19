package mc.craig.software.angels.network.messages;

import mc.craig.software.angels.common.CatacombTracker;
import mc.craig.software.angels.network.WANetworkManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record CatacombUpdate(
        boolean isInCatacomb) implements CustomPacketPayload, WANetworkManager.Handler<CatacombUpdate> {

    public static final CustomPacketPayload.Type<CatacombUpdate> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("mymod", "my_data"));

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

    @Override
    public void receive(CatacombUpdate catacombUpdate, WANetworkManager.Context context) {
        CatacombTracker.setIsInCatacomb(catacombUpdate.isInCatacomb());
    }
}
