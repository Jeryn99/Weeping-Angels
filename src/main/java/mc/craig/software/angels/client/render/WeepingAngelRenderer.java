package mc.craig.software.angels.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.client.models.ModelRegistration;
import mc.craig.software.angels.client.models.angel.WeepingAngelModel;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class WeepingAngelRenderer extends LivingEntityRenderer<WeepingAngel, WeepingAngelModel> {

    public WeepingAngelRenderer(EntityRendererProvider.Context context) {
        super(context, new WeepingAngelModel(context.bakeLayer(ModelRegistration.WEEPING_ANGEL)), 0F);
        this.addLayer(new ItemInHandLayer(this, context.getItemInHandRenderer()));
    }

    @Override
    public void render(WeepingAngel pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    @Nullable
    @Override
    protected RenderType getRenderType(WeepingAngel pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing) {
        return RenderType.entityTranslucentCull(getTextureLocation(pLivingEntity));
    }

    @Override
    protected void setupRotations(WeepingAngel pEntityLiving, PoseStack pMatrixStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        if (pEntityLiving.deathTime < 0) return;
        super.setupRotations(pEntityLiving, pMatrixStack, pAgeInTicks, pRotationYaw, pPartialTicks);
    }

    @Override
    public ResourceLocation getTextureLocation(WeepingAngel weepingAngel) {
        return new ResourceLocation(WeepingAngels.MODID, "textures/entity/angel/alice/normal/normal_angel_" + weepingAngel.getEmotion().getId() + ".png");
    }

    @Override
    protected boolean shouldShowName(WeepingAngel p_115333_) {
        return false;
    }
}
