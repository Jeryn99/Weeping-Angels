package me.swirtzly.angels.client;

import me.swirtzly.angels.client.renders.entities.RenderAnomaly;
import me.swirtzly.angels.client.renders.entities.RenderCG;
import me.swirtzly.angels.client.renders.entities.RenderWeepingAngel;
import me.swirtzly.angels.client.renders.tileentities.RenderTileEntityCG;
import me.swirtzly.angels.client.renders.tileentities.RenderTileEntityPlinth;
import me.swirtzly.angels.client.renders.tileentities.RenderTileEntitySnowArm;
import me.swirtzly.angels.common.WAObjects;
import me.swirtzly.angels.common.entities.EntityAnomaly;
import me.swirtzly.angels.common.entities.EntityChronodyneGenerator;
import me.swirtzly.angels.common.entities.EntityWeepingAngel;
import me.swirtzly.angels.common.items.ItemDetector;
import me.swirtzly.angels.common.tileentities.TileEntityChronodyneGenerator;
import me.swirtzly.angels.common.tileentities.TileEntityPlinth;
import me.swirtzly.angels.common.tileentities.TileEntitySnowArm;
import me.swirtzly.angels.utils.ClientUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

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
            ClientUtil.playSound(player, WAObjects.Sounds.PROJECTOR.get(), SoundCategory.PLAYERS, true, () -> !(player.getHeldItemMainhand().getItem() instanceof ItemDetector) && !(player.getHeldItemOffhand().getItem() instanceof ItemDetector), 0.4f);
        }
    }


    @SubscribeEvent
    public static void doClientStuff(FMLClientSetupEvent event) {
        ClientUtil.bindTESR(TileEntitySnowArm.class, new RenderTileEntitySnowArm());
        ClientUtil.bindTESR(TileEntityChronodyneGenerator.class, new RenderTileEntityCG());
        ClientUtil.bindTESR(TileEntityPlinth.class, new RenderTileEntityPlinth());

        ClientUtil.bindEntityRender(EntityWeepingAngel.class, RenderWeepingAngel::new);
        ClientUtil.bindEntityRender(EntityAnomaly.class, RenderAnomaly::new);
        ClientUtil.bindEntityRender(EntityChronodyneGenerator.class, (EntityRendererManager p_i50956_1_) -> new RenderCG(p_i50956_1_, Minecraft.getInstance().getItemRenderer(), 12));
    }
}
