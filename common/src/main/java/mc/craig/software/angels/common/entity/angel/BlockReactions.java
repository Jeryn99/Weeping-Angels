package mc.craig.software.angels.common.entity.angel;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.WASounds;
import mc.craig.software.angels.util.WATags;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.POWERED;

public class BlockReactions {

    public static BlockReaction TOGGLE_LIGHTS = (weepingAngel, blockState, level, blockPos) -> {
        if (blockState.hasProperty(BlockStateProperties.LIT)) {
            if (level.getBlockState(blockPos).getValue(BlockStateProperties.LIT)) {
                level.setBlock(blockPos, blockState.setValue(BlockStateProperties.LIT, false), 16);
                return true;
            }
        }
        return false;
    };

    public static BlockReaction TOGGLE_POWER = (weepingAngel, blockState, level, blockPos) -> {
        if (blockState.hasProperty(POWERED)) {
            BlockState newState = blockState.cycle(POWERED);
            // Lever
            if (newState.getBlock() instanceof LeverBlock leverBlock && level.random.nextBoolean()) {
                leverBlock.pull(blockState, level, blockPos);
                return true;
            }
        }
        return false;
    };

    public static BlockReaction BREAK_BLOCKS = (weepingAngel, blockState, level, blockPos) -> {
        if (blockState.getLightEmission(level, blockPos) > 0 && blockState.getBlock().getExplosionResistance() < Blocks.STONE.getExplosionResistance()) {
            if (level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, blockState), blockPos.getX(), blockPos.getY(), blockPos.getZ(), 0, 0, 0, 0, 0);
                serverLevel.playSound(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), blockState.getBlock().getSoundType(blockState, level, blockPos, weepingAngel).getBreakSound(), SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            Containers.dropItemStack(weepingAngel.level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), new ItemStack(blockState.getBlock()));
            level.removeBlock(blockPos, true);
            return true;
        }
        return false;
    };

    public static BlockReaction BLOWOUT_CANDLES = (weepingAngel, blockState, level, blockPos) -> {
        if (blockState.getBlock() instanceof CandleBlock) {
            weepingAngel.playSound(WASounds.BLOW.get());
            AbstractCandleBlock.extinguish(null, blockState, level, blockPos);
            return true;
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

    public static void init() {
        for (Map.Entry<ResourceKey<Block>, Block> entry : ForgeRegistries.BLOCKS.getEntries()) {
            BlockState blockState = entry.getValue().defaultBlockState();
            Block block = entry.getValue();
            if (!block.defaultBlockState().is(WATags.NO_BREAKING)) {

                // Destroy Lights
                if (blockState.getLightEmission() > 0 && !(blockState.getBlock() instanceof NetherPortalBlock) && !(blockState.getBlock() instanceof EndPortalBlock)) {
                    registerBehavior(entry.getValue(), BREAK_BLOCKS);
                    WeepingAngels.LOGGER.debug("{} was registered as a breaking block", ForgeRegistries.BLOCKS.getKey(entry.getValue()));
                }

                // Toggle Lights
                if (blockState.hasProperty(BlockStateProperties.LIT)) {
                    boolean shouldSkip = entry.getValue() instanceof CandleBlock;
                    if (!shouldSkip) {
                        registerBehavior(entry.getValue(), TOGGLE_LIGHTS);
                        WeepingAngels.LOGGER.debug("{} was registered as a light toggle block", ForgeRegistries.BLOCKS.getKey(entry.getValue()));
                    }
                }

                // Toggle Power
                if (blockState.hasProperty(POWERED)) {
                    registerBehavior(entry.getValue(), TOGGLE_POWER);
                    WeepingAngels.LOGGER.debug("{} was registered as a power toggle block", ForgeRegistries.BLOCKS.getKey(entry.getValue()));
                }

                // Candles
                if (block instanceof CandleBlock candleBlock) {
                    registerBehavior(candleBlock, BLOWOUT_CANDLES);
                    WeepingAngels.LOGGER.debug("{} was registered as a candle block", ForgeRegistries.BLOCKS.getKey(entry.getValue()));
                }
            }
        }
    }

    public interface BlockReaction {
        boolean interact(WeepingAngel weepingAngel, BlockState blockState, Level level, BlockPos blockPos);
    }

}
