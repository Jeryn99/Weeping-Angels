package me.suff.mc.angels.utils;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.tileentities.CoffinTile;
import net.minecraft.util.ResourceLocation;

public class TextureUtil {

    public static ResourceLocation getCoffinTexture(CoffinTile.Coffin coffin) {
        return new ResourceLocation(WeepingAngels.MODID, "textures/tiles/coffin/" + coffin.name().toLowerCase() + ".png");
    }
}
