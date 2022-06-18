package craig.software.mc.angels.utils;

import craig.software.mc.angels.WeepingAngels;
import craig.software.mc.angels.client.models.entity.*;
import craig.software.mc.angels.client.renders.blockentities.CoffinRenderer;
import craig.software.mc.angels.client.renders.blockentities.PlinthRender;
import craig.software.mc.angels.client.renders.blockentities.SnowAngelRenderer;
import craig.software.mc.angels.client.renders.blockentities.StatueRender;
import craig.software.mc.angels.client.sounds.EchoSound;
import craig.software.mc.angels.common.WAObjects;
import craig.software.mc.angels.common.entities.AngelType;
import craig.software.mc.angels.common.entities.WeepingAngel;
import craig.software.mc.angels.common.items.AngelSpawnerItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class ClientUtil {


    public static final Map<AngelType, EntityModel<WeepingAngel>> MODEL_MAP = new HashMap<>();

    public static EntityModel<WeepingAngel> getModelForAngel(AngelType angelType) {
        if (MODEL_MAP.isEmpty()) {
            EntityModelSet theBakerMan = Minecraft.getInstance().getEntityModels();
            MODEL_MAP.put(AngelType.ED_ANGEL_CHILD, new ModelAngelChild(theBakerMan.bakeLayer(WAModels.ANGEL_CHERUB)));
            MODEL_MAP.put(AngelType.DISASTER_MC, new ModelDisasterAngel(theBakerMan.bakeLayer(WAModels.ANGEL_DISASTER))); //DISASTER
            MODEL_MAP.put(AngelType.ED, new ModelAngelEd(theBakerMan.bakeLayer(WAModels.ANGEL_ED))); //ED
            MODEL_MAP.put(AngelType.A_DIZZLE, new ModelClassicAngel(theBakerMan.bakeLayer(WAModels.ANGEL_CLASSIC))); //CLASSIC
            MODEL_MAP.put(AngelType.VILLAGER, new ModelWeepingVillager(theBakerMan.bakeLayer(WAModels.ANGEL_VILLAGER))); //DOC
            MODEL_MAP.put(AngelType.DYING, new ModelAplan(theBakerMan.bakeLayer(WAModels.DYING_ANGEL), true));
            MODEL_MAP.put(AngelType.DOCTOR, new ModelDoctorAngel(theBakerMan.bakeLayer(WAModels.DOCTOR_ANGEL)));
            MODEL_MAP.put(AngelType.SPARE_TIME, new ModelVAWeepingAngel(theBakerMan.bakeLayer(WAModels.ANGEL_SPARE)));
        }
        return MODEL_MAP.get(angelType);
    }

    @OnlyIn(Dist.CLIENT)
    public static void playSound(SoundEvent soundIn, float volumeSfx) {
        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(soundIn, volumeSfx));
    }

    public static void doClientStuff() {


        BlockEntityRenderers.register(WAObjects.Tiles.SNOW_ANGEL.get(), SnowAngelRenderer::new);
        BlockEntityRenderers.register(WAObjects.Tiles.PLINTH.get(), PlinthRender::new);
        BlockEntityRenderers.register(WAObjects.Tiles.STATUE.get(), StatueRender::new);
        BlockEntityRenderers.register(WAObjects.Tiles.COFFIN.get(), CoffinRenderer::new);

        ItemBlockRenderTypes.setRenderLayer(WAObjects.Blocks.SNOW_ANGEL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WAObjects.Blocks.PLINTH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WAObjects.Blocks.STATUE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WAObjects.Blocks.KONTRON_ORE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WAObjects.Blocks.KONTRON_ORE_DEEPSLATE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WAObjects.Blocks.CHRONODYNE_GENERATOR.get(), RenderType.cutout());

        ItemProperties.register(WAObjects.Items.TIMEY_WIMEY_DETECTOR.get(), new ResourceLocation("time"), new ClampedItemPropertyFunction() {
            private double rotation;
            private double rota;
            private long lastUpdateTick;

            public float unclampedCall(@NotNull ItemStack itemStack, @Nullable ClientLevel clientLevel, @Nullable LivingEntity livingEntity, int p_174668_) {
                Entity entity = livingEntity != null ? livingEntity : itemStack.getEntityRepresentation();

                if (entity instanceof WeepingAngel && AngelUtil.isHalloween()) {
                    return 18;
                }

                if (entity == null) {
                    return 0.0F;
                } else {
                    if (clientLevel == null && entity.level instanceof ClientLevel) {
                        clientLevel = (ClientLevel) entity.level;
                    }

                    if (clientLevel == null) {
                        return 0.0F;
                    } else {
                        double d0;
                        if (clientLevel.dimensionType().natural()) {
                            d0 = clientLevel.getTimeOfDay(1.0F);
                        } else {
                            d0 = Math.random();
                        }

                        d0 = this.wobble(clientLevel, d0);
                        return (float) d0;
                    }
                }
            }

            private double wobble(Level level, double p_117905_) {
                if (level.getGameTime() != this.lastUpdateTick) {
                    this.lastUpdateTick = level.getGameTime();
                    double d0 = p_117905_ - this.rotation;
                    d0 = Mth.positiveModulo(d0 + 0.5D, 1.0D) - 0.5D;
                    this.rota += d0 * 0.1D;
                    this.rota *= 0.9D;
                    this.rotation = Mth.positiveModulo(this.rotation + this.rota, 1.0D);
                }

                return this.rotation;
            }
        });

        ItemProperties.register(WAObjects.Items.ANGEL_SPAWNER.get(), new ResourceLocation(WeepingAngels.MODID, "angel_type"), (itemStack, clientWorld, livingEntity, something) -> {
            if (itemStack == null || itemStack.isEmpty()) {
                return 0;
            }

            AngelType type = AngelSpawnerItem.getType(itemStack);
            return type.ordinal();
        });

        EchoSound.addReloader();
    }

}
