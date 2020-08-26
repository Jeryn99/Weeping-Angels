package me.swirtzly.minecraft.angels.client.renders.entities;

import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.entity.projectile.ProjectileItemEntity;

public class CGRender extends SpriteRenderer<ProjectileItemEntity> {

    public CGRender(EntityRendererManager renderManagerIn, ItemRenderer itemRendererIn) {
        super(renderManagerIn, itemRendererIn);
    }

}
