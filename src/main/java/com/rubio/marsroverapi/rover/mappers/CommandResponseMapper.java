package com.rubio.marsroverapi.rover.mappers;

import com.rubio.marsroverapi.rover.dto.response.CommandResponseDto;
import com.rubio.marsroverapi.rover.models.Rover;

/**
 * Interfaz que mapea manualmente los datos del rover a un DTO de respuesta
 * {@link CommandResponseDto} después de ejecutar los comandos.
 * <p>
 * Este mapper toma la entidad {@link Rover} y un indicador de si se encontró
 * un obstáculo, y genera un DTO listo para ser retornado por los endpoints.
 */
public interface CommandResponseMapper {
    CommandResponseDto toDto(Rover rover, boolean isObstacleEncountered);
}
