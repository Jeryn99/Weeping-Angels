package me.swirtzly.minecraft.angels.common.blocks;

import me.swirtzly.minecraft.angels.common.tileentities.SnowArmTile;
import me.swirtzly.minecraft.angels.utils.AngelUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SnowBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class SnowArmBlock extends SnowBlock {

    public SnowArmBlock() {
        super(Block.Properties.create(Material.CORAL).notSolid().hardnessAndResistance(3).sound(SoundType.SNOW).setRequiresTool());
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new SnowArmTile();
    }


    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        TileEntity tile = worldIn.getTileEntity(pos);
        if (tile instanceof SnowArmTile) {
            int rotation = MathHelper.floor(placer.rotationYaw);
            SnowArmTile snowArmTile = (SnowArmTile) tile;
            snowArmTile.setSnowAngelStage(AngelUtils.randowSnowStage());
            snowArmTile.setRotation(rotation);
            snowArmTile.setHasSetup(true);
            snowArmTile.setAngelVarients(AngelUtils.randomVarient());
            snowArmTile.sendUpdates();
        }
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

}
