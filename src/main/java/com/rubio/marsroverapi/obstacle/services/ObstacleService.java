package com.rubio.marsroverapi.obstacle.services;

import com.rubio.marsroverapi.obstacle.dto.ObstacleDto;

import java.util.List;

/**
 * Servicio para la entidad {@link com.rubio.marsroverapi.obstacle.models.Obstacle}.
 * <p>
 * Proporciona metodos para gestionar los obstaculos dentro del mapa:
 * <ul>
 *     <li><code>findAllObstacles</code>: obtiene la lista completa de obstáculos.</li>
 *     <li><code>createObstacle</code>: crea un nuevo obstáculo en una posición dada.</li>
 *     <li><code>deleteAllObstacles</code>: elimina todos los obstáculos existentes.</li>
 * </ul>
 */
public interface ObstacleService {
    List<ObstacleDto> findAllObstacles();

    ObstacleDto createObstacle(int posX, int posY);

    String deleteAllObstacles();
}
