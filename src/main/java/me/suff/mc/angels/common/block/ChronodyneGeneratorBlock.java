package me.suff.mc.angels.common.block;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.entity.PortalEntity;
import me.suff.mc.angels.common.entity.WeepingAngelEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class ChronodyneGeneratorBlock extends Block {

    public ChronodyneGeneratorBlock() {
        super(Settings.of(Material.STONE).nonOpaque().sounds(BlockSoundGroup.STONE).requiresTool());
    }


    @Override
    public int getOpacity(BlockState state, BlockView world, BlockPos pos) {
        return 0;
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, Entity entity) {
        super.onSteppedOn(world, pos, entity);
        if (entity instanceof WeepingAngelEntity) {
            portal((ServerWorld) world, pos);
        }
    }

    private void portal(ServerWorld world, BlockPos pos) {
        PortalEntity portalEntity = WeepingAngels.PORTAL.spawn(world, null, null, null, pos.up(), SpawnReason.EVENT, false, false);
        world.removeBlock(pos, false);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        super.neighborUpdate(state, world, pos, block, fromPos, notify);
        if (world.isReceivingRedstonePower(pos) && !world.isClient) {
            portal((ServerWorld) world, pos);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            if (hand == Hand.MAIN_HAND) {
                portal((ServerWorld) world, pos);
            }
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

}