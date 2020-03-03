package me.swirtzly.angels.proxy;

import me.swirtzly.angels.client.renders.entities.RenderAngelPainting;
import me.swirtzly.angels.client.renders.entities.RenderAnomaly;
import me.swirtzly.angels.client.renders.entities.RenderChronodyneGenerator;
import me.swirtzly.angels.client.renders.entities.RenderWeepingAngel;
import me.swirtzly.angels.client.renders.tileentities.RenderTileEntityCG;
import me.swirtzly.angels.client.renders.tileentities.RenderTileEntityPlinth;
import me.swirtzly.angels.client.renders.tileentities.RenderTileEntitySnowArm;
import me.swirtzly.angels.client.renders.tileentities.RenderTileEntityStatue;
import me.swirtzly.angels.common.entities.EntityAngelPainting;
import me.swirtzly.angels.common.entities.EntityAnomaly;
import me.swirtzly.angels.common.entities.EntityChronodyneGenerator;
import me.swirtzly.angels.common.entities.EntityWeepingAngel;
import me.swirtzly.angels.common.tileentities.TileEntityChronodyneGenerator;
import me.swirtzly.angels.common.tileentities.TileEntityPlinth;
import me.swirtzly.angels.common.tileentities.TileEntitySnowArm;
import me.swirtzly.angels.common.tileentities.TileEntityStatue;
import me.swirtzly.angels.utils.RenderUtil;

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
        RenderUtil.bindTESR(TileEntityStatue.class, new RenderTileEntityStatue());
    }

    private void entityRenders() {
        RenderUtil.bindEntityRender(EntityWeepingAngel.class, RenderWeepingAngel::new);
        RenderUtil.bindEntityRender(EntityAngelPainting.class, RenderAngelPainting::new);
        RenderUtil.bindEntityRender(EntityAnomaly.class, RenderAnomaly::new);
        RenderUtil.bindEntityRender(EntityChronodyneGenerator.class, RenderChronodyneGenerator::new);
    }
}
