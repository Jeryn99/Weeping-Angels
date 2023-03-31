package mc.craig.software.angels.data.forge;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.util.WATags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ItemTags extends ItemTagsProvider {


    public ItemTags(PackOutput arg, CompletableFuture<HolderLookup.Provider> completableFuture, CompletableFuture<TagLookup<Block>> completableFuture2, @Nullable ExistingFileHelper existingFileHelper) {
        super(arg, completableFuture, completableFuture2, WeepingAngels.MODID, existingFileHelper);
    }

    public ItemTags(PackOutput arg, CompletableFuture<HolderLookup.Provider> completableFuture, CompletableFuture<TagLookup<Item>> completableFuture2, CompletableFuture<TagLookup<Block>> completableFuture3, @Nullable ExistingFileHelper existingFileHelper) {
        super(arg, completableFuture, completableFuture2, completableFuture3, WeepingAngels.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        tag(WATags.STEALABLE_ITEMS).add(Items.CLOCK, Items.TORCH, Items.COMPASS, Items.RECOVERY_COMPASS);
        tag(WATags.STEALABLE_ITEMS).addOptionalTag(Tags.Items.TOOLS_PICKAXES.location());
        tag(WATags.ATTACK_OVERRIDES).addOptionalTag(Tags.Items.TOOLS_PICKAXES.location());
    }
}
