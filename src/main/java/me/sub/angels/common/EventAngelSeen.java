package me.sub.angels.common;

import me.sub.angels.common.entities.EntityAngel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Event;

public class EventAngelSeen extends Event {

    EntityPlayer player;
    EntityAngel angel;

    public EventAngelSeen(EntityPlayer player, EntityAngel angel) {
        this.player = player;
        this.angel = angel;
    }

    public EntityAngel getAngel() {
        return angel;
    }

    public EntityPlayer getPlayer() {
        return player;
    }
}
