package mc.craig.software.angels.client.models;

import com.google.common.base.Supplier;
import dev.architectury.injectables.annotations.ExpectPlatform;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.client.models.blockentity.CoffinModel;
import mc.craig.software.angels.client.models.blockentity.GeneratorModel;
import mc.craig.software.angels.client.models.blockentity.PortalModel;
import mc.craig.software.angels.client.models.blockentity.TardisModel;
import mc.craig.software.angels.client.models.entity.angel.*;
import mc.craig.software.angels.common.entity.angel.AngelTextureVariant;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class ModelRegistration {

    public static ModelLayerLocation COFFIN, MERCY_WINGS, PORTAL, ALICE_ANGEL, GAS_ANGEL, TARDIS, A_DIZZLE_ANGEL, DOCTOR_ANGEL, GENERATOR;
    private static AngelModel GAS_MODEL, ALICE_MODEL, A_DIZZLE_MODEL, DOCTOR_ANGEL_MODEL;

    public static void init() {
        ALICE_ANGEL = register(new ModelLayerLocation(new ResourceLocation(WeepingAngels.MODID, "model"), "alice_angel"), AliceAngelModel::meshLayer);
        GAS_ANGEL = register(new ModelLayerLocation(new ResourceLocation(WeepingAngels.MODID, "model"), "gas_angel"), GasAngelModel::meshLayer);
        MERCY_WINGS = register(new ModelLayerLocation(new ResourceLocation(WeepingAngels.MODID, "model"), "mercy_wings"), MercyWingsModel::meshLayer);
        COFFIN = register(new ModelLayerLocation(new ResourceLocation(WeepingAngels.MODID, "model"), "coffin"), CoffinModel::meshLayer);
        TARDIS = register(new ModelLayerLocation(new ResourceLocation(WeepingAngels.MODID, "model"), "tardis"), TardisModel::meshLayer);
        A_DIZZLE_ANGEL = register(new ModelLayerLocation(new ResourceLocation(WeepingAngels.MODID, "model"), "a_dizzle_model"), ADizzleAngelModel::meshLayer);
        DOCTOR_ANGEL = register(new ModelLayerLocation(new ResourceLocation(WeepingAngels.MODID, "model"), "doctor_angel"), DoctorAngelModel::meshLayer);
        PORTAL = register(new ModelLayerLocation(new ResourceLocation(WeepingAngels.MODID, "model"), "portal"), PortalModel::meshLayer);
        GENERATOR = register(new ModelLayerLocation(new ResourceLocation(WeepingAngels.MODID, "model"), "generator"), GeneratorModel::meshLayer);
    }

    public static void regModels(BlockEntityRendererProvider.Context context) {
        EntityModelSet entityModels = context.getModelSet();
        GAS_MODEL = new GasAngelModel(entityModels.bakeLayer(GAS_ANGEL));
        ALICE_MODEL = new AliceAngelModel(entityModels.bakeLayer(ALICE_ANGEL));
        A_DIZZLE_MODEL = new ADizzleAngelModel(entityModels.bakeLayer(A_DIZZLE_ANGEL));
        DOCTOR_ANGEL_MODEL = new DoctorAngelModel(entityModels.bakeLayer(DOCTOR_ANGEL));
    }

    public static AngelModel getModelFor(AngelTextureVariant angelTextureVariant) {

        if(angelTextureVariant == AngelTextureVariant.DOCTOR){
            return DOCTOR_ANGEL_MODEL;
        }

        if (angelTextureVariant == AngelTextureVariant.A_DIZZLE) {
            return A_DIZZLE_MODEL;
        }

        if (angelTextureVariant == AngelTextureVariant.GAS_RUSTED || angelTextureVariant == AngelTextureVariant.GAS_STONE) {
            return GAS_MODEL;
        }

        return ALICE_MODEL;
    }


    @ExpectPlatform
    public static ModelLayerLocation register(ModelLayerLocation location, Supplier<LayerDefinition> definition) {
        throw new RuntimeException("Uh oh! Wrong platform!");
    }


}
