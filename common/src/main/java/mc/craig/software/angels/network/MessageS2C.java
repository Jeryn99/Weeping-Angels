
package mc.craig.software.angels.network;

public abstract class MessageC2S extends Message {

    /**
     * Sends message to every player online
     */
    public void send() {
        this.getType().getNetworkManager().sendToServer(this);
    }

}