package mc.craig.software.angels.client.overlays;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.math.Transformation;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import mc.craig.software.angels.common.entity.anomaly.AnomalyEntity;
import mc.craig.software.angels.common.items.WAItems;
import mc.craig.software.angels.util.HandUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import org.jetbrains.annotations.NotNull;

public class TimeyWimeyOverlay implements @NotNull IGuiOverlay {

    @Override
    public void render(ForgeGui gui, PoseStack poseStack, float partialTick, int screenWidth, int screenHeight) {

        if (HandUtil.isInEitherHand(Minecraft.getInstance().player, WAItems.TIMEY_WIMEY_DETECTOR.get())) {
            Level clientLevel = Minecraft.getInstance().player.level;

            String warning = "There are %s anomalies nearby";
            int angelsAmount = clientLevel.getEntitiesOfClass(WeepingAngel.class, Minecraft.getInstance().player.getBoundingBox().inflate(64, 64, 64)).size();
            int anomalyLevel = clientLevel.getEntitiesOfClass(AnomalyEntity.class, Minecraft.getInstance().player.getBoundingBox().inflate(64, 64, 64)).size();

            warning = warning.replaceAll("%s", String.valueOf(angelsAmount + anomalyLevel));

            MultiBufferSource.BufferSource renderImpl = MultiBufferSource.immediate(Tesselator.getInstance().getBuilder());
            if (warning != null)
                Minecraft.getInstance().font.drawInBatch(warning, 80 - Minecraft.getInstance().font.width(warning) / 2, 4, ChatFormatting.WHITE.getColor(), false, Transformation.identity().getMatrix(), renderImpl, false, 0, 15728880);
            renderImpl.endBatch();
        }
    }
}
