package dev.jeryn.angels.common;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.jeryn.angels.WeepingAngels;
import dev.jeryn.angels.registry.DeferredRegistry;
import dev.jeryn.angels.registry.RegistrySupplier;
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
