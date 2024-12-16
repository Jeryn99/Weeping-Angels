package dev.jeryn.angels.client.screen;

import com.google.common.collect.Lists;
import dev.jeryn.angels.WeepingAngels;
import dev.jeryn.angels.common.WAConstants;
import dev.jeryn.angels.common.blockentity.StatueBlockEntity;
import dev.jeryn.angels.common.entity.angel.WeepingAngel;
import dev.jeryn.angels.common.entity.angel.ai.AngelEmotion;
import dev.jeryn.angels.common.entity.angel.ai.AngelVariant;
import dev.jeryn.angels.network.messages.UpdateStatueMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.level.Level;

import java.awt.*;
import java.util.function.Function;

public class ChiselScreen extends Screen {

    private static final ResourceLocation BACKGROUND = new ResourceLocation(WeepingAngels.MODID, "textures/ui/menubg.png");
    private static final WeepingAngel weepingAngelFake = new WeepingAngel(Minecraft.getInstance().level);
    public static final AnimationState POSE_ANIMATION_STATE = new AnimationState();

    private final BlockPos blockPos;
    private final ResourceKey<Level> level;

    private int guiLeft, guiTop, xSize, ySize;

    public ChiselScreen(Component component, BlockPos blockPos, ResourceKey<Level> levelResourceKey) {
        super(component);
        this.xSize = 200;
        this.ySize = 222;
        this.blockPos = blockPos;
        this.level = levelResourceKey;
    }

    @Override
    public void init() {
        super.init();
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;

        // Create buttons for Angel attributes
        CycleButton<AngelEmotion> emotionButton = CycleButton.builder((Function<AngelEmotion, Component>) o -> Component.literal(o.getId()))
                .withValues(AngelEmotion.values())
                .withInitialValue(AngelEmotion.ANGRY)
                .create(this.guiLeft + this.xSize - 125, this.guiTop + 50, 130, 20, Component.translatable(WAConstants.ANGEL_EMOTION), (cycleButton, angelEmotion) -> weepingAngelFake.setEmotion(angelEmotion));

        CycleButton<AngelVariant> variantCycleButton = CycleButton.builder((Function<AngelVariant, Component>) o -> Component.translatable("variant." + WeepingAngels.MODID + "." + o.location().getPath()))
                .withValues(AngelVariant.VARIANTS.values())
                .withInitialValue(AngelVariant.BASALT)
                .create(this.guiLeft + this.xSize - 125, this.guiTop + 80, 130, 20, Component.translatable(WAConstants.ANGEL_VARIANT), (cycleButton, angelVariant) -> weepingAngelFake.setVariant(angelVariant));

        CycleButton<Integer> poseCycleButton = CycleButton.builder((Function<Integer, Component>) o -> Component.literal(o.toString()))
                .withValues(Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12))
                .withInitialValue(0)
                .create(this.guiLeft + this.xSize - 125, this.guiTop + 110, 130, 20, Component.translatable(WAConstants.ANGEL_POSES), (cycleButton, pose) -> weepingAngelFake.setFakeAnimation(pose));

        this.addRenderableWidget(emotionButton);
        this.addRenderableWidget(variantCycleButton);
        this.addRenderableWidget(poseCycleButton);

        if (Minecraft.getInstance().level.getBlockEntity(blockPos) instanceof StatueBlockEntity statueBlockEntity) {
            weepingAngelFake.setVariant(statueBlockEntity.getVariant());
            weepingAngelFake.setEmotion(statueBlockEntity.getEmotion());
            weepingAngelFake.setFakeAnimation(statueBlockEntity.getAnimation());

            variantCycleButton.setValue(statueBlockEntity.getVariant());
            emotionButton.setValue(statueBlockEntity.getEmotion());
            poseCycleButton.setValue(statueBlockEntity.getAnimation());
        } else {
            Minecraft.getInstance().setScreen(null);
            return;
        }

        // Add the "Chisel" button with right alignment
        Button chiselButton = Button.builder(Component.translatable("gui.chisel.confirm"), button -> {
            new UpdateStatueMessage(variantCycleButton.getValue(), emotionButton.getValue(), poseCycleButton.getValue(), blockPos, level).send();
            Minecraft.getInstance().setScreen(null);
        }).bounds(this.guiLeft + this.xSize - 125, this.guiTop + 160, 130, 20).build();

        this.addRenderableWidget(chiselButton);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // Draw the background
        guiGraphics.blit(BACKGROUND, this.guiLeft - 30, this.guiTop, 0, 0, 256, 256, 256, 256);

        // Start animation if not already started
        if (!POSE_ANIMATION_STATE.isStarted()) {
            POSE_ANIMATION_STATE.start(12);
        }

        // Render entity preview
        InventoryScreen.renderEntityInInventoryFollowsMouse(guiGraphics, this.guiLeft + 25, this.guiTop + 160, 55, -90, -45, weepingAngelFake);

        // Draw title
        guiGraphics.drawString(this.font, Component.translatable("gui.statue.appearance"), width / 2, this.guiTop + 10, Color.WHITE.getRGB());

        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }
}
