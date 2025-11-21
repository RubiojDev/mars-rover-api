package com.rubio.marsroverapi.rover.services.components.movement;

import com.rubio.marsroverapi.config.MapProperties;
import com.rubio.marsroverapi.rover.models.Rover;
import com.rubio.marsroverapi.rover.validations.RoverValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MoveSouthStrategyTest {
    @InjectMocks
    private MoveSouthStrategy moveStrategy;
    @Mock
    private RoverValidation roverValidation;
    @Mock
    private MapProperties mapProperties;
    @Mock
    private Rover rover;

    @BeforeEach
    void setUp() {
        lenient().when(mapProperties.getHeight()).thenReturn(5);
    }

    @Test
    public void testMove_WhenValidationIsFalse() {
        //Antes
        int posX = 2;
        int posYBefore = 4; //Considerar la dimension del Mapa
        int posYAfter = 0;

        //Entonces
        when(rover.getPosX()).thenReturn(posX);
        when(rover.getPosY()).thenReturn(posYBefore);
        when(roverValidation.isOccupied(posX, posYAfter)).thenReturn(false);
        boolean result = moveStrategy.move(rover);

        //Despues
        Assertions.assertFalse(result);
        verify(rover).setPosY(posYAfter);
    }

    @Test
    public void testMove_WhenValidationIsTrue() {
        //Antes
        int posX = 2;
        int posYBefore = 4; //Considerar la dimension del Mapa
        int posYAfter = 0;

        //Entonces
        when(rover.getPosX()).thenReturn(posX);
        when(rover.getPosY()).thenReturn(posYBefore);
        when(roverValidation.isOccupied(posX, posYAfter)).thenReturn(true);
        boolean result = moveStrategy.move(rover);

        //Despues
        Assertions.assertTrue(result);
        verify(rover, never()).setPosY(posYAfter);
    }

}