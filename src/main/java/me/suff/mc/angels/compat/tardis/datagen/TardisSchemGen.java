package me.suff.mc.angels.compat.tardis.datagen;

import me.suff.mc.angels.compat.tardis.registry.TardisExteriorReg;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.tardis.mod.datagen.TardisSchematicGen;

import java.io.IOException;

public class TardisSchemGen extends TardisSchematicGen {
    public TardisSchemGen(DataGenerator generator) {
        super(generator);
    }

    @Override
    public void run(DirectoryCache cache) throws IOException {
        this.generateExterior(cache, TardisExteriorReg.ABPROP.get());
    }
}
