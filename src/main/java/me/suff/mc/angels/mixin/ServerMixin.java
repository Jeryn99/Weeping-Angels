package me.suff.mc.angels.mixin;

import net.minecraft.resource.ServerResourceManager;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/* Created by Craig on 23/02/2021 */
@Mixin(MinecraftServer.class)
public interface ServerMixin {

    @Accessor("serverResourceManager")
    ServerResourceManager getServerManager();

}
