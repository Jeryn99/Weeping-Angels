package mc.craig.software.angels.data.neoforge;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.blocks.WABlocks;
import mc.craig.software.angels.common.items.DetectorItem;
import mc.craig.software.angels.common.items.WAItems;
import mc.craig.software.angels.registry.RegistryHolder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.Objects;

public class ModelProviderItem extends ItemModelProvider {

    public ModelProviderItem(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator.getPackOutput(), WeepingAngels.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (RegistryHolder<Item, ? extends Item> entry : WAItems.ITEMS.getEntries()) {
            if (entry.get() instanceof DetectorItem || entry.get() == WAItems.CHRONODYNE_GENERATOR.get()) continue;


            if (entry.get() == WABlocks.COFFIN.get().asItem() || entry.get() == WABlocks.STATUE.get().asItem()) {
                basicItem(entry.get());
                continue;
            }

            if (entry.get() == WAItems.CHISEL.get()) {
                toolItem(entry.get());
                continue;
            }

            if (entry.get() instanceof BlockItem blockItem) {
                if(blockItem.getBlock() == WABlocks.SNOW_ANGEL.get()){
                    basicItem(entry.get());
                    continue;
                }

                blockItem(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(entry.get())));
                continue;
            }

            basicItem(entry.get());
        }
    }

    public ItemModelBuilder toolItem(Item item) {
        return toolItem(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)));
    }

    public ItemModelBuilder toolItem(ResourceLocation item) {
        return getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/handheld"))
                .texture("layer0", ResourceLocation.tryBuild(item.getNamespace(), "item/" + item.getPath()));
    }


    public ItemModelBuilder blockItem(ResourceLocation item) {
        return getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile(ResourceLocation.tryBuild(item.getNamespace(), "block/" + item.getPath())));
    }

    public ItemModelBuilder layeredItem(ResourceLocation destination, ResourceLocation item, ResourceLocation resourceLocation) {
        return getBuilder(destination.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", ResourceLocation.tryBuild(item.getNamespace(), "item/" + item.getPath()))
                .texture("layer1", ResourceLocation.tryBuild(resourceLocation.getNamespace(), "item/" + resourceLocation.getPath()));
    }

    public ItemModelBuilder layeredItem(ResourceLocation item, ResourceLocation resourceLocation) {
        return getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", ResourceLocation.tryBuild(item.getNamespace(), "item/" + item.getPath()))
                .texture("layer1", ResourceLocation.tryBuild(resourceLocation.getNamespace(), "item/" + resourceLocation.getPath()));
    }


}