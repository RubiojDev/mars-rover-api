package com.rubio.marsroverapi.obstacle.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObstacleDto {
    @NotNull(message = "Axis X Required")
    private Integer posX;
    @NotNull(message = "Axis Y Required")
    private Integer posY;
}
