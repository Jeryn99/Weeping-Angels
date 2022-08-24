package mc.craig.software.angels.handlers;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.client.models.ModelRegistration;
import mc.craig.software.angels.client.render.WeepingAngelRenderer;
import mc.craig.software.angels.common.entity.WAEntities;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = WeepingAngels.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModBus {

    @SubscribeEvent
    public static void regModels(EntityRenderersEvent.RegisterLayerDefinitions definitions) {
        ModelRegistration.registerModels(definitions);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {

    }

    @SubscribeEvent
    public static void entityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(WAEntities.WEEPING_ANGEL.get(), WeepingAngelRenderer::new);
    }

}
