//package mc.craig.software.angels.data.forge;
// TODO LOOT GEN
//import com.mojang.datafixers.util.Pair;
//import mc.craig.software.angels.common.blocks.WABlocks;
//import mc.craig.software.angels.common.items.WAItems;
//import mc.craig.software.angels.registry.RegistrySupplier;
//import net.minecraft.data.DataGenerator;
//import net.minecraft.data.PackOutput;
//import net.minecraft.data.loot.BlockLoot;
//import net.minecraft.data.loot.ChestLoot;
//import net.minecraft.data.loot.LootTableProvider;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.storage.loot.LootTable;
//import net.minecraft.world.level.storage.loot.LootTables;
//import net.minecraft.world.level.storage.loot.ValidationContext;
//import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
//import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.function.BiConsumer;
//import java.util.function.Consumer;
//import java.util.function.Supplier;
//
//public class LootProvider extends LootTableProvider {
//
//
//    public LootProvider(PackOutput arg, Set<ResourceLocation> set, List<SubProviderEntry> list) {
//        super(arg, set, list);
//    }
//
//    @Override
//    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
//        return List.of(Pair.of(ModBlockLoot::new, LootContextParamSets.BLOCK), Pair.of(ModChestLoot::new, LootContextParamSets.CHEST));
//    }
//
//    @Override
//    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationContext) {
//        for (Map.Entry<ResourceLocation, LootTable> entry : map.entrySet())
//            LootTables.validate(validationContext, entry.getKey(), entry.getValue());
//    }
//
//    public static class ModBlockLoot extends BlockLoot {
//        @Override
//        protected void addTables() {
//            this.add(WABlocks.KONTRON_ORE.get(), (block) -> createOreDrop(block, WAItems.KONTRON_INGOT.get()));
//            this.add(WABlocks.KONTRON_ORE_DEEPSLATE.get(), (block) -> createOreDrop(block, WAItems.KONTRON_INGOT.get()));
//            dropSelf(WABlocks.CHRONODYNE_GENERATOR.get());
//            dropSelf(WABlocks.COFFIN.get());
//            dropSelf(WABlocks.STATUE.get());
//            dropSelf(WABlocks.SNOW_ANGEL.get());
//            dropSelf(WABlocks.PLINTH.get());
//        }
//
//        @Override
//        protected Iterable<Block> getKnownBlocks() {
//            ArrayList<Block> blocks = new ArrayList<>();
//            for (RegistrySupplier<Block> entry : WABlocks.BLOCKS.getEntries()) {
//                blocks.add(entry.get());
//            }
//            return blocks;
//        }
//    }
//
//    public static class ModChestLoot extends ChestLoot {
//        @Override
//        public void accept(BiConsumer<ResourceLocation, LootTable.Builder> biConsumer) {
//            //  biConsumer.accept(WeepingAngels.CRYPT_LOOT, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(Items.GOLDEN_APPLE).setWeight(20)).add(LootItem.lootTableItem(Items.ENCHANTED_GOLDEN_APPLE)).add(LootItem.lootTableItem(Items.NAME_TAG).setWeight(30)).add(LootItem.lootTableItem(Items.BOOK).setWeight(10).apply(EnchantRandomlyFunction.randomApplicableEnchantment())).add(LootItem.lootTableItem(Items.IRON_PICKAXE).setWeight(5)).add(EmptyLootItem.emptyItem().setWeight(5))).withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 4.0F)).add(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F)))).add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))).add(LootItem.lootTableItem(Items.REDSTONE).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 9.0F)))).add(LootItem.lootTableItem(Items.LAPIS_LAZULI).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 9.0F)))).add(LootItem.lootTableItem(Items.DIAMOND).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))).add(LootItem.lootTableItem(Items.COAL).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 8.0F)))).add(LootItem.lootTableItem(Items.BREAD).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))).add(LootItem.lootTableItem(Items.GLOW_BERRIES).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 6.0F)))).add(LootItem.lootTableItem(Items.MELON_SEEDS).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))).add(LootItem.lootTableItem(Items.PUMPKIN_SEEDS).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))).add(LootItem.lootTableItem(Items.BEETROOT_SEEDS).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(3.0F)).add(LootItem.lootTableItem(Blocks.RAIL).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 8.0F)))).add(LootItem.lootTableItem(Blocks.POWERED_RAIL).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))).add(LootItem.lootTableItem(Blocks.DETECTOR_RAIL).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))).add(LootItem.lootTableItem(Blocks.ACTIVATOR_RAIL).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))).add(LootItem.lootTableItem(Blocks.TORCH).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 16.0F))))));
//        }
//    }
//}
