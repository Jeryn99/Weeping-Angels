package mc.craig.software.angels.data.neoforge;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.util.WATags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class AngelItemTags extends ItemTagsProvider {

    public AngelItemTags(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries,
                            CompletableFuture<TagLookup<Block>> blockTagsProvider, ExistingFileHelper existingFileHelper) {
        super(packOutput, registries, blockTagsProvider, WeepingAngels.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        tag(WATags.STEALABLE_ITEMS).add(Items.CLOCK, Items.TORCH, Items.COMPASS, Items.RECOVERY_COMPASS);
        tag(WATags.STEALABLE_ITEMS).addOptionalTag(ItemTags.PICKAXES.location());
        tag(WATags.ATTACK_OVERRIDES).addOptionalTag(ItemTags.PICKAXES.location());
    }
}
