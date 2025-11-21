package com.rubio.marsroverapi.obstacle.validations;

import com.rubio.marsroverapi.config.MapProperties;
import com.rubio.marsroverapi.shared.exceptions.ObstacleCollisionException;
import org.springframework.stereotype.Component;

@Component
public class BoundsObstacleValidation implements ObstacleValidation {

    private final MapProperties mapProperties;

    public BoundsObstacleValidation(MapProperties mapProperties) {
        this.mapProperties = mapProperties;
    }

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
