package mc.craig.software.angels.client.renders.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.client.models.entity.IAngelModel;
import mc.craig.software.angels.client.poses.WeepingAngelPose;
import mc.craig.software.angels.common.entities.AngelType;
import mc.craig.software.angels.common.entities.WeepingAngelEntity;
import mc.craig.software.angels.common.variants.AngelVariants;
import mc.craig.software.angels.utils.ClientUtil;
import mc.craig.software.angels.utils.DateChecker;
import mc.craig.software.angels.utils.DonationUtil;
import mc.craig.software.angels.utils.Donator;
import java.util.HashSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerModelPart;
import net.minecraft.util.ResourceLocation;

public class WingsLayer extends LayerRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> {

    protected static HashSet<Donator> modDonators = new HashSet<>();

    public WingsLayer(IEntityRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> renderer) {
        super(renderer);
        Minecraft.getInstance().submitAsync(DateChecker.DONATOR_RUNNABLE);
    }

    public static void update() {
        modDonators.addAll(DonationUtil.getDonators());
        WeepingAngels.LOGGER.debug("Updated Donators: " + modDonators);
    }

    public static Donator shouldDisplay(PlayerEntity player) {
        for (Donator person : modDonators) {
            if (player.getUUID().equals(person.getUuid()) && !player.isModelPartShown(PlayerModelPart.CAPE)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int lighting, AbstractClientPlayerEntity playerEntity, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
        if (!playerEntity.isInvisible() && shouldDisplay(playerEntity) != null) {
            Donator data = shouldDisplay(playerEntity);
            if (data.getWings().equalsIgnoreCase("Mercy")) {
                data.setWings(AngelType.DISASTER_MC.name());
            }
            EntityModel<WeepingAngelEntity> model = ClientUtil.getModelForAngel(data.getPureWings());
            if (model instanceof IAngelModel) {
                IAngelModel iAngelModel = (IAngelModel) model;
                getParentModel().body.translateAndRotate(matrixStack);
                iAngelModel.setAngelPose(data.isPerked() ? WeepingAngelPose.ANGRY : WeepingAngelPose.IDLE);
                model.setupAnim(null, 0, 0, 0, 0, 0);
                for (ModelRenderer wing : iAngelModel.wings(matrixStack)) {
                    if (wing != null) {
                        wing.render(matrixStack, buffer.getBuffer(RenderType.entityTranslucent(iAngelModel.generateTex(WeepingAngelPose.HIDING, AngelVariants.VARIANTS_REGISTRY.get().getValue(new ResourceLocation(data.getVariant()))))), lighting, OverlayTexture.NO_OVERLAY);
                    }
                }
            }
        }
    }
}
