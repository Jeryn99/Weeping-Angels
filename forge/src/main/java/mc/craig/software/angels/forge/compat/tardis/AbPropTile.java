package mc.craig.software.angels.forge.compat.tardis;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.forge.compat.tardis.registry.TardisTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.tardis.mod.Tardis;
import net.tardis.mod.tileentities.exteriors.ExteriorTile;

public class AbPropTile extends ExteriorTile {
    public static final AABB RENDER = new AABB(-1, -1, -1, 2, 2, 2);

    public AbPropTile(BlockPos blockPos, BlockState blockState) {
        super(TardisTiles.EXTERIOR_2005.get(), blockPos, blockState);
        this.setVariants(WATexVariants.PTB);
    }
    @Override
    public Vec3 getExteriorOffsetVec3(Vec3 vec3) {
        return vec3.add(0, 0.3750, 0).relative(this.level.getBlockState(this.getBlockPos()).getValue(BlockStateProperties.HORIZONTAL_FACING), 0.3);
    }
    @Override
    public Vec3 getInteriorOffsetVec3(Vec3 vec3, Direction direction) {
        return vec3.add(0, 0.0625, 0).relative(direction.getOpposite(), 0.125);
    }
    @Override
    public double getPortalWidth() {
        return 1.25;
    }
    @Override
    public double getPortalHeight() {
        return 2.0625;
    }

    @Override
    public double getPortalExtHeight() {
        return 2.5625;
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
