package mc.jeryn.dev.statues.fabric;

import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
import mc.jeryn.dev.statues.EntitySpawns;
import mc.jeryn.dev.statues.WAConfiguration;
import mc.jeryn.dev.statues.WeepingAngels;
import mc.jeryn.dev.statues.common.WAConstants;
import mc.jeryn.dev.statues.common.blockentity.GeneratorBlockEntity;
import mc.jeryn.dev.statues.common.blocks.GeneratorBlock;
import mc.jeryn.dev.statues.common.entity.angel.AbstractWeepingAngel;
import mc.jeryn.dev.statues.common.entity.angel.ai.AngelVariant;
import mc.jeryn.dev.statues.common.items.WAItems;
import mc.jeryn.dev.statues.util.Platform;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.fml.config.ModConfig;

import java.util.function.Predicate;

import static mc.jeryn.dev.statues.common.WAEntities.WEEPING_ANGEL;

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
        NeoForgeConfigRegistry.INSTANCE.register(WeepingAngels.MODID, ModConfig.Type.COMMON, WAConfiguration.CONFIG_SPEC);
        NeoForgeConfigRegistry.INSTANCE.register(WeepingAngels.MODID, ModConfig.Type.CLIENT, WAConfiguration.CLIENT_SPEC);
        NeoForgeConfigRegistry.INSTANCE.register(WeepingAngels.MODID, ModConfig.Type.COMMON, WAConfiguration.SPAWNS_SPEC, "weeping-angels-spawns");
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
        BiomeModifications.addFeature(isSnowy(), GenerationStep.Decoration.RAW_GENERATION, ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.tryBuild(WeepingAngels.MODID, "snow_angel")));
    }

    private Predicate<BiomeSelectionContext> isSnowy() {
        return biomeSelectionContext -> biomeSelectionContext.getBiomeKey().location().getPath().contains("snow");
    }

}
