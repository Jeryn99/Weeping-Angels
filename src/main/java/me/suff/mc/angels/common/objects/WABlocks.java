package me.suff.mc.angels.common.objects;

import me.suff.mc.angels.common.block.CoffinBlock;
import me.suff.mc.angels.common.block.PlinthBlock;
import me.suff.mc.angels.common.block.StatueBlock;
import me.suff.mc.angels.common.block.WAOreBlock;
import me.suff.mc.angels.util.Constants;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/* Created by Craig on 18/02/2021 */
public class WABlocks {

    public static Block KONTRON_ORE, COFFIN, PLINTH, STATUE = null;


    public static Block makeBlock(Block block, String name) {
        Block regBlock = Registry.register(Registry.BLOCK, new Identifier(Constants.MODID, name), block);
        Registry.register(Registry.ITEM, new Identifier(Constants.MODID, name), new BlockItem(block, new Item.Settings().group(WAItems.ITEM_GROUP)));
        return regBlock;
    }


    public static void init() {
        KONTRON_ORE = makeBlock(new WAOreBlock(FabricBlockSettings.of(Material.STONE).strength(3.0F, 3.0F).requiresTool()), "kontron_ore");
        COFFIN = makeBlock(new CoffinBlock(FabricBlockSettings.of(Material.WOOD).nonOpaque().hardness(4.0F)), "coffin");
        PLINTH = makeBlock(new PlinthBlock(FabricBlockSettings.of(Material.STONE).nonOpaque().hardness(4.0F)), "plinth");
        STATUE = makeBlock(new StatueBlock(FabricBlockSettings.of(Material.STONE).nonOpaque().hardness(4.0F)), "statue");
    }

}
