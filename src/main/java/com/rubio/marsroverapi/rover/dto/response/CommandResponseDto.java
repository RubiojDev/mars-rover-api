package com.rubio.marsroverapi.rover.dto.response;

import com.rubio.marsroverapi.rover.dto.RoverDto;
import lombok.Data;

/**
 * DTO que representa la respuesta generada luego de que el rover ejecute
 * los comandos ingresados.
 * <p>
 * Está compuesto por:
 * <ul>
 *     <li><code>RoverDto</code>: representación del estado actual del rover depués de ejecutar los comandos.</li>
 *     <li><code>obstacleEncountered</code>: indica si se encontró un obstaculo durante su recorrido.</li>
 * </ul>
 */
@Data
public class CommandResponseDto {
    private RoverDto roverDto;
    private boolean obstacleEncountered;
}
