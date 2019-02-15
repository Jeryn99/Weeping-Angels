package me.suff.angels.client.events;

import me.suff.angels.WeepingAngels;
import me.suff.angels.client.models.item.ModelDetector;
import me.suff.angels.client.renders.items.RenderItemStackBase;
import me.suff.angels.common.WAObjects;
import me.suff.angels.utils.RenderUtil;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = WeepingAngels.MODID)
public class ClientEventHandler {
	
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent ev) {
		WAObjects.ITEMS = new ArrayList<>();
		RenderUtil.setItemRender(WAObjects.Items.TIMEY_WIMEY_DETECTOR, new RenderItemStackBase(new ModelDetector()));
	}
}
