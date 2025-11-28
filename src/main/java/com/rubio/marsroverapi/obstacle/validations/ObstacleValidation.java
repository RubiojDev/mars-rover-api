package com.rubio.marsroverapi.obstacle.validations;

/**
 * Define una validación que se aplica sobre las coordenadas del eje X e Y
 * para determinar si una posición del mapa es válida.
 */
public interface ObstacleValidation {
    void isValid(int posX, int posY);
}
