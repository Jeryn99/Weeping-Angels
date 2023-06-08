package mc.craig.software.angels;

import com.mojang.logging.LogUtils;
import mc.craig.software.angels.common.WAEntities;
import mc.craig.software.angels.common.WASounds;
import mc.craig.software.angels.common.WATabs;
import mc.craig.software.angels.common.blockentity.WABlockEntities;
import mc.craig.software.angels.common.blocks.WABlocks;
import mc.craig.software.angels.common.entity.angel.BlockReactions;
import mc.craig.software.angels.common.entity.angel.ai.AngelVariant;
import mc.craig.software.angels.common.items.WAItems;
import mc.craig.software.angels.common.level.structures.WAStructures;
import mc.craig.software.angels.network.WANetwork;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

public class WeepingAngels {

    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "weeping_angels";

    public static ResourceLocation CRYPT_LOOT = new ResourceLocation(WeepingAngels.MODID, "chests/catacombs");

    public static void init() {
        WAItems.ITEMS.register();
        WASounds.SOUNDS.register();
        WABlocks.BLOCKS.register();
        WAEntities.ENTITY_TYPES.register();
        WABlockEntities.BLOCK_ENTITY_TYPES.register();
        WAStructures.STRUCTURES.register();
        WATabs.TABS.register();
 /*       WAFeatures.PLACED_FEATURES.register();
        WAFeatures.CONFIGURED_FEATURES.register();*/
        BlockReactions.init();
        AngelVariant.init();
        WANetwork.init();

    }

}
