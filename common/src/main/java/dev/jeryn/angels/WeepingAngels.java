package dev.jeryn.angels;

import com.mojang.logging.LogUtils;
import dev.jeryn.angels.common.WAEntities;
import dev.jeryn.angels.common.WASounds;
import dev.jeryn.angels.common.WATabs;
import dev.jeryn.angels.common.blockentity.WABlockEntities;
import dev.jeryn.angels.common.blocks.WABlocks;
import dev.jeryn.angels.common.entity.angel.BlockReactions;
import dev.jeryn.angels.common.entity.angel.ai.AngelVariant;
import dev.jeryn.angels.common.items.WAItems;
import dev.jeryn.angels.common.level.structures.WAStructures;
import dev.jeryn.angels.compat.vivecraft.WAVRPluginHandler;
import dev.jeryn.angels.network.WANetwork;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

public class WeepingAngels {

    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "weeping_angels";

    public static final WAVRPluginHandler VR_HANDLER = new WAVRPluginHandler();


    public static ResourceLocation CRYPT_LOOT = new ResourceLocation(WeepingAngels.MODID, "chests/catacombs");

    public static void init() {
        WAItems.ITEMS.register();
        WASounds.SOUNDS.register();
        WABlocks.BLOCKS.register();
        WAEntities.ENTITY_TYPES.register();
        WABlockEntities.BLOCK_ENTITY_TYPES.register();
        WAStructures.STRUCTURES.register();
        WATabs.TABS.register();
        BlockReactions.init();
        AngelVariant.init();
        WANetwork.init();
    }

}
