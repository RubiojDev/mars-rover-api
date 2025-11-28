package com.rubio.marsroverapi.rover.dto.request;

import lombok.Data;

/**
 * DTO que representa la entrada de los comandos que el rover deberá ejecutar.
 * <p>
 * Está compuesto por un arreglo de cadenas que indican
 * las instrucciones enviadas al rover.
 */
@Data
public class CommandRequestDto {
    private String[] commandList;
}
