package mc.craig.software.angels.fabric;

import mc.craig.software.angels.WeepingAngels;
import net.fabricmc.api.ModInitializer;

public class WeepingAngelsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        WeepingAngels.init();
    }
}
