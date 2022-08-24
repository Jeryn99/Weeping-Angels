package mc.craig.software.angels;

import com.mojang.logging.LogUtils;
import mc.craig.software.angels.common.WAEntities;
import mc.craig.software.angels.common.entity.angel.BlockBehaviour;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import mc.craig.software.angels.common.items.WAItems;
import mc.craig.software.angels.compat.vivecraft.ServerReflector;
import mc.craig.software.angels.data.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.StartupMessageManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(WeepingAngels.MODID)
public class WeepingAngels {
    public static final String MODID = "weeping_angels";
    public static final ServerReflector VR_REFLECTOR = new ServerReflector();
    public static final Logger LOGGER = LogUtils.getLogger();

    public WeepingAngels() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::onAttributeAssign);
        modEventBus.addListener(this::onGatherData);

        MinecraftForge.EVENT_BUS.register(this);
        WAItems.ITEMS.register(modEventBus);
        WAEntities.ENTITY_TYPES.register(modEventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, WAConfiguration.CONFIG_SPEC);
        StartupMessageManager.addModMessage("Don't Blink!");


    }

    public void onGatherData(GatherDataEvent e) {
        DataGenerator generator = e.getGenerator();
        ExistingFileHelper existingFileHelper = e.getExistingFileHelper();
        generator.addProvider(true, new EnglishLang(generator));
        generator.addProvider(true, new LootProvider(generator));
        generator.addProvider(true, new WABiomeMods(generator));
        generator.addProvider(true, new BlockTagsProvider(generator, existingFileHelper));
        generator.addProvider(true, new ItemTags(generator, new BlockTagsProvider(generator, existingFileHelper), existingFileHelper));

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        BlockBehaviour.init();
        SpawnPlacements.register(WAEntities.WEEPING_ANGEL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
    }

    public void onAttributeAssign(EntityAttributeCreationEvent event) {
        event.put(WAEntities.WEEPING_ANGEL.get(), WeepingAngel.createAttributes().build());
        event.put(WAEntities.ANOMALY.get(), WeepingAngel.createAttributes().build());
    }
}
