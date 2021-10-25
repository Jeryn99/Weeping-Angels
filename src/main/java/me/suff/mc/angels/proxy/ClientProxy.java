package me.suff.mc.angels.proxy;

import me.suff.mc.angels.client.renders.entities.RenderAngelPainting;
import me.suff.mc.angels.client.renders.entities.RenderAnomaly;
import me.suff.mc.angels.client.renders.entities.RenderChronodyneGenerator;
import me.suff.mc.angels.client.renders.entities.RenderWeepingAngel;
import me.suff.mc.angels.client.renders.tileentities.RenderTileEntityCG;
import me.suff.mc.angels.client.renders.tileentities.RenderTileEntityPlinth;
import me.suff.mc.angels.client.renders.tileentities.RenderTileEntitySnowArm;
import me.suff.mc.angels.client.renders.tileentities.RenderTileEntityStatue;
import me.suff.mc.angels.common.entities.EntityAngelPainting;
import me.suff.mc.angels.common.entities.EntityAnomaly;
import me.suff.mc.angels.common.entities.EntityChronodyneGenerator;
import me.suff.mc.angels.common.entities.EntityWeepingAngel;
import me.suff.mc.angels.common.tileentities.TileEntityChronodyneGenerator;
import me.suff.mc.angels.common.tileentities.TileEntityPlinth;
import me.suff.mc.angels.common.tileentities.TileEntitySnowArm;
import me.suff.mc.angels.common.tileentities.TileEntityStatue;
import me.suff.mc.angels.utils.RenderUtil;

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
