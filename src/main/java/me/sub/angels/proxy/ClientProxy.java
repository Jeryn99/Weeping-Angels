package me.sub.angels.proxy;

import me.sub.angels.client.models.item.ModelDetector;
import me.sub.angels.client.renders.RenderAngelPainting;
import me.sub.angels.client.renders.entities.*;
import me.sub.angels.client.renders.items.RenderItemStackBase;
import me.sub.angels.common.WAObjects;
import me.sub.angels.common.entities.EntityAngelPainting;
import me.sub.angels.common.entities.EntityAnomaly;
import me.sub.angels.common.entities.EntityChronodyneGenerator;
import me.sub.angels.common.entities.EntityWeepingAngel;
import me.sub.angels.common.tileentities.TileEntityChronodyneGenerator;
import me.sub.angels.common.tileentities.TileEntityPlinth;
import me.sub.angels.common.tileentities.TileEntitySnowArm;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit() {
        super.preInit();

    }

    @Override
    public void init() {
        super.init();

        // Entities
        RenderingRegistry.registerEntityRenderingHandler(EntityWeepingAngel.class, new RenderWeepingAngel());
        RenderingRegistry.registerEntityRenderingHandler(EntityAngelPainting.class, new RenderAngelPainting());
        RenderingRegistry.registerEntityRenderingHandler(EntityAnomaly.class, new RenderAnomaly());

        // Projectiles
        RenderingRegistry.registerEntityRenderingHandler(EntityChronodyneGenerator.class, new RenderChronodyneGenerator());

        // TESRS
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySnowArm.class, new RenderTileEntitySnowArm());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChronodyneGenerator.class, new RenderTileEntityCG());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlinth.class, new RenderTileEntityPlinth());

    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent ev) {
        for (Item item : WAObjects.items) {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
        }
        WAObjects.items = new ArrayList<>();
    }

    @Override
    public void postInit() {
        super.postInit();

        // ITEM TESRS
        WAObjects.Items.TIMEY_WIMEY_DETECTOR.setTileEntityItemStackRenderer(new RenderItemStackBase(new ModelDetector()));

    }
}
