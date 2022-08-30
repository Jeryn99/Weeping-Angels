package mc.craig.software.angels.mixin;

import mc.craig.software.angels.client.WAMusic;
import mc.craig.software.angels.common.CatacombTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.sounds.MusicManager;
import net.minecraft.sounds.Music;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Shadow
    @Nullable
    public LocalPlayer player;
    @Shadow
    @Final
    private MusicManager musicManager;

    @Inject(at = @At("HEAD"), method = "getSituationalMusic()Lnet/minecraft/sounds/Music;", cancellable = true)
    private void getSituationalMusic(CallbackInfoReturnable<Music> musicCallbackInfoReturnable) {
        if (CatacombTracker.isInCatacomb() && Minecraft.getInstance().level != null) {
            if (musicManager.isPlayingMusic(WAMusic.CATACOMB_MUSIC) || CatacombTracker.isInCatacomb()) {
                musicCallbackInfoReturnable.setReturnValue(WAMusic.CATACOMB_MUSIC);
            }
        }
    }

}
