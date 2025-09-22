package dev.jeryn.angels.common.entity.angel.fabric;

import dev.jeryn.angels.WeepingAngels;
import dev.jeryn.angels.util.WATags;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.EndPortalBlock;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import static dev.jeryn.angels.common.entity.angel.BlockReactions.*;
import static net.minecraft.world.level.block.state.properties.BlockStateProperties.POWERED;

public class BlockReactionsImpl {

    public static void init() {
        for (Block block : BuiltInRegistries.BLOCK) {
            BlockState blockState = block.defaultBlockState();
            WeepingAngels.LOGGER.debug(blockState.toString());

            if (!block.defaultBlockState().is(WATags.NO_BREAKING) && !block.defaultBlockState().liquid()) {

                // Destroy Lights
                if (blockState.getLightEmission() > 0 && !(blockState.getBlock() instanceof NetherPortalBlock) && !(blockState.getBlock() instanceof EndPortalBlock)) {
                    registerBehavior(block, BREAK_BLOCKS);
                    WeepingAngels.LOGGER.debug("{} was registered as a breaking block", BuiltInRegistries.BLOCK.getKey(block));
                }

                // Toggle Lights
                if (blockState.hasProperty(BlockStateProperties.LIT)) {
                    boolean shouldSkip = block instanceof CandleBlock;
                    if (!shouldSkip) {
                        registerBehavior(block, TOGGLE_LIGHTS);
                        WeepingAngels.LOGGER.debug("{} was registered as a light toggle block", BuiltInRegistries.BLOCK.getKey(block));
                    }
                }

                // Toggle Power
                if (blockState.hasProperty(POWERED)) {
                    registerBehavior(block, TOGGLE_POWER);
                    WeepingAngels.LOGGER.debug("{} was registered as a power toggle block", BuiltInRegistries.BLOCK.getKey(block));
                }

                // Candles
                if (block instanceof CandleBlock candleBlock) {
                    registerBehavior(candleBlock, BLOWOUT_CANDLES);
                    WeepingAngels.LOGGER.debug("{} was registered as a candle block", BuiltInRegistries.BLOCK.getKey(block));
                }
            }
        }
    }

}
