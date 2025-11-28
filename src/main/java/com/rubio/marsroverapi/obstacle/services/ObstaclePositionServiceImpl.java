package com.rubio.marsroverapi.obstacle.services;

import com.rubio.marsroverapi.obstacle.repositories.ObstacleRepository;
import org.springframework.stereotype.Service;

/**
 * Implementación de {@link ObstaclePositionService}.
 * <p>
 * Realiza una consulta en la base de datos y verifica si existe
 * un obstáculo en la posición especificada.
 */
@Service
public class ObstaclePositionServiceImpl implements ObstaclePositionService {
    private final ObstacleRepository repository;

    public ObstaclePositionServiceImpl(ObstacleRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isObstacleAt(int posX, int posY) {
        return repository.existsByPosXAndPosY(posX, posY);
    }
}
