package me.suff.mc.angels.compat.tardis;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.tileentities.CoffinTile;
import me.suff.mc.angels.utils.TextureUtil;
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
