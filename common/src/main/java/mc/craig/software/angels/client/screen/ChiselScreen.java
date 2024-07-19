package mc.craig.software.angels.client.screen;

import com.google.common.collect.Lists;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.WAConstants;
import mc.craig.software.angels.common.blockentity.StatueBlockEntity;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import mc.craig.software.angels.common.entity.angel.ai.AngelEmotion;
import mc.craig.software.angels.common.entity.angel.ai.AngelVariant;
import mc.craig.software.angels.network.WANetworkManager;
import mc.craig.software.angels.network.messages.StatueUpdate;
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
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.awt.*;
import java.util.function.Function;

public class ChiselScreen extends Screen {

    private static final ResourceLocation BACKGROUND = ResourceLocation.tryBuild(WeepingAngels.MODID, "textures/ui/menubg.png");
    private static final WeepingAngel weepingAngelFake = new WeepingAngel(Minecraft.getInstance().level);
    public static AnimationState POSE_ANIMATION_STATE = new AnimationState();
    private final BlockPos blockPos;
    private final ResourceKey<Level> level;
    public int guiLeft, guiTop, xSize, ySize;
    private CycleButton<AngelEmotion> emotionButton;
    private Button quitButton;
    private CycleButton<AngelVariant> variantCycleButton;
    private CycleButton<Integer> poseCycleButton;


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

        emotionButton = CycleButton.builder((Function<AngelEmotion, Component>) o -> Component.literal(o.getId()))
                .withValues(AngelEmotion.values())
                .withInitialValue(AngelEmotion.ANGRY)
                .create(this.width / 2 + 4 - 20, this.height / 4 + 72 + -16, 130, 20, Component.translatable(WAConstants.ANGEL_EMOTION), (cycleButton, angelEmotion) -> weepingAngelFake.setEmotion(angelEmotion));

        variantCycleButton = CycleButton.builder((Function<AngelVariant, Component>) o -> Component.translatable("variant." + WeepingAngels.MODID + "." + o.location().getPath()))
                .withValues(AngelVariant.VARIANTS.values())
                .withInitialValue(AngelVariant.BASALT)
                .create(this.width / 2 + 4 - 20, this.height / 4 + 48 + -16, 130, 20, Component.translatable(WAConstants.ANGEL_VARIANT), (cycleButton, angelVariant) -> weepingAngelFake.setVariant(angelVariant));

        poseCycleButton = CycleButton.builder((Function<Integer, Component>) o -> Component.literal(o.toString()))
                .withValues(Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12))
                .withInitialValue(0)
                .create(this.width / 2 + 4 - 20, this.height / 4 + 72 + 25 + -16, 130, 20, Component.translatable(WAConstants.ANGEL_POSES), (cycleButton, angelVariant) -> weepingAngelFake.setFakeAnimation(angelVariant));

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

        quitButton = Button.builder(Component.translatable("Chisel"), button -> {
            updateServerStatue();
            Minecraft.getInstance().setScreen(null);
        }).size(130, 20).build();

        int quitButtonX = this.width / 2 + 4 - 20;
        int quitButtonY = this.height / 4 + 72 + 25 + -16 + 25 + 5;

        quitButton.setPosition(quitButtonX, quitButtonY);

        addRenderableWidget(quitButton);
    }

    private void updateServerStatue() {
        StatueUpdate statueUpdate = new StatueUpdate(variantCycleButton.getValue(), emotionButton.getValue(), poseCycleButton.getValue(), blockPos, level);
        WANetworkManager.get().sendToServer(statueUpdate);
    }

    @Override
    public void onClose() {
        super.onClose();
        updateServerStatue();
    }


    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBlurredBackground(partialTick);


        if (!POSE_ANIMATION_STATE.isStarted()) {
            POSE_ANIMATION_STATE.start(12);
        }

        Vector3f ARMOR_STAND_TRANSLATION = new Vector3f();
        Quaternionf ARMOR_STAND_ANGLE = (new Quaternionf()).rotationXYZ(0.43633232F, 45 - 180, 3.1415927F);

        InventoryScreen.renderEntityInInventory(guiGraphics, (float) (this.guiLeft + 25), (float) (this.guiTop + 160), 50, ARMOR_STAND_TRANSLATION, ARMOR_STAND_ANGLE, null, weepingAngelFake);

        guiGraphics.blit(BACKGROUND, guiLeft - 30, guiTop, 0, 0, 256, 256, 256, 256);
        guiGraphics.drawString(font, Component.translatable("Statue appearance"), guiLeft - 20, guiTop + 8, Color.WHITE.getRGB());




        super.render(guiGraphics, mouseX, mouseY, partialTick);

    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int i, int j, float f) {

    }
}
