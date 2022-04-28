package me.suff.mc.angels.compat.tardis.datagen;

import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.compat.tardis.TardisMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.item.crafting.Ingredient;
import net.tardis.mod.datagen.SpectrometerRecipeGen;

import java.io.IOException;
import java.nio.file.Path;

public class TardisSpectroGen extends SpectrometerRecipeGen {


    private final DataGenerator generator;

    public TardisSpectroGen(DataGenerator generator) {
        super(generator);
        this.generator = generator;
    }

    @Override
    public void run(DirectoryCache cache) throws IOException {
        final Path path = this.generator.getOutputFolder();
     //   createSpectrometerRecipe(path, cache, 200, Ingredient.of(WAObjects.Items.KONTRON_INGOT.get()), TardisMod::getSchem);
    }

    @Override
    public String getName() {
        return "Angels";
    }
}
