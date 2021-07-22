package me.suff.mc.angels.client.renders.entities;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;

public class CGRender extends ThrownItemRenderer<ThrowableItemProjectile> {

    public CGRender(EntityRenderDispatcher renderManagerIn, ItemRenderer itemRendererIn) {
        super(renderManagerIn, itemRendererIn);
    }

}
