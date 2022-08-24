package mc.craig.software.angels.client.models;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.client.models.angel.WeepingAngelModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;

public class ModelRegistration {

    public static ModelLayerLocation WEEPING_ANGEL = new ModelLayerLocation(new ResourceLocation(WeepingAngels.MODID, "model"), "weeping_angel");


    public static void registerModels(EntityRenderersEvent.RegisterLayerDefinitions definitions) {
        definitions.registerLayerDefinition(WEEPING_ANGEL, WeepingAngelModel::meshLayer);
    }

}
