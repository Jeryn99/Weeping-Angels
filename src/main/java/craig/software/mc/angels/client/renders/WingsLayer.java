package craig.software.mc.angels.client.renders;

import com.mojang.blaze3d.vertex.PoseStack;
import craig.software.mc.angels.WeepingAngels;
import craig.software.mc.angels.client.models.entity.IAngelModel;
import craig.software.mc.angels.client.models.entity.WAModels;
import craig.software.mc.angels.client.poses.WeepingAngelPose;
import craig.software.mc.angels.common.entities.WeepingAngel;
import craig.software.mc.angels.common.variants.AngelVariants;
import craig.software.mc.angels.utils.ClientUtil;
import craig.software.mc.angels.utils.DateChecker;
import craig.software.mc.angels.utils.DonationUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
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

public class WingsLayer<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> extends RenderLayer<T, M> {

    protected static HashSet<Donator> modDonators = new HashSet<>();
    public final MercyWings mercyWings;
    private final ResourceLocation TEXTURE = new ResourceLocation(WeepingAngels.MODID, "textures/entities/mercy_wings.png");
    private final ResourceLocation TEXTURE_LIGHTMAP = new ResourceLocation(WeepingAngels.MODID, "textures/entities/mercy_wings_lightmap.png");
    private EntityModel<WeepingAngel> model;

    public WingsLayer(RenderLayerParent<T, M> renderLayerParent) {
        super(renderLayerParent);
        Minecraft.getInstance().submitAsync(DateChecker.DONATOR_RUNNABLE);
        mercyWings = new MercyWings(Minecraft.getInstance().getEntityModels().bakeLayer(WAModels.MERCY_WINGS));
    }

    public static Optional<Donator> getDonatorData(Player player) {
        for (Donator person : modDonators) {
            if (player.getUUID().equals(person.getUuid()) && !player.isModelPartShown(PlayerModelPart.CAPE)) {
                return Optional.of(person);
            }
        }
        return Optional.empty();
    }

    public static void update() {
        modDonators.addAll(DonationUtil.getDonators());
        WeepingAngels.LOGGER.debug("Updated Donators: " + modDonators);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource multiBufferSource, int p_117351_, T p_117352_, float p_117353_, float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) {
        if (p_117352_.isInvisibleTo(Minecraft.getInstance().player)) return;
        if (p_117352_ instanceof Player player) {
            getDonatorData(player).ifPresent(data -> {
                poseStack.pushPose();
                if (data.getWings().equalsIgnoreCase("mercy") || true) {
                    getParentModel().body.translateAndRotate(poseStack);
                    mercyWings.setupAnim(player, 0, 0, player.tickCount, 0, 0);
                    mercyWings.renderToBuffer(poseStack, multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(TEXTURE)), p_117351_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

                    mercyWings.setupAnim(player, 0, 0, player.tickCount, 0, 0);
                    mercyWings.renderToBuffer(poseStack, multiBufferSource.getBuffer(RenderType.eyes(TEXTURE_LIGHTMAP)), 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 0.5F);

                } else {
                    model = ClientUtil.getModelForAngel(data.getPureWings());
                    if (model instanceof IAngelModel iAngelModel) {
                        getParentModel().body.translateAndRotate(poseStack);
                        iAngelModel.setAngelPose(data.isPerked() ? WeepingAngelPose.ANGRY : WeepingAngelPose.IDLE);
                        model.setupAnim(null, 0, 0, 0, 0, 0);
                        for (ModelPart wing : iAngelModel.wings(poseStack)) {
                            if (wing != null) {
                                wing.render(poseStack, multiBufferSource.getBuffer(RenderType.entityTranslucent(iAngelModel.generateTex(WeepingAngelPose.HIDING, AngelVariants.VARIANTS_REGISTRY.get().getValue(new ResourceLocation(data.getVariant()))))), p_117351_, OverlayTexture.NO_OVERLAY);
                            }
                        }
                    }
                }
                poseStack.popPose();
            });
        }
    }
}
