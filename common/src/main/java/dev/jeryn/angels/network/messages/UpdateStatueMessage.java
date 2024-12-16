package dev.jeryn.angels.network.messages;

import dev.jeryn.angels.common.blockentity.StatueBlockEntity;
import dev.jeryn.angels.common.entity.angel.ai.AngelEmotion;
import dev.jeryn.angels.common.entity.angel.ai.AngelVariant;
import dev.jeryn.angels.network.MessageC2S;
import dev.jeryn.angels.network.MessageType;
import dev.jeryn.angels.network.WANetwork;
import dev.jeryn.angels.util.Platform;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

public class UpdateStatueMessage extends MessageC2S {

    private ResourceKey<Level> level;
    private BlockPos blockPos;
    private AngelVariant angelVariant;
    private AngelEmotion angelEmotion;
    private int pose;

    public UpdateStatueMessage(AngelVariant angelVariant, AngelEmotion angelEmotion, int pose, BlockPos blockPos, ResourceKey<Level> resourceKey) {
        this.angelEmotion = angelEmotion;
        this.angelVariant = angelVariant;
        this.pose = pose;
        this.blockPos = blockPos;
        this.level = resourceKey;
    }

    public UpdateStatueMessage(FriendlyByteBuf buf) {
        this.angelEmotion = AngelEmotion.find(buf.readUtf());
        this.angelVariant = AngelVariant.getVariant(buf.readResourceLocation());
        this.pose = buf.readInt();
        this.blockPos = buf.readBlockPos();
        this.level = buf.readResourceKey(Registries.DIMENSION);
    }

    @NotNull
    @Override
    public MessageType getType() {
        return WANetwork.UPDATE_STATUE;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(angelEmotion.getId());
        buf.writeResourceLocation(angelVariant.location());
        buf.writeInt(this.pose);
        buf.writeBlockPos(blockPos);
        buf.writeResourceKey(level);
    }

    @Override
    public void handle() {
        ServerLevel mcLevel = Platform.getServer().getLevel(level);
        if (mcLevel.getBlockEntity(blockPos) instanceof StatueBlockEntity statueBlockEntity) {
            statueBlockEntity.setAnimation(this.pose);
            statueBlockEntity.setSpecificVariant(angelVariant);
            statueBlockEntity.setEmotion(angelEmotion);
            statueBlockEntity.sendUpdates();
            mcLevel.gameEvent(null, GameEvent.BLOCK_CHANGE, blockPos);
        }
    }
}
