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
import me.sub.angels.utils.RenderUtil;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.ArrayList;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
	
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent ev) {
		for (Item item : WAObjects.ITEMS) {
			RenderUtil.setItemRender(item);
		}
		WAObjects.ITEMS = new ArrayList<>();
		RenderUtil.setItemRender(WAObjects.Items.TIMEY_WIMEY_DETECTOR, new RenderItemStackBase(new ModelDetector()));
	}
	
	@Override
	public void preInit() {
		super.preInit();
		entityRenders();
	}
	
	@Override
	public void init() {
		super.init();
		tileRenders();
	}
	
	@Override
	public void postInit() {
		super.postInit();
	}

	private void tileRenders() {
		RenderUtil.bindTESR(TileEntitySnowArm.class, new RenderTileEntitySnowArm());
		RenderUtil.bindTESR(TileEntityChronodyneGenerator.class, new RenderTileEntityCG());
		RenderUtil.bindTESR(TileEntityPlinth.class, new RenderTileEntityPlinth());
	}

	private void entityRenders() {
		RenderUtil.bindEntityRender(EntityWeepingAngel.class, RenderWeepingAngel::new);
		RenderUtil.bindEntityRender(EntityAngelPainting.class, RenderAngelPainting::new);
		RenderUtil.bindEntityRender(EntityAnomaly.class, RenderAnomaly::new);
		RenderUtil.bindEntityRender(EntityChronodyneGenerator.class, RenderChronodyneGenerator::new);
	}

	@SubscribeEvent
	public static void renderHand(RenderSpecificHandEvent event) {

	}

}
