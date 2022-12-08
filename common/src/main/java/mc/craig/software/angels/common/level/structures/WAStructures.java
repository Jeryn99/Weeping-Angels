package mc.craig.software.angels.common.level.structures;

import com.mojang.serialization.Codec;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.registry.DeferredRegistry;
import mc.craig.software.angels.registry.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;

public class WAStructures {

    public static final DeferredRegistry<StructureType<?>> STRUCTURES = DeferredRegistry.create(WeepingAngels.MODID, Registries.STRUCTURE_TYPE);
    public static final RegistrySupplier<StructureType<?>> CATACOMB = STRUCTURES.register("catacombs", () -> typeConvert(CatacombStructure.CODEC));


    private static <S extends Structure> StructureType<S> typeConvert(Codec<S> codec) {
        return () -> codec;
    }


}