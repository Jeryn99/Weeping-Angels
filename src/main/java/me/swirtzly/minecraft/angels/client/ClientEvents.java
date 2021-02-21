package me.swirtzly.minecraft.angels.client;

import com.mojang.blaze3d.platform.GlStateManager;
import me.swirtzly.minecraft.angels.WeepingAngels;
import me.swirtzly.minecraft.angels.common.WAObjects;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.DrawHighlightEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

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
        if (minecraft.objectMouseOver.getType() == RayTraceResult.Type.BLOCK) {
            BlockRayTraceResult blockRayTraceResult = (BlockRayTraceResult) minecraft.objectMouseOver;
            ClientPlayerEntity playerEntity = minecraft.player;
            ClientWorld world = minecraft.world;
            boolean canSee = playerEntity.getHeldItemMainhand().getItem() == WAObjects.Blocks.STATUE.get().asItem() || playerEntity.getHeldItemOffhand().getItem() == WAObjects.Blocks.STATUE.get().asItem();
            event.setCanceled(!canSee && world.getBlockState(blockRayTraceResult.getPos()).getBlock() == WAObjects.Blocks.STATUE.get());
        }
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (Minecraft.getInstance().player == null) return;
        SoundHandler sound = Minecraft.getInstance().getSoundHandler();
        if(isInCatacombs) {

            if(iSound == null){
                iSound = SimpleSound.master(WAObjects.Sounds.CATACOMB.get(), 1);
            }

            if(!sound.isPlaying(iSound)){
                sound.play(iSound);
                sound.stop(null, SoundCategory.MUSIC);
            }
        } else {
            if(sound.isPlaying(iSound)){
                sound.stop(iSound);
            }
        }
    }

    @SubscribeEvent
    public static void onSetupFogDensity(EntityViewRenderEvent.RenderFogEvent.FogDensity event) {
        if (Minecraft.getInstance().world != null && isInCatacombs) {
            GlStateManager.fogMode(GlStateManager.FogMode.EXP.param);
            event.setCanceled(true);
            event.setDensity(0.07F);
        }
    }

    @SubscribeEvent
    public static void onSetupFogColor(EntityViewRenderEvent.RenderFogEvent.FogColors event) {
        if (Minecraft.getInstance().world != null && isInCatacombs) {
/*           event.setRed(105 / 255F);
            event.setGreen(105 / 255F);
            event.setBlue(105 / 255F);*/
            event.setRed(0.14F);
            event.setGreen(0.15F);
            event.setBlue(0.22F);
        }
    }

}
