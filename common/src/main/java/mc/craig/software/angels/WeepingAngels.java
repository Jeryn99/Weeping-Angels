package mc.craig.software.angels;

import com.mojang.logging.LogUtils;
import mc.craig.software.angels.common.WAEntities;
import mc.craig.software.angels.common.WASounds;
import mc.craig.software.angels.common.blockentity.WABlockEntities;
import mc.craig.software.angels.common.blocks.WABlocks;
import mc.craig.software.angels.common.entity.angel.AngelVariant;
import mc.craig.software.angels.common.entity.angel.BlockReactions;
import mc.craig.software.angels.common.items.WAItems;
import mc.craig.software.angels.common.level.structures.WAStructures;
import org.slf4j.Logger;

public class WeepingAngels {

    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "weeping_angels";

    public static void init() {
        WAItems.ITEMS.register();
        WASounds.SOUNDS.register();
        WABlocks.BLOCKS.register();
        WAEntities.ENTITY_TYPES.register();
        WABlockEntities.BLOCK_ENTITY_TYPES.register();
        WAStructures.STRUCTURES.register();
        BlockReactions.init(); //TODO Move to later
        AngelVariant.init();
    }

}
