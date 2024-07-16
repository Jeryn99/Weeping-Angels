
package mc.craig.software.angels.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@SuppressWarnings({"unchecked", "rawtypes"})
public class ArchDeferredRegisterWrapper<T> extends DeferredRegister<T> {

    private final dev.architectury.registry.registries.DeferredRegister<T> register;
    private final ResourceKey<Registry<T>> resourceKey;
    private final Map<dev.architectury.registry.registries.RegistrySupplier<T>, RegistrySupplier<T>> suppliers = new HashMap<>();

    public ArchDeferredRegisterWrapper(String modid, ResourceKey<Registry<T>> resourceKey) {
        this.register = dev.architectury.registry.registries.DeferredRegister.create(modid, resourceKey);
        this.resourceKey = resourceKey;
    }

    public static <T> DeferredRegister<T> get(String modid, ResourceKey<Registry<T>> resourceKey) {
        return new ArchDeferredRegisterWrapper<>(modid, resourceKey);
    }

    @Override
    public void register() {
        this.register.register();
    }

    @Override
    public <R extends T> RegistrySupplier<R> register(String id, Supplier<R> supplier) {
        var obj = this.register.register(id, supplier);
        var registrySupplier = (RegistrySupplier<R>) this.suppliers.computeIfAbsent((dev.architectury.registry.registries.RegistrySupplier<T>) obj, rs -> new RegistrySupplier<>(rs.getId(), rs));
        if (Platform.isFabric() && this.resourceKey.equals(Registries.POINT_OF_INTEREST_TYPE)) {
            POI_TYPES_TO_FIX.add((RegistrySupplier) registrySupplier);
        }
        return registrySupplier;
    }

    @Override
    public Collection<RegistrySupplier<T>> getEntries() {
        return this.suppliers.values();
    }
}