package craig.software.mc.angels.data;

import craig.software.mc.angels.WeepingAngels;
import craig.software.mc.angels.common.level.WAFeatures;
import craig.software.mc.angels.utils.AngelUtil;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

public class WAStructureTagGen extends TagsProvider<Structure> {

    public WAStructureTagGen(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, BuiltinRegistries.STRUCTURES, WeepingAngels.MODID, helper);
    }

    @Override
    protected void addTags() {
        for (Structure structureFeature : BuiltinRegistries.STRUCTURES) {
            this.tag(AngelUtil.TELEPORT_STRUCTURES).add(structureFeature);
        }

        this.tag(AngelUtil.CATACOMBS).addOptional(WAFeatures.CATACOMB.getId());

    }


    public @NotNull String getName() {
        return "Angel Structure - Structure Teleport Tags";
    }
}