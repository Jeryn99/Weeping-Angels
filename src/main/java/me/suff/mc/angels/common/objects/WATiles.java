package me.suff.mc.angels.common.objects;

import me.suff.mc.angels.common.blockentity.CoffinTile;
import me.suff.mc.angels.common.blockentity.PlinthTile;
import me.suff.mc.angels.common.blockentity.StatueTile;
import me.suff.mc.angels.util.Constants;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/* Created by Craig on 19/02/2021 */
public class WATiles {

    public static BlockEntityType< StatueTile > STATUE_TILE;
    public static BlockEntityType< PlinthTile > PLINTH_TILE;
    public static BlockEntityType< CoffinTile > COFFIN_TILE;

    public static void init() {
        STATUE_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Constants.MODID, "statue"), FabricBlockEntityTypeBuilder.create(StatueTile::new, WABlocks.STATUE).build(null));
        PLINTH_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Constants.MODID, "plinth"), FabricBlockEntityTypeBuilder.create(PlinthTile::new, WABlocks.PLINTH).build());
        COFFIN_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Constants.MODID, "coffin"), FabricBlockEntityTypeBuilder.create(CoffinTile::new, WABlocks.COFFIN).build());
    }

}
