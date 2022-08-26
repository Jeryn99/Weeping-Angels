package mc.craig.software.angels.client.overlays;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.math.Transformation;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.WAConstants;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import mc.craig.software.angels.common.entity.anomaly.AnomalyEntity;
import mc.craig.software.angels.common.items.WAItems;
import mc.craig.software.angels.util.HandUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

import static net.minecraft.client.gui.GuiComponent.drawCenteredString;

public class TimeyWimeyOverlay implements @NotNull IGuiOverlay {

    private ResourceLocation BACKGROUND = new ResourceLocation(WeepingAngels.MODID, "textures/ui/detector_backdrop.png");

    @Override
    public void render(ForgeGui gui, PoseStack poseStack, float partialTick, int screenWidth, int screenHeight) {
        if (!HandUtil.isInEitherHand(Minecraft.getInstance().player, WAItems.TIMEY_WIMEY_DETECTOR.get())) return;

        Level clientLevel = Minecraft.getInstance().player.level;

        // Texture
        poseStack.pushPose();
        RenderSystem.setShaderTexture(0, BACKGROUND);
        GuiComponent.blit(poseStack, 4, 4, 0, 0, 134, 22, 134, 22);

        // Item
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        itemRenderer.renderAndDecorateItem(new ItemStack(WAItems.TIMEY_WIMEY_DETECTOR.get()), 7, 6);
        poseStack.popPose();

        // Text
        int angelsAmount = clientLevel.getEntitiesOfClass(WeepingAngel.class, Minecraft.getInstance().player.getBoundingBox().inflate(64, 64, 64)).size();
        int anomalyLevel = clientLevel.getEntitiesOfClass(AnomalyEntity.class, Minecraft.getInstance().player.getBoundingBox().inflate(64, 64, 64)).size();
        String warning = Component.translatable(WAConstants.ANOMALIES_DETECTED, String.valueOf(angelsAmount + anomalyLevel)).getString();

        renderWidthScaledText(warning, poseStack, Minecraft.getInstance().font, 78, 11, Color.WHITE.getRGB(), 100);


    }

    public void renderWidthScaledText(String text, PoseStack matrix, Font font, float x, float y, int color, int width) {
        matrix.pushPose();
        int textWidth = font.width(text);
        float scale = width / (float) textWidth;
        scale = Mth.clamp(scale, 0.0F, 1.0F);
        matrix.translate(x, y, 0);
        matrix.scale(scale, scale, scale);
        drawCenteredString(matrix, Minecraft.getInstance().font, text, 0, 0, color);
        matrix.popPose();
    }
}
