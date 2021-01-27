package me.swirtzly.minecraft.angels.common.blocks;

import me.swirtzly.minecraft.angels.common.entities.AngelEnums;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import me.swirtzly.minecraft.angels.common.tileentities.SnowArmTile;
import me.swirtzly.minecraft.angels.utils.AngelUtils;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class SnowArmBlock extends SnowBlock {

    public SnowArmBlock() {
        super(Block.Properties.create(Material.CORAL).tickRandomly().notSolid().hardnessAndResistance(3).sound(SoundType.SNOW).setRequiresTool());
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
            if (!snowArmTile.isHasSetup()) {
                snowArmTile.setSnowAngelStage(AngelUtils.randowSnowStage());
                snowArmTile.setRotation(rotation);
                snowArmTile.setHasSetup(true);
                snowArmTile.setAngelVariants(AngelUtils.randomVarient());
                snowArmTile.sendUpdates();
            }
        }
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.isIn(newState.getBlock())) {
            super.onReplaced(state, worldIn, pos, newState, isMoving);
        }
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (worldIn.getLightFor(LightType.BLOCK, pos) > 11) {
            InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Blocks.SNOW));
            TileEntity tile = worldIn.getTileEntity(pos);
            if (tile instanceof SnowArmTile) {
                SnowArmTile snowArmTile = (SnowArmTile) tile;
                WeepingAngelEntity angel = new WeepingAngelEntity(worldIn);
                angel.setType(AngelEnums.AngelType.ANGELA_MC);
                angel.setVarient(snowArmTile.getAngelVariants());
                angel.setPosition(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
                worldIn.addEntity(angel);
                worldIn.removeBlock(pos, false);
            }
        }

    }

}
