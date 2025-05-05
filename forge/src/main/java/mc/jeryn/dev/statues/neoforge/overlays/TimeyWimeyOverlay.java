package mc.jeryn.dev.statues.neoforge.overlays;

import mc.jeryn.dev.statues.client.DetectorOverlay;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import org.jetbrains.annotations.NotNull;

public class TimeyWimeyOverlay implements LayeredDraw.@NotNull Layer {

    @Override
    public void render(GuiGraphics arg, DeltaTracker arg2) {
        DetectorOverlay.renderOverlay(arg);
    }
}
