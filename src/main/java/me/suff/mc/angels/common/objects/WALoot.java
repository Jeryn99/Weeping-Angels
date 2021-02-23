package me.suff.mc.angels.common.objects;

import me.suff.mc.angels.util.Constants;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

/* Created by Craig on 23/02/2021 */
public class WALoot {
    public static Identifier CRYPT_LOOT = new Identifier(Constants.MODID, "chests/crypt");
    public static Identifier CATACOMB_LOOT = new Identifier(Constants.MODID, "chests/catacombs");

    public static final Tag< Block > POTTED_PLANETS = TagRegistry.block(new Identifier(Constants.MODID, "grave_plants"));

}
