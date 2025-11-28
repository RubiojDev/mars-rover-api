package com.rubio.marsroverapi.rover.validations;

/**
 * Valida si una posición dentro del mapa
 * se encuentra ocupada por un obstáculo que impida
 * el movimiento del rover.
 */
public interface RoverValidation {
    boolean isOccupied(int posX, int posY);
}
