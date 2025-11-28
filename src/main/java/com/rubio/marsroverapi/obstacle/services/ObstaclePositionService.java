package com.rubio.marsroverapi.obstacle.services;

/**
 * Verifica si en las coordenadas dadas existe algún obstáculo.
 * <p>
 * Devuelve <code>true</code> si la posición está ocupada por un obstáculo.
 */
public interface ObstaclePositionService {
    boolean isObstacleAt(int posX, int posY);
}
