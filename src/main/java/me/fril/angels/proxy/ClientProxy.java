package me.fril.angels.proxy;

import me.fril.angels.client.renders.entities.RenderAngelPainting;
import me.fril.angels.client.renders.entities.RenderAnomaly;
import me.fril.angels.client.renders.entities.RenderChronodyneGenerator;
import me.fril.angels.client.renders.entities.RenderWeepingAngel;
import me.fril.angels.client.renders.tileentities.RenderTileEntityCG;
import me.fril.angels.client.renders.tileentities.RenderTileEntityPlinth;
import me.fril.angels.client.renders.tileentities.RenderTileEntitySnowArm;
import me.fril.angels.common.entities.EntityAngelPainting;
import me.fril.angels.common.entities.EntityAnomaly;
import me.fril.angels.common.entities.EntityChronodyneGenerator;
import me.fril.angels.common.entities.EntityWeepingAngel;
import me.fril.angels.common.tileentities.TileEntityChronodyneGenerator;
import me.fril.angels.common.tileentities.TileEntityPlinth;
import me.fril.angels.common.tileentities.TileEntitySnowArm;
import me.fril.angels.utils.RenderUtil;

@SuppressWarnings("unused")
public class ClientProxy extends CommonProxy {
	
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
}
