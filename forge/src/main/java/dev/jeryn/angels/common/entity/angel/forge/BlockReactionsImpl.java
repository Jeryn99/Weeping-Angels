package dev.jeryn.angels.common.entity.angel.forge;

import dev.jeryn.angels.WeepingAngels;
import dev.jeryn.angels.util.WATags;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.EndPortalBlock;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;

import static dev.jeryn.angels.common.entity.angel.BlockReactions.*;
import static net.minecraft.world.level.block.state.properties.BlockStateProperties.POWERED;

public class BlockReactionsImpl {
    public static void init() {
        for (Map.Entry<ResourceKey<Block>, Block> entry : ForgeRegistries.BLOCKS.getEntries()) {
            BlockState blockState = entry.getValue().defaultBlockState();
            Block block = entry.getValue();
            if (!block.defaultBlockState().is(WATags.NO_BREAKING) && !block.defaultBlockState().getMaterial().isLiquid()) {

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

}
