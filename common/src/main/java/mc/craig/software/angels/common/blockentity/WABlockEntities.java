package mc.craig.software.angels.common.blockentity;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.blocks.WABlocks;
import mc.craig.software.angels.registry.DeferredRegister;
import mc.craig.software.angels.registry.RegistryHolder;
import mc.craig.software.angels.registry.RegistryHolder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class WABlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(WeepingAngels.MODID, Registries.BLOCK_ENTITY_TYPE);

    public static final RegistryHolder<BlockEntityType<?>, BlockEntityType<CoffinBlockEntity>> COFFIN = BLOCK_ENTITY_TYPES.register("coffin", () -> BlockEntityType.Builder.of(CoffinBlockEntity::new, WABlocks.COFFIN.get()).build(null));
    public static final RegistryHolder<BlockEntityType<?>, BlockEntityType<StatueBlockEntity>> STATUE = BLOCK_ENTITY_TYPES.register("statue", () -> BlockEntityType.Builder.of(StatueBlockEntity::new, WABlocks.STATUE.get()).build(null));
    public static final RegistryHolder<BlockEntityType<?>, BlockEntityType<GeneratorBlockEntity>> GENERATOR = BLOCK_ENTITY_TYPES.register("generator", () -> BlockEntityType.Builder.of(GeneratorBlockEntity::new, WABlocks.CHRONODYNE_GENERATOR.get()).build(null));
    public static final RegistryHolder<BlockEntityType<?>, BlockEntityType<SnowAngelBlockEntity>> SNOW_ANGEL = BLOCK_ENTITY_TYPES.register("snow_angel", () -> BlockEntityType.Builder.of(SnowAngelBlockEntity::new, WABlocks.SNOW_ANGEL.get()).build(null));


}
