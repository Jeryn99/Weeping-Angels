package me.sub.angels.client.render.entity;

import me.sub.angels.client.models.entity.ModelCG;
import me.sub.angels.common.entities.EntityChronodyneGenerator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import org.lwjgl.opengl.GL11;

public class RenderCG extends RenderSnowball<EntityChronodyneGenerator> {

    public RenderCG(Item itemIn) {
        super(Minecraft.getMinecraft().getRenderManager(), itemIn, null);
    }

    private ModelCG model = new ModelCG();

    @Override
    public void doRender(EntityChronodyneGenerator e, double par2, double par4, double par6, float par8, float par9) {
        GL11.glPushMatrix();
        model.renderModel(par9);
        GL11.glPopMatrix();
    }

}
