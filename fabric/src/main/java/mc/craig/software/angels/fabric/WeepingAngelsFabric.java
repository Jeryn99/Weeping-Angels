package mc.craig.software.angels.fabric;

import mc.craig.software.angels.EntitySpawns;
import mc.craig.software.angels.WAConfiguration;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.WAEntities;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.api.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

import static mc.craig.software.angels.common.WAEntities.WEEPING_ANGEL;

public class WeepingAngelsFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ModLoadingContext.registerConfig(WeepingAngels.MODID, ModConfig.Type.COMMON, WAConfiguration.CONFIG_SPEC);
        WeepingAngels.init();
        FabricDefaultAttributeRegistry.register(WEEPING_ANGEL.get(), WeepingAngel.createAttributes());
        FabricDefaultAttributeRegistry.register(WAEntities.ANOMALY.get(), Mob.createMobAttributes());
        EntitySpawns.init();
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, new ResourceLocation(WeepingAngels.MODID, "ore_kontron")));
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, new ResourceLocation(WeepingAngels.MODID, "ore_kontron_small")));
        LootModifying.init();
    }

}
