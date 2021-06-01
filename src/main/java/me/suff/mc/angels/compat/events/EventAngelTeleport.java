package me.suff.mc.angels.compat.events;

import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;

@Cancelable
public class EventAngelTeleport extends PlayerEvent {

    /**
     * This event fires when a weeping angel has teleported a player.
     */

    private ServerWorld targetDimension;
    private WeepingAngelEntity angel;
    private BlockPos previous, destinationPos;

    public EventAngelTeleport(PlayerEntity player, WeepingAngelEntity angel, BlockPos destinationPos, ServerWorld targetDimension) {
        super(player);
        this.angel = angel;
        previous = player.getPosition();
        this.destinationPos = destinationPos;
        this.targetDimension = targetDimension;
    }

    /**
     * Returns the angel entity
     */
    public WeepingAngelEntity getAngel() {
        return angel;
    }

    /**
     * Returns the intended destination position
     */
    public BlockPos getDestination() {
        return destinationPos;
    }

    /**
     * Returns the position of the player BEFORE being teleported
     */
    public BlockPos getPreviousLocation() {
        return previous;
    }

    /**
     * Returns the intended dimension that the player will be teleported to
     *
     * @return
     */
    public ServerWorld getTargetDimension() {
        return targetDimension;
    }

    public void setTargetDimension(ServerWorld targetDimension) {
        this.targetDimension = targetDimension;
    }

    public void setDestinationPos(BlockPos destinationPos) {
        this.destinationPos = destinationPos;
    }

}
