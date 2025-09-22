package dev.jeryn.angels.data.forge;

import dev.jeryn.angels.WeepingAngels;
import dev.jeryn.angels.common.items.WAItems;
import dev.jeryn.angels.util.WATags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class AngelItemTags extends ItemTagsProvider {

    public AngelItemTags(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries,
                            CompletableFuture<TagLookup<Block>> blockTagsProvider, ExistingFileHelper existingFileHelper) {
        super(packOutput, registries, blockTagsProvider, WeepingAngels.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        System.out.println("I am doing something");
        tag(WATags.STEALABLE_ITEMS).add(Items.CLOCK, Items.TORCH, Items.COMPASS, Items.RECOVERY_COMPASS);
        tag(ItemTags.MUSIC_DISCS).add(WAItems.DISC_SALLY.get(), WAItems.DISC_TIME_PREVAILS.get());
        tag(WATags.STEALABLE_ITEMS).addOptionalTag(ItemTags.PICKAXES.location());
        tag(WATags.ATTACK_OVERRIDES).addOptionalTag(ItemTags.PICKAXES.location());
        System.out.println("I seemingly finished up doing that something");
    }
}
