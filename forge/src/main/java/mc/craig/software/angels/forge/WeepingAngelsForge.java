package mc.craig.software.angels.forge;

import com.mojang.serialization.Codec;
import mc.craig.software.angels.WAConfiguration;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.WAEntities;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import mc.craig.software.angels.common.entity.angel.ai.AngelVariant;
import mc.craig.software.angels.data.forge.*;
import mc.craig.software.angels.data.forge.biome.AddAngelSpawns;
import mc.craig.software.angels.forge.compat.vivecraft.ServerReflector;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.StartupMessageManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

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

        final DeferredRegister<Codec<? extends BiomeModifier>> serializers = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, WeepingAngels.MODID);
        serializers.register(modEventBus);
        serializers.register(AddAngelSpawns.WEEPING_ANGEL_SPAWNS.getPath(), AddAngelSpawns::makeCodec);

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
        generator.addProvider(true, new WABiomeMods(generator));
        generator.addProvider(true, new RecipeProvider(generator));
        generator.addProvider(true, new SoundProvider(generator, existingFileHelper));
        generator.addProvider(true, new BlockTags(generator, existingFileHelper));
        generator.addProvider(true, new EntityTypeTags(generator, existingFileHelper));
        generator.addProvider(true, new ItemTags(generator, new BlockTags(generator, existingFileHelper), existingFileHelper));
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        VR_REFLECTOR.init();
        AngelVariant.init();
        SpawnPlacements.register(WAEntities.WEEPING_ANGEL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
    }

    public void onAttributeAssign(EntityAttributeCreationEvent event) {
        event.put(WAEntities.WEEPING_ANGEL.get(), WeepingAngel.createAttributes().build());
    }

}
