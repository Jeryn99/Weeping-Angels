package mc.craig.software.angels.forge.overlays;

import com.mojang.blaze3d.vertex.PoseStack;
import mc.craig.software.angels.client.DectectorOverlay;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import org.jetbrains.annotations.NotNull;

public class TimeyWimeyOverlay implements @NotNull IGuiOverlay {

    @Override
    public void render(ForgeGui gui, PoseStack poseStack, float partialTick, int screenWidth, int screenHeight) {
        DectectorOverlay.renderOverlay(poseStack);
    }

}
