package me.suff.mc.angels.common.objects;

import me.suff.mc.angels.util.Constants;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/* Created by Craig on 18/02/2021 */
public class WABlocks {


    public static final Block KONTRON_ORE = makeBlock(new Block(FabricBlockSettings.of(Material.STONE)), "kontron_ore");


    public static Block makeBlock(Block block, String name) {
        return Registry.register(Registry.BLOCK, new Identifier(Constants.MODID, name), block);
    }

}
