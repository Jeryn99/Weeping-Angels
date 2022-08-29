package mc.craig.software.angels.fabric.mixin;

import mc.craig.software.angels.common.CatacombTracker;
import mc.craig.software.angels.donators.DonationChecker;
import mc.craig.software.angels.donators.Donator;
import net.minecraft.server.level.ServerPlayer;
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

        if (!player.level.isClientSide) {
            if (player.tickCount % 40 == 0) {
                boolean isInCatacomb = CatacombTracker.isInCatacomb(player);
                CatacombTracker.tellClient((ServerPlayer) player, isInCatacomb);
            }
        }

        // Update Donators
        if (player.level.isClientSide()) {
            for (Donator donator : DonationChecker.getModDonators()) {
                if (player.getStringUUID().equals(donator.getUuid())) {
                    donator.tick(player);
                }
            }
        }
    }

}
