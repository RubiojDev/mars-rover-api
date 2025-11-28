package com.rubio.marsroverapi.rover.mappers;

import com.rubio.marsroverapi.rover.dto.RoverDto;
import com.rubio.marsroverapi.rover.models.Rover;

/**
 * Interace que mapea manualmente un {@link Rover}
 * y su representación como DTO {@link RoverDto} y viceversa.
 */
public interface RoverMapper {
    RoverDto toDto(Rover rover);

    Rover toEntity(RoverDto roverDto);
}
