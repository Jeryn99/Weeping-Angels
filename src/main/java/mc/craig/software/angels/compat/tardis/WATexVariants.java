package mc.craig.software.angels.compat.tardis;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.tileentities.CoffinTile;
import mc.craig.software.angels.utils.TextureUtil;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.misc.TexVariant;

public class WATexVariants {
    public static final ResourceLocation BLU = new ResourceLocation(WeepingAngels.MODID, "textures/exteriors/ab_door_blue.png");
    public static final ResourceLocation YELLOW = new ResourceLocation(WeepingAngels.MODID, "textures/exteriors/ab_door_yellow.png");
    public static final ResourceLocation YELLOW_DARKER = new ResourceLocation(WeepingAngels.MODID, "textures/exteriors/ab_door_yellow_darker.png");
    public static final ResourceLocation WAR = new ResourceLocation(WeepingAngels.MODID, "textures/exteriors/ab_door_war.png");

    public static final TexVariant[] PTB = {
            new TexVariant(TextureUtil.getCoffinTexture(CoffinTile.Coffin.PTB), "blue_windows").addInteriorDoorVariant(BLU),
            new TexVariant(TextureUtil.getCoffinTexture(CoffinTile.Coffin.PTB_2), "yellow_windows").addInteriorDoorVariant(YELLOW),
            new TexVariant(TextureUtil.getCoffinTexture(CoffinTile.Coffin.PTB_3), "battle").addInteriorDoorVariant(WAR),
            new TexVariant(TextureUtil.getCoffinTexture(CoffinTile.Coffin.PTB_4), "damaged").addInteriorDoorVariant(WAR),
            new TexVariant(TextureUtil.getCoffinTexture(CoffinTile.Coffin.PTB_5), "yellow_darker").addInteriorDoorVariant(YELLOW_DARKER)
    };
}
