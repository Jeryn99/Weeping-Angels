package mc.craig.software.angels.common;

import dev.architectury.injectables.annotations.ExpectPlatform;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.registry.DeferredRegister;
import mc.craig.software.angels.registry.RegistryHolder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;

public class WATabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(WeepingAngels.MODID, Registries.CREATIVE_MODE_TAB);

    public static final RegistryHolder<CreativeModeTab, CreativeModeTab> MAIN_TAB = TABS.register("main_tab", WATabs::createTab);


    @ExpectPlatform
    public static CreativeModeTab createTab() {
        throw new RuntimeException("no");
    }

}
