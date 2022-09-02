package mc.craig.software.angels.mixin;

import mc.craig.software.angels.util.ClientUtil;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientLevel.class)
public class ClientLevelMixin {

    @Inject(at = @At("HEAD"), method = "addPlayer(ILnet/minecraft/client/player/AbstractClientPlayer;)V")
    private void addPlayer(int playerId, AbstractClientPlayer playerEntity, CallbackInfo ci) {
        ClientUtil.playDectorSound(playerEntity);
    }


}
