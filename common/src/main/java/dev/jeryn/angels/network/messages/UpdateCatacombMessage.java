package dev.jeryn.angels.network.messages;

import dev.jeryn.angels.common.CatacombTracker;
import dev.jeryn.angels.network.MessageS2C;
import dev.jeryn.angels.network.MessageType;
import dev.jeryn.angels.network.WANetwork;
import net.minecraft.network.FriendlyByteBuf;
import org.jetbrains.annotations.NotNull;

public class UpdateCatacombMessage extends MessageS2C {

    private final boolean inCatacomb;

    public UpdateCatacombMessage(boolean inCatacomb) {
        this.inCatacomb = inCatacomb;
    }

    public UpdateCatacombMessage(FriendlyByteBuf buf) {
        this.inCatacomb = buf.readBoolean();
    }

    @NotNull
    @Override
    public MessageType getType() {
        return WANetwork.UPDATE_CATACOMB;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(this.inCatacomb);
    }

    @Override
    public void handle() {
        CatacombTracker.setIsInCatacomb(this.inCatacomb);
    }
}
