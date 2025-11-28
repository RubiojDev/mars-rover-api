package com.rubio.marsroverapi.rover.services.components.movement;

import com.rubio.marsroverapi.rover.models.Rover;

/**
 * Define la estrategia de movimiento a aplicar sobre un {@link Rover}.
 * <p>
 * Cada implementación de esta interfaz representa el movimiento del rover
 * en una dirección específica (norte, sur, este u oeste), siguiendo el
 * patrón de diseño Strategy.
 */
public interface MoveStrategy {
    boolean move(Rover rover);
}
