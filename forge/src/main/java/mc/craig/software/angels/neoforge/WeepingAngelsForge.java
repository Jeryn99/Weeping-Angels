package mc.craig.software.angels.neoforge;

import mc.craig.software.angels.WAConfiguration;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.WAEntities;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import mc.craig.software.angels.common.entity.angel.ai.AngelVariant;
import mc.craig.software.angels.data.neoforge.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.progress.StartupNotificationManager;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

@Mod(WeepingAngels.MODID)
public class WeepingAngelsForge {


    public WeepingAngelsForge(ModContainer modContainer) {
        WeepingAngels.init();
        modContainer.registerConfig(ModConfig.Type.COMMON, WAConfiguration.CONFIG_SPEC);
        modContainer.registerConfig(ModConfig.Type.COMMON, WAConfiguration.SPAWNS_SPEC, "weeping-angels-spawns.toml");
        modContainer.registerConfig(ModConfig.Type.CLIENT, WAConfiguration.CLIENT_SPEC);

        IEventBus modEventBus = modContainer.getEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::onAttributeAssign);
        modEventBus.addListener(this::onGatherData);
        modEventBus.addListener(this::spawns);

        StartupNotificationManager.addModMessage("Don't Blink!");

    }

    public static Optional<IEventBus> getModEventBus(String modId) {
        return ModList.get().getModContainerById(modId)
                .map(ModContainer::getEventBus);
    }

    public static void whenModBusAvailable(String modId, Consumer<IEventBus> busConsumer) {
        IEventBus bus = getModEventBus(modId).orElseThrow(() -> new IllegalStateException("Mod '" + modId + "' is not available!"));
        busConsumer.accept(bus);
    }


    public void onGatherData(GatherDataEvent e) {
        DataGenerator generator = e.getGenerator();
        ExistingFileHelper existingFileHelper = e.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookup = e.getLookupProvider();
        PackOutput packOutput = e.getGenerator().getPackOutput();

        /*Resource Pack*/
        generator.addProvider(e.includeClient(), new WAEnglishLangProvider(generator));
        generator.addProvider(e.includeClient(), new WAItemModelProvider(generator, existingFileHelper));
        generator.addProvider(e.includeClient(), new WABlockModelProvider(generator, existingFileHelper));
        generator.addProvider(e.includeClient(), new WASoundProvider(generator, existingFileHelper));

        /*Data Pack*/

        var registries = e.getLookupProvider();

        var blockTagsProvider = generator.addProvider(e.includeServer(), new WABlockTagProvider(packOutput, registries, existingFileHelper));
        generator.addProvider(e.includeServer(), new WAItemTagsProvider(packOutput, registries, blockTagsProvider.contentsGetter(), existingFileHelper));

        generator.addProvider(e.includeServer(), new WALootProvider(generator.getPackOutput(), BuiltInLootTables.all(), List.of(new LootTableProvider.SubProviderEntry(WALootProvider.ModBlockLoot::new, LootContextParamSets.BLOCK), new LootTableProvider.SubProviderEntry(WALootProvider.ModChestLoot::new, LootContextParamSets.CHEST)), lookup));
        generator.addProvider(e.includeServer(), new WABiomeTagsProvider(generator.getPackOutput(), lookup, existingFileHelper));
        generator.addProvider(e.includeServer(), new WorldGenProvider(generator.getPackOutput(), e.getLookupProvider()));
        generator.addProvider(e.includeServer(), new WARecipeProvider(generator.getPackOutput(), e.getLookupProvider()));
        generator.addProvider(e.includeServer(), new WAEntityTypeTagsProvider(generator.getPackOutput(), lookup, existingFileHelper));

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        AngelVariant.init();
    }


    public void onAttributeAssign(EntityAttributeCreationEvent event) {
        event.put(WAEntities.WEEPING_ANGEL.get(), WeepingAngel.createAttributes().build());
    }


    public void spawns(RegisterSpawnPlacementsEvent registerSpawnPlacementsEvent) {
        registerSpawnPlacementsEvent.register(WAEntities.WEEPING_ANGEL.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }

}
