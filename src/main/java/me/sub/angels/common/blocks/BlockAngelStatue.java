package me.sub.angels.common.blocks;

import me.sub.angels.client.models.poses.PoseManager;
import me.sub.angels.common.WAObjects;
import me.sub.angels.common.tileentities.TileEntityPlinth;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class BlockAngelStatue extends Block implements BlockEntityProvider
{

    public BlockAngelStatue() {
		super(Settings.of(Material.UNDERWATER_PLANT));
	}

	@Override public boolean isTranslucent(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1)
	{
		return true;
	}

	@Override public void onPlaced(World world_1, BlockPos blockPos_1, BlockState blockState_1, LivingEntity livingEntity_1, ItemStack itemStack_1)
	{
		super.onPlaced(world_1, blockPos_1, blockState_1, livingEntity_1, itemStack_1);
		if (world_1.getBlockEntity(blockPos_1) instanceof TileEntityPlinth) {
			int rotation = MathHelper.floor(livingEntity_1.yaw + 180);
			TileEntityPlinth plinth = (TileEntityPlinth) world_1.getBlockEntity(blockPos_1);
			plinth.setRotation(rotation);
			plinth.setPose(getRandomPose(world_1).toString());
			plinth.sendUpdates();
		}
	}


    public PoseManager.AngelPoses getRandomPose(World world) {
		return PoseManager.AngelPoses.values()[world.random.nextInt(PoseManager.AngelPoses.values().length)];
	}

	@Override public BlockEntity createBlockEntity(BlockView blockView)
	{
		return new TileEntityPlinth(WAObjects.BlockEntities.PLINTH);
	}
}
