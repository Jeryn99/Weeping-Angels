package mc.craig.software.angels.common.entity.angel.forge;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.util.WATags;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.EndPortalBlock;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.Map;

import static mc.craig.software.angels.common.entity.angel.BlockReactions.*;
import static net.minecraft.world.level.block.state.properties.BlockStateProperties.POWERED;

public class BlockReactionsImpl {
    public static void init() {
        for (Map.Entry<ResourceKey<Block>, Block> entry : BuiltInRegistries.BLOCK.entrySet()) {
            BlockState blockState = entry.getValue().defaultBlockState();
            Block block = entry.getValue();
            if (!block.defaultBlockState().is(WATags.NO_BREAKING) && !block.defaultBlockState().liquid()) {

                // Destroy Lights
                if (blockState.getLightEmission() > 0 && !(blockState.getBlock() instanceof NetherPortalBlock) && !(blockState.getBlock() instanceof EndPortalBlock)) {
                    registerBehavior(entry.getValue(), BREAK_BLOCKS);
                    WeepingAngels.LOGGER.debug("{} was registered as a breaking block", BuiltInRegistries.BLOCK.getKey(entry.getValue()));
                }

                // Toggle Lights
                if (blockState.hasProperty(BlockStateProperties.LIT)) {
                    boolean shouldSkip = entry.getValue() instanceof CandleBlock;
                    if (!shouldSkip) {
                        registerBehavior(entry.getValue(), TOGGLE_LIGHTS);
                        WeepingAngels.LOGGER.debug("{} was registered as a light toggle block", BuiltInRegistries.BLOCK.getKey(entry.getValue()));
                    }
                }

                // Toggle Power
                if (blockState.hasProperty(POWERED)) {
                    registerBehavior(entry.getValue(), TOGGLE_POWER);
                    WeepingAngels.LOGGER.debug("{} was registered as a power toggle block", BuiltInRegistries.BLOCK.getKey(entry.getValue()));
                }

                // Candles
                if (block instanceof CandleBlock candleBlock) {
                    registerBehavior(candleBlock, BLOWOUT_CANDLES);
                    WeepingAngels.LOGGER.debug("{} was registered as a candle block", BuiltInRegistries.BLOCK.getKey(entry.getValue()));
                }
            }
        }
    }

}
