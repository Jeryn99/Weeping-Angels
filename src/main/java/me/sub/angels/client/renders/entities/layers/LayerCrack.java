package me.sub.angels.client.renders.entities.layers;

import com.mojang.blaze3d.platform.GlStateManager;
import me.sub.angels.WeepingAngels;
import me.sub.angels.client.models.entity.ModelAngel;
import me.sub.angels.client.models.entity.ModelAngelEd;
import me.sub.angels.common.entities.EntityWeepingAngel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.Identifier;


@Environment(EnvType.CLIENT)
public class LayerCrack extends FeatureRenderer<EntityWeepingAngel, EntityModel<EntityWeepingAngel>> {

    private static final Identifier CRACK_TEX = new Identifier(WeepingAngels.MODID, "textures/entities/angel_crack.png");
    private static final Identifier CRACK_TEX_2 = new Identifier(WeepingAngels.MODID, "textures/entities/angel_2_cracked.png");
    private final FeatureRendererContext<EntityWeepingAngel, EntityModel<EntityWeepingAngel>> context;

    private final EntityModel<EntityWeepingAngel> modelOne = new ModelAngel();
    private final EntityModel<EntityWeepingAngel> modelTwo = new ModelAngelEd();
    private EntityModel<EntityWeepingAngel> modelMain = modelTwo;

    public LayerCrack(FeatureRendererContext<EntityWeepingAngel, EntityModel<EntityWeepingAngel>> featureRendererContext_1) {
        super(featureRendererContext_1);
        this.context = featureRendererContext_1;
    }

    @Override public void render(EntityWeepingAngel angel, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (angel.getAngelType() == 0) {
            modelMain = modelOne;
        }

        if (angel.getAngelType() == 1) {
            modelMain = modelTwo;
        }

        if (angel.getHealth() <= 5 && angel.getHealth() > 0 || angel.hurtTime > 0) {
            GlStateManager.pushMatrix();

            boolean flag = angel.isInvisible();
            GlStateManager.depthMask(!flag);
            if (!angel.isChild()) {
                if (angel.getAngelType() == 1) {
                    context.bindTexture(CRACK_TEX_2);
                } else {
                    context.bindTexture(CRACK_TEX);
                }

                modelMain.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            }
            GlStateManager.popMatrix();
        }
    }

    @Override public boolean method_4200()
    {
        return false;
    }
}
