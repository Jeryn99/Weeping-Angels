package me.swirtzly.minecraft.angels.client;

import me.swirtzly.minecraft.angels.common.WAObjects;
import me.swirtzly.minecraft.angels.common.items.DetectorItem;
import me.swirtzly.minecraft.angels.utils.ClientUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.DrawHighlightEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Created by Swirtzly on 11/02/2020 @ 21:31
 */
@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class ClientEvents {

	@SubscribeEvent
	public static void onblockHighlight(DrawHighlightEvent.HighlightBlock event) {
		Minecraft minecraft = Minecraft.getInstance();
		if (minecraft.objectMouseOver.getType() == RayTraceResult.Type.BLOCK) {
			BlockRayTraceResult blockRayTraceResult = (BlockRayTraceResult) minecraft.objectMouseOver;
			ClientPlayerEntity playerEntity = minecraft.player;
			ClientWorld world = minecraft.world;
			boolean canSee = playerEntity.getHeldItemMainhand().getItem() == WAObjects.Blocks.STATUE.get().asItem() || playerEntity.getHeldItemOffhand().getItem() == WAObjects.Blocks.STATUE.get().asItem();
			event.setCanceled(!canSee && world.getBlockState(blockRayTraceResult.getPos()).getBlock() == WAObjects.Blocks.STATUE.get());
		}
	}

}
