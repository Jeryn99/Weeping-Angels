package mc.craig.software.angels.fabric;

import mc.craig.software.angels.common.items.WAItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;

public class LootModifying {

    //TODO Lessen
    public static void init() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin() && shouldAdd(id)) {

                LootPool sallyDiscPool = LootPool.lootPool().with(LootItem.lootTableItem(WAItems.DISC_SALLY.get()).build()).build();
                LootPool poolBuilderPool = LootPool.lootPool().with(LootItem.lootTableItem(WAItems.DISC_TIME_PREVAILS.get()).build()).build();
                LootPool timeyWimeyDetectorPool = LootPool.lootPool().with(LootItem.lootTableItem(WAItems.TIMEY_WIMEY_DETECTOR.get()).setWeight(1).build()).build();

                tableBuilder.pools(List.of(sallyDiscPool, poolBuilderPool, timeyWimeyDetectorPool));
            }
        });
    }


    private static boolean shouldAdd(ResourceLocation id) {
        ResourceLocation[] specificTables = new ResourceLocation[]{
                BuiltInLootTables.ABANDONED_MINESHAFT,
                BuiltInLootTables.BASTION_BRIDGE,
                BuiltInLootTables.LIBRARIAN_GIFT,
                BuiltInLootTables.WOODLAND_MANSION,
                BuiltInLootTables.STRONGHOLD_LIBRARY,
                BuiltInLootTables.SHIPWRECK_SUPPLY
        };

        for (ResourceLocation specificTable : specificTables) {
            if (specificTable.equals(id)) return true;
        }

        return id.toString().contains("treasure");
    }

}
