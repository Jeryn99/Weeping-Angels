package me.swirtzly.minecraft.angels.common.world.structures;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import me.swirtzly.minecraft.angels.WeepingAngels;
import me.swirtzly.minecraft.angels.common.WAObjects;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Collections;
import java.util.List;

public class CatacombStructure extends Structure< NoFeatureConfig > {

    protected static final String[] variants = new String[]{"flat", "clean", "broken", "normal"};
    private static final List< MobSpawnInfo.Spawners > STRUCTURE_CREATURES = ImmutableList.of(
            new MobSpawnInfo.Spawners(EntityType.BAT, 100, 1, 7),
            new MobSpawnInfo.Spawners(EntityType.SPIDER, 100, 1, 2),
            new MobSpawnInfo.Spawners(WAObjects.EntityEntries.WEEPING_ANGEL.get(), 20, 1, 3));

    public CatacombStructure(Codec< NoFeatureConfig > codec) {
        super(codec);
    }

    @Override
    public IStartFactory< NoFeatureConfig > getStartFactory() {
        return CatacombStructure.Start::new;
    }

    @Override
    public GenerationStage.Decoration getDecorationStage() {
        return GenerationStage.Decoration.UNDERGROUND_STRUCTURES;
    }

    @Override
    public List< MobSpawnInfo.Spawners > getDefaultSpawnList() {
        return Collections.emptyList();
    }

    @Override
    public List< MobSpawnInfo.Spawners > getDefaultCreatureSpawnList() {
        return STRUCTURE_CREATURES;
    }


    //Notes: Anything that uses the mods "Rotation" system for placement gets cooked in the rotations of the structure
    public static class Start extends StructureStart< NoFeatureConfig > {
        public Start(Structure< NoFeatureConfig > structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override
        public void func_230364_a_(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn, NoFeatureConfig config) {

            int x = (chunkX << 4) + 7;
            int z = (chunkZ << 4) + 7;
            BlockPos blockpos = new BlockPos(x, MathHelper.clamp(rand.nextInt(45), 30, 55), z);

            String choosen = variants[rand.nextInt(variants.length)];
            System.out.println(choosen);

            JigsawManager.func_242837_a(
                    dynamicRegistryManager,
                    new VillageConfig(() -> dynamicRegistryManager.getRegistry(Registry.JIGSAW_POOL_KEY).getOrDefault(new ResourceLocation(WeepingAngels.MODID, "catacombs/" + choosen + "/catacomb")), 9),
                    AbstractVillagePiece::new,
                    chunkGenerator,
                    templateManagerIn,
                    blockpos, this.components, this.rand, false, false);

            this.recalculateStructureSize();

        }
    }


}