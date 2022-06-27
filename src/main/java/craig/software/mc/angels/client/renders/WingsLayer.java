package craig.software.mc.angels.client.renders;

import com.mojang.blaze3d.vertex.PoseStack;
import craig.software.mc.angels.WeepingAngels;
import craig.software.mc.angels.client.models.entity.IAngelModel;
import craig.software.mc.angels.client.models.entity.WAModels;
import craig.software.mc.angels.client.poses.WeepingAngelPose;
import craig.software.mc.angels.common.entities.WeepingAngel;
import craig.software.mc.angels.common.variants.AngelTypes;
import craig.software.mc.angels.utils.ClientUtil;
import craig.software.mc.angels.utils.DateChecker;
import craig.software.mc.angels.utils.DonationUtil;
import craig.software.mc.angels.utils.PlayerUtil;
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

import java.util.Arrays;
import java.util.HashSet;

public class WingsLayer<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> extends RenderLayer<T, M> {

    protected static HashSet<Donator> modDonators = new HashSet<>();
    public final MercyWings mercyWings;
    private final ResourceLocation TEXTURE = new ResourceLocation(WeepingAngels.MODID, "textures/entities/mercy_wings.png");
    private EntityModel<WeepingAngel> model;

    public WingsLayer(RenderLayerParent<T, M> p_117346_) {
        super(p_117346_);
        Minecraft.getInstance().submitAsync(DateChecker.DONATOR_RUNNABLE);
        mercyWings = new MercyWings(Minecraft.getInstance().getEntityModels().bakeLayer(WAModels.MERCY_WINGS));
    }

    public static Donator shouldDisplay(Player player) {
        for (Donator person : modDonators) {
            if (player.getUUID().equals(person.getUuid()) && !player.isModelPartShown(PlayerModelPart.CAPE)) {
                return person;
            }
        }
        return null;
    }

    public static void update() {
        modDonators.addAll(DonationUtil.getDonators());
        WeepingAngels.LOGGER.debug("Updated Donators: " + modDonators);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource p_117350_, int p_117351_, T p_117352_, float p_117353_, float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) {
        if (p_117352_.isInvisibleTo(Minecraft.getInstance().player)) return;
        if (p_117352_ instanceof Player player && shouldDisplay(player) != null) {
            Donator data = shouldDisplay(player);
            poseStack.pushPose();
            if (data.getWings().equalsIgnoreCase("mercy")) {
                getParentModel().body.translateAndRotate(poseStack);
                mercyWings.setupAnim(player, 0, 0, 0, 0, 0);
                mercyWings.RWing.render(poseStack, p_117350_.getBuffer(RenderType.entityTranslucent(TEXTURE)), p_117351_, OverlayTexture.NO_OVERLAY);
                mercyWings.Lwing.render(poseStack, p_117350_.getBuffer(RenderType.entityTranslucent(TEXTURE)), p_117351_, OverlayTexture.NO_OVERLAY);
            } else {
                model = ClientUtil.getModelForAngel(data.getPureWings());
                if (model instanceof IAngelModel iAngelModel) {
                    getParentModel().body.translateAndRotate(poseStack);
                    iAngelModel.setAngelPose(data.isPerked() ? WeepingAngelPose.ANGRY : WeepingAngelPose.IDLE);
                    model.setupAnim(null, 0, 0, 0, 0, 0);
                    for (ModelPart wing : iAngelModel.wings(poseStack)) {
                        if (wing != null) {
                            wing.render(poseStack, p_117350_.getBuffer(RenderType.entityTranslucent(iAngelModel.generateTex(WeepingAngelPose.HIDING, AngelTypes.VARIANTS_REGISTRY.get().getValue(new ResourceLocation(data.getVariant()))))), p_117351_, OverlayTexture.NO_OVERLAY);
                        }
                    }
                }
            }
            poseStack.popPose();
        }
    }
}
