package dev.jeryn.angels.common.blockentity;

import dev.jeryn.angels.WeepingAngels;
import dev.jeryn.angels.common.blocks.WABlocks;
import dev.jeryn.angels.registry.DeferredRegistry;
import dev.jeryn.angels.registry.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class WABlockEntities {

    public static final DeferredRegistry<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegistry.create(WeepingAngels.MODID, Registry.BLOCK_ENTITY_TYPE.key());

    public static final RegistrySupplier<BlockEntityType<CoffinBlockEntity>> COFFIN = BLOCK_ENTITY_TYPES.register("coffin", () -> BlockEntityType.Builder.of(CoffinBlockEntity::new, WABlocks.COFFIN.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<StatueBlockEntity>> STATUE = BLOCK_ENTITY_TYPES.register("statue", () -> BlockEntityType.Builder.of(StatueBlockEntity::new, WABlocks.STATUE.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<GeneratorBlockEntity>> GENERATOR = BLOCK_ENTITY_TYPES.register("generator", () -> BlockEntityType.Builder.of(GeneratorBlockEntity::new, WABlocks.CHRONODYNE_GENERATOR.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<SnowAngelBlockEntity>> SNOW_ANGEL = BLOCK_ENTITY_TYPES.register("snow_angel", () -> BlockEntityType.Builder.of(SnowAngelBlockEntity::new, WABlocks.SNOW_ANGEL.get()).build(null));


}
