package me.suff.angels;

import me.suff.angels.client.renders.entities.RenderAngelPainting;
import me.suff.angels.client.renders.entities.RenderAnomaly;
import me.suff.angels.client.renders.entities.RenderChronodyneGenerator;
import me.suff.angels.client.renders.entities.RenderWeepingAngel;
import me.suff.angels.client.renders.tileentities.RenderTileEntityCG;
import me.suff.angels.client.renders.tileentities.RenderTileEntityPlinth;
import me.suff.angels.client.renders.tileentities.RenderTileEntitySnowArm;
import me.suff.angels.common.entities.EntityAngelPainting;
import me.suff.angels.common.entities.EntityAnomaly;
import me.suff.angels.common.entities.EntityChronodyneGenerator;
import me.suff.angels.common.entities.EntityWeepingAngel;
import me.suff.angels.common.tileentities.TileEntityChronodyneGenerator;
import me.suff.angels.common.tileentities.TileEntityPlinth;
import me.suff.angels.common.tileentities.TileEntitySnowArm;
import me.suff.angels.proxy.CommonProxy;
import me.suff.angels.utils.RenderUtil;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(WeepingAngels.MODID)
public class WeepingAngels {
	
	public static final String MODID = "weeping-angels";
	public static final String NAME = "Weeping Angels";
	
	public static Logger LOGGER = LogManager.getLogger(NAME);
	
	public WeepingAngels(){
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
	}
	
	private void doClientStuff(final FMLClientSetupEvent event) {
		RenderUtil.bindTESR(TileEntitySnowArm.class, new RenderTileEntitySnowArm());
		RenderUtil.bindTESR(TileEntityChronodyneGenerator.class, new RenderTileEntityCG());
		RenderUtil.bindTESR(TileEntityPlinth.class, new RenderTileEntityPlinth());
		
		RenderUtil.bindEntityRender(EntityWeepingAngel.class, RenderWeepingAngel::new);
		RenderUtil.bindEntityRender(EntityAngelPainting.class, RenderAngelPainting::new);
		RenderUtil.bindEntityRender(EntityAnomaly.class, RenderAnomaly::new);
		RenderUtil.bindEntityRender(EntityChronodyneGenerator.class, RenderChronodyneGenerator::new);
	}
	
	
	
}
