package mc.craig.software.angels.forge.compat.tardis.datagen;

import mc.craig.software.angels.forge.compat.tardis.registry.TardisExteriorReg;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.tardis.mod.datagen.TardisSchematicGen;

import java.io.IOException;

public class TardisSchemGen extends TardisSchematicGen {
    public TardisSchemGen(DataGenerator generator) {
        super(generator);
    }

    @Override
    public void run(CachedOutput cache) throws IOException {
        this.generateExterior(cache, TardisExteriorReg.ABPROP.get());
    }
}
