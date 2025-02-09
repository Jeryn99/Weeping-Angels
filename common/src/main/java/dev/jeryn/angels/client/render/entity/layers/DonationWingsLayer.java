package dev.jeryn.angels.client.render.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.jeryn.angels.WeepingAngels;
import dev.jeryn.angels.client.models.MercyWingsModel;
import dev.jeryn.angels.client.models.ModelRegistration;
import dev.jeryn.angels.client.models.entity.angel.AliceAngelModel;
import dev.jeryn.angels.client.models.entity.angel.AngelModel;
import dev.jeryn.angels.common.entity.angel.ai.AngelEmotion;
import dev.jeryn.angels.common.entity.angel.ai.AngelVariant;
import dev.jeryn.angels.donators.DonationChecker;
import dev.jeryn.angels.donators.Donator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.PlayerModelPart;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

public class DonationWingsLayer<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> extends RenderLayer<T, M> {
    public final MercyWingsModel mercyWings;
    public final AngelModel angelModel;
    private final ResourceLocation TEXTURE_LIGHTMAP = new ResourceLocation(WeepingAngels.MODID, "textures/entity/wings/mercy_wings_lightmap.png");

    public DonationWingsLayer(RenderLayerParent<T, M> renderLayerParent) {
        super(renderLayerParent);
        mercyWings = new MercyWingsModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelRegistration.MERCY_WINGS));
        angelModel = new AliceAngelModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelRegistration.ALICE_ANGEL));
    }

    public static Optional<Donator> getDonatorData(Player player) {
        for (Donator person : DonationChecker.getModDonators()) {
            if (player.getStringUUID().equals(person.getUuid())) {
                return Optional.of(person);
            }
        }
        return Optional.empty();
    }

    public static Optional<Donator> getDonatorData(UUID uuid) {
        for (Donator person : DonationChecker.getModDonators()) {
            if (uuid.toString().equals(person.getUuid())) {
                return Optional.of(person);
            }
        }
        return Optional.empty();
    }


    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource multiBufferSource, int pPackedLight, T pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        if (pLivingEntity.isInvisibleTo(Minecraft.getInstance().player)) return;
        if (pLivingEntity instanceof Player player) {
            getDonatorData(player).ifPresent(data -> {


                if (player.isModelPartShown(PlayerModelPart.CAPE)) return;

                // tCups Mercy Wings
                if (data.getWings().equalsIgnoreCase("mercy")) {
                    poseStack.pushPose();
                    getParentModel().body.translateAndRotate(poseStack);
                    mercyWings.setupAnim(player, 0, 0, player.tickCount, 0, 0);
                    mercyWings.renderToBuffer(poseStack, multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(mercyWings.texture(AngelEmotion.IDLE, AngelVariant.STONE))), pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

                    mercyWings.setupAnim(player, 0, 0, player.tickCount, 0, 0);
                    mercyWings.renderToBuffer(poseStack, multiBufferSource.getBuffer(RenderType.eyes(TEXTURE_LIGHTMAP)), 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 0.5F);
                    poseStack.popPose();
                    return;
                }

                // Normal Wings
                poseStack.pushPose();
                getParentModel().body.translateAndRotate(poseStack);
                poseStack.translate(0, 0.5, 0);
                for (ModelPart wing : angelModel.getWings()) {
                    wing.render(poseStack, multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(angelModel.texture(AngelEmotion.IDLE, AngelVariant.getVariant(new ResourceLocation(data.getVariant()))))), pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
                }
                poseStack.popPose();
            });
        }
    }
}