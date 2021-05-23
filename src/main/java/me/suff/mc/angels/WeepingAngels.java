package me.suff.mc.angels;

import com.google.common.collect.ImmutableList;
import me.suff.mc.angels.client.ClientEvents;
import me.suff.mc.angels.common.AngelParticles;
import me.suff.mc.angels.common.PaintingStuff;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import me.suff.mc.angels.common.entities.attributes.WAAttributes;
import me.suff.mc.angels.compat.tardis.TardisMod;
import me.suff.mc.angels.compat.vr.ServerReflector;
import me.suff.mc.angels.config.WAConfig;
import me.suff.mc.angels.data.*;
import me.suff.mc.angels.network.Network;
import me.suff.mc.angels.utils.AngelUtils;
import me.suff.mc.angels.utils.ClientUtil;
import me.suff.mc.angels.utils.FortuneEnchantBonus;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
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
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, WAConfig.CONFIG_SPEC);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> modBus.addListener(this::doClientStuff));
        MinecraftForge.EVENT_BUS.addGenericListener(Block.class, this::onMissingMappingsBlock);
        MinecraftForge.EVENT_BUS.addGenericListener(TileEntityType.class, this::onMissingMappingsTile);
        MinecraftForge.EVENT_BUS.addGenericListener(Item.class, this::onMissingMappingsItem);
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
        PaintingStuff.PAINTINGS.register(bus);
        WAAttributes.ATTRIBUTES.register(bus);
        AngelParticles.TYPES.register(bus);
    }

    private void setup(final FMLCommonSetupEvent event) {
        Network.init();
        GlobalEntityTypeAttributes.put(WAObjects.EntityEntries.WEEPING_ANGEL.get(), WeepingAngelEntity.createAttributes().build());
        GlobalEntityTypeAttributes.put(WAObjects.EntityEntries.ANOMALY.get(), WeepingAngelEntity.createAttributes().build());
        AngelUtils.registerFunction(new ResourceLocation(MODID, "fortune_enchant"), new FortuneEnchantBonus.Serializer()); //registerFunction
        event.enqueueWork(() ->
        {
            WAObjects.setupStructures();
            WAObjects.ConfiguredStructures.registerConfiguredStructures();
            WAObjects.ConfiguredFeatures.registerConfiguredFeatures();
        });
        VR_REFLECTOR.init();

        if (ModList.get().isLoaded("tardis")) {
            TardisMod.enableTardis();
        }
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
    }


    public void onMissingMappingsItem(RegistryEvent.MissingMappings<Item> mappings) {
        ImmutableList<RegistryEvent.MissingMappings.Mapping<Item>> mapp = mappings.getAllMappings();
        for (RegistryEvent.MissingMappings.Mapping<Item> itemMapping : mapp) {
            if (itemMapping.key.toString().equalsIgnoreCase("weeping_angels:snow_arm")) {
                LOGGER.info("Remapped Item weeping_angels:snow_arm to " + WAObjects.Blocks.SNOW_ANGEL.get().asItem().getRegistryName());
                itemMapping.remap(WAObjects.Blocks.SNOW_ANGEL.get().asItem());
            }
        }
    }


    public void onMissingMappingsBlock(RegistryEvent.MissingMappings<Block> mappings) {
        ImmutableList<RegistryEvent.MissingMappings.Mapping<Block>> mapp = mappings.getAllMappings();
        for (RegistryEvent.MissingMappings.Mapping<Block> blockMapping : mapp) {
            if (blockMapping.key.toString().equalsIgnoreCase("weeping_angels:snow_arm")) {
                LOGGER.info("Remapped Block weeping_angels:snow_arm to " + WAObjects.Blocks.SNOW_ANGEL.get().getRegistryName());
                blockMapping.remap(WAObjects.Blocks.SNOW_ANGEL.get());
            }
        }
    }

    public void onMissingMappingsTile(RegistryEvent.MissingMappings<TileEntityType<?>> mappings) {
        ImmutableList<RegistryEvent.MissingMappings.Mapping<TileEntityType<?>>> mapp = mappings.getAllMappings();
        for (RegistryEvent.MissingMappings.Mapping<TileEntityType<?>> entityTypeMapping : mapp) {
            if (entityTypeMapping.key.toString().equalsIgnoreCase("weeping_angels:snow_arm")) {
                LOGGER.info("Remapped Tile weeping_angels:snow_arm to " + WAObjects.Tiles.SNOW_ANGEL.get().getRegistryName());
                entityTypeMapping.remap(WAObjects.Tiles.SNOW_ANGEL.get());
            }
        }
    }


}
