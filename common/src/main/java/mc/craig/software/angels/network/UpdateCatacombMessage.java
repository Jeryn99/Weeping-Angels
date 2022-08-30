package mc.craig.software.angels.network;

import mc.craig.software.angels.common.CatacombTracker;
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
