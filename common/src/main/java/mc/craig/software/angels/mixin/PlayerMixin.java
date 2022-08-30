package mc.craig.software.angels.mixin;

import mc.craig.software.angels.common.CatacombTracker;
import mc.craig.software.angels.donators.DonationChecker;
import mc.craig.software.angels.donators.Donator;
import mc.craig.software.angels.util.WAHelper;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
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

        if (player instanceof ServerPlayer serverPlayer) {
            if (!player.level.isClientSide) {
                if (player.tickCount % 40 == 0) {
                    boolean isInCatacomb = CatacombTracker.isInCatacomb(player);
                    CatacombTracker.tellClient(serverPlayer, isInCatacomb);
                }

                RandomSource randomSource = serverPlayer.level.getRandom();

                if (serverPlayer.tickCount % 6000 == 0) {
                    serverPlayer.connection.send(new ClientboundSoundPacket(WAHelper.getRandomSounds(randomSource), SoundSource.AMBIENT, player.getX() + randomSource.nextInt(18), player.getY() + randomSource.nextInt(18), player.getZ() + randomSource.nextInt(18), 0.25F, 1F, serverPlayer.level.random.nextLong()));
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

}
