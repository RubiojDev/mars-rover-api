package com.rubio.marsroverapi.obstacle.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO del Modelo {@link com.rubio.marsroverapi.obstacle.models.Obstacle} usado para la entrada y salida de datos.
 * <p>
 * Representa la posicion de un Obstaculo en el mapa mediante sus
 * coordenadas X e Y.
 * <p>
 * Ambos Valores son obligatorios.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObstacleDto {
    @NotNull(message = "Axis X Required")
    private Integer posX;
    @NotNull(message = "Axis Y Required")
    private Integer posY;
}
