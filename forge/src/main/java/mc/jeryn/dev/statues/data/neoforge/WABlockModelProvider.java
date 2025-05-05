package mc.jeryn.dev.statues.data.neoforge;

import mc.jeryn.dev.statues.WeepingAngels;
import mc.jeryn.dev.statues.common.blocks.WABlocks;
import net.minecraft.data.DataGenerator;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class WABlockModelProvider extends BlockStateProvider {

    public WABlockModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator.getPackOutput(), WeepingAngels.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(WABlocks.COFFIN.get());
        simpleBlock(WABlocks.STATUE.get());
    }
}