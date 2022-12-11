package mc.craig.software.angels.compat.tardis.datagen;

import mc.craig.software.angels.compat.tardis.registry.TardisExteriorReg;
import java.io.IOException;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.tardis.mod.datagen.TardisSchematicGen;

public class TardisSchemGen extends TardisSchematicGen {
    public TardisSchemGen(DataGenerator generator) {
        super(generator);
    }

    @Override
    public void run(DirectoryCache cache) throws IOException {
        this.generateExterior(cache, TardisExteriorReg.ABPROP.get());
    }
}
