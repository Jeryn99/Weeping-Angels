package craig.software.mc.angels.api;

import craig.software.mc.angels.common.entities.QuantumLockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.EntityEvent;

public class EventAngelTeportedPlayerCrossDim extends EntityEvent {

    private final PlayerEntity player;
    private final QuantumLockEntity quantumEntity;
    private final ServerWorld destinationDim;


    public EventAngelTeportedPlayerCrossDim(QuantumLockEntity quantumLockEntity, PlayerEntity player, ServerWorld serverWorld) {
        super(quantumLockEntity);
        this.player = player;
        this.quantumEntity = quantumLockEntity;
        this.destinationDim = serverWorld;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public QuantumLockEntity getQuantumEntity() {
        return quantumEntity;
    }

    public ServerWorld getDestinationDim() {
        return destinationDim;
    }

    @Override
    public boolean isCancelable() {
        return true;
    }
}
