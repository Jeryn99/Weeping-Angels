package me.swirtzly.angels.client;

import me.swirtzly.angels.common.WAObjects;
import me.swirtzly.angels.common.items.DetectorItem;
import me.swirtzly.angels.utils.ClientUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Created by Swirtzly
 * on 11/02/2020 @ 21:31
 */
@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void onPlayerJoin(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getEntity();
            ClientUtil.playSound(player, WAObjects.Sounds.PROJECTOR.get(), SoundCategory.PLAYERS, true, () -> !(player.getHeldItemMainhand().getItem() instanceof DetectorItem) && !(player.getHeldItemOffhand().getItem() instanceof DetectorItem), 0.4f);
        }
    }

}
