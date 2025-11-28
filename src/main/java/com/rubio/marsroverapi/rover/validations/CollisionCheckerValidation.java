package com.rubio.marsroverapi.rover.validations;

import com.rubio.marsroverapi.obstacle.services.ObstaclePositionService;
import org.springframework.stereotype.Component;

/**
 * Implementación de {@link RoverValidation} encargada de verificar
 * si el rover colisionaría al intentar ocupar una determinada posición.
 * <p>
 * Utiliza el {@link ObstaclePositionService} para consultar si las
 * coordenadas especificadas del mapa se encuentran ocupadas por un obstáculo.
 */
@Component
public class CollisionCheckerValidation implements RoverValidation {
    private final ObstaclePositionService obstaclePositionService;

    public CollisionCheckerValidation(ObstaclePositionService obstaclePositionService) {
        this.obstaclePositionService = obstaclePositionService;
    }

    /**
     * Determina si en las coordenadas del eje X e Y se encuentra un obstaculo.
     * @param posX coordenada del eje X dentro del mapa
     * @param posY coordenada del eje Y dentro del mapa
     * @return {@code true} si en las coordenadas ya hay un obstaculo;<br>
     *          {@code false} si se encuentra libre el espacio en las coordenadas dadas.
     */
    @Override
    public boolean isOccupied(int posX, int posY) {
        return obstaclePositionService.isObstacleAt(posX, posY);
    }

}
