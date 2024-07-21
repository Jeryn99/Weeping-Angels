package mc.craig.software.angels.fabric.mixin;

import mc.craig.software.angels.util.ClientUtil;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientLevel.class)
public class ClientLevelMixin {

    @Inject(at = @At("HEAD"), method = "addEntity(Lnet/minecraft/world/entity/Entity;)V")
    private void addPlayer(Entity entity, CallbackInfo ci) {
        if (entity instanceof Player player) {
            ClientUtil.playDectorSound(player);
        }
    }


}
