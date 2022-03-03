package me.suff.mc.angels.compat.tardis;

import me.suff.mc.angels.compat.tardis.registry.TardisTiles;
import net.minecraft.util.math.AxisAlignedBB;
import net.tardis.mod.tileentities.exteriors.ExteriorTile;

public class AbPropTile extends ExteriorTile {
    public static final AxisAlignedBB RENDER = new AxisAlignedBB(-1, -1, -1, 2, 2, 2);
    AxisAlignedBB NORTH_DOOR = new AxisAlignedBB(0, 0, -0.1, 1, 2, 0.5);

    public AbPropTile() {
        super(TardisTiles.EXTERIOR_2005.get());
        this.setVariants(WATexVariants.PTB);
    }

    @Override
    public AxisAlignedBB getDoorAABB() {
        return this.getDefaultEntryBox();
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return RENDER.move(this.getBlockPos());
    }
}