package com.rubio.marsroverapi.rover.services;

import com.rubio.marsroverapi.rover.dto.RoverDto;
import com.rubio.marsroverapi.rover.dto.request.CommandRequestDto;
import com.rubio.marsroverapi.rover.dto.response.CommandResponseDto;

/**
 * Servicio de la entidad {@link com.rubio.marsroverapi.rover.models.Rover}.
 * <p>
 * Proporciona los métodos necesarios para obtener el estado actual del rover
 * y procesar la ejecución de comandos sobre él.
 */
public interface RoverService {
    RoverDto findRover();

    CommandResponseDto setCommand(CommandRequestDto commandList);
}
