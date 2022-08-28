package mc.craig.software.angels.client.models.forge;

import com.google.common.base.Supplier;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Mod.EventBusSubscriber
public class ModelRegistration {
    private static final Map<ModelLayerLocation, Supplier<LayerDefinition>> DEFINITIONS = new ConcurrentHashMap<>();


    public static void register(ModelLayerLocation location, Supplier<LayerDefinition> definition) {
        DEFINITIONS.put(location, definition);
    }

    @SubscribeEvent
    public static void event(EntityRenderersEvent.RegisterLayerDefinitions event) {
        for (Map.Entry<ModelLayerLocation, Supplier<LayerDefinition>> entry : DEFINITIONS.entrySet()) {
            event.registerLayerDefinition(entry.getKey(), entry.getValue());
        }
    }
}
