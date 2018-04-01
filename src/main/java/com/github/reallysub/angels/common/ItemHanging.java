package com.github.reallysub.angels.common;

package blaze.items;

import blaze.entities.EntityPaintingEB;
import com.github.reallysub.angels.common.entities.EntityPainting2;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemCustomPainting extends Item
{
    private final Class hangingEntityClass;

    public ItemCustomPainting(Class entityClass)
    {
        super();
        this.hangingEntityClass = entityClass;
        this.setCreativeTab(CreativeTabs.DECORATIONS);
    }

    /**
     * Called when a Block is right-clicked with this Item
     *
     * @param pos The block being right-clicked
     * @param side The side being right-clicked
     */
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (side == EnumFacing.DOWN)
        {
            return false;
        }
        else if (side == EnumFacing.UP)
        {
            return false;
        }
        else
        {
            BlockPos blockpos1 = pos.offset(side);

            if (!playerIn.canPlayerEdit(blockpos1, side, stack))
            {
                return false;
            }
            else
            {
                EntityHanging entityhanging = this.createHangingEntity(worldIn, blockpos1, side);

                if (entityhanging != null && entityhanging.onValidSurface())
                {
                    if (!worldIn.isRemote)
                    {
                        worldIn.spawnEntity(entityhanging);
                    }

                   stack.shrink(1);
                }

                return true;
            }
        }
    }

    private EntityHanging createHangingEntity(World worldIn, BlockPos pos, EnumFacing clickedSide)
    {
        return new EntityPainting2(worldIn, pos, clickedSide);
    }
}