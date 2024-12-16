package dev.jeryn.angels.forge.overlays;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.jeryn.angels.client.DetectorOverlay;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import org.jetbrains.annotations.NotNull;

public class TimeyWimeyOverlay implements @NotNull IGuiOverlay {

    @Override
    public void render(ForgeGui forgeGui, PoseStack arg, float f, int i, int j) {
        DetectorOverlay.renderOverlay(arg);
    }
}
