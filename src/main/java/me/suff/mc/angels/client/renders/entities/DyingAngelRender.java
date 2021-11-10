package me.suff.mc.angels.client.renders.entities;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.entities.DyingAngel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

public class DyingAngelRender extends MobRenderer<DyingAngel, PlayerModel<DyingAngel>> implements EntityRendererProvider<DyingAngel> {

    public static final ResourceLocation ANGRY = new ResourceLocation(WeepingAngels.MODID, "textures/entities/disaster/dying/normal/normal_dying_angel_angry.png");


    public DyingAngelRender(Context context) {
        super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER_SLIM), true), 0.5F);
        addLayer(new ItemInHandLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(DyingAngel p_114482_) {
        return ANGRY;
    }

    @Override
    public EntityRenderer<DyingAngel> create(Context p_174010_) {
        return new DyingAngelRender(p_174010_);
    }
}
