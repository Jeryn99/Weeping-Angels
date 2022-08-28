package mc.craig.software.angels.fabric.mixin;

import mc.craig.software.angels.util.WAHelper;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DimensionSpecialEffects.OverworldEffects.class)
public class FogMixin {

    @Inject(at = @At("RETURN"), cancellable = true, method = "getBrightnessDependentFogColor(Lnet/minecraft/world/phys/Vec3;F)Lnet/minecraft/world/phys/Vec3;")
    private void onAdjustFogColor(CallbackInfoReturnable<Vec3> ci) {
        ci.setReturnValue(WAHelper.fogColor());
    }

    @Inject(at = @At("RETURN"), cancellable = true, method = "isFoggyAt(II)Z")
    private void isFoggy(CallbackInfoReturnable<Boolean> ci) {
        ci.setReturnValue(true);
    }

}
