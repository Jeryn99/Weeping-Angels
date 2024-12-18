package dev.jeryn.angels.fabric;

import dev.jeryn.angels.FabricSpawnHelper;
import dev.jeryn.angels.WAEntitySpawns;
import dev.jeryn.angels.WeepingAngels;
import dev.jeryn.angels.common.WAConstants;
import dev.jeryn.angels.common.blockentity.GeneratorBlockEntity;
import dev.jeryn.angels.common.blocks.GeneratorBlock;
import dev.jeryn.angels.common.entity.angel.AbstractWeepingAngel;
import dev.jeryn.angels.common.entity.angel.ai.AngelVariant;
import dev.jeryn.angels.common.items.WAItems;
import dev.jeryn.angels.util.Platform;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.fml.config.ModConfig;

import java.util.function.Predicate;

import static dev.jeryn.angels.common.WAEntities.WEEPING_ANGEL;

public class WeepingAngelsFabric implements ModInitializer {


    public static final CreativeModeTab ITEM_GROUP = FabricItemGroup.builder().icon(() -> new ItemStack(WAItems.ANGEL_SPAWNER.get())).displayItems((enabledFeatures, entries) -> {

        BuiltInRegistries.ITEM.iterator().forEachRemaining(item -> {
            if (BuiltInRegistries.ITEM.getKey(item).getNamespace().matches(WeepingAngels.MODID)) {
                entries.accept(item);
            }
        });
    }).title(Component.translatable(WAConstants.CREATIVE_TAB)).build();

    @Override
    public void onInitialize() {

        Platform.init();
        ForgeConfigRegistry.INSTANCE.register(WeepingAngels.MODID, ModConfig.Type.COMMON, WAConfiguration.CONFIG_SPEC);
        ForgeConfigRegistry.INSTANCE.register(WeepingAngels.MODID, ModConfig.Type.CLIENT, WAConfiguration.CLIENT_SPEC);
        WeepingAngels.init();
        levelManipulation();

        entityAttributes();



        AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {

            if (world.getBlockEntity(pos) instanceof GeneratorBlockEntity) {
                if (!GeneratorBlock.isBreakable(world, pos)) {
                    return InteractionResult.FAIL;
                }
            }
            return InteractionResult.PASS;
        });

        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            AngelVariant.init();
            WAEntitySpawns.init(server);
            FabricSpawnHelper.init();
        });

        spawns();
    }


    private void spawns() {
        SpawnPlacements.register(WAEntities.WEEPING_ANGEL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
    }

    private void entityAttributes() {
        FabricDefaultAttributeRegistry.register(WEEPING_ANGEL.get(), AbstractWeepingAngel.createAttributes());
    }

    private void levelManipulation() {
        BiomeModifications.addFeature(isSnowy(), GenerationStep.Decoration.RAW_GENERATION, ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(WeepingAngels.MODID, "snow_angel")));
    }

    private Predicate<BiomeSelectionContext> isSnowy() {
        return biomeSelectionContext -> biomeSelectionContext.getBiomeKey().location().getPath().contains("snow");
    }

}
