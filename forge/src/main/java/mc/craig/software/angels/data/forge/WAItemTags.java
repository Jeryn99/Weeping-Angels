package mc.craig.software.angels.data.forge;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.items.WAItems;
import mc.craig.software.angels.util.WATags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class WAItemTags extends ItemTagsProvider {


    public WAItemTags(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider,
                      CompletableFuture<TagsProvider.TagLookup<Block>> blockTagProvider, ExistingFileHelper helper) {
        super(packOutput, lookupProvider, blockTagProvider, WeepingAngels.MODID, helper);
    }

    @Override
    public String getName() {
        return "Item Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        System.out.println("AAAAAAAAAAAAAA");
        tag(WATags.STEALABLE_ITEMS).add(Items.CLOCK, Items.TORCH, Items.COMPASS, Items.RECOVERY_COMPASS);
      //  tag(ItemTags.MUSIC_DISCS).add(WAItems.DISC_SALLY.get(), WAItems.DISC_TIME_PREVAILS.get());
        tag(WATags.STEALABLE_ITEMS).addOptionalTag(ItemTags.PICKAXES.location());
        tag(WATags.ATTACK_OVERRIDES).addOptionalTag(ItemTags.PICKAXES.location());
        System.out.println("BBBBBBBBBBBBBBBBBBBB");


    }
}
