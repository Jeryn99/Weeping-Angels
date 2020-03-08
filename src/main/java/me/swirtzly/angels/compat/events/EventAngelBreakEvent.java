package me.swirtzly.angels.compat.events;

import me.swirtzly.angels.common.entities.WeepingAngelEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

/**
 * Created by Swirtzly on 08/03/2020 @ 16:43
 */
@Cancelable
public class EventAngelBreakEvent extends Event {
	
	private final BlockPos pos;
	private final WeepingAngelEntity angel;
	private final BlockState state;
	
	public EventAngelBreakEvent(WeepingAngelEntity weepingAngelEntity, BlockState state, BlockPos pos) {
		this.angel = weepingAngelEntity;
		this.state = state;
		this.pos = pos;
	}
	
	public BlockPos getPos() {
		return pos;
	}
	
	public BlockState getState() {
		return state;
	}
	
	public WeepingAngelEntity getAngel() {
		return angel;
	}
	
}
