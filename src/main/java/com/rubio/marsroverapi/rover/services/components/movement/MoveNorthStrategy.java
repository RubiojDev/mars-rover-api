package com.rubio.marsroverapi.rover.services.components.movement;

import com.rubio.marsroverapi.config.MapProperties;
import com.rubio.marsroverapi.rover.models.Rover;
import com.rubio.marsroverapi.rover.validations.RoverValidation;

import static com.rubio.marsroverapi.rover.utilities.CircularPosition.backward;

public class MoveNorthStrategy implements MoveStrategy {

    private final RoverValidation roverValidation;
    private final MapProperties mapProperties;

    public MoveNorthStrategy(RoverValidation roverValidation, MapProperties mapProperties) {
        this.roverValidation = roverValidation;
        this.mapProperties = mapProperties;
    }

    @Override
    public boolean move(Rover rover) {

        Integer posXRover = rover.getPosX();
        Integer posYRover = backward(rover.getPosY(), mapProperties.getHeight());

        if (roverValidation.isOccupied(posXRover, posYRover)) {
            return true;
        }

        rover.setPosY(posYRover);
        return false;
    }

}
