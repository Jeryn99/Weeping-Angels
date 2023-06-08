package mc.craig.software.angels.forge.overlays;

import mc.craig.software.angels.client.DetectorOverlay;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import org.jetbrains.annotations.NotNull;

public class TimeyWimeyOverlay implements @NotNull IGuiOverlay {
    @Override
    public void render(ForgeGui forgeGui, GuiGraphics arg, float partialTick, int screenWidth, int screenHeight) {
        DetectorOverlay.renderOverlay(arg);

    }
}
