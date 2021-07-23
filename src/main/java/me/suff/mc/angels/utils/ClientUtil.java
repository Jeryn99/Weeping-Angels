package me.suff.mc.angels.utils;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.client.models.entity.*;
import me.suff.mc.angels.client.renders.entities.AngelRender;
import me.suff.mc.angels.client.renders.entities.AnomalyRender;
import me.suff.mc.angels.client.renders.entities.CGRender;
import me.suff.mc.angels.client.renders.tileentities.CoffinRenderer;
import me.suff.mc.angels.client.renders.tileentities.PlinthTileRender;
import me.suff.mc.angels.client.renders.tileentities.SnowArmTileRender;
import me.suff.mc.angels.client.renders.tileentities.StatueRender;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.entities.AngelEnums;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import me.suff.mc.angels.common.items.AngelSpawnerItem;
import me.suff.mc.angels.common.items.DetectorItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.HashMap;
import java.util.Map;

public class ClientUtil {


    public static final Map<AngelEnums.AngelType, EntityModel<WeepingAngelEntity>> MODEL_MAP = new HashMap<>();

    public static EntityModel<WeepingAngelEntity> getModelForAngel(AngelEnums.AngelType angelType) {
        if(MODEL_MAP.isEmpty()){
            MODEL_MAP.put(AngelEnums.AngelType.CHERUB, new ModelAngelChild(Minecraft.getInstance().getEntityModels().bakeLayer(WAModels.ANGEL_CHERUB)));
            MODEL_MAP.put(AngelEnums.AngelType.ANGELA_MC, new ModelAngelaAngel(Minecraft.getInstance().getEntityModels().bakeLayer(WAModels.ANGEL_ANGELA))); //ANGELA
            MODEL_MAP.put(AngelEnums.AngelType.ED, new ModelAngelEd(Minecraft.getInstance().getEntityModels().bakeLayer(WAModels.ANGEL_ED))); //ED
            MODEL_MAP.put(AngelEnums.AngelType.A_DIZZLE, new ModelClassicAngel(Minecraft.getInstance().getEntityModels().bakeLayer(WAModels.ANGEL_CLASSIC))); //CLASSIC
            MODEL_MAP.put(AngelEnums.AngelType.VILLAGER, new ModelWeepingVillager(Minecraft.getInstance().getEntityModels().bakeLayer(WAModels.ANGEL_VILLAGER))); //DOC

        }
        return MODEL_MAP.get(angelType);
    }

    @OnlyIn(Dist.CLIENT)
    public static void playSound(SoundEvent soundIn, float volumeSfx) {
        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(soundIn, volumeSfx));
    }

    public static void doClientStuff() {

        WAModels.init();

        BlockEntityRenderers.register(WAObjects.Tiles.SNOW_ANGEL.get(), SnowArmTileRender::new);
        BlockEntityRenderers.register(WAObjects.Tiles.PLINTH.get(), PlinthTileRender::new);
        BlockEntityRenderers.register(WAObjects.Tiles.STATUE.get(), StatueRender::new);
        BlockEntityRenderers.register(WAObjects.Tiles.COFFIN.get(), CoffinRenderer::new);

        EntityRenderers.register(WAObjects.EntityEntries.WEEPING_ANGEL.get(), AngelRender::new);
        EntityRenderers.register(WAObjects.EntityEntries.ANOMALY.get(), AnomalyRender::new);
        EntityRenderers.register(WAObjects.EntityEntries.CHRONODYNE_GENERATOR.get(), CGRender::new);

        ItemBlockRenderTypes.setRenderLayer(WAObjects.Blocks.SNOW_ANGEL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WAObjects.Blocks.PLINTH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WAObjects.Blocks.STATUE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WAObjects.Blocks.KONTRON_ORE.get(), RenderType.cutout());

        ItemProperties.register(WAObjects.Items.TIMEY_WIMEY_DETECTOR.get(), new ResourceLocation("angle"), (itemStack, clientLevel, livingEntity, p_174679_) -> DetectorItem.getTime(itemStack));

        ItemProperties.register(WAObjects.Items.ANGEL_SPAWNER.get(), new ResourceLocation(WeepingAngels.MODID, "angel_type"), (itemStack, clientWorld, livingEntity, something) -> {
            if (itemStack == null || itemStack.isEmpty()) {
                return 0;
            }
            AngelEnums.AngelType type = AngelSpawnerItem.getType(itemStack);
            return type.ordinal();
        });
    }

}
