package com.rubio.marsroverapi.rover.services.components.movement;

import com.rubio.marsroverapi.rover.models.Rover;
import org.springframework.stereotype.Component;

/**
 * Orquestador responsable de aplicar la estrategia de movimiento adecuada
 * según la dirección actual del {@link Rover}.
 * <p>
 * Retorna un {@code boolean} que indica si el rover encontró un obstáculo
 * durante su intento de desplazamiento.
 */
@Component
public class RoverMover {
    private final MoveStrategyFactory strategyFactory;

    public RoverMover(MoveStrategyFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
    }

    /**
     * Desplaza el rover una unidad hacia adelante, utilizando la estrategia
     * correspondiente a su dirección actual.
     *
     * @param rover entidad que desea desplazarse.
     * @return {@code true} si la posición destino está ocupada y el rover no se mueve;<br>
     *         {@code false} si el movimiento se realiza exitosamente.
     */
    public boolean moveForward(Rover rover) {
        MoveStrategy moveStrategy = strategyFactory.getStrategy(rover.getDirection());
        return moveStrategy.move(rover);
    }

}