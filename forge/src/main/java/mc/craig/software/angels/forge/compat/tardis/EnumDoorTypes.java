package mc.craig.software.angels.forge.compat.tardis;

import net.tardis.mod.client.models.interiordoors.IInteriorDoorRenderer;
import net.tardis.mod.enums.EnumDoorState;
import net.tardis.mod.misc.IDoorType;

import java.util.function.Function;
import java.util.function.Supplier;

public enum EnumDoorTypes implements IDoorType {
    ABPROP(state -> {
        switch (state) {
            case CLOSED:
                return 0.0D;
            case ONE:
                return 85.0;
            case BOTH:
                return -85.0;
            default:
                return 0.0;
        }
    }, EnumDoorState.CLOSED, EnumDoorState.ONE, EnumDoorState.BOTH);


    Function<EnumDoorState, Double> func;
    EnumDoorState[] validStates;
    Supplier<Supplier<IInteriorDoorRenderer>> renderer;

    EnumDoorTypes(Function<EnumDoorState, Double> func, EnumDoorState... states) {
        this.validStates = states;
        this.func = func;
    }

    @Override
    public EnumDoorState[] getValidStates() {
        return this.validStates;
    }

    @Override
    public double getRotationForState(EnumDoorState state) {
        return func.apply(state);
    }

    @Override
    public void setInteriorDoorModel(IInteriorDoorRenderer renderer) {
        this.renderer = () -> () -> renderer;
    }

    @Override
    public IInteriorDoorRenderer getInteriorDoorRenderer() {
        return this.renderer.get().get();
    }
}
