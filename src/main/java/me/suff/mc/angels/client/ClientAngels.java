package me.suff.mc.angels.client;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.client.renderer.entiity.PortalRenderer;
import me.suff.mc.angels.client.renderer.entiity.WeepingAngelRenderer;
import me.suff.mc.angels.client.renderer.tile.CoffinTileRenderer;
import me.suff.mc.angels.client.renderer.tile.PlinthTileRenderer;
import me.suff.mc.angels.client.renderer.tile.StatueTileRender;
import me.suff.mc.angels.common.objects.WAItems;
import me.suff.mc.angels.common.objects.WATiles;
import me.suff.mc.angels.util.AngelUtils;
import me.suff.mc.angels.util.EntitySpawnPacket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;

import java.util.UUID;

import static me.suff.mc.angels.WeepingAngels.spawnPacket;

/* Created by Craig on 21/02/2021 */
@SuppressWarnings("deprecation")
public class ClientAngels implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // Predicates
        FabricModelPredicateProviderRegistry.register(WAItems.DETECTOR, new Identifier("angle"), (stack, world, entity) -> AngelUtils.RAND.nextInt(17));

        //Block Entity
        BlockEntityRendererRegistry.INSTANCE.register(WATiles.STATUE_TILE, StatueTileRender::new);
        BlockEntityRendererRegistry.INSTANCE.register(WATiles.PLINTH_TILE, PlinthTileRenderer::new);
        BlockEntityRendererRegistry.INSTANCE.register(WATiles.COFFIN_TILE, CoffinTileRenderer::new);

        //Item
        EntityRendererRegistry.INSTANCE.register(WeepingAngels.WEEPING_ANGEL, (dispatcher, context) -> new WeepingAngelRenderer(dispatcher));
        EntityRendererRegistry.INSTANCE.register(WeepingAngels.PORTAL, (dispatcher, context) -> new PortalRenderer(dispatcher));
        EntityRendererRegistry.INSTANCE.register(WeepingAngels.CHRONO, (dispatcher, context) -> new FlyingItemEntityRenderer(dispatcher, context.getItemRenderer()));

        // :(
        receiveEntityPacket();
    }

    public void receiveEntityPacket() {
        ClientSidePacketRegistry.INSTANCE.register(spawnPacket, (ctx, byteBuf) -> {
            EntityType< ? > et = Registry.ENTITY_TYPE.get(byteBuf.readVarInt());
            UUID uuid = byteBuf.readUuid();
            int entityId = byteBuf.readVarInt();
            Vec3d pos = EntitySpawnPacket.PacketBufUtil.readVec3d(byteBuf);
            float pitch = EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
            float yaw = EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
            ctx.getTaskQueue().execute(() -> {
                if (MinecraftClient.getInstance().world == null)
                    throw new IllegalStateException("Tried to spawn entity in a null world!");
                Entity e = et.create(MinecraftClient.getInstance().world);
                if (e == null)
                    throw new IllegalStateException("Failed to create instance of entity \"" + Registry.ENTITY_TYPE.getId(et) + "\"!");
                e.updateTrackedPosition(pos);
                e.setPos(pos.x, pos.y, pos.z);
                e.pitch = pitch;
                e.yaw = yaw;
                e.setEntityId(entityId);
                e.setUuid(uuid);
                MinecraftClient.getInstance().world.addEntity(entityId, e);
            });
        });
    }
}
