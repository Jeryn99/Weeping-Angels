package me.suff.mc.angels.common.block;

import me.suff.mc.angels.common.blockentity.CoffinTile;
import me.suff.mc.angels.util.AngelUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/* Created by Craig on 19/02/2021 */
public class CoffinBlock extends StatueBlock {

    public static final BooleanProperty UPRIGHT = BooleanProperty.of("upright");


    public CoffinBlock(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockView world) {
        return new CoffinTile();
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    protected void appendProperties(StateManager.Builder< Block, BlockState > builder) {
        super.appendProperties(builder);
        builder.add(UPRIGHT);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        BlockState state = super.getPlacementState(context);
        return state.with(ROTATION, MathHelper.floor((double) (context.getPlayerYaw() * 16.0F / 360.0F) + 0.5D) & 15).with(UPRIGHT, context.getPlayer().isSneaking());
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient && hand == Hand.MAIN_HAND) {
            CoffinTile coffinTile = (CoffinTile) world.getBlockEntity(pos);
            if (coffinTile != null) {
                if (!coffinTile.getCoffin().name().contains("PTB")) {
                    coffinTile.setOpen(!coffinTile.isOpen());
                    world.playSound(null, pos.getX() + 0.5D, (double) pos.getY() + 0.5D, pos.getZ() + 0.5D, coffinTile.isOpen() ? SoundEvents.BLOCK_ENDER_CHEST_OPEN : SoundEvents.BLOCK_CHEST_CLOSE, SoundCategory.BLOCKS, 0.5F, world.random.nextFloat() * 0.1F + 0.9F);
                    coffinTile.sendUpdates();
                } else {
                    if (player.getMainHandStack().getItem() instanceof MusicDiscItem) {
                        if (!coffinTile.isDoingSomething()) {
                            coffinTile.setDoingSomething(true);
                            if (!player.isCreative()) {
                                player.getMainHandStack().decrement(1);
                            }
                        }
                    }
                }
                coffinTile.sendUpdates();
            }
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        if (world.getBlockEntity(pos) instanceof CoffinTile) {
            CoffinTile blockEntity = (CoffinTile) world.getBlockEntity(pos);
            blockEntity.setCoffin(AngelUtils.randomCoffin());
            blockEntity.markDirty();
        }
    }
}
