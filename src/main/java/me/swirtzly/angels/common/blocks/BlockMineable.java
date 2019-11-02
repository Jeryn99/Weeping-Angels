package me.swirtzly.angels.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;
import java.util.function.Supplier;

public class BlockMineable extends Block {
	
	private Supplier<ItemStack> itemSuppler;
	private int itemQuantity, itemVariation;
	
	public BlockMineable(Supplier<ItemStack> stackSupplier, int quantity, int variation) {
		super(Properties.create(Material.ROCK).hardnessAndResistance(3).sound(SoundType.STONE));
		this.itemSuppler = stackSupplier;
		itemQuantity = quantity;
		itemVariation = variation;
	}

/*
	@Override
	public IItemProvider getItemDropped(BlockState state, World worldIn, BlockPos pos, int fortune) {
		return itemSuppler.get().getItem();
	}

	@Override
	public int quantityDropped(BlockState state, Random random) {
		return itemQuantity + random.nextInt(itemVariation);
	}
*/
@Override
	public void dropXpOnBlockBreak(World worldIn, BlockPos pos, int amount) {
		super.dropXpOnBlockBreak(worldIn, pos, 1 + RANDOM.nextInt(5));
	}
	
	
}
