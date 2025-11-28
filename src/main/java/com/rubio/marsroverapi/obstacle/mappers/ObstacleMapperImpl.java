package com.rubio.marsroverapi.obstacle.mappers;

import com.rubio.marsroverapi.obstacle.dto.ObstacleDto;
import com.rubio.marsroverapi.obstacle.models.Obstacle;
import org.springframework.stereotype.Component;

/**
 * Implementacion Manual de {@link ObstacleMapper}.
 * <p>
 * Realiza un Mapeo entre los campos equivalentes
 * de la Entidad y el DTO.
 */
@Component
public class ObstacleMapperImpl implements ObstacleMapper {

    @Override
    public ObstacleDto toDto(Obstacle obstacle) {
        if (obstacle == null) return null;

        ObstacleDto dto = new ObstacleDto();
        dto.setPosX(obstacle.getPosX());
        dto.setPosY(obstacle.getPosY());

        return dto;
    }

    @Override
    public Obstacle toEntity(ObstacleDto obstacleDto) {
        if (obstacleDto == null) return null;

        Obstacle entity = new Obstacle();
        entity.setPosX(obstacleDto.getPosX());
        entity.setPosY(obstacleDto.getPosY());

        return entity;
    }

}
