package mc.craig.software.angels.common;

import com.mojang.serialization.MapCodec;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.blockentity.GeneratorBlockEntity;
import mc.craig.software.angels.common.blocks.CoffinBlock;
import mc.craig.software.angels.common.blocks.StatueBaseBlock;
import mc.craig.software.angels.common.blocks.WABlocks;
import mc.craig.software.angels.registry.DeferredRegister;
import mc.craig.software.angels.registry.RegistryHolder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BlockTypes;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class WACodecs {

    public static final DeferredRegister<MapCodec<? extends Block>> BLOCK_TYPE = DeferredRegister.create(WeepingAngels.MODID, Registries.BLOCK_TYPE);


    public static final RegistryHolder<MapCodec<? extends Block>, MapCodec<CoffinBlock>> COFFIN = BLOCK_TYPE.register("coffin", () -> CoffinBlock.CODEC);
    public static final RegistryHolder<MapCodec<? extends Block>, MapCodec<StatueBaseBlock>> STATUE = BLOCK_TYPE.register("statue", () -> StatueBaseBlock.CODEC);



}
