package mc.craig.software.angels.forge;

import com.mojang.serialization.Codec;
import mc.craig.software.angels.WAConfiguration;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.WAEntities;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import mc.craig.software.angels.common.entity.angel.ai.AngelVariant;
import mc.craig.software.angels.data.forge.*;
import mc.craig.software.angels.data.forge.biome.AddAngelSpawns;
import mc.craig.software.angels.forge.compat.tardis.TardisClient;
import mc.craig.software.angels.forge.compat.tardis.TardisMod;
import mc.craig.software.angels.forge.compat.tardis.datagen.TardisSchemGen;
import mc.craig.software.angels.forge.compat.tardis.datagen.TardisSpectroGen;
import mc.craig.software.angels.forge.compat.tardis.registry.TardisBlocks;
import mc.craig.software.angels.forge.compat.tardis.registry.TardisExteriorReg;
import mc.craig.software.angels.forge.compat.tardis.registry.TardisTiles;
import mc.craig.software.angels.forge.compat.vivecraft.ServerReflector;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.StartupMessageManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegisterEvent;

@Mod(WeepingAngels.MODID)
public class WeepingAngelsForge {

    public static final ServerReflector VR_REFLECTOR = new ServerReflector();


    public WeepingAngelsForge() {
        WeepingAngels.init();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, WAConfiguration.CONFIG_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, WAConfiguration.SPAWNS_SPEC, "weeping-angels-spawns.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, WAConfiguration.CLIENT_SPEC);

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::onAttributeAssign);
        modEventBus.addListener(this::onGatherData);

        MinecraftForge.EVENT_BUS.register(this);

        if (ModList.get().isLoaded("tardis")) {
            TardisTiles.TILES.register(modEventBus);
            TardisBlocks.BLOCKS.register(modEventBus);
            TardisExteriorReg.EXTERIORS.register(modEventBus);
        }

        final DeferredRegister<Codec<? extends BiomeModifier>> serializers = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, WeepingAngels.MODID);
        serializers.register(modEventBus);
        serializers.register(AddAngelSpawns.WEEPING_ANGEL_SPAWNS.getPath(), AddAngelSpawns::makeCodec);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> modEventBus.addListener(this::doClientStuff));
        StartupMessageManager.addModMessage("Don't Blink!");
    }

    public void onGatherData(GatherDataEvent e) {
        DataGenerator generator = e.getGenerator();
        ExistingFileHelper existingFileHelper = e.getExistingFileHelper();
        generator.addProvider(true, new EnglishLang(generator));
        generator.addProvider(true, new ModelProviderItem(generator, existingFileHelper));
        generator.addProvider(true, new ModelProviderBlock(generator, existingFileHelper));
        generator.addProvider(true, new LootProvider(generator));
        generator.addProvider(true, new BiomeTagsProvider(generator, existingFileHelper));
        generator.addProvider(true, new StructureTagsProvider(generator, WeepingAngels.MODID, existingFileHelper));
        generator.addProvider(true, new WABiomeMods(generator));
        generator.addProvider(true, new RecipeProvider(generator));
        generator.addProvider(true, new SoundProvider(generator, existingFileHelper));
        generator.addProvider(true, new BlockTags(generator, existingFileHelper));
        generator.addProvider(true, new EntityTypeTags(generator, existingFileHelper));
        generator.addProvider(true, new ItemTags(generator, new BlockTags(generator, existingFileHelper), existingFileHelper));

        if (ModList.get().isLoaded("tardis")) {
            generator.addProvider(true, new TardisSpectroGen(generator));
            generator.addProvider(true, new TardisSchemGen(generator));
        }
    }
    private void doClientStuff(final FMLClientSetupEvent event) {
        if (ModList.get().isLoaded("tardis")) {
            MinecraftForge.EVENT_BUS.register(TardisClient.class);
            DistExecutor.runWhenOn(Dist.CLIENT, () -> TardisClient::clientStuff);
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        VR_REFLECTOR.init();
        AngelVariant.init();
        SpawnPlacements.register(WAEntities.WEEPING_ANGEL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);

        if (ModList.get().isLoaded("tardis")) {
            TardisMod.enableTardis();
        }
    }

    public void onAttributeAssign(EntityAttributeCreationEvent event) {
        event.put(WAEntities.WEEPING_ANGEL.get(), WeepingAngel.createAttributes().build());
    }

}
