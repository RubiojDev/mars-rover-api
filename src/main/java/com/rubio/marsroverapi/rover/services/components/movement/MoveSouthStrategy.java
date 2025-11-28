package com.rubio.marsroverapi.rover.services.components.movement;

import com.rubio.marsroverapi.config.MapProperties;
import com.rubio.marsroverapi.rover.models.Rover;
import com.rubio.marsroverapi.rover.validations.RoverValidation;

import static com.rubio.marsroverapi.rover.utilities.CircularPosition.forward;

/**
 * Implementación de {@link MoveStrategy} que mueve al rover en dirección sur.
 * <p>
 * Calcula la nueva posición del rover aplicando desplazamiento circular sobre el eje Y,
 * tomando en cuenta el alto del mapa. Antes de moverlo valida si la nueva
 * posición está ocupada por un obstáculo.
 * <p>
 * Retorna {@code false} cuando el rover puede avanzar sin interferencias,
 * y {@code true} cuando la posición objetivo está ocupada y el movimiento no se realiza.
 */
public class MoveSouthStrategy implements MoveStrategy {

    private final RoverValidation roverValidation;
    private final MapProperties mapProperties;

    public MoveSouthStrategy(RoverValidation roverValidation, MapProperties mapProperties) {
        this.roverValidation = roverValidation;
        this.mapProperties = mapProperties;
    }

    /**
     * Mueve el rover una unidad hacia el sur.
     * <p>
     * Aplica el desplazamiento circular según el alto del mapa y verifica
     * que la posición resultante no esté ocupada antes de actualizar la posición.
     *
     * @param rover entidad que será desplazada.
     * @return {@code true} si la nueva posición está ocupada y el rover no se mueve;<br>
     *         {@code false} si el movimiento se realiza exitosamente.
     */
    @Override
    public boolean move(Rover rover) {
        Integer posXRover = rover.getPosX();
        Integer posYRover = forward(rover.getPosY(), mapProperties.getHeight());

        if (roverValidation.isOccupied(posXRover, posYRover)) {
            return true;
        }

        rover.setPosY(posYRover);
        return false;
    }

}
