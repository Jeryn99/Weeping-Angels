package me.swirtzly.angels.client.renders.entities;

import me.swirtzly.angels.WeepingAngels;
import me.swirtzly.angels.client.models.entity.*;
import me.swirtzly.angels.client.renders.entities.layers.LayerCrack;
import me.swirtzly.angels.common.entities.EntityWeepingAngel;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;

public class RenderWeepingAngel extends RenderLiving<EntityWeepingAngel> {

    public static ResourceLocation TEXTURE_FOUR = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_4.png");
    private ResourceLocation TEXTURE_ONE = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel.png");
    private ResourceLocation TEXTURE_TWO = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_2.png");

    private ResourceLocation TEXTURE_CLASSIC = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_3.png");

    private ResourceLocation TEXTURE_CHILD = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_child.png");

    private ModelBase modelOne = new ModelAngel();
    private ModelBase modelTwo = new ModelAngelEd();
    private ModelBase modelChild = new ModelAngelChild();
    private ModelBase modelClassic = new ModelClassicAngel();
    private ModelBase modelMel = new ModelAngelMel();

    public RenderWeepingAngel(RenderManager manager) {
        super(manager, new ModelAngelEd(), 0.0F);
        mainModel = modelTwo;
        addLayer(new LayerCrack(this));
        addLayer(new LayerHeldItem(this));
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityWeepingAngel entity) {
        return null;
    }

    @Override
    protected boolean setBrightness(EntityWeepingAngel angel, float partialTicks, boolean combineTextures) {
        return true;
    }

    /**
     * Renders the model in RenderLiving
     */
    @Override
    protected void renderModel(EntityWeepingAngel angel, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        if (angel.isDead) return;

        GlStateManager.pushMatrix();
        RenderHelper.enableStandardItemLighting();

        switch (angel.getType()) {
            case -1:
                bindTexture(TEXTURE_CHILD);
                modelChild.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                break;
            case 0:
                bindTexture(TEXTURE_ONE);
                modelOne.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                break;
            case 1:
                bindTexture(TEXTURE_TWO);
                modelTwo.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                break;
            case 2:
                bindTexture(TEXTURE_CLASSIC);
                modelClassic.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                break;
            case 3:
                bindTexture(TEXTURE_FOUR);
                modelMel.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                break;
        }

        RenderHelper.disableStandardItemLighting();
        GlStateManager.popMatrix();
    }
}
