package me.suff.mc.angels.client.renders.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import me.suff.mc.angels.client.models.entity.IAngelModel;
import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.entities.AngelType;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import me.suff.mc.angels.common.variants.AngelVariants;
import me.suff.mc.angels.utils.ClientUtil;
import me.suff.mc.angels.utils.DateChecker;
import me.suff.mc.angels.utils.Donator;
import me.suff.mc.angels.utils.PlayerUtil;
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

import java.util.UUID;

public class WingsLayer extends LayerRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> {

    protected static Donator[] people = new Donator[0];

    public WingsLayer(IEntityRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> p_i50926_1_) {
        super(p_i50926_1_);
        DateChecker.update(true);
    }

    public static Donator[] getPeople() {
        return people;
    }

    public static void update() {
        people = PlayerUtil.getDonators();
    }

    public static Donator shouldDisplay(PlayerEntity player) {
        for (Donator person : people) {
            if (player.getUUID().equals(UUID.fromString(person.getUuid())) && !player.isModelPartShown(PlayerModelPart.CAPE)) {
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
