package mc.craig.software.angels.common.entity.angel;

import dev.architectury.injectables.annotations.ExpectPlatform;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import mc.craig.software.angels.common.WASounds;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Map;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.POWERED;

public class BlockReactions {

    public static BlockReaction TOGGLE_LIGHTS = (weepingAngel, blockState, level, blockPos) -> {
        if (blockState.hasProperty(BlockStateProperties.LIT)) {
            if (level.getBlockState(blockPos).getValue(BlockStateProperties.LIT)) {
                level.setBlock(blockPos, blockState.setValue(BlockStateProperties.LIT, false), 16);
                level.gameEvent(null, GameEvent.BLOCK_CHANGE, blockPos);
                return true;
            }
        }
        return false;
    };

    public static BlockReaction TOGGLE_POWER = (weepingAngel, blockState, level, blockPos) -> {
        if (blockState.hasProperty(POWERED)) {
            // Lever
            if (blockState.getBlock() instanceof LeverBlock leverBlock && level.random.nextBoolean()) {
                leverBlock.pull(blockState, level, blockPos);
                level.gameEvent(null, GameEvent.BLOCK_CHANGE, blockPos);
                return true;
            }
        }
        return false;
    };

    public static BlockReaction BREAK_BLOCKS = (weepingAngel, blockState, level, blockPos) -> {
        if (blockState.getLightBlock(level, blockPos) > 0 && blockState.getBlock().getExplosionResistance() < Blocks.STONE.getExplosionResistance()) {
            if (level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, blockState), blockPos.getX(), blockPos.getY(), blockPos.getZ(), 0, 0, 0, 0, 0);
                serverLevel.playSound(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), blockState.getBlock().getSoundType(blockState).getBreakSound(), SoundSource.BLOCKS, 1.0F, 1.0F);
                Containers.dropItemStack(weepingAngel.level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), new ItemStack(blockState.getBlock()));
                level.removeBlock(blockPos, true);
                level.gameEvent(null, GameEvent.BLOCK_CHANGE, blockPos);
            }
            return true;
        }
        return false;
    };

    public static BlockReaction BLOWOUT_CANDLES = (weepingAngel, blockState, level, blockPos) -> {
        if (blockState.getBlock() instanceof CandleBlock) {
            if (blockState.getValue(BlockStateProperties.LIT)) {
                weepingAngel.playSound(WASounds.BLOW.get(), 1F, 1F);
                AbstractCandleBlock.extinguish(null, blockState, level, blockPos);
                return true;
            }
        }
        return false;
    };

    public static final Map<Block, BlockReaction> BLOCK_BEHAVIOUR = Util.make(new Object2ObjectOpenHashMap<>(), (objectOpenHashMap) -> {
        objectOpenHashMap.defaultReturnValue((weepingAngel, blockState, level, blockPos) -> {
            // DO NOTHING
            return false;
        });
    });

    public static void registerBehavior(Block block, BlockReaction pBehavior) {
        if(BLOCK_BEHAVIOUR.containsKey(block)){
            BLOCK_BEHAVIOUR.replace(block, pBehavior);
        }
        BLOCK_BEHAVIOUR.put(block, pBehavior);
    }

    @ExpectPlatform
    public static void init() {
        throw new RuntimeException("Wrong platform!");
    }

    public interface BlockReaction {
        boolean interact(WeepingAngel weepingAngel, BlockState blockState, Level level, BlockPos blockPos);
    }

}
