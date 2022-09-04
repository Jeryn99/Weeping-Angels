package mc.craig.software.angels.fabric.mixin;

import mc.craig.software.angels.util.WAHelper;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class PlayerMixin {

    @Inject(at = @At("RETURN"), method = "tick()V")
    private void tick(CallbackInfo ci) {
        Player player = (Player) (Object) this;
        WAHelper.onPlayerTick(player);
    }

}
