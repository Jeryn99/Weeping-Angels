package me.sub.angels.client.render.entity;

import me.sub.angels.common.entities.EntityChronodyneGenerator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;

public class RenderCG extends RenderSnowball<EntityChronodyneGenerator> {

    public RenderCG(RenderManager renderManagerIn, Item itemIn) {
        super(renderManagerIn, itemIn, null);
    }

}
