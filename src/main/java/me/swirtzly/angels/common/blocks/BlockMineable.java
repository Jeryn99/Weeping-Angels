package me.swirtzly.angels.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import java.util.Random;
import java.util.function.Supplier;

public class BlockMineable extends Block {

    private Supplier<ItemStack> itemSuppler;
    private int itemQuantity, itemVariation;

    public BlockMineable(Supplier<ItemStack> stackSupplier, int quantity, int variation) {
        super(Material.ROCK);
        setHardness(5.0F);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        this.itemSuppler = stackSupplier;
        setSoundType(SoundType.STONE);
        itemQuantity = quantity;
        itemVariation = variation;
        setHarvestLevel("pickaxe", 0);
    }

    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return MapColor.IRON;
    }

    /**
     * Get the itemSuppler that this Block should drop when harvested.
     *
     * @param fortune the level of the Fortune enchantment on the player's tool
     */
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return itemSuppler.get().getItem();
    }

    /**
     * Get the quantity dropped based on the given fortune level
     */
    @Override
    public int quantityDroppedWithBonus(int fortune, Random random) {
        return quantityDropped(random) + random.nextInt(fortune + 1);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    @Override
    public int quantityDropped(Random random) {
        return itemQuantity + random.nextInt(itemVariation);
    }

    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
        if (getItemDropped(world.getBlockState(pos), RANDOM, fortune) != Item.getItemFromBlock(this)) {
            return 1 + RANDOM.nextInt(5);
        }
        return 0;
    }

}
