package mc.craig.software.angels.utils;

import mc.craig.software.angels.WeepingAngels;
import craig.software.mc.angels.client.models.entity.*;
import mc.craig.software.angels.client.models.entity.*;
import mc.craig.software.angels.client.renders.entities.AnomalyRender;
import mc.craig.software.angels.client.renders.entities.CGRender;
import mc.craig.software.angels.client.renders.entities.WeepingAngelsRenderer;
import mc.craig.software.angels.client.renders.layers.WingsLayer;
import mc.craig.software.angels.client.renders.tileentities.CoffinRenderer;
import mc.craig.software.angels.client.renders.tileentities.PlinthTileRender;
import mc.craig.software.angels.client.renders.tileentities.SnowArmTileRender;
import mc.craig.software.angels.client.renders.tileentities.StatueRender;
import mc.craig.software.angels.common.WAObjects;
import mc.craig.software.angels.common.entities.AngelType;
import mc.craig.software.angels.common.entities.WeepingAngelEntity;
import mc.craig.software.angels.common.items.AngelSpawnerItem;
import mc.craig.software.angels.common.items.DetectorItem;
import mc.craig.software.angels.compat.tardis.TardisClientStuff;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientUtil {

    private static final EntityModel<WeepingAngelEntity> VIO_1 = new ModelAngel();
    private static final EntityModel<WeepingAngelEntity> ED = new ModelAngelEd();
    private static final EntityModel<WeepingAngelEntity> CHERUB = new ModelAngelChild();
    private static final EntityModel<WeepingAngelEntity> A_DIZZLE = new ModelClassicAngel();
    private static final EntityModel<WeepingAngelEntity> VIO_2 = new ModelAngelMel();
    private static final EntityModel<WeepingAngelEntity> VILLAGER = new ModelWeepingVillager();
    private static final EntityModel<WeepingAngelEntity> DISASTER_MC = new ModelDisasterAngel();
    private static final EntityModel<WeepingAngelEntity> DYING = new ModelDyingAngel(0.0F, true);
    private static final EntityModel<WeepingAngelEntity> SPARE_TIME = new ModelVAAngel();
    private static final EntityModel<WeepingAngelEntity> DOCTOR = new ModelDoctorAngel();

    private static final Map<AngelType, EntityModel<WeepingAngelEntity>> MODEL_MAP = new HashMap<>();

    static {
        MODEL_MAP.put(AngelType.CHERUB, CHERUB); // ED
        MODEL_MAP.put(AngelType.ED, ED);// ED
        MODEL_MAP.put(AngelType.DISASTER_MC, DISASTER_MC); //DISASTER
        MODEL_MAP.put(AngelType.A_DIZZLE, A_DIZZLE); //A_DIZZLE
        MODEL_MAP.put(AngelType.VILLAGER, VILLAGER); //DOC
        MODEL_MAP.put(AngelType.VIO_1, VIO_1); //VIOLET
        MODEL_MAP.put(AngelType.VIO_2, VIO_2);//VIOLET
        MODEL_MAP.put(AngelType.DYING, DYING);//DISASTER
        MODEL_MAP.put(AngelType.SPARE_TIME, SPARE_TIME);//GAS
        MODEL_MAP.put(AngelType.DOCTOR, DOCTOR);//GAS
    }


    public static EntityModel<WeepingAngelEntity> getModelForAngel(AngelType angelType) {
        if (!MODEL_MAP.containsKey(angelType)) {
            try {
                throw new Exception("No model registered for " + angelType.name());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return MODEL_MAP.get(angelType);
    }

    @OnlyIn(Dist.CLIENT)
    public static void playSound(SoundEvent soundIn, float volumeSfx) {
        Minecraft.getInstance().getSoundManager().play(SimpleSound.forUI(soundIn, volumeSfx));
    }

    public static void doClientStuff() {

        if (ModList.get().isLoaded("tardis")) {
            TardisClientStuff.clientStuff();
        }

        ClientRegistry.bindTileEntityRenderer(WAObjects.Tiles.SNOW_ANGEL.get(), SnowArmTileRender::new);
        ClientRegistry.bindTileEntityRenderer(WAObjects.Tiles.PLINTH.get(), PlinthTileRender::new);
        ClientRegistry.bindTileEntityRenderer(WAObjects.Tiles.STATUE.get(), StatueRender::new);
        ClientRegistry.bindTileEntityRenderer(WAObjects.Tiles.COFFIN.get(), CoffinRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(WAObjects.EntityEntries.WEEPING_ANGEL.get(), WeepingAngelsRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(WAObjects.EntityEntries.ANOMALY.get(), AnomalyRender::new);
        RenderingRegistry.registerEntityRenderingHandler(WAObjects.EntityEntries.CHRONODYNE_GENERATOR.get(), (EntityRendererManager entityRendererManager) -> new CGRender(entityRendererManager, Minecraft.getInstance().getItemRenderer()));

        RenderTypeLookup.setRenderLayer(WAObjects.Blocks.SNOW_ANGEL.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(WAObjects.Blocks.PLINTH.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(WAObjects.Blocks.STATUE.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(WAObjects.Blocks.KONTRON_ORE.get(), RenderType.cutout());


        Map<String, PlayerRenderer> skinMap = Minecraft.getInstance().getEntityRenderDispatcher().getSkinMap();
        for (PlayerRenderer renderPlayer : skinMap.values()) {
            renderPlayer.addLayer(new WingsLayer(renderPlayer));
        }


        ItemModelsProperties.register(WAObjects.Items.TIMEY_WIMEY_DETECTOR.get(), new ResourceLocation("angle"), (itemStack, clientWorld, livingEntity) -> {
            if (livingEntity instanceof WeepingAngelEntity && AngelUtil.isHalloween()) {
                return 18;
            }
            return DetectorItem.getTime(itemStack);
        });

        ItemModelsProperties.register(WAObjects.Items.ANGEL_SPAWNER.get(), new ResourceLocation(WeepingAngels.MODID, "angel_type"), (itemStack, clientWorld, livingEntity) -> {
            if (itemStack == null || itemStack.isEmpty()) {
                return 0;
            }
            AngelType type = AngelSpawnerItem.getType(itemStack);
            return type.ordinal();
        });
    }

}
