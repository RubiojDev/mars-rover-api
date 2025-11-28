package com.rubio.marsroverapi.rover.services.components.movement;

import com.rubio.marsroverapi.config.MapProperties;
import com.rubio.marsroverapi.rover.models.RoverDirectionEnum;
import com.rubio.marsroverapi.rover.validations.RoverValidation;
import org.springframework.stereotype.Component;

/**
 * Fábrica responsable de proporcionar la estrategia de movimiento adecuada
 * según la dirección en la que el rover debe desplazarse.
 * <p>
 * Las direcciones soportadas son: Norte, Sur, Este y Oeste. Cada una de ellas
 * corresponde a una implementación específica de {@link MoveStrategy}.
 */
@Component
public class MoveStrategyFactory {

    private final RoverValidation roverValidation;
    private final MapProperties mapProperties;

    public MoveStrategyFactory(RoverValidation roverValidation, MapProperties mapProperties) {
        this.roverValidation = roverValidation;
        this.mapProperties = mapProperties;
    }

    /**
     * Obtiene la estrategia de movimiento correspondiente a la dirección indicada.
     *
     * @param direction dirección hacia la cual el rover desea desplazarse.
     * @return una instancia de {@link MoveStrategy} configurada para la dirección especificada.
     */
    public MoveStrategy getStrategy(RoverDirectionEnum direction) {
        return switch (direction) {
            case EAST -> new MoveEastStrategy(roverValidation, mapProperties);
            case WEST -> new MoveWestStrategy(roverValidation, mapProperties);
            case NORTH -> new MoveNorthStrategy(roverValidation, mapProperties);
            case SOUTH -> new MoveSouthStrategy(roverValidation, mapProperties);
        };
    }

}
