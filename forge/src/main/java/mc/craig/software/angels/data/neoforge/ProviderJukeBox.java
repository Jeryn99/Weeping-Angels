package mc.craig.software.angels.data.neoforge;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.WASounds;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.item.JukeboxSongs;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import static mc.craig.software.angels.common.items.WAItems.SALLY;
import static mc.craig.software.angels.common.items.WAItems.TIME_PREVAILS;

public class ProviderJukeBox {

    public static void bootstrap(BootstrapContext<JukeboxSong> jukeboxSongBootstrapContext) {
        register(jukeboxSongBootstrapContext, TIME_PREVAILS, Holder.direct(WASounds.DISC_TIME_PREVAILS.get()), 320 / 20, 1);
        register(jukeboxSongBootstrapContext, SALLY, Holder.direct(WASounds.DISC_SALLY.get()), 1300 / 20, 1);
    }

    private static void register(BootstrapContext<JukeboxSong> arg, ResourceKey<JukeboxSong> arg2, Holder<SoundEvent> arg3, int i, int j) {
        arg.register(arg2, new JukeboxSong(arg3, Component.translatable(Util.makeDescriptionId("jukebox_song", arg2.location())), (float) i, j));
    }
}
