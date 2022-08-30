package mc.craig.software.angels.network;

public abstract class MessageC2S extends Message {

    public void send() {
        this.getType().getNetworkManager().sendToServer(this);
    }

}
