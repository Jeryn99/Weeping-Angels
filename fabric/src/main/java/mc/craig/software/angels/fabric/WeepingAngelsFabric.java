package mc.craig.software.angels.fabric;

import mc.craig.software.angels.EntitySpawns;
import mc.craig.software.angels.WAConfiguration;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.blockentity.GeneratorBlockEntity;
import mc.craig.software.angels.common.blocks.GeneratorBlock;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.api.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

import java.util.function.Predicate;

import static mc.craig.software.angels.common.WAEntities.WEEPING_ANGEL;

public class WeepingAngelsFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ModLoadingContext.registerConfig(WeepingAngels.MODID, ModConfig.Type.COMMON, WAConfiguration.CONFIG_SPEC);
        WeepingAngels.init();
        FabricDefaultAttributeRegistry.register(WEEPING_ANGEL.get(), WeepingAngel.createAttributes());
        EntitySpawns.init();
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, new ResourceLocation(WeepingAngels.MODID, "ore_kontron")));
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, new ResourceLocation(WeepingAngels.MODID, "ore_kontron_small")));
        BiomeModifications.addFeature(isSnowy(), GenerationStep.Decoration.RAW_GENERATION, ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, new ResourceLocation(WeepingAngels.MODID, "snow_angel")));
        LootModifying.init();

        AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {

            if (world.getBlockEntity(pos) instanceof GeneratorBlockEntity) {
                if (!GeneratorBlock.isBreakable(world, pos)) {
                    return InteractionResult.FAIL;
                }
            }
            return InteractionResult.PASS;
        });
    }

    private Predicate<BiomeSelectionContext> isSnowy() {
        return biomeSelectionContext -> biomeSelectionContext.getBiome().getPrecipitation() == Biome.Precipitation.SNOW;
    }

}
