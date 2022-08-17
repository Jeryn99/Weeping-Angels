package craig.software.mc.angels.events;

import com.mojang.blaze3d.shaders.FogShape;
import craig.software.mc.angels.client.renders.WingsLayer;
import craig.software.mc.angels.client.sounds.DetectorTickableSound;
import craig.software.mc.angels.common.WAObjects;
import craig.software.mc.angels.common.entities.WeepingAngelTypes;
import craig.software.mc.angels.common.variants.AngelVariant;
import craig.software.mc.angels.common.variants.AngelVariants;
import craig.software.mc.angels.utils.DateChecker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.SkinCustomizationScreen;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RenderHighlightEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by Craig on 11/02/2020 @ 21:31
 */
@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class ClientEvents {

    public static boolean isInCatacombs = false;
    private static SoundInstance iSound = null;

    @SubscribeEvent
    public static void onPlayerJoin(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Player player) {
            Minecraft.getInstance().getSoundManager().play(new DetectorTickableSound(player));
        }
    }

    @SubscribeEvent
    public static void onBlockHighlight(RenderHighlightEvent.Block event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.hitResult != null && minecraft.hitResult.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockRayTraceResult = (BlockHitResult) minecraft.hitResult;
            LocalPlayer playerEntity = minecraft.player;
            ClientLevel world = minecraft.level;
            boolean canSee = playerEntity.getMainHandItem().getItem() == WAObjects.Blocks.STATUE.get().asItem() || playerEntity.getOffhandItem().getItem() == WAObjects.Blocks.STATUE.get().asItem();
            event.setCanceled(!canSee && world.getBlockState(blockRayTraceResult.getBlockPos()).getBlock() == WAObjects.Blocks.STATUE.get());
        }
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (Minecraft.getInstance().player == null) return;
        SoundManager sound = Minecraft.getInstance().getSoundManager();
        if (isInCatacombs) {
            sound.stop(null, SoundSource.MUSIC);
            if (iSound == null) {
                iSound = SimpleSoundInstance.forAmbientAddition(WAObjects.Sounds.CATACOMB.get());
            }
            if (!sound.isActive(iSound)) {
                sound.play(iSound);
            }
        } else {
            if (sound.isActive(iSound)) {
                sound.stop(iSound);
            }
        }
    }

    @SubscribeEvent
    public static void on(ScreenEvent.Init screenEvent) {
        Screen screen = screenEvent.getScreen();
        if (screen instanceof SkinCustomizationScreen) {
            screenEvent.addListener(createAngelVariantButton(screen));
            screenEvent.addListener(createAngelModelButton(screen));
        }
    }

    public static CycleButton<WeepingAngelTypes> createAngelModelButton(Screen screen) {
        return CycleButton.builder((Function<WeepingAngelTypes, Component>) weepingAngelTypes -> Component.literal(weepingAngelTypes.getReadable())).withValues(WeepingAngelTypes.values()).displayOnlyValue().withInitialValue(WeepingAngelTypes.DISASTER_MC).create(screen.width / 2 - 20, 185, 40, 20, Component.literal("MIRROR"), (p_169843_, p_169844_) -> {
           //TODO
        });
    }

    public static CycleButton<Object> createAngelVariantButton(Screen screen) {
        ArrayList<AngelVariant> variants = new ArrayList<>();
        for (Map.Entry<ResourceKey<AngelVariant>, AngelVariant> entry : AngelVariants.VARIANTS_REGISTRY.get().getEntries()) {
            variants.add(entry.getValue());
        }

        return CycleButton.builder((variant) -> {
            if (variant instanceof AngelVariant angelVariant) {
                return Component.literal(String.valueOf(angelVariant.getRegistryName()));
            }
            return Component.literal("");
        }).withValues(variants.toArray(new AngelVariant[0])).withInitialValue(variants.get(0)).create(screen.width / 2 - 102, 4, 205, 20, Component.translatable("Wings"), (p_167441_, p_167442_) -> {
            //TODO
        });
    }

    @SubscribeEvent
    public static void tickDate(TickEvent.ClientTickEvent event) {
        DateChecker.tick(event);
    }

    @SubscribeEvent
    public static void onSetupFogDensity(ViewportEvent.RenderFog event) {
        if (Minecraft.getInstance().level != null && isInCatacombs) {
            event.setCanceled(true);
            event.setNearPlaneDistance(-8);
            event.setFarPlaneDistance(60 * 0.5F);
            event.setFogShape(FogShape.SPHERE);
        }
    }

    @SubscribeEvent
    public static void onSetupFogColor(ViewportEvent.RenderFog.ComputeFogColor event) {
        if (Minecraft.getInstance().level != null && isInCatacombs) {
        /*   event.setRed(105 / 255F);
            event.setGreen(105 / 255F);
            event.setBlue(105 / 255F);*/
            event.setRed(0.14F);
            event.setGreen(0.15F);
            event.setBlue(0.22F);
        }
    }

    @Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class On {

        @SubscribeEvent
        public static void renderLayers(EntityRenderersEvent.AddLayers addLayers) {
            addLayers.getSkins().forEach(skin -> {
                LivingEntityRenderer<? extends Player, ? extends EntityModel<? extends Player>> renderer = addLayers.getSkin(skin);
                renderer.addLayer(new WingsLayer(renderer));
            });
        }
    }

}
