package craig.software.mc.angels.client.renders.entities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3d;
import craig.software.mc.angels.WeepingAngels;
import craig.software.mc.angels.client.models.entity.HeadPlacement;
import craig.software.mc.angels.client.models.entity.IAngelModel;
import craig.software.mc.angels.client.models.entity.SantaHat;
import craig.software.mc.angels.client.models.entity.WAModels;
import craig.software.mc.angels.common.entities.WeepingAngel;
import craig.software.mc.angels.common.variants.AngelVariant;
import craig.software.mc.angels.config.WAConfig;
import craig.software.mc.angels.utils.DateChecker;
import craig.software.mc.angels.utils.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class SeasonalLayer extends RenderLayer<WeepingAngel, EntityModel<WeepingAngel>> {

    public static final ResourceLocation SANTA = new ResourceLocation(WeepingAngels.MODID, "textures/entities/santa_hat.png");
    private final SantaHat<Entity> model;

    public SeasonalLayer(RenderLayerParent<WeepingAngel, EntityModel<WeepingAngel>> renderer) {
        super(renderer);
        model = new SantaHat<>(Minecraft.getInstance().getEntityModels().bakeLayer(WAModels.SANTA_HAT));
    }

    public static void santaHat(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, EntityModel santa, EntityModel model, AngelVariant angelVariant) {
        if (angelVariant.isHeadless()) return;
        if (model instanceof IAngelModel iAngelModel) {
            Pair<ModelPart, Vector3d> santaHead = iAngelModel.getHeadData(HeadPlacement.SANTA);
            if (DateChecker.isXmas() && santaHead != null && WAConfig.CONFIG.showSantaHatsAtXmas.get()) {
                pMatrixStack.pushPose();
                santaHead.getFirst().translateAndRotate(pMatrixStack);
                pMatrixStack.translate(santaHead.getSecond().x, santaHead.getSecond().y, santaHead.getSecond().z);
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