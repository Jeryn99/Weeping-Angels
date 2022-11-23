package mc.craig.software.angels.client.render.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import mc.craig.software.angels.WAConfiguration;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.client.models.ModelRegistration;
import mc.craig.software.angels.client.models.entity.SantaHat;
import mc.craig.software.angels.client.models.entity.angel.AngelModel;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import mc.craig.software.angels.common.entity.angel.ai.AngelVariant;
import mc.craig.software.angels.donators.DonationChecker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class SeasonalLayer extends RenderLayer<WeepingAngel, AngelModel> {

    public static final ResourceLocation SANTA = new ResourceLocation(WeepingAngels.MODID, "textures/entity/wearables/santa_hat.png");
    private final SantaHat<Entity> model;

    public SeasonalLayer(RenderLayerParent<WeepingAngel, AngelModel> renderer) {
        super(renderer);
        model = new SantaHat<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelRegistration.SANTA_HAT));
    }

    public static void santaHat(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, SantaHat<Entity> santa, EntityModel model, AngelVariant angelVariant) {
        if (angelVariant == AngelVariant.RUSTED_NO_HEAD) return;
        if (model instanceof AngelModel angelModel) {
            if (DonationChecker.isXmas()) {
                pMatrixStack.pushPose();
                angelModel.getHead().translateAndRotate(pMatrixStack);
              /*  santa.head.x = ((AngelModel) model).getHead().x;
                santa.head.y = ((AngelModel) model).getHead().y + 18;
                santa.head.z = ((AngelModel) model).getHead().z - 10*/;

                santa.renderToBuffer(pMatrixStack, pBuffer.getBuffer(RenderType.entityTranslucent(SANTA)), pPackedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
                pMatrixStack.popPose();
            }
        }
    }

    @Override
    public void render(@NotNull PoseStack pMatrixStack, @NotNull MultiBufferSource pBuffer, int pPackedLight, WeepingAngel pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        if (!pLivingEntity.isInvisible()) {
            //SANTA
            santaHat(pMatrixStack, pBuffer, pPackedLight, model, getParentModel(), pLivingEntity.getVariant());
        }
    }

}