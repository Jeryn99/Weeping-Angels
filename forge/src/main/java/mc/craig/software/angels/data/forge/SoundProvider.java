package mc.craig.software.angels.data.forge;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.WASounds;
import mc.craig.software.angels.registry.RegistrySupplier;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.data.SoundDefinitionsProvider;
import net.minecraftforge.registries.RegistryObject;

public class SoundProvider extends SoundDefinitionsProvider {
    /**
     * Creates a new instance of this data provider.
     *
     * @param generator The data generator instance provided by the event you are initializing this provider in.
     * @param helper    The existing file helper provided by the event you are initializing this provider in.
     */
    public SoundProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator.getPackOutput(), WeepingAngels.MODID, helper);
    }

    @Override
    public void registerSounds() {
        for (RegistrySupplier<SoundEvent> entry : WASounds.SOUNDS.getEntries()) {
            createDefinitionAndAdd(entry.get(), SoundDefinition.SoundType.SOUND, entry.get().getLocation().getPath(), entry.get().getLocation().getPath());
        }
    }

    public void createDefinitionAndAdd(SoundEvent mainSound, SoundDefinition.SoundType soundType, String subtitle, String... soundEvent) {
        SoundDefinition def = SoundDefinition.definition().subtitle("subtitle." + WeepingAngels.MODID + "." + subtitle);
        for (String event : soundEvent) {
            def.with(SoundDefinition.Sound.sound(new ResourceLocation(WeepingAngels.MODID, event), soundType));
        }
        add(mainSound, def);
    }

}