package mc.craig.software.angels.network.messages;

import io.netty.buffer.ByteBuf;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.blockentity.StatueBlockEntity;
import mc.craig.software.angels.common.entity.angel.ai.AngelEmotion;
import mc.craig.software.angels.common.entity.angel.ai.AngelVariant;
import mc.craig.software.angels.network.WANetworkManager;
import mc.craig.software.angels.util.Platform;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

public record StatueUpdate(AngelVariant angelVariant, AngelEmotion angelEmotion, int pose, BlockPos blockPos, ResourceKey<Level> level)
        implements CustomPacketPayload, WANetworkManager.Handler<StatueUpdate> {

    public static final CustomPacketPayload.Type<StatueUpdate> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.tryBuild(WeepingAngels.MODID, "update_statue"));

    public static final StreamCodec<FriendlyByteBuf, StatueUpdate> STREAM_CODEC = StreamCodec.of((buf, ref) -> {
        buf.writeUtf(ref.angelEmotion().getId());
        buf.writeResourceLocation(ref.angelVariant().location());
        buf.writeInt(ref.pose());
        buf.writeBlockPos(ref.blockPos());
        buf.writeResourceKey(ref.level());
    }, buf -> {
        var angelEmotion = AngelEmotion.find(buf.readUtf());
        var angelVariant = AngelVariant.getVariant(buf.readResourceLocation());
        var pose = buf.readInt();
        var blockPos = buf.readBlockPos();
        var level = buf.readResourceKey(Registries.DIMENSION);
        return new StatueUpdate(angelVariant, angelEmotion, pose, blockPos, level);
    });

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void receive(StatueUpdate message, WANetworkManager.Context context) {
        ServerLevel mcLevel = Platform.getServer().getLevel(message.level());
        if (mcLevel.getBlockEntity(message.blockPos()) instanceof StatueBlockEntity statueBlockEntity) {
            statueBlockEntity.setAnimation(message.pose());
            statueBlockEntity.setSpecificVariant(message.angelVariant());
            statueBlockEntity.setEmotion(message.angelEmotion());
            statueBlockEntity.sendUpdates();
            mcLevel.gameEvent(null, GameEvent.BLOCK_CHANGE, message.blockPos());
        }
    }
}
