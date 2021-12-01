package me.suff.mc.angels.client.renders;

import com.mojang.blaze3d.vertex.PoseStack;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.client.models.entity.ModelDisasterAngel;
import me.suff.mc.angels.client.models.entity.WAModels;
import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.variants.AngelTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.PlayerModelPart;

import java.util.UUID;

public class WingsLayer<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> extends RenderLayer<T, M> {


    private static String[] people = new String[]{"bc8b891e-5c25-4c9f-ae61-cdfb270f1cc1", "96511168-1bb3-4ff0-a894-271e42606a39", "6e17cac4-6d28-48ca-a112-61f208fbdcd8", "bd049f17-7fdd-42aa-bd19-81a60d6b526b"};
    private final ModelDisasterAngel model;
    public final MercyWings mercyWings;
    private final ResourceLocation TEXTURE = new ResourceLocation(WeepingAngels.MODID, "textures/entities/mercy_wings.png");

    public WingsLayer(RenderLayerParent<T, M> p_117346_) {
        super(p_117346_);
        model = new ModelDisasterAngel(Minecraft.getInstance().getEntityModels().bakeLayer(WAModels.ANGEL_DISASTER));
        mercyWings = new MercyWings(Minecraft.getInstance().getEntityModels().bakeLayer(WAModels.MERCY_WINGS));
    }

    public static boolean shouldDisplay(Player player) {
        for (String person : people) {
            if (player.getUUID().equals(UUID.fromString(person)) && !player.isModelPartShown(PlayerModelPart.CAPE)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void render(PoseStack p_117349_, MultiBufferSource p_117350_, int p_117351_, T p_117352_, float p_117353_, float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) {
       if(p_117352_.isInvisibleTo(Minecraft.getInstance().player)) return;
        if (p_117352_ instanceof Player player && shouldDisplay(player)) {
            p_117349_.pushPose();
            if (!player.getUUID().equals(UUID.fromString("bd049f17-7fdd-42aa-bd19-81a60d6b526b"))) {
                getParentModel().body.translateAndRotate(p_117349_);
                model.leftWing.render(p_117349_, p_117350_.getBuffer(RenderType.entityTranslucent(model.generateTex(WeepingAngelPose.HIDING, AngelTypes.NORMAL.get()))), p_117351_, OverlayTexture.NO_OVERLAY);
                model.rightWing.render(p_117349_, p_117350_.getBuffer(RenderType.entityTranslucent(model.generateTex(WeepingAngelPose.HIDING, AngelTypes.NORMAL.get()))), p_117351_, OverlayTexture.NO_OVERLAY);
            } else {
                getParentModel().body.translateAndRotate(p_117349_);
                mercyWings.setupAnim(player, 0,0,0,0,0);
                mercyWings.RWing.render(p_117349_, p_117350_.getBuffer(RenderType.entityTranslucent(TEXTURE)), p_117351_, OverlayTexture.NO_OVERLAY);
                mercyWings.Lwing.render(p_117349_, p_117350_.getBuffer(RenderType.entityTranslucent(TEXTURE)), p_117351_, OverlayTexture.NO_OVERLAY);
            }
            p_117349_.popPose();
        }
    }
}
