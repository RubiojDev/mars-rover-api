package com.rubio.marsroverapi.rover.services.components.movement;

import com.rubio.marsroverapi.config.MapProperties;
import com.rubio.marsroverapi.rover.models.Rover;
import com.rubio.marsroverapi.rover.validations.RoverValidation;

import static com.rubio.marsroverapi.rover.utilities.CircularPosition.forward;

/**
 * Implementación de {@link MoveStrategy} que mueve al rover en dirección este.
 * <p>
 * Calcula la nueva posición del rover aplicando desplazamiento circular sobre el eje X,
 * tomando en cuenta el ancho del mapa. Antes de moverlo valida si la nueva
 * posición está ocupada por un obstáculo.
 * <p>
 * Retorna {@code false} cuando el rover puede avanzar sin interferencias,
 * y {@code true} cuando la posición objetivo está ocupada y el movimiento no se realiza.
 */
public class MoveEastStrategy implements MoveStrategy {

    private final RoverValidation roverValidation;
    private final MapProperties mapProperties;

    public MoveEastStrategy(RoverValidation roverValidation, MapProperties mapProperties) {
        this.roverValidation = roverValidation;
        this.mapProperties = mapProperties;
    }

    /**
     * Mueve el rover una unidad hacia el este.
     * <p>
     * Aplica el desplazamiento circular según el ancho del mapa y verifica
     * que la posición resultante no esté ocupada antes de actualizar la posición.
     *
     * @param rover entidad que será desplazada.
     * @return {@code true} si la nueva posición está ocupada y el rover no se mueve;<br>
     *         {@code false} si el movimiento se realiza exitosamente.
     */
    @Override
    public boolean move(Rover rover) {

        Integer posXRover = forward(rover.getPosX(), mapProperties.getWidth());
        Integer posYRover = rover.getPosY();

        if (roverValidation.isOccupied(posXRover, posYRover)) {
            return true;
        }

        rover.setPosX(posXRover);
        return false;
    }

}
