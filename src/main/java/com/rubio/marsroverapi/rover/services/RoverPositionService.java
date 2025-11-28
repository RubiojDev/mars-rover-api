package com.rubio.marsroverapi.rover.services;

/**
 * Servicio que verifica si el rover está en las coordenadas dadas.
 * <p>
 * Devuelve <code>true</code> si en la posición se encuentra el rover.
 */
public interface RoverPositionService {
    boolean isRoverAt(int posX, int posY);
}
