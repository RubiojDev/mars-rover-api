package com.rubio.marsroverapi.obstacle.validations;

import com.rubio.marsroverapi.obstacle.repositories.ObstacleRepository;
import com.rubio.marsroverapi.rover.services.RoverPositionService;
import com.rubio.marsroverapi.shared.exceptions.ObstacleCollisionException;
import org.springframework.stereotype.Component;

/**
 * Implementación de {@link ObstacleValidation}.
 * <p>
 * Ejecuta una validación a través de consultas en la base de datos y servicios
 * auxiliares para determinar si la posición indicada ya se encuentra ocupada
 * por un obstáculo o por el rover.
 */
@Component
public class OccupiedSpaceValidation implements ObstacleValidation {
    private final ObstacleRepository repository;
    private final RoverPositionService roverPositionService;

    public OccupiedSpaceValidation(ObstacleRepository repository, RoverPositionService roverPositionService) {
        this.repository = repository;
        this.roverPositionService = roverPositionService;
    }

    /**
     * Valida que la coordenada especificada no esté ocupada por ningún
     * obstáculo registrado en la base de datos ni por el rover.
     * <p>
     * Si la posición está ocupada, se lanzará la excepción personalizada
     * {@link ObstacleCollisionException}.
     *
     * @param posX coordenada del eje X dentro del mapa
     * @param posY coordenada del eje Y dentro del mapa
     * @throws ObstacleCollisionException si la coordenada ya está ocupada
     */
    @Override
    public void isValid(int posX, int posY) {
        boolean obstaclePresent = repository.existsByPosXAndPosY(posX, posY);
        boolean roverPresent = roverPositionService.isRoverAt(posX, posY);

        if (obstaclePresent || roverPresent) {
            throw new ObstacleCollisionException(
                    "The coordinate (" + posX + ", " + posY + ") is already occupied"
            );
        }
    }

}
