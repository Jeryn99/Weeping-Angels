package craig.software.mc.angels.utils;

import craig.software.mc.angels.WeepingAngels;
import craig.software.mc.angels.common.tileentities.CoffinTile;
import net.minecraft.util.ResourceLocation;

public class TextureUtil {

    public static ResourceLocation getCoffinTexture(CoffinTile.Coffin coffin) {
        return new ResourceLocation(WeepingAngels.MODID, "textures/tiles/coffin/" + coffin.name().toLowerCase() + ".png");
    }
}
