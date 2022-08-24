package mc.craig.software.angels.client.render;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.client.models.ModelRegistration;
import mc.craig.software.angels.client.models.angel.AnomalyModel;
import mc.craig.software.angels.common.entity.anomaly.AnomalyEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class AnomalyRenderer extends LivingEntityRenderer<AnomalyEntity, AnomalyModel> {

    public AnomalyRenderer(EntityRendererProvider.Context context) {
        super(context, new AnomalyModel(context.bakeLayer(ModelRegistration.ANOMALY)), 0);
    }

    @Nullable
    @Override
    protected RenderType getRenderType(AnomalyEntity pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing) {
        return RenderType.entityTranslucentEmissive(getTextureLocation(pLivingEntity));
    }

    @Override
    public ResourceLocation getTextureLocation(AnomalyEntity pLivingEntity) {
        return new ResourceLocation(WeepingAngels.MODID, "textures/entity/anomaly/black_hole.png");
    }

    @Override
    protected boolean shouldShowName(AnomalyEntity pEntity) {
        return false;
    }
}


