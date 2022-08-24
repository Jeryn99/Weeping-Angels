package mc.craig.software.angels;

import com.mojang.logging.LogUtils;
import mc.craig.software.angels.common.entity.WAEntities;
import mc.craig.software.angels.common.entity.WeepingAngel;
import mc.craig.software.angels.common.items.WAItems;
import mc.craig.software.angels.compat.vivecraft.ServerReflector;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.StartupMessageManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
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

        MinecraftForge.EVENT_BUS.register(this);
        WAItems.ITEMS.register(modEventBus);
        WAEntities.ENTITY_TYPES.register(modEventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, WAConfiguration.CONFIG_SPEC);
        StartupMessageManager.addModMessage("Don't Blink!");


    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    public void onAttributeAssign(EntityAttributeCreationEvent event) {
        event.put(WAEntities.WEEPING_ANGEL.get(), WeepingAngel.createAttributes().build());
    }
}
