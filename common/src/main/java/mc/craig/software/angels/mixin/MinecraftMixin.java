package mc.craig.software.angels.mixin;

import mc.craig.software.angels.client.WAMusic;
import mc.craig.software.angels.common.CatacombTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.Music;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Unique
    public boolean weepingAngels$hasCatacombMusicSetUp = false;

    @Inject(at = @At("HEAD"), method = "getSituationalMusic()Lnet/minecraft/sounds/Music;", cancellable = true)
    private void getSituationalMusic(CallbackInfoReturnable<Music> musicCallbackInfoReturnable) {
        Minecraft minecraft = Minecraft.getInstance();
        boolean inAWorld = minecraft.level != null;
        if(inAWorld) {

            if(!weepingAngels$hasCatacombMusicSetUp){
                WAMusic.init();
            }

            if (CatacombTracker.isInCatacomb()) {
                musicCallbackInfoReturnable.setReturnValue(WAMusic.CATACOMB_MUSIC);
            } else {
                if (minecraft.getMusicManager().isPlayingMusic(WAMusic.CATACOMB_MUSIC)) {
                    minecraft.getMusicManager().stopPlaying();
                }
            }
        }

    }

}
