package com.rubio.marsroverapi.rover.dto;

import com.rubio.marsroverapi.rover.models.RoverDirectionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que representa al modelo {@link com.rubio.marsroverapi.rover.models.Rover}
 * utilizado para la entrada y salida de datos.
 * <p>
 * Contiene la información esencial del rover:
 * <ul>
 *     <li><code>posX</code>: coordenada del eje X dentro del mapa.</li>
 *     <li><code>posY</code>: coordenada del eje Y dentro del mapa.</li>
 *     <li><code>direction</code>: dirección a la cual el rover apunta.</li>
 * </ul>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoverDto {
    private Integer posX;
    private Integer posY;
    private RoverDirectionEnum direction;
}
