package me.suff.mc.angels.common.tileentities;

import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.AxisAlignedBB;
import net.tardis.mod.tileentities.exteriors.ExteriorTile;

public class AbPropTile extends ExteriorTile {
    AxisAlignedBB NORTH_DOOR = new AxisAlignedBB(0, 0, -0.1, 1, 2, 0.5);
    public static final AxisAlignedBB RENDER = new AxisAlignedBB(-1, -1, -1, 2, 2, 2);

    public AbPropTile() {
        super(STiles.exterior_abprop.get());
    }

    @Override
    public AxisAlignedBB getDoorAABB() {
        return this.getDefaultEntryBox();
    }
}