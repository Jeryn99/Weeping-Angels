package mc.craig.software.angels.registry.forge;

import mc.craig.software.angels.registry.DeferredRegistry;
import mc.craig.software.angels.registry.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class DeferredRegistryImpl {

    public static <T> DeferredRegistry<T> create(String modid, ResourceKey<? extends Registry<T>> resourceKey) {
        return new Impl<>(modid, resourceKey);
    }

    @SuppressWarnings("unchecked")
    public static class Impl<T> extends DeferredRegistry<T> {

        private final DeferredRegister<T> register;
        private final List<RegistrySupplier<T>> entries;

        public Impl(String modid, ResourceKey<? extends Registry<T>> resourceKey) {
            this.register = DeferredRegister.create(resourceKey, modid);
            this.entries = new ArrayList<>();
        }

        @Override
        public void register() {
            this.register.register(FMLJavaModLoadingContext.get().getModEventBus());
        }


        @Override
        public <R extends T> RegistrySupplier<R> register(String id, Supplier<R> supplier) {
            var orig = this.register.register(id, supplier);
            var registrySupplier = new RegistrySupplier<>(orig.getId(), orig);
            this.entries.add((RegistrySupplier<T>) registrySupplier);
            return registrySupplier;
        }

        @Override
        public Collection<RegistrySupplier<T>> getEntries() {
            return this.entries;
        }
    }


}
