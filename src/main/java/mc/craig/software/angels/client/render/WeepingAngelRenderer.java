package mc.craig.software.angels.client.render;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.client.models.ModelRegistration;
import mc.craig.software.angels.client.models.angel.WeepingAngelModel;
import mc.craig.software.angels.common.entity.WeepingAngel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;

public class WeepingAngelRenderer extends LivingEntityRenderer<WeepingAngel, WeepingAngelModel> {

    public WeepingAngelRenderer(EntityRendererProvider.Context context) {
        super(context, new WeepingAngelModel(context.bakeLayer(ModelRegistration.WEEPING_ANGEL)), 0F);
    }

    @Override
    public ResourceLocation getTextureLocation(WeepingAngel weepingAngel) {
        return new ResourceLocation(WeepingAngels.MODID, "textures/entity/angel/alice/alice.png");
    }

    @Override
    protected boolean shouldShowName(WeepingAngel p_115333_) {
        return false;
    }
}
