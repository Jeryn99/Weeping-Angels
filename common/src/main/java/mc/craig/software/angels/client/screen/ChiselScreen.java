package mc.craig.software.angels.client.screen;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.WAConstants;
import mc.craig.software.angels.common.blockentity.StatueBlockEntity;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import mc.craig.software.angels.common.entity.angel.ai.AngelEmotion;
import mc.craig.software.angels.common.entity.angel.ai.AngelVariant;
import mc.craig.software.angels.network.messages.UpdateStatueMessage;
import net.minecraft.client.Minecraft;
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
    public static AnimationState POSE_ANIMATION_STATE = new AnimationState();
    private final BlockPos blockPos;
    private final ResourceKey<Level> level;
    public int guiLeft, guiTop, xSize, ySize;


    public ChiselScreen(Component component, BlockPos blockPos, ResourceKey<Level> levelResourceKey) {
        super(component);
        xSize = 200;
        ySize = 222;
        this.blockPos = blockPos;
        this.level = levelResourceKey;
    }

    @Override
    public void init() {
        super.init();
        guiLeft = (width - xSize) / 2;
        guiTop = (height - ySize) / 2;

        CycleButton<AngelEmotion> emotionButton = CycleButton.builder((Function<AngelEmotion, Component>) o -> Component.literal(o.getId())).withValues(AngelEmotion.values()).withInitialValue(AngelEmotion.ANGRY).create(this.width / 2 + 4 - 20, this.height / 4 + 72 + -16, 130, 20, Component.translatable(WAConstants.ANGEL_EMOTION), (cycleButton, angelEmotion) -> weepingAngelFake.setEmotion(angelEmotion));

        CycleButton<AngelVariant> variantCycleButton = CycleButton.builder((Function<AngelVariant, Component>) o -> Component.translatable("variant." + WeepingAngels.MODID + "." + o.location().getPath())).withValues(AngelVariant.VARIANTS.values()).withInitialValue(AngelVariant.BASALT).create(this.width / 2 + 4 - 20, this.height / 4 + 48 + -16, 130, 20, Component.translatable(WAConstants.ANGEL_VARIANT), (cycleButton, angelVariant) -> weepingAngelFake.setVariant(angelVariant));
        CycleButton<Integer> poseCycleButton = CycleButton.builder((Function<Integer, Component>) o -> Component.literal(o.toString())).withValues(Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)).withInitialValue(0).create(this.width / 2 + 4 - 20, this.height / 4 + 72 + 25 + -16, 130, 20, Component.translatable(WAConstants.ANGEL_POSES), (cycleButton, angelVariant) -> weepingAngelFake.setFakeAnimation(angelVariant));

        addRenderableWidget(emotionButton);
        addRenderableWidget(variantCycleButton);
        addRenderableWidget(poseCycleButton);

        if (Minecraft.getInstance().level.getBlockEntity(blockPos) instanceof StatueBlockEntity statueBlockEntity) {
            weepingAngelFake.setVariant(statueBlockEntity.getVariant());
            weepingAngelFake.setEmotion(statueBlockEntity.getEmotion());
            weepingAngelFake.setFakeAnimation(statueBlockEntity.getAnimation());

            variantCycleButton.setValue(statueBlockEntity.getVariant());
            emotionButton.setValue(statueBlockEntity.getEmotion());
            poseCycleButton.setValue(statueBlockEntity.getAnimation());
        } else {
            Minecraft.getInstance().setScreen(null);
        }

        Button quitButton = Button.builder(Component.translatable("Chisel"), button -> {
            new UpdateStatueMessage(variantCycleButton.getValue(), emotionButton.getValue(), poseCycleButton.getValue(), blockPos, level).send();
            Minecraft.getInstance().setScreen(null);
        }).build();

        addWidget(quitButton);
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        RenderSystem.setShaderTexture(0, BACKGROUND);
        blit(poseStack, guiLeft - 30, guiTop, 0, 0, 256, 256, 256, 256);

        if (!POSE_ANIMATION_STATE.isStarted()) {
            POSE_ANIMATION_STATE.start(12);
        }

        poseStack.pushPose();
        InventoryScreen.renderEntityInInventory(guiLeft + 25, guiTop + 160, 55, -90, -45, weepingAngelFake);
        poseStack.popPose();
        font.draw(poseStack, Component.translatable("Statue appearance"), guiLeft - 20, guiTop + 8, Color.BLACK.getRGB());


        super.render(poseStack, mouseX, mouseY, partialTick);

    }
}
