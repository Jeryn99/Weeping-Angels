package me.swirtzly.angels;

import me.swirtzly.angels.client.renders.entities.RenderAnomaly;
import me.swirtzly.angels.client.renders.entities.RenderCG;
import me.swirtzly.angels.client.renders.entities.RenderWeepingAngel;
import me.swirtzly.angels.client.renders.tileentities.RenderTileEntityCG;
import me.swirtzly.angels.client.renders.tileentities.RenderTileEntityPlinth;
import me.swirtzly.angels.client.renders.tileentities.RenderTileEntitySnowArm;
import me.swirtzly.angels.common.WAObjects;
import me.swirtzly.angels.common.entities.EntityAnomaly;
import me.swirtzly.angels.common.entities.EntityChronodyneGenerator;
import me.swirtzly.angels.common.entities.EntityWeepingAngel;
import me.swirtzly.angels.common.events.EventHandler;
import me.swirtzly.angels.common.misc.FortuneEnchantBonus;
import me.swirtzly.angels.common.tileentities.TileEntityChronodyneGenerator;
import me.swirtzly.angels.common.tileentities.TileEntityPlinth;
import me.swirtzly.angels.common.tileentities.TileEntitySnowArm;
import me.swirtzly.angels.common.world.WorldGen;
import me.swirtzly.angels.config.WAConfig;
import me.swirtzly.angels.utils.ClientUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//TODO: Fix server crash regarding Attempted to load class net/minecraft/client/renderer/tileentity/TileEntityRenderer for invalid dist DEDICATED_SERVER
@Mod("weeping_angels")
public class WeepingAngels {
	
	public static final String MODID = "weeping_angels";
	public static final String NAME = "Weeping Angels";
	
	public static Logger LOGGER = LogManager.getLogger(NAME);
	
	public WeepingAngels() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
		MinecraftForge.EVENT_BUS.register(new EventHandler());
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, WAConfig.CONFIG_SPEC);
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onNewRegistries(RegistryEvent.NewRegistry e) {
		WAObjects.Sounds.SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
		WAObjects.Items.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		WAObjects.Blocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
		WAObjects.Blocks.BLOCK_ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		WAObjects.EntityEntries.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
		WAObjects.Tiles.TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
		WAObjects.WorldGenEntries.FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
	}


	private void commonSetup(final FMLCommonSetupEvent event) {
		WorldGen.applyFeatures();
		LootFunctionManager.registerFunction(new FortuneEnchantBonus.Serializer());
	}
	
	private void doClientStuff(final FMLClientSetupEvent event) {
        ClientUtil.bindTESR(TileEntitySnowArm.class, new RenderTileEntitySnowArm());
        ClientUtil.bindTESR(TileEntityChronodyneGenerator.class, new RenderTileEntityCG());
        ClientUtil.bindTESR(TileEntityPlinth.class, new RenderTileEntityPlinth());

        ClientUtil.bindEntityRender(EntityWeepingAngel.class, RenderWeepingAngel::new);
        //ClientUtil.bindEntityRender(EntityAngelPainting.class, RenderAngelPainting::new);
        ClientUtil.bindEntityRender(EntityAnomaly.class, RenderAnomaly::new);
        ClientUtil.bindEntityRender(EntityChronodyneGenerator.class, (EntityRendererManager p_i50956_1_) -> new RenderCG(p_i50956_1_, Minecraft.getInstance().getItemRenderer(), 12));
	}
	
	
}
