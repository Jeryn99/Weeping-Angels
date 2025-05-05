package mc.jeryn.dev.statues.common;

import com.mojang.serialization.MapCodec;
import mc.jeryn.dev.statues.WeepingAngels;
import mc.jeryn.dev.statues.common.blocks.CoffinBlock;
import mc.jeryn.dev.statues.common.blocks.StatueBaseBlock;
import mc.jeryn.dev.statues.registry.DeferredRegister;
import mc.jeryn.dev.statues.registry.RegistryHolder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;

public class WACodecs {

    public static final DeferredRegister<MapCodec<? extends Block>> BLOCK_TYPE = DeferredRegister.create(WeepingAngels.MODID, Registries.BLOCK_TYPE);


    public static final RegistryHolder<MapCodec<? extends Block>, MapCodec<CoffinBlock>> COFFIN = BLOCK_TYPE.register("coffin", () -> CoffinBlock.CODEC);
    public static final RegistryHolder<MapCodec<? extends Block>, MapCodec<StatueBaseBlock>> STATUE = BLOCK_TYPE.register("statue", () -> StatueBaseBlock.CODEC);



}
