package craig.software.mc.angels;

import craig.software.mc.angels.common.WAPaintings;
import craig.software.mc.angels.common.entities.attributes.WAAttributes;
import craig.software.mc.angels.common.variants.AngelVariants;
import craig.software.mc.angels.compat.tardis.datagen.TardisSchemGen;
import craig.software.mc.angels.compat.tardis.datagen.TardisSpectroGen;
import craig.software.mc.angels.compat.tardis.registry.NewTardisBlocks;
import craig.software.mc.angels.compat.tardis.registry.TardisExteriorReg;
import craig.software.mc.angels.compat.tardis.registry.TardisTiles;
import craig.software.mc.angels.compat.vr.ServerReflector;
import craig.software.mc.angels.config.WAConfig;
import craig.software.mc.angels.data.*;
import craig.software.mc.angels.network.Network;
import craig.software.mc.angels.utils.FortuneEnchantBonus;
import craig.software.mc.angels.common.WAGlm;
import craig.software.mc.angels.common.WAObjects;
import craig.software.mc.angels.common.entities.WeepingAngelEntity;
import craig.software.mc.angels.compat.tardis.TardisMod;
import me.suff.mc.angels.data.*;
import craig.software.mc.angels.utils.AngelUtil;
import craig.software.mc.angels.utils.ClientUtil;
import net.minecraft.data.DataGenerator;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.progress.StartupMessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        AngelVariants.VARIANTS.register(modBus);
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, WAConfig.CONFIG_SPEC);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> modBus.addListener(this::doClientStuff));
        StartupMessageManager.addModMessage("Don't Blink!");
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onNewRegistries(RegistryEvent.NewRegistry e) {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        WAObjects.Sounds.SOUNDS.register(bus);
        WAObjects.Items.ITEMS.register(bus);
        WAObjects.Blocks.BLOCKS.register(bus);
        WAObjects.Blocks.BLOCK_ITEMS.register(bus);
        WAObjects.EntityEntries.ENTITIES.register(bus);
        WAObjects.Tiles.TILES.register(bus);
        WAObjects.WorldGenEntries.FEATURES.register(bus);
        WAObjects.Structures.STRUCTURES.register(bus);
        WAPaintings.PAINTINGS.register(bus);
        WAAttributes.ATTRIBUTES.register(bus);
        WAGlm.GLM.register(bus);

        if (ModList.get().isLoaded("tardis")) {
            TardisTiles.TILES.register(bus);
            NewTardisBlocks.BLOCKS.register(bus);
            TardisExteriorReg.EXTERIORS.register(bus);
            TardisMod.enableTardis();
        }
    }


    private void setup(final FMLCommonSetupEvent event) {
        Network.init();
        GlobalEntityTypeAttributes.put(WAObjects.EntityEntries.WEEPING_ANGEL.get(), WeepingAngelEntity.createAttributes().build());
        GlobalEntityTypeAttributes.put(WAObjects.EntityEntries.ANOMALY.get(), WeepingAngelEntity.createAttributes().build());
        AngelUtil.registerFunction(new ResourceLocation(MODID, "fortune_enchant"), new FortuneEnchantBonus.Serializer()); //registerFunction
        event.enqueueWork(() ->
        {
            WAObjects.setupStructures();
            WAObjects.ConfiguredStructures.registerConfiguredStructures();
            WAObjects.ConfiguredFeatures.registerConfiguredFeatures();
        });
        VR_REFLECTOR.init();

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
        generator.addProvider(new WALangEnglish(generator));
        generator.addProvider(new WARecipeGen(generator));
        generator.addProvider(new WALootTables(generator));
        generator.addProvider(new LootTablesForDrops(generator));

        if (ModList.get().isLoaded("tardis")) {
            generator.addProvider(new TardisSpectroGen(generator));
            generator.addProvider(new TardisSchemGen(generator));
        }
    }


}
