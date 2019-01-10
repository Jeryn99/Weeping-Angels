package me.fril.angels.client.events;

import me.fril.angels.WeepingAngels;
import me.fril.angels.client.models.item.ModelDetector;
import me.fril.angels.client.renders.items.RenderItemStackBase;
import me.fril.angels.common.WAObjects;
import me.fril.angels.utils.RenderUtil;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.ArrayList;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = WeepingAngels.MODID)
public class ClientEventHandler {

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent ev) {
		WAObjects.ITEMS.forEach(RenderUtil::setItemRender);

		WAObjects.ITEMS = new ArrayList<>();
		RenderUtil.setItemRender(WAObjects.Items.TIMEY_WIMEY_DETECTOR, new RenderItemStackBase(new ModelDetector()));
	}
}
