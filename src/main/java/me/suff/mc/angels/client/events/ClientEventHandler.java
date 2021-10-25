package me.suff.mc.angels.client.events;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.utils.PlayerUtils;
import me.suff.mc.angels.utils.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.ArrayList;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = WeepingAngels.MODID)
public class ClientEventHandler {

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent ev) {
        WAObjects.ITEMS.forEach(RenderUtil::setItemRender);

        WAObjects.ITEMS = new ArrayList<>();
    }

    @SubscribeEvent
    public static void onBlockHightLight(DrawBlockHighlightEvent event){
        if (event.getTarget().typeOfHit == RayTraceResult.Type.BLOCK) {
            RayTraceResult blockRayTraceResult = event.getTarget();
            boolean canSee = PlayerUtils.isInEitherHand(Minecraft.getMinecraft().player, Item.getItemFromBlock(WAObjects.Blocks.STATUE));
            event.setCanceled(!canSee && Minecraft.getMinecraft().world.getBlockState(event.getTarget().getBlockPos()).getBlock() == WAObjects.Blocks.STATUE);
        }
    }

}
