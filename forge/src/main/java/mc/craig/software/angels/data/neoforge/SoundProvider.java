package mc.craig.software.angels.data.neoforge;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.WASounds;
import mc.craig.software.angels.registry.RegistryHolder;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SoundDefinition;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;

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
        for (RegistryHolder<SoundEvent, ? extends SoundEvent> entry : WASounds.SOUNDS.getEntries()) {
            createDefinitionAndAdd(entry.get(), SoundDefinition.SoundType.SOUND, entry.get().getLocation().getPath(), entry.get().getLocation().getPath());
        }
    }

    public void createDefinitionAndAdd(SoundEvent mainSound, SoundDefinition.SoundType soundType, String subtitle, String... soundEvent) {
        SoundDefinition def = SoundDefinition.definition().subtitle("subtitle." + WeepingAngels.MODID + "." + subtitle);
        for (String event : soundEvent) {
            def.with(SoundDefinition.Sound.sound(ResourceLocation.tryBuild(WeepingAngels.MODID, event), soundType));
        }
        add(mainSound, def);
    }

}