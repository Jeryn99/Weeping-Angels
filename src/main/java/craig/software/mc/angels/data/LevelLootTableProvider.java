package craig.software.mc.angels.data;

import craig.software.mc.angels.WeepingAngels;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

/* Created by Craig on 10/03/2021 */
public class LevelLootTableProvider extends GlobalLootModifierProvider {
    public LevelLootTableProvider(DataGenerator gen) {
        super(gen, WeepingAngels.MODID);
    }

    @Override
    protected void start() {

        //FOB WATCH
        ResourceLocation[] specificTables = new ResourceLocation[]{
                BuiltInLootTables.ABANDONED_MINESHAFT,
                BuiltInLootTables.BASTION_BRIDGE,
                BuiltInLootTables.LIBRARIAN_GIFT,
                BuiltInLootTables.WOODLAND_MANSION,
                BuiltInLootTables.STRONGHOLD_LIBRARY,
                BuiltInLootTables.SHIPWRECK_SUPPLY
        };

        for (ResourceLocation currentTable : specificTables) {
            add(currentTable.getPath(), WAGlobalLootModifiers.ANGEL_LOOT.get(), new WAGlobalLootModifiers.DiscLoot(
                    new LootItemCondition[]{LootTableIdCondition.builder(currentTable).build()}, 15)
            );
        }

        for (ResourceLocation resourceLocation : BuiltInLootTables.all()) {
            //Fob Treasure
            if (resourceLocation.getPath().contains("treasure")) {
                add(resourceLocation.getPath(), WAGlobalLootModifiers.ANGEL_LOOT.get(), new WAGlobalLootModifiers.DiscLoot(
                        new LootItemCondition[]{LootTableIdCondition.builder(resourceLocation).build()}, 40)
                );
            }
        }


    }
}