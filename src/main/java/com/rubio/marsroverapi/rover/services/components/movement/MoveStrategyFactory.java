package com.rubio.marsroverapi.rover.services.components.movement;

import com.rubio.marsroverapi.config.MapProperties;
import com.rubio.marsroverapi.rover.models.RoverDirectionEnum;
import com.rubio.marsroverapi.rover.validations.RoverValidation;
import org.springframework.stereotype.Component;

@Component
public class MoveStrategyFactory {

    private final RoverValidation roverValidation;
    private final MapProperties mapProperties;

    public MoveStrategyFactory(RoverValidation roverValidation, MapProperties mapProperties) {
        this.roverValidation = roverValidation;
        this.mapProperties = mapProperties;
    }

    public MoveStrategy getStrategy(RoverDirectionEnum direction) {
        return switch (direction) {
            case EAST -> new MoveEastStrategy(roverValidation, mapProperties);
            case WEST -> new MoveWestStrategy(roverValidation, mapProperties);
            case NORTH -> new MoveNorthStrategy(roverValidation, mapProperties);
            case SOUTH -> new MoveSouthStrategy(roverValidation, mapProperties);
        };
    }

}
