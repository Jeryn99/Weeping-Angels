package mc.craig.software.angels;

import mc.craig.software.angels.client.models.entity.WAModels;
import mc.craig.software.angels.client.renders.entities.AngelRender;
import mc.craig.software.angels.client.renders.entities.AnomalyRender;
import mc.craig.software.angels.client.renders.entities.CGRender;
import mc.craig.software.angels.common.WAObjects;
import mc.craig.software.angels.common.WAPaintings;
import mc.craig.software.angels.common.entities.WeepingAngel;
import mc.craig.software.angels.common.entities.attributes.WAAttributes;
import mc.craig.software.angels.common.level.WAFeatures;
import mc.craig.software.angels.common.level.WAPieces;
import mc.craig.software.angels.common.variants.AngelTypes;
import mc.craig.software.angels.compat.vivecraft.ServerReflector;
import mc.craig.software.angels.config.WAConfig;
import mc.craig.software.angels.data.*;
import mc.craig.software.angels.network.Network;
import mc.craig.software.angels.utils.AngelUtil;
import mc.craig.software.angels.utils.ClientUtil;
import mc.craig.software.angels.utils.FortuneBonusEnchant;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.progress.StartupMessageManager;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.minecraftforge.registries.NewRegistryEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

@Mod("weeping_angels")
public class WeepingAngels {

    public static final String MODID = "weeping_angels";
    public static final String NAME = "Weeping Angels";
    public static final ServerReflector VR_REFLECTOR = new ServerReflector();
    public static Logger LOGGER = LogManager.getLogger(NAME);

    public WeepingAngels() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.register(this);
        modBus.addListener(this::setup);
        modBus.addListener(this::onAttributeAssign);

        AngelTypes.VARIANTS.register(modBus);
        WAGlobalLoot.GLM.register(modBus);

        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, WAConfig.CONFIG_SPEC);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> modBus.addListener(this::doClientStuff));
        StartupMessageManager.addModMessage("Don't Blink!");
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onNewRegistries(NewRegistryEvent e) {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        WAObjects.Sounds.SOUNDS.register(bus);
        WAObjects.Items.ITEMS.register(bus);
        WAObjects.Blocks.BLOCKS.register(bus);
        WAObjects.Blocks.BLOCK_ITEMS.register(bus);
        WAObjects.EntityEntries.ENTITIES.register(bus);
        WAObjects.Tiles.TILES.register(bus);
        WAFeatures.DEFERRED_REGISTRY_STRUCTURE.register(bus);
        WAPaintings.PAINTINGS.register(bus);
        WAAttributes.ATTRIBUTES.register(bus);
    }

    private void setup(final @NotNull FMLCommonSetupEvent event) {
        Network.init();
        AngelUtil.registerFunction(new ResourceLocation(MODID, "fortune_enchant"), new FortuneBonusEnchant.Serializer()); //registerFunction
        event.enqueueWork(WAFeatures::ores);
        VR_REFLECTOR.init();
        WAPieces.init();
    }


    @SubscribeEvent
    public void regModels(EntityRenderersEvent.RegisterLayerDefinitions definitions) {
        WAModels.init(definitions);
    }

    @SubscribeEvent
    public void entityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(WAObjects.EntityEntries.WEEPING_ANGEL.get(), AngelRender::new);
        event.registerEntityRenderer(WAObjects.EntityEntries.ANOMALY.get(), AnomalyRender::new);
        event.registerEntityRenderer(WAObjects.EntityEntries.CHRONODYNE_GENERATOR.get(), CGRender::new);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        DistExecutor.runWhenOn(Dist.CLIENT, () -> ClientUtil::doClientStuff);
    }

    @SubscribeEvent
    public void onGatherData(GatherDataEvent e) {
        DataGenerator generator = e.getGenerator();
        ExistingFileHelper existingFileHelper = e.getExistingFileHelper();
        generator.addProvider(new WAItemTags(generator, new WABlockTags(generator, existingFileHelper), existingFileHelper));
        generator.addProvider(new WABlockTags(generator, existingFileHelper));
        generator.addProvider(new LootTablesForDrops(generator));
        generator.addProvider(new WALangEnglish());
        generator.addProvider(new WABiomeGen(generator, existingFileHelper));
        generator.addProvider(new WAStructureTagGen(generator, existingFileHelper));
        generator.addProvider(new WARecipeGen(generator));
        // generator.addProvider(new WALootTables(generator));
    }

    public void onAttributeAssign(EntityAttributeCreationEvent event) {
        event.put(WAObjects.EntityEntries.WEEPING_ANGEL.get(), WeepingAngel.createAttributes().build());
        event.put(WAObjects.EntityEntries.ANOMALY.get(), WeepingAngel.createAttributes().build());
    }


}
