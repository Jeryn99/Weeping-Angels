package mc.craig.software.angels.forge.compat.tardis;

import mc.craig.software.angels.forge.compat.tardis.registry.TardisTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.tardis.mod.tileentities.exteriors.ExteriorTile;

public class AbPropTile extends ExteriorTile {
    public static final AABB RENDER = new AABB(-1, -1, -1, 2, 2, 2);

    public AbPropTile(BlockPos blockPos, BlockState blockState) {
        super(TardisTiles.EXTERIOR_2005.get(), blockPos, blockState);
        this.setVariants(WATexVariants.PTB);
    }

    @Override
    public AABB getDoorAABB() {
        return this.getDefaultEntryBox();
    }

    @Override
    public AABB getRenderBoundingBox() {
        return RENDER.move(this.getBlockPos());
    }
}
