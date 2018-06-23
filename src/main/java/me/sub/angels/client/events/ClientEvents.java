package me.sub.angels.client.events;

import me.sub.angels.common.entities.EntityAngel;
import me.sub.angels.main.config.WAConfig;
import me.sub.angels.utils.AngelUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientEvents {

	@SubscribeEvent
	public static void setUpCamera(FOVUpdateEvent e) {

		EntityPlayerSP player = Minecraft.getMinecraft().player;
		float prev = e.getFov();

		if (Minecraft.getMinecraft().objectMouseOver.entityHit instanceof EntityAngel && WAConfig.angels.cameraUpdate) {
			EntityAngel angel = (EntityAngel) Minecraft.getMinecraft().objectMouseOver.entityHit;
			if (AngelUtils.isInSight(player, angel) && AngelUtils.isInSight(angel, player)) {
				e.setNewfov(0.5F);
			} else {
				e.setNewfov(prev);
			}
		}
	}

}
