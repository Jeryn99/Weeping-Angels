package mc.craig.software.angels.client.models;

import com.google.common.base.Supplier;
import dev.architectury.injectables.annotations.ExpectPlatform;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.client.models.blockentity.CoffinModel;
import mc.craig.software.angels.client.models.entity.angel.AnomalyModel;
import mc.craig.software.angels.client.models.entity.angel.WeepingAngelModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;

public class ModelRegistration {

    public static ModelLayerLocation COFFIN, MERCY_WINGS, ANOMALY, WEEPING_ANGEL;


    public static void init() {
        WEEPING_ANGEL = register(new ModelLayerLocation(new ResourceLocation(WeepingAngels.MODID, "model"), "weeping_angel"), WeepingAngelModel::meshLayer);
        ANOMALY = register(new ModelLayerLocation(new ResourceLocation(WeepingAngels.MODID, "model"), "anomaly"), AnomalyModel::meshLayer);
        MERCY_WINGS = register(new ModelLayerLocation(new ResourceLocation(WeepingAngels.MODID, "model"), "mercy_wings"), MercyWingsModel::meshLayer);
        COFFIN = register(new ModelLayerLocation(new ResourceLocation(WeepingAngels.MODID, "model"), "coffin"), CoffinModel::meshLayer);

    }

    @ExpectPlatform
    public static ModelLayerLocation register(ModelLayerLocation location, Supplier<LayerDefinition> definition) {
        throw new RuntimeException("Uh oh! Wrong platform!");
    }


}
