package me.suff.mc.angels.client.models.block.compat;

import me.suff.mc.angels.common.WAObjects;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;
import net.tardis.mod.tileentities.exteriors.ExteriorTile;

public class PTBExterior extends ExteriorTile {

    public static final AxisAlignedBB RENDER_BOX = new AxisAlignedBB(-2, -2, -2, 2, 5, 2);

    public static final AxisAlignedBB NORTH_BOX = new AxisAlignedBB(0, -1, -0.1, 1, 1, 1);
    public static final AxisAlignedBB EAST_BOX = new AxisAlignedBB(0, -1, 0, 1.1, 1, 1);
    public static final AxisAlignedBB SOUTH_BOX = new AxisAlignedBB(0, -1, 0, 1, 1, 1.1);
    public static final AxisAlignedBB WEST_BOX = new AxisAlignedBB(-0.1, -1, 0, 1, 1, 1);


    public PTBExterior(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public PTBExterior() {
        super(WAObjects.Tiles.POLICE_BOX.get());
    }


    @Override
    public AxisAlignedBB getDoorAABB() {
        if(level.getBlockState(getBlockPos()).hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
            switch(level.getBlockState(getBlockPos()).getValue(BlockStateProperties.HORIZONTAL_FACING)) {
                case EAST: return EAST_BOX;
                case SOUTH: return SOUTH_BOX;
                case WEST: return WEST_BOX;
                default: return NORTH_BOX;
            }
        }
        return NORTH_BOX;
    }
}
