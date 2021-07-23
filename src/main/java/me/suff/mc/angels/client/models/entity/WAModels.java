package me.suff.mc.angels.client.models.entity;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.client.models.block.CoffinModel;
import me.suff.mc.angels.client.models.block.PoliceBoxModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fmlclient.registry.RenderingRegistry;

public class WAModels {

    //Angels
    public static ModelLayerLocation ANGEL_ANGELA = createAngelModelLocation("main");
    public static ModelLayerLocation ANGEL_VILLAGER = createAngelModelLocation("villager");
    public static ModelLayerLocation ANGEL_ED = createAngelModelLocation("ed");
    public static ModelLayerLocation ANGEL_CLASSIC = createAngelModelLocation("a_dizzle");
    public static ModelLayerLocation ANGEL_CHERUB = createAngelModelLocation("cherub");

    private static ModelLayerLocation createAngelModelLocation(String weeping_angel) {
        return new ModelLayerLocation(new ResourceLocation(WeepingAngels.MODID, "model"), "weeping_angel_"+weeping_angel);
    }

    //Other
    public static ModelLayerLocation ANOMALY = new ModelLayerLocation(new ResourceLocation(WeepingAngels.MODID, "model"), "anomaly");
    public static ModelLayerLocation COFFIN = new ModelLayerLocation(new ResourceLocation(WeepingAngels.MODID, "model"), "coffin");
    public static ModelLayerLocation POLICE_BOX = new ModelLayerLocation(new ResourceLocation(WeepingAngels.MODID, "model"), "police_box");

    @SubscribeEvent
    public static void init() {
        RenderingRegistry.registerLayerDefinition(ANGEL_ANGELA, ModelAngelaAngel::getModelData);
        RenderingRegistry.registerLayerDefinition(ANGEL_VILLAGER, ModelWeepingVillager::getModelData);
        RenderingRegistry.registerLayerDefinition(ANGEL_CLASSIC, ModelClassicAngel::getModelData);
        RenderingRegistry.registerLayerDefinition(ANGEL_CHERUB, ModelAngelChild::getModelData);
        RenderingRegistry.registerLayerDefinition(ANGEL_ED, ModelAngelEd::getModelData);
        RenderingRegistry.registerLayerDefinition(COFFIN, CoffinModel::getModelData);
        RenderingRegistry.registerLayerDefinition(ANOMALY, PortalModel::getModelData);
        RenderingRegistry.registerLayerDefinition(POLICE_BOX, PoliceBoxModel::getModelData);
    }

}
