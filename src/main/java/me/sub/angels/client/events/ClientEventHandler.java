package me.sub.angels.client.events;

import me.sub.angels.WeepingAngels;
import me.sub.angels.client.models.item.ModelDetector;
import me.sub.angels.client.renders.items.RenderItemStackBase;
import me.sub.angels.common.WAObjects;
import me.sub.angels.common.entities.EntityQuantumLockBase;
import me.sub.angels.common.packets.MessageUpdateSeen;
import me.sub.angels.config.WAConfig;
import me.sub.angels.utils.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.ArrayList;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = WeepingAngels.MODID)
public class ClientEventHandler {

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent ev) {
		for (Item item : WAObjects.ITEMS) {
			RenderUtil.setItemRender(item);
		}
		WAObjects.ITEMS = new ArrayList<>();
		RenderUtil.setItemRender(WAObjects.Items.TIMEY_WIMEY_DETECTOR, new RenderItemStackBase(new ModelDetector()));
	}

	@SubscribeEvent
	public static void viveCraft(RenderLivingEvent.Post<EntityQuantumLockBase> e) {
		if (WAConfig.integrations.vivecraftSupport) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
						EntityLivingBase entity = e.getEntity();
						if (entity instanceof EntityQuantumLockBase) {
							EntityQuantumLockBase angel = (EntityQuantumLockBase) entity;
							if (!angel.isSeen()) {
								WeepingAngels.NETWORK.sendToServer(new MessageUpdateSeen(angel.getEntityId()));
							}
						}
					}
			);
		}
	}


}
