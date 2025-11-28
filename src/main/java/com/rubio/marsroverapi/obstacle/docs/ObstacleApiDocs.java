package com.rubio.marsroverapi.obstacle.docs;

import com.rubio.marsroverapi.obstacle.dto.ObstacleDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name="Obstacle", description = "Endpoint para gestionar los obstaculos")
public interface ObstacleApiDocs {

    @Operation(
            summary = "Obtiene todos los obstaculos",
            description = "Devuelve todos los obstaculos dentro del mapa",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Consulta exitosa",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ObstacleDto.class))
                            )
                    )
            }
    )
    ResponseEntity<List<ObstacleDto>> findAllObstacles();

    @Operation(
            summary = "Crea un obstaculo",
            description = "Recibe las coordenadas del obstaculo para poder ser creada",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Coordenadas del obstaculo",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ObstacleDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Obstaculo creado con exito",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ObstacleDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Error en alguna coordenada X ó Y",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            value = "{\"message\":\"The coordinate (X, Y) is necessary\"}"
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Coordenadas X e Y invalidas",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            value = "{\"message\":\"The coordinate (X, Y) is already occupied\"}"
                                    )
                            )
                    )
            }
    )
    ResponseEntity<ObstacleDto> createObstacle(@Valid @RequestBody ObstacleDto obstacleRequest);

    @Operation(
            summary = "Borra todos los obtaculos",
            description = "Borra todos los obstaculos dentro del mapa",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Borrado exitoso",
                            content = @Content(
                                    mediaType = "text/plain",
                                    examples = @ExampleObject(
                                            value = "Deleted Successfully"
                                    )
                            )
                    )
            }
    )
    ResponseEntity<String> deleteAllObstacles();
}
