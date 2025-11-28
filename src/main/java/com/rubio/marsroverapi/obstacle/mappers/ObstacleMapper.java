package com.rubio.marsroverapi.obstacle.mappers;

import com.rubio.marsroverapi.obstacle.dto.ObstacleDto;
import com.rubio.marsroverapi.obstacle.models.Obstacle;

/**
 * Mapper encargado de convertir entre entidades {@link Obstacle} y
 * sus respectivos DTOs {@link ObstacleDto}.
 */
public interface ObstacleMapper {

    /**
     * Convierte una Entidad {@link Obstacle} a su representacion DTO
     * @param obstacle Entidad a convertir.
     * @return ObstacleDto convertido.
     */
    ObstacleDto toDto(Obstacle obstacle);

    /**
     * Convierte un DTO {@link ObstacleDto} a su Entidad correspondiente.
     * @param obstacleDto DTO a convertir.
     * @return Obstacle convertido.
     */
    Obstacle toEntity(ObstacleDto obstacleDto);

}
