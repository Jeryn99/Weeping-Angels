package mc.craig.software.angels.fabric.mixin;

import mc.craig.software.angels.util.ClientUtil;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientLevel.class)
public class ClientLevelMixin {

    //TODO
  /*  @Inject(at = @At("HEAD"), method = "Lnet/minecraft/client/multiplayer/ClientLevel$EntityCallbacks;onTrackingStart(Lnet/minecraft/world/entity/Entity;)V")
    private void addPlayer(int playerId, AbstractClientPlayer playerEntity, CallbackInfo ci) {
        ClientUtil.playDectorSound(playerEntity);
    }*/


}
