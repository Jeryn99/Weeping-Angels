package me.suff.mc.angels.events;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.client.sounds.DetectorTickableSound;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WeepingAngels.MODID, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void onPlayerJoin(EntityJoinWorldEvent event) {
        if(event.getEntity() instanceof PlayerEntity){
            PlayerEntity playerEntity = (PlayerEntity) event.getEntity();
            Minecraft.getInstance().getSoundManager().play(new DetectorTickableSound(playerEntity));
        }
    }

}
