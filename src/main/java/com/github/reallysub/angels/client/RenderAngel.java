package com.github.reallysub.angels.client;

import com.github.reallysub.angels.WeepingAngels;
import com.github.reallysub.angels.client.models.ModelAngel;
import com.github.reallysub.angels.common.entities.EntityAngel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderAngel extends RenderLiving<EntityAngel> {

    ResourceLocation TEX = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel.png");

    public RenderAngel() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelAngel(), 1.0F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     *
     * @param entity
     */
    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityAngel entity) {
        return null;
    }
}
