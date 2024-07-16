package mc.craig.software.angels.client.models.neoforge;

import com.google.common.base.Supplier;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ModelRegistrationImpl {
    public static final Map<ModelLayerLocation, Supplier<LayerDefinition>> DEFINITIONS = new ConcurrentHashMap<>();

    public static ModelLayerLocation register(ModelLayerLocation location, Supplier<LayerDefinition> definition) {
        DEFINITIONS.put(location, definition);
        return location;
    }

    public static void register(EntityRenderersEvent.RegisterLayerDefinitions event) {
        DEFINITIONS.forEach(event::registerLayerDefinition);
    }
}
