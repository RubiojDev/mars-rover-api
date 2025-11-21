package com.rubio.marsroverapi.rover.services.components.movement;

import com.rubio.marsroverapi.config.MapProperties;
import com.rubio.marsroverapi.rover.models.Rover;
import com.rubio.marsroverapi.rover.validations.RoverValidation;

import static com.rubio.marsroverapi.rover.utilities.CircularPosition.forward;

public class MoveEastStrategy implements MoveStrategy {

    private final RoverValidation roverValidation;
    private final MapProperties mapProperties;

    public MoveEastStrategy(RoverValidation roverValidation, MapProperties mapProperties) {
        this.roverValidation = roverValidation;
        this.mapProperties = mapProperties;
    }

    @Override
    public boolean move(Rover rover) {

        Integer posXRover = forward(rover.getPosX(), mapProperties.getWidth());
        Integer posYRover = rover.getPosY();

        if (roverValidation.isOccupied(posXRover, posYRover)) {
            return true;
        }

        rover.setPosX(posXRover);
        return false;
    }

}
