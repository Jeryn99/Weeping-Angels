package me.sub.angels.proxy;

import me.sub.angels.client.models.item.ModelDetector;
import me.sub.angels.client.renders.entities.RenderAngelPainting;
import me.sub.angels.client.renders.entities.RenderAnomaly;
import me.sub.angels.client.renders.entities.RenderChronodyneGenerator;
import me.sub.angels.client.renders.entities.RenderWeepingAngel;
import me.sub.angels.client.renders.items.RenderItemStackBase;
import me.sub.angels.client.renders.tileentities.RenderTileEntityCG;
import me.sub.angels.client.renders.tileentities.RenderTileEntityPlinth;
import me.sub.angels.client.renders.tileentities.RenderTileEntitySnowArm;
import me.sub.angels.common.WAObjects;
import me.sub.angels.common.entities.EntityAngelPainting;
import me.sub.angels.common.entities.EntityAnomaly;
import me.sub.angels.common.entities.EntityChronodyneGenerator;
import me.sub.angels.common.entities.EntityWeepingAngel;
import me.sub.angels.common.tileentities.TileEntityChronodyneGenerator;
import me.sub.angels.common.tileentities.TileEntityPlinth;
import me.sub.angels.common.tileentities.TileEntitySnowArm;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.ArrayList;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
	
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent ev) {
		for (Item item : WAObjects.items) {
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}
		WAObjects.items = new ArrayList<>();
		WAObjects.Items.TIMEY_WIMEY_DETECTOR.setTileEntityItemStackRenderer(new RenderItemStackBase(new ModelDetector()));
	}
	
	@Override
	public void preInit() {
		super.preInit();
		RenderingRegistry.registerEntityRenderingHandler(EntityWeepingAngel.class, RenderWeepingAngel::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityAngelPainting.class, RenderAngelPainting::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityAnomaly.class, RenderAnomaly::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityChronodyneGenerator.class, RenderChronodyneGenerator::new);
	}
	
	@Override
	public void init() {
		super.init();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySnowArm.class, new RenderTileEntitySnowArm());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChronodyneGenerator.class, new RenderTileEntityCG());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlinth.class, new RenderTileEntityPlinth());
	}
	
	@Override
	public void postInit() {
		super.postInit();
	}
}
