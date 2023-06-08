package mc.craig.software.angels.client.renders.entities;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;

public class CGRender extends ThrownItemRenderer<ThrowableItemProjectile> {

    public CGRender(EntityRendererProvider.Context p_174416_, float p_174417_, boolean p_174418_) {
        super(p_174416_, p_174417_, p_174418_);
    }

    public CGRender(EntityRendererProvider.Context p_174414_) {
        super(p_174414_);
    }
}
