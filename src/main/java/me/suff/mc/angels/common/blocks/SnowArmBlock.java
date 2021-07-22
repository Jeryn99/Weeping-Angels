package me.suff.mc.angels.common.blocks;

import me.suff.mc.angels.common.entities.AngelEnums;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import me.suff.mc.angels.common.tileentities.SnowArmTile;
import me.suff.mc.angels.common.variants.AngelTypes;
import me.suff.mc.angels.utils.AngelUtil;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class SnowArmBlock extends SnowBlock {

    public SnowArmBlock() {
        super(AbstractBlock.Properties.of(Material.CORAL).randomTicks().noOcclusion().strength(3).sound(SoundType.SNOW).requiresCorrectToolForDrops());
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new SnowArmTile();
    }

    @Override
    public void entityInside(BlockState blockState, World world, BlockPos blockPos, Entity entity) {
        if (world.getBlockEntity(blockPos) instanceof SnowArmTile) {
            SnowArmTile snowArmTile = (SnowArmTile) world.getBlockEntity(blockPos);
            if (snowArmTile.getSnowAngelStage() == SnowArmTile.SnowAngelStages.ARM) {
                entity.makeStuckInBlock(blockState, new Vector3d(0.15D, 0.05F, 0.15D));
            }
        }
    }


    @Override
    public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {

        super.setPlacedBy(worldIn, pos, state, placer, stack);
        TileEntity tile = worldIn.getBlockEntity(pos);
        if (tile instanceof SnowArmTile) {
            int rotation = MathHelper.floor(placer.yRot);
            SnowArmTile snowArmTile = (SnowArmTile) tile;
            if (!snowArmTile.isHasSetup()) {
                snowArmTile.setSnowAngelStage(AngelUtil.randowSnowStage());
                snowArmTile.setRotation(rotation);
                snowArmTile.setHasSetup(true);
                snowArmTile.setVariant(AngelTypes.getWeightedRandom());
                snowArmTile.sendUpdates();
            }
        }
    }

    @Override
    public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (worldIn.getBrightness(LightType.BLOCK, pos) > 11) {
            InventoryHelper.dropItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Blocks.SNOW));
            TileEntity tile = worldIn.getBlockEntity(pos);
            if (tile instanceof SnowArmTile) {
                SnowArmTile snowArmTile = (SnowArmTile) tile;
                WeepingAngelEntity angel = new WeepingAngelEntity(worldIn);
                angel.setType(AngelEnums.AngelType.ANGELA_MC);
                angel.setVarient(snowArmTile.getVariant());
                angel.setPos(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
                worldIn.addFreshEntity(angel);
                worldIn.removeBlock(pos, false);
            }
        }

    }

}
