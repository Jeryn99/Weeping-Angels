package mc.craig.software.angels.fabric;

import mc.craig.software.angels.EntitySpawns;
import mc.craig.software.angels.WAConfiguration;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.blockentity.GeneratorBlockEntity;
import mc.craig.software.angels.common.blocks.GeneratorBlock;
import mc.craig.software.angels.common.entity.angel.AbstractWeepingAngel;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import mc.craig.software.angels.common.entity.angel.ai.AngelVariant;
import mc.craig.software.angels.util.Platform;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
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
        ModLoadingContext.registerConfig(WeepingAngels.MODID, ModConfig.Type.CLIENT, WAConfiguration.CLIENT_SPEC);
        ModLoadingContext.registerConfig(WeepingAngels.MODID, ModConfig.Type.COMMON, WAConfiguration.SPAWNS_SPEC, "weeping-angels-spawns");
        WeepingAngels.init();
        EntitySpawns.init();
        levelManipulation();
        Platform.init();

        entityAttributes();

        AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {

            if (world.getBlockEntity(pos) instanceof GeneratorBlockEntity) {
                if (!GeneratorBlock.isBreakable(world, pos)) {
                    return InteractionResult.FAIL;
                }
            }
            return InteractionResult.PASS;
        });

        ServerLifecycleEvents.SERVER_STARTED.register(server -> AngelVariant.init());
    }

    private void entityAttributes() {
        FabricDefaultAttributeRegistry.register(WEEPING_ANGEL.get(), AbstractWeepingAngel.createAttributes());
    }

    private void levelManipulation() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(WeepingAngels.MODID, "ore_kontron")));
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(WeepingAngels.MODID, "ore_kontron_small")));
        BiomeModifications.addFeature(isSnowy(), GenerationStep.Decoration.RAW_GENERATION, ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(WeepingAngels.MODID, "snow_angel")));
    }

    private Predicate<BiomeSelectionContext> isSnowy() {
        return biomeSelectionContext -> biomeSelectionContext.getBiome().getPrecipitation() == Biome.Precipitation.SNOW;
    }

}
