package com.rubio.marsroverapi.obstacle.validations;

import com.rubio.marsroverapi.config.MapProperties;
import com.rubio.marsroverapi.shared.exceptions.ObstacleCollisionException;
import org.springframework.stereotype.Component;

/**
 * Implementación de {@link ObstacleValidation}.
 * <p>
 * Ejecuta la validación en las coordenadas de los ejes X e Y
 * para evitar el ingreso erróneo de posiciones fuera de los limites del mapa.
 */
@Component
public class BoundsObstacleValidation implements ObstacleValidation {

    private final MapProperties mapProperties;

    public BoundsObstacleValidation(MapProperties mapProperties) {
        this.mapProperties = mapProperties;
    }

    /**
     * Valida las coordenadas dadas y en caso de ser inválidas
     * lanza una excepción personalizada: <code>ObstacleCollisionException</code>.
     * @param posX Coordenada X dentro del mapa.
     * @param posY Coordenada Y dentro del mapa.
     * @throws ObstacleCollisionException Si las coordenadas se encuentran fuera de los limites del mapa
     */
    @Override
    public void isValid(int posX, int posY) {
        final int limitMapX = mapProperties.getWidth();
        final int limitMapY = mapProperties.getHeight();

        if ((posX < 0 || posY < 0 || posX >= limitMapX || posY >= limitMapY)) {
            throw new ObstacleCollisionException(
                    "Cannot create an obstacle outside the map (" + posX + ", " + posY + ")"
            );
        }
    }

}
