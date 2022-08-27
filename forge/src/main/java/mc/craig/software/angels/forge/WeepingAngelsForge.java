package mc.craig.software.angels.forge;

import mc.craig.software.angels.WAConfiguration;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.WAEntities;
import mc.craig.software.angels.common.WASounds;
import mc.craig.software.angels.common.blockentity.WABlockEntities;
import mc.craig.software.angels.common.blocks.WABlocks;
import mc.craig.software.angels.common.entity.angel.BlockReactions;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import mc.craig.software.angels.common.items.WAItems;
import mc.craig.software.angels.forge.compat.vivecraft.ServerReflector;
import mc.craig.software.angels.data.forge.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.StartupMessageManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(WeepingAngels.MODID)
public class WeepingAngelsForge {

    public static final ServerReflector VR_REFLECTOR = new ServerReflector();


    public WeepingAngelsForge() {
        WeepingAngels.init();

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::onAttributeAssign);
        modEventBus.addListener(this::onGatherData);


        MinecraftForge.EVENT_BUS.register(this);
        WAItems.ITEMS.register(modEventBus);
        WASounds.SOUNDS.register(modEventBus);
        WABlocks.BLOCKS.register(modEventBus);
        WAEntities.ENTITY_TYPES.register(modEventBus);
        WABlockEntities.BLOCK_ENTITY_TYPES.register(modEventBus);

        StartupMessageManager.addModMessage("Don't Blink!");

    }

    public void onGatherData(GatherDataEvent e) {
        DataGenerator generator = e.getGenerator();
        ExistingFileHelper existingFileHelper = e.getExistingFileHelper();
        generator.addProvider(true, new EnglishLang(generator));
        generator.addProvider(true, new ModelProviderItem(generator, existingFileHelper));
        generator.addProvider(true, new ModelProviderBlock(generator, existingFileHelper));
        generator.addProvider(true, new LootProvider(generator));
        generator.addProvider(true, new BiomeTags(generator, existingFileHelper));
        generator.addProvider(true, new WABiomeMods(generator));
        generator.addProvider(true, new SoundProvider(generator, existingFileHelper));
        generator.addProvider(true, new BlockTags(generator, existingFileHelper));
        generator.addProvider(true, new EntityTypeTags(generator, existingFileHelper));
        generator.addProvider(true, new ItemTags(generator, new BlockTags(generator, existingFileHelper), existingFileHelper));
    }


    private void commonSetup(final FMLCommonSetupEvent event) {
        BlockReactions.init();
        SpawnPlacements.register(WAEntities.WEEPING_ANGEL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        VR_REFLECTOR.init();
    }

    public void onAttributeAssign(EntityAttributeCreationEvent event) {
        event.put(WAEntities.WEEPING_ANGEL.get(), WeepingAngel.createAttributes().build());
        event.put(WAEntities.ANOMALY.get(), WeepingAngel.createAttributes().build());
    }

}
