package mc.jeryn.dev.statues;

import com.mojang.logging.LogUtils;
import mc.jeryn.dev.statues.common.WACodecs;
import mc.jeryn.dev.statues.common.WAEntities;
import mc.jeryn.dev.statues.common.WASounds;
import mc.jeryn.dev.statues.common.WATabs;
import mc.jeryn.dev.statues.common.blockentity.WABlockEntities;
import mc.jeryn.dev.statues.common.blocks.WABlocks;
import mc.jeryn.dev.statues.common.entity.angel.BlockReactions;
import mc.jeryn.dev.statues.common.entity.angel.ai.AngelVariant;
import mc.jeryn.dev.statues.common.items.WAItems;
import mc.jeryn.dev.statues.common.level.structures.WAStructures;
import mc.jeryn.dev.statues.compat.vivecraft.WAVRPluginHandler;
import mc.jeryn.dev.statues.network.WANetworkManager;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import org.slf4j.Logger;

public class WeepingAngels {

    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "weeping_angels";

    public static final WAVRPluginHandler VR_HANDLER = new WAVRPluginHandler();


    public static ResourceKey<LootTable> CRYPT_LOOT = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.tryBuild(WeepingAngels.MODID, "chests/catacombs"));

    public static void init() {

        WAItems.ITEMS.register();
        WASounds.SOUNDS.register();
        WABlocks.BLOCKS.register();
        WAEntities.ENTITY_TYPES.register();
        WABlockEntities.BLOCK_ENTITY_TYPES.register();
        WAStructures.STRUCTURES.register();
        WATabs.TABS.register();
        WACodecs.BLOCK_TYPE.register();
        BlockReactions.init();
        AngelVariant.init();

        WANetworkManager.init();
    }

}
