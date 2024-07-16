package mc.craig.software.angels.neoforge.overlays;

import mc.craig.software.angels.client.DetectorOverlay;
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
