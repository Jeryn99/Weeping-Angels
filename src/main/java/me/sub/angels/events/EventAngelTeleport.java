package me.sub.angels.events;

import me.sub.angels.common.entities.EntityAngel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Event;

public class EventAngelTeleport extends Event {
	
	EntityPlayer player;
    EntityAngel angel;

    public EventAngelTeleport(EntityPlayer player, EntityAngel angel) {
		this.player = player;
        this.angel = angel;
	}
	
	public EntityPlayer getPlayer() {
		return player;
	}

    public EntityAngel getAngel() {
        return angel;
    }
}
