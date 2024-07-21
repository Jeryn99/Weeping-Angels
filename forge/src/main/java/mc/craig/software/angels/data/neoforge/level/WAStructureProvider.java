package mc.craig.software.angels.data.neoforge.level;

import mc.craig.software.angels.common.level.structures.CatacombStructure;
import mc.craig.software.angels.util.WATags;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.NoiseThresholdCountPlacement;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;

import java.util.List;

public class WAStructureProvider {

    public static void bootstrap(BootstrapContext<Structure> structureTypeBootstrapContext) {
     /*   Structure.StructureSettings structureSettings = new Structure.StructureSettings(
                HolderSet.direct(WATags.CATACOMB_STRUCTURE_BIOMES),
                null,
                GenerationStep.Decoration.UNDERGROUND_STRUCTURES,
                TerrainAdjustment.NONE);

        structureTypeBootstrapContext.register(Registries.STRUCTURE, new CatacombStructure(
                structureSettings, ));*/


    }
}
