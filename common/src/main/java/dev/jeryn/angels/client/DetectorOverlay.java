package dev.jeryn.angels.client;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.jeryn.angels.WeepingAngels;
import dev.jeryn.angels.common.WAConstants;
import dev.jeryn.angels.common.items.WAItems;
import dev.jeryn.angels.util.HandUtil;
import dev.jeryn.angels.util.WAHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;

import java.awt.*;

public class DetectorOverlay {

    private static final ResourceLocation BACKGROUND = new ResourceLocation(WeepingAngels.MODID, "textures/ui/detector_backdrop.png");


    public static void renderOverlay(GuiGraphics guiGraphics) {
        if (!HandUtil.isInEitherHand(Minecraft.getInstance().player, WAItems.TIMEY_WIMEY_DETECTOR.get())) return;


        // Texture
        guiGraphics.blit(BACKGROUND, 4, 4, 0, 0, 134, 22, 134, 22);
        // Item

        guiGraphics.renderFakeItem(new ItemStack(WAItems.TIMEY_WIMEY_DETECTOR.get()), 7, 6);

        // Text
        int anomalies = WAHelper.getAnomaliesAroundEntity(Minecraft.getInstance().player, 64).size();

        String warning = Component.translatable(WAConstants.ANOMALIES_DETECTED, String.valueOf(anomalies)).getString();

        renderWidthScaledText(warning, guiGraphics, Minecraft.getInstance().font, 78, 11, Color.WHITE.getRGB(), 100);
    }


    public static void renderWidthScaledText(String text, GuiGraphics guiGraphics, Font font, float x, float y, int color, int width) {
        PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();
        int textWidth = font.width(text);
        float scale = width / (float) textWidth;
        scale = Mth.clamp(scale, 0.0F, 1.0F);
        poseStack.translate(x, y, 0);
        poseStack.scale(scale, scale, scale);
        guiGraphics.drawString(Minecraft.getInstance().font, text, 0, 0, color);
        poseStack.popPose();
    }



}
