package mc.craig.software.angels.forge.compat.tardis.datagen;

import com.google.gson.JsonObject;
import mc.craig.software.angels.common.items.WAItems;
import mc.craig.software.angels.forge.compat.tardis.TardisMod;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.crafting.Ingredient;
import net.tardis.mod.datagen.SpectrometerRecipeGen;
import net.tardis.mod.schematics.Schematic;

import java.io.IOException;
import java.nio.file.Path;

public class TardisSpectroGen extends SpectrometerRecipeGen {


    private final DataGenerator generator;

    public TardisSpectroGen(DataGenerator generator) {
        super(generator);
        this.generator = generator;
    }

    @Override
    public void run(CachedOutput cache) throws IOException {
        final Path path = this.generator.getOutputFolder();
        createSpectrometerRecipe(path, cache, 200, Ingredient.of(WAItems.KONTRON_INGOT.get()), TardisMod::getSchem);
    }

    @Override
    public JsonObject createRecipe(int processTicks, Ingredient ingredient, Schematic output) {
        return super.createRecipe(processTicks, ingredient, output);
    }

    @Override
    public String getName() {
        return "Angels";
    }
}
