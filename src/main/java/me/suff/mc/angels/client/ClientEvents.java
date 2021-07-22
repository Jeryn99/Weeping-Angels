package me.suff.mc.angels.client;

import me.suff.mc.angels.common.AngelParticles;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.conversion.particle.AngelParticle;
import me.suff.mc.angels.utils.DateChecker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.DrawSelectionEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Created by Craig on 11/02/2020 @ 21:31
 */
@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class ClientEvents {

    public static boolean isInCatacombs = false;
    private static SoundInstance iSound = null;

    @SubscribeEvent
    public static void registerParticles(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(AngelParticles.INFECTION.get(), new AngelParticle.Factory());
    }

    @SubscribeEvent
    public static void onBlockHighlight(DrawSelectionEvent.HighlightBlock event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.hitResult.getType() == HitResult.Type.BLOCK) {
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
                iSound = SimpleSoundInstance.forUI(WAObjects.Sounds.CATACOMB.get(), 1);
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
            //TODO GlStateManager._fogMode(GlStateManager.EXP.value);
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
