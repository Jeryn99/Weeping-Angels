package mc.craig.software.angels.client.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.client.models.MercyWingsModel;
import mc.craig.software.angels.client.models.ModelRegistration;
import mc.craig.software.angels.client.models.angel.AngelModel;
import mc.craig.software.angels.client.models.angel.WeepingAngelModel;
import mc.craig.software.angels.common.entity.angel.AngelEmotion;
import mc.craig.software.angels.donators.DonationChecker;
import mc.craig.software.angels.donators.Donator;
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

import java.util.HashSet;
import java.util.Optional;

public class DonationWingsLayer<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> extends RenderLayer<T, M> {

    protected static HashSet<Donator> modDonators = new HashSet<>();
    public final MercyWingsModel mercyWings;
    public final AngelModel angelModel;
    private final ResourceLocation TEXTURE = new ResourceLocation(WeepingAngels.MODID, "textures/entity/wings/mercy_wings.png");
    private final ResourceLocation NORMAL_WINGS = new ResourceLocation(WeepingAngels.MODID, "textures/entity/angel/alice/normal/normal_angel_" + AngelEmotion.ANGRY.getId() + ".png");
    private final ResourceLocation TEXTURE_LIGHTMAP = new ResourceLocation(WeepingAngels.MODID, "textures/entity/wings/mercy_wings_lightmap.png");

    public DonationWingsLayer(RenderLayerParent<T, M> renderLayerParent) {
        super(renderLayerParent);
        Minecraft.getInstance().submitAsync(DonationChecker.DONATOR_RUNNABLE);
        mercyWings = new MercyWingsModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelRegistration.MERCY_WINGS));
        angelModel = new WeepingAngelModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelRegistration.WEEPING_ANGEL));
    }

    public static Optional<Donator> getDonatorData(Player player) {
        for (Donator person : modDonators) {
            if (player.getUUID().equals(person.getUuid()) && !player.isModelPartShown(PlayerModelPart.CAPE)) {
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

                // tCups Mercy Wings
                if (data.getWings().equalsIgnoreCase("mercy")) {
                    poseStack.pushPose();
                    getParentModel().body.translateAndRotate(poseStack);
                    mercyWings.setupAnim(player, 0, 0, player.tickCount, 0, 0);
                    mercyWings.renderToBuffer(poseStack, multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(TEXTURE)), pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

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
                    wing.render(poseStack, multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(NORMAL_WINGS)), pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
                }
                poseStack.popPose();
            });
        }
    }
}