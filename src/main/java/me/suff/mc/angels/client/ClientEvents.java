package me.suff.mc.angels.client;

import com.mojang.blaze3d.platform.GlStateManager;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.utils.DateChecker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.DrawHighlightEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Created by Craig on 11/02/2020 @ 21:31
 */
@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class ClientEvents {

    public static boolean isInCatacombs = false;
    private static ISound iSound = null;

    @SubscribeEvent
    public static void onBlockHighlight(DrawHighlightEvent.HighlightBlock event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.hitResult.getType() == RayTraceResult.Type.BLOCK) {
            BlockRayTraceResult blockRayTraceResult = (BlockRayTraceResult) minecraft.hitResult;
            ClientPlayerEntity playerEntity = minecraft.player;
            ClientWorld world = minecraft.level;
            boolean canSee = playerEntity.getMainHandItem().getItem() == WAObjects.Blocks.STATUE.get().asItem() || playerEntity.getOffhandItem().getItem() == WAObjects.Blocks.STATUE.get().asItem();
            event.setCanceled(!canSee && world.getBlockState(blockRayTraceResult.getBlockPos()).getBlock() == WAObjects.Blocks.STATUE.get());
        }
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (Minecraft.getInstance().player == null) return;
        SoundHandler sound = Minecraft.getInstance().getSoundManager();
        if (isInCatacombs) {
            sound.stop(null, SoundCategory.MUSIC);
            if (iSound == null) {
                iSound = SimpleSound.forUI(WAObjects.Sounds.CATACOMB.get(), 1);
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
    public static void tickDate(TickEvent.ClientTickEvent event) {
        DateChecker.tick();
    }

    @SubscribeEvent
    public static void onSetupFogDensity(EntityViewRenderEvent.RenderFogEvent.FogDensity event) {
        if (Minecraft.getInstance().level != null && isInCatacombs) {
            GlStateManager._fogMode(GlStateManager.FogMode.EXP.value);
            event.setCanceled(true);
            event.setDensity(0.07F);
        }
    }

    @SubscribeEvent
    public static void onSetupFogColor(EntityViewRenderEvent.RenderFogEvent.FogColors event) {
        if (Minecraft.getInstance().level != null && isInCatacombs) {
/*           event.setRed(105 / 255F);
            event.setGreen(105 / 255F);
            event.setBlue(105 / 255F);*/
            event.setRed(0.14F);
            event.setGreen(0.15F);
            event.setBlue(0.22F);
        }
    }

}
