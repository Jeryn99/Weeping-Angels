package mc.craig.software.angels.data.forge;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.blocks.WABlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModelProviderBlock extends BlockStateProvider {

    public ModelProviderBlock(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator.getPackOutput(), WeepingAngels.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(WABlocks.KONTRON_ORE.get());
        simpleBlock(WABlocks.KONTRON_ORE_DEEPSLATE.get());
        simpleBlock(WABlocks.COFFIN.get());
        simpleBlock(WABlocks.STATUE.get());
    }
}