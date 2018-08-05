package me.sub.angels.utils;

import me.sub.angels.WeepingAngels;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderUtil {

    public static <T extends TileEntity> void bindTESR(Class<T> tileEntityClass, TileEntitySpecialRenderer<? super T> specialRenderer) {
        WeepingAngels.LOG.info("Registering Render for tile: " + tileEntityClass.getName() + ", [" + specialRenderer.getClass().getName() + "]");
        ClientRegistry.bindTileEntitySpecialRenderer(tileEntityClass, specialRenderer);
    }

    public static <T extends Entity> void bindEntityRender(Class<T> entityClass, IRenderFactory<? super T> renderFactory) {
        WeepingAngels.LOG.info("Registering Render for entity: " + entityClass.getSimpleName() + ", [" + renderFactory.getClass().getName() + "]");
        RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory);
    }

    public static void setItemRender(Item item, TileEntityItemStackRenderer stackRenderer) {
        WeepingAngels.LOG.info("Registering TESR Render for item: " + item.getRegistryName() + ", [" + stackRenderer.getClass().getName() + "]");
        item.setTileEntityItemStackRenderer(stackRenderer);
    }

    public static void setItemRender(Item item) {
        WeepingAngels.LOG.info("Registering Render for item: " + item.getRegistryName());
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
