package mc.craig.software.angels.client.models;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.client.models.blockentity.CoffinModel;
import mc.craig.software.angels.client.models.entity.angel.AnomalyModel;
import mc.craig.software.angels.client.models.entity.angel.WeepingAngelModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModelRegistration {

    public static ModelLayerLocation WEEPING_ANGEL = new ModelLayerLocation(new ResourceLocation(WeepingAngels.MODID, "model"), "weeping_angel");
    public static ModelLayerLocation ANOMALY = new ModelLayerLocation(new ResourceLocation(WeepingAngels.MODID, "model"), "anomaly");
    public static ModelLayerLocation MERCY_WINGS = new ModelLayerLocation(new ResourceLocation(WeepingAngels.MODID, "model"), "mercy_wings");
    public static ModelLayerLocation COFFIN = new ModelLayerLocation(new ResourceLocation(WeepingAngels.MODID, "model"), "coffin");


    public static void registerModels(EntityRenderersEvent.RegisterLayerDefinitions definitions) {
        definitions.registerLayerDefinition(WEEPING_ANGEL, WeepingAngelModel::meshLayer);
        definitions.registerLayerDefinition(ANOMALY, AnomalyModel::meshLayer);
        definitions.registerLayerDefinition(MERCY_WINGS, MercyWingsModel::meshLayer);
        definitions.registerLayerDefinition(COFFIN, CoffinModel::meshLayer);
    }

}
