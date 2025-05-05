package mc.jeryn.dev.statues.common;

import dev.architectury.injectables.annotations.ExpectPlatform;
import mc.jeryn.dev.statues.WeepingAngels;
import mc.jeryn.dev.statues.registry.DeferredRegister;
import mc.jeryn.dev.statues.registry.RegistryHolder;
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
