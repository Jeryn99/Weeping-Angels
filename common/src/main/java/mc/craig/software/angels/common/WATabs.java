package mc.craig.software.angels.common;

import dev.architectury.injectables.annotations.ExpectPlatform;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.registry.DeferredRegistry;
import mc.craig.software.angels.registry.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;

public class WATabs {
    public static final DeferredRegistry<CreativeModeTab> TABS = DeferredRegistry.create(WeepingAngels.MODID, Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<CreativeModeTab> MAIN_TAB = TABS.register("main_tab", WATabs::createTab);


    @ExpectPlatform
    public static CreativeModeTab createTab() {
        throw new RuntimeException("no");
    }

}
