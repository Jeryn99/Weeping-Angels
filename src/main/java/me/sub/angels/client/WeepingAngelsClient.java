package me.sub.angels.client;

import me.sub.angels.client.renders.entities.RenderAnomaly;
import me.sub.angels.client.renders.entities.RenderChronodyneGenerator;
import me.sub.angels.client.renders.entities.RenderWeepingAngel;
import me.sub.angels.client.renders.tileentities.RenderTileEntityCG;
import me.sub.angels.client.renders.tileentities.RenderTileEntityPlinth;
import me.sub.angels.client.renders.tileentities.RenderTileEntitySnowArm;
import me.sub.angels.common.entities.EntityAnomaly;
import me.sub.angels.common.entities.EntityChronodyneGenerator;
import me.sub.angels.common.entities.EntityWeepingAngel;
import me.sub.angels.common.tileentities.TileEntityChronodyneGenerator;
import me.sub.angels.common.tileentities.TileEntityPlinth;
import me.sub.angels.common.tileentities.TileEntitySnowArm;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.client.render.BlockEntityRendererRegistry;
import net.fabricmc.fabric.client.render.EntityRendererRegistry;

/**
 * Created by Nictogen on 1/31/19.
 */
public class WeepingAngelsClient implements ClientModInitializer
{
	@Override public void onInitializeClient()
	{
		//TODO
//		WAObjects.Items.TIMEY_WIMEY_DETECTOR.setTileEntityItemStackRenderer(new RenderItemStackBase(new ModelDetector()));
		EntityRendererRegistry.INSTANCE.register(EntityWeepingAngel.class, (manager, context) -> new RenderWeepingAngel(manager));
		EntityRendererRegistry.INSTANCE.register(EntityAnomaly.class, (manager, context) -> new RenderAnomaly(manager));
		EntityRendererRegistry.INSTANCE.register(EntityChronodyneGenerator.class, (manager, context) -> new RenderChronodyneGenerator(manager));
//		EntityRendererRegistry.INSTANCE.register(EntityAngelPainting.class, (manager, context) -> new RenderAngelPainting(manager));

		BlockEntityRendererRegistry.INSTANCE.register(TileEntitySnowArm.class, new RenderTileEntitySnowArm());
		BlockEntityRendererRegistry.INSTANCE.register(TileEntityChronodyneGenerator.class, new RenderTileEntityCG());
		BlockEntityRendererRegistry.INSTANCE.register(TileEntityPlinth.class, new RenderTileEntityPlinth());
	}

}
