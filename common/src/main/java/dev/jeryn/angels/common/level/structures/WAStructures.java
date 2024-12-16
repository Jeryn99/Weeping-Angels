package dev.jeryn.angels.common.level.structures;

import com.mojang.serialization.Codec;
import dev.jeryn.angels.WeepingAngels;
import dev.jeryn.angels.registry.DeferredRegistry;
import dev.jeryn.angels.registry.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;

public class WAStructures {

    public static final DeferredRegistry<StructureType<?>> STRUCTURES = DeferredRegistry.create(WeepingAngels.MODID, Registry.STRUCTURE_TYPE_REGISTRY);
    public static final RegistrySupplier<StructureType<?>> CATACOMB = STRUCTURES.register("catacombs", () -> typeConvert(CatacombStructure.CODEC));


    private static <S extends Structure> StructureType<S> typeConvert(Codec<S> codec) {
        return () -> codec;
    }


}