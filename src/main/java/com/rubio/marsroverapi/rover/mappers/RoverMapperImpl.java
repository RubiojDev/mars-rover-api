package com.rubio.marsroverapi.rover.mappers;

import com.rubio.marsroverapi.rover.dto.RoverDto;
import com.rubio.marsroverapi.rover.models.Rover;
import org.springframework.stereotype.Component;

/**
 * Implementacion del {@link RoverMapper}.
 * <p>
 * Proporciona la conversión manual entre la entidad {@link Rover} y su
 * representación como DTO {@link RoverDto}.
 * <p>
 * Esta conversión puede ser de manera bidireccional.
 */
@Component
public class RoverMapperImpl implements RoverMapper {

    @Override
    public RoverDto toDto(Rover rover) {
        if (rover == null) return null;

        RoverDto dto = new RoverDto();
        dto.setPosX(rover.getPosX());
        dto.setPosY(rover.getPosY());
        dto.setDirection(rover.getDirection());

        return dto;
    }

    @Override
    public Rover toEntity(RoverDto roverDto) {
        if (roverDto == null) return null;

        Rover entity = new Rover();
        entity.setPosX(roverDto.getPosX());
        entity.setPosY(roverDto.getPosY());
        entity.setDirection(roverDto.getDirection());

        return entity;
    }

}
