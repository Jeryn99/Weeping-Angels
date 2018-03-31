package com.github.reallysub.angels.common;

import com.github.reallysub.angels.WeepingAngels;
import com.github.reallysub.angels.client.RenderAngel;
import com.github.reallysub.angels.common.entities.EntityAngel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = WeepingAngels.MODID)
public class InitEvents {

    private static int entityID = 0;

    private static <E extends Entity> EntityEntryBuilder<E> createBuilder(String name) {
        EntityEntryBuilder<E> builder = EntityEntryBuilder.create();
        ResourceLocation registryName = new ResourceLocation(WeepingAngels.MODID + ":" + name);
        return builder.id(registryName, entityID++).name(registryName.toString().replaceAll(WeepingAngels.MODID + ":", ""));
    }


    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
        EntityEntry[] entries = {createBuilder("weepingAngel").entity(EntityAngel.class).tracker(80, 3, false).build()};
        event.getRegistry().registerAll(entries);

        if(FMLCommonHandler.instance().getSide() == Side.CLIENT)
        {
            setUpRenders();
        }
    }

    @SideOnly(Side.CLIENT)
    private static void setUpRenders()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityAngel.class, new RenderAngel());
    }
}
